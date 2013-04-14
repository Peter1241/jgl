/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.core;

import java.awt.Point;

import javax.media.opengl.GL;

import jgl.math.vector.ConstVec2f;
import jgl.math.vector.Vec2f;

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
   * Transforms normalized device coordinates (NDC) to window coordinates. Normalized device
   * coordinates are in [-1, 1], where -1 is at the left/bottom and +1 is at the right/top; the
   * center of the viewport in NDC is (0,0). The window coordinates are use the bottom-left as the
   * origin.
   * 
   * @param ndcX - x-coordinate in normalized device space
   * @param ndcY - y-coordinate in normalized device space
   */
  public Point ndcToWindow(ConstVec2f ndc) {
    int winX = (int) (ndc.x() + 1) * (width / 2) + x;
    int winY = (int) (ndc.y() + 1) * (height / 2) + y;
    return new Point(winX, winY);
  }

  /**
   * Transform window space coordinates to normalized device coordinates (NDC). Normalized device
   * coordinates are in [-1, 1], where -1 is at the left/bottom and +1 is at the right/top; the
   * center of the viewport in NDC is (0,0). The window coordinates are use the bottom-left as the
   * origin.
   * 
   * @param winX - x-coordinate in window space
   * @param winY - y-coordinate in window space
   */
  public Vec2f windowToNDC(Point winCoords) {
    float ndcX = (float) (winCoords.x - x) / (width / 2) - 1;
    float ndcY = (float) (winCoords.y - y) / (height / 2) - 1;
    return new Vec2f(ndcX, ndcY);
  }

  public boolean equals(Viewport that) {
    if (that == null)
      return false;
    return x == that.x && y == that.y && width == that.width && height == that.height;
  }
}
