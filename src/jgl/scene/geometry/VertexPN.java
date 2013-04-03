package jgl.scene.geometry;

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
public class VertexPN implements Vertex {

  /**
   * Geometry with the VertexPN type
   */
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
  
  public Vec3f normal = new Vec3f(0);
  public Vec3f position = new Vec3f(0);
  
  public VertexPN position(float x, float y, float z) {
    position.x = x;
    position.y = y;
    position.z = z;
    return this;
  }
  
  public VertexPN position(ConstVec3f p) {
    return position(p.x(), p.y(), p.z());
  }
  
  public VertexPN normal(float x, float y, float z) {
    normal.x = x;
    normal.y = y;
    normal.z = z;
    return this;
  }
  
  public VertexPN normal(ConstVec3f p) {
    return normal(p.x(), p.y(), p.z());
  }
  
  @Override
  public int stride() {
    return 24;  // 6 floats * 4 bytes per float
  }

  @Override
  public void put(ByteBuffer buffer) {
    buffer.putFloat(position.x);
    buffer.putFloat(position.y);
    buffer.putFloat(position.z);
    buffer.putFloat(normal.x);
    buffer.putFloat(normal.y);
    buffer.putFloat(normal.z);
  }

  @Override
  public void get(ByteBuffer buffer) {
    position.x = buffer.getFloat();
    position.y = buffer.getFloat();
    position.z = buffer.getFloat();
    normal.x = buffer.getFloat();
    normal.y = buffer.getFloat();
    normal.z = buffer.getFloat();
  }

  @Override
  public void drawImmediate(GL2 gl) {
    gl.glNormal3f(normal.x, normal.y, normal.z);
    gl.glVertex3f(position.x, position.y, position.z);
  }

  @Override
  public void startArrays(GL2 gl) {
    gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
    gl.glVertexPointer(3, GL.GL_FLOAT, stride(), 0);
    gl.glEnableClientState(GL2.GL_NORMAL_ARRAY);
    gl.glNormalPointer(GL.GL_FLOAT, stride(), 12);
  }

  @Override
  public void startArrays(GL2 gl, Buffer vertices) {
    gl.glEnableClientState(GL2.GL_VERTEX_ARRAY); 
    gl.glVertexPointer(3, GL.GL_FLOAT, stride(), vertices.position(0));
    gl.glEnableClientState(GL2.GL_NORMAL_ARRAY);
    gl.glNormalPointer(GL.GL_FLOAT, stride(), vertices.position(12));
  }

  @Override
  public void endArrays(GL2 gl) {
    gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
    gl.glDisableClientState(GL2.GL_NORMAL_ARRAY);
  }
  
  @Override
  public void transform(Mat4f matrix) {
    position = matrix.times(position.x, position.y, position.z, 1).xyz();
    normal = matrix.times(normal);
  }
}

