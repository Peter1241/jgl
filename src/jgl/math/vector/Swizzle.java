/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.math.vector;

/**
 * Interface for creating vectors from an existing vector's two components (swizzling).
 * 
 * @author justin
 */
interface Swizzle2<T> {
  /** Creates a vector with components (x, x) */
  T xx();

  /** Creates a vector with components (x, y) */
  T xy();

  /** Creates a vector with components (y, x) */
  T yx();

  /** Creates a vector with components (y, y) */
  T yy();
}

/**
 * Interface for creating vectors from an existing vector's three components (swizzling).
 * 
 * @author justin
 */
interface Swizzle3<T2, T3> {
  /** Creates a vector with components (x, x) */
  T2 xx();

  /** Creates a vector with components (x, y) */
  T2 xy();

  /** Creates a vector with components (x, z) */
  T2 xz();

  /** Creates a vector with components (y, x) */
  T2 yx();

  /** Creates a vector with components (y, y) */
  T2 yy();

  /** Creates a vector with components (y, z) */
  T2 yz();

  /** Creates a vector with components (z, x) */
  T2 zx();

  /** Creates a vector with components (z, y) */
  T2 zy();

  /** Creates a vector with components (z, z) */
  T2 zz();

  /** Creates a vector with components (x, x, x) */
  T3 xxx();

  /** Creates a vector with components (x, x, y) */
  T3 xxy();

  /** Creates a vector with components (x, x, z) */
  T3 xxz();

  /** Creates a vector with components (x, y, x) */
  T3 xyx();

  /** Creates a vector with components (x, y, y) */
  T3 xyy();

  /** Creates a vector with components (x, y, z) */
  T3 xyz();

  /** Creates a vector with components (x, z, x) */
  T3 xzx();

  /** Creates a vector with components (x, z, y) */
  T3 xzy();

  /** Creates a vector with components (x, z, z) */
  T3 xzz();

  /** Creates a vector with components (y, x, x) */
  T3 yxx();

  /** Creates a vector with components (y, x, y) */
  T3 yxy();

  /** Creates a vector with components (y, x, z) */
  T3 yxz();

  /** Creates a vector with components (y, y, x) */
  T3 yyx();

  /** Creates a vector with components (y, y, y) */
  T3 yyy();

  /** Creates a vector with components (y, y, z) */
  T3 yyz();

  /** Creates a vector with components (y, z, x) */
  T3 yzx();

  /** Creates a vector with components (y, z, y) */
  T3 yzy();

  /** Creates a vector with components (y, z, z) */
  T3 yzz();

  /** Creates a vector with components (z, x, x) */
  T3 zxx();

  /** Creates a vector with components (z, x, y) */
  T3 zxy();

  /** Creates a vector with components (z, x, z) */
  T3 zxz();

  /** Creates a vector with components (z, y, x) */
  T3 zyx();

  /** Creates a vector with components (z, y, y) */
  T3 zyy();

  /** Creates a vector with components (z, y, z) */
  T3 zyz();

  /** Creates a vector with components (z, z, x) */
  T3 zzx();

  /** Creates a vector with components (z, z, y) */
  T3 zzy();

  /** Creates a vector with components (z, z, z) */
  T3 zzz();
}

/**
 * Interface for creating vectors from an existing vector's four components (swizzling).
 * 
 * @author justin
 */
interface Swizzle4<T2, T3, T4> {
  /** Creates a vector with components (x, x) */
  T2 xx();

  /** Creates a vector with components (x, y) */
  T2 xy();

  /** Creates a vector with components (x, z) */
  T2 xz();

  /** Creates a vector with components (x, w) */
  T2 xw();

  /** Creates a vector with components (y, x) */
  T2 yx();

  /** Creates a vector with components (y, y) */
  T2 yy();

  /** Creates a vector with components (y, z) */
  T2 yz();

  /** Creates a vector with components (y, w) */
  T2 yw();

  /** Creates a vector with components (z, x) */
  T2 zx();

  /** Creates a vector with components (z, y) */
  T2 zy();

  /** Creates a vector with components (z, z) */
  T2 zz();

  /** Creates a vector with components (z, w) */
  T2 zw();

  /** Creates a vector with components (w, x) */
  T2 wx();

