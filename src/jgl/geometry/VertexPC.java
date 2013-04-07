package jgl.geometry;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import jgl.math.vector.ConstVec3f;
import jgl.math.vector.Vec3f;


/**
 * A vertex that contains a position and color.
 * 
 * @author justin
 */
public class VertexPC extends VertexP {

  public Vec3f color = new Vec3f(0);
  
  public VertexPC position(float x, float y, float z) {
    position.set(x, y, z);
    return this;
  }
  
  public VertexPC position(ConstVec3f p) {
    position.set(p);
    return this;
  }
  
  public VertexPC color(float r, float g, float b) {
    color.set(r, g, b);
    return this;
  }
  
  public VertexPC color(ConstVec3f c) {
    color.set(c);
    return this;
  }
  
  @Override
  public int stride() {
    return super.stride() + 12;
  }

  @Override
  public void put(ByteBuffer buffer) {
    super.put(buffer);
    buffer.putFloat(color.x);
    buffer.putFloat(color.y);
    buffer.putFloat(color.z);
  }

  @Override
  public void get(ByteBuffer buffer) {
    super.get(buffer);
    color.x = buffer.getFloat();
    color.y = buffer.getFloat();
    color.z = buffer.getFloat();
  }

  @Override
  public void drawImmediate(GL2 gl) {
    gl.glColor3f(color.x, color.y, color.z);
    super.drawImmediate(gl);
  }

  @Override
  public void startArrays(GL2 gl) {
    super.startArrays(gl);
    gl.glEnableClientState(GL2.GL_COLOR_ARRAY);
    gl.glColorPointer(3, GL.GL_FLOAT, stride(), super.stride());
  }

  @Override
  public void startArrays(GL2 gl, Buffer vertices) {
    super.startArrays(gl, vertices);
    gl.glEnableClientState(GL2.GL_COLOR_ARRAY);
    gl.glColorPointer(3, GL.GL_FLOAT, stride(), vertices.position(super.stride()));
  }

  @Override
  public void endArrays(GL2 gl) {
    super.endArrays(gl);
    gl.glDisableClientState(GL2.GL_COLOR_ARRAY);
  }
  
  public static class GeometryPC extends Geometry<VertexPC> {
    public GeometryPC(Primitive type, int numVertices, int numIndices) {
      super(type, VertexPC.CONSTRUCTOR, numVertices, numIndices);
    }
  }
  
  public static final Constructor<VertexPC> CONSTRUCTOR = new Constructor<VertexPC>() {
    public VertexPC construct() {
      return new VertexPC();
    }
  };
}
