/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.scene.geometry;

import jgl.math.vector.Mat4f;
import jgl.math.vector.ReadableVec3f;
import jgl.math.vector.Transform;
import jgl.math.vector.Vec3f;

/**
 * A triangulated cylinder.
 * 
 * @author justin
 */
public class CylinderGeometry extends Geometry {

  /**
   * Creates a hollow cylinder (tube).
   */
  public CylinderGeometry(ReadableVec3f start, ReadableVec3f direction, float innerRadius,
      float outerRadius, float length, int radialSegments, int lengthSegments) {

    Vec3f p = start.copy();
    Mat4f m = Transform.rotation(direction, Math.PI * 2.0 / radialSegments);

    Vec3f[] basePositions = new Vec3f[radialSegments * 2];
    for (int i = 0; i < radialSegments * 2; i += 2) {
      basePositions[i] = p.times(innerRadius);
      basePositions[i + 1] = p.times(outerRadius);
      p = m.times(p);
    }

    int numVerts = (4 + 2 * lengthSegments) * basePositions.length;
    int numIndices = (2 + 2 * lengthSegments) * (6 * radialSegments);
    init(numVerts, numIndices, Geometry.PrimitiveType.TRIANGLES);

    // top
    int[] baseIndices = { 0, 1, 3, 0, 3, 2 };
    Vec3f offset = direction.times(length / 2);
    for (int i = 0; i < basePositions.length; i++) {
      basePositions[i].plus(offset).putInto(vertices);
      direction.putInto(normals);
    }
    for (int i = 0; i < radialSegments; i++) {
      int indexOffset = i * 2;
      for (int j = 0; j < baseIndices.length; j++) {
        indices.put((baseIndices[j] + indexOffset) % basePositions.length);
      }
    }

    // bottom
    for (int i = 0; i < basePositions.length; i++) {
      basePositions[i].minus(offset).putInto(vertices);
      direction.times(-1).putInto(normals);
    }
    for (int i = 0; i < 6 * radialSegments; i += 3) {
      // reverse triangle order from top so they appear CCW
      int a = indices.get(i);
      int b = indices.get(i + 1);
      int c = indices.get(i + 2);
      indices.put(a + basePositions.length);
      indices.put(c + basePositions.length);
      indices.put(b + basePositions.length);
    }

    // outside
    int vertsAdded = basePositions.length * 2;
    Vec3f step = direction.times(length / lengthSegments);
    for (int i = 0; i < lengthSegments; i++) {
      for (int j = 0; j < radialSegments; j++) {
        int j2 = j * 2;
        basePositions[1 + j2].plus(offset).putInto(vertices);
        basePositions[1 + j2].plus(offset.minus(step)).putInto(vertices);
        basePositions[1 + j2].normalized().putInto(normals);
        basePositions[1 + j2].normalized().putInto(normals);
        for (int k = 0; k < baseIndices.length; k++) {
          indices.put((baseIndices[k] + j2) % basePositions.length + vertsAdded);
        }
      }
      offset.subtract(step);
      vertsAdded += radialSegments * 2;
    }

    // inside
    offset = direction.times(length / 2);
    for (int i = 0; i < lengthSegments; i++) {
      for (int j = 0; j < radialSegments; j++) {
        int j2 = j * 2;
        basePositions[j2].plus(offset).putInto(vertices);
        basePositions[j2].plus(offset.minus(step)).putInto(vertices);
        basePositions[j2].times(-1).normalize().putInto(normals);
        basePositions[j2].times(-1).normalize().putInto(normals);
        indices.put((baseIndices[0] + j2) % basePositions.length + vertsAdded);
        indices.put((baseIndices[2] + j2) % basePositions.length + vertsAdded);
        indices.put((baseIndices[1] + j2) % basePositions.length + vertsAdded);
        indices.put((baseIndices[3] + j2) % basePositions.length + vertsAdded);
        indices.put((baseIndices[5] + j2) % basePositions.length + vertsAdded);
        indices.put((baseIndices[4] + j2) % basePositions.length + vertsAdded);
      }
      offset.subtract(step);
      vertsAdded += radialSegments * 2;
    }

    rewindBuffers();
  }

