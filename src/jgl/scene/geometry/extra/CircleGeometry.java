/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.scene.geometry.extra;

import jgl.math.vector.Transform;
import jgl.scene.geometry.Geometry;
import jgl.scene.geometry.VertexPN;

/**
 * A triangulated circle.
 * 
 * @author justin
 */
public class CircleGeometry extends Geometry<VertexPN> {

  /**
   * Creates a filled circle in the XY plane.
   * 
   * @param radius - radius of the circle.
   * @param segments - number of segments in 2pi.
   */
  public CircleGeometry(float radius, int segments) {
    super(Primitive.TRIANGLE_FAN, VertexPN.CONSTRUCTOR);
    allocate(segments, 0);
    
    double theta = Math.PI * 2 / segments;
    float c = (float)Math.cos(theta);
    float s = (float)Math.sin(theta);
    
    VertexPN v = new VertexPN().position(radius, 0, 0).normal(0, 0, 1);
    for (int i = 0; i < segments; i++) {
      putVertex(v);
      v.position(c * v.position.x - s * v.position.y, s * v.position.x + c * v.position.y, 0);
    }
    
    rewind();
  }

  /**
   * Creates a ring in the XY plane.
   * 
   * @param innerRadius - radius of the start of the circle.
   * @param outerRadius - radius of the end of the circle.
   * @param segments - number of segments in 2pi.
   */
  public CircleGeometry(float innerRadius, float outerRadius, int segments) {
    super(Primitive.TRIANGLE_STRIP, VertexPN.CONSTRUCTOR);
    allocate(segments * 2 + 2, 0);
    
    double theta = Math.PI * 2 / segments;
    float c = (float)Math.cos(theta);
    float s = (float)Math.sin(theta);
    float x = 1;
    float y = 0;
    
    VertexPN v = new VertexPN().position(x, y, 0).normal(0, 0, 1);
    for (int i = 0; i <= segments; i++) {
      putVertex(v.position(x * innerRadius, y * innerRadius, 0));
      putVertex(v.position(x * outerRadius, y * outerRadius, 0));
      float oldX = x;
      x = c * x - s * y;
      y = s * oldX + c * y;
    }
    
    rewind();
  }
  
  
  /** Creates a circle around the +X axis */
  public static CircleGeometry posX(float innerRadius, float outerRadius, int segments) {
    CircleGeometry g = new CircleGeometry(innerRadius, outerRadius, segments);
    g.transform(Transform.rotationY(Math.PI / 2));
    return g;
  }
  
  /** Creates a circle around the -X axis */
  public static CircleGeometry negX(float innerRadius, float outerRadius, int segments) {
    CircleGeometry g = new CircleGeometry(innerRadius, outerRadius, segments);
    g.transform(Transform.rotationY(-Math.PI / 2));
    return g;
  }

  /** Creates a circle around the +Y axis */
  public static CircleGeometry posY(float innerRadius, float outerRadius, int segments) {
    CircleGeometry g = new CircleGeometry(innerRadius, outerRadius, segments);
    g.transform(Transform.rotationX(-Math.PI / 2));
    return g;
  }
  
  /** Creates a circle around the -Y axis */
  public static CircleGeometry negY(float innerRadius, float outerRadius, int segments) {
    CircleGeometry g = new CircleGeometry(innerRadius, outerRadius, segments);
    g.transform(Transform.rotationX(Math.PI / 2));
    return g;
  }
  
  /** Creates a circle around the +Z axis */
  public static CircleGeometry posZ(float innerRadius, float outerRadius, int segments) {
    return new CircleGeometry(innerRadius, outerRadius, segments);
  }
  
  /** Creates a circle around the -Z axis */
  public static CircleGeometry negZ(float innerRadius, float outerRadius, int segments) {
    CircleGeometry g = new CircleGeometry(innerRadius, outerRadius, segments);
    g.transform(Transform.rotationY(Math.PI));
    return g;
  }
  
  /** Creates a circle around the +X axis */
  public static CircleGeometry posX(float radius, int segments) {
    CircleGeometry g = new CircleGeometry(radius, segments);
    g.transform(Transform.rotationY(Math.PI / 2));
    return g;
  }
  
  /** Creates a circle around the -X axis */
  public static CircleGeometry negX(float radius, int segments) {
    CircleGeometry g = new CircleGeometry(radius, segments);
    g.transform(Transform.rotationY(-Math.PI / 2));
    return g;
  }

  /** Creates a circle around the +Y axis */
  public static CircleGeometry posY(float radius, int segments) {
    CircleGeometry g = new CircleGeometry(radius, segments);
    g.transform(Transform.rotationX(-Math.PI / 2));
    return g;
  }
  
  /** Creates a circle around the -Y axis */
  public static CircleGeometry negY(float radius, int segments) {
    CircleGeometry g = new CircleGeometry(radius, segments);
    g.transform(Transform.rotationX(Math.PI / 2));
    return g;
  }
  
  /** Creates a circle around the +Z axis */
  public static CircleGeometry posZ(float radius, int segments) {
    return new CircleGeometry(radius, segments);
  }
  
  /** Creates a circle around the -Z axis */
  public static CircleGeometry negZ(float radius, int segments) {
    CircleGeometry g = new CircleGeometry(radius, segments);
    g.transform(Transform.rotationY(Math.PI));
    return g;
  }
}
