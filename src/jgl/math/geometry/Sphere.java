/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.geometry;

import jgl.math.vector.ReadableVec3f;


/**
 * A sphere defined by its center and radius.
 * 
 * @author justin
 */
public class Sphere {
  
  /** The center point. */
  public final ReadableVec3f c;

  /** The radius. */
  public final float  r;

  public Sphere(ReadableVec3f c, float r) {
    this.c = c;
    this.r = r;
  }
}
