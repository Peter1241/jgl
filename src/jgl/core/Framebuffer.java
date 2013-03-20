/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.core;

import javax.media.opengl.GL;
import javax.media.opengl.GL2GL3;

/**
 * Reference to an OpenGL Framebuffer object (FBO).
 * 
 * @author justin
 */
public class Framebuffer {

  public enum Target {
    FRAMEBUFFER(GL.GL_FRAMEBUFFER),
    READ_FRAMEBUFFER(GL2GL3.GL_READ_FRAMEBUFFER),
    DRAW_FRAMEBUFFER(GL2GL3.GL_DRAW_FRAMEBUFFER);

    public final int value;

    private Target(int value) {
      this.value = value;
    }
  }

  private Target target;
  private int    id = -1;

  public Framebuffer(Target target) {
    this.target = target;
  }

  public Target getTarget() {
    return target;
  }

  public void setTarget(Target target) {
    this.target = target;
  }

  public int id() {
    return id;
  }

  public void delete(GL gl) {
    if (id != -1) {
      gl.glDeleteFramebuffers(1, new int[] { id }, 0);
      id = -1;
    }
  }

  public void bind(GL gl) {
    if (id == -1) {
      int[] temp = new int[1];
      gl.glGenFramebuffers(1, temp, 0);
      id = temp[0];
    }
    gl.glBindBuffer(target.value, id);
  }

  public void unbind(GL gl) {
    gl.glBindBuffer(target.value, 0);
  }

  /**
   * Assigns a renderbuffer to the bound FBO depth attachment.
   */
  public void depthTarget(GL gl, Renderbuffer renderbuffer) {
    gl.glFramebufferRenderbuffer(target.value, GL.GL_DEPTH_ATTACHMENT, GL.GL_RENDERBUFFER,
        renderbuffer.id());
  }

  /**
   * Assigns a 2D texture to the bound FBO depth attachment.
   */
  public void depthTarget(GL gl, Texture texture, int level) {
    gl.glFramebufferTexture2D(target.value, GL.GL_DEPTH_ATTACHMENT, GL.GL_TEXTURE_2D, texture.id(),
        level);
  }

  /**
   * Assigns a renderbuffer to the bound FBO color attachment i.
   */
  public void colorTarget(GL gl, int i, Renderbuffer renderbuffer) {
    gl.glFramebufferRenderbuffer(target.value, GL.GL_COLOR_ATTACHMENT0 + i, GL.GL_RENDERBUFFER,
        renderbuffer.id());
  }

  /**
   * Assigns a 2D texture to the bound FBO color attachment i.
   */
  public void colorTarget(GL gl, int i, Texture texture, int level) {
    gl.glFramebufferTexture2D(target.value, GL.GL_COLOR_ATTACHMENT0 + i, GL.GL_TEXTURE_2D,
        texture.id(), level);
  }

  /**
   * Assigns a renderbuffer to the bound FBO stencil attachment.
   */
  public void stencilTarget(GL gl, Renderbuffer renderbuffer) {
    gl.glFramebufferRenderbuffer(target.value, GL.GL_STENCIL_ATTACHMENT, GL.GL_RENDERBUFFER,
        renderbuffer.id());
  }

  /**
   * Assigns a 2D texture to the bound FBO stencil attachment.
   */
  public void stencilTarget(GL gl, Texture texture, int level) {
    gl.glFramebufferTexture2D(target.value, GL.GL_STENCIL_ATTACHMENT, GL.GL_TEXTURE_2D,
        texture.id(), level);
  }
}
