package jgl.scene.geometry.extra;

import jgl.math.vector.Transform;
import jgl.scene.geometry.Geometry;
import jgl.scene.geometry.VertexPNT;

/**
 * A triangulated plane.
 * 
 * @author justin
 */
public class PlaneGeometry extends Geometry<VertexPNT> {

  /**
   * Creates a plane with normal +Z, centered at the origin.
   * 
   * @param width - size along x axis.
   * @param height - size along y axis.
   * @param widthSegments - divisions along x axis (minimum 1).
   * @param heightSegments - divisions along y axis (minimum 1).
   */
  public PlaneGeometry(float width, float height, int widthSegments, int heightSegments) {
    super(Primitive.TRIANGLES, VertexPNT.CONSTRUCTOR);

    int numVertices = (widthSegments + 1) * (heightSegments + 1);
    int numIndices = widthSegments * heightSegments * 6;
    allocate(numVertices, numIndices);
    
    VertexPNT vertex = new VertexPNT().normal(0, 0, 1);

    int i = 0;
    for (int iy = 0; iy <= heightSegments; iy++) {
      float normalizedY = (float) iy / heightSegments;
      for (int ix = 0; ix <= widthSegments; ix++) {
        float normalizedX = (float) ix / widthSegments;

        vertex.position.x = (normalizedX - 0.5f) * width;
        vertex.position.y = (normalizedY - 0.5f) * height;
        vertex.texCoords(normalizedX, normalizedY);
        putVertex(vertex);

        if (ix < widthSegments && iy < heightSegments) {
          putIndex(i);
          putIndex(i + 1);
          putIndex(i + 2 + widthSegments);
          putIndex(i);
          putIndex(i + 2 + widthSegments);
          putIndex(i + 1 + widthSegments);
        }
        i++;
      }
    }
    
    rewind();
  }
  
  /**
   * Creates a plane with normal +Z, centered at the origin. Contains 2 triangles.
   * 
   * @param width - size along x axis.
   * @param height - size along y axis.
   */
  public PlaneGeometry(float width, float height) {
    super(Primitive.TRIANGLES, VertexPNT.CONSTRUCTOR);
    allocate(4, 6);
    VertexPNT vertex = new VertexPNT().normal(0, 0, 1);
    putVertex(vertex.position(-width / 2, -height / 2, 0).texCoords(0, 0));
    putVertex(vertex.position( width / 2, -height / 2, 0).texCoords(1, 0));
    putVertex(vertex.position( width / 2,  height / 2, 0).texCoords(1, 1));
    putVertex(vertex.position(-width / 2,  height / 2, 0).texCoords(0, 1));
    putIndices(new int[] {0, 1, 2, 0, 2, 3 });
    rewind();
  }
  
  /** Creates a plane with normal +X: width is in Z, height is in Y. */
  public static PlaneGeometry posX(float width, float height, int widthSegments, int heightSegments) {
    PlaneGeometry g = new PlaneGeometry(width, height, widthSegments, heightSegments);
    g.transform(Transform.rotationY(Math.PI / 2));
    return g;
  }

  /** Creates a plane with normal -X: width is in Z, height is in Y. */
  public static PlaneGeometry negX(float width, float height, int widthSegments, int heightSegments) {
    PlaneGeometry g = new PlaneGeometry(width, height, widthSegments, heightSegments);
    g.transform(Transform.rotationY(-Math.PI / 2));
    return g;
  }

  /** Creates a plane with normal +Y: width is in X, height is in Z. */
  public static PlaneGeometry posY(float width, float height, int widthSegments, int heightSegments) {
    PlaneGeometry g = new PlaneGeometry(width, height, widthSegments, heightSegments);
    g.transform(Transform.rotationX(-Math.PI / 2));
    return g;
  }

  /** Creates a plane with normal -Y: width is in X, height is in Z. */
  public static PlaneGeometry negY(float width, float height, int widthSegments, int heightSegments) {
    PlaneGeometry g = new PlaneGeometry(width, height, widthSegments, heightSegments);
    g.transform(Transform.rotationX(Math.PI / 2));
    return g;
  }

  /** Creates a plane with normal +Z: width is in X, height is in Y. */
  public static PlaneGeometry posZ(float width, float height, int widthSegments, int heightSegments) {
    return new PlaneGeometry(width, height, widthSegments, heightSegments);
  }

  /** Creates a plane with normal -Z: width is in X, height is in Y. */
  public static PlaneGeometry negZ(float width, float height, int widthSegments, int heightSegments) {
    PlaneGeometry g = new PlaneGeometry(width, height, widthSegments, heightSegments);
    g.transform(Transform.rotationY(Math.PI));
    return g;
  }

  /**
   * Creates a plane with normal +X: width is in Z, height is in Y. Width and height segments are
   * both 1.
   */
  public static PlaneGeometry posX(float width, float height) {
    return posX(width, height);
  }

  /**
   * Creates a plane with normal -X: width is in Z, height is in Y. Width and height segments are
   * both 1.
   */
  public static PlaneGeometry negX(float width, float height) {
    return negX(width, height);
  }

  /**
   * Creates a plane with normal +Y: width is in X, height is in Z. Width and height segments are
   * both 1.
   */
  public static PlaneGeometry posY(float width, float height) {
    return posY(width, height);
  }

  /**
   * Creates a plane with normal -Y: width is in X, height is in Z. Width and height segments are
   * both 1.
   */
  public static PlaneGeometry negY(float width, float height) {
    return negY(width, height);
  }

  /**
   * Creates a plane with normal +Z: width is in X, height is in Y. Width and height segments are
   * both 1.
   */
  public static PlaneGeometry posZ(float width, float height) {
    return posZ(width, height);
  }

  /**
   * Creates a plane with normal -Z: width is in X, height is in Y. Width and height segments are
   * both 1.
   */
  public static PlaneGeometry negZ(float width, float height) {
    return negZ(width, height);
  }
}
