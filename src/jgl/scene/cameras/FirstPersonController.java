/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.scene.cameras;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import jgl.math.Maths;
import jgl.math.vector.ConstVec3f;
import jgl.math.vector.Transform;
import jgl.math.vector.Vec3f;

public class FirstPersonController implements MouseListener, MouseMotionListener,
    MouseWheelListener, KeyListener {

  /**
   * Having the camera directly on the world up/down axes creates a special case for calculating the
   * local vectors. Instead of introducing more code, the pitch is limited to a value just under
   * PI/2 (or just above -PI/2); the offset is tiny so that visually there is no difference.
   */
  private static final float PITCH_EPSILON = 0.000001f;
  private static final float MAX_PITCH     = Maths.PI / 2 - PITCH_EPSILON;
  private static final float MIN_PITCH     = -MAX_PITCH;

  Camera                     camera;
  Vec3f                      eye;
  float                      yaw;
  float                      pitch;
  final boolean              yUp;

  MotionParameter            motionLR = new MotionParameter();
  MotionParameter            motionUD = new MotionParameter();

  public FirstPersonController(ConstVec3f eye, float yaw, float pitch, boolean yUp) {
    this.eye = eye.copy();
    this.yUp = yUp;
    setRotation(yaw, pitch);
  }

  public void setCamera(Camera camera) {
    this.camera = camera;
  }

  public float getYaw() {
    return yaw;
  }

  public float getPitch() {
    return pitch;
  }

  public void moveForward(float units) {
    if (camera != null)
      move(camera.getForward().times(units));
  }

  public void moveBackward(float units) {
    if (camera != null)
      move(camera.getForward().times(-units));
  }

  public void moveRight(float units) {
    if (camera != null)
      move(camera.getRight().times(units));
  }

  public void moveLeft(float units) {
    if (camera != null)
      move(camera.getRight().times(-units));
  }

  public void moveUp(float units) {
    if (camera != null)
      move(camera.getUp().times(units));
  }

  public void moveDown(float units) {
    if (camera != null)
      move(camera.getUp().times(-units));
  }

  public void move(ConstVec3f t) {
    if (camera != null)
      move(t.x(), t.y(), t.z());
  }

  public void move(float x, float y, float z) {
    if (camera == null)
      return;

    eye.x += x;
    eye.y += y;
    eye.z += z;

    Vec3f ctr = eye.plus(camera.getForward());
    ConstVec3f up = camera.getUp();
    camera.setView(Transform.lookAt(eye.x, eye.y, eye.z, ctr.x, ctr.y, ctr.z, up.x(), up.y(),
        up.z()));
  }

  public void setYaw(float yaw) {
    setRotation(yaw, pitch);
  }

  public void setPitch(float pitch) {
    setRotation(yaw, pitch);
  }

  public void addRotation(float yaw, float pitch) {
    setRotation(this.yaw + yaw, this.pitch + pitch);
  }

  public void setRotation(float yaw, float pitch) {
    if (camera == null)
      return;

    this.yaw = yaw;
    this.pitch = Maths.clamp(pitch, MIN_PITCH, MAX_PITCH);

    Vec3f forward = Maths.sphericalToCartesian(1, this.pitch, this.yaw, yUp);
    Vec3f right = forward.cross(yUp ? Vec3f.axisY() : Vec3f.axisZ()).normalize();
    Vec3f up = right.cross(forward).normalize();
    Vec3f ctr = eye.plus(forward);

    camera.setView(Transform.lookAt(eye.x, eye.y, eye.z, ctr.x, ctr.y, ctr.z, up.x, up.y, up.z));
  }

  public void update() {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
  }

  @Override
  public void mouseDragged(MouseEvent e) {
  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }
}