  /**
   * Creates a cylinder.
   */
  public CylinderGeometry(ReadableVec3f start, ReadableVec3f direction, float radius, float length,
      boolean capped, int radialSegments, int lengthSegments) {

    int numVerts = radialSegments * (lengthSegments + 1);
    int numIndices = 6 * radialSegments * lengthSegments;
    if (capped) {
      numVerts += radialSegments * 2;
      numIndices += (radialSegments - 2) * 6;
    }
    init(numVerts, numIndices, Geometry.PrimitiveType.TRIANGLES);

    Vec3f[] basePositions = new Vec3f[radialSegments];
    Vec3f v = start.copy();
    Mat4f m = Transform.rotation(direction, Math.PI * 2.0 / radialSegments);
    for (int i = 0; i < radialSegments; i++) {
      basePositions[i] = v;
      v = m.times(v);
    }
    
    Vec3f step = direction.times(length / lengthSegments);
    Vec3f offset = direction.times(-length / 2);
    int indexOffset = 0;
    for (int iy = 0; iy <= lengthSegments; iy++) {
      float normalizedY = (float)iy / lengthSegments;
      for (int ix = 0; ix < radialSegments; ix++) {
        
        float normalizedX = (float) ix / radialSegments;
        basePositions[ix].times(radius).plus(offset).putInto(vertices);
        basePositions[ix].putInto(normals);
        texCoords.put(normalizedX);
        texCoords.put(normalizedY);
        
        if (iy < lengthSegments) {
          int cx = ix + indexOffset;
          int nx = (ix + 1) % radialSegments + indexOffset;
          indices.put(cx);
          indices.put(nx);
          indices.put(nx + radialSegments);
          indices.put(cx);
          indices.put(nx + radialSegments);
          indices.put(cx + radialSegments);
        }
        
        v = m.times(v);
      }
      indexOffset += radialSegments;
      offset.add(step);
    }

    if (capped) {
      offset = direction.times(length / 2);
      
      // top
      for (int i = 0; i < basePositions.length; i++) {
        basePositions[i].times(radius).plus(offset).putInto(vertices);
        direction.putInto(normals);
        if (i < basePositions.length - 2) {
          indices.put(indexOffset);
          indices.put(i + 1 + indexOffset);
          indices.put(i + 2 + indexOffset);
        }
      }
      indexOffset += radialSegments;
      
      // bottom
      for (int i = 0; i < basePositions.length; i++) {
        basePositions[i].times(radius).minus(offset).putInto(vertices);
        direction.times(-1).putInto(normals);
        if (i < basePositions.length - 2) {
          indices.put(indexOffset);
          indices.put(i + 2 + indexOffset);
          indices.put(i + 1 + indexOffset);
        }
      }
      
    }

    rewindBuffers();
  }
  
  public static CylinderGeometry aroundX(float radius, float length, boolean capped, int radialSegments, int lengthSegments) {
    return new CylinderGeometry(Vec3f.axisZ(), Vec3f.axisX(), radius, length, capped, radialSegments, lengthSegments);
  }

  public static CylinderGeometry aroundX(float radius, float length, boolean capped, int radialSegments) {
    return new CylinderGeometry(Vec3f.axisZ(), Vec3f.axisX(), radius, length, capped, radialSegments, 1);
  }

  public static CylinderGeometry aroundY(float radius, float length, boolean capped, int radialSegments, int lengthSegments) {
    return new CylinderGeometry(Vec3f.axisX(), Vec3f.axisY(), radius, length, capped, radialSegments, lengthSegments);
  }

  public static CylinderGeometry aroundY(float radius, float length, boolean capped, int radialSegments) {
    return new CylinderGeometry(Vec3f.axisX(), Vec3f.axisY(), radius, length, capped, radialSegments, 1);
  }
  
  public static CylinderGeometry aroundZ(float radius, float length, boolean capped, int radialSegments, int lengthSegments) {
    return new CylinderGeometry(Vec3f.axisX(), Vec3f.axisZ(), radius, length, capped, radialSegments, lengthSegments);
  }

  public static CylinderGeometry aroundZ(float radius, float length, boolean capped, int radialSegments) {
    return new CylinderGeometry(Vec3f.axisX(), Vec3f.axisZ(), radius, length, capped, radialSegments, 1);
  }

  public static CylinderGeometry aroundX(float innerRadius, float outerRadius, float length, int radialSegments, int lengthSegments) {
    return new CylinderGeometry(Vec3f.axisZ(), Vec3f.axisX(), innerRadius, outerRadius, length, radialSegments, lengthSegments);
  }
  
  public static CylinderGeometry aroundX(float innerRadius, float outerRadius, float length, int radialSegments) {
    return new CylinderGeometry(Vec3f.axisZ(), Vec3f.axisX(), innerRadius, outerRadius, length, radialSegments, 1);
  }
  
  public static CylinderGeometry aroundY(float innerRadius, float outerRadius, float length, int radialSegments, int lengthSegments) {
    return new CylinderGeometry(Vec3f.axisX(), Vec3f.axisY(), innerRadius, outerRadius, length, radialSegments, lengthSegments);
  }
  
  public static CylinderGeometry aroundY(float innerRadius, float outerRadius, float length, int radialSegments) {
    return new CylinderGeometry(Vec3f.axisX(), Vec3f.axisY(), innerRadius, outerRadius, length, radialSegments, 1);
  }
  
  public static CylinderGeometry aroundZ(float innerRadius, float outerRadius, float length, int radialSegments, int lengthSegments) {
    return new CylinderGeometry(Vec3f.axisX(), Vec3f.axisZ(), innerRadius, outerRadius, length, radialSegments, lengthSegments);
  }
  
  public static CylinderGeometry aroundZ(float innerRadius, float outerRadius, float length, int radialSegments) {
    return new CylinderGeometry(Vec3f.axisX(), Vec3f.axisZ(), innerRadius, outerRadius, length, radialSegments, 1);
  }
}
