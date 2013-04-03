/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.math.geometry;

import jgl.math.vector.ConstVec3f;

/**
 * A triangle defined by its three vertices arranged in counter-clockwise order around its normal.
 * 
 * @author justin
 */
public class Triangle extends ConvexPolygon {

  /** A triangle defined by three points arranged counter-clockwise for the front face. */
  public Triangle(ConstVec3f a, ConstVec3f b, ConstVec3f c) {
    super(new ConstVec3f[] { a, b, c }, c.minus(b).cross(a.minus(b)).normalize());
  }
}