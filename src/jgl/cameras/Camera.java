/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.cameras;

import javax.media.opengl.GL2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import jgl.math.vector.ConstVec3f;
import jgl.math.vector.Mat4f;
import jgl.math.vector.Transform;
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
  protected Mat4f viewInverse;
  protected Mat4f projectionInverse;
  
  public Camera() {
    setView(Transform.identity());
    setProjection(Transform.identity());
  }

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
  
  public Mat4f getProjectionInverse() {
    return projectionInverse;
  }

  public Mat4f getView() {
    return view;
  }
  
  public Mat4f getViewInverse() {
    return viewInverse;
  }

  public void setProjection(Mat4f projection) {
    this.projection = projection;
    this.projectionInverse = projection.inverse();
  }

  public void setView(Mat4f view) {
    this.view = view;
    this.viewInverse = view.inverse();
    updateVectors();
  }

  public void apply(GL2 gl) {
    gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
    gl.glLoadMatrixf(projection.a, 0);
    gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
    gl.glLoadMatrixf(view.a, 0);
  }

  public void translateForward(float units) {
    translate(forward.times(units));
  }

  public void translateBackward(float units) {
    translate(forward.times(-units));
  }

  public void translateRight(float units) {
    translate(right.times(units));
  }

  public void translateLeft(float units) {
    translate(right.times(-units));
  }

  public void translateUp(float units) {
    translate(up.times(units));
  }

  public void translateDown(float units) {
    translate(up.times(-units));
  }

  public void translate(ConstVec3f t) {
    translate(t.x(), t.y(), t.z());
  }

  public void translate(float x, float y, float z) {
    view = view.times(Transform.translation(x, y, z));
    viewInverse = Transform.translation(-x, -y, -z).times(viewInverse);
    updateVectors();
  }
  
  public void rotateX(double radians) {
    view = view.times(Transform.rotationX(radians));
    viewInverse = Transform.rotationX(-radians).times(viewInverse);
    updateVectors();
  }
  
  public void rotateY(double radians) {
    view = view.times(Transform.rotationY(radians));
    viewInverse = Transform.rotationY(-radians).times(viewInverse);
    updateVectors();
  }
  
  public void rotateZ(double radians) {
    view = view.times(Transform.rotationX(radians));
    viewInverse = Transform.rotationX(-radians).times(viewInverse);
    updateVectors();
  }
  
  public void rotate(ConstVec3f axis, double radians) {
    view = view.times(Transform.rotation(axis, radians));
    viewInverse = Transform.rotation(axis, -radians).times(viewInverse);
    updateVectors();
  }
  
  private void updateVectors() {
    right = viewInverse.col(0).xyz();
    up = viewInverse.col(1).xyz();
    forward = viewInverse.col(2).xyz().multiply(-1);
    eye = viewInverse.col(3).xyz();
  }
}
