/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.scene.shading;

import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLLightingFunc;

import jgl.math.Maths;
import jgl.math.vector.Vec3f;

/**
 * A point light that emits light restricted within a cone.
 * 
 * @author Justin Stoecker
 */
public class SpotLight extends PointLight {
  private static final int MIN_CUTOFF    = 0;
  private static final int MAX_CUTOFF    = 90;
  private static final int MIN_EXPONENT  = 0;
  private static final int MAX_EXPONENT  = 128;

  protected float[]        spotDirection = new float[] { 0, 0, -1 };
  protected float          spotCutoff    = 45;
  protected float          spotExponent  = 0;

  public Vec3f getSpotDirection() {
    return new Vec3f(spotDirection);
  }

  public float getSpotCutoff() {
    return spotCutoff;
  }

  public float getSpotExponent() {
    return spotExponent;
  }

  /** Creates a new spotlight. */
  public SpotLight(Vec3f pos, Vec3f dir) {
    super(pos);
    setSpotDirection(dir);
  }

  /** Creates a new spotlight. */
  public SpotLight(Vec3f pos, Vec3f dir, float cutoff) {
    this(pos, dir);
    this.spotCutoff = cutoff;
  }

  /** Creates a new spotlight. */
  public SpotLight(Vec3f pos, Vec3f dir, float cutoff, float exponent) {
    this(pos, dir, cutoff);
    this.spotExponent = exponent;
  }

  /** Sets the light's spot direction */
  public void setSpotDirection(Vec3f dir) {
    spotDirection[0] = dir.x;
    spotDirection[1] = dir.y;
    spotDirection[2] = dir.z;
  }

  /** Sets the light's spot direction */
  public void setSpotDirection(float x, float y, float z) {
    spotDirection[0] = x;
    spotDirection[1] = y;
    spotDirection[2] = z;
  }

  /**
   * Sets the light's spot cutoff angle in degrees. Values should be in the range [0,90].
   */
  public void setSpotCutoff(float c) {
    spotCutoff = Maths.clamp(c, MIN_CUTOFF, MAX_CUTOFF);
  }

  /**
   * Sets the light's spot exponent, which determines the intensity distribution of the light.
   * Values should be in the range [0,128].
   */
  public void setSpotExponent(float e) {
    spotExponent = Maths.clamp(e, MIN_EXPONENT, MAX_EXPONENT);
  }

  @Override
  public void applyTo(GL2 gl, int glLight) {
    super.applyTo(gl, glLight);
    gl.glLightfv(glLight, GLLightingFunc.GL_SPOT_DIRECTION, spotDirection, 0);
    gl.glLightf(glLight, GLLightingFunc.GL_SPOT_CUTOFF, spotCutoff);
    gl.glLightf(glLight, GLLightingFunc.GL_SPOT_EXPONENT, spotExponent);
  }
}
