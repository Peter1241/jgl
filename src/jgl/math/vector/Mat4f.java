/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

/**
 * A 4x4 matrix with single-precision values.
 * 
 * @author justin
 */
public class Mat4f {

  public final float[] a;

  /** Creates a 4x4 matrix with all 16 elements set to 0. */
  public Mat4f() {
    a = new float[16];
  }

  /** Creates a 4x4 matrix from a 16-element array in column-major order. */
  public Mat4f(float[] a) {
    this.a = a;
  }

  /**
   * Creates a new 4x4 matrix using column vectors (a, b, c, d), (e, f, g, h), (i, j, k, l), and (m,
   * n, o p).
   */
  public Mat4f(float a, float b, float c, float d, float e, float f, float g, float h, float i,
      float j, float k, float l, float m, float n, float o, float p) {

    this.a = new float[16];

    this.a[0] = a;
    this.a[1] = b;
    this.a[2] = c;
    this.a[3] = d;

    this.a[4] = e;
    this.a[5] = f;
    this.a[6] = g;
    this.a[7] = h;

    this.a[8] = i;
    this.a[9] = j;
    this.a[10] = k;
    this.a[11] = l;

    this.a[12] = m;
    this.a[13] = n;
    this.a[14] = o;
    this.a[15] = p;
  }

  /**
   * Creates a new 4x4 matrix using column vectors x, y, z, and w.
   */
  public Mat4f(ConstVec4f x, ConstVec4f y, ConstVec4f z, ConstVec4f w) {
    this.a = new float[16];

    this.a[0] = x.x();
    this.a[1] = x.y();
    this.a[2] = x.z();
    this.a[3] = x.w();

    this.a[4] = y.x();
    this.a[5] = y.y();
    this.a[6] = y.z();
    this.a[7] = y.w();

    this.a[8] = z.x();
    this.a[9] = z.y();
    this.a[10] = z.z();
    this.a[11] = z.w();

    this.a[12] = w.x();
    this.a[13] = w.y();
    this.a[14] = w.z();
    this.a[15] = w.w();
  }

  /**
   * The column vector i in [0,3]. Returns a new vector.
   */
  public Vec4f col(int i) {
    int j = i * 4;
    return new Vec4f(a[j], a[j + 1], a[j + 2], a[j + 3]);
  }

  /**
   * The row vector i in [0,3]. Returns a new vector.
   */
  public Vec4f row(int i) {
    return new Vec4f(a[i], a[i + 4], a[i + 8], a[i + 12]);
  }

  /**
   * The value at [row][col].
   */
  public float value(int row, int col) {
    return a[col * 4 + row];
  }

  /**
   * Multiplication with (v.x, v.y, v.z, v.w). Returns a new vector.
   */
  public Vec4f times(ConstVec4f v) {
    return times(v.x(), v.y(), v.z(), v.w());
  }
  
  /**
   * Multiplication with (v.x, v.y, v.z, 0). Returns a new vector.
   */
  public Vec3f times(ConstVec3f v) {
    float vx = v.x() * a[0] + v.y() * a[4] + v.z() * a[8];
    float vy = v.x() * a[1] + v.y() * a[5] + v.z() * a[9];
    float vz = v.x() * a[2] + v.y() * a[6] + v.z() * a[10];
    return new Vec3f(vx, vy, vz);
  }

  /**
   * Multiplication with (x, y, z, w). Returns a new vector.
   */
  public Vec4f times(float x, float y, float z, float w) {
    float vx = x * a[0] + y * a[4] + z * a[8] + w * a[12];
    float vy = x * a[1] + y * a[5] + z * a[9] + w * a[13];
    float vz = x * a[2] + y * a[6] + z * a[10] + w * a[14];
    float vw = x * a[3] + y * a[7] + z * a[11] + w * a[15];
    return new Vec4f(vx, vy, vz, vw);
  }

  /**
   * Matrix multiplication: (this) * (m), in place. Returns this matrix.
   */
  public Mat4f multiply(Mat4f m) {
    System.arraycopy(this.times(m).a, 0, this.a, 0, 16);
    return this;
  }

