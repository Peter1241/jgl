package jgl.geometry;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import jgl.math.vector.ConstVec3f;
import jgl.math.vector.Vec3f;

/**
 * A vertex that contains a position, normal, and color.
 * 
 * @author justin
 */
public class VertexPNC extends VertexPN {

  public Vec3f color = new Vec3f();

  public VertexPNC position(float x, float y, float z) {
    position.set(x, y, z);
    return this;
  }
  
  public VertexPNC position(ConstVec3f p) {
    position.set(p);
    return this;
  }
  
  public VertexPNC normal(float x, float y, float z) {
    normal.set(x, y, z);
    return this;
  }
  
  public VertexPNC normal(ConstVec3f n) {
    normal.set(n);
    return this;
  }

  public VertexPNC color(float r, float g, float b) {
    color.set(r, g, b);
    return this;
  }

  public VertexPNC color(ConstVec3f c) {
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
  
  public static class GeometryPNC extends Geometry<VertexPNC> {
    public GeometryPNC(Primitive type, int numVertices, int numIndices) {
      super(type, VertexPNC.CONSTRUCTOR, numVertices, numIndices);
    }
  }
  
  public static final Constructor<VertexPNC> CONSTRUCTOR = new Constructor<VertexPNC>() {
    public VertexPNC construct() {
      return new VertexPNC();
    }
  };
}
