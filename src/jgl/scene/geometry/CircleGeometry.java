package jgl.scene.geometry;

import java.nio.FloatBuffer;

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
   * @param x
   * @param y
   * @param radius
   * @param segments
   */
  public CircleGeometry(Vec3f x, Vec3f y, float radius, int segments) {

    int numVerts = segments + 2;
    vertices = FloatBuffer.allocate(numVerts * 3);
    normals = FloatBuffer.allocate(numVerts * 3);
    texCoords = FloatBuffer.allocate(numVerts * 2);
    type = Geometry.PrimitiveType.TRIANGLE_FAN;
    
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
      texCoords.put((float)i/segments);
      texCoords.put(1);
      
      v = m.times(v);
    }
    
    vertices.rewind();
    normals.rewind();
    texCoords.rewind();
  }

  /**
   * Creates a circle with a hole in the middle.
   * 
   * @param x
   * @param y
   * @param innerRadius
   * @param outerRadius
   * @param segments
   */
  public CircleGeometry(Vec3f x, Vec3f y, float innerRadius, float outerRadius, int segments) {

    Vec3f z = x.cross(y);

    Mat4f m = Transform.rotation(z, Math.PI * 2.0 / segments);
    Vec4f v = new Vec4f(x.x, x.y, x.z, 0);

    for (int i = 0; i <= segments; i++) {

      // triangle strip

      float progress = (float) i / segments;

      // inner
      vertices.put(v.x);
      vertices.put(v.y);
      vertices.put(v.z);
      normals.put(z.x);
      normals.put(z.y);
      normals.put(z.z);
      texCoords.put(progress);
      texCoords.put(0);

      // outer
      texCoords.put(progress);
      texCoords.put(1);

      v = m.times(v);
    }
  }
}
