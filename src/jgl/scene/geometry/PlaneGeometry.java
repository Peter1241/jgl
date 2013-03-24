package jgl.scene.geometry;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import jgl.math.vector.Vec3f;

/**
 * A triangulated plane.
 * 
 * @author justin
 */
public class PlaneGeometry extends Geometry {

  /**
   * Creates a plane.
   * 
   * @param x - axis 1
   * @param y - axis 2
   * @param xSize - size of plane along axis 1
   * @param ySize - size of plane along axis 2
   * @param xSegments - number of segments along axis 1
   * @param ySegments - number of segments along axis 2
   */
  public PlaneGeometry(Vec3f x, Vec3f y, float xSize, float ySize, int xSegments, int ySegments) {
    int numVerts = (xSegments + 1) * (ySegments + 1);
    vertices = FloatBuffer.allocate(numVerts * 3);
    normals = FloatBuffer.allocate(numVerts * 3);
    texCoords = FloatBuffer.allocate(numVerts * 2);
    faces = IntBuffer.allocate(xSegments * ySegments * 6);

    Vec3f z = x.cross(y);
    float stepX = xSize / xSegments;
    float stepY = ySize / ySegments;
    Vec3f bottomLeft = x.times(-xSize / 2).plus(y.times(-ySize / 2));

    int i = 0;
    for (int iy = 0; iy <= ySegments; iy++) {
      float normalizedY = (float) iy / ySegments;
      for (int ix = 0; ix <= xSegments; ix++) {
        float normalizedX = (float) ix / xSegments;
        Vec3f p = bottomLeft.plus(x.times(ix * stepX)).plus(y.times(iy * stepY));
        vertices.put(p.x);
        vertices.put(p.y);
        vertices.put(p.z);
        normals.put(z.x);
        normals.put(z.y);
        normals.put(z.z);
        texCoords.put(normalizedX);
        texCoords.put(normalizedY);

        if (ix < xSegments && iy < ySegments) {
          faces.put(i);
          faces.put(i + 1);
          faces.put(i + 2 + xSegments);
          faces.put(i);
          faces.put(i + 2 + xSegments);
          faces.put(i + 1 + xSegments);
        }
        i++;
      }
    }

    vertices.rewind();
    normals.rewind();
    texCoords.rewind();
    faces.rewind();
  }
}
