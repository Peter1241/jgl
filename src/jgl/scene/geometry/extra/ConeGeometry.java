/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.scene.geometry.extra;

import jgl.math.vector.Mat4f;
import jgl.math.vector.Transform;
import jgl.math.vector.Vec3f;
import jgl.scene.geometry.Geometry;
import jgl.scene.geometry.VertexPN;

/**
 * A triangulated cone.
 * 
 * @author justin
 */
public class ConeGeometry extends Geometry<VertexPN> {

  /**
   * Creates a cone.
   * 
   * @param baseRadius - radius of the base of the cone.
   * @param length - length of the cone along Z axis.
   * @param segments - number of segments in 2pi.
   * @param fillBase - add triangles to the base of the cone.
   */
  public ConeGeometry(float baseRadius, float length, int segments, boolean fillBase) {
    super(VertexPN.CONSTRUCTOR);

    if (fillBase) {
      primitive = Primitive.TRIANGLES;
      allocate(2 * segments + 1, 6 * segments - 6);

      // cone
      Mat4f m = Transform.rotationZ(Math.PI * 2 / segments);
      VertexPN v = new VertexPN().position(0, 0, length / 2).normal(0, 0, 1);
      putVertex(v);
      v.position(baseRadius, 0, -length / 2);
      v.normal(new Vec3f(length, 0, baseRadius).normalized());
      for (int i = 0; i < segments; i++) {
        putVertex(v);
        putIndex(0);
        putIndex(i + 1);
        putIndex((i + 1) % segments + 1);
        v.transform(m);
      }
      
      // base
      v.position(baseRadius, 0, -length / 2).normal(0, 0, -1);
      for (int i = 0; i < segments; i++) {
        putVertex(v);
        v.transform(m);
        if (i < segments - 2) {
          putIndex(segments + 1);
          putIndex(i + 1 + segments + 1);
          putIndex(i + 2 + segments + 1);
        }
      }
      
    } else {
      primitive = Primitive.TRIANGLE_FAN;
      allocate(segments + 2, 0);

      Mat4f m = Transform.rotationZ(Math.PI * 2 / segments);
      VertexPN v = new VertexPN().position(0, 0, length / 2).normal(0, 0, 1);
      putVertex(v);
      v.position(baseRadius, 0, -length / 2);
      v.normal(new Vec3f(length, 0, baseRadius).normalized());
      for (int i = 0; i <= segments; i++) {
        putVertex(v);
        v.transform(m);
      }
    }

    rewind();
  }
  
  public static ConeGeometry posX(float baseRadius, float length, int segments, boolean fillBase) {
    ConeGeometry g = new ConeGeometry(baseRadius, length, segments, fillBase);
    g.transform(Transform.rotationY(Math.PI / 2));
    return g;
  }
  
  public static ConeGeometry negX(float baseRadius, float length, int segments, boolean fillBase) {
    ConeGeometry g = new ConeGeometry(baseRadius, length, segments, fillBase);
    g.transform(Transform.rotationY(-Math.PI / 2));
    return g;
  }
  
  public static ConeGeometry posY(float baseRadius, float length, int segments, boolean fillBase) {
    ConeGeometry g = new ConeGeometry(baseRadius, length, segments, fillBase);
    g.transform(Transform.rotationX(-Math.PI / 2));
    return g;
  }
  
  public static ConeGeometry negY(float baseRadius, float length, int segments, boolean fillBase) {
    ConeGeometry g = new ConeGeometry(baseRadius, length, segments, fillBase);
    g.transform(Transform.rotationX(Math.PI / 2));
    return g;
  }
  
  public static ConeGeometry posZ(float baseRadius, float length, int segments, boolean fillBase) {
    return new ConeGeometry(baseRadius, length, segments, fillBase);
  }
  
  public static ConeGeometry negZ(float baseRadius, float length, int segments, boolean fillBase) {
    ConeGeometry g = new ConeGeometry(baseRadius, length, segments, fillBase);
    g.transform(Transform.rotationY(Math.PI));
    return g;
  }
}
