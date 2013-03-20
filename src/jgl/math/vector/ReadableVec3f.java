/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

/**
 * Immutable interface for a Vec3f.
 * 
 * @author justin
 */
public interface ReadableVec3f extends Swizzle3<Vec2f, Vec3f> {
  
  /**
   *  The first component.
   */
  float x();

  /**
   *  The second component.
   */
  float y();
  
  /**
   *  The third component.
   */
  float z();
  
  /**
   *  Component-wise addition. Creates a new vector.
   */
  Vec3f plus(ReadableVec3f v);
  
  /**
   *  Component-wise addition. Creates a new vector.
   */
  Vec3f plus(float x, float y, float z);
  
  /**
   *  Adds scalar to each component. Creates a new vector.
   */
  Vec3f plus(float s);

  /**
   *  Component-wise subtraction. Creates a new vector.
   */
  Vec3f minus(ReadableVec3f v);
  
  /**
   *  Component-wise subtraction. Creates a new vector.
   */
  Vec3f minus(float x, float y, float z);
  
  /**
   *  Subtracts scalar from each component. Creates a new vector.
   */
  Vec3f minus(float s);

  /**
   *  Component-wise multiplication. Creates a new vector.
   */
  Vec3f times(ReadableVec3f v);
  
  /**
   *  Component-wise multiplication. Creates a new vector.
   */
  Vec3f times(float x, float y, float z);
  
  /**
   *  Multiplies scalar with each component. Creates a new vector.
   */
  Vec3f times(float s);

  /**
   *  Component-wise division. Creates a new vector.
   */
  Vec3f over(ReadableVec3f v);

  /**
   *  Component-wise division. Creates a new vector.
   */
  Vec3f over(float x, float y, float z);
  
  /**
   *  Divides each component by scalar. Creates a new vector.
   */
  Vec3f over(float s);
  
  /**
   *  Dot product: sum of squared components.
   */
  float dot(ReadableVec3f v);
  
  /**
   *  The 3D cross product: (this) x (v).
   */
  Vec3f cross(ReadableVec3f v);
  
  /**
   *  This vector reflected across a surface with normal n. Creates a
   * new vector.
   */
  Vec3f reflected(ReadableVec3f n);
  
  /**
   *  This vector with length 1. Creates a new vector.
   */
  Vec3f normalized();
  
  /**
   *  This vector with its components negated. Creates a new vector.
   */
  Vec3f negated();

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
  Vec3f copy();
}