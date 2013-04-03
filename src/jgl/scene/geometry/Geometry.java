package jgl.scene.geometry;

import java.nio.ByteBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import jgl.math.vector.Mat4f;
import jgl.scene.geometry.Vertex.Constructor;

import com.jogamp.common.nio.Buffers;

/**
 * Generic storage of vertices and indices. Vertex data is stored interleaved in a single buffer;
 * its layout is determined by the vertex type parameter. Actual storage is done in direct byte
 * buffers, but this class provides convenience methods for manipulating vertices rather than bytes.
 * 
 * @author justin
 */
public class Geometry<T extends Vertex> {

  private final Vertex                vertexType;
  private final Vertex.Constructor<T> constructor;
  private IndexType                   indexType;
  private ByteBuffer                  vertices;
  private ByteBuffer                  indices;
  protected Primitive                 primitive;

  /**
   * Creates empty geometry and allocates buffers to fixed sizes.
   * 
   * @param type - type of primitives that will be stored.
   * @param constructor - constructor class for instantiating empty vertices.
   * @param numVertices - number of vertices to store.
   * @param numIndices - number of indices to store.
   */
  public Geometry(Primitive type, Constructor<T> constructor, int numVertices, int numIndices) {
    this.primitive = type;
    this.constructor = constructor;
    this.vertexType = constructor.construct();
    allocate(numVertices, numIndices);
  }

  /**
   * Creates empty geometry without allocating space. Intended for subclasses so they can calculate
   * number of vertices & indices before calling the super constructor.
   */
  protected Geometry(Primitive type, Vertex.Constructor<T> constructor) {
    this.primitive = type;
    this.constructor = constructor;
    this.vertexType = constructor.construct();
  }

  /**
   * Creates empty geometry without allocating space. Intended for subclasses so they can calculate
   * number of vertices & indices before calling the super constructor.
   */
  protected Geometry(Vertex.Constructor<T> constructor) {
    this.constructor = constructor;
    this.vertexType = constructor.construct();
  }

  /**
   * Initialize buffers to required sizes.
   */
  protected void allocate(int numVertices, int numIndices) {
    indexType = IndexType.fromVertexCount(numVertices);
    vertices = Buffers.newDirectByteBuffer(vertexType.stride() * numVertices);
    if (numIndices > 0)
      indices = Buffers.newDirectByteBuffer(indexType.size * numIndices);
  }

  /**
   * Returns the primitive type.
   */
  public Primitive getPrimitive() {
    return primitive;
  }

  /**
   * Returns the vertex type.
   */
  public Vertex getVertexType() {
    return vertexType;
  }

  /**
   * Returns the index type.
   */
  public IndexType getIndexType() {
    return indexType;
  }

  /**
   * Returns the total number of vertices the geometry can store.
   */
  public int numVertices() {
    return vertices.capacity() / vertexType.stride();
  }

  /**
   * Returns the total number of indices the geometry can store.
   */
  public int numIndices() {
    return indices == null ? 0 : indices.capacity() / indexType.size;
  }

  /**
   * Returns the current vertex position.
   */
  public int vertexPosition() {
    return vertices.position() / vertexType.stride();
  }

  /**
   * Returns the current index position.
   */
  public int indexPosition() {
    return indices == null ? 0 : indices.position() / indexType.size;
  }

  /**
   * Sets the vertex and index positions to the start.
   */
  public void rewind() {
    vertices.rewind();
    if (indices != null)
      indices.rewind();
  }

  /**
   * Reads vertex at the current vertex position, then increments the position.
   */
  public T getVertex() {
    T vertex = constructor.construct();
    vertex.get(vertices);
    return vertex;
  }

  /**
   * Reads vertex at the specified vertex position. The position remains unchanged.
   */
  public T getVertex(int position) {
    int prevPosition = vertexPosition();
    vertices.position(position * vertexType.stride());
    T vertex = getVertex();
    vertices.position(prevPosition);
    return vertex;
  }

