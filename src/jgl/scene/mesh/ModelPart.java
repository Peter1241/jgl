/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.scene.mesh;

public class ModelPart {

  ModelMaterial material;
  int          offset = 0;
  int          numIndices;

  public ModelPart(ModelMaterial material, int offset, int numIndices) {
    this.material = material;
    this.offset = offset;
    this.numIndices = numIndices;
  }
}
