/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.scene.mesh;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import jgl.core.GLBuffer;
import jgl.core.GLBuffer.Target;
import jgl.core.GLBuffer.Usage;

public class ModelRendererVBO implements ModelRenderer {

  private final Model    mesh;
  private final GLBuffer vertexBuffer = new GLBuffer(Target.ARRAY, Usage.STATIC_DRAW);
  private final GLBuffer indexBuffer  = new GLBuffer(Target.ELEMENT_ARRAY, Usage.STATIC_DRAW);
  private boolean        initialized  = false;

  public ModelRendererVBO(Model mesh) {
    this.mesh = mesh;
  }

  @Override
  public void init(GL gl) {
    vertexBuffer.bind(gl);
    vertexBuffer.setData(gl, mesh.vertexData);
    vertexBuffer.unbind(gl);
    indexBuffer.bind(gl);
    indexBuffer.setData(gl, mesh.indexData);
    indexBuffer.unbind(gl);
    initialized = true;
  }

  @Override
  public void draw(GL gl) {
    if (!initialized)
      init(gl);

    vertexBuffer.bind(gl);
    indexBuffer.bind(gl);
    draw(gl.getGL2());
    vertexBuffer.unbind(gl);
    indexBuffer.unbind(gl);
  }

  private void draw(GL2 gl) {
    mesh.vertexFormat.setState(gl);
    for (ModelPart part : mesh.parts) {
      part.material.apply(gl);
      gl.glDrawElements(GL.GL_TRIANGLES, part.numIndices, GL.GL_UNSIGNED_INT, part.offset * 4);
    }
    mesh.vertexFormat.unsetState(gl);
  }

  @Override
  public void delete(GL gl) {
    vertexBuffer.delete(gl);
    indexBuffer.delete(gl);
  }
}