  /**
   * Writes vertex at the current vertex position, then increments the position.
   */
  public void putVertex(T vertex) {
    vertex.put(vertices);
  }

  /**
   * Writes vertex at the specified vertex position. The position remains unchanged.
   */
  public void putVertex(int position, T vertex) {
    int prevPosition = vertexPosition();
    vertices.position(position * vertexType.stride());
    putVertex(vertex);
    vertices.position(prevPosition);
  }

  /**
   * Reads index at the current index position, then increments the position.
   */
  public int getIndex() {
    switch (indexType) {
    case UBYTE:
      return indices.get();
    case USHORT:
      return indices.getShort();
    case UINT:
      return indices.getInt();
    case NONE:
    default:
      return 0;
    }
  }

  /**
   * Writes index at the current index position, then increments the position.
   */
  public void putIndex(int value) {
    switch (indexType) {
    case UBYTE:
      indices.put((byte) value);
      break;
    case USHORT:
      indices.putShort((short) value);
      break;
    case UINT:
      indices.putInt(value);
      break;
    case NONE:
    default:
    }
  }

  /**
   * Writes index at the specified index position. The position remains unchanged.
   */
  public void putIndex(int position, int value) {
    int prevPosition = indexPosition();
    indices.position(position * indexType.size);
    putIndex(value);
    vertices.position(prevPosition);
  }

  /**
   * Writes indices starting at the current index position, then increments position by
   * indices.length.
   */
  public void putIndices(int[] indices) {
    for (int i : indices)
      putIndex(i);
  }

  /**
   * Applies a transformation matrix to all vertices.
   */
  public void transform(Mat4f matrix) {
    for (int i = 0; i < numVertices(); i++) {
      T vertex = getVertex(i);
      vertex.transform(matrix);
      putVertex(i, vertex);
    }
  }

  /**
   * Render the geometry using immediate mode.
   */
  public void drawImmediate(GL2 gl) {
    rewind();
    gl.glBegin(primitive.glConstant);
    if (indices == null) {
      for (int i = 0; i < numVertices(); i++)
        getVertex().drawImmediate(gl);
    } else {
      for (int i = 0; i < numIndices(); i++)
        getVertex(getIndex()).drawImmediate(gl);
    }
    gl.glEnd();
    rewind();
  }

  /**
   * Render the geometry using vertex arrays.
   */
  public void drawArrays(GL2 gl) {
    rewind();
    vertexType.startArrays(gl, vertices);
    if (indices == null)
      gl.glDrawArrays(primitive.glConstant, 0, numVertices());
    else
      gl.glDrawElements(primitive.glConstant, numIndices(), indexType.glConstant, indices);
    vertexType.endArrays(gl);
    rewind();
  }

  /**
   * Describes a valid OpenGL primitive type (GL_QUADS are excluded).
   */
  public enum Primitive {
    POINTS(GL.GL_POINTS),
    LINES(GL.GL_LINES),
    LINE_LOOP(GL.GL_LINE_LOOP),
    LINE_STRIP(GL.GL_LINE_STRIP),
    TRIANGLES(GL.GL_TRIANGLES),
    TRIANGLE_FAN(GL.GL_TRIANGLE_FAN),
    TRIANGLE_STRIP(GL.GL_TRIANGLE_STRIP);

    public final int glConstant;

    private Primitive(int value) {
      this.glConstant = value;
    }
  }

  /**
   * Index type for the geometry.
   */
  public enum IndexType {
    NONE(0, 0),
    UBYTE(1, GL.GL_UNSIGNED_BYTE),
    USHORT(2, GL.GL_UNSIGNED_SHORT),
    UINT(4, GL.GL_UNSIGNED_INT);

    public final int size;
    public final int glConstant;

    IndexType(int size, int constant) {
      this.size = size;
      this.glConstant = constant;
    }

    public static IndexType fromVertexCount(int vertexCount) {
      if (vertexCount == 0)
        return NONE;
      if (vertexCount <= 256)
        return UBYTE;
      if (vertexCount <= 65536)
        return USHORT;
      return UINT;
    }
  }
}
