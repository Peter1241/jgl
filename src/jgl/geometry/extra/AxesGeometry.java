/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.geometry.extra;

import jgl.geometry.Geometry;
import jgl.geometry.VertexPN;
import jgl.math.vector.Transform;

/**
 * Draws 3D axes.
 * 
 * @author justin
 */
public class AxesGeometry extends Geometry<VertexPN> {

  public AxesGeometry(float length) {
    super(Primitive.TRIANGLES, VertexPN.CONSTRUCTOR);

    int segments = 8;
    int numVertices = 3 * (2 * segments + 1 + 4 * segments);
    int numIndices = 3 * (6 * segments - 6 + 12 * segments - 12);
    allocate(numVertices, numIndices);
  
    ConeGeometry xCone = ConeGeometry.posX(.15f, .3f, segments, true);
    xCone.transform(Transform.translation(length, 0, 0));
    add(xCone);
    
    ConeGeometry yCone = ConeGeometry.posY(.15f, .3f, segments, true);
    yCone.transform(Transform.translation(0, length, 0));
    add(yCone);
    
    ConeGeometry zCone = ConeGeometry.posZ(.15f, .3f, segments, true);
    zCone.transform(Transform.translation(0, 0, length));
    add(zCone);

    CylinderGeometry xCylinder = CylinderGeometry.posX(.05f, .05f, length, segments, true);
    xCylinder.transform(Transform.translation(length/2, 0, 0));
    add(xCylinder);
    
    CylinderGeometry yCylinder = CylinderGeometry.posY(.05f, .05f, length, segments, true);
    yCylinder.transform(Transform.translation(0, length/2, 0));
    add(yCylinder);
    
    CylinderGeometry zCylinder = CylinderGeometry.posZ(.05f, .05f, length, segments, true);
    zCylinder.transform(Transform.translation(0, 0, length/2));
    add(zCylinder);
    
    rewind();
  }
  
  private void add(Geometry<VertexPN> g) {
    int indexOffset = vertexPosition();
    for (int i = 0; i < g.numVertices(); i++) {
      putVertex(g.getVertex());
    }
    for (int i = 0; i < g.numIndices(); i++) {
      putIndex(g.getIndex() + indexOffset);
    }
  }
}
