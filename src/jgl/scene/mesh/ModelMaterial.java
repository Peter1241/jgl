/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.scene.mesh;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import jgl.scene.materials.Material;

public class ModelMaterial extends Material {

  protected String  name;
  protected boolean containsTransparency = false;

  public ModelMaterial(String name, boolean containsTransparency) {
    this.name = name;
    this.containsTransparency = containsTransparency;
  }

  public String getName() {
    return name;
  }

  public void init(GL2 gl) {
  }

  public void destroy(GL gl) {
  }
}
