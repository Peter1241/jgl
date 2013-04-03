/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.scene.geometry.extra;

import jgl.scene.geometry.Geometry;
import jgl.scene.geometry.VertexPN;

/**
 * A triangulated circular arc.
 * 
 * @author justin
 */
public class ArcGeometry extends Geometry<VertexPN> {

  /**
   * Creates a filled arc in the XY plane.
   * 
   * @param radius - radius of the circle.
   * @param segments - number of segments in 2pi.
   */
  public ArcGeometry(float radius, double radians, int segments) {
    super(Primitive.TRIANGLE_FAN, VertexPN.CONSTRUCTOR);
    allocate(segments + 1, 0);

    double theta = radians / segments;
    float c = (float) Math.cos(theta);
    float s = (float) Math.sin(theta);

    VertexPN v = new VertexPN().position(0, 0, 0).normal(0, 0, 1);
    putVertex(v);
    v.position(radius, 0, 0);
    for (int i = 0; i < segments; i++) {
      putVertex(v);
      v.position(c * v.position.x - s * v.position.y, s * v.position.x + c * v.position.y, 0);
    }

    rewind();
  }

  /**
   * Creates an arc in the XY plane.
   * 
   * @param innerRadius - radius of the start of the circle.
   * @param outerRadius - radius of the end of the circle.
   * @param segments - number of segments in 2pi.
   */
  public ArcGeometry(float innerRadius, float outerRadius, double radians, int segments) {
    super(Primitive.TRIANGLE_STRIP, VertexPN.CONSTRUCTOR);
    allocate(segments * 2, 0);

    double theta = radians / segments;
    float c = (float) Math.cos(theta);
    float s = (float) Math.sin(theta);
    float x = 1;
    float y = 0;

    VertexPN v = new VertexPN().position(x, y, 0).normal(0, 0, 1);
    for (int i = 0; i < segments; i++) {
      putVertex(v.position(x * innerRadius, y * innerRadius, 0));
      putVertex(v.position(x * outerRadius, y * outerRadius, 0));
      float oldX = x;
      x = c * x - s * y;
      y = s * oldX + c * y;
    }

    rewind();
  }
}
