/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.math.geometry;

import jgl.math.vector.ConstVec3f;

/**
 * A circle.
 * 
 * @author justin
 */
public class Circle {

  /** Center */
  public final ConstVec3f center;

  /** Normal direction */
  public final ConstVec3f normal;

  /** Radius */
  public final float         radius;

  public Circle(ConstVec3f c, ConstVec3f n, float r) {
    this.center = c;
    this.normal = n;
    this.radius = r;
  }
}
