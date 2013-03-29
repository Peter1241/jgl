/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

import java.nio.FloatBuffer;

/**
 * 3D point/vector with float components.
 * 
 * @author justin
 */
public class Vec4f implements ConstVec4f {

  /** The first component. */
  public float x;

  /** The second component. */
  public float y;

  /** The third component. */
  public float z;

  /** The fourth component. */
  public float w;

  /**
   * Creates a vector with all values 0
   */
  public Vec4f() {
  }

  /**
   * Creates a vector using the values (x, y, z, w).
   */
  public Vec4f(float x, float y, float z, float w) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
  }

  /**
   * Creates a vector using the values (s, s, s, s).
   */
  public Vec4f(float s) {
    this(s, s, s, s);
  }

  /**
   * Creates a vector using the values (v.x, v.y, z, w).
   */
  public Vec4f(ConstVec2f v, float z, float w) {
    this(v.x(), v.y(), z, w);
  }
  
  /**
   * Creates a vector using the values (v.x, v.y, v.z, w).
   */
  public Vec4f(ConstVec3f v, float w) {
    this(v.x(), v.y(), v.z(), w);
  }
  
  /**
   * Creates a vector using the values (v.x, v.y, v.z, v.w).
   */
  public Vec4f(ConstVec4f v) {
    this(v.x(), v.y(), v.z(), v.w());
  }
  
  /**
   * Creates a vector using the values (a[0], a[1], a[2], a[3]).
   */
  public Vec4f(float[] a) {
    this(a[0], a[1], a[2], a[3]);
  }

  /**
   * Creates a vector using the values (a[start], a[start+1], a[start+2], a[start+3]).
   */
  public Vec4f(float[] a, int start) {
    this(a[start], a[start + 1], a[start + 2], a[start + 3]);
  }

  /**
   *  Component-wise addition, in place. Returns this vector.
   */
  public Vec4f add(ConstVec4f v) {
    return add(v.x(), v.y(), v.z(), v.w());
  }

  /**
   *  Component-wise addition, in place. Returns this vector.
   */
  public Vec4f add(float x, float y, float z, float w) {
    this.x += x;
    this.y += y;
    this.z += z;
    this.w += w;
    return this;
  }

  /**
   *  Adds a scalar to each component, in place. Returns this vector.
   */
  public Vec4f add(float s) {
    return add(s, s, s, s);
  }

  /**
   *  Component-wise subtraction, in place. Returns this vector.
   */
  public Vec4f subtract(ConstVec4f v) {
    return subtract(v.x(), v.y(), v.z(), v.w());
  }

  /**
   *  Component-wise subtraction, in place. Returns this vector.
   */
  public Vec4f subtract(float x, float y, float z, float w) {
    this.x -= x;
    this.y -= y;
    this.z -= z;
    this.w -= w;
    return this;
  }

  /**
   *  Subtracts a scalar from each component, in place. Returns this vector.
   */
  public Vec4f subtract(float s) {
    return subtract(s, s, s, s);
  }

  /**
   *  Component-wise multiplication, in place. Returns this vector.
   */
  public Vec4f multiply(ConstVec4f v) {
    return multiply(v.x(), v.y(), v.z(), v.w());
  }

  /**
   *  Component-wise multiplication, in place. Returns this vector.
   */
  public Vec4f multiply(float x, float y, float z, float w) {
    this.x *= x;
    this.y *= y;
    this.z *= z;
    this.w *= w;
    return this;
  }

  /**
   *  Multiplies a scalar with each component, in place. Returns this vector.
   */
  public Vec4f multiply(float s) {
    return multiply(s, s, s, s);
  }

  /**
   *  Component-wise division, in place. Returns this vector.
   */
  public Vec4f divide(ConstVec4f v) {
    return divide(v.x(), v.y(), v.z(), v.w());
  }

  /**
   *  Component-wise division, in place. Returns this vector.
   */
  public Vec4f divide(float x, float y, float z, float w) {
    this.x /= x;
    this.y /= y;
    this.z /= z;
    this.w /= w;
    return this;
  }

  /**
   *  Divides each component by a scalar, in place. Returns this vector.
   */
  public Vec4f divide(float s) {
    return divide(s, s, s, s);
  }

  /**
   *  Scales the length of this vector to 1, in place. Returns this vector.
   */
  public Vec4f normalize() {
    return divide(length());
  }

  /**
   * Negates this vector's components, in place. Returns this vector.
   */
  public Vec4f negate() {
    x = -x;
    y = -y;
    z = -z;
    w = -w;
    return this;
  }

  @Override
  public float x() {
    return x;
  }

  @Override
  public float y() {
    return y;
  }

  @Override
  public float z() {
    return z;
  }
  
  @Override
  public float w() {
    return w;
  }

  @Override
  public Vec4f plus(ConstVec4f v) {
    return copy().add(v);
  }

  @Override
  public Vec4f plus(float x, float y, float z, float w) {
    return copy().add(x, y, z, w);
  }

  @Override
  public Vec4f plus(float s) {
    return copy().add(s);
  }

  @Override
  public Vec4f minus(ConstVec4f v) {
    return copy().subtract(v);
  }

  @Override
  public Vec4f minus(float x, float y, float z, float w) {
    return copy().subtract(x, y, z, w);
  }

  @Override
  public Vec4f minus(float s) {
    return copy().subtract(s);
  }

  @Override
  public Vec4f times(ConstVec4f v) {
    return copy().multiply(v);
  }

  @Override
  public Vec4f times(float x, float y, float z, float w) {
    return copy().multiply(x, y, z, w);
  }

  @Override
  public Vec4f times(float s) {
    return copy().multiply(s);
  }

  @Override
  public Vec4f over(ConstVec4f v) {
    return copy().divide(v);
  }

  @Override
  public Vec4f over(float x, float y, float z, float w) {
    return copy().divide(x, y, z, w);
  }

  @Override
  public Vec4f over(float s) {
    return copy().divide(s);
  }

  @Override
  public float dot(ConstVec4f v) {
    return x * v.x() + y * v.y() + z * v.z() + w * v.w();
  }

  @Override
  public Vec4f normalized() {
    return copy().normalize();
  }

  @Override
  public Vec4f negated() {
    return copy().negate();
  }

  @Override
  public float length() {
    return (float) Math.sqrt(lengthSquared());
  }

  @Override
  public float lengthSquared() {
    return dot(this);
  }

  @Override
  public float[] toArray() {
    return new float[] { x, y, z, w };
  }
  
  @Override
  public String toString() {
    return String.format("{%.5f, %.5f, %.5f, %.5f}", x, y, z, w);
  }

  @Override
  public Vec4f copy() {
    return new Vec4f(x, y, z, w);
  }
  
  @Override
  public void putInto(FloatBuffer buf) {
    buf.put(x);
    buf.put(y);
    buf.put(z);
    buf.put(w);
  }
  
  /**
   * Creates a unit vector along the x axis (1, 0, 0, 0).
   */
  public static Vec4f axisX() {
    return new Vec4f(1, 0, 0, 0);
  }
  
  /**
   * Creates a unit vector along the y axis (0, 1, 0, 0).
   */
  public static Vec4f axisY() {
    return new Vec4f(0, 1, 0, 0);
  }
  
  /**
   * Creates a unit vector along the z axis (0, 0, 1, 0).
   */
  public static Vec4f axisZ() {
    return new Vec4f(0, 0, 1, 0);
  }
  
  /**
   * Creates a unit vector along the z axis (0, 0, 0, 1).
   */
  public static Vec4f axisW() {
    return new Vec4f(0, 0, 0, 1);
  }
  
  // SWIZZLING OPERATIONS
  // ==============================================================================================
  public final Vec2f xx() { return new Vec2f(x, x); }
  public final Vec2f xy() { return new Vec2f(x, y); }
  public final Vec2f xz() { return new Vec2f(x, z); }
  public final Vec2f xw() { return new Vec2f(x, w); }
  public final Vec2f yx() { return new Vec2f(y, x); }
  public final Vec2f yy() { return new Vec2f(y, y); }
  public final Vec2f yz() { return new Vec2f(y, z); }
  public final Vec2f yw() { return new Vec2f(y, w); }
  public final Vec2f zx() { return new Vec2f(z, x); }
  public final Vec2f zy() { return new Vec2f(z, y); }
  public final Vec2f zz() { return new Vec2f(z, z); }
  public final Vec2f zw() { return new Vec2f(z, w); }
  public final Vec2f wx() { return new Vec2f(w, x); }
  public final Vec2f wy() { return new Vec2f(w, y); }
  public final Vec2f wz() { return new Vec2f(w, z); }
  public final Vec2f ww() { return new Vec2f(w, w); }
  public final Vec3f xxx() { return new Vec3f(x, x, x); }
  public final Vec3f xxy() { return new Vec3f(x, x, y); }
  public final Vec3f xxz() { return new Vec3f(x, x, z); }
  public final Vec3f xxw() { return new Vec3f(x, x, w); }
  public final Vec3f xyx() { return new Vec3f(x, y, x); }
  public final Vec3f xyy() { return new Vec3f(x, y, y); }
  public final Vec3f xyz() { return new Vec3f(x, y, z); }
  public final Vec3f xyw() { return new Vec3f(x, y, w); }
  public final Vec3f xzx() { return new Vec3f(x, z, x); }
  public final Vec3f xzy() { return new Vec3f(x, z, y); }
  public final Vec3f xzz() { return new Vec3f(x, z, z); }
  public final Vec3f xzw() { return new Vec3f(x, z, w); }
  public final Vec3f xwx() { return new Vec3f(x, w, x); }
  public final Vec3f xwy() { return new Vec3f(x, w, y); }
  public final Vec3f xwz() { return new Vec3f(x, w, z); }
  public final Vec3f xww() { return new Vec3f(x, w, w); }
  public final Vec3f yxx() { return new Vec3f(y, x, x); }
  public final Vec3f yxy() { return new Vec3f(y, x, y); }
  public final Vec3f yxz() { return new Vec3f(y, x, z); }
  public final Vec3f yxw() { return new Vec3f(y, x, w); }
  public final Vec3f yyx() { return new Vec3f(y, y, x); }
  public final Vec3f yyy() { return new Vec3f(y, y, y); }
  public final Vec3f yyz() { return new Vec3f(y, y, z); }
  public final Vec3f yyw() { return new Vec3f(y, y, w); }
  public final Vec3f yzx() { return new Vec3f(y, z, x); }
  public final Vec3f yzy() { return new Vec3f(y, z, y); }
  public final Vec3f yzz() { return new Vec3f(y, z, z); }
  public final Vec3f yzw() { return new Vec3f(y, z, w); }
  public final Vec3f ywx() { return new Vec3f(y, w, x); }
  public final Vec3f ywy() { return new Vec3f(y, w, y); }
  public final Vec3f ywz() { return new Vec3f(y, w, z); }
  public final Vec3f yww() { return new Vec3f(y, w, w); }
  public final Vec3f zxx() { return new Vec3f(z, x, x); }
  public final Vec3f zxy() { return new Vec3f(z, x, y); }
  public final Vec3f zxz() { return new Vec3f(z, x, z); }
  public final Vec3f zxw() { return new Vec3f(z, x, w); }
  public final Vec3f zyx() { return new Vec3f(z, y, x); }
  public final Vec3f zyy() { return new Vec3f(z, y, y); }
  public final Vec3f zyz() { return new Vec3f(z, y, z); }
  public final Vec3f zyw() { return new Vec3f(z, y, w); }
  public final Vec3f zzx() { return new Vec3f(z, z, x); }
  public final Vec3f zzy() { return new Vec3f(z, z, y); }
  public final Vec3f zzz() { return new Vec3f(z, z, z); }
  public final Vec3f zzw() { return new Vec3f(z, z, w); }
  public final Vec3f zwx() { return new Vec3f(z, w, x); }
  public final Vec3f zwy() { return new Vec3f(z, w, y); }
  public final Vec3f zwz() { return new Vec3f(z, w, z); }
  public final Vec3f zww() { return new Vec3f(z, w, w); }
  public final Vec3f wxx() { return new Vec3f(w, x, x); }
  public final Vec3f wxy() { return new Vec3f(w, x, y); }
  public final Vec3f wxz() { return new Vec3f(w, x, z); }
  public final Vec3f wxw() { return new Vec3f(w, x, w); }
  public final Vec3f wyx() { return new Vec3f(w, y, x); }
  public final Vec3f wyy() { return new Vec3f(w, y, y); }
  public final Vec3f wyz() { return new Vec3f(w, y, z); }
  public final Vec3f wyw() { return new Vec3f(w, y, w); }
  public final Vec3f wzx() { return new Vec3f(w, z, x); }
  public final Vec3f wzy() { return new Vec3f(w, z, y); }
  public final Vec3f wzz() { return new Vec3f(w, z, z); }
  public final Vec3f wzw() { return new Vec3f(w, z, w); }
  public final Vec3f wwx() { return new Vec3f(w, w, x); }
  public final Vec3f wwy() { return new Vec3f(w, w, y); }
  public final Vec3f wwz() { return new Vec3f(w, w, z); }
  public final Vec3f www() { return new Vec3f(w, w, w); }
  public final Vec4f xxxx() { return new Vec4f(x, x, x, x); }
  public final Vec4f xxxy() { return new Vec4f(x, x, x, y); }
  public final Vec4f xxxz() { return new Vec4f(x, x, x, z); }
  public final Vec4f xxxw() { return new Vec4f(x, x, x, w); }
  public final Vec4f xxyx() { return new Vec4f(x, x, y, x); }
  public final Vec4f xxyy() { return new Vec4f(x, x, y, y); }
  public final Vec4f xxyz() { return new Vec4f(x, x, y, z); }
  public final Vec4f xxyw() { return new Vec4f(x, x, y, w); }
  public final Vec4f xxzx() { return new Vec4f(x, x, z, x); }
  public final Vec4f xxzy() { return new Vec4f(x, x, z, y); }
  public final Vec4f xxzz() { return new Vec4f(x, x, z, z); }
  public final Vec4f xxzw() { return new Vec4f(x, x, z, w); }
  public final Vec4f xxwx() { return new Vec4f(x, x, w, x); }
  public final Vec4f xxwy() { return new Vec4f(x, x, w, y); }
  public final Vec4f xxwz() { return new Vec4f(x, x, w, z); }
  public final Vec4f xxww() { return new Vec4f(x, x, w, w); }
  public final Vec4f xyxx() { return new Vec4f(x, y, x, x); }
  public final Vec4f xyxy() { return new Vec4f(x, y, x, y); }
  public final Vec4f xyxz() { return new Vec4f(x, y, x, z); }
  public final Vec4f xyxw() { return new Vec4f(x, y, x, w); }
  public final Vec4f xyyx() { return new Vec4f(x, y, y, x); }
  public final Vec4f xyyy() { return new Vec4f(x, y, y, y); }
  public final Vec4f xyyz() { return new Vec4f(x, y, y, z); }
  public final Vec4f xyyw() { return new Vec4f(x, y, y, w); }
  public final Vec4f xyzx() { return new Vec4f(x, y, z, x); }
  public final Vec4f xyzy() { return new Vec4f(x, y, z, y); }
  public final Vec4f xyzz() { return new Vec4f(x, y, z, z); }
  public final Vec4f xyzw() { return new Vec4f(x, y, z, w); }
  public final Vec4f xywx() { return new Vec4f(x, y, w, x); }
  public final Vec4f xywy() { return new Vec4f(x, y, w, y); }
  public final Vec4f xywz() { return new Vec4f(x, y, w, z); }
  public final Vec4f xyww() { return new Vec4f(x, y, w, w); }
  public final Vec4f xzxx() { return new Vec4f(x, z, x, x); }
  public final Vec4f xzxy() { return new Vec4f(x, z, x, y); }
  public final Vec4f xzxz() { return new Vec4f(x, z, x, z); }
  public final Vec4f xzxw() { return new Vec4f(x, z, x, w); }
  public final Vec4f xzyx() { return new Vec4f(x, z, y, x); }
  public final Vec4f xzyy() { return new Vec4f(x, z, y, y); }
  public final Vec4f xzyz() { return new Vec4f(x, z, y, z); }
  public final Vec4f xzyw() { return new Vec4f(x, z, y, w); }
  public final Vec4f xzzx() { return new Vec4f(x, z, z, x); }
  public final Vec4f xzzy() { return new Vec4f(x, z, z, y); }
  public final Vec4f xzzz() { return new Vec4f(x, z, z, z); }
  public final Vec4f xzzw() { return new Vec4f(x, z, z, w); }
  public final Vec4f xzwx() { return new Vec4f(x, z, w, x); }
  public final Vec4f xzwy() { return new Vec4f(x, z, w, y); }
  public final Vec4f xzwz() { return new Vec4f(x, z, w, z); }
  public final Vec4f xzww() { return new Vec4f(x, z, w, w); }
  public final Vec4f xwxx() { return new Vec4f(x, w, x, x); }
  public final Vec4f xwxy() { return new Vec4f(x, w, x, y); }
  public final Vec4f xwxz() { return new Vec4f(x, w, x, z); }
  public final Vec4f xwxw() { return new Vec4f(x, w, x, w); }
  public final Vec4f xwyx() { return new Vec4f(x, w, y, x); }
  public final Vec4f xwyy() { return new Vec4f(x, w, y, y); }
  public final Vec4f xwyz() { return new Vec4f(x, w, y, z); }
  public final Vec4f xwyw() { return new Vec4f(x, w, y, w); }
  public final Vec4f xwzx() { return new Vec4f(x, w, z, x); }
  public final Vec4f xwzy() { return new Vec4f(x, w, z, y); }
  public final Vec4f xwzz() { return new Vec4f(x, w, z, z); }
  public final Vec4f xwzw() { return new Vec4f(x, w, z, w); }
  public final Vec4f xwwx() { return new Vec4f(x, w, w, x); }
  public final Vec4f xwwy() { return new Vec4f(x, w, w, y); }
  public final Vec4f xwwz() { return new Vec4f(x, w, w, z); }
  public final Vec4f xwww() { return new Vec4f(x, w, w, w); }
  public final Vec4f yxxx() { return new Vec4f(y, x, x, x); }
  public final Vec4f yxxy() { return new Vec4f(y, x, x, y); }
  public final Vec4f yxxz() { return new Vec4f(y, x, x, z); }
  public final Vec4f yxxw() { return new Vec4f(y, x, x, w); }
  public final Vec4f yxyx() { return new Vec4f(y, x, y, x); }
  public final Vec4f yxyy() { return new Vec4f(y, x, y, y); }
  public final Vec4f yxyz() { return new Vec4f(y, x, y, z); }
  public final Vec4f yxyw() { return new Vec4f(y, x, y, w); }
  public final Vec4f yxzx() { return new Vec4f(y, x, z, x); }
  public final Vec4f yxzy() { return new Vec4f(y, x, z, y); }
  public final Vec4f yxzz() { return new Vec4f(y, x, z, z); }
  public final Vec4f yxzw() { return new Vec4f(y, x, z, w); }
  public final Vec4f yxwx() { return new Vec4f(y, x, w, x); }
  public final Vec4f yxwy() { return new Vec4f(y, x, w, y); }
  public final Vec4f yxwz() { return new Vec4f(y, x, w, z); }
  public final Vec4f yxww() { return new Vec4f(y, x, w, w); }
  public final Vec4f yyxx() { return new Vec4f(y, y, x, x); }
  public final Vec4f yyxy() { return new Vec4f(y, y, x, y); }
  public final Vec4f yyxz() { return new Vec4f(y, y, x, z); }
  public final Vec4f yyxw() { return new Vec4f(y, y, x, w); }
  public final Vec4f yyyx() { return new Vec4f(y, y, y, x); }
  public final Vec4f yyyy() { return new Vec4f(y, y, y, y); }
  public final Vec4f yyyz() { return new Vec4f(y, y, y, z); }
  public final Vec4f yyyw() { return new Vec4f(y, y, y, w); }
  public final Vec4f yyzx() { return new Vec4f(y, y, z, x); }
  public final Vec4f yyzy() { return new Vec4f(y, y, z, y); }
  public final Vec4f yyzz() { return new Vec4f(y, y, z, z); }
  public final Vec4f yyzw() { return new Vec4f(y, y, z, w); }
  public final Vec4f yywx() { return new Vec4f(y, y, w, x); }
  public final Vec4f yywy() { return new Vec4f(y, y, w, y); }
  public final Vec4f yywz() { return new Vec4f(y, y, w, z); }
  public final Vec4f yyww() { return new Vec4f(y, y, w, w); }
  public final Vec4f yzxx() { return new Vec4f(y, z, x, x); }
  public final Vec4f yzxy() { return new Vec4f(y, z, x, y); }
  public final Vec4f yzxz() { return new Vec4f(y, z, x, z); }
  public final Vec4f yzxw() { return new Vec4f(y, z, x, w); }
  public final Vec4f yzyx() { return new Vec4f(y, z, y, x); }
  public final Vec4f yzyy() { return new Vec4f(y, z, y, y); }
  public final Vec4f yzyz() { return new Vec4f(y, z, y, z); }
  public final Vec4f yzyw() { return new Vec4f(y, z, y, w); }
  public final Vec4f yzzx() { return new Vec4f(y, z, z, x); }
  public final Vec4f yzzy() { return new Vec4f(y, z, z, y); }
  public final Vec4f yzzz() { return new Vec4f(y, z, z, z); }
  public final Vec4f yzzw() { return new Vec4f(y, z, z, w); }
  public final Vec4f yzwx() { return new Vec4f(y, z, w, x); }
  public final Vec4f yzwy() { return new Vec4f(y, z, w, y); }
  public final Vec4f yzwz() { return new Vec4f(y, z, w, z); }
  public final Vec4f yzww() { return new Vec4f(y, z, w, w); }
  public final Vec4f ywxx() { return new Vec4f(y, w, x, x); }
  public final Vec4f ywxy() { return new Vec4f(y, w, x, y); }
  public final Vec4f ywxz() { return new Vec4f(y, w, x, z); }
  public final Vec4f ywxw() { return new Vec4f(y, w, x, w); }
  public final Vec4f ywyx() { return new Vec4f(y, w, y, x); }
  public final Vec4f ywyy() { return new Vec4f(y, w, y, y); }
  public final Vec4f ywyz() { return new Vec4f(y, w, y, z); }
  public final Vec4f ywyw() { return new Vec4f(y, w, y, w); }
  public final Vec4f ywzx() { return new Vec4f(y, w, z, x); }
  public final Vec4f ywzy() { return new Vec4f(y, w, z, y); }
  public final Vec4f ywzz() { return new Vec4f(y, w, z, z); }
  public final Vec4f ywzw() { return new Vec4f(y, w, z, w); }
  public final Vec4f ywwx() { return new Vec4f(y, w, w, x); }
  public final Vec4f ywwy() { return new Vec4f(y, w, w, y); }
  public final Vec4f ywwz() { return new Vec4f(y, w, w, z); }
  public final Vec4f ywww() { return new Vec4f(y, w, w, w); }
  public final Vec4f zxxx() { return new Vec4f(z, x, x, x); }
  public final Vec4f zxxy() { return new Vec4f(z, x, x, y); }
  public final Vec4f zxxz() { return new Vec4f(z, x, x, z); }
  public final Vec4f zxxw() { return new Vec4f(z, x, x, w); }
  public final Vec4f zxyx() { return new Vec4f(z, x, y, x); }
  public final Vec4f zxyy() { return new Vec4f(z, x, y, y); }
  public final Vec4f zxyz() { return new Vec4f(z, x, y, z); }
  public final Vec4f zxyw() { return new Vec4f(z, x, y, w); }
  public final Vec4f zxzx() { return new Vec4f(z, x, z, x); }
  public final Vec4f zxzy() { return new Vec4f(z, x, z, y); }
  public final Vec4f zxzz() { return new Vec4f(z, x, z, z); }
  public final Vec4f zxzw() { return new Vec4f(z, x, z, w); }
  public final Vec4f zxwx() { return new Vec4f(z, x, w, x); }
  public final Vec4f zxwy() { return new Vec4f(z, x, w, y); }
  public final Vec4f zxwz() { return new Vec4f(z, x, w, z); }
  public final Vec4f zxww() { return new Vec4f(z, x, w, w); }
  public final Vec4f zyxx() { return new Vec4f(z, y, x, x); }
  public final Vec4f zyxy() { return new Vec4f(z, y, x, y); }
  public final Vec4f zyxz() { return new Vec4f(z, y, x, z); }
  public final Vec4f zyxw() { return new Vec4f(z, y, x, w); }
  public final Vec4f zyyx() { return new Vec4f(z, y, y, x); }
  public final Vec4f zyyy() { return new Vec4f(z, y, y, y); }
  public final Vec4f zyyz() { return new Vec4f(z, y, y, z); }
  public final Vec4f zyyw() { return new Vec4f(z, y, y, w); }
  public final Vec4f zyzx() { return new Vec4f(z, y, z, x); }
  public final Vec4f zyzy() { return new Vec4f(z, y, z, y); }
  public final Vec4f zyzz() { return new Vec4f(z, y, z, z); }
  public final Vec4f zyzw() { return new Vec4f(z, y, z, w); }
  public final Vec4f zywx() { return new Vec4f(z, y, w, x); }
  public final Vec4f zywy() { return new Vec4f(z, y, w, y); }
  public final Vec4f zywz() { return new Vec4f(z, y, w, z); }
  public final Vec4f zyww() { return new Vec4f(z, y, w, w); }
  public final Vec4f zzxx() { return new Vec4f(z, z, x, x); }
  public final Vec4f zzxy() { return new Vec4f(z, z, x, y); }
  public final Vec4f zzxz() { return new Vec4f(z, z, x, z); }
  public final Vec4f zzxw() { return new Vec4f(z, z, x, w); }
  public final Vec4f zzyx() { return new Vec4f(z, z, y, x); }
  public final Vec4f zzyy() { return new Vec4f(z, z, y, y); }
  public final Vec4f zzyz() { return new Vec4f(z, z, y, z); }
  public final Vec4f zzyw() { return new Vec4f(z, z, y, w); }
  public final Vec4f zzzx() { return new Vec4f(z, z, z, x); }
  public final Vec4f zzzy() { return new Vec4f(z, z, z, y); }
  public final Vec4f zzzz() { return new Vec4f(z, z, z, z); }
  public final Vec4f zzzw() { return new Vec4f(z, z, z, w); }
  public final Vec4f zzwx() { return new Vec4f(z, z, w, x); }
  public final Vec4f zzwy() { return new Vec4f(z, z, w, y); }
  public final Vec4f zzwz() { return new Vec4f(z, z, w, z); }
  public final Vec4f zzww() { return new Vec4f(z, z, w, w); }
  public final Vec4f zwxx() { return new Vec4f(z, w, x, x); }
  public final Vec4f zwxy() { return new Vec4f(z, w, x, y); }
  public final Vec4f zwxz() { return new Vec4f(z, w, x, z); }
  public final Vec4f zwxw() { return new Vec4f(z, w, x, w); }
  public final Vec4f zwyx() { return new Vec4f(z, w, y, x); }
  public final Vec4f zwyy() { return new Vec4f(z, w, y, y); }
  public final Vec4f zwyz() { return new Vec4f(z, w, y, z); }
  public final Vec4f zwyw() { return new Vec4f(z, w, y, w); }
  public final Vec4f zwzx() { return new Vec4f(z, w, z, x); }
  public final Vec4f zwzy() { return new Vec4f(z, w, z, y); }
  public final Vec4f zwzz() { return new Vec4f(z, w, z, z); }
  public final Vec4f zwzw() { return new Vec4f(z, w, z, w); }
  public final Vec4f zwwx() { return new Vec4f(z, w, w, x); }
  public final Vec4f zwwy() { return new Vec4f(z, w, w, y); }
  public final Vec4f zwwz() { return new Vec4f(z, w, w, z); }
  public final Vec4f zwww() { return new Vec4f(z, w, w, w); }
  public final Vec4f wxxx() { return new Vec4f(w, x, x, x); }
  public final Vec4f wxxy() { return new Vec4f(w, x, x, y); }
  public final Vec4f wxxz() { return new Vec4f(w, x, x, z); }
  public final Vec4f wxxw() { return new Vec4f(w, x, x, w); }
  public final Vec4f wxyx() { return new Vec4f(w, x, y, x); }
  public final Vec4f wxyy() { return new Vec4f(w, x, y, y); }
  public final Vec4f wxyz() { return new Vec4f(w, x, y, z); }
  public final Vec4f wxyw() { return new Vec4f(w, x, y, w); }
  public final Vec4f wxzx() { return new Vec4f(w, x, z, x); }
  public final Vec4f wxzy() { return new Vec4f(w, x, z, y); }
  public final Vec4f wxzz() { return new Vec4f(w, x, z, z); }
  public final Vec4f wxzw() { return new Vec4f(w, x, z, w); }
  public final Vec4f wxwx() { return new Vec4f(w, x, w, x); }
  public final Vec4f wxwy() { return new Vec4f(w, x, w, y); }
  public final Vec4f wxwz() { return new Vec4f(w, x, w, z); }
  public final Vec4f wxww() { return new Vec4f(w, x, w, w); }
  public final Vec4f wyxx() { return new Vec4f(w, y, x, x); }
  public final Vec4f wyxy() { return new Vec4f(w, y, x, y); }
  public final Vec4f wyxz() { return new Vec4f(w, y, x, z); }
  public final Vec4f wyxw() { return new Vec4f(w, y, x, w); }
  public final Vec4f wyyx() { return new Vec4f(w, y, y, x); }
  public final Vec4f wyyy() { return new Vec4f(w, y, y, y); }
  public final Vec4f wyyz() { return new Vec4f(w, y, y, z); }
  public final Vec4f wyyw() { return new Vec4f(w, y, y, w); }
  public final Vec4f wyzx() { return new Vec4f(w, y, z, x); }
  public final Vec4f wyzy() { return new Vec4f(w, y, z, y); }
  public final Vec4f wyzz() { return new Vec4f(w, y, z, z); }
  public final Vec4f wyzw() { return new Vec4f(w, y, z, w); }
  public final Vec4f wywx() { return new Vec4f(w, y, w, x); }
  public final Vec4f wywy() { return new Vec4f(w, y, w, y); }
  public final Vec4f wywz() { return new Vec4f(w, y, w, z); }
  public final Vec4f wyww() { return new Vec4f(w, y, w, w); }
  public final Vec4f wzxx() { return new Vec4f(w, z, x, x); }
  public final Vec4f wzxy() { return new Vec4f(w, z, x, y); }
  public final Vec4f wzxz() { return new Vec4f(w, z, x, z); }
  public final Vec4f wzxw() { return new Vec4f(w, z, x, w); }
  public final Vec4f wzyx() { return new Vec4f(w, z, y, x); }
  public final Vec4f wzyy() { return new Vec4f(w, z, y, y); }
  public final Vec4f wzyz() { return new Vec4f(w, z, y, z); }
  public final Vec4f wzyw() { return new Vec4f(w, z, y, w); }
  public final Vec4f wzzx() { return new Vec4f(w, z, z, x); }
  public final Vec4f wzzy() { return new Vec4f(w, z, z, y); }
  public final Vec4f wzzz() { return new Vec4f(w, z, z, z); }
  public final Vec4f wzzw() { return new Vec4f(w, z, z, w); }
  public final Vec4f wzwx() { return new Vec4f(w, z, w, x); }
  public final Vec4f wzwy() { return new Vec4f(w, z, w, y); }
  public final Vec4f wzwz() { return new Vec4f(w, z, w, z); }
  public final Vec4f wzww() { return new Vec4f(w, z, w, w); }
  public final Vec4f wwxx() { return new Vec4f(w, w, x, x); }
  public final Vec4f wwxy() { return new Vec4f(w, w, x, y); }
  public final Vec4f wwxz() { return new Vec4f(w, w, x, z); }
  public final Vec4f wwxw() { return new Vec4f(w, w, x, w); }
  public final Vec4f wwyx() { return new Vec4f(w, w, y, x); }
  public final Vec4f wwyy() { return new Vec4f(w, w, y, y); }
  public final Vec4f wwyz() { return new Vec4f(w, w, y, z); }
  public final Vec4f wwyw() { return new Vec4f(w, w, y, w); }
  public final Vec4f wwzx() { return new Vec4f(w, w, z, x); }
  public final Vec4f wwzy() { return new Vec4f(w, w, z, y); }
  public final Vec4f wwzz() { return new Vec4f(w, w, z, z); }
  public final Vec4f wwzw() { return new Vec4f(w, w, z, w); }
  public final Vec4f wwwx() { return new Vec4f(w, w, w, x); }
  public final Vec4f wwwy() { return new Vec4f(w, w, w, y); }
  public final Vec4f wwwz() { return new Vec4f(w, w, w, z); }
  public final Vec4f wwww() { return new Vec4f(w, w, w, w); }
  // ==============================================================================================
}