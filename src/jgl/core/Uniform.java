/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.core;

import javax.media.opengl.GL2GL3;

import jgl.math.vector.Mat4f;
import jgl.math.vector.ReadableVec2f;
import jgl.math.vector.ReadableVec3f;
import jgl.math.vector.ReadableVec4f;

/**
 * GLSL uniform variable.
 * 
 * @author justin
 */
public class Uniform {

  public final String name;
  public final int    size;
  public final int    type;
  public final int    loc;

  Uniform(String name, int size, int type, int loc) {
    this.name = name;
    this.size = size;
    this.type = type;
    this.loc = loc;
  }

  public void set(GL2GL3 gl, int x) {
    gl.glUniform1i(loc, x);
  }

  public void set(GL2GL3 gl, int x, int y) {
    gl.glUniform2i(loc, x, y);
  }

  public void set(GL2GL3 gl, int x, int y, int z) {
    gl.glUniform3i(loc, x, y, z);
  }

  public void set(GL2GL3 gl, int x, int y, int z, int w) {
    gl.glUniform4i(loc, x, y, z, w);
  }

  public void set2(GL2GL3 gl, int[] v) {
    gl.glUniform2iv(loc, v.length / 2, v, 0);
  }

  public void set3(GL2GL3 gl, int[] v) {
    gl.glUniform3iv(loc, v.length / 3, v, 0);
  }

  public void set4(GL2GL3 gl, int[] v) {
    gl.glUniform4iv(loc, v.length / 4, v, 0);
  }

  public void set(GL2GL3 gl, float x) {
    gl.glUniform1f(loc, x);
  }

  public void set(GL2GL3 gl, float x, float y) {
    gl.glUniform2f(loc, x, y);
  }

  public void set(GL2GL3 gl, float x, float y, float z) {
    gl.glUniform3f(loc, x, y, z);
  }

  public void set(GL2GL3 gl, float x, float y, float z, float w) {
    gl.glUniform4f(loc, x, y, z, w);
  }

  public void set2(GL2GL3 gl, float[] v) {
    gl.glUniform2fv(loc, v.length / 2, v, 0);
  }

  public void set3(GL2GL3 gl, float[] v) {
    gl.glUniform3fv(loc, v.length / 3, v, 0);
  }

  public void set4(GL2GL3 gl, float[] v) {
    gl.glUniform4fv(loc, v.length / 4, v, 0);
  }

  public void set(GL2GL3 gl, ReadableVec2f v) {
    set(gl, v.x(), v.y());
  }

  public void set(GL2GL3 gl, ReadableVec3f v) {
    set(gl, v.x(), v.y(), v.z());
  }

  public void set(GL2GL3 gl, ReadableVec4f v) {
    set(gl, v.x(), v.y(), v.z(), v.w());
  }

  public void set(GL2GL3 gl, boolean x) {
    gl.glUniform1i(loc, x ? 1 : 0);
  }

  public void set(GL2GL3 gl, boolean x, boolean y) {
    gl.glUniform2i(loc, x ? 1 : 0, y ? 1 : 0);
  }

  public void set(GL2GL3 gl, boolean x, boolean y, boolean z) {
    gl.glUniform3i(loc, x ? 1 : 0, y ? 1 : 0, z ? 1 : 0);
  }

  public void set(GL2GL3 gl, boolean x, boolean y, boolean z, boolean w) {
    gl.glUniform4i(loc, x ? 1 : 0, y ? 1 : 0, z ? 1 : 0, w ? 1 : 0);
  }

  public void set(GL2GL3 gl, Mat4f m) {
    gl.glUniformMatrix4fv(loc, 1, false, m.a, 0);
  }
}
