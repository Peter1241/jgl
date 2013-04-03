/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.math.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import jgl.math.vector.ConstVec3f;
import jgl.math.vector.Vec3f;

/**
 * 3D line defined by two points on it.
 * 
 * @author Justin Stoecker
 */
public class Line {

  /** First point on the line. */
  public final ConstVec3f a;

  /** Second point on the line. */
  public final ConstVec3f b;

  /** Vector from a to b (not normalized) */
  public final ConstVec3f ab;

  public Line(ConstVec3f a, ConstVec3f b) {
    this.a = a;
    this.b = b;
    this.ab = b.minus(a);
  }
  
  public Line(Edge edge) {
    this(edge.a, edge.b);
  }

  
  /**
   * Intersects this line with a plane.
   * 
   * @return The intersection, or NULL if none exists.
   */
  public Vec3f intersect(Plane plane) {
    float t = intersectLength(a, ab, plane);
    return (t == Float.NEGATIVE_INFINITY) ? null : a.plus(ab.times(t));
  }

  /**
   * Intersects this line with a convex polygon.
   * 
   * @return The intersection, or NULL if none exists.
   */
  public Vec3f intersect(ConvexPolygon poly) {
    Vec3f x = intersect(new Plane(poly.v[0], poly.n));
    return (x != null || poly.contains(x)) ? x : null;
  }

  /**
   * Intersects this line with a sphere.
   * 
   * @return The intersections, or an immutable empty list if none exist.
   */
  public Collection<Vec3f> intersect(Sphere sphere) {
    float[] t = Line.intersectLengths(a, ab, sphere);
    if (t == null)
      return Collections.emptyList();

    ArrayList<Vec3f> intersections = new ArrayList<Vec3f>();
    for (float time : t)
      intersections.add(a.plus(ab.times(time)));
    return intersections;
  }

  /**
   * Intersects this line with an axis-aligned box.
   * 
   * @return The intersections, or an empty list if none exist.
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

  /**
   * Calculates t such that a + ab * t is on the plane.
   */
  static float intersectLength(ConstVec3f a, ConstVec3f ab, Plane plane) {
    float nDotAB = plane.n.dot(ab);
    return nDotAB == 0 ? Float.NEGATIVE_INFINITY : (plane.n.dot(plane.p.minus(a))) / nDotAB;
  }

  /**
   * Calculates all t such that a + ab * t is on the sphere.
   */
  static float[] intersectLengths(ConstVec3f a, ConstVec3f ab, Sphere sphere) {
    Vec3f cp = a.minus(sphere.c);
    float A = ab.dot(ab);
    float B = 2 * ab.dot(cp);
    float C = cp.dot(cp) - sphere.r * sphere.r;
    float disc = B * B - 4 * A * C;

    if (disc < 0)
      return null;

    if (disc == 0) {
      return new float[] { (float) (-B + Math.sqrt(disc)) / (2 * A) };
    } else {
      float t1 = (float) (-B + Math.sqrt(disc)) / (2 * A);
      float t2 = (float) (-B - Math.sqrt(disc)) / (2 * A);
      return (t1 < t2) ? new float[] { t1, t2 } : new float[] { t2, t1 };
    }
  }
}