  /** Creates a vector with components (w, y) */
  T2 wy();

  /** Creates a vector with components (w, z) */
  T2 wz();

  /** Creates a vector with components (w, w) */
  T2 ww();

  /** Creates a vector with components (x, x, x) */
  T3 xxx();

  /** Creates a vector with components (x, x, y) */
  T3 xxy();

  /** Creates a vector with components (x, x, z) */
  T3 xxz();

  /** Creates a vector with components (x, x, w) */
  T3 xxw();

  /** Creates a vector with components (x, y, x) */
  T3 xyx();

  /** Creates a vector with components (x, y, y) */
  T3 xyy();

  /** Creates a vector with components (x, y, z) */
  T3 xyz();

  /** Creates a vector with components (x, y, w) */
  T3 xyw();

  /** Creates a vector with components (x, z, x) */
  T3 xzx();

  /** Creates a vector with components (x, z, y) */
  T3 xzy();

  /** Creates a vector with components (x, z, z) */
  T3 xzz();

  /** Creates a vector with components (x, z, w) */
  T3 xzw();

  /** Creates a vector with components (x, w, x) */
  T3 xwx();

  /** Creates a vector with components (x, w, y) */
  T3 xwy();

  /** Creates a vector with components (x, w, z) */
  T3 xwz();

  /** Creates a vector with components (x, w, w) */
  T3 xww();

  /** Creates a vector with components (y, x, x) */
  T3 yxx();

  /** Creates a vector with components (y, x, y) */
  T3 yxy();

  /** Creates a vector with components (y, x, z) */
  T3 yxz();

  /** Creates a vector with components (y, x, w) */
  T3 yxw();

  /** Creates a vector with components (y, y, x) */
  T3 yyx();

  /** Creates a vector with components (y, y, y) */
  T3 yyy();

  /** Creates a vector with components (y, y, z) */
  T3 yyz();

  /** Creates a vector with components (y, y, w) */
  T3 yyw();

  /** Creates a vector with components (y, z, x) */
  T3 yzx();

  /** Creates a vector with components (y, z, y) */
  T3 yzy();

  /** Creates a vector with components (y, z, z) */
  T3 yzz();

  /** Creates a vector with components (y, z, w) */
  T3 yzw();

  /** Creates a vector with components (y, w, x) */
  T3 ywx();

  /** Creates a vector with components (y, w, y) */
  T3 ywy();

  /** Creates a vector with components (y, w, z) */
  T3 ywz();

  /** Creates a vector with components (y, w, w) */
  T3 yww();

  /** Creates a vector with components (z, x, x) */
  T3 zxx();

  /** Creates a vector with components (z, x, y) */
  T3 zxy();

  /** Creates a vector with components (z, x, z) */
  T3 zxz();

  /** Creates a vector with components (z, x, w) */
  T3 zxw();

  /** Creates a vector with components (z, y, x) */
  T3 zyx();

  /** Creates a vector with components (z, y, y) */
  T3 zyy();

  /** Creates a vector with components (z, y, z) */
  T3 zyz();

  /** Creates a vector with components (z, y, w) */
  T3 zyw();

  /** Creates a vector with components (z, z, x) */
  T3 zzx();

  /** Creates a vector with components (z, z, y) */
  T3 zzy();

  /** Creates a vector with components (z, z, z) */
  T3 zzz();

  /** Creates a vector with components (z, z, w) */
  T3 zzw();

  /** Creates a vector with components (z, w, x) */
  T3 zwx();

  /** Creates a vector with components (z, w, y) */
  T3 zwy();

  /** Creates a vector with components (z, w, z) */
  T3 zwz();

  /** Creates a vector with components (z, w, w) */
  T3 zww();

  /** Creates a vector with components (w, x, x) */
  T3 wxx();

  /** Creates a vector with components (w, x, y) */
  T3 wxy();

  /** Creates a vector with components (w, x, z) */
  T3 wxz();

  /** Creates a vector with components (w, x, w) */
  T3 wxw();

  /** Creates a vector with components (w, y, x) */
  T3 wyx();

  /** Creates a vector with components (w, y, y) */
  T3 wyy();

  /** Creates a vector with components (w, y, z) */
  T3 wyz();

