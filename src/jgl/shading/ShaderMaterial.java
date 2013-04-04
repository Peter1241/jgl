package jgl.shading;

import javax.media.opengl.GL;

import jgl.core.Program;

/**
 * Abstract shader material.
 * 
 * @author justin
 */
public class ShaderMaterial implements Material {

  public Program shaderProgram;
  public boolean disposeShader = true;

  @Override
  public void enable(GL gl) {
    if (gl.isGL2GL3() && shaderProgram != null) {
      shaderProgram.bind(gl.getGL2GL3());
    }
  }

  @Override
  public void disable(GL gl) {
    if (gl.isGL2GL3() && shaderProgram != null) {
      shaderProgram.unbind(gl.getGL2GL3());
    }
  }

  @Override
  public void dispose(GL gl) {
    if (shaderProgram != null && disposeShader) {
      shaderProgram.delete(gl.getGL2GL3(), true);
    }
  }
}
