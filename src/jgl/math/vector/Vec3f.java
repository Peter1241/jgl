/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

import java.nio.FloatBuffer;

/**
 * 3D point/vector with float components.
 * 
 * @author justin
 */
public class Vec3f implements ConstVec3f {

  /** The first component. */
  public float x;

  /** The second component. */
  public float y;

  /** The third component. */
  public float z;

  /**
   * Creates a vector with all values 0
   */
  public Vec3f() {
  }

  /**
   * Creates a vector using the values (x, y, z).
   */
  public Vec3f(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Creates a vector using the values (s, s, s).
   */
  public Vec3f(float s) {
    this(s, s, s);
  }

  /**
   * Creates a vector using the values (v.x, v.y, v.z).
   */
  public Vec3f(ConstVec3f v) {
    this(v.x(), v.y(), v.z());
  }

  /**
   * Creates a vector using the values (v.x, v.y, v.z).
   */
  public Vec3f(ConstVec4f v) {
    this(v.x(), v.y(), v.z());
  }

  /**
   * Creates a vector using the values (a[0], a[1], a[2]).
   */
  public Vec3f(float[] a) {
    this(a[0], a[1], a[2]);
  }

  /**
   * Creates a vector using the values (a[start], a[start+1], a[start+2]).
   */
  public Vec3f(float[] a, int start) {
    this(a[start], a[start + 1], a[start + 2]);
  }

  /**
   *  Component-wise addition, in place. Returns this vector.
   */
  public Vec3f add(ConstVec3f v) {
    return add(v.x(), v.y(), v.z());
  }

  /**
   *  Component-wise addition, in place. Returns this vector.
   */
  public Vec3f add(float x, float y, float z) {
    this.x += x;
    this.y += y;
    this.z += z;
    return this;
  }

  /**
   *  Adds a scalar to each component, in place. Returns this vector.
   */
  public Vec3f add(float s) {
    return add(s, s, s);
  }

  /**
   *  Component-wise subtraction, in place. Returns this vector.
   */
  public Vec3f subtract(ConstVec3f v) {
    return subtract(v.x(), v.y(), v.z());
  }

  /**
   *  Component-wise subtraction, in place. Returns this vector.
   */
  public Vec3f subtract(float x, float y, float z) {
    this.x -= x;
    this.y -= y;
    this.z -= z;
    return this;
  }

  /**
   *  Subtracts a scalar from each component, in place. Returns this vector.
   */
  public Vec3f subtract(float s) {
    return subtract(s, s, s);
  }

  /**
   *  Component-wise multiplication, in place. Returns this vector.
   */
  public Vec3f multiply(ConstVec3f v) {
    return multiply(v.x(), v.y(), v.z());
  }

  /**
   *  Component-wise multiplication, in place. Returns this vector.
   */
  public Vec3f multiply(float x, float y, float z) {
    this.x *= x;
    this.y *= y;
    this.z *= z;
    return this;
  }

  /**
   *  Multiplies a scalar with each component, in place. Returns this vector.
   */
  public Vec3f multiply(float s) {
    return multiply(s, s, s);
  }

  /**
   *  Component-wise division, in place. Returns this vector.
   */
  public Vec3f divide(ConstVec3f v) {
    return divide(v.x(), v.y(), v.z());
  }

  /**
   *  Component-wise division, in place. Returns this vector.
   */
  public Vec3f divide(float x, float y, float z) {
    this.x /= x;
    this.y /= y;
    this.z /= z;
    return this;
  }

  /**
   *  Divides each component by a scalar, in place. Returns this vector.
   */
  public Vec3f divide(float s) {
    return divide(s, s, s);
  }

  /**
   *  Scales the length of this vector to 1, in place. Returns this vector.
   */
  public Vec3f normalize() {
    return divide(length());
  }

  /**
   * Negates this vector's components, in place. Returns this vector.
   */
  public Vec3f negate() {
    x = -x;
    y = -y;
    z = -z;
    return this;
  }

  /**
   * Reflects this vector across a surface normal, in place. Returns this vector.
   */
  public Vec3f reflect(ConstVec3f n) {
    Vec3f v = reflected(n);
    x = v.x;
    y = v.y;
    z = v.z;
    return this;
  }

  @Override
  public float x() {
    return x;
  }

  @Override
  public float y() {
    return y;
  }

  @Override
  public float z() {
    return z;
  }

  @Override
  public Vec3f plus(ConstVec3f v) {
    return copy().add(v);
  }

  @Override
  public Vec3f plus(float x, float y, float z) {
    return copy().add(x, y, z);
  }

  @Override
  public Vec3f plus(float s) {
    return copy().add(s);
  }

  @Override
  public Vec3f minus(ConstVec3f v) {
    return copy().subtract(v);
  }

  @Override
  public Vec3f minus(float x, float y, float z) {
    return copy().subtract(x, y, z);
  }

  @Override
  public Vec3f minus(float s) {
    return copy().subtract(s);
  }

  @Override
  public Vec3f times(ConstVec3f v) {
    return copy().multiply(v);
  }

  @Override
  public Vec3f times(float x, float y, float z) {
    return copy().multiply(x, y, z);
  }

  @Override
  public Vec3f times(float s) {
    return copy().multiply(s);
  }

  @Override
  public Vec3f over(ConstVec3f v) {
    return copy().divide(v);
  }

  @Override
  public Vec3f over(float x, float y, float z) {
    return copy().divide(x, y, z);
  }

  @Override
  public Vec3f over(float s) {
    return copy().divide(s);
  }

  @Override
  public float dot(ConstVec3f v) {
    return x * x + y * y + z * z;
  }

  @Override
  public Vec3f cross(ConstVec3f v) {
    return new Vec3f(y * v.z() - z * v.y(), z * v.x() - x * v.z(), x * v.y() - y * v.x());
  }

  @Override
  public Vec3f reflected(ConstVec3f n) {
    return this.minus(n.times(2f * n.dot(this)));
  }

  @Override
  public Vec3f normalized() {
    return copy().normalize();
  }

  @Override
  public Vec3f negated() {
    return copy().negate();
  }

  @Override
  public float length() {
    return (float) Math.sqrt(lengthSquared());
  }

  @Override
  public float lengthSquared() {
    return dot(this);
  }

  @Override
  public float[] array() {
    return new float[] { x, y, z };
  }

  @Override
  public Vec3f copy() {
    return new Vec3f(x, y, z);
  }
  
  @Override
  public void putInto(FloatBuffer buf) {
    buf.put(x);
    buf.put(y);
    buf.put(z);
  }
  
  /**
   * Creates a unit vector along the x axis (1, 0, 0).
   */
  public static Vec3f axisX() {
    return new Vec3f(1, 0, 0);
  }
  
  /**
   * Creates a unit vector along the y axis (0, 1, 0).
   */
  public static Vec3f axisY() {
    return new Vec3f(0, 1, 0);
  }
  
  /**
   * Creates a unit vector along the z axis (0, 0, 1).
   */
  public static Vec3f axisZ() {
    return new Vec3f(0, 0, 1);
  }
  
  // SWIZZLING OPERATIONS
  // ==============================================================================================
  public final Vec2f xx() { return new Vec2f(x, x); }
  public final Vec2f xy() { return new Vec2f(x, y); }
  public final Vec2f xz() { return new Vec2f(x, z); }
  public final Vec2f yx() { return new Vec2f(y, x); }
  public final Vec2f yy() { return new Vec2f(y, y); }
  public final Vec2f yz() { return new Vec2f(y, z); }
  public final Vec2f zx() { return new Vec2f(z, x); }
  public final Vec2f zy() { return new Vec2f(z, y); }
  public final Vec2f zz() { return new Vec2f(z, z); }
  public final Vec3f xxx() { return new Vec3f(x, x, x); }
  public final Vec3f xxy() { return new Vec3f(x, x, y); }
  public final Vec3f xxz() { return new Vec3f(x, x, z); }
  public final Vec3f xyx() { return new Vec3f(x, y, x); }
  public final Vec3f xyy() { return new Vec3f(x, y, y); }
  public final Vec3f xyz() { return new Vec3f(x, y, z); }
  public final Vec3f xzx() { return new Vec3f(x, z, x); }
  public final Vec3f xzy() { return new Vec3f(x, z, y); }
  public final Vec3f xzz() { return new Vec3f(x, z, z); }
  public final Vec3f yxx() { return new Vec3f(y, x, x); }
  public final Vec3f yxy() { return new Vec3f(y, x, y); }
  public final Vec3f yxz() { return new Vec3f(y, x, z); }
  public final Vec3f yyx() { return new Vec3f(y, y, x); }
  public final Vec3f yyy() { return new Vec3f(y, y, y); }
  public final Vec3f yyz() { return new Vec3f(y, y, z); }
  public final Vec3f yzx() { return new Vec3f(y, z, x); }
  public final Vec3f yzy() { return new Vec3f(y, z, y); }
  public final Vec3f yzz() { return new Vec3f(y, z, z); }
  public final Vec3f zxx() { return new Vec3f(z, x, x); }
  public final Vec3f zxy() { return new Vec3f(z, x, y); }
  public final Vec3f zxz() { return new Vec3f(z, x, z); }
  public final Vec3f zyx() { return new Vec3f(z, y, x); }
  public final Vec3f zyy() { return new Vec3f(z, y, y); }
  public final Vec3f zyz() { return new Vec3f(z, y, z); }
  public final Vec3f zzx() { return new Vec3f(z, z, x); }
  public final Vec3f zzy() { return new Vec3f(z, z, y); }
  public final Vec3f zzz() { return new Vec3f(z, z, z); }
  // ==============================================================================================
}