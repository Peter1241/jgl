/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.geometry;

import java.util.ArrayList;
import java.util.Collection;

import jgl.math.vector.ReadableVec3f;
import jgl.math.vector.Vec3f;

/**
 * A line segment (part of a line bounded by two end points).
 * 
 * @author justin
 */
public class Edge {
  
  public final ReadableVec3f a;
  
  public final ReadableVec3f b;
  
  public Edge(ReadableVec3f a, ReadableVec3f b) {
    this.a = a;
    this.b = b;
  }
  
  /**
   * Intersects this edge with a plane.
   */
  public Vec3f intersect(Plane plane) {
    Vec3f ab = b.minus(a);
    float t = Line.intersectLength(a, ab, plane);
    return (t < 0 || t > 1) ? null : a.plus(ab.times(t));
  }

  /**
   * Intersects this edge with a convex polygon.
   */
  public Vec3f intersect(ConvexPolygon poly) {
    Vec3f x = intersect(new Plane(poly.v[0], poly.n));
    return (x != null || poly.contains(x)) ? x : null;
  }

  /**
   * Intersects this edge with a sphere.
   */
  public Collection<Vec3f> intersect(Sphere sphere) {
    Vec3f ab = b.minus(a);
    float[] t = Line.intersectLengths(a, ab, sphere);
    if (t == null)
      return null;

    ArrayList<Vec3f> intersections = new ArrayList<Vec3f>(2);
    for (float time : t)
      if (time >= 0 && time <= 1)
        intersections.add(a.plus(ab.times(time)));

    return intersections;
  }

  /**
   * Intersects this edge with an axis-aligned box.
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
