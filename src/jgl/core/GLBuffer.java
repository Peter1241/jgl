/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.core;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2GL3;

/**
 * Reference to an OpenGL buffer object.
 * 
 * @author justin
 */
public class GLBuffer {

  public enum Target {
    ARRAY(GL2GL3.GL_ARRAY_BUFFER), 
    ELEMENT_ARRAY(GL2GL3.GL_ELEMENT_ARRAY_BUFFER), 
    PIXEL_PACK(GL2GL3.GL_PIXEL_PACK_BUFFER), 
    PIXEL_UNPACK(GL2GL3.GL_PIXEL_UNPACK_BUFFER);

    public final int glConstant;

    private Target(int glConstant) {
      this.glConstant = glConstant;
    }
  };

  public enum Usage {
    STREAM_DRAW(GL2GL3.GL_STREAM_DRAW), 
    STREAM_READ(GL2GL3.GL_STREAM_READ), 
    STREAM_COPY(GL2GL3.GL_STREAM_COPY), 
    STATIC_DRAW(GL2GL3.GL_STATIC_DRAW), 
    STATIC_READ(GL2GL3.GL_STATIC_READ), 
    STATIC_COPY(GL2GL3.GL_STATIC_COPY), 
    DYNAMIC_DRAW(GL2GL3.GL_DYNAMIC_DRAW), 
    DYNAMIC_READ(GL2GL3.GL_DYNAMIC_READ), 
    DYNAMIC_COPY(GL2GL3.GL_DYNAMIC_COPY);

    public final int glConstant;

    private Usage(int glConstant) {
      this.glConstant = glConstant;
    }
  };

  public enum Access {
    READ_ONLY(GL2GL3.GL_READ_ONLY), WRITE_ONLY(GL2GL3.GL_WRITE_ONLY), READ_WRITE(
        GL2GL3.GL_READ_WRITE);

    public final int glConstant;

    private Access(int glConstant) {
      this.glConstant = glConstant;
    }
  }

  private Target target;
  private Usage usage;
  private int id = -1;

  public GLBuffer(Target target, Usage usage) {
    this.target = target;
    this.usage = usage;
  }

  public int id() {
    return id;
  }

  public void setTarget(Target target) {
    this.target = target;
  }

  public void setUsage(Usage usage) {
    this.usage = usage;
  }

  public void delete(GL gl) {
    if (id != -1) {
      gl.glDeleteBuffers(1, new int[] { id }, 0);
      id = -1;
    }
  }

  public void bind(GL gl) {
    if (id == -1) {
      int[] temp = new int[1];
      gl.glGenBuffers(1, temp, 0);
      id = temp[0];
    }
    gl.glBindBuffer(target.glConstant, id);
  }

  public void unbind(GL gl) {
    gl.glBindBuffer(target.glConstant, 0);
  }

  /**
   * Assigns data to the OpenGL buffer.
   */
  public void setData(GL gl, Buffer data) {
    gl.glBufferData(target.glConstant, sizeOf(data), data, usage.glConstant);
  }

  /**
   * Assigns the subset of the buffer's data starting at offset (in bytes).
   */
  public void setSubData(GL gl, long offset, Buffer data) {
    gl.glBufferSubData(target.glConstant, offset, sizeOf(data), data);
  }

  /**
   * Map a buffer object's data (device -> host).
   */
  public ByteBuffer map(GL gl, Access access) {
    return gl.glMapBuffer(target.glConstant, access.glConstant);
  }

  /**
   * Map a section of a buffer object's data (device -> host).
   */
  public ByteBuffer mapRange(GL2GL3 gl, long offset, long length, Access access) {
    return gl.glMapBufferRange(target.glConstant, offset, length,
        access.glConstant);
  }

  /** Unmaps the bound buffer object's data store. */
  public void unmap(GL gl) {
    gl.glUnmapBuffer(target.glConstant);
  }

  private static long sizeOf(Buffer buffer) {
    if (buffer instanceof FloatBuffer)
      return buffer.capacity() * Float.SIZE / 8;
    if (buffer instanceof DoubleBuffer)
      return buffer.capacity() * Double.SIZE / 8;
    if (buffer instanceof ByteBuffer)
      return buffer.capacity() * Byte.SIZE / 8;
    if (buffer instanceof IntBuffer)
      return buffer.capacity() * Integer.SIZE / 8;
    if (buffer instanceof ShortBuffer)
      return buffer.capacity() * Short.SIZE / 8;
    if (buffer instanceof CharBuffer)
      return buffer.capacity() * Character.SIZE / 8;
    if (buffer instanceof LongBuffer)
      return buffer.capacity() * Long.SIZE / 8;
    return 0;
  }
}