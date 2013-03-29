/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

import java.nio.DoubleBuffer;

/**
 * Immutable interface for a Vec2d.
 * 
 * @author justin
 */
public interface ConstVec2d extends Swizzle2<Vec2d> {
  
  /**
   *  The first component.
   */
  double x();

  /**
   *  The second component.
   */
  double y();
  
  /**
   *  Component-wise addition. Creates a new vector.
   */
  Vec2d plus(ConstVec2d v);
  
  /**
   *  Component-wise addition. Creates a new vector.
   */
  Vec2d plus(double x, double y);
  
  /**
   *  Adds scalar to each component. Creates a new vector.
   */
  Vec2d plus(double s);

  /**
   *  Component-wise subtraction. Creates a new vector.
   */
  Vec2d minus(ConstVec2d v);
  
  /**
   *  Component-wise subtraction. Creates a new vector.
   */
  Vec2d minus(double x, double y);
  
  /**
   *  Subtracts scalar from each component. Creates a new vector.
   */
  Vec2d minus(double s);

  /**
   *  Component-wise multiplication. Creates a new vector.
   */
  Vec2d times(ConstVec2d v);
  
  /**
   *  Component-wise multiplication. Creates a new vector.
   */
  Vec2d times(double x, double y);
  
  /**
   *  Multiplies scalar with each component. Creates a new vector.
   */
  Vec2d times(double s);

  /**
   *  Component-wise division. Creates a new vector.
   */
  Vec2d over(ConstVec2d v);

  /**
   *  Component-wise division. Creates a new vector.
   */
  Vec2d over(double x, double y);
  
  /**
   *  Divides each component by scalar. Creates a new vector.
   */
  Vec2d over(double s);
  
  /**
   *  Dot product: sum of squared components.
   */
  double dot(ConstVec2d v);
  
  /**
   *  The 2D cross product: (this) x (v).
   */
  double cross(ConstVec2d v);
  
  /**
   *  This vector rotated 90 degrees counter-clockwise. Creates a new vector.
   */
  Vec2d rotatedDegrees90();
  
  /**
   *  This vector rotated 180 degrees counter-clockwise. Creates a new vector.
   */
  Vec2d rotatedDegrees180();

  /**
   *  This vector rotated 270 degrees counter-clockwise. Creates a new vector.
   */
  Vec2d rotatedDegrees270();
  
  /**
   *  This vector rotated by degrees counter-clockwise. Creates a new vector.
   */
  Vec2d rotatedDegrees(double degrees);
  
  /**
   *  This vector rotated by radians counter-clockwise. Creates a new vector.
   */
  Vec2d rotatedRadians(double radians);
  
  /**
   *  This vector reflected across a surface with normal n. Creates a
   * new vector.
   */
  Vec2d reflected(ConstVec2d n);
  
  /**
   *  This vector with length 1. Creates a new vector.
   */
  Vec2d normalized();
  
  /**
   *  This vector with its components negated. Creates a new vector.
   */
  Vec2d negated();

  /**
   *  The angle of the vector (around Z axis) in [-pi, pi].
   */
  double anglePi();
  
  /**
   *  The angle of the vector (around Z axis) in [0, 2pi].
   */
  double angle2Pi();
  
  /**
   *  The angle of the vector (around Z axis) in [-180, 180].
   */
  double angle180();

  /**
   *  The angle of the vector (around Z axis) in [0, 360].
   */
  double angle360();
  
  /**
   *  The length/magnitude of this vector.
   */
  double length();

  /**
   *  The squared length/magnitude of this vector.
   */
  double lengthSquared();

  /**
   *  A copy of the vector as an array.
   */
  double[] toArray();
  
  /**
   *  A copy of the vector that is mutable.
   */
  Vec2d copy();
  
  /**
   * A copy of the vector with its components casted to floats.
   */
  Vec2f toFloat();
  
  /**
   * Adds this vector's components to the end of a buffer.
   */
  void putInto(DoubleBuffer buf);
}