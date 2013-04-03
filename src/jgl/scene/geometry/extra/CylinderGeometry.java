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
 * A triangulated cylinder.
 * 
 * @author justin
 */
public class CylinderGeometry extends Geometry<VertexPN> {

  /**
   * Creates a cylinder.
   * 
   * @param topRadius - radius at the top (+Z)
   * @param bottomRadius - radius at the bottom (-Z)
   * @param length - distance from bottom to top in Z
   * @param segments - number of segments in 2pi.
   * @param fillEnds - add triangles at the top and bottom of the cylinder.
   */
  public CylinderGeometry(float topRadius, float bottomRadius, float length, int segments,
      boolean fillEnds) {
    super(VertexPN.CONSTRUCTOR);

    if (fillEnds) {
      primitive = Primitive.TRIANGLES;
      allocate(4 * segments, 12 * segments - 12);

      Mat4f m = Transform.rotationZ(Math.PI * 2 / segments);
      
      // side
      {
        Vec3f n = new Vec3f(length, 0, (bottomRadius - topRadius)).normalized();
        VertexPN b = new VertexPN().position(bottomRadius, 0, -length / 2).normal(n);
        VertexPN t = new VertexPN().position(topRadius, 0, length / 2).normal(n);
        for (int i = 0; i < segments; i++) {
          putVertex(t);
          putVertex(b);
          b.transform(m);
          t.transform(m);

          int cur = 2 * i;
          int next = 2 * ((i + 1) % segments);
          putIndices(new int[] { cur, cur + 1, next + 1, cur, next + 1, next });
        }
      }
      
      // top
      {
        int startIndex = 2 * segments;
        VertexPN v = new VertexPN().position(topRadius, 0, length / 2).normal(0, 0, 1);
        for (int i = 0; i < segments; i++) {
          putVertex(v);
          v.transform(m);
          if (i < segments - 2)
            putIndices(new int[] { startIndex, startIndex + i + 1, startIndex + i + 2 });
        }
      }

      // bottom
      {
        int startIndex = 3 * segments;
        VertexPN v = new VertexPN().position(bottomRadius, 0, -length / 2).normal(0, 0, -1);
        for (int i = 0; i < segments; i++) {
          putVertex(v);
          v.transform(m);
          if (i < segments - 2)
            putIndices(new int[] { startIndex, startIndex + i + 2, startIndex + i + 1 });
        }
      }
      
    } else {
      primitive = Primitive.TRIANGLE_STRIP;
      allocate(2 * segments + 2, 0);
      
      Mat4f m = Transform.rotationZ(Math.PI * 2 / segments);
      Vec3f n = new Vec3f(length, 0, (bottomRadius - topRadius)).normalized();
      VertexPN b = new VertexPN().position(bottomRadius, 0, -length / 2).normal(n);
      VertexPN t = new VertexPN().position(topRadius, 0, length / 2).normal(n);
      for (int i = 0; i <= segments; i++) {
        putVertex(t);
        putVertex(b);
        b.transform(m);
        t.transform(m);
      }
    }
    
    rewind();
  }
  
  /** Creates a cylinder around the +X axis */
  public static CylinderGeometry posX(float topRadius, float bottomRadius, float length, int segments,
      boolean fillEnds) {
    CylinderGeometry g = new CylinderGeometry(topRadius, bottomRadius, length, segments, fillEnds);
    g.transform(Transform.rotationY(Math.PI / 2));
    return g;
  }
  
  /** Creates a cylinder around the -X axis */
  public static CylinderGeometry negX(float topRadius, float bottomRadius, float length, int segments,
      boolean fillEnds) {
    CylinderGeometry g = new CylinderGeometry(topRadius, bottomRadius, length, segments, fillEnds);
    g.transform(Transform.rotationY(-Math.PI / 2));
    return g;
  }

  /** Creates a cylinder around the +Y axis */
  public static CylinderGeometry posY(float topRadius, float bottomRadius, float length, int segments,
      boolean fillEnds) {
    CylinderGeometry g = new CylinderGeometry(topRadius, bottomRadius, length, segments, fillEnds);
    g.transform(Transform.rotationX(-Math.PI / 2));
    return g;
  }
  
  /** Creates a cylinder around the -Y axis */
  public static CylinderGeometry negY(float topRadius, float bottomRadius, float length, int segments,
      boolean fillEnds) {
    CylinderGeometry g = new CylinderGeometry(topRadius, bottomRadius, length, segments, fillEnds);
    g.transform(Transform.rotationX(Math.PI / 2));
    return g;
  }
  
  /** Creates a cylinder around the +Z axis */
  public static CylinderGeometry posZ(float topRadius, float bottomRadius, float length, int segments,
      boolean fillEnds) {
    return new CylinderGeometry(topRadius, bottomRadius, length, segments, fillEnds);
  }
  
  /** Creates a cylinder around the -Z axis */
  public static CylinderGeometry negZ(float topRadius, float bottomRadius, float length, int segments,
      boolean fillEnds) {
    CylinderGeometry g = new CylinderGeometry(topRadius, bottomRadius, length, segments, fillEnds);
    g.transform(Transform.rotationY(Math.PI));
    return g;
  }
}
