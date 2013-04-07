/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.geometry;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import jgl.math.vector.ConstVec3f;
import jgl.math.vector.Mat4f;
import jgl.math.vector.Vec3f;

/**
 * A vertex that contains a position and normal.
 * 
 * @author justin
 */
public class VertexPN extends VertexP {

  public Vec3f normal = new Vec3f(0);
  
  public VertexPN position(float x, float y, float z) {
    position.set(x, y, z);
    return this;
  }
  
  public VertexPN position(ConstVec3f p) {
    position.set(p);
    return this;
  }
  
  public VertexPN normal(float x, float y, float z) {
    normal.set(x, y, z);
    return this;
  }
  
  public VertexPN normal(ConstVec3f n) {
    normal.set(n);
    return this;
  }
  
  @Override
  public int stride() {
    return super.stride() + 12;
  }

  @Override
  public void put(ByteBuffer buffer) {
    super.put(buffer);
    buffer.putFloat(normal.x);
    buffer.putFloat(normal.y);
    buffer.putFloat(normal.z);
  }

  @Override
  public void get(ByteBuffer buffer) {
    super.get(buffer);
    normal.x = buffer.getFloat();
    normal.y = buffer.getFloat();
    normal.z = buffer.getFloat();
  }

  @Override
  public void drawImmediate(GL2 gl) {
    gl.glNormal3f(normal.x, normal.y, normal.z);
    super.drawImmediate(gl);
  }

  @Override
  public void startArrays(GL2 gl) {
    super.startArrays(gl);
    gl.glEnableClientState(GL2.GL_NORMAL_ARRAY);
    gl.glNormalPointer(GL.GL_FLOAT, stride(), super.stride());
  }

  @Override
  public void startArrays(GL2 gl, Buffer vertices) {
    super.startArrays(gl, vertices);
    gl.glEnableClientState(GL2.GL_NORMAL_ARRAY);
    gl.glNormalPointer(GL.GL_FLOAT, stride(), vertices.position(super.stride()));
  }

  @Override
  public void endArrays(GL2 gl) {
    super.endArrays(gl);
    gl.glDisableClientState(GL2.GL_NORMAL_ARRAY);
  }
  
  @Override
  public void transform(Mat4f matrix) {
    super.transform(matrix);
    normal = matrix.times(normal);
  }
  
  public static class GeometryPN extends Geometry<VertexPN> {
    public GeometryPN(Primitive type, int numVertices, int numIndices) {
      super(type, VertexPN.CONSTRUCTOR, numVertices, numIndices);
    }
  }
  
  public static final Constructor<VertexPN> CONSTRUCTOR = new Constructor<VertexPN>() {
    public VertexPN construct() {
      return new VertexPN();
    }
  };
}

