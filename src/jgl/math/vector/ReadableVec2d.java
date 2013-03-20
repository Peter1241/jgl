/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

/**
 * Immutable interface for a Vec2d.
 * 
 * @author justin
 */
public interface ReadableVec2d extends Swizzle2<Vec2d> {
  
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
  Vec2d plus(ReadableVec2d v);
  
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
  Vec2d minus(ReadableVec2d v);
  
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
  Vec2d times(ReadableVec2d v);
  
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
  Vec2d over(ReadableVec2d v);

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
  double dot(ReadableVec2d v);
  
  /**
   *  The 2D cross product: (this) x (v).
   */
  double cross(ReadableVec2d v);
  
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
  Vec2d reflected(ReadableVec2d n);
  
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
  double[] array();
  
  /**
   *  A copy of the vector that is mutable.
   */
  Vec2d copy();
}