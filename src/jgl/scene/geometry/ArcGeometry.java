/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.scene.geometry;

import jgl.math.vector.Mat4f;
import jgl.math.vector.ConstVec3f;
import jgl.math.vector.Transform;
import jgl.math.vector.Vec3f;

/**
 * A triangulated circular arc.
 * 
 * @author justin
 */
public class ArcGeometry extends Geometry {

  /**
   * Creates an arc.
   * 
   * @param start - starting axis in the plane.
   * @param normal - normal vector to the plane.
   * @param radians - size of the arc angle in radians.
   * @param innerRadius - radius of the inside of the arc.
   * @param outerRadius - radius of the outerside of the arc.
   * @param segments - number of segments in the arc.
   */
  public ArcGeometry(ConstVec3f start, ConstVec3f normal, double radians, float innerRadius,
      float outerRadius, int segments) {
    init((segments + 1) * 2, 0, Geometry.PrimitiveType.TRIANGLE_STRIP);

    Mat4f m = Transform.rotation(normal, radians / segments);
    Vec3f p = start.copy();

    for (int i = 0; i <= segments; i++) {
      float progress = (float) i / segments;

      p.times(innerRadius).putInto(vertices);
      normal.putInto(normals);
      texCoords.put(progress);
      texCoords.put(0);

      p.times(outerRadius).putInto(vertices);
      normal.putInto(normals);
      texCoords.put(progress);
      texCoords.put(0);

      p = m.times(p);
    }

    rewindBuffers();
  }

  /**
   * Creates an arc in the +X/-Z plane with the normal +Y.
   */
  public static ArcGeometry aroundY(double startRadians, double endRadians, float innerRadius,
      float outerRadius, int segments) {
    Vec3f start = new Vec3f((float) Math.cos(startRadians), 0, -(float) Math.sin(startRadians));
    if (endRadians < startRadians)
      endRadians += Math.PI * 2.0;
    return new ArcGeometry(start, Vec3f.axisY(), endRadians - startRadians, innerRadius,
        outerRadius, segments);
  }

  /**
   * Creates an arc in the +X/+Y plane with the normal +Z.
   */
  public static ArcGeometry aroundZ(double startRadians, double endRadians, float innerRadius,
      float outerRadius, int segments) {
    Vec3f start = new Vec3f((float) Math.cos(startRadians), (float) Math.sin(startRadians), 0);
    if (endRadians < startRadians)
      endRadians += Math.PI * 2.0;
    return new ArcGeometry(start, Vec3f.axisZ(), endRadians - startRadians, innerRadius,
        outerRadius, segments);
  }
}
