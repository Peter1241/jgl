/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.core;

import java.nio.Buffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2GL3;

public class Texture {
  
  // OpenGL texture state
  private static int bound;

  /** Texture target */
  public enum Target {
    TEXTURE_1D(GL2GL3.GL_TEXTURE_1D),
    TEXTURE_2D(GL2GL3.GL_TEXTURE_2D),
    TEXTURE_3D(GL2GL3.GL_TEXTURE_3D),
    TEXTURE_CUBE_MAP(GL.GL_TEXTURE_CUBE_MAP);

    public final int glConstant;

    private Target(int glConstant) {
      this.glConstant = glConstant;
    }
  }

  private Target target;
  private int    id = -1;
  private int    width;
  private int    height;
  private int    depth;

  public Texture(Target target) {
    this.target = target;
  }

  public Texture() {
    this(Target.TEXTURE_2D);
  }

  public int id() {
    return id;
  }
  
  public int getWidth() {
    return width;
  }
  
  public int getHeight() {
    return height;
  }
  
  public int getDepth() {
    return depth;
  }

  public Target getTarget() {
    return target;
  }

  public void setTarget(Target target) {
    this.target = target;
  }

  private void generate(GL gl) {
    int[] temp = new int[1];
    gl.glGenTextures(1, temp, 0);
    id = temp[0];
  }
  
  public void setData2D(GL gl, int level, int internalFormat, int width, int height, int format,
      int type, Buffer data) {
    if (id == -1)
      generate(gl);
    if (id != bound)
      bind(gl);
    this.width = width;
    this.height = height;
    this.depth = 0;
    gl.glTexImage2D(target.glConstant, level, internalFormat, width, height, 0, format, type, data);
  }

  public void setData2D(GL gl, int internalFormat, int width, int height, int format, int type,
      Buffer data) {
    setData2D(gl, 0, internalFormat, width, height, format, type, data);
  }

  /**
   * Deletes the buffer object referenced by this object.
   */
  public void delete(GL gl) {
    if (id != -1) {
      gl.glDeleteTextures(1, new int[] { id }, 0);
      id = -1;
    }
  }

  /**
   * Binds this texture object to it's target.
   */
  public void bind(GL gl) {
    gl.glBindTexture(target.glConstant, id);
    bound = id;
  }

  /**
   * Binds NULL or 0 to this texture object's target.
   */
  public void unbind(GL gl) {
    gl.glBindTexture(target.glConstant, 0);
    bound = 0;
  }
}
