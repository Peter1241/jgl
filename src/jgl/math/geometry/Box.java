/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.geometry;

import jgl.math.vector.ReadableVec3f;
import jgl.math.vector.Vec3f;

/**
 * An axis-aligned box defined by its min. and max. points.
 * 
 * @author Justin Stoecker
 */
public class Box {

  public static final int[][] FACES = { { 0, 4, 5, 1 }, { 1, 2, 6, 5 }, { 2, 3, 7, 6 },
      { 3, 6, 4, 7 }, { 4, 5, 6, 7 }, { 0, 3, 2, 1 } };

  public static final int[][] EDGES = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 }, { 0, 4 }, { 1, 5 },
      { 2, 6 }, { 3, 7 }, { 4, 5 }, { 5, 6 }, { 6, 7 }, { 7, 4 } };

  public final ReadableVec3f         min;
  public final ReadableVec3f         max;
  public final ReadableVec3f[]       verts = new ReadableVec3f[8];

  public Box(ReadableVec3f min, ReadableVec3f max) {
    this.min = min;
    this.max = max;

    Vec3f d = max.minus(min);
    Vec3f u = d.times(0, 1, 0);

    verts[0] = min;
    verts[1] = min.plus(d.times(0, 0, 1));
    verts[2] = min.plus(d.times(1, 0, 1));
    verts[3] = min.plus(d.times(1, 0, 0));
    verts[4] = verts[0].plus(u);
    verts[5] = verts[1].plus(u);
    verts[6] = verts[2].plus(u);
    verts[7] = verts[3].plus(u);
  }

  public Vec3f getCenter() {
    return max.minus(min).over(2.0f).plus(min);
  }

  public boolean contains(Vec3f p) {
    return p.x >= min.x() && p.x <= max.x() && p.y >= min.y() && p.y <= max.y() && p.z >= min.z()
        && p.z <= max.z();
  }

  public boolean intersects(Box b) {
    return (min.x() < b.max.x()) && (max.x() > b.min.x()) && (min.y() < b.max.y())
        && (max.y() > b.min.y()) && (min.z() < b.max.z()) && (max.z() > b.min.z());
  }

  public Line getEdge(int i) {
    int[] j = EDGES[i];
    return new Line(verts[j[0]], verts[j[1]]);
  }

  public ConvexPolygon getFace(int i) {
    int[] j = FACES[i];
    return new ConvexPolygon(new ReadableVec3f[] { 
        verts[j[0]], verts[j[1]], verts[j[2]], verts[j[3]] });
  }
}
