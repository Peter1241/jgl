/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.geometry;

import jgl.math.vector.ReadableVec3f;


/**
 * A plane defined by a point on its surface and its normal.
 * 
 * @author justin
 */
public class Plane {

  /** A point on the plane's surface. */
  public final ReadableVec3f p;

  /** The plane's normal direction. */
  public final ReadableVec3f n;

  public Plane(ReadableVec3f a, ReadableVec3f n) {
    this.p = a;
    this.n = n;
  }
}
