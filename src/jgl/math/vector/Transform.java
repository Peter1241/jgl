/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

/**
 * Transformation matrix functions.
 * 
 * @author justin
 */
public final class Transform {

  /**
   * An identity matrix
   */
  public static Mat4f identity() {
    float[] a = new float[16];
    a[0] = a[5] = a[10] = a[15] = 1;
    return new Mat4f(a);
  }

  /**
   * A translation matrix
   */
  public static Mat4f translation(float x, float y, float z) {
    return new Mat4f(new float[] { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, x, y, z, 1 });
  }

  /**
   * A translation matrix
   */
  public static Mat4f translation(ReadableVec3f v) {
    return translation(v.x(), v.y(), v.z());
  }

  /**
   * A scale matrix.
   */
  public static Mat4f scale(float x, float y, float z) {
    return new Mat4f(new float[] { x, 0, 0, 0, 0, y, 0, 0, 0, 0, z, 0, 0, 0, 0, 1 });
  }

  /**
   * A scale matrix
   */
  public static Mat4f scale(ReadableVec3f v) {
    return scale(v.x(), v.y(), v.z());
  }

  /**
   * A rotation matrix around the x-axis
   */
  public Mat4f rotationX(double radians) {
    float c = (float) Math.cos(radians);
    float s = (float) Math.sin(radians);
    return new Mat4f(new float[] { 1, 0, 0, 0, 0, c, s, 0, 0, -s, c, 0, 0, 0, 0, 1 });
  }

  /**
   * A rotation matrix around the y-axis
   */
  public static Mat4f rotationY(double radians) {
    float c = (float) Math.cos(radians);
    float s = (float) Math.sin(radians);
    return new Mat4f(new float[] { c, 0, -s, 0, 0, 1, 0, 0, s, 0, c, 0, 0, 0, 0, 1 });
  }

  /**
   * A rotation matrix around the z-axis
   */
  public static Mat4f rotationZ(double radians) {
    float c = (float) Math.cos(radians);
    float s = (float) Math.sin(radians);
    return new Mat4f(new float[] { c, s, 0, 0, -s, c, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 });
  }

  /**
   * A rotation matrix around the u-axis = {x,y,z} with length 1.
   */
  public static Mat4f rotation(float x, float y, float z, double radians) {
    // http://en.wikipedia.org/wiki/Rotation_matrix#Rotation_matrix_from_axis_and_angle

    float c = (float) Math.cos(radians);
    float s = (float) Math.sin(radians);
    float ic = 1 - c;

    float[] a = new float[16];
    // column 1
    a[0] = x * x * ic + c;
    a[1] = x * y * ic + z * s;
    a[2] = x * z * ic - y * s;
    a[3] = 0;
    // column 2
    a[4] = y * x * ic - z * s;
    a[5] = y * y * ic + c;
    a[6] = y * z * ic + x * s;
    a[7] = 0;
    // column 3
    a[8] = z * x * ic + y * s;
    a[9] = z * y * ic - x * s;
    a[10] = z * z * ic + c;
    a[11] = 0;
    // column 4
    a[12] = 0;
    a[13] = 0;
    a[14] = 0;
    a[15] = 1;

    return new Mat4f(a);
  }

  /**
   * A rotation matrix around the u-axis with length 1.
   */
  public static Mat4f rotation(ReadableVec3f u, float radians) {
    return rotation(u.x(), u.y(), u.z(), radians);
  }

  /**
   * A rotation matrix using yaw, pitch, and roll (in radians).
   */
  public static Mat4f yawPitchRoll(float yaw, float pitch, float roll) {
    float a = (float) Math.cos(yaw);
    float b = (float) Math.sin(yaw);
    float c = (float) Math.cos(pitch);
    float d = (float) Math.sin(pitch);
    float f = (float) Math.cos(roll);
    float g = (float) Math.sin(roll);
    return new Mat4f(new float[] { f * a + d * g * b, c * g, d * g * a - f * b, 0,
        -g * a + d * f * b, c * f, d * f * a + g * b, 0, c * b, -d, c * a, 0, 0, 0, 0, 1 });
  }

  /**
   * A perspective projection matrix (same as glFrustum).
   */
  public static Mat4f perspective(float left, float right, float bottom, float top, float near,
      float far) {
    float rightMinusLeft = right - left;
    float topMinusBottom = top - bottom;
    float farMinusNear = far - near;
    float twoNear = 2 * near;
    float A = (right + left) / rightMinusLeft;
    float B = (top + bottom) / topMinusBottom;
    float C = -(far + near) / farMinusNear;
    float D = -twoNear * far / farMinusNear;
    return new Mat4f(new float[] { twoNear / rightMinusLeft, 0, 0, 0, 0, twoNear / topMinusBottom,
        0, 0, A, B, C, -1, 0, 0, D, 0, });
  }

  /**
   * A perspective projection matrix (same as gluPerspective).
   */
  public static Mat4f perspective(float fovY, float aspect, float near, float far) {
    float f = 1.0f / (float) Math.tan(Math.toRadians(fovY) / 2);
    float nearMinusFar = near - far;
    return new Mat4f(new float[] { f / aspect, 0, 0, 0, 0, f, 0, 0, 0, 0,
        (far + near) / nearMinusFar, -1, 0, 0, (2 * far * near) / nearMinusFar, 0 });
  }

  /**
   * An orthographic projection matrix (same as glOrtho).
   */
  public static Mat4f orthographic(float left, float right, float bottom, float top, float near,
      float far) {
    float rightMinusLeft = right - left;
    float topMinusBottom = top - bottom;
    float farMinusNear = far - near;
    float tx = -(right + left) / rightMinusLeft;
    float ty = -(top + bottom) / topMinusBottom;
    float tz = -(far + near) / farMinusNear;
    return new Mat4f(new float[] { 2.0f / rightMinusLeft, 0, 0, 0, 0, 2.0f / topMinusBottom, 0, 0,
        0, 0, -2.0f / farMinusNear, 0, tx, ty, tz, 1.0f });
  }

  /**
   * An orthographic projection matrix (same as gulOrtho2D).
   */
  public static Mat4f orthographic2D(float left, float right, float bottom, float top) {
    return orthographic(left, right, bottom, top, -1, 1);
  }

  /**
   * A view matrix defined by an eye and target position (same as gluLookAt)
   */
  public static Mat4f lookAt(float eyeX, float eyeY, float eyeZ, float centerX, float centerY,
      float centerZ, float upX, float upY, float upZ) {
    Vec3f f = new Vec3f(centerX - eyeX, centerY - eyeY, centerZ - eyeZ).normalize();
    Vec3f up = new Vec3f(upX, upY, upZ).normalize();
    Vec3f s = f.cross(up).normalize();
    Vec3f u = s.cross(f).normalize();
    Mat4f m1 = new Mat4f(new float[] { s.x, u.x, -f.x, 0, s.y, u.y, -f.y, 0, s.z, u.z, -f.z, 0, 0,
        0, 0, 1 });
    Mat4f m2 = translation(-eyeX, -eyeY, -eyeZ);
    return m1.times(m2);
  }
}
