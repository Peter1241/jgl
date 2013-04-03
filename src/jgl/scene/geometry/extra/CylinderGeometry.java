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
      
      // side
      Mat4f m = Transform.rotationZ(Math.PI * 2 / segments);
      Vec3f n = new Vec3f(length, 0, (bottomRadius - topRadius)).normalized();
      VertexPN b = new VertexPN().position(bottomRadius, 0, -length / 2).normal(n);
      VertexPN t = new VertexPN().position(topRadius, 0, length / 2).normal(n);
      for (int i = 0; i <= segments; i++) {
        putVertex(b);
        putVertex(t);
        b.transform(m);
        t.transform(m);
      }
      
      // top
      
      // bottom
      
    } else {
      primitive = Primitive.TRIANGLE_STRIP;
      allocate(2 * segments + 2, 0);
      
      Mat4f m = Transform.rotationZ(Math.PI * 2 / segments);
      Vec3f n = new Vec3f(length, 0, (bottomRadius - topRadius)).normalized();
      VertexPN b = new VertexPN().position(bottomRadius, 0, -length / 2).normal(n);
      VertexPN t = new VertexPN().position(topRadius, 0, length / 2).normal(n);
      for (int i = 0; i <= segments; i++) {
        putVertex(b);
        putVertex(t);
        b.transform(m);
        t.transform(m);
      }
    }
  }
}
