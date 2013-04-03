/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.scene.geometry.extra;

import jgl.math.vector.Transform;
import jgl.math.vector.Vec3f;
import jgl.scene.geometry.Geometry;
import jgl.scene.geometry.VertexPNT;

/**
 * A triangulated box.
 * 
 * @author justin
 */
public class BoxGeometry extends Geometry<VertexPNT> {

  /**
   * Creates a box. Each face is 2 triangles.
   * 
   * @param width - size along x axis.
   * @param height - size along y axis.
   * @param depth - size along z axis.
   */
  public BoxGeometry(float width, float height, float depth) {
    this(width, height, depth, 1, 1, 1);
  }
  
  /**
   * Creates a box.
   * 
   * @param width - size along the x axis.
   * @param height - size along the y axis.
   * @param depth - size along the z axis.
   * @param widthSegments - number of segments along the x axis.
   * @param heightSegments - number of segments along the y axis.
   * @param depthSegments - number of segments along the z axis.
   */
  public BoxGeometry(float width, float height, float depth, int widthSegments, int heightSegments, int depthSegments) {
    super(Primitive.TRIANGLES, VertexPNT.CONSTRUCTOR);

    int numVertices = 2 * (
        (widthSegments + 1) * (heightSegments + 1) + 
        (widthSegments + 1) * (depthSegments + 1) + 
        (heightSegments + 1) * (depthSegments + 1));
    
    int numIndices = 12 * (
        (widthSegments * heightSegments) + 
        (widthSegments * depthSegments) +
        (heightSegments * depthSegments));
    
    allocate(numVertices, numIndices);
    
    add(PlaneGeometry.posX(depth, height, depthSegments, heightSegments), new Vec3f(width / 2, 0, 0));
    add(PlaneGeometry.negX(depth, height, depthSegments, heightSegments), new Vec3f(-width / 2, 0, 0));
    add(PlaneGeometry.posY(width, depth, widthSegments, depthSegments), new Vec3f(0, height / 2, 0));
    add(PlaneGeometry.negY(width, depth, widthSegments, depthSegments), new Vec3f(0, -height / 2, 0));
    add(PlaneGeometry.posZ(width, height, widthSegments, heightSegments), new Vec3f(0, 0, depth / 2));
    add(PlaneGeometry.negZ(width, height, widthSegments, heightSegments), new Vec3f(0, 0, -depth / 2));

    rewind();
  }

  private void add(PlaneGeometry plane, Vec3f vertexOffset) {
    // copy indices with offset = # vertices from previous planes
    int indexOffset = vertexPosition();
    while (plane.indexPosition() < plane.numIndices()) {
      putIndex(plane.getIndex() + indexOffset);
    }
    
    // copy vertices with offset to position
    while (plane.vertexPosition() < plane.numVertices()) {
      VertexPNT vertex = plane.getVertex();
      vertex.position.add(vertexOffset);
      putVertex(vertex);
    }
  }
}
