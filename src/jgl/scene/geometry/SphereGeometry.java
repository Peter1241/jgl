package jgl.scene.geometry;

import jgl.math.vector.Vec3f;

/**
 * A triangulated sphere.
 * 
 * @author justin
 */
public class SphereGeometry extends Geometry {

//  /**
//   * Creates a sphere by creating horizontal and vertical slices.
//   * 
//   * @param radius
//   * @param horizontalSegments
//   * @param verticalSegments
//   */
//  public SphereGeometry(float radius, int horizontalSegments, int verticalSegments) {
//
//  }

  /**
   * Creates a sphere by normalizing the vertices of a cube.
   * 
   * @param radius - radius of the sphere.
   * @param segments - segments of the box in x, y, and z.
   */
  public SphereGeometry(float radius, int segments) {
    BoxGeometry boxGeometry = new BoxGeometry(1, 1, 1, segments, segments, segments);
    init(boxGeometry.numVertices(), boxGeometry.numIndices(), boxGeometry.type);

    indices.put(boxGeometry.indices);
    texCoords.put(boxGeometry.texCoords);
    for (int i = 0; i < boxGeometry.numVertices(); i++) {
      Vec3f p = boxGeometry.nextVertex().normalize();
      p.times(radius).putInto(vertices);
      p.putInto(normals);
    }

    rewindBuffers();
  }
}
