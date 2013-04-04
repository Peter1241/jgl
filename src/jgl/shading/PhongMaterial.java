package jgl.shading;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import jgl.core.Texture;
import jgl.math.vector.Vec4f;

/**
 * Classic material used for Phong / Blinn-Phong shading.
 * 
 * @author justin
 */
public class PhongMaterial implements Material {

  public Vec4f   diffuse           = new Vec4f(0.8f, 0.8f, 0.8f, 1.0f);
  public Vec4f   ambient           = new Vec4f(0.2f, 0.2f, 0.2f, 1.0f);
  public Vec4f   specular          = new Vec4f(0.0f, 0.0f, 0.0f, 1.0f);
  public int     shininess         = 96;
  public Texture diffuseMap        = null;
  public boolean disposeDiffuseMap = true; // set to false if texture is shared

  public void enable(GL gl) {
    if (diffuseMap != null) {
      diffuseMap.bind(gl);
    }

    if (gl.isGL2()) {
      GL2 gl2 = gl.getGL2();
      gl2.glMaterialfv(GL.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, specular.toArray(), 0);
      gl2.glMaterialfv(GL.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, ambient.toArray(), 0);
      gl2.glMaterialfv(GL.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, diffuse.toArray(), 0);
      gl2.glMateriali(GL.GL_FRONT_AND_BACK, GL2.GL_SHININESS, shininess);
    }
  }

  public void disable(GL gl) {
    if (diffuseMap != null) {
      diffuseMap.unbind(gl);
    }
  }

  @Override
  public void dispose(GL gl) {
    if (diffuseMap != null && disposeDiffuseMap) {
      diffuseMap.delete(gl);
    }
  }
}
