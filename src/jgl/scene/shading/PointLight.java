/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.scene.shading;

import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLLightingFunc;

import jgl.math.vector.Vec3f;

/**
 * Stores point light parameters
 * 
 * @author Justin Stoecker
 */
public class PointLight extends Light {

  protected float constantAttenuation  = 1;
  protected float linearAttenuation    = 0;
  protected float quadraticAttenuation = 0;

  /** Creates a new point light */
  public PointLight(float posX, float posY, float posZ) {
    setPosition(posX, posY, posZ, 1);
  }

  /** Creates a new point light */
  public PointLight(Vec3f pos) {
    setPosition(pos.x, pos.y, pos.z, 1);
  }

  public float getConstantAttenuation() {
    return constantAttenuation;
  }

  public float getLinearAttenuation() {
    return linearAttenuation;
  }

  public float getQuadraticAttenuation() {
    return quadraticAttenuation;
  }

  public void setConstantAttenuation(float c) {
    constantAttenuation = c;
  }

  public void setLinearAttenuation(float c) {
    linearAttenuation = c;
  }

  public void setQuadraticAttenuation(float c) {
    quadraticAttenuation = c;
  }

  @Override
  public void applyTo(GL2 gl, int light) {
    super.applyTo(gl, light);
    gl.glLightf(light, GLLightingFunc.GL_CONSTANT_ATTENUATION, constantAttenuation);
    gl.glLightf(light, GLLightingFunc.GL_LINEAR_ATTENUATION, linearAttenuation);
    gl.glLightf(light, GLLightingFunc.GL_QUADRATIC_ATTENUATION, quadraticAttenuation);
  }
}
