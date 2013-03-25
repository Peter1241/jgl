/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

import java.nio.FloatBuffer;

/**
 * Immutable interface for a Vec2f.
 * 
 * @author justin
 */
public interface ReadableVec2f extends Swizzle2<Vec2f> {

  /**
   *  The first component.
   */
  float x();

  /**
   *  The second component.
   */
  float y();

  /**
   *  Component-wise addition. Creates a new vector.
   */
  Vec2f plus(ReadableVec2f v);

  /**
   *  Component-wise addition. Creates a new vector.
   */
  Vec2f plus(float x, float y);

  /**
   *  Adds scalar to each component. Creates a new vector.
   */
  Vec2f plus(float s);

  /**
   *  Component-wise subtraction. Creates a new vector.
   */
  Vec2f minus(ReadableVec2f v);

  /**
   *  Component-wise subtraction. Creates a new vector.
   */
  Vec2f minus(float x, float y);

  /**
   *  Subtracts scalar from each component. Creates a new vector.
   */
  Vec2f minus(float s);

  /**
   *  Component-wise multiplication. Creates a new vector.
   */
  Vec2f times(ReadableVec2f v);

  /**
   *  Component-wise multiplication. Creates a new vector.
   */
  Vec2f times(float x, float y);

  /**
   *  Multiplies scalar with each component. Creates a new vector.
   */
  Vec2f times(float s);

  /**
   *  Component-wise division. Creates a new vector.
   */
  Vec2f over(ReadableVec2f v);

  /**
   *  Component-wise division. Creates a new vector.
   */
  Vec2f over(float x, float y);

  /**
   *  Divides each component by scalar. Creates a new vector.
   */
  Vec2f over(float s);

  /**
   *  Dot product: sum of squared components.
   */
  float dot(ReadableVec2f v);

  /**
   *  The 2D cross product: (this) x (v).
   */
  float cross(ReadableVec2f v);

  /**
   *  This vector rotated 90 degrees counter-clockwise. Creates a new vector.
   */
  Vec2f rotatedDegrees90();

  /**
   *  This vector rotated 180 degrees counter-clockwise. Creates a new vector.
   */
  Vec2f rotatedDegrees180();

  /**
   *  This vector rotated 270 degrees counter-clockwise. Creates a new vector.
   */
  Vec2f rotatedDegrees270();

  /**
   *  This vector rotated by degrees counter-clockwise. Creates a new vector.
   */
  Vec2f rotatedDegrees(double degrees);

  /**
   *  This vector rotated by radians counter-clockwise. Creates a new vector.
   */
  Vec2f rotatedRadians(double radians);

  /**
   *  This vector reflected across a surface with normal n. Creates a new vector.
   */
  Vec2f reflected(ReadableVec2f n);

  /**
   *  This vector with length 1. Creates a new vector.
   */
  Vec2f normalized();

  /**
   *  This vector with its components negated. Creates a new vector.
   */
  Vec2f negated();

  /**
   *  The angle of the vector (around Z axis) in [-pi, pi].
   */
  float anglePi();

  /**
   *  The angle of the vector (around Z axis) in [0, 2pi].
   */
  float angle2Pi();

  /**
   *  The angle of the vector (around Z axis) in [-180, 180].
   */
  float angle180();

  /**
   *  The angle of the vector (around Z axis) in [0, 360].
   */
  float angle360();

  /**
   *  The length/magnitude of this vector.
   */
  float length();

  /**
   *  The squared length/magnitude of this vector.
   */
  float lengthSquared();

  /**
   *  A copy of the vector as an array.
   */
  float[] array();

  /**
   *  A copy of the vector that is mutable.
   */
  Vec2f copy();
  
  /**
   * Adds this vector's components to the end of a buffer.
   */
  void putInto(FloatBuffer buf);
}