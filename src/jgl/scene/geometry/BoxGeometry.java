package jgl.scene.geometry;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import jgl.math.vector.Vec3f;

/**
 * A triangulated box.
 * 
 * @author justin
 */
public class BoxGeometry extends Geometry {

  /**
   * Creates a box.
   * 
   * @param xSize - size along the x axis.
   * @param ySize - size along the y axis.
   * @param zSize - size along the z axis.
   * @param xSegments - number of segments along the x axis.
   * @param ySegments - number of segments along the y axis.
   * @param zSegments - number of segments along the z axis.
   */
  public BoxGeometry(float xSize, float ySize, float zSize, int xSegments, int ySegments,
      int zSegments) {

    PlaneGeometry posX = new PlaneGeometry(new Vec3f(0, 0, -1), new Vec3f(0, 1, 0), zSize, ySize,
        zSegments, ySegments);
    PlaneGeometry negX = new PlaneGeometry(new Vec3f(0, 0, 1), new Vec3f(0, 1, 0), zSize, ySize,
        zSegments, ySegments);
    PlaneGeometry posY = new PlaneGeometry(new Vec3f(1, 0, 0), new Vec3f(0, 0, -1), xSize, zSize,
        xSegments, zSegments);
    PlaneGeometry negY = new PlaneGeometry(new Vec3f(-1, 0, 0), new Vec3f(0, 0, -1), xSize, zSize,
        xSegments, zSegments);
    PlaneGeometry posZ = new PlaneGeometry(new Vec3f(1, 0, 0), new Vec3f(0, 1, 0), xSize, ySize,
        xSegments, ySegments);
    PlaneGeometry negZ = new PlaneGeometry(new Vec3f(-1, 0, 0), new Vec3f(0, 1, 0), xSize, ySize,
        xSegments, ySegments);

    int numVerts = 2 * (posX.numVertices() + posY.numVertices() + posZ.numVertices());
    int numFaces = 2 * (posX.numFaces() + posY.numFaces() + posZ.numFaces());
    vertices = FloatBuffer.allocate(numVerts * 3);
    normals = FloatBuffer.allocate(numVerts * 3);
    texCoords = FloatBuffer.allocate(numVerts * 2);
    faces = IntBuffer.allocate(numFaces * 6);

    addGeometry(posX, new Vec3f(xSize / 2, 0, 0));
    addGeometry(negX, new Vec3f(-xSize / 2, 0, 0));
    addGeometry(posY, new Vec3f(0, ySize / 2, 0));
    addGeometry(negY, new Vec3f(0, -ySize / 2, 0));
    addGeometry(posZ, new Vec3f(0, 0, zSize / 2));
    addGeometry(negZ, new Vec3f(0, 0, -zSize / 2));

    vertices.rewind();
    normals.rewind();
    texCoords.rewind();
    faces.rewind();
  }

  /**
   * Creates a box. Each face is 2 triangles.
   * 
   * @param xSize - size along x axis.
   * @param ySize - size along y axis.
   * @param zSize - size along z axis.
   */
  public BoxGeometry(float xSize, float ySize, float zSize) {
    this(xSize, ySize, zSize, 1, 1, 1);
  }

  /**
   * Creates a box. Each face is 2 triangles, and the sides are all 1 unit.
   */
  public BoxGeometry() {
    this(1, 1, 1, 1, 1, 1);
  }

  private void addGeometry(PlaneGeometry plane, Vec3f vertexOffset) {
    int faceOffset = vertices.position() / 3;

    normals.put(plane.normals);
    texCoords.put(plane.texCoords);

    for (int i = 0; i < plane.numVertices(); i++) {
      vertices.put(plane.vertices.get() + vertexOffset.x);
      vertices.put(plane.vertices.get() + vertexOffset.y);
      vertices.put(plane.vertices.get() + vertexOffset.z);
    }

    while (plane.faces.hasRemaining())
      faces.put(plane.faces.get() + faceOffset);
  }
}
