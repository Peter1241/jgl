/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

import java.nio.FloatBuffer;

/**
 * Immutable interface for a Vec4f.
 * 
 * @author justin
 */
public interface ConstVec4f extends Swizzle4<Vec2f, Vec3f, Vec4f> {
  
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
   *  The fourth component.
   */
  float w();
  
  /**
   *  Component-wise addition. Creates a new vector.
   */
  Vec4f plus(ConstVec4f v);
  
  /**
   *  Component-wise addition. Creates a new vector.
   */
  Vec4f plus(float x, float y, float z, float w);
  
  /**
   *  Adds scalar to each component. Creates a new vector.
   */
  Vec4f plus(float s);

  /**
   *  Component-wise subtraction. Creates a new vector.
   */
  Vec4f minus(ConstVec4f v);
  
  /**
   *  Component-wise subtraction. Creates a new vector.
   */
  Vec4f minus(float x, float y, float z, float w);
  
  /**
   *  Subtracts scalar from each component. Creates a new vector.
   */
  Vec4f minus(float s);

  /**
   *  Component-wise multiplication. Creates a new vector.
   */
  Vec4f times(ConstVec4f v);
  
  /**
   *  Component-wise multiplication. Creates a new vector.
   */
  Vec4f times(float x, float y, float z, float w);
  
  /**
   *  Multiplies scalar with each component. Creates a new vector.
   */
  Vec4f times(float s);

  /**
   *  Component-wise division. Creates a new vector.
   */
  Vec4f over(ConstVec4f v);

  /**
   *  Component-wise division. Creates a new vector.
   */
  Vec4f over(float x, float y, float z, float w);
  
  /**
   *  Divides each component by scalar. Creates a new vector.
   */
  Vec4f over(float s);
  
  /**
   *  Dot product: sum of squared components.
   */
  float dot(ConstVec4f v);
  
  /**
   *  This vector with length 1. Creates a new vector.
   */
  Vec4f normalized();
  
  /**
   *  This vector with its components negated. Creates a new vector.
   */
  Vec4f negated();

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
  float[] toArray();
  
  /**
   *  A copy of the vector that is mutable.
   */
  Vec4f copy();
  
  /**
   * Adds this vector's components to the end of a buffer.
   */
  void putInto(FloatBuffer buf);
}