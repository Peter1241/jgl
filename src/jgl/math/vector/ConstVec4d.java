/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

import java.nio.DoubleBuffer;

/**
 * Immutable interface for a Vec4d.
 * 
 * @author justin
 */
public interface ConstVec4d extends Swizzle4<Vec2d, Vec3d, Vec4d> {
  
  /**
   *  The first component.
   */
  double x();

  /**
   *  The second component.
   */
  double y();
  
  /**
   *  The third component.
   */
  double z();
  
  /**
   *  The fourth component.
   */
  double w();
  
  /**
   *  Component-wise addition. Creates a new vector.
   */
  Vec4d plus(ConstVec4d v);
  
  /**
   *  Component-wise addition. Creates a new vector.
   */
  Vec4d plus(double x, double y, double z, double w);
  
  /**
   *  Adds scalar to each component. Creates a new vector.
   */
  Vec4d plus(double s);

  /**
   *  Component-wise subtraction. Creates a new vector.
   */
  Vec4d minus(ConstVec4d v);
  
  /**
   *  Component-wise subtraction. Creates a new vector.
   */
  Vec4d minus(double x, double y, double z, double w);
  
  /**
   *  Subtracts scalar from each component. Creates a new vector.
   */
  Vec4d minus(double s);

  /**
   *  Component-wise multiplication. Creates a new vector.
   */
  Vec4d times(ConstVec4d v);
  
  /**
   *  Component-wise multiplication. Creates a new vector.
   */
  Vec4d times(double x, double y, double z, double w);
  
  /**
   *  Multiplies scalar with each component. Creates a new vector.
   */
  Vec4d times(double s);

  /**
   *  Component-wise division. Creates a new vector.
   */
  Vec4d over(ConstVec4d v);

  /**
   *  Component-wise division. Creates a new vector.
   */
  Vec4d over(double x, double y, double z, double w);
  
  /**
   *  Divides each component by scalar. Creates a new vector.
   */
  Vec4d over(double s);
  
  /**
   *  Dot product: sum of squared components.
   */
  double dot(ConstVec4d v);
  
  /**
   *  This vector with length 1. Creates a new vector.
   */
  Vec4d normalized();
  
  /**
   *  This vector with its components negated. Creates a new vector.
   */
  Vec4d negated();

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
  Vec4d copy();
  
  /**
   * A copy of the vector with its components casted to floats.
   */
  Vec4f toFloat();
  
  /**
   * Adds this vector's components to the end of a buffer.
   */
  void putInto(DoubleBuffer buf);
}