/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.scene.shading;

import jgl.math.vector.Vec3f;

/**
 * Stores directional light parameters
 * 
 * @author Justin Stoecker
 */
public class DirectionalLight extends Light {

  /** Creates a new directional light */
  public DirectionalLight(float dirX, float dirY, float dirZ) {
    setDirection(dirX, dirY, dirZ);
  }

  /** Creates a new directional light */
  public DirectionalLight(Vec3f direction) {
    setDirection(direction);
  }

  /** Returns a copy of the light's direction */
  public Vec3f getDirection() {
    return new Vec3f(position);
  }

  /** Sets the light direction */
  public void setDirection(float x, float y, float z) {
    setPosition(x, y, z, 0);
  }

  /** Sets the light direction */
  public void setDirection(Vec3f direction) {
    setPosition(direction.x, direction.y, direction.z, 0);
  }
}
