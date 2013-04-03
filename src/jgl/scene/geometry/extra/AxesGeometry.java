/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.scene.geometry.extra;

import jgl.scene.geometry.Geometry;
import jgl.scene.geometry.VertexPN;

/**
 * Draws 3D axes.
 * 
 * @author justin
 */
public class AxesGeometry extends Geometry<VertexPN> {

  public AxesGeometry(float length, boolean xAxis, boolean yAxis, boolean zAxis) {
    super(Primitive.TRIANGLES, VertexPN.CONSTRUCTOR);

    ConeGeometry x = ConeGeometry.posX(0.2f, 1.0f, 16, true);
    
    
  }
}