  /** Creates a vector with components (w, y, w) */
  T3 wyw();

  /** Creates a vector with components (w, z, x) */
  T3 wzx();

  /** Creates a vector with components (w, z, y) */
  T3 wzy();

  /** Creates a vector with components (w, z, z) */
  T3 wzz();

  /** Creates a vector with components (w, z, w) */
  T3 wzw();

  /** Creates a vector with components (w, w, x) */
  T3 wwx();

  /** Creates a vector with components (w, w, y) */
  T3 wwy();

  /** Creates a vector with components (w, w, z) */
  T3 wwz();

  /** Creates a vector with components (w, w, w) */
  T3 www();

  /** Creates a vector with components (x, x, x, x) */
  T4 xxxx();

  /** Creates a vector with components (x, x, x, y) */
  T4 xxxy();

  /** Creates a vector with components (x, x, x, z) */
  T4 xxxz();

  /** Creates a vector with components (x, x, x, w) */
  T4 xxxw();

  /** Creates a vector with components (x, x, y, x) */
  T4 xxyx();

  /** Creates a vector with components (x, x, y, y) */
  T4 xxyy();

  /** Creates a vector with components (x, x, y, z) */
  T4 xxyz();

  /** Creates a vector with components (x, x, y, w) */
  T4 xxyw();

  /** Creates a vector with components (x, x, z, x) */
  T4 xxzx();

  /** Creates a vector with components (x, x, z, y) */
  T4 xxzy();

  /** Creates a vector with components (x, x, z, z) */
  T4 xxzz();

  /** Creates a vector with components (x, x, z, w) */
  T4 xxzw();

  /** Creates a vector with components (x, x, w, x) */
  T4 xxwx();

  /** Creates a vector with components (x, x, w, y) */
  T4 xxwy();

  /** Creates a vector with components (x, x, w, z) */
  T4 xxwz();

  /** Creates a vector with components (x, x, w, w) */
  T4 xxww();

  /** Creates a vector with components (x, y, x, x) */
  T4 xyxx();

  /** Creates a vector with components (x, y, x, y) */
  T4 xyxy();

  /** Creates a vector with components (x, y, x, z) */
  T4 xyxz();

  /** Creates a vector with components (x, y, x, w) */
  T4 xyxw();

  /** Creates a vector with components (x, y, y, x) */
  T4 xyyx();

  /** Creates a vector with components (x, y, y, y) */
  T4 xyyy();

  /** Creates a vector with components (x, y, y, z) */
  T4 xyyz();

  /** Creates a vector with components (x, y, y, w) */
  T4 xyyw();

  /** Creates a vector with components (x, y, z, x) */
  T4 xyzx();

  /** Creates a vector with components (x, y, z, y) */
  T4 xyzy();

  /** Creates a vector with components (x, y, z, z) */
  T4 xyzz();

  /** Creates a vector with components (x, y, z, w) */
  T4 xyzw();

  /** Creates a vector with components (x, y, w, x) */
  T4 xywx();

  /** Creates a vector with components (x, y, w, y) */
  T4 xywy();

  /** Creates a vector with components (x, y, w, z) */
  T4 xywz();

  /** Creates a vector with components (x, y, w, w) */
  T4 xyww();

  /** Creates a vector with components (x, z, x, x) */
  T4 xzxx();

  /** Creates a vector with components (x, z, x, y) */
  T4 xzxy();

  /** Creates a vector with components (x, z, x, z) */
  T4 xzxz();

  /** Creates a vector with components (x, z, x, w) */
  T4 xzxw();

  /** Creates a vector with components (x, z, y, x) */
  T4 xzyx();

  /** Creates a vector with components (x, z, y, y) */
  T4 xzyy();

  /** Creates a vector with components (x, z, y, z) */
  T4 xzyz();

  /** Creates a vector with components (x, z, y, w) */
  T4 xzyw();

  /** Creates a vector with components (x, z, z, x) */
  T4 xzzx();

  /** Creates a vector with components (x, z, z, y) */
  T4 xzzy();

  /** Creates a vector with components (x, z, z, z) */
  T4 xzzz();

  /** Creates a vector with components (x, z, z, w) */
  T4 xzzw();

  /** Creates a vector with components (x, z, w, x) */
  T4 xzwx();

