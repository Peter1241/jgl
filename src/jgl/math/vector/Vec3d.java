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
public class Vec3d implements ConstVec3d {

  /** The first component. */
  public double x;

  /** The second component. */
  public double y;

  /** The third component. */
  public double z;

  /**
   * Creates a vector with all values 0
   */
  public Vec3d() {
  }

  /**
   * Creates a vector using the values (x, y, z).
   */
  public Vec3d(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Creates a vector using the values (s, s, s).
   */
  public Vec3d(double s) {
    this(s, s, s);
  }

  /**
   * Creates a vector using the values (v.x, v.y, v.z).
   */
  public Vec3d(ConstVec3d v) {
    this(v.x(), v.y(), v.z());
  }

  /**
   * Creates a vector using the values (v.x, v.y, v.z).
   */
  public Vec3d(ConstVec4f v) {
    this(v.x(), v.y(), v.z());
  }

  /**
   * Creates a vector using the values (a[0], a[1], a[2]).
   */
  public Vec3d(double[] a) {
    this(a[0], a[1], a[2]);
  }

  /**
   * Creates a vector using the values (a[start], a[start+1], a[start+2]).
   */
  public Vec3d(double[] a, int start) {
    this(a[start], a[start + 1], a[start + 2]);
  }

  /**
   *  Component-wise addition, in place. Returns this vector.
   */
  public Vec3d add(ConstVec3d v) {
    return add(v.x(), v.y(), v.z());
  }

  /**
   *  Component-wise addition, in place. Returns this vector.
   */
  public Vec3d add(double x, double y, double z) {
    this.x += x;
    this.y += y;
    this.z += z;
    return this;
  }

  /**
   *  Adds a scalar to each component, in place. Returns this vector.
   */
  public Vec3d add(double s) {
    return add(s, s, s);
  }

  /**
   *  Component-wise subtraction, in place. Returns this vector.
   */
  public Vec3d subtract(ConstVec3d v) {
    return subtract(v.x(), v.y(), v.z());
  }

  /**
   *  Component-wise subtraction, in place. Returns this vector.
   */
  public Vec3d subtract(double x, double y, double z) {
    this.x -= x;
    this.y -= y;
    this.z -= z;
    return this;
  }

  /**
   *  Subtracts a scalar from each component, in place. Returns this vector.
   */
  public Vec3d subtract(double s) {
    return subtract(s, s, s);
  }

  /**
   *  Component-wise multiplication, in place. Returns this vector.
   */
  public Vec3d multiply(ConstVec3d v) {
    return multiply(v.x(), v.y(), v.z());
  }

  /**
   *  Component-wise multiplication, in place. Returns this vector.
   */
  public Vec3d multiply(double x, double y, double z) {
    this.x *= x;
    this.y *= y;
    this.z *= z;
    return this;
  }

  /**
   *  Multiplies a scalar with each component, in place. Returns this vector.
   */
  public Vec3d multiply(double s) {
    return multiply(s, s, s);
  }

  /**
   *  Component-wise division, in place. Returns this vector.
   */
  public Vec3d divide(ConstVec3d v) {
    return divide(v.x(), v.y(), v.z());
  }

  /**
   *  Component-wise division, in place. Returns this vector.
   */
  public Vec3d divide(double x, double y, double z) {
    this.x /= x;
    this.y /= y;
    this.z /= z;
    return this;
  }

  /**
   *  Divides each component by a scalar, in place. Returns this vector.
   */
  public Vec3d divide(double s) {
    return divide(s, s, s);
  }

  /**
   *  Scales the length of this vector to 1, in place. Returns this vector.
   */
  public Vec3d normalize() {
    return divide(length());
  }

  /**
   * Negates this vector's components, in place. Returns this vector.
   */
  public Vec3d negate() {
    x = -x;
    y = -y;
    z = -z;
    return this;
  }

  /**
   * Reflects this vector across a surface normal, in place. Returns this vector.
   */
  public Vec3d reflect(ConstVec3d n) {
    Vec3d v = reflected(n);
    x = v.x;
    y = v.y;
    z = v.z;
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
  public Vec3d plus(ConstVec3d v) {
    return copy().add(v);
  }

  @Override
  public Vec3d plus(double x, double y, double z) {
    return copy().add(x, y, z);
  }

  @Override
  public Vec3d plus(double s) {
    return copy().add(s);
  }

  @Override
  public Vec3d minus(ConstVec3d v) {
    return copy().subtract(v);
  }

  @Override
  public Vec3d minus(double x, double y, double z) {
    return copy().subtract(x, y, z);
  }

  @Override
  public Vec3d minus(double s) {
    return copy().subtract(s);
  }

  @Override
  public Vec3d times(ConstVec3d v) {
    return copy().multiply(v);
  }

  @Override
  public Vec3d times(double x, double y, double z) {
    return copy().multiply(x, y, z);
  }

  @Override
  public Vec3d times(double s) {
    return copy().multiply(s);
  }

  @Override
  public Vec3d over(ConstVec3d v) {
    return copy().divide(v);
  }

  @Override
  public Vec3d over(double x, double y, double z) {
    return copy().divide(x, y, z);
  }

  @Override
  public Vec3d over(double s) {
    return copy().divide(s);
  }

  @Override
  public double dot(ConstVec3d v) {
    return x * x + y * y + z * z;
  }

  @Override
  public Vec3d cross(ConstVec3d v) {
    return new Vec3d(y * v.z() - z * v.y(), z * v.x() - x * v.z(), x * v.y() - y * v.x());
  }

  @Override
  public Vec3d reflected(ConstVec3d n) {
    return this.minus(n.times(2.0 * n.dot(this)));
  }

  @Override
  public Vec3d normalized() {
    return copy().normalize();
  }

  @Override
  public Vec3d negated() {
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
  public double[] array() {
    return new double[] { x, y, z };
  }

  @Override
  public Vec3d copy() {
    return new Vec3d(x, y, z);
  }
  
  @Override
  public void putInto(DoubleBuffer buf) {
    buf.put(x);
    buf.put(y);
    buf.put(z);
  }
  
  /**
   * Creates a unit vector along the x axis (1, 0, 0).
   */
  public static Vec3d axisX() {
    return new Vec3d(1, 0, 0);
  }
  
  /**
   * Creates a unit vector along the y axis (0, 1, 0).
   */
  public static Vec3d axisY() {
    return new Vec3d(0, 1, 0);
  }
  
  /**
   * Creates a unit vector along the z axis (0, 0, 1).
   */
  public static Vec3d axisZ() {
    return new Vec3d(0, 0, 1);
  }
  
  // SWIZZLING OPERATIONS
  // ==============================================================================================
  public final Vec2d xx() { return new Vec2d(x, x); }
  public final Vec2d xy() { return new Vec2d(x, y); }
  public final Vec2d xz() { return new Vec2d(x, z); }
  public final Vec2d yx() { return new Vec2d(y, x); }
  public final Vec2d yy() { return new Vec2d(y, y); }
  public final Vec2d yz() { return new Vec2d(y, z); }
  public final Vec2d zx() { return new Vec2d(z, x); }
  public final Vec2d zy() { return new Vec2d(z, y); }
  public final Vec2d zz() { return new Vec2d(z, z); }
  public final Vec3d xxx() { return new Vec3d(x, x, x); }
  public final Vec3d xxy() { return new Vec3d(x, x, y); }
  public final Vec3d xxz() { return new Vec3d(x, x, z); }
  public final Vec3d xyx() { return new Vec3d(x, y, x); }
  public final Vec3d xyy() { return new Vec3d(x, y, y); }
  public final Vec3d xyz() { return new Vec3d(x, y, z); }
  public final Vec3d xzx() { return new Vec3d(x, z, x); }
  public final Vec3d xzy() { return new Vec3d(x, z, y); }
  public final Vec3d xzz() { return new Vec3d(x, z, z); }
  public final Vec3d yxx() { return new Vec3d(y, x, x); }
  public final Vec3d yxy() { return new Vec3d(y, x, y); }
  public final Vec3d yxz() { return new Vec3d(y, x, z); }
  public final Vec3d yyx() { return new Vec3d(y, y, x); }
  public final Vec3d yyy() { return new Vec3d(y, y, y); }
  public final Vec3d yyz() { return new Vec3d(y, y, z); }
  public final Vec3d yzx() { return new Vec3d(y, z, x); }
  public final Vec3d yzy() { return new Vec3d(y, z, y); }
  public final Vec3d yzz() { return new Vec3d(y, z, z); }
  public final Vec3d zxx() { return new Vec3d(z, x, x); }
  public final Vec3d zxy() { return new Vec3d(z, x, y); }
  public final Vec3d zxz() { return new Vec3d(z, x, z); }
  public final Vec3d zyx() { return new Vec3d(z, y, x); }
  public final Vec3d zyy() { return new Vec3d(z, y, y); }
  public final Vec3d zyz() { return new Vec3d(z, y, z); }
  public final Vec3d zzx() { return new Vec3d(z, z, x); }
  public final Vec3d zzy() { return new Vec3d(z, z, y); }
  public final Vec3d zzz() { return new Vec3d(z, z, z); }
  // ==============================================================================================
}