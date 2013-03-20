/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.core;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.media.opengl.GL2GL3;

/**
 * GLSL shader program.
 * 
 * @author justin
 */
public class Program {

  private static int             bound      = 0;

  private int                    id         = -1;
  private Set<Shader>            attached   = new HashSet<Shader>();
  private Map<String, Attribute> attributes = new HashMap<String, Attribute>();
  private Map<String, Uniform>   uniforms   = new HashMap<String, Uniform>();
  private String                 log;

  public Program() {
  }

  public int id() {
    return id;
  }

  public void bind(GL2GL3 gl) {
    if (id != bound) {
      gl.glUseProgram(id);
      bound = id;
    }
  }

  public void unbind(GL2GL3 gl) {
    if (bound != 0) {
      gl.glUseProgram(0);
      bound = 0;
    }
  }

  public Uniform uniform(String name) {
    return uniforms.get(name);
  }

  public Attribute attribute(String name) {
    return attributes.get(name);
  }

  public String getLog() {
    return log;
  }

  public Collection<Uniform> getUniforms() {
    return uniforms.values();
  }

  public Collection<Attribute> getAttributes() {
    return attributes.values();
  }

  public Collection<Shader> getAttached() {
    return attached;
  }

  public void attach(GL2GL3 gl, Shader shader) {
    if (id == -1)
      id = gl.glCreateProgram();

    gl.glAttachShader(id, shader.id());
    attached.add(shader);
  }

  public void detach(GL2GL3 gl, Shader shader) {
    if (id != -1) {
      gl.glDetachShader(id, shader.id());
      attached.remove(shader);
    }
  }

  public void detachAll(GL2GL3 gl) {
    if (id != -1) {
      for (Shader shader : attached)
        gl.glDetachShader(id, shader.id());
      attached.clear();
    }
  }

  public boolean link(GL2GL3 gl) {
    gl.glLinkProgram(id);

    IntBuffer status = IntBuffer.allocate(1);
    gl.glGetProgramiv(id, GL2GL3.GL_LINK_STATUS, status);

    if (status.get() == 0) {
      updateLog(gl);
      return false;
    }

    IntBuffer numActive = IntBuffer.allocate(1);
    IntBuffer bufSize = IntBuffer.allocate(1);
    IntBuffer length = IntBuffer.allocate(1);
    IntBuffer size = IntBuffer.allocate(1);
    IntBuffer type = IntBuffer.allocate(1);
    ByteBuffer nameBuf;

    // read vertex attributes
    attributes.clear();
    gl.glGetProgramiv(id, GL2GL3.GL_ACTIVE_ATTRIBUTES, numActive);
    gl.glGetProgramiv(id, GL2GL3.GL_ACTIVE_ATTRIBUTE_MAX_LENGTH, bufSize);
    nameBuf = ByteBuffer.allocate(bufSize.get(0));
    for (int i = 0; i < numActive.get(0); i++) {
      gl.glGetActiveAttrib(id, i, bufSize.get(0), length, size, type, nameBuf);
      String name = new String(nameBuf.array(), 0, length.get(0));
      int index = gl.glGetAttribLocation(id, name);
      attributes.put(name, new Attribute(name, size.get(0), type.get(0), index));
    }

    // read uniform variables
    uniforms.clear();
    gl.glGetProgramiv(id, GL2GL3.GL_ACTIVE_UNIFORMS, numActive);
    gl.glGetProgramiv(id, GL2GL3.GL_ACTIVE_UNIFORM_MAX_LENGTH, bufSize);
    nameBuf = ByteBuffer.allocate(bufSize.get(0));
    for (int i = 0; i < numActive.get(0); i++) {
      gl.glGetActiveUniform(id, i, bufSize.get(0), length, size, type, nameBuf);
      String name = new String(nameBuf.array(), 0, length.get(0));
      int loc = gl.glGetUniformLocation(id, name);
      uniforms.put(name, new Uniform(name, size.get(0), type.get(0), loc));
    }

    log = null;
    return true;
  }

  public boolean validate(GL2GL3 gl) {
    gl.glValidateProgram(id);
    IntBuffer status = IntBuffer.allocate(1);
    gl.glGetProgramiv(id, GL2GL3.GL_VALIDATE_STATUS, status);

    if (status.get() == 0) {
      updateLog(gl);
      return false;
    }

    log = null;
    return true;
  }

  private void updateLog(GL2GL3 gl) {
    IntBuffer maxLength = IntBuffer.allocate(1);
    gl.glGetProgramiv(id, GL2GL3.GL_INFO_LOG_LENGTH, maxLength);

    ByteBuffer buf = ByteBuffer.allocate(maxLength.get());
    gl.glGetProgramInfoLog(id, buf.capacity(), maxLength, buf);

    log = new String(buf.array());
  }

  public void delete(GL2GL3 gl, boolean deleteAttached) {
    if (id != -1) {
      if (deleteAttached) {
        for (Shader shader : attached) {
          gl.glDetachShader(id, shader.id());
          shader.delete(gl);
        }
      }
      gl.glDeleteProgram(id);
      id = -1;
    }
  }
}