  /** Creates a vector with components (x, z, w, y) */
  T4 xzwy();

  /** Creates a vector with components (x, z, w, z) */
  T4 xzwz();

  /** Creates a vector with components (x, z, w, w) */
  T4 xzww();

  /** Creates a vector with components (x, w, x, x) */
  T4 xwxx();

  /** Creates a vector with components (x, w, x, y) */
  T4 xwxy();

  /** Creates a vector with components (x, w, x, z) */
  T4 xwxz();

  /** Creates a vector with components (x, w, x, w) */
  T4 xwxw();

  /** Creates a vector with components (x, w, y, x) */
  T4 xwyx();

  /** Creates a vector with components (x, w, y, y) */
  T4 xwyy();

  /** Creates a vector with components (x, w, y, z) */
  T4 xwyz();

  /** Creates a vector with components (x, w, y, w) */
  T4 xwyw();

  /** Creates a vector with components (x, w, z, x) */
  T4 xwzx();

  /** Creates a vector with components (x, w, z, y) */
  T4 xwzy();

  /** Creates a vector with components (x, w, z, z) */
  T4 xwzz();

  /** Creates a vector with components (x, w, z, w) */
  T4 xwzw();

  /** Creates a vector with components (x, w, w, x) */
  T4 xwwx();

  /** Creates a vector with components (x, w, w, y) */
  T4 xwwy();

  /** Creates a vector with components (x, w, w, z) */
  T4 xwwz();

  /** Creates a vector with components (x, w, w, w) */
  T4 xwww();

  /** Creates a vector with components (y, x, x, x) */
  T4 yxxx();

  /** Creates a vector with components (y, x, x, y) */
  T4 yxxy();

  /** Creates a vector with components (y, x, x, z) */
  T4 yxxz();

  /** Creates a vector with components (y, x, x, w) */
  T4 yxxw();

  /** Creates a vector with components (y, x, y, x) */
  T4 yxyx();

  /** Creates a vector with components (y, x, y, y) */
  T4 yxyy();

  /** Creates a vector with components (y, x, y, z) */
  T4 yxyz();

  /** Creates a vector with components (y, x, y, w) */
  T4 yxyw();

  /** Creates a vector with components (y, x, z, x) */
  T4 yxzx();

  /** Creates a vector with components (y, x, z, y) */
  T4 yxzy();

  /** Creates a vector with components (y, x, z, z) */
  T4 yxzz();

  /** Creates a vector with components (y, x, z, w) */
  T4 yxzw();

  /** Creates a vector with components (y, x, w, x) */
  T4 yxwx();

  /** Creates a vector with components (y, x, w, y) */
  T4 yxwy();

  /** Creates a vector with components (y, x, w, z) */
  T4 yxwz();

  /** Creates a vector with components (y, x, w, w) */
  T4 yxww();

  /** Creates a vector with components (y, y, x, x) */
  T4 yyxx();

  /** Creates a vector with components (y, y, x, y) */
  T4 yyxy();

  /** Creates a vector with components (y, y, x, z) */
  T4 yyxz();

  /** Creates a vector with components (y, y, x, w) */
  T4 yyxw();

  /** Creates a vector with components (y, y, y, x) */
  T4 yyyx();

  /** Creates a vector with components (y, y, y, y) */
  T4 yyyy();

  /** Creates a vector with components (y, y, y, z) */
  T4 yyyz();

  /** Creates a vector with components (y, y, y, w) */
  T4 yyyw();

  /** Creates a vector with components (y, y, z, x) */
  T4 yyzx();

  /** Creates a vector with components (y, y, z, y) */
  T4 yyzy();

  /** Creates a vector with components (y, y, z, z) */
  T4 yyzz();

  /** Creates a vector with components (y, y, z, w) */
  T4 yyzw();

  /** Creates a vector with components (y, y, w, x) */
  T4 yywx();

  /** Creates a vector with components (y, y, w, y) */
  T4 yywy();

  /** Creates a vector with components (y, y, w, z) */
  T4 yywz();

  /** Creates a vector with components (y, y, w, w) */
  T4 yyww();

  /** Creates a vector with components (y, z, x, x) */
  T4 yzxx();

  /** Creates a vector with components (y, z, x, y) */
  T4 yzxy();

