/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.geometry;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import jgl.math.vector.ConstVec2f;
import jgl.math.vector.ConstVec3f;
import jgl.math.vector.Mat4f;
import jgl.math.vector.Vec2f;
import jgl.math.vector.Vec3f;

/**
 * A vertex that contains a position, normal, and texture coordinates.
 * 
 * @author justin
 */
public class VertexPNT implements Vertex {
  
  /**
   * Geometry with the VertexPNT type
   */
  public static class GeometryPNT extends Geometry<VertexPNT> {
    public GeometryPNT(Primitive type, int numVertices, int numIndices) {
      super(type, VertexPNT.CONSTRUCTOR, numVertices, numIndices);
    }
  }
  
  public static final Constructor<VertexPNT> CONSTRUCTOR = new Constructor<VertexPNT>() {
    public VertexPNT construct() {
      return new VertexPNT();
    }
  };
  
  public Vec3f position = new Vec3f(0);
  public Vec3f normal = new Vec3f(0);
  public Vec2f texCoords = new Vec2f(0);
  
  public VertexPNT position(float x, float y, float z) {
    position.x = x;
    position.y = y;
    position.z = z;
    return this;
  }
  
  public VertexPNT position(ConstVec3f p) {
    return position(p.x(), p.y(), p.z());
  }
  
  public VertexPNT normal(float x, float y, float z) {
    normal.x = x;
    normal.y = y;
    normal.z = z;
    return this;
  }
  
  public VertexPNT normal(ConstVec3f p) {
    return normal(p.x(), p.y(), p.z());
  }
  
  public VertexPNT texCoords(float x, float y) {
    texCoords.x = x;
    texCoords.y = y;
    return this;
  }
  
  public VertexPNT texCoords(ConstVec2f p) {
    return texCoords(p.x(), p.y());
  }
  
  @Override
  public int stride() {
    return 32;  // 8 floats * 4 bytes per float
  }

  @Override
  public void put(ByteBuffer buffer) {
    buffer.putFloat(position.x);
    buffer.putFloat(position.y);
    buffer.putFloat(position.z);
    buffer.putFloat(normal.x);
    buffer.putFloat(normal.y);
    buffer.putFloat(normal.z);
    buffer.putFloat(texCoords.x);
    buffer.putFloat(texCoords.y);
  }

  @Override
  public void get(ByteBuffer buffer) {
    position.x = buffer.getFloat();
    position.y = buffer.getFloat();
    position.z = buffer.getFloat();
    normal.x = buffer.getFloat();
    normal.y = buffer.getFloat();
    normal.z = buffer.getFloat();
    texCoords.x = buffer.getFloat();
    texCoords.y = buffer.getFloat();
  }
  
  @Override
  public void drawImmediate(GL2 gl) {
    gl.glTexCoord2f(texCoords.x, texCoords.y);
    gl.glNormal3f(normal.x, normal.y, normal.z);
    gl.glVertex3f(position.x, position.y, position.z);
  }

  @Override
  public void startArrays(GL2 gl) {
    gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
    gl.glVertexPointer(3, GL.GL_FLOAT, stride(), 0);
    gl.glEnableClientState(GL2.GL_NORMAL_ARRAY);
    gl.glNormalPointer(GL.GL_FLOAT, stride(), 12);
    gl.glEnableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
    gl.glTexCoordPointer(2, GL.GL_FLOAT, stride(), 24);
  }

  @Override
  public void startArrays(GL2 gl, Buffer vertices) {
    gl.glEnableClientState(GL2.GL_VERTEX_ARRAY); 
    gl.glVertexPointer(3, GL.GL_FLOAT, stride(), vertices.position(0));
    gl.glEnableClientState(GL2.GL_NORMAL_ARRAY);
    gl.glNormalPointer(GL.GL_FLOAT, stride(), vertices.position(12));
    gl.glEnableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
    gl.glTexCoordPointer(2, GL.GL_FLOAT, stride(), vertices.position(24));
  }

  @Override
  public void endArrays(GL2 gl) {
    gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
    gl.glDisableClientState(GL2.GL_NORMAL_ARRAY);
    gl.glDisableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
  }
  
  @Override
  public void transform(Mat4f matrix) {
    position = matrix.times(position.x, position.y, position.z, 1).xyz();
    normal = matrix.times(normal);
  }
}
