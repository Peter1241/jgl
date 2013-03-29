/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

import java.nio.DoubleBuffer;

/**
 * 3D point/vector with double components.
 * 
 * @author justin
 */
public class Vec4d implements ConstVec4d {

  /** The first component. */
  public double x;

  /** The second component. */
  public double y;

  /** The third component. */
  public double z;

  /** The fourth component. */
  public double w;

  /**
   * Creates a vector with all values 0
   */
  public Vec4d() {
  }

  /**
   * Creates a vector using the values (x, y, z, w).
   */
  public Vec4d(double x, double y, double z, double w) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
  }

  /**
   * Creates a vector using the values (s, s, s, s).
   */
  public Vec4d(double s) {
    this(s, s, s, s);
  }

  /**
   * Creates a vector using the values (v.x, v.y, v.z, v.w).
   */
  public Vec4d(ConstVec4d v) {
    this(v.x(), v.y(), v.z(), v.w());
  }

  /**
   * Creates a vector using the values (a[0], a[1], a[2], a[3]).
   */
  public Vec4d(double[] a) {
    this(a[0], a[1], a[2], a[3]);
  }

  /**
   * Creates a vector using the values (a[start], a[start+1], a[start+2], a[start+3]).
   */
  public Vec4d(double[] a, int start) {
    this(a[start], a[start + 1], a[start + 2], a[start + 3]);
  }

  /**
   *  Component-wise addition, in place. Returns this vector.
   */
  public Vec4d add(ConstVec4d v) {
    return add(v.x(), v.y(), v.z(), v.w());
  }

  /**
   *  Component-wise addition, in place. Returns this vector.
   */
  public Vec4d add(double x, double y, double z, double w) {
    this.x += x;
    this.y += y;
    this.z += z;
    this.w += w;
    return this;
  }

  /**
   *  Adds a scalar to each component, in place. Returns this vector.
   */
  public Vec4d add(double s) {
    return add(s, s, s, s);
  }

  /**
   *  Component-wise subtraction, in place. Returns this vector.
   */
  public Vec4d subtract(ConstVec4d v) {
    return subtract(v.x(), v.y(), v.z(), v.w());
  }

  /**
   *  Component-wise subtraction, in place. Returns this vector.
   */
  public Vec4d subtract(double x, double y, double z, double w) {
    this.x -= x;
    this.y -= y;
    this.z -= z;
    this.w -= w;
    return this;
  }

  /**
   *  Subtracts a scalar from each component, in place. Returns this vector.
   */
  public Vec4d subtract(double s) {
    return subtract(s, s, s, s);
  }

  /**
   *  Component-wise multiplication, in place. Returns this vector.
   */
  public Vec4d multiply(ConstVec4d v) {
    return multiply(v.x(), v.y(), v.z(), v.w());
  }

  /**
   *  Component-wise multiplication, in place. Returns this vector.
   */
  public Vec4d multiply(double x, double y, double z, double w) {
    this.x *= x;
    this.y *= y;
    this.z *= z;
    this.w *= w;
    return this;
  }

  /**
   *  Multiplies a scalar with each component, in place. Returns this vector.
   */
  public Vec4d multiply(double s) {
    return multiply(s, s, s, s);
  }

  /**
   *  Component-wise division, in place. Returns this vector.
   */
  public Vec4d divide(ConstVec4d v) {
    return divide(v.x(), v.y(), v.z(), v.w());
  }

  /**
   *  Component-wise division, in place. Returns this vector.
   */
  public Vec4d divide(double x, double y, double z, double w) {
    this.x /= x;
    this.y /= y;
    this.z /= z;
    this.w /= w;
    return this;
  }

  /**
   *  Divides each component by a scalar, in place. Returns this vector.
   */
  public Vec4d divide(double s) {
    return divide(s, s, s, s);
  }

  /**
   *  Scales the length of this vector to 1, in place. Returns this vector.
   */
  public Vec4d normalize() {
    return divide(length());
  }

  /**
   * Negates this vector's components, in place. Returns this vector.
   */
  public Vec4d negate() {
    x = -x;
    y = -y;
    z = -z;
    w = -w;
    return this;
  }

  @Override
  public double x() {
    return x;
  }

  @Override
  public double y() {
    return y;
  }

  @Override
  public double z() {
    return z;
  }
  
  @Override
  public double w() {
    return w;
  }

  @Override
  public Vec4d plus(ConstVec4d v) {
    return copy().add(v);
  }

  @Override
  public Vec4d plus(double x, double y, double z, double w) {
    return copy().add(x, y, z, w);
  }

  @Override
  public Vec4d plus(double s) {
    return copy().add(s);
  }

  @Override
  public Vec4d minus(ConstVec4d v) {
    return copy().subtract(v);
  }

  @Override
  public Vec4d minus(double x, double y, double z, double w) {
    return copy().subtract(x, y, z, w);
  }

  @Override
  public Vec4d minus(double s) {
    return copy().subtract(s);
  }

  @Override
  public Vec4d times(ConstVec4d v) {
    return copy().multiply(v);
  }

  @Override
  public Vec4d times(double x, double y, double z, double w) {
    return copy().multiply(x, y, z, w);
  }

  @Override
  public Vec4d times(double s) {
    return copy().multiply(s);
  }

  @Override
  public Vec4d over(ConstVec4d v) {
    return copy().divide(v);
  }

  @Override
  public Vec4d over(double x, double y, double z, double w) {
    return copy().divide(x, y, z, w);
  }

  @Override
  public Vec4d over(double s) {
    return copy().divide(s);
  }

  @Override
  public double dot(ConstVec4d v) {
    return x * x + y * y + z * z + w * w;
  }

  @Override
  public Vec4d normalized() {
    return copy().normalize();
  }

  @Override
  public Vec4d negated() {
    return copy().negate();
  }

  @Override
  public double length() {
    return Math.sqrt(lengthSquared());
  }

  @Override
  public double lengthSquared() {
    return dot(this);
  }

  @Override
  public double[] toArray() {
    return new double[] { x, y, z, w };
  }

  @Override
  public Vec4d copy() {
    return new Vec4d(x, y, z, w);
  }
  
  @Override
  public Vec4f toFloat() {
    return new Vec4f((float)x, (float)y, (float)z, (float)w);
  }
  
  @Override
  public void putInto(DoubleBuffer buf) {
    buf.put(x);
    buf.put(y);
    buf.put(z);
    buf.put(w);
  }
  
  /**
   * Creates a unit vector along the x axis (1, 0, 0, 0).
   */
  public static Vec4d axisX() {
    return new Vec4d(1, 0, 0, 0);
  }
  
  /**
   * Creates a unit vector along the y axis (0, 1, 0, 0).
   */
  public static Vec4d axisY() {
    return new Vec4d(0, 1, 0, 0);
  }
  
  /**
   * Creates a unit vector along the z axis (0, 0, 1, 0).
   */
  public static Vec4d axisZ() {
    return new Vec4d(0, 0, 1, 0);
  }
  
  /**
   * Creates a unit vector along the z axis (0, 0, 0, 1).
   */
  public static Vec4d axisW() {
    return new Vec4d(0, 0, 0, 1);
  }
  
  // SWIZZLING OPERATIONS
  // ==============================================================================================
  public final Vec2d xx() { return new Vec2d(x, x); }
  public final Vec2d xy() { return new Vec2d(x, y); }
  public final Vec2d xz() { return new Vec2d(x, z); }
  public final Vec2d xw() { return new Vec2d(x, w); }
  public final Vec2d yx() { return new Vec2d(y, x); }
  public final Vec2d yy() { return new Vec2d(y, y); }
  public final Vec2d yz() { return new Vec2d(y, z); }
  public final Vec2d yw() { return new Vec2d(y, w); }
  public final Vec2d zx() { return new Vec2d(z, x); }
  public final Vec2d zy() { return new Vec2d(z, y); }
  public final Vec2d zz() { return new Vec2d(z, z); }
  public final Vec2d zw() { return new Vec2d(z, w); }
  public final Vec2d wx() { return new Vec2d(w, x); }
  public final Vec2d wy() { return new Vec2d(w, y); }
  public final Vec2d wz() { return new Vec2d(w, z); }
  public final Vec2d ww() { return new Vec2d(w, w); }
  public final Vec3d xxx() { return new Vec3d(x, x, x); }
  public final Vec3d xxy() { return new Vec3d(x, x, y); }
  public final Vec3d xxz() { return new Vec3d(x, x, z); }
  public final Vec3d xxw() { return new Vec3d(x, x, w); }
  public final Vec3d xyx() { return new Vec3d(x, y, x); }
  public final Vec3d xyy() { return new Vec3d(x, y, y); }
  public final Vec3d xyz() { return new Vec3d(x, y, z); }
  public final Vec3d xyw() { return new Vec3d(x, y, w); }
  public final Vec3d xzx() { return new Vec3d(x, z, x); }
  public final Vec3d xzy() { return new Vec3d(x, z, y); }
  public final Vec3d xzz() { return new Vec3d(x, z, z); }
  public final Vec3d xzw() { return new Vec3d(x, z, w); }
  public final Vec3d xwx() { return new Vec3d(x, w, x); }
  public final Vec3d xwy() { return new Vec3d(x, w, y); }
  public final Vec3d xwz() { return new Vec3d(x, w, z); }
  public final Vec3d xww() { return new Vec3d(x, w, w); }
  public final Vec3d yxx() { return new Vec3d(y, x, x); }
  public final Vec3d yxy() { return new Vec3d(y, x, y); }
  public final Vec3d yxz() { return new Vec3d(y, x, z); }
  public final Vec3d yxw() { return new Vec3d(y, x, w); }
  public final Vec3d yyx() { return new Vec3d(y, y, x); }
  public final Vec3d yyy() { return new Vec3d(y, y, y); }
  public final Vec3d yyz() { return new Vec3d(y, y, z); }
  public final Vec3d yyw() { return new Vec3d(y, y, w); }
  public final Vec3d yzx() { return new Vec3d(y, z, x); }
  public final Vec3d yzy() { return new Vec3d(y, z, y); }
  public final Vec3d yzz() { return new Vec3d(y, z, z); }
  public final Vec3d yzw() { return new Vec3d(y, z, w); }
  public final Vec3d ywx() { return new Vec3d(y, w, x); }
  public final Vec3d ywy() { return new Vec3d(y, w, y); }
  public final Vec3d ywz() { return new Vec3d(y, w, z); }
  public final Vec3d yww() { return new Vec3d(y, w, w); }
  public final Vec3d zxx() { return new Vec3d(z, x, x); }
  public final Vec3d zxy() { return new Vec3d(z, x, y); }
  public final Vec3d zxz() { return new Vec3d(z, x, z); }
  public final Vec3d zxw() { return new Vec3d(z, x, w); }
  public final Vec3d zyx() { return new Vec3d(z, y, x); }
  public final Vec3d zyy() { return new Vec3d(z, y, y); }
  public final Vec3d zyz() { return new Vec3d(z, y, z); }
  public final Vec3d zyw() { return new Vec3d(z, y, w); }
  public final Vec3d zzx() { return new Vec3d(z, z, x); }
  public final Vec3d zzy() { return new Vec3d(z, z, y); }
  public final Vec3d zzz() { return new Vec3d(z, z, z); }
  public final Vec3d zzw() { return new Vec3d(z, z, w); }
  public final Vec3d zwx() { return new Vec3d(z, w, x); }
  public final Vec3d zwy() { return new Vec3d(z, w, y); }
  public final Vec3d zwz() { return new Vec3d(z, w, z); }
  public final Vec3d zww() { return new Vec3d(z, w, w); }
  public final Vec3d wxx() { return new Vec3d(w, x, x); }
  public final Vec3d wxy() { return new Vec3d(w, x, y); }
  public final Vec3d wxz() { return new Vec3d(w, x, z); }
  public final Vec3d wxw() { return new Vec3d(w, x, w); }
  public final Vec3d wyx() { return new Vec3d(w, y, x); }
  public final Vec3d wyy() { return new Vec3d(w, y, y); }
  public final Vec3d wyz() { return new Vec3d(w, y, z); }
  public final Vec3d wyw() { return new Vec3d(w, y, w); }
  public final Vec3d wzx() { return new Vec3d(w, z, x); }
  public final Vec3d wzy() { return new Vec3d(w, z, y); }
  public final Vec3d wzz() { return new Vec3d(w, z, z); }
  public final Vec3d wzw() { return new Vec3d(w, z, w); }
  public final Vec3d wwx() { return new Vec3d(w, w, x); }
  public final Vec3d wwy() { return new Vec3d(w, w, y); }
  public final Vec3d wwz() { return new Vec3d(w, w, z); }
  public final Vec3d www() { return new Vec3d(w, w, w); }
  public final Vec4d xxxx() { return new Vec4d(x, x, x, x); }
  public final Vec4d xxxy() { return new Vec4d(x, x, x, y); }
  public final Vec4d xxxz() { return new Vec4d(x, x, x, z); }
  public final Vec4d xxxw() { return new Vec4d(x, x, x, w); }
  public final Vec4d xxyx() { return new Vec4d(x, x, y, x); }
  public final Vec4d xxyy() { return new Vec4d(x, x, y, y); }
  public final Vec4d xxyz() { return new Vec4d(x, x, y, z); }
  public final Vec4d xxyw() { return new Vec4d(x, x, y, w); }
  public final Vec4d xxzx() { return new Vec4d(x, x, z, x); }
  public final Vec4d xxzy() { return new Vec4d(x, x, z, y); }
  public final Vec4d xxzz() { return new Vec4d(x, x, z, z); }
  public final Vec4d xxzw() { return new Vec4d(x, x, z, w); }
  public final Vec4d xxwx() { return new Vec4d(x, x, w, x); }
  public final Vec4d xxwy() { return new Vec4d(x, x, w, y); }
  public final Vec4d xxwz() { return new Vec4d(x, x, w, z); }
  public final Vec4d xxww() { return new Vec4d(x, x, w, w); }
  public final Vec4d xyxx() { return new Vec4d(x, y, x, x); }
  public final Vec4d xyxy() { return new Vec4d(x, y, x, y); }
  public final Vec4d xyxz() { return new Vec4d(x, y, x, z); }
  public final Vec4d xyxw() { return new Vec4d(x, y, x, w); }
  public final Vec4d xyyx() { return new Vec4d(x, y, y, x); }
  public final Vec4d xyyy() { return new Vec4d(x, y, y, y); }
  public final Vec4d xyyz() { return new Vec4d(x, y, y, z); }
  public final Vec4d xyyw() { return new Vec4d(x, y, y, w); }
  public final Vec4d xyzx() { return new Vec4d(x, y, z, x); }
  public final Vec4d xyzy() { return new Vec4d(x, y, z, y); }
  public final Vec4d xyzz() { return new Vec4d(x, y, z, z); }
  public final Vec4d xyzw() { return new Vec4d(x, y, z, w); }
  public final Vec4d xywx() { return new Vec4d(x, y, w, x); }
  public final Vec4d xywy() { return new Vec4d(x, y, w, y); }
  public final Vec4d xywz() { return new Vec4d(x, y, w, z); }
  public final Vec4d xyww() { return new Vec4d(x, y, w, w); }
  public final Vec4d xzxx() { return new Vec4d(x, z, x, x); }
  public final Vec4d xzxy() { return new Vec4d(x, z, x, y); }
  public final Vec4d xzxz() { return new Vec4d(x, z, x, z); }
  public final Vec4d xzxw() { return new Vec4d(x, z, x, w); }
  public final Vec4d xzyx() { return new Vec4d(x, z, y, x); }
  public final Vec4d xzyy() { return new Vec4d(x, z, y, y); }
  public final Vec4d xzyz() { return new Vec4d(x, z, y, z); }
  public final Vec4d xzyw() { return new Vec4d(x, z, y, w); }
  public final Vec4d xzzx() { return new Vec4d(x, z, z, x); }
  public final Vec4d xzzy() { return new Vec4d(x, z, z, y); }
  public final Vec4d xzzz() { return new Vec4d(x, z, z, z); }
  public final Vec4d xzzw() { return new Vec4d(x, z, z, w); }
  public final Vec4d xzwx() { return new Vec4d(x, z, w, x); }
  public final Vec4d xzwy() { return new Vec4d(x, z, w, y); }
  public final Vec4d xzwz() { return new Vec4d(x, z, w, z); }
  public final Vec4d xzww() { return new Vec4d(x, z, w, w); }
  public final Vec4d xwxx() { return new Vec4d(x, w, x, x); }
  public final Vec4d xwxy() { return new Vec4d(x, w, x, y); }
  public final Vec4d xwxz() { return new Vec4d(x, w, x, z); }
  public final Vec4d xwxw() { return new Vec4d(x, w, x, w); }
  public final Vec4d xwyx() { return new Vec4d(x, w, y, x); }
  public final Vec4d xwyy() { return new Vec4d(x, w, y, y); }
  public final Vec4d xwyz() { return new Vec4d(x, w, y, z); }
  public final Vec4d xwyw() { return new Vec4d(x, w, y, w); }
  public final Vec4d xwzx() { return new Vec4d(x, w, z, x); }
  public final Vec4d xwzy() { return new Vec4d(x, w, z, y); }
  public final Vec4d xwzz() { return new Vec4d(x, w, z, z); }
  public final Vec4d xwzw() { return new Vec4d(x, w, z, w); }
  public final Vec4d xwwx() { return new Vec4d(x, w, w, x); }
  public final Vec4d xwwy() { return new Vec4d(x, w, w, y); }
  public final Vec4d xwwz() { return new Vec4d(x, w, w, z); }
  public final Vec4d xwww() { return new Vec4d(x, w, w, w); }
  public final Vec4d yxxx() { return new Vec4d(y, x, x, x); }
  public final Vec4d yxxy() { return new Vec4d(y, x, x, y); }
  public final Vec4d yxxz() { return new Vec4d(y, x, x, z); }
  public final Vec4d yxxw() { return new Vec4d(y, x, x, w); }
  public final Vec4d yxyx() { return new Vec4d(y, x, y, x); }
  public final Vec4d yxyy() { return new Vec4d(y, x, y, y); }
  public final Vec4d yxyz() { return new Vec4d(y, x, y, z); }
  public final Vec4d yxyw() { return new Vec4d(y, x, y, w); }
  public final Vec4d yxzx() { return new Vec4d(y, x, z, x); }
  public final Vec4d yxzy() { return new Vec4d(y, x, z, y); }
  public final Vec4d yxzz() { return new Vec4d(y, x, z, z); }
  public final Vec4d yxzw() { return new Vec4d(y, x, z, w); }
  public final Vec4d yxwx() { return new Vec4d(y, x, w, x); }
  public final Vec4d yxwy() { return new Vec4d(y, x, w, y); }
  public final Vec4d yxwz() { return new Vec4d(y, x, w, z); }
  public final Vec4d yxww() { return new Vec4d(y, x, w, w); }
  public final Vec4d yyxx() { return new Vec4d(y, y, x, x); }
  public final Vec4d yyxy() { return new Vec4d(y, y, x, y); }
  public final Vec4d yyxz() { return new Vec4d(y, y, x, z); }
  public final Vec4d yyxw() { return new Vec4d(y, y, x, w); }
  public final Vec4d yyyx() { return new Vec4d(y, y, y, x); }
  public final Vec4d yyyy() { return new Vec4d(y, y, y, y); }
  public final Vec4d yyyz() { return new Vec4d(y, y, y, z); }
  public final Vec4d yyyw() { return new Vec4d(y, y, y, w); }
  public final Vec4d yyzx() { return new Vec4d(y, y, z, x); }
  public final Vec4d yyzy() { return new Vec4d(y, y, z, y); }
  public final Vec4d yyzz() { return new Vec4d(y, y, z, z); }
  public final Vec4d yyzw() { return new Vec4d(y, y, z, w); }
  public final Vec4d yywx() { return new Vec4d(y, y, w, x); }
  public final Vec4d yywy() { return new Vec4d(y, y, w, y); }
  public final Vec4d yywz() { return new Vec4d(y, y, w, z); }
  public final Vec4d yyww() { return new Vec4d(y, y, w, w); }
  public final Vec4d yzxx() { return new Vec4d(y, z, x, x); }
  public final Vec4d yzxy() { return new Vec4d(y, z, x, y); }
  public final Vec4d yzxz() { return new Vec4d(y, z, x, z); }
  public final Vec4d yzxw() { return new Vec4d(y, z, x, w); }
  public final Vec4d yzyx() { return new Vec4d(y, z, y, x); }
  public final Vec4d yzyy() { return new Vec4d(y, z, y, y); }
  public final Vec4d yzyz() { return new Vec4d(y, z, y, z); }
  public final Vec4d yzyw() { return new Vec4d(y, z, y, w); }
  public final Vec4d yzzx() { return new Vec4d(y, z, z, x); }
  public final Vec4d yzzy() { return new Vec4d(y, z, z, y); }
  public final Vec4d yzzz() { return new Vec4d(y, z, z, z); }
  public final Vec4d yzzw() { return new Vec4d(y, z, z, w); }
  public final Vec4d yzwx() { return new Vec4d(y, z, w, x); }
  public final Vec4d yzwy() { return new Vec4d(y, z, w, y); }
  public final Vec4d yzwz() { return new Vec4d(y, z, w, z); }
  public final Vec4d yzww() { return new Vec4d(y, z, w, w); }
  public final Vec4d ywxx() { return new Vec4d(y, w, x, x); }
  public final Vec4d ywxy() { return new Vec4d(y, w, x, y); }
  public final Vec4d ywxz() { return new Vec4d(y, w, x, z); }
  public final Vec4d ywxw() { return new Vec4d(y, w, x, w); }
  public final Vec4d ywyx() { return new Vec4d(y, w, y, x); }
  public final Vec4d ywyy() { return new Vec4d(y, w, y, y); }
  public final Vec4d ywyz() { return new Vec4d(y, w, y, z); }
  public final Vec4d ywyw() { return new Vec4d(y, w, y, w); }
  public final Vec4d ywzx() { return new Vec4d(y, w, z, x); }
  public final Vec4d ywzy() { return new Vec4d(y, w, z, y); }
  public final Vec4d ywzz() { return new Vec4d(y, w, z, z); }
  public final Vec4d ywzw() { return new Vec4d(y, w, z, w); }
  public final Vec4d ywwx() { return new Vec4d(y, w, w, x); }
  public final Vec4d ywwy() { return new Vec4d(y, w, w, y); }
  public final Vec4d ywwz() { return new Vec4d(y, w, w, z); }
  public final Vec4d ywww() { return new Vec4d(y, w, w, w); }
  public final Vec4d zxxx() { return new Vec4d(z, x, x, x); }
  public final Vec4d zxxy() { return new Vec4d(z, x, x, y); }
  public final Vec4d zxxz() { return new Vec4d(z, x, x, z); }
  public final Vec4d zxxw() { return new Vec4d(z, x, x, w); }
  public final Vec4d zxyx() { return new Vec4d(z, x, y, x); }
  public final Vec4d zxyy() { return new Vec4d(z, x, y, y); }
  public final Vec4d zxyz() { return new Vec4d(z, x, y, z); }
  public final Vec4d zxyw() { return new Vec4d(z, x, y, w); }
  public final Vec4d zxzx() { return new Vec4d(z, x, z, x); }
  public final Vec4d zxzy() { return new Vec4d(z, x, z, y); }
  public final Vec4d zxzz() { return new Vec4d(z, x, z, z); }
  public final Vec4d zxzw() { return new Vec4d(z, x, z, w); }
  public final Vec4d zxwx() { return new Vec4d(z, x, w, x); }
  public final Vec4d zxwy() { return new Vec4d(z, x, w, y); }
  public final Vec4d zxwz() { return new Vec4d(z, x, w, z); }
  public final Vec4d zxww() { return new Vec4d(z, x, w, w); }
  public final Vec4d zyxx() { return new Vec4d(z, y, x, x); }
  public final Vec4d zyxy() { return new Vec4d(z, y, x, y); }
  public final Vec4d zyxz() { return new Vec4d(z, y, x, z); }
  public final Vec4d zyxw() { return new Vec4d(z, y, x, w); }
  public final Vec4d zyyx() { return new Vec4d(z, y, y, x); }
  public final Vec4d zyyy() { return new Vec4d(z, y, y, y); }
  public final Vec4d zyyz() { return new Vec4d(z, y, y, z); }
  public final Vec4d zyyw() { return new Vec4d(z, y, y, w); }
  public final Vec4d zyzx() { return new Vec4d(z, y, z, x); }
  public final Vec4d zyzy() { return new Vec4d(z, y, z, y); }
  public final Vec4d zyzz() { return new Vec4d(z, y, z, z); }
  public final Vec4d zyzw() { return new Vec4d(z, y, z, w); }
  public final Vec4d zywx() { return new Vec4d(z, y, w, x); }
  public final Vec4d zywy() { return new Vec4d(z, y, w, y); }
  public final Vec4d zywz() { return new Vec4d(z, y, w, z); }
  public final Vec4d zyww() { return new Vec4d(z, y, w, w); }
  public final Vec4d zzxx() { return new Vec4d(z, z, x, x); }
  public final Vec4d zzxy() { return new Vec4d(z, z, x, y); }
  public final Vec4d zzxz() { return new Vec4d(z, z, x, z); }
  public final Vec4d zzxw() { return new Vec4d(z, z, x, w); }
  public final Vec4d zzyx() { return new Vec4d(z, z, y, x); }
  public final Vec4d zzyy() { return new Vec4d(z, z, y, y); }
  public final Vec4d zzyz() { return new Vec4d(z, z, y, z); }
  public final Vec4d zzyw() { return new Vec4d(z, z, y, w); }
  public final Vec4d zzzx() { return new Vec4d(z, z, z, x); }
  public final Vec4d zzzy() { return new Vec4d(z, z, z, y); }
  public final Vec4d zzzz() { return new Vec4d(z, z, z, z); }
  public final Vec4d zzzw() { return new Vec4d(z, z, z, w); }
  public final Vec4d zzwx() { return new Vec4d(z, z, w, x); }
  public final Vec4d zzwy() { return new Vec4d(z, z, w, y); }
  public final Vec4d zzwz() { return new Vec4d(z, z, w, z); }
  public final Vec4d zzww() { return new Vec4d(z, z, w, w); }
  public final Vec4d zwxx() { return new Vec4d(z, w, x, x); }
  public final Vec4d zwxy() { return new Vec4d(z, w, x, y); }
  public final Vec4d zwxz() { return new Vec4d(z, w, x, z); }
  public final Vec4d zwxw() { return new Vec4d(z, w, x, w); }
  public final Vec4d zwyx() { return new Vec4d(z, w, y, x); }
  public final Vec4d zwyy() { return new Vec4d(z, w, y, y); }
  public final Vec4d zwyz() { return new Vec4d(z, w, y, z); }
  public final Vec4d zwyw() { return new Vec4d(z, w, y, w); }
  public final Vec4d zwzx() { return new Vec4d(z, w, z, x); }
  public final Vec4d zwzy() { return new Vec4d(z, w, z, y); }
  public final Vec4d zwzz() { return new Vec4d(z, w, z, z); }
  public final Vec4d zwzw() { return new Vec4d(z, w, z, w); }
  public final Vec4d zwwx() { return new Vec4d(z, w, w, x); }
  public final Vec4d zwwy() { return new Vec4d(z, w, w, y); }
  public final Vec4d zwwz() { return new Vec4d(z, w, w, z); }
  public final Vec4d zwww() { return new Vec4d(z, w, w, w); }
  public final Vec4d wxxx() { return new Vec4d(w, x, x, x); }
  public final Vec4d wxxy() { return new Vec4d(w, x, x, y); }
  public final Vec4d wxxz() { return new Vec4d(w, x, x, z); }
  public final Vec4d wxxw() { return new Vec4d(w, x, x, w); }
  public final Vec4d wxyx() { return new Vec4d(w, x, y, x); }
  public final Vec4d wxyy() { return new Vec4d(w, x, y, y); }
  public final Vec4d wxyz() { return new Vec4d(w, x, y, z); }
  public final Vec4d wxyw() { return new Vec4d(w, x, y, w); }
  public final Vec4d wxzx() { return new Vec4d(w, x, z, x); }
  public final Vec4d wxzy() { return new Vec4d(w, x, z, y); }
  public final Vec4d wxzz() { return new Vec4d(w, x, z, z); }
  public final Vec4d wxzw() { return new Vec4d(w, x, z, w); }
  public final Vec4d wxwx() { return new Vec4d(w, x, w, x); }
  public final Vec4d wxwy() { return new Vec4d(w, x, w, y); }
  public final Vec4d wxwz() { return new Vec4d(w, x, w, z); }
  public final Vec4d wxww() { return new Vec4d(w, x, w, w); }
  public final Vec4d wyxx() { return new Vec4d(w, y, x, x); }
  public final Vec4d wyxy() { return new Vec4d(w, y, x, y); }
  public final Vec4d wyxz() { return new Vec4d(w, y, x, z); }
  public final Vec4d wyxw() { return new Vec4d(w, y, x, w); }
  public final Vec4d wyyx() { return new Vec4d(w, y, y, x); }
  public final Vec4d wyyy() { return new Vec4d(w, y, y, y); }
  public final Vec4d wyyz() { return new Vec4d(w, y, y, z); }
  public final Vec4d wyyw() { return new Vec4d(w, y, y, w); }
  public final Vec4d wyzx() { return new Vec4d(w, y, z, x); }
  public final Vec4d wyzy() { return new Vec4d(w, y, z, y); }
  public final Vec4d wyzz() { return new Vec4d(w, y, z, z); }
  public final Vec4d wyzw() { return new Vec4d(w, y, z, w); }
  public final Vec4d wywx() { return new Vec4d(w, y, w, x); }
  public final Vec4d wywy() { return new Vec4d(w, y, w, y); }
  public final Vec4d wywz() { return new Vec4d(w, y, w, z); }
  public final Vec4d wyww() { return new Vec4d(w, y, w, w); }
  public final Vec4d wzxx() { return new Vec4d(w, z, x, x); }
  public final Vec4d wzxy() { return new Vec4d(w, z, x, y); }
  public final Vec4d wzxz() { return new Vec4d(w, z, x, z); }
  public final Vec4d wzxw() { return new Vec4d(w, z, x, w); }
  public final Vec4d wzyx() { return new Vec4d(w, z, y, x); }
  public final Vec4d wzyy() { return new Vec4d(w, z, y, y); }
  public final Vec4d wzyz() { return new Vec4d(w, z, y, z); }
  public final Vec4d wzyw() { return new Vec4d(w, z, y, w); }
  public final Vec4d wzzx() { return new Vec4d(w, z, z, x); }
  public final Vec4d wzzy() { return new Vec4d(w, z, z, y); }
  public final Vec4d wzzz() { return new Vec4d(w, z, z, z); }
  public final Vec4d wzzw() { return new Vec4d(w, z, z, w); }
  public final Vec4d wzwx() { return new Vec4d(w, z, w, x); }
  public final Vec4d wzwy() { return new Vec4d(w, z, w, y); }
  public final Vec4d wzwz() { return new Vec4d(w, z, w, z); }
  public final Vec4d wzww() { return new Vec4d(w, z, w, w); }
  public final Vec4d wwxx() { return new Vec4d(w, w, x, x); }
  public final Vec4d wwxy() { return new Vec4d(w, w, x, y); }
  public final Vec4d wwxz() { return new Vec4d(w, w, x, z); }
  public final Vec4d wwxw() { return new Vec4d(w, w, x, w); }
  public final Vec4d wwyx() { return new Vec4d(w, w, y, x); }
  public final Vec4d wwyy() { return new Vec4d(w, w, y, y); }
  public final Vec4d wwyz() { return new Vec4d(w, w, y, z); }
  public final Vec4d wwyw() { return new Vec4d(w, w, y, w); }
  public final Vec4d wwzx() { return new Vec4d(w, w, z, x); }
  public final Vec4d wwzy() { return new Vec4d(w, w, z, y); }
  public final Vec4d wwzz() { return new Vec4d(w, w, z, z); }
  public final Vec4d wwzw() { return new Vec4d(w, w, z, w); }
  public final Vec4d wwwx() { return new Vec4d(w, w, w, x); }
  public final Vec4d wwwy() { return new Vec4d(w, w, w, y); }
  public final Vec4d wwwz() { return new Vec4d(w, w, w, z); }
  public final Vec4d wwww() { return new Vec4d(w, w, w, w); }
  // ==============================================================================================
}