/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.scene.mesh;
import javax.media.opengl.GL;


public interface ModelRenderer {

  void init(GL gl);
  
  void draw(GL gl);
  
  void delete(GL gl);
}
