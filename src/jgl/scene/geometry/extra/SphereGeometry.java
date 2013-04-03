/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.scene.geometry.extra;

import jgl.scene.geometry.Geometry;
import jgl.scene.geometry.VertexPNT;

/**
 * A triangulated sphere.
 * 
 * @author justin
 */
public class SphereGeometry extends Geometry<VertexPNT> {

  /**
   * Creates a sphere by normalizing the vertices of a cube.
   * 
   * @param radius - radius of the sphere.
   * @param segments - segments of the box in x, y, and z.
   */
  public SphereGeometry(float radius, int segments) {
    super(Primitive.TRIANGLES, VertexPNT.CONSTRUCTOR);

    BoxGeometry box = new BoxGeometry(1, 1, 1, segments, segments, segments);
    allocate(box.numVertices(), box.numIndices());

    while (box.indexPosition() < box.numIndices())
      putIndex(box.getIndex());
    
    while (box.vertexPosition() < box.numVertices()) {
      VertexPNT vertex = box.getVertex();
      vertex.position.normalize();
      vertex.normal = vertex.position;
      putVertex(vertex);
    }

    rewind();
  }
}
