/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.IntBuffer;

import javax.media.opengl.GL2;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GL3;
import javax.media.opengl.GL4;

/**
 * Creates and compiles GLSL shaders.
 * 
 * @author justin
 */
public class Shader {

  public enum Type {
    VERTEX(GL2.GL_VERTEX_SHADER),
    FRAGMENT(GL2.GL_FRAGMENT_SHADER),
    GEOMETRY(GL3.GL_GEOMETRY_SHADER),
    TESS_CONTROL(GL4.GL_TESS_CONTROL_SHADER),
    TESS_EVALUATION(GL4.GL_TESS_EVALUATION_SHADER);
    public final int value;

    private Type(int value) {
      this.value = value;
    }
  };

  private int    id = -1;
  private String source;
  private String log;
  private Type   type;

  /**
   * Creates a new shader.
   */
  public Shader() {
  }

  /**
   * Shader name in the OpenGL context.
   */
  public int id() {
    return id;
  }

  /**
   * Shader type.
   */
  public Type getType() {
    return type;
  }

  /**
   * Source code of the shader.
   */
  public String getSource() {
    return source;
  }

  /**
   * Stores error log from failed compile.
   */
  public String getLog() {
    return log;
  }

  /**
   * Deletes the shader from the OpenGL context.
   */
  public void delete(GL2GL3 gl) {
    if (id != -1) {
      gl.glDeleteShader(id);
      id = -1;
    }
  }

  /**
   * Compiles shader source code. Creates a new shader object if necessary. If compiling fails,
   * false is returned and the error log is set.
   */
  public boolean compile(GL2GL3 gl, String source, Type type) {
    // create new shader name if necessary
    if (id == -1)
      id = gl.glCreateShader(type.value);
    this.type = type;
    this.source = source;

    // compile source
    gl.glShaderSource(id, 1, new String[] { source }, null, 0);
    gl.glCompileShader(id);

    // check for successful compile
    IntBuffer status = IntBuffer.allocate(1);
    gl.glGetShaderiv(id, GL2GL3.GL_COMPILE_STATUS, status);

    // if error is detected, store it in the log
    if (status.get() == 0) {
      int[] maxLength = new int[1];
      gl.glGetShaderiv(id, GL2.GL_INFO_LOG_LENGTH, maxLength, 0);
      byte[] buf = new byte[maxLength[0]];
      gl.glGetShaderInfoLog(id, maxLength[0], maxLength, 0, buf, 0);
      log = new String(buf);
      delete(gl);
      return false;
    }

    log = null;
    return true;
  }

  /**
   * Creates a new shader and compiles source from a file. If the file is not found or an error
   * occurs while compiling, a message is stored in the returned shader's log.
   */
  public static Shader load(GL2GL3 gl, File file, Type type) {
    Shader shader;
    try {
      FileInputStream fileStream = new FileInputStream(file);
      shader = load(gl, fileStream, type);
    } catch (FileNotFoundException e) {
      shader = new Shader();
      shader.log = "ERROR loading " + file.getName() + ": " + e.getMessage();
    }
    return shader;
  }

  /**
   * Creates a new shader and compile source from a classpath resource. If the resource does not
   * exist or an error occurs while compiling, a message is stored in the returned shader's log.
   */
  public static Shader load(GL2GL3 gl, String resource, Type type) {
    return load(gl, Shader.class.getResourceAsStream(resource), type);
  }

  /**
   * Creates a new shader and compile source from an input stream. If an error occurs a message is
   * stored in the returned shader's log.
   */
  public static Shader load(GL2GL3 gl, InputStream stream, Type type) {
    Shader shader = new Shader();
    try {
      StringBuilder source = new StringBuilder();
      InputStreamReader in = new InputStreamReader(stream);
      BufferedReader br = new BufferedReader(in);
      String line;
      while ((line = br.readLine()) != null) {
        source.append(line);
        source.append("\n");
      }
      in.close();
      shader.compile(gl, source.toString(), type);
    } catch (IOException e) {
      shader.log = "ERROR loading shader type " + type + ": " + e.getMessage();
    }
    return shader;
  }
}
