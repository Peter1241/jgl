/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.scene.mesh;

import java.nio.Buffer;
import java.util.List;

import javax.media.opengl.GL;

import jgl.math.geometry.Box;

public class Model {

  public enum RenderMode {
    VBO,
    DisplayList
  }

  final ModelRenderer   renderer;
  final List<ModelPart> parts;
  final ModelFormat     vertexFormat;
  final Buffer          vertexData;
  final Buffer          indexData;
  public final Box      bounds;

  public Model(List<ModelPart> parts, Buffer vertexData, Buffer indexData, RenderMode renderMode,
      ModelFormat vertexFormat, Box bounds) {
    this.parts = parts;
    this.vertexData = vertexData;
    this.indexData = indexData;
    this.vertexFormat = vertexFormat;
    this.bounds = bounds;

    switch (renderMode) {
    case VBO:
      renderer = new ModelRendererVBO(this);
      break;
    default:
    case DisplayList:
      renderer = new ModelRendererDL(this);
      break;
    }
  }

  public void draw(GL gl) {
    renderer.draw(gl);
  }

  public void delete(GL gl) {
    renderer.delete(gl);
  }
}