  /** Creates a vector with components (y, z, x, z) */
  T4 yzxz();

  /** Creates a vector with components (y, z, x, w) */
  T4 yzxw();

  /** Creates a vector with components (y, z, y, x) */
  T4 yzyx();

  /** Creates a vector with components (y, z, y, y) */
  T4 yzyy();

  /** Creates a vector with components (y, z, y, z) */
  T4 yzyz();

  /** Creates a vector with components (y, z, y, w) */
  T4 yzyw();

  /** Creates a vector with components (y, z, z, x) */
  T4 yzzx();

  /** Creates a vector with components (y, z, z, y) */
  T4 yzzy();

  /** Creates a vector with components (y, z, z, z) */
  T4 yzzz();

  /** Creates a vector with components (y, z, z, w) */
  T4 yzzw();

  /** Creates a vector with components (y, z, w, x) */
  T4 yzwx();

  /** Creates a vector with components (y, z, w, y) */
  T4 yzwy();

  /** Creates a vector with components (y, z, w, z) */
  T4 yzwz();

  /** Creates a vector with components (y, z, w, w) */
  T4 yzww();

  /** Creates a vector with components (y, w, x, x) */
  T4 ywxx();

  /** Creates a vector with components (y, w, x, y) */
  T4 ywxy();

  /** Creates a vector with components (y, w, x, z) */
  T4 ywxz();

  /** Creates a vector with components (y, w, x, w) */
  T4 ywxw();

  /** Creates a vector with components (y, w, y, x) */
  T4 ywyx();

  /** Creates a vector with components (y, w, y, y) */
  T4 ywyy();

  /** Creates a vector with components (y, w, y, z) */
  T4 ywyz();

  /** Creates a vector with components (y, w, y, w) */
  T4 ywyw();

  /** Creates a vector with components (y, w, z, x) */
  T4 ywzx();

  /** Creates a vector with components (y, w, z, y) */
  T4 ywzy();

  /** Creates a vector with components (y, w, z, z) */
  T4 ywzz();

  /** Creates a vector with components (y, w, z, w) */
  T4 ywzw();

  /** Creates a vector with components (y, w, w, x) */
  T4 ywwx();

  /** Creates a vector with components (y, w, w, y) */
  T4 ywwy();

  /** Creates a vector with components (y, w, w, z) */
  T4 ywwz();

  /** Creates a vector with components (y, w, w, w) */
  T4 ywww();

  /** Creates a vector with components (z, x, x, x) */
  T4 zxxx();

  /** Creates a vector with components (z, x, x, y) */
  T4 zxxy();

  /** Creates a vector with components (z, x, x, z) */
  T4 zxxz();

  /** Creates a vector with components (z, x, x, w) */
  T4 zxxw();

  /** Creates a vector with components (z, x, y, x) */
  T4 zxyx();

  /** Creates a vector with components (z, x, y, y) */
  T4 zxyy();

  /** Creates a vector with components (z, x, y, z) */
  T4 zxyz();

  /** Creates a vector with components (z, x, y, w) */
  T4 zxyw();

  /** Creates a vector with components (z, x, z, x) */
  T4 zxzx();

  /** Creates a vector with components (z, x, z, y) */
  T4 zxzy();

  /** Creates a vector with components (z, x, z, z) */
  T4 zxzz();

  /** Creates a vector with components (z, x, z, w) */
  T4 zxzw();

  /** Creates a vector with components (z, x, w, x) */
  T4 zxwx();

  /** Creates a vector with components (z, x, w, y) */
  T4 zxwy();

  /** Creates a vector with components (z, x, w, z) */
  T4 zxwz();

  /** Creates a vector with components (z, x, w, w) */
  T4 zxww();

  /** Creates a vector with components (z, y, x, x) */
  T4 zyxx();

  /** Creates a vector with components (z, y, x, y) */
  T4 zyxy();

  /** Creates a vector with components (z, y, x, z) */
  T4 zyxz();

  /** Creates a vector with components (z, y, x, w) */
  T4 zyxw();

  /** Creates a vector with components (z, y, y, x) */
  T4 zyyx();

  /** Creates a vector with components (z, y, y, y) */
  T4 zyyy();

