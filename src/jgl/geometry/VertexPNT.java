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
import jgl.math.vector.Vec2f;

/**
 * A vertex that contains a position, normal, and texture coordinates.
 * 
 * @author justin
 */
public class VertexPNT extends VertexPN {
  
  public Vec2f texCoords = new Vec2f(0);
  
  public VertexPNT position(float x, float y, float z) {
    position.set(x, y, z);
    return this;
  }
  
  public VertexPNT position(ConstVec3f p) {
    position.set(p);
    return this;
  }
  
  public VertexPNT normal(float x, float y, float z) {
    normal.set(x, y, z);
    return this;
  }
  
  public VertexPNT normal(ConstVec3f n) {
    normal.set(n);
    return this;
  }

  public VertexPNT texCoords(float u, float v) {
    texCoords.set(u, v);
    return this;
  }

  public VertexPNT texCoords(ConstVec2f tc) {
    texCoords.set(tc);
    return this;
  }
  
  @Override
  public int stride() {
    return super.stride() + 8;
  }

  @Override
  public void put(ByteBuffer buffer) {
    super.put(buffer);
    buffer.putFloat(texCoords.x);
    buffer.putFloat(texCoords.y);
  }

  @Override
  public void get(ByteBuffer buffer) {
    super.get(buffer);
    texCoords.x = buffer.getFloat();
    texCoords.y = buffer.getFloat();
  }
  
  @Override
  public void drawImmediate(GL2 gl) {
    gl.glTexCoord2f(texCoords.x, texCoords.y);
    super.drawImmediate(gl);
  }

  @Override
  public void startArrays(GL2 gl) {
    super.startArrays(gl);
    gl.glEnableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
    gl.glTexCoordPointer(2, GL.GL_FLOAT, stride(), super.stride());
  }

  @Override
  public void startArrays(GL2 gl, Buffer vertices) {
    super.startArrays(gl, vertices);
    gl.glEnableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
    gl.glTexCoordPointer(2, GL.GL_FLOAT, stride(), vertices.position(super.stride()));
  }

  @Override
  public void endArrays(GL2 gl) {
    super.endArrays(gl);
    gl.glDisableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
  }
  
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
}
