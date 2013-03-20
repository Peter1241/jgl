/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.geometry;

import jgl.math.vector.ReadableVec3f;

/**
 * An arc.
 * 
 * @author justin
 */
public class Arc {

  /** Center of the arc */
  public final ReadableVec3f c;

  /** X axis of the arc plane */
  public final ReadableVec3f x;

  /** Y axis of the arc plane */
  public final ReadableVec3f y;

  /** Z axis of the arc plane */
  public final ReadableVec3f z;

  /** Start angle of the arc w.r.t. its X axis (in radians) */
  public final float         start;

  /** End angle of the arc w.r.t. its X axis (in radians) */
  public final float         end;

  /** Angle of the entire arc : (end - start) */
  public final float         angle;

  /** Radius of the arc */
  public final float         radius;

  /**
   * Creates an arc.
   */
  public Arc(ReadableVec3f c, ReadableVec3f x, ReadableVec3f y, float start, float end, float radius) {
    this.c = c;
    this.x = x;
    this.y = y;
    this.z = x.cross(y);
    this.start = start;
    this.end = end;
    this.radius = radius;
    this.angle = end - start;
  }
}
