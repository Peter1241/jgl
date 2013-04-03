/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.core;

import javax.media.opengl.GL2GL3;

import jgl.math.vector.ConstVec2f;
import jgl.math.vector.ConstVec3f;
import jgl.math.vector.ConstVec4f;

/**
 * References a generic vertex attribute.
 * 
 * @author justin
 */
public class Attribute {

  public final String name;
  public final int    size;
  public final int    type;
  public final int    index;

  Attribute(String name, int size, int type, int index) {
    this.name = name;
    this.size = size;
    this.type = type;
    this.index = index;
  }

  public void set(GL2GL3 gl, boolean normalized, int stride, long ptr) {
    gl.glVertexAttribPointer(index, size, type, normalized, stride, ptr);
  }

  public void set(GL2GL3 gl, float x) {
    gl.glVertexAttrib1f(index, x);
  }

  public void set(GL2GL3 gl, float x, float y) {
    gl.glVertexAttrib2f(index, x, y);
  }

  public void set(GL2GL3 gl, float x, float y, float z) {
    gl.glVertexAttrib3f(index, x, y, z);
  }

  public void set(GL2GL3 gl, float x, float y, float z, float w) {
    gl.glVertexAttrib4f(index, x, y, z, w);
  }

  public void set(GL2GL3 gl, ConstVec2f v) {
    set(gl, v.x(), v.y());
  }

  public void set(GL2GL3 gl, ConstVec3f v) {
    set(gl, v.x(), v.y(), v.z());
  }

  public void set(GL2GL3 gl, ConstVec4f v) {
    set(gl, v.x(), v.y(), v.z(), v.w());
  }
}
