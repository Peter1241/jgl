/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.scene.geometry;

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
    int numIndices = xSegments * ySegments * 6;
    init(numVerts, numIndices, Geometry.PrimitiveType.TRIANGLES);
    
    Vec3f z = x.cross(y);
    Vec3f bottomLeft = x.times(-xSize / 2).plus(y.times(-ySize / 2));

    int i = 0;
    for (int iy = 0; iy <= ySegments; iy++) {
      float normalizedY = (float) iy / ySegments;
      for (int ix = 0; ix <= xSegments; ix++) {
        float normalizedX = (float) ix / xSegments;
        
        Vec3f p = bottomLeft.copy();
        p.add(x.times(normalizedX * xSize));
        p.add(y.times(normalizedY * ySize));
        
        p.putInto(vertices);
        z.putInto(normals);
        texCoords.put(normalizedX);
        texCoords.put(normalizedY);

        if (ix < xSegments && iy < ySegments) {
          indices.put(i);
          indices.put(i + 1);
          indices.put(i + 2 + xSegments);
          indices.put(i);
          indices.put(i + 2 + xSegments);
          indices.put(i + 1 + xSegments);
        }
        i++;
      }
    }

    rewindBuffers();
  }
}
