/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.scene.geometry;

import jgl.math.vector.Vec3f;

/**
 * A line-based grid.
 * 
 * @author justin
 */
public class GridGeometry extends Geometry {

  /**
   * Creates a grid.
   * 
   * @param x - axis 1.
   * @param y - axis 2.
   * @param segments - number of segments in the grid.
   * @param segmentSize - size of each segment.
   */
  public GridGeometry(Vec3f x, Vec3f y, int segments, float segmentSize) {

    float size = segmentSize * segments;
    init((segments + 1) * 4, 0, Geometry.PrimitiveType.LINES);

    Vec3f normal = x.cross(y);

    Vec3f gridWidth = x.times(size);
    Vec3f gridHeight = y.times(size);
    Vec3f bottom = x.times(-size / 2).plus(y.times(-size / 2));
    Vec3f left = bottom.copy();

    for (int i = 0; i <= segments; i++) {
      float progress = (float) i / segments;

      bottom.putInto(vertices);
      normal.putInto(normals);
      texCoords.put(progress);
      texCoords.put(0);

      bottom.plus(gridHeight).putInto(vertices);
      normal.putInto(normals);
      texCoords.put(progress);
      texCoords.put(1);

      left.putInto(vertices);
      normal.putInto(normals);
      texCoords.put(0);
      texCoords.put(progress);

      left.plus(gridWidth).putInto(vertices);
      normal.putInto(normals);
      texCoords.put(1);
      texCoords.put(progress);

      bottom.add(x.times(segmentSize));
      left.add(y.times(segmentSize));
    }

    rewindBuffers();
  }
  
  public static GridGeometry aroundX(int segments, float segmentSize) {
    return new GridGeometry(new Vec3f(0, 0, -1), Vec3f.axisY(), segments, segmentSize);
  }
  
  public static GridGeometry aroundY(int segments, float segmentSize) {
    return new GridGeometry(Vec3f.axisX(), new Vec3f(0, 0, -1), segments, segmentSize);
  }
  
  public static GridGeometry aroundZ(int segments, float segmentSize) {
    return new GridGeometry(Vec3f.axisX(), Vec3f.axisY(), segments, segmentSize);
  }
}
