/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.math.geometry;

import jgl.math.vector.ConstVec3f;


/**
 * A sphere defined by its center and radius.
 * 
 * @author justin
 */
public class Sphere {
  
  /** The center point. */
  public final ConstVec3f c;

  /** The radius. */
  public final float  r;

  public Sphere(ConstVec3f c, float r) {
    this.c = c;
    this.r = r;
  }
}
