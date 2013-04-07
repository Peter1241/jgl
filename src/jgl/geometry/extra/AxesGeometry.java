/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.geometry.extra;

import jgl.geometry.Geometry;
import jgl.geometry.VertexPN;
import jgl.geometry.VertexPNC;
import jgl.math.vector.Transform;
import jgl.math.vector.Vec3f;

/**
 * Draws 3D axes.
 * 
 * @author justin
 */
public class AxesGeometry extends Geometry<VertexPNC> {

  public AxesGeometry(float length) {
    super(Primitive.TRIANGLES, VertexPNC.CONSTRUCTOR);

    int segments = 8;
    int numVertices = 3 * (2 * segments + 1 + 4 * segments);
    int numIndices = 3 * (6 * segments - 6 + 12 * segments - 12);
    allocate(numVertices, numIndices);
  
    ConeGeometry xCone = ConeGeometry.posX(.15f, .3f, segments, true);
    xCone.transform(Transform.translation(length, 0, 0));
    add(xCone, new Vec3f(1, 0, 0));
    
    ConeGeometry yCone = ConeGeometry.posY(.15f, .3f, segments, true);
    yCone.transform(Transform.translation(0, length, 0));
    add(yCone, new Vec3f(0, 1, 0));
    
    ConeGeometry zCone = ConeGeometry.posZ(.15f, .3f, segments, true);
    zCone.transform(Transform.translation(0, 0, length));
    add(zCone, new Vec3f(0, 0, 1));

    CylinderGeometry xCylinder = CylinderGeometry.posX(.05f, .05f, length, segments, true);
    xCylinder.transform(Transform.translation(length/2, 0, 0));
    add(xCylinder, new Vec3f(1, 0, 0));
    
    CylinderGeometry yCylinder = CylinderGeometry.posY(.05f, .05f, length, segments, true);
    yCylinder.transform(Transform.translation(0, length/2, 0));
    add(yCylinder, new Vec3f(0, 1, 0));
    
    CylinderGeometry zCylinder = CylinderGeometry.posZ(.05f, .05f, length, segments, true);
    zCylinder.transform(Transform.translation(0, 0, length/2));
    add(zCylinder, new Vec3f(0, 0, 1));
    
    rewind();
  }
  
  private void add(Geometry<VertexPN> g, Vec3f color) {
    int indexOffset = vertexPosition();
    VertexPNC v = new VertexPNC();
    for (int i = 0; i < g.numVertices(); i++) {
      VertexPN vOther = g.getVertex();
      putVertex(v.position(vOther.position).normal(vOther.normal).color(color));
    }
    for (int i = 0; i < g.numIndices(); i++) {
      putIndex(g.getIndex() + indexOffset);
    }
  }
}
