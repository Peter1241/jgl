/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.scene.geometry;

import jgl.math.vector.Mat4f;
import jgl.math.vector.Transform;
import jgl.math.vector.Vec3f;
import jgl.math.vector.Vec4f;

/**
 * Creates a triangulated circle.
 * 
 * @author justin
 */
public class CircleGeometry extends Geometry {

  /**
   * Creates a filled circle.
   * 
   * @param x - axis 1.
   * @param y - axis 2.
   * @param radius - radius of the circle.
   * @param segments - number of segments in the circle.
   */
  public CircleGeometry(Vec3f x, Vec3f y, float radius, int segments) {

    init(segments + 2, 0, Geometry.PrimitiveType.TRIANGLE_FAN);

    Vec3f z = x.cross(y);
    Mat4f m = Transform.rotation(z, Math.PI * 2.0 / segments);
    Vec4f v = new Vec4f(x.x * radius, x.y * radius, x.z * radius, 0);

    vertices.put(0);
    vertices.put(0);
    vertices.put(0);
    normals.put(z.x);
    normals.put(z.y);
    normals.put(z.z);
    texCoords.put(0);
    texCoords.put(0);

    for (int i = 0; i <= segments; i++) {
      vertices.put(v.x);
      vertices.put(v.y);
      vertices.put(v.z);
      normals.put(z.x);
      normals.put(z.y);
      normals.put(z.z);
      texCoords.put((float) i / segments);
      texCoords.put(1);

      v = m.times(v);
    }

    rewindBuffers();
  }

  /**
   * Creates a circle with inner and outer radii (not filled).
   * 
   * @param x - axis 1.
   * @param y - axis 2.
   * @param innerRadius
   * @param outerRadius
   * @param segments
   */
  public CircleGeometry(Vec3f x, Vec3f y, float innerRadius, float outerRadius, int segments) {

    init((segments+1) * 2, 0, Geometry.PrimitiveType.TRIANGLE_STRIP);
    
    Vec3f z = x.cross(y);
    Mat4f m = Transform.rotation(z, Math.PI * 2.0 / segments);
    Vec4f v = new Vec4f(x.x, x.y, x.z, 0);

    for (int i = 0; i <= segments; i++) {
      float progress = (float) i / segments;
      
      vertices.put(v.x * innerRadius);
      vertices.put(v.y * innerRadius);
      vertices.put(v.z * innerRadius);
      normals.put(z.x);
      normals.put(z.y);
      normals.put(z.z);
      texCoords.put(progress);
      texCoords.put(0);

      vertices.put(v.x * outerRadius);
      vertices.put(v.y * outerRadius);
      vertices.put(v.z * outerRadius);
      normals.put(z.x);
      normals.put(z.y);
      normals.put(z.z);
      texCoords.put(progress);
      texCoords.put(0);

      v = m.times(v);
    }
    
    rewindBuffers();
  }
}