  /** Creates a vector with components (z, y, y, z) */
  T4 zyyz();

  /** Creates a vector with components (z, y, y, w) */
  T4 zyyw();

  /** Creates a vector with components (z, y, z, x) */
  T4 zyzx();

  /** Creates a vector with components (z, y, z, y) */
  T4 zyzy();

  /** Creates a vector with components (z, y, z, z) */
  T4 zyzz();

  /** Creates a vector with components (z, y, z, w) */
  T4 zyzw();

  /** Creates a vector with components (z, y, w, x) */
  T4 zywx();

  /** Creates a vector with components (z, y, w, y) */
  T4 zywy();

  /** Creates a vector with components (z, y, w, z) */
  T4 zywz();

  /** Creates a vector with components (z, y, w, w) */
  T4 zyww();

  /** Creates a vector with components (z, z, x, x) */
  T4 zzxx();

  /** Creates a vector with components (z, z, x, y) */
  T4 zzxy();

  /** Creates a vector with components (z, z, x, z) */
  T4 zzxz();

  /** Creates a vector with components (z, z, x, w) */
  T4 zzxw();

  /** Creates a vector with components (z, z, y, x) */
  T4 zzyx();

  /** Creates a vector with components (z, z, y, y) */
  T4 zzyy();

  /** Creates a vector with components (z, z, y, z) */
  T4 zzyz();

  /** Creates a vector with components (z, z, y, w) */
  T4 zzyw();

  /** Creates a vector with components (z, z, z, x) */
  T4 zzzx();

  /** Creates a vector with components (z, z, z, y) */
  T4 zzzy();

  /** Creates a vector with components (z, z, z, z) */
  T4 zzzz();

  /** Creates a vector with components (z, z, z, w) */
  T4 zzzw();

  /** Creates a vector with components (z, z, w, x) */
  T4 zzwx();

  /** Creates a vector with components (z, z, w, y) */
  T4 zzwy();

  /** Creates a vector with components (z, z, w, z) */
  T4 zzwz();

  /** Creates a vector with components (z, z, w, w) */
  T4 zzww();

  /** Creates a vector with components (z, w, x, x) */
  T4 zwxx();

  /** Creates a vector with components (z, w, x, y) */
  T4 zwxy();

  /** Creates a vector with components (z, w, x, z) */
  T4 zwxz();

  /** Creates a vector with components (z, w, x, w) */
  T4 zwxw();

  /** Creates a vector with components (z, w, y, x) */
  T4 zwyx();

  /** Creates a vector with components (z, w, y, y) */
  T4 zwyy();

  /** Creates a vector with components (z, w, y, z) */
  T4 zwyz();

  /** Creates a vector with components (z, w, y, w) */
  T4 zwyw();

  /** Creates a vector with components (z, w, z, x) */
  T4 zwzx();

  /** Creates a vector with components (z, w, z, y) */
  T4 zwzy();

  /** Creates a vector with components (z, w, z, z) */
  T4 zwzz();

  /** Creates a vector with components (z, w, z, w) */
  T4 zwzw();

  /** Creates a vector with components (z, w, w, x) */
  T4 zwwx();

  /** Creates a vector with components (z, w, w, y) */
  T4 zwwy();

  /** Creates a vector with components (z, w, w, z) */
  T4 zwwz();

  /** Creates a vector with components (z, w, w, w) */
  T4 zwww();

  /** Creates a vector with components (w, x, x, x) */
  T4 wxxx();

  /** Creates a vector with components (w, x, x, y) */
  T4 wxxy();

  /** Creates a vector with components (w, x, x, z) */
  T4 wxxz();

  /** Creates a vector with components (w, x, x, w) */
  T4 wxxw();

  /** Creates a vector with components (w, x, y, x) */
  T4 wxyx();

  /** Creates a vector with components (w, x, y, y) */
  T4 wxyy();

  /** Creates a vector with components (w, x, y, z) */
  T4 wxyz();

  /** Creates a vector with components (w, x, y, w) */
  T4 wxyw();

  /** Creates a vector with components (w, x, z, x) */
  T4 wxzx();

  /** Creates a vector with components (w, x, z, y) */
  T4 wxzy();

  /** Creates a vector with components (w, x, z, z) */
  T4 wxzz();

