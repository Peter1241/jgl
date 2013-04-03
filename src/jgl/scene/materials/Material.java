/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.scene.materials;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import jgl.math.Maths;

/**
 * Data structure that stores material properties used in OpenGL lighting
 */
public class Material {
  protected static final int MAX_SHININESS = 128;
  protected float[]          emission      = new float[] { 0, 0, 0, 1 };
  protected float[]          diffuse       = new float[] { 0.8f, 0.8f, 0.8f, 1 };
  protected float[]          ambient       = new float[] { 0.2f, 0.2f, 0.2f, 1 };
  protected float[]          specular      = new float[] { 0, 0, 0, 1 };
  protected int              shininess     = 96;

  public float[] getEmission() {
    return emission;
  }

  public float[] getDiffuse() {
    return diffuse;
  }

  public float[] getAmbient() {
    return ambient;
  }

  public float[] getSpecular() {
    return specular;
  }

  public int getShininess() {
    return shininess;
  }

  public void setEmission(float[] color) {
    System.arraycopy(color, 0, emission, 0, Math.min(color.length, 4));
  }

  public void setEmission(float r, float g, float b, float a) {
    emission = new float[] { r, g, b, a };
  }

  public void setAmbient(float[] color) {
    System.arraycopy(color, 0, ambient, 0, Math.min(color.length, 4));
  }

  public void setAmbient(float r, float g, float b, float a) {
    ambient = new float[] { r, g, b, a };
  }

  public void setDiffuse(float[] color) {
    System.arraycopy(color, 0, diffuse, 0, Math.min(color.length, 4));
  }

  public void setDiffuse(float r, float g, float b, float a) {
    diffuse = new float[] { r, g, b, a };
  }

  public void setDiffAmbient(float r, float g, float b, float a) {
    setDiffuse(r, g, b, a);
    setAmbient(r, g, b, a);
  }

  public void setSpecular(float[] color) {
    System.arraycopy(color, 0, specular, 0, Math.min(color.length, 4));
  }

  public void setSpecular(float r, float g, float b, float a) {
    specular = new float[] { r, g, b, a };
  }

  public void setShininess(int shininess) {
    this.shininess = Maths.clamp(shininess, 0, MAX_SHININESS);
  }

  /** Applies material to a specific face */
  public void apply(GL2 gl, int face) {
    gl.glMaterialfv(face, GL2.GL_EMISSION, emission, 0);
    gl.glMaterialfv(face, GL2.GL_SPECULAR, specular, 0);
    gl.glMaterialfv(face, GL2.GL_AMBIENT, ambient, 0);
    gl.glMaterialfv(face, GL2.GL_DIFFUSE, diffuse, 0);
    gl.glMateriali(face, GL2.GL_SHININESS, shininess);
  }

  /** Applies material to front and back faces */
  public void apply(GL2 gl) {
    gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL2.GL_EMISSION, emission, 0);
    gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, specular, 0);
    gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, ambient, 0);
    gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, diffuse, 0);
    gl.glMateriali(GL.GL_FRONT_AND_BACK, GL2.GL_SHININESS, shininess);
  }
}
