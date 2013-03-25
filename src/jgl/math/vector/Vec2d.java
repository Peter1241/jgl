/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

import java.nio.DoubleBuffer;

class Swizzle {
  
}

/**
 * 2D point/vector with double components.
 * 
 * @author justin
 */
public class Vec2d implements ReadableVec2d {

  /** The first component. */
  public double x;

  /** The second component. */
  public double y;

  /**
   * Creates a vector with all values 0
   */
  public Vec2d() {
  }

  /**
   * Creates a vector using the values (x, y).
   */
  public Vec2d(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Creates a vector using the values (s, s).
   */
  public Vec2d(double s) {
    this(s, s);
  }

  /**
   * Creates a vector using the values (v.x, v.y).
   */
  public Vec2d(ReadableVec2d v) {
    this(v.x(), v.y());
  }

  /**
   * Creates a vector using the values (v.x, v.y).
   */
  public Vec2d(ReadableVec3f v) {
    this(v.x(), v.y());
  }

  /**
   * Creates a vector using the values (v.x, v.y).
   */
  public Vec2d(ReadableVec4f v) {
    this(v.x(), v.y());
  }

  /**
   * Creates a vector using the values (a[0], a[1]).
   */
  public Vec2d(double[] a) {
    this(a[0], a[1]);
  }

  /**
   * Creates a vector using the values (a[start], a[start+1]).
   */
  public Vec2d(double[] a, int start) {
    this(a[start], a[start + 1]);
  }

  /**
   *  Component-wise addition, in place. Returns this vector.
   */
  public Vec2d add(ReadableVec2d v) {
    return add(v.x(), v.y());
  }

  /**
   *  Component-wise addition, in place. Returns this vector.
   */
  public Vec2d add(double x, double y) {
    this.x += x;
    this.y += y;
    return this;
  }

  /**
   *  Adds a scalar to each component, in place. Returns this vector.
   */
  public Vec2d add(double s) {
    return add(s, s);
  }

  /**
   *  Component-wise subtraction, in place. Returns this vector.
   */
  public Vec2d subtract(ReadableVec2d v) {
    return subtract(v.x(), v.y());
  }

  /**
   *  Component-wise subtraction, in place. Returns this vector.
   */
  public Vec2d subtract(double x, double y) {
    this.x -= x;
    this.y -= y;
    return this;
  }

  /**
   *  Subtracts a scalar from each component, in place. Returns this vector.
   */
  public Vec2d subtract(double s) {
    return subtract(s, s);
  }

  /**
   *  Component-wise multiplication, in place. Returns this vector.
   */
  public Vec2d multiply(ReadableVec2d v) {
    return multiply(v.x(), v.y());
  }

  /**
   *  Component-wise multiplication, in place. Returns this vector.
   */
  public Vec2d multiply(double x, double y) {
    this.x *= x;
    this.y *= y;
    return this;
  }

  /**
   *  Multiplies a scalar with each component, in place. Returns this vector.
   */
  public Vec2d multiply(double s) {
    return multiply(s, s);
  }

  /**
   *  Component-wise division, in place. Returns this vector.
   */
  public Vec2d divide(ReadableVec2d v) {
    return divide(v.x(), v.y());
  }

  /**
   *  Component-wise division, in place. Returns this vector.
   */
  public Vec2d divide(double x, double y) {
    this.x /= x;
    this.y /= y;
    return this;
  }

  /**
   *  Divides each component by a scalar, in place. Returns this vector.
   */
  public Vec2d divide(double s) {
    return divide(s, s);
  }

  /**
   *  Scales the length of this vector to 1, in place. Returns this vector.
   */
  public Vec2d normalize() {
    return divide(length());
  }

  /**
   * Rotates this vector counter-clockwise by degrees, in place. Returns this vector.
   */
  public Vec2d rotateDegrees(double degrees) {
    return rotateRadians(Math.toRadians(degrees));
  }

  /**
   * Rotates this vector counter-clockwise by radians, in place. Returns this vector.
   */
  public Vec2d rotateRadians(double radians) {
    double c = Math.cos(radians);
    double s = Math.sin(radians);
    double xNew = c * x - s * y;
    y = s * x + c * y;
    x = xNew;
    return this;
  }

  /**
   * Rotates this vector counter-clockwise by 90 degrees, in place. Returns this vector.
   */
  public Vec2d rotateDegrees90() {
    double temp = x;
    x = -y;
    y = temp;
    return this;
  }

  /**
   * Rotates this vector counter-clockwise by 180 degrees, in place. Returns this vector.
   */
  public Vec2d rotateDegrees180() {
    return negate();
  }

  /**
   * Rotates this vector counter-clockwise by 270 degrees, in place. Returns this vector.
   */
  public Vec2d rotateDegrees270() {
    double temp = x;
    x = y;
    y = -temp;
    return this;
  }

  /**
   * Negates this vector's components, in place. Returns this vector.
   */
  public Vec2d negate() {
    x = -x;
    y = -y;
    return this;
  }

  /**
   * Reflects this vector across a surface normal, in place. Returns this vector.
   */
  public Vec2d reflect(ReadableVec2d n) {
    Vec2d v = reflected(n);
    x = v.x;
    y = v.y;
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
  public Vec2d plus(ReadableVec2d v) {
    return copy().add(v);
  }

  @Override
  public Vec2d plus(double x, double y) {
    return copy().add(x, y);
  }

  @Override
  public Vec2d plus(double s) {
    return copy().add(s);
  }

  @Override
  public Vec2d minus(ReadableVec2d v) {
    return copy().subtract(v);
  }

  @Override
  public Vec2d minus(double x, double y) {
    return copy().subtract(x, y);
  }

  @Override
  public Vec2d minus(double s) {
    return copy().subtract(s);
  }

  @Override
  public Vec2d times(ReadableVec2d v) {
    return copy().multiply(v);
  }

  @Override
  public Vec2d times(double x, double y) {
    return copy().multiply(x, y);
  }

  @Override
  public Vec2d times(double s) {
    return copy().multiply(s);
  }

  @Override
  public Vec2d over(ReadableVec2d v) {
    return copy().divide(v);
  }

  @Override
  public Vec2d over(double x, double y) {
    return copy().divide(x, y);
  }

  @Override
  public Vec2d over(double s) {
    return copy().divide(s);
  }

  @Override
  public double dot(ReadableVec2d v) {
    return x * x + y * y;
  }

  @Override
  public double cross(ReadableVec2d v) {
    return x * v.y() - y * v.x();
  }

  @Override
  public Vec2d rotatedDegrees90() {
    return copy().rotateDegrees90();
  }

  @Override
  public Vec2d rotatedDegrees180() {
    return copy().rotateDegrees180();
  }

  @Override
  public Vec2d rotatedDegrees270() {
    return copy().rotateDegrees270();
  }

  @Override
  public Vec2d rotatedDegrees(double degrees) {
    return copy().rotateDegrees(degrees);
  }

  @Override
  public Vec2d rotatedRadians(double radians) {
    return copy().rotateRadians(radians);
  }

  @Override
  public Vec2d reflected(ReadableVec2d n) {
    return this.minus(n.times(2.0 * n.dot(this)));
  }

  @Override
  public Vec2d normalized() {
    return copy().normalize();
  }

  @Override
  public Vec2d negated() {
    return copy().negate();
  }

  @Override
  public double anglePi() {
    return Math.atan2(y, x);
  }

  @Override
  public double angle2Pi() {
    double radians = Math.atan2(y, x);
    return (radians < 0 ? Math.PI * 2.0 + radians : radians);
  }

  @Override
  public double angle180() {
    return Math.toDegrees(Math.atan2(y, x));
  }

  @Override
  public double angle360() {
    double radians = Math.atan2(y, x);
    return Math.toDegrees(radians < 0 ? Math.PI * 2.0 + radians : radians);
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
    return new double[] { x, y };
  }

  @Override
  public Vec2d copy() {
    return new Vec2d(x, y);
  }
  
  @Override
  public void putInto(DoubleBuffer buf) {
    buf.put(x);
    buf.put(y);
  }
  
  /**
   * Creates a unit vector along the x axis (1, 0).
   */
  public static Vec2d axisX() {
    return new Vec2d(1, 0);
  }
  
  /**
   * Creates a unit vector along the y axis (0, 1).
   */
  public static Vec2d axisY() {
    return new Vec2d(0, 1);
  }
  
  // SWIZZLING OPERATIONS
  // ==============================================================================================
  public final Vec2d xx() { return new Vec2d(x, x); }
  public final Vec2d xy() { return new Vec2d(x, y); }
  public final Vec2d yx() { return new Vec2d(y, x); }
  public final Vec2d yy() { return new Vec2d(y, y); }
  // ==============================================================================================
}