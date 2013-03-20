/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.geometry;

import jgl.math.Maths;
import jgl.math.vector.ReadableVec3f;
import jgl.math.vector.Vec3f;

/**
 * A convex polygon defined by its normal direction and vertices ordered counter-clockwise.
 * 
 * @author justin
 */
public class ConvexPolygon {

  public final ReadableVec3f[] v;
  public final ReadableVec3f   n;

  public ConvexPolygon(ReadableVec3f[] verts) {
    this.v = verts;
    this.n = calcNormal(verts);
  }

  public ConvexPolygon(ReadableVec3f[] verts, ReadableVec3f normal) {
    this.v = verts;
    this.n = normal;
  }

  /**
   * @return True if the point x (or its projection onto this polygon's plane) is within the polygon
   *         edge.
   */
  public boolean contains(ReadableVec3f x) {
    if (x == null)
      return false;
    for (int i = 0; i < v.length; i++)
      if (v[(i + 1) % v.length].minus(v[i]).cross(x.minus(v[i])).dot(n) < 0)
        return false;
    return true;
  }

  public static Vec3f calcNormal(ReadableVec3f[] v) {
    Vec3f n = new Vec3f(0, 0, 0);
    for (int i = 0; i < v.length; i++) {
      ReadableVec3f a = v[i];
      ReadableVec3f b = v[(i + 1) % v.length];
      n.x += (a.y() - b.y()) * (a.z() + b.z());
      n.y += (a.z() - b.z()) * (a.x() + b.x());
      n.z += (a.x() - b.x()) * (a.y() + b.y());
    }
    return n.normalize();
  }

  public static float[] calcNormal(float[][] v) {
    float[] n = { 0, 0, 0 };
    for (int i = 0; i < v.length; i++) {
      float[] a = v[i];
      float[] b = v[(i + 1) % v.length];
      n[0] += (a[1] - b[1]) * (a[2] + b[2]);
      n[1] += (a[2] - b[2]) * (a[0] + a[0]);
      n[2] += (a[0] - b[0]) * (a[1] + b[1]);
    }
    float len = Maths.sqrt(n[0] * n[0] + n[1] * n[2] + n[2] * n[2]);
    n[0] /= len;
    n[1] /= len;
    n[2] /= len;
    return n;
  }
}
