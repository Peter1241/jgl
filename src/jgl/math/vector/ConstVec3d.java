/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

import java.nio.DoubleBuffer;

/**
 * Immutable interface for a Vec3d.
 * 
 * @author justin
 */
public interface ConstVec3d extends Swizzle3<Vec2d, Vec3d> {
  
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
   *  Component-wise addition. Creates a new vector.
   */
  Vec3d plus(ConstVec3d v);
  
  /**
   *  Component-wise addition. Creates a new vector.
   */
  Vec3d plus(double x, double y, double z);
  
  /**
   *  Adds scalar to each component. Creates a new vector.
   */
  Vec3d plus(double s);

  /**
   *  Component-wise subtraction. Creates a new vector.
   */
  Vec3d minus(ConstVec3d v);
  
  /**
   *  Component-wise subtraction. Creates a new vector.
   */
  Vec3d minus(double x, double y, double z);
  
  /**
   *  Subtracts scalar from each component. Creates a new vector.
   */
  Vec3d minus(double s);

  /**
   *  Component-wise multiplication. Creates a new vector.
   */
  Vec3d times(ConstVec3d v);
  
  /**
   *  Component-wise multiplication. Creates a new vector.
   */
  Vec3d times(double x, double y, double z);
  
  /**
   *  Multiplies scalar with each component. Creates a new vector.
   */
  Vec3d times(double s);

  /**
   *  Component-wise division. Creates a new vector.
   */
  Vec3d over(ConstVec3d v);

  /**
   *  Component-wise division. Creates a new vector.
   */
  Vec3d over(double x, double y, double z);
  
  /**
   *  Divides each component by scalar. Creates a new vector.
   */
  Vec3d over(double s);
  
  /**
   *  Dot product: sum of squared components.
   */
  double dot(ConstVec3d v);
  
  /**
   *  The 3D cross product: (this) x (v).
   */
  Vec3d cross(ConstVec3d v);
  
  /**
   *  This vector reflected across a surface with normal n. Creates a
   * new vector.
   */
  Vec3d reflected(ConstVec3d n);
  
  /**
   *  This vector with length 1. Creates a new vector.
   */
  Vec3d normalized();
  
  /**
   *  This vector with its components negated. Creates a new vector.
   */
  Vec3d negated();

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
  Vec3d copy();
  
  /**
   * A copy of the vector with its components casted to floats.
   */
  Vec3f toFloat();
  
  /**
   * Adds this vector's components to the end of a buffer.
   */
  void putInto(DoubleBuffer buf);
}