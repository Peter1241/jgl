/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.scene.geometry;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import jgl.math.vector.Vec2f;
import jgl.math.vector.Vec3f;

/**
 * Stores geometry data: vertex positions, normals, texture coordinates, and face indices.
 * 
 * @author justin
 */
public abstract class Geometry {

  public enum PrimitiveType {
    POINTS(GL.GL_POINTS),
    LINES(GL.GL_LINES),
    LINE_LOOP(GL.GL_LINE_LOOP),
    LINE_STRIP(GL.GL_LINE_STRIP),
    TRIANGLES(GL.GL_TRIANGLES),
    TRIANGLE_FAN(GL.GL_TRIANGLE_FAN),
    TRIANGLE_STRIP(GL.GL_TRIANGLE_STRIP);

    public final int value;

    private PrimitiveType(int value) {
      this.value = value;
    }
  }

  public FloatBuffer   vertices;
  public FloatBuffer   normals;
  public FloatBuffer   texCoords;
  public IntBuffer     indices;
  public PrimitiveType type;

  protected void init(int numVertices, int numIndices, PrimitiveType type) {
    this.type = type;

    // each vertex position & normal has 3 values: x, y, z
    vertices = FloatBuffer.allocate(numVertices * 3);
    normals = FloatBuffer.allocate(numVertices * 3);
    // each vertex texture coordinate has 2 values: u, v
    texCoords = FloatBuffer.allocate(numVertices * 2);

    if (numIndices > 0)
      indices = IntBuffer.allocate(numIndices);
  }

  protected void rewindBuffers() {
    vertices.rewind();
    normals.rewind();
    texCoords.rewind();
    if (indices != null)
      indices.rewind();
  }

  /**
   * Calculates the number of primitives (points, lines, or triangles) in the geometry.
   */
  public int numPrimitives() {
    Buffer buffer = isIndexed() ? indices : vertices;
    switch (type) {
    case POINTS:
      return buffer.capacity() / 3;
    case LINES:
      return buffer.capacity() / 6;
    case LINE_LOOP:
      return buffer.capacity() / 3;
    case LINE_STRIP:
      return buffer.capacity() / 3 - 1;
    case TRIANGLES:
      return buffer.capacity() / 3;
    case TRIANGLE_FAN:
      return buffer.capacity() / 3 - 2;
    case TRIANGLE_STRIP:
      return buffer.capacity() / 3 - 2;
    default:
      return 0;
    }
  }

  /**
   * Calculates the number of vertices in the geometry.
   */
  public int numVertices() {
    return vertices.capacity() / 3;
  }

  /**
   * The number of indices in the geometry.
   */
  public int numIndices() {
    return indices.capacity();
  }

  /**
   * Returns true if indices are present.
   */
  public boolean isIndexed() {
    return indices != null;
  }
  
  /**
   * Gets the next vertex from the geometry, or null if there are no more.
   */
  public Vec3f nextVertex() {
    if (!vertices.hasRemaining())
      return null;
    return new Vec3f(vertices.get(), vertices.get(), vertices.get());
  }
  
  /**
   * Gets the next normal from the geometry, or null if there are no more.
   */
  public Vec3f nextNormal() {
    if (!normals.hasRemaining())
      return null;
    return new Vec3f(normals.get(), normals.get(), normals.get());
  }
  
  /**
   * Gets the next texture coordinate from the geometry, or null if there are no more.
   */
  public Vec2f nextTexCoord() {
    if (!texCoords.hasRemaining())
      return null;
    return new Vec2f(texCoords.get(), texCoords.get());
  }
  
  public Vec3f getVertex(int i) {
    int j = i * 3;
    return new Vec3f(vertices.get(j), vertices.get(j + 1), vertices.get(j + 2));
  }

  /**
   * Renders the geometry using immediate mode OpenGL. Useful for quick testing, but deprecated in
   * newer versions of OpenGL.
   */
  public void drawImmediate(GL2 gl) {
    gl.glBegin(type.value);
    if (isIndexed()) {
      for (int i = 0; i < numIndices(); i++)
        immedateVertex(gl, indices.get());
    } else {
      for (int i = 0; i < numVertices(); i++) {
        immedateVertex(gl, i);
      }
    }
    gl.glEnd();

    rewindBuffers();
  }

  private void immedateVertex(GL2 gl, int i) {
    normals.position(i * 3);
    texCoords.position(i * 2);
    vertices.position(i * 3);
    gl.glNormal3fv(normals);
    gl.glTexCoord2fv(texCoords);
    gl.glVertex3fv(vertices);
  }
}