/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

import java.nio.FloatBuffer;

/**
 * 2D point/vector with float components.
 * 
 * @author justin
 */
public class Vec2f implements ConstVec2f {

  /** The first component. */
  public float x;

  /** The second component. */
  public float y;

  /**
   * Creates a vector with all values 0
   */
  public Vec2f() {
  }

  /**
   * Creates a vector using the values (x, y).
   */
  public Vec2f(float x, float y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Creates a vector using the values (s, s).
   */
  public Vec2f(float s) {
    this(s, s);
  }

  /**
   * Creates a vector using the values (v.x, v.y).
   */
  public Vec2f(ConstVec2f v) {
    this(v.x(), v.y());
  }

  /**
   * Creates a vector using the values (v.x, v.y).
   */
  public Vec2f(ConstVec3f v) {
    this(v.x(), v.y());
  }

  /**
   * Creates a vector using the values (v.x, v.y).
   */
  public Vec2f(ConstVec4f v) {
    this(v.x(), v.y());
  }

  /**
   * Creates a vector using the values (a[0], a[1]).
   */
  public Vec2f(float[] a) {
    this(a[0], a[1]);
  }

  /**
   * Creates a vector using the values (a[start], a[start+1]).
   */
  public Vec2f(float[] a, int start) {
    this(a[start], a[start + 1]);
  }

  /**
   *  Component-wise addition, in place. Returns this vector.
   */
  public Vec2f add(ConstVec2f v) {
    return add(v.x(), v.y());
  }

  /**
   *  Component-wise addition, in place. Returns this vector.
   */
  public Vec2f add(float x, float y) {
    this.x += x;
    this.y += y;
    return this;
  }

  /**
   *  Adds a scalar to each component, in place. Returns this vector.
   */
  public Vec2f add(float s) {
    return add(s, s);
  }

  /**
   *  Component-wise subtraction, in place. Returns this vector.
   */
  public Vec2f subtract(ConstVec2f v) {
    return subtract(v.x(), v.y());
  }

  /**
   *  Component-wise subtraction, in place. Returns this vector.
   */
  public Vec2f subtract(float x, float y) {
    this.x -= x;
    this.y -= y;
    return this;
  }

  /**
   *  Subtracts a scalar from each component, in place. Returns this vector.
   */
  public Vec2f subtract(float s) {
    return subtract(s, s);
  }

  /**
   *  Component-wise multiplication, in place. Returns this vector.
   */
  public Vec2f multiply(ConstVec2f v) {
    return multiply(v.x(), v.y());
  }

  /**
   *  Component-wise multiplication, in place. Returns this vector.
   */
  public Vec2f multiply(float x, float y) {
    this.x *= x;
    this.y *= y;
    return this;
  }

  /**
   *  Multiplies a scalar with each component, in place. Returns this vector.
   */
  public Vec2f multiply(float s) {
    return multiply(s, s);
  }

  /**
   *  Component-wise division, in place. Returns this vector.
   */
  public Vec2f divide(ConstVec2f v) {
    return divide(v.x(), v.y());
  }

  /**
   *  Component-wise division, in place. Returns this vector.
   */
  public Vec2f divide(float x, float y) {
    this.x /= x;
    this.y /= y;
    return this;
  }

  /**
   *  Divides each component by a scalar, in place. Returns this vector.
   */
  public Vec2f divide(float s) {
    return divide(s, s);
  }

  /**
   *  Scales the length of this vector to 1, in place. Returns this vector.
   */
  public Vec2f normalize() {
    return divide(length());
  }

  /**
   * Rotates this vector counter-clockwise by degrees, in place. Returns this vector.
   */
  public Vec2f rotateDegrees(double degrees) {
    return rotateRadians(Math.toRadians(degrees));
  }

  /**
   * Rotates this vector counter-clockwise by radians, in place. Returns this vector.
   */
  public Vec2f rotateRadians(double radians) {
    double c = Math.cos(radians);
    double s = Math.sin(radians);
    float xNew = (float) (c * x - s * y);
    y = (float) (s * x + c * y);
    x = xNew;
    return this;
  }

  /**
   * Rotates this vector counter-clockwise by 90 degrees, in place. Returns this vector.
   */
  public Vec2f rotateDegrees90() {
    float temp = x;
    x = -y;
    y = temp;
    return this;
  }

  /**
   * Rotates this vector counter-clockwise by 180 degrees, in place. Returns this vector.
   */
  public Vec2f rotateDegrees180() {
    return negate();
  }

  /**
   * Rotates this vector counter-clockwise by 270 degrees, in place. Returns this vector.
   */
  public Vec2f rotateDegrees270() {
    float temp = x;
    x = y;
    y = -temp;
    return this;
  }

  /**
   * Negates this vector's components, in place. Returns this vector.
   */
  public Vec2f negate() {
    x = -x;
    y = -y;
    return this;
  }

  /**
   * Reflects this vector across a surface normal, in place. Returns this vector.
   */
  public Vec2f reflect(ConstVec2f n) {
    Vec2f v = reflected(n);
    x = v.x;
    y = v.y;
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
  public Vec2f plus(ConstVec2f v) {
    return copy().add(v);
  }

  @Override
  public Vec2f plus(float x, float y) {
    return copy().add(x, y);
  }

  @Override
  public Vec2f plus(float s) {
    return copy().add(s);
  }

  @Override
  public Vec2f minus(ConstVec2f v) {
    return copy().subtract(v);
  }

  @Override
  public Vec2f minus(float x, float y) {
    return copy().subtract(x, y);
  }

  @Override
  public Vec2f minus(float s) {
    return copy().subtract(s);
  }

  @Override
  public Vec2f times(ConstVec2f v) {
    return copy().multiply(v);
  }

  @Override
  public Vec2f times(float x, float y) {
    return copy().multiply(x, y);
  }

  @Override
  public Vec2f times(float s) {
    return copy().multiply(s);
  }

  @Override
  public Vec2f over(ConstVec2f v) {
    return copy().divide(v);
  }

  @Override
  public Vec2f over(float x, float y) {
    return copy().divide(x, y);
  }

  @Override
  public Vec2f over(float s) {
    return copy().divide(s);
  }

  @Override
  public float dot(ConstVec2f v) {
    return x * x + y * y;
  }

  @Override
  public float cross(ConstVec2f v) {
    return x * v.y() - y * v.x();
  }

  @Override
  public Vec2f rotatedDegrees90() {
    return copy().rotateDegrees90();
  }

  @Override
  public Vec2f rotatedDegrees180() {
    return copy().rotateDegrees180();
  }

  @Override
  public Vec2f rotatedDegrees270() {
    return copy().rotateDegrees270();
  }

  @Override
  public Vec2f rotatedDegrees(double degrees) {
    return copy().rotateDegrees(degrees);
  }

  @Override
  public Vec2f rotatedRadians(double radians) {
    return copy().rotateRadians(radians);
  }

  @Override
  public Vec2f reflected(ConstVec2f n) {
    return this.minus(n.times(2f * n.dot(this)));
  }

  @Override
  public Vec2f normalized() {
    return copy().normalize();
  }

  @Override
  public Vec2f negated() {
    return copy().negate();
  }

  @Override
  public float anglePi() {
    return (float) Math.atan2(y, x);
  }

  @Override
  public float angle2Pi() {
    double radians = Math.atan2(y, x);
    return (float) (radians < 0 ? Math.PI * 2.0 + radians : radians);
  }

  @Override
  public float angle180() {
    return (float) Math.toDegrees(Math.atan2(y, x));
  }

  @Override
  public float angle360() {
    double radians = Math.atan2(y, x);
    return (float) Math.toDegrees(radians < 0 ? Math.PI * 2.0 + radians : radians);
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
    return new float[] { x, y };
  }

  @Override
  public Vec2f copy() {
    return new Vec2f(x, y);
  }
  
  @Override
  public void putInto(FloatBuffer buf) {
    buf.put(x);
    buf.put(y);
  }
  
  /**
   * Creates a unit vector along the x axis (1, 0).
   */
  public static Vec2f axisX() {
    return new Vec2f(1, 0);
  }
  
  /**
   * Creates a unit vector along the y axis (0, 1).
   */
  public static Vec2f axisY() {
    return new Vec2f(0, 1);
  }

  // SWIZZLING OPERATIONS
  // ==============================================================================================
  public final Vec2f xx() { return new Vec2f(x, x); }
  public final Vec2f xy() { return new Vec2f(x, y); }
  public final Vec2f yx() { return new Vec2f(y, x); }
  public final Vec2f yy() { return new Vec2f(y, y); }
  // ==============================================================================================
}