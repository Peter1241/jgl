/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.geometry;

import jgl.math.vector.ReadableVec3f;

/**
 * A circle.
 * 
 * @author justin
 */
public class Circle {

  /** Center */
  public final ReadableVec3f center;

  /** Normal direction */
  public final ReadableVec3f normal;

  /** Radius */
  public final float         radius;

  public Circle(ReadableVec3f c, ReadableVec3f n, float r) {
    this.center = c;
    this.normal = n;
    this.radius = r;
  }
}
