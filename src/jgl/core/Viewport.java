/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.core;

import java.awt.Point;

import javax.media.opengl.GL;

import jgl.math.vector.ReadableVec4f;
import jgl.math.vector.Vec3f;

/**
 * Defines a rectangle that transforms normalized device coordinates to window coordinates.
 * 
 * @author justin
 */
public class Viewport {

  /** Left side of the rectangle */
  public int x;

  /** Right side of the rectangle */
  public int y;

  /** Width of rectangle */
  public int width;

  /** Height of rectangle */
  public int height;

  /**
   * Creates a new viewport.
   */
  public Viewport(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * X-coordinate of the left of the viewport.
   */
  public int left() {
    return x;
  }

  /**
   * X-coordinate of the right of the viewport.
   */
  public int right() {
    return x + width;
  }

  /**
   * Y-coordinate of the bottom of the viewport.
   */
  public int bottom() {
    return y;
  }

  /**
   * Y-coordinate of the top of the viewport.
   */
  public int top() {
    return y + height;
  }

  /**
   * Aspect ratio: width / height
   */
  public float aspect() {
    return (float) width / height;
  }

  /**
   * Checks if a point in window coordinates is inside the viewport.
   */
  public boolean contains(Point p) {
    return p.x >= x && p.x <= right() && p.y >= y && p.y <= top();
  }

  /**
   * Sets the viewport transform in OpenGL.
   */
  public void apply(GL gl) {
    gl.glViewport(x, y, width, height);
  }

  /**
   * Transforms normalized device coordinates to window coordinates
   */
  public Vec3f transform(ReadableVec4f ndc, float near, float far) {
    float hW = width / 2;
    float hH = height / 2;
    float wx = hW * ndc.x() + (this.x + hW);
    float wy = hH * ndc.y() + (this.y + hH);
    float wz = (far - near) / 2 * ndc.z() + (far + near) / 2;
    return new Vec3f(wx, wy, wz);
  }

  public boolean equals(Viewport that) {
    if (that == null)
      return false;
    return x == that.x && y == that.y && width == that.width && height == that.height;
  }
}
