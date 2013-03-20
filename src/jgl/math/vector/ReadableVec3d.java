/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

/**
 * Immutable interface for a Vec3d.
 * 
 * @author justin
 */
public interface ReadableVec3d extends Swizzle3<Vec2d, Vec3d> {
  
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
  Vec3d plus(ReadableVec3d v);
  
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
  Vec3d minus(ReadableVec3d v);
  
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
  Vec3d times(ReadableVec3d v);
  
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
  Vec3d over(ReadableVec3d v);

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
  double dot(ReadableVec3d v);
  
  /**
   *  The 3D cross product: (this) x (v).
   */
  Vec3d cross(ReadableVec3d v);
  
  /**
   *  This vector reflected across a surface with normal n. Creates a
   * new vector.
   */
  Vec3d reflected(ReadableVec3d n);
  
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
  double[] array();
  
  /**
   *  A copy of the vector that is mutable.
   */
  Vec3d copy();
}