  /**
   * Matrix multiplication: (this) * (m). Returns a new matrix.
   */
  public Mat4f times(Mat4f m) {
    Mat4f mat = new Mat4f();
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        float sum = 0;
        for (int k = 0; k < 4; k++)
          sum += a[i + 4 * k] * m.a[k + 4 * j];
        mat.a[i + 4 * j] = sum;
      }
    }
    return mat;
  }

  /**
   * Transposes this matrix, in place. Returns this matrix.
   */
  public Mat4f transpose() {
    System.arraycopy(transposed().a, 0, this.a, 0, 16);
    return this;
  }

  /**
   * Returns the transpose of this matrix. Returns a new matrix.
   */
  public Mat4f transposed() {
    return new Mat4f(a[0], a[4], a[8], a[12], a[1], a[5], a[9], a[13], a[2], a[6], a[10], a[14],
        a[3], a[7], a[11], a[15]);
  }

  /**
   * Computes the inverse of the matrix; returns null if none exists.
   */
  public Mat4f inverse() {
    // adapted from MESA implementation of gluInvertMatrix
    float[] inv = new float[16];
    float det;
    int i;

    inv[0] = a[5] * a[10] * a[15] - a[5] * a[11] * a[14] - a[9] * a[6] * a[15] + a[9] * a[7]
        * a[14] + a[13] * a[6] * a[11] - a[13] * a[7] * a[10];
    inv[4] = -a[4] * a[10] * a[15] + a[4] * a[11] * a[14] + a[8] * a[6] * a[15] - a[8] * a[7]
        * a[14] - a[12] * a[6] * a[11] + a[12] * a[7] * a[10];
    inv[8] = a[4] * a[9] * a[15] - a[4] * a[11] * a[13] - a[8] * a[5] * a[15] + a[8] * a[7] * a[13]
        + a[12] * a[5] * a[11] - a[12] * a[7] * a[9];
    inv[12] = -a[4] * a[9] * a[14] + a[4] * a[10] * a[13] + a[8] * a[5] * a[14] - a[8] * a[6]
        * a[13] - a[12] * a[5] * a[10] + a[12] * a[6] * a[9];
    inv[1] = -a[1] * a[10] * a[15] + a[1] * a[11] * a[14] + a[9] * a[2] * a[15] - a[9] * a[3]
        * a[14] - a[13] * a[2] * a[11] + a[13] * a[3] * a[10];
    inv[5] = a[0] * a[10] * a[15] - a[0] * a[11] * a[14] - a[8] * a[2] * a[15] + a[8] * a[3]
        * a[14] + a[12] * a[2] * a[11] - a[12] * a[3] * a[10];
    inv[9] = -a[0] * a[9] * a[15] + a[0] * a[11] * a[13] + a[8] * a[1] * a[15] - a[8] * a[3]
        * a[13] - a[12] * a[1] * a[11] + a[12] * a[3] * a[9];
    inv[13] = a[0] * a[9] * a[14] - a[0] * a[10] * a[13] - a[8] * a[1] * a[14] + a[8] * a[2]
        * a[13] + a[12] * a[1] * a[10] - a[12] * a[2] * a[9];
    inv[2] = a[1] * a[6] * a[15] - a[1] * a[7] * a[14] - a[5] * a[2] * a[15] + a[5] * a[3] * a[14]
        + a[13] * a[2] * a[7] - a[13] * a[3] * a[6];
    inv[6] = -a[0] * a[6] * a[15] + a[0] * a[7] * a[14] + a[4] * a[2] * a[15] - a[4] * a[3] * a[14]
        - a[12] * a[2] * a[7] + a[12] * a[3] * a[6];
    inv[10] = a[0] * a[5] * a[15] - a[0] * a[7] * a[13] - a[4] * a[1] * a[15] + a[4] * a[3] * a[13]
        + a[12] * a[1] * a[7] - a[12] * a[3] * a[5];
    inv[14] = -a[0] * a[5] * a[14] + a[0] * a[6] * a[13] + a[4] * a[1] * a[14] - a[4] * a[2]
        * a[13] - a[12] * a[1] * a[6] + a[12] * a[2] * a[5];
    inv[3] = -a[1] * a[6] * a[11] + a[1] * a[7] * a[10] + a[5] * a[2] * a[11] - a[5] * a[3] * a[10]
        - a[9] * a[2] * a[7] + a[9] * a[3] * a[6];
    inv[7] = a[0] * a[6] * a[11] - a[0] * a[7] * a[10] - a[4] * a[2] * a[11] + a[4] * a[3] * a[10]
        + a[8] * a[2] * a[7] - a[8] * a[3] * a[6];
    inv[11] = -a[0] * a[5] * a[11] + a[0] * a[7] * a[9] + a[4] * a[1] * a[11] - a[4] * a[3] * a[9]
        - a[8] * a[1] * a[7] + a[8] * a[3] * a[5];
    inv[15] = a[0] * a[5] * a[10] - a[0] * a[6] * a[9] - a[4] * a[1] * a[10] + a[4] * a[2] * a[9]
        + a[8] * a[1] * a[6] - a[8] * a[2] * a[5];

    det = a[0] * inv[0] + a[1] * inv[4] + a[2] * inv[8] + a[3] * inv[12];

    if (det == 0)
      return null;

    det = 1.0f / det;

    for (i = 0; i < 16; i++)
      inv[i] *= det;

    return new Mat4f(inv);
  }

  @Override
  public String toString() {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < 4; i++)
      b.append(String.format("| %8.3f %8.3f %8.3f %8.3f |\n", a[i], a[i + 4], a[i + 8], a[i + 12]));
    return b.toString();
  }
}