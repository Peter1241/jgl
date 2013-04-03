package jgl.scene.geometry.extra;

import jgl.math.vector.Transform;
import jgl.scene.geometry.Geometry;
import jgl.scene.geometry.VertexP;

/**
 * Creates a grid. Similar to PlaneGeometry, but drawn with GL_LINES and no normals or texture
 * coordinates.
 * 
 * @author justin
 */
public class GridGeometry extends Geometry<VertexP> {

  /**
   * Creates a grid in the XY plane.
   * 
   * @param width - size of the grid in x.
   * @param height - size of the grid in y.
   * @param widthSegments - number of segments in x.
   * @param heightSegments - number of segments in y.
   */
  public GridGeometry(float width, float height, int widthSegments, int heightSegments) {
    super(Primitive.LINES, VertexP.CONSTRUCTOR);

    allocate(2 * (widthSegments + heightSegments + 2), 0);

    VertexP vertex = new VertexP();

    for (int i = 0; i <= widthSegments; i++) {
      float x = width * ((float)i / widthSegments - 0.5f);
      putVertex(vertex.position(x, -height / 2, 0));
      putVertex(vertex.position(x, height / 2, 0));
    }
    
    for (int i = 0; i <= heightSegments; i++) {
      float y = width * ((float)i / widthSegments - 0.5f);
      putVertex(vertex.position(-width / 2, y, 0));
      putVertex(vertex.position(width / 2, y, 0));
    }
    
    rewind();
  }

  public static GridGeometry inXY(float width, float height, int widthSegments, int heightSegments) {
    return new GridGeometry(width, height, widthSegments, heightSegments);
  }

  public static GridGeometry inYZ(float width, float height, int widthSegments, int heightSegments) {
    GridGeometry g = new GridGeometry(width, height, widthSegments, heightSegments);
    g.transform(Transform.rotationY(Math.PI / 2));
    return g;
  }

  public static GridGeometry inXZ(float width, float height, int widthSegments, int heightSegments) {
    GridGeometry g = new GridGeometry(width, height, widthSegments, heightSegments);
    g.transform(Transform.rotationX(-Math.PI / 2));
    return g;
  }
}
