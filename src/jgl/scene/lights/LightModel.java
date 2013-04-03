/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.scene.lights;

import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.fixedfunc.GLLightingFunc;

/**
 * An arrangement of lights that can be applied to a scene
 * 
 * @author Justin Stoecker
 */
public class LightModel {

  private float[]   globalAmbient = new float[] { 0.2f, 0.2f, 0.2f, 1 };
  private Light[] lights        = new Light[8];
  private int       numLights     = 0;

  public void setGlobalAmbient(float r, float g, float b, float a) {
    globalAmbient = new float[] { r, g, b, a };
  }

  public void addLight(Light light) {
    if (numLights < lights.length)
      lights[numLights++] = light;
  }

  public void removeLight(int i) {
    for (int j = i; j < lights.length - 1; j++)
      lights[j] = lights[j + 1];
    numLights--;
  }

  public void removeLight(Light light) {
    for (int i = 0; i < lights.length; i++)
      if (lights[i] == light)
        removeLight(i);
  }

  public Light getLight(int i) {
    return lights[i];
  }

  public void apply(GL2 gl) {
    gl.glLightModelfv(GL2ES1.GL_LIGHT_MODEL_AMBIENT, globalAmbient, 0);
    for (int i = 0; i < lights.length; i++) {
      if (lights[i] != null) {
        gl.glEnable(GLLightingFunc.GL_LIGHT0 + i);
        lights[i].applyTo(gl, GLLightingFunc.GL_LIGHT0 + i);
      }
    }
  }
}
