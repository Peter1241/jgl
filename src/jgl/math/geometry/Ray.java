/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.geometry;

import java.util.ArrayList;
import java.util.Collection;

import jgl.math.vector.ConstVec3f;
import jgl.math.vector.Vec3f;

/**
 * A 3D ray defined by a point and direction.
 * 
 * @author justin
 */
public class Ray {

  /** Position of the start of the ray. */
  public final ConstVec3f p;

  /** Direction of the ray. */
  public final ConstVec3f d;

  public Ray(ConstVec3f position, ConstVec3f direction) {
    this.p = position;
    this.d = direction.normalized();
  }

  /**
   * Intersects this ray with a plane.
   */
  public Vec3f intersect(Plane plane) {
    float t = Line.intersectLength(p, d, plane);
    return t < 0 ? null : p.plus(d.times(t));
  }

  /**
   * Intersects this ray with a convex polygon.
   */
  public Vec3f intersect(ConvexPolygon poly) {
    float nDotD = poly.n.dot(d);
    if (nDotD == 0)
      return null;
    float t = poly.n.dot(poly.v[0].minus(p)) / nDotD;
    Vec3f x = p.plus(d.times(t));
    return (poly.contains(x) ? x : null);
    
//    Vec3f x = intersect(new Plane(poly.v[0], poly.n));
//    return (x != null || poly.contains(x)) ? x : null;
  }

  /**
   * Intersects this ray with a sphere.
   */
  public Collection<Vec3f> intersect(Sphere sphere) {
    float[] t = Line.intersectLengths(p, d, sphere);
    if (t == null)
      return null;

    ArrayList<Vec3f> intersections = new ArrayList<Vec3f>(2);
    for (float time : t)
      if (time >= 0)
        intersections.add(p.plus(d.times(time)));

    return intersections;
  }

  /**
   * Intersects this ray with an axis-aligned box.
   */
  public Collection<Vec3f> intersect(Box box) {
    ArrayList<Vec3f> intersections = new ArrayList<Vec3f>();
    for (int i = 0; i < Box.FACES.length; i++) {
      Vec3f x = intersect(box.getFace(i));
      if (x != null)
        intersections.add(x);
    }
    return intersections;
  }
}
