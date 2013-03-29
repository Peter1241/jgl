/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.geometry;

import jgl.math.vector.ConstVec3f;


/**
 * A plane defined by a point on its surface and its normal.
 * 
 * @author justin
 */
public class Plane {

  /** A point on the plane's surface. */
  public final ConstVec3f p;

  /** The plane's normal direction. */
  public final ConstVec3f n;

  public Plane(ConstVec3f a, ConstVec3f n) {
    this.p = a;
    this.n = n;
  }
}
