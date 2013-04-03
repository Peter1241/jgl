/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.core;

import javax.media.opengl.GL;
import javax.media.opengl.GL2GL3;

/**
 * Reference to an OpenGL Renderbuffer object (RBO).
 * 
 * @author justin
 */
public class Renderbuffer {

  private int id = -1;

  /**
   * Creates a Renderbuffer.
   */
  public Renderbuffer() {
  }

  /**
   * Renderbuffer's name in the OpenGL context.
   */
  public int id() {
    return id;
  }

  /**
   * Generates a Renderbuffer object and stores a reference to it in this object.
   */
  public void generate(GL gl) {
    int[] temp = new int[1];
    gl.glGenRenderbuffers(1, temp, 0);
    id = temp[0];
  }

  /**
   * Deletes the Renderbuffer from the OpenGL context.
   */
  public void delete(GL gl) {
    if (id != -1) {
      gl.glDeleteRenderbuffers(1, new int[] { id }, 0);
      id = -1;
    }
  }

  /** Binds this Renderbuffer to it's target. */
  public void bind(GL gl) {
    if (id == -1) {
      int[] temp = new int[1];
      gl.glGenRenderbuffers(1, temp, 0);
      id = temp[0];
    }
    gl.glBindRenderbuffer(GL.GL_RENDERBUFFER, id);
  }

  /**
   * Binds NULL or 0 to this renderbuffer's target.
   */
  public void unbind(GL gl) {
    gl.glBindRenderbuffer(GL.GL_RENDERBUFFER, 0);
  }

  /**
   * Establish data storage, format and dimensions of a renderbuffer object's image.
   */
  public void storage(GL gl, int internalformat, int width, int height) {
    gl.glRenderbufferStorage(GL.GL_RENDERBUFFER, internalformat, width, height);
  }

  /**
   * Establish data storage, format, dimensions and sample count of a renderbuffer object's image.
   */
  public void storage(GL2GL3 gl, int samples, int internalformat, int width, int height) {
    gl.glRenderbufferStorageMultisample(GL.GL_RENDERBUFFER, samples, internalformat, width, height);
  }
}