  /** Creates a vector with components (w, x, z, w) */
  T4 wxzw();

  /** Creates a vector with components (w, x, w, x) */
  T4 wxwx();

  /** Creates a vector with components (w, x, w, y) */
  T4 wxwy();

  /** Creates a vector with components (w, x, w, z) */
  T4 wxwz();

  /** Creates a vector with components (w, x, w, w) */
  T4 wxww();

  /** Creates a vector with components (w, y, x, x) */
  T4 wyxx();

  /** Creates a vector with components (w, y, x, y) */
  T4 wyxy();

  /** Creates a vector with components (w, y, x, z) */
  T4 wyxz();

  /** Creates a vector with components (w, y, x, w) */
  T4 wyxw();

  /** Creates a vector with components (w, y, y, x) */
  T4 wyyx();

  /** Creates a vector with components (w, y, y, y) */
  T4 wyyy();

  /** Creates a vector with components (w, y, y, z) */
  T4 wyyz();

  /** Creates a vector with components (w, y, y, w) */
  T4 wyyw();

  /** Creates a vector with components (w, y, z, x) */
  T4 wyzx();

  /** Creates a vector with components (w, y, z, y) */
  T4 wyzy();

  /** Creates a vector with components (w, y, z, z) */
  T4 wyzz();

  /** Creates a vector with components (w, y, z, w) */
  T4 wyzw();

  /** Creates a vector with components (w, y, w, x) */
  T4 wywx();

  /** Creates a vector with components (w, y, w, y) */
  T4 wywy();

  /** Creates a vector with components (w, y, w, z) */
  T4 wywz();

  /** Creates a vector with components (w, y, w, w) */
  T4 wyww();

  /** Creates a vector with components (w, z, x, x) */
  T4 wzxx();

  /** Creates a vector with components (w, z, x, y) */
  T4 wzxy();

  /** Creates a vector with components (w, z, x, z) */
  T4 wzxz();

  /** Creates a vector with components (w, z, x, w) */
  T4 wzxw();

  /** Creates a vector with components (w, z, y, x) */
  T4 wzyx();

  /** Creates a vector with components (w, z, y, y) */
  T4 wzyy();

  /** Creates a vector with components (w, z, y, z) */
  T4 wzyz();

  /** Creates a vector with components (w, z, y, w) */
  T4 wzyw();

  /** Creates a vector with components (w, z, z, x) */
  T4 wzzx();

  /** Creates a vector with components (w, z, z, y) */
  T4 wzzy();

  /** Creates a vector with components (w, z, z, z) */
  T4 wzzz();

  /** Creates a vector with components (w, z, z, w) */
  T4 wzzw();

  /** Creates a vector with components (w, z, w, x) */
  T4 wzwx();

  /** Creates a vector with components (w, z, w, y) */
  T4 wzwy();

  /** Creates a vector with components (w, z, w, z) */
  T4 wzwz();

  /** Creates a vector with components (w, z, w, w) */
  T4 wzww();

  /** Creates a vector with components (w, w, x, x) */
  T4 wwxx();

  /** Creates a vector with components (w, w, x, y) */
  T4 wwxy();

  /** Creates a vector with components (w, w, x, z) */
  T4 wwxz();

  /** Creates a vector with components (w, w, x, w) */
  T4 wwxw();

  /** Creates a vector with components (w, w, y, x) */
  T4 wwyx();

  /** Creates a vector with components (w, w, y, y) */
  T4 wwyy();

  /** Creates a vector with components (w, w, y, z) */
  T4 wwyz();

  /** Creates a vector with components (w, w, y, w) */
  T4 wwyw();

  /** Creates a vector with components (w, w, z, x) */
  T4 wwzx();

  /** Creates a vector with components (w, w, z, y) */
  T4 wwzy();

  /** Creates a vector with components (w, w, z, z) */
  T4 wwzz();

  /** Creates a vector with components (w, w, z, w) */
  T4 wwzw();

  /** Creates a vector with components (w, w, w, x) */
  T4 wwwx();

  /** Creates a vector with components (w, w, w, y) */
  T4 wwwy();

  /** Creates a vector with components (w, w, w, z) */
  T4 wwwz();

  /** Creates a vector with components (w, w, w, w) */
  T4 wwww();
}