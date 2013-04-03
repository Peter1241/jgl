/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.math.vector;

/**
 * Utility vector functions.
 * 
 * @author justin
 */
public class Vector {
  
  public static Vec2f lerp(Vec2f a, Vec2f b, float s) {
    return a.plus(b.minus(a).times(s));
  }
  
  public static Vec2d lerp(Vec2d a, Vec2d b, float s) {
    return a.plus(b.minus(a).times(s));
  }
  
  public static Vec3f lerp(Vec3f a, Vec3f b, float s) {
    return a.plus(b.minus(a).times(s));
  }
  
  public static Vec3d lerp(Vec3d a, Vec3d b, float s) {
    return a.plus(b.minus(a).times(s));
  }
  
  public static Vec4f lerp(Vec4f a, Vec4f b, float s) {
    return a.plus(b.minus(a).times(s));
  }
  
  public static Vec4d lerp(Vec4d a, Vec4d b, float s) {
    return a.plus(b.minus(a).times(s));
  }
}
