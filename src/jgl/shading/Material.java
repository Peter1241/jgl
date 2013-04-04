/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.shading;

import javax.media.opengl.GL;

/**
 * Describes surface properties used for shading.
 */
public interface Material {

  void enable(GL gl);
  
  void disable(GL gl);
  
  void dispose(GL gl);
}
