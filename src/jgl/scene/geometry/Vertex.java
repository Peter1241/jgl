package jgl.scene.geometry;

import java.nio.Buffer;
import java.nio.ByteBuffer;

import javax.media.opengl.GL2;

import jgl.math.vector.Mat4f;

/**
 * Stores the contents of one vertex from geometry.
 * 
 * @author justin
 */
public interface Vertex {
  
  /**
   * Object capable of instantiating an empty vertex.
   */
  interface Constructor<T extends Vertex> {
    T construct();
  }

  /**
   * Total size in bytes of the vertex.
   */
  int stride();

  /**
   * Adds this vertex to a buffer.
   */
  void put(ByteBuffer buffer);

  /**
   * Gets this vertex from a buffer.
   */
  void get(ByteBuffer buffer);
  
  /**
   * Adds the contents of this vertex for immediate mode rendering.
   */
  void drawImmediate(GL2 gl);
  
  /**
   * Setup pointers for vertex array rendering using a host vertex buffer.
   */
  void startArrays(GL2 gl);
  
  /**
   * Setup pointers for vertex array rendering using a local vertex buffer.
   */
  void startArrays(GL2 gl, Buffer vertices);

  /**
   * Clear pointers for vertex array rendering.
   */
  void endArrays(GL2 gl);

  /**
   * Applies a transformation matrix to this vertex.
   */
  void transform(Mat4f matrix);
}
