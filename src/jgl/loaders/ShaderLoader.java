package jgl.loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.media.opengl.GL2GL3;

import jgl.core.Shader;
import jgl.core.Shader.Type;

/**
 * Utility functions for loading shaders and shader programs.
 * 
 * @author justin
 */
public class ShaderLoader {

  /**
   * Compiles shader source code from an input stream. Creates a new shader object if necessary. If
   * compiling fails, false is returned and the error log is set.
   */
  public static Shader load(GL2GL3 gl, InputStream stream, Type type) {
    try {
      StringBuilder source = new StringBuilder();
      BufferedReader br = new BufferedReader(new InputStreamReader(stream));
      String line;
      while ((line = br.readLine()) != null) {
        source.append(line);
        source.append("\n");
      }
      stream.close();
      
      Shader shader = new Shader();
      if (!shader.compile(gl, source.toString(), type)) {
        System.err.println(shader.getLog());
        return null;
      }
      
      return shader;
    } catch (IOException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

  /**
   * Compiles shader source code from a file. Creates a new shader object if necessary. If compiling
   * fails, false is returned and the error log is set.
   */
  public static Shader load(GL2GL3 gl, File file, Type type) {
    try {
      return load(gl, new FileInputStream(file), type);
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }
  
  /**
   * Loads a shader using an absolute resource path.
   */
  public static Shader load(GL2GL3 gl, String resource, Type type) {
    return load(gl, Shader.class.getResourceAsStream(resource), type);
  }
}
