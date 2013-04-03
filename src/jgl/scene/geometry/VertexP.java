/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.scene.geometry;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import jgl.math.vector.ConstVec3f;
import jgl.math.vector.Mat4f;
import jgl.math.vector.Vec3f;

/**
 * A vertex that contains a position.
 * 
 * @author justin
 */
public class VertexP implements Vertex {

  /**
   * Geometry with the VertexP type
   */
  public static class GeometryP extends Geometry<VertexP> {
    public GeometryP(Primitive type, int numVertices, int numIndices) {
      super(type, VertexP.CONSTRUCTOR, numVertices, numIndices);
    }
  }
  
  public static final Constructor<VertexP> CONSTRUCTOR = new Constructor<VertexP>() {
    public VertexP construct() {
      return new VertexP();
    }
  };
  
  public Vec3f position = new Vec3f(0);
  
  public VertexP position(float x, float y, float z) {
    position.x = x;
    position.y = y;
    position.z = z;
    return this;
  }
  
  public VertexP position(ConstVec3f p) {
    return position(p.x(), p.y(), p.z());
  }
  
  @Override
  public int stride() {
    return 12;  // 3 floats * 4 bytes per float
  }

  @Override
  public void put(ByteBuffer buffer) {
    buffer.putFloat(position.x);
    buffer.putFloat(position.y);
    buffer.putFloat(position.z);
  }

  @Override
  public void get(ByteBuffer buffer) {
    position.x = buffer.getFloat();
    position.y = buffer.getFloat();
    position.z = buffer.getFloat();
  }

  @Override
  public void drawImmediate(GL2 gl) {
    gl.glVertex3f(position.x, position.y, position.z);
  }

  @Override
  public void startArrays(GL2 gl) {
    gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
    gl.glVertexPointer(3, GL.GL_FLOAT, stride(), 0);
  }

  @Override
  public void startArrays(GL2 gl, Buffer vertices) {
    gl.glEnableClientState(GL2.GL_VERTEX_ARRAY); 
    gl.glVertexPointer(3, GL.GL_FLOAT, stride(), vertices.position(0));
  }

  @Override
  public void endArrays(GL2 gl) {
    gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
  }

  @Override
  public void transform(Mat4f matrix) {
    position = matrix.times(position.x, position.y, position.z, 1).xyz();
  }
}
