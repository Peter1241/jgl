/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.scene.cameras;

import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import jgl.math.vector.Mat4f;
import jgl.math.vector.ConstVec3f;
import jgl.math.vector.Vec3f;

/**
 * Camera model.
 * 
 * @author justin
 */
public class Camera {

  protected Vec3f eye;
  protected Vec3f forward;
  protected Vec3f right;
  protected Vec3f up;
  protected Mat4f view;
  protected Mat4f projection;

  public ConstVec3f getEye() {
    return eye;
  }

  public ConstVec3f getForward() {
    return forward;
  }

  public ConstVec3f getBackward() {
    return forward.times(-1);
  }

  public ConstVec3f getRight() {
    return right;
  }

  public ConstVec3f getLeft() {
    return right.times(-1);
  }

  public ConstVec3f getUp() {
    return up;
  }

  public ConstVec3f getDown() {
    return up.times(-1);
  }

  public Mat4f getProjection() {
    return projection;
  }

  public Mat4f getView() {
    return view;
  }

  public void setProjection(Mat4f projection) {
    this.projection = projection;
  }

  public void setView(Mat4f view) {
    this.view = view;
  }

  public void apply(GL2 gl) {
    gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
    gl.glLoadMatrixf(projection.a, 0);
    gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
    gl.glLoadMatrixf(view.a, 0);
  }
}
