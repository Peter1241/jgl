/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.scene.geometry;

import jgl.math.vector.Mat4f;
import jgl.math.vector.ReadableVec3f;
import jgl.math.vector.Transform;
import jgl.math.vector.Vec3f;

/**
 * Creates a triangulated circle.
 * 
 * @author justin
 */
public class CircleGeometry extends Geometry {

  /**
   * Creates a filled circle.
   * 
   * @param start - starting axis in the circle plane.
   * @param normal - normal vector to the circle plane.
   * @param radius - radius of the circle.
   * @param segments - number of segments in the circle.
   */
  public CircleGeometry(ReadableVec3f start, ReadableVec3f normal, float radius,  int segments) {

    init(segments + 2, 0, Geometry.PrimitiveType.TRIANGLE_FAN);

    Mat4f m = Transform.rotation(normal, Math.PI * 2.0 / segments);
    Vec3f p = start.times(radius);

    vertices.put(0);
    vertices.put(0);
    vertices.put(0);
    normal.putInto(normals);
    texCoords.put(0);
    texCoords.put(0);

    for (int i = 0; i <= segments; i++) {
      p.putInto(vertices);
      normal.putInto(normals);
      texCoords.put((float) i / segments);
      texCoords.put(1);

      p = m.times(p);
    }

    rewindBuffers();
  }

  /**
   * Creates a circle with inner and outer radii (not filled).
   * 
   * @param start - starting axis in the circle plane.
   * @param normal - normal vector to the circle plane.
   * @param innerRadius
   * @param outerRadius
   * @param segments
   */
  public CircleGeometry(ReadableVec3f start, ReadableVec3f normal, float innerRadius, float outerRadius, int segments) {

    init((segments+1) * 2, 0, Geometry.PrimitiveType.TRIANGLE_STRIP);
    
    Mat4f m = Transform.rotation(normal, Math.PI * 2.0 / segments);
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
  
  public static CircleGeometry aroundX(float radius,  int segments) {
    return new CircleGeometry(new Vec3f(0, 0, -1), Vec3f.axisX(), radius, segments);
  }
  
  public static CircleGeometry aroundX(float innerRadius, float outerRadius, int segments) {
    return new CircleGeometry(new Vec3f(0, 0, -1), Vec3f.axisX(), innerRadius, outerRadius, segments);
  }
  
  public static CircleGeometry aroundY(float radius,  int segments) {
    return new CircleGeometry(Vec3f.axisX(), Vec3f.axisY(), radius, segments);
  }
  
  public static CircleGeometry aroundY(float innerRadius, float outerRadius, int segments) {
    return new CircleGeometry(Vec3f.axisX(), Vec3f.axisY(), innerRadius, outerRadius, segments);
  }
  
  public static CircleGeometry aroundZ(float radius, int segments) {
    return new CircleGeometry(Vec3f.axisX(), Vec3f.axisZ(), radius, segments);
  }
  
  public static CircleGeometry aroundZ(float innerRadius, float outerRadius, int segments) {
    return new CircleGeometry(Vec3f.axisX(), Vec3f.axisZ(), innerRadius, outerRadius, segments);
  }
}
