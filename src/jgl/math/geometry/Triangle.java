/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.geometry;

import jgl.math.vector.ReadableVec3f;

/**
 * A triangle defined by its three vertices arranged in counter-clockwise order around its normal.
 * 
 * @author justin
 */
public class Triangle extends ConvexPolygon {

  /** A triangle defined by three points arranged counter-clockwise for the front face. */
  public Triangle(ReadableVec3f a, ReadableVec3f b, ReadableVec3f c) {
    super(new ReadableVec3f[] { a, b, c }, c.minus(b).cross(a.minus(b)).normalize());
  }
}