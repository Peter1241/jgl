/**
 * Copyright (C) 2013 Justin Stoecker
 * The MIT License. See LICENSE in project root.
 */

package jgl.scene.primitives;

import jgl.math.vector.Vec3f;

/**
 * Box primitive
 * 
 * @author justin
 */
public class BoxPrimitive extends Primitive {
  public BoxPrimitive(float width, float height, float depth) {

    float w = width / 2f;
    float h = height / 2f;
    float l = depth / 2f;

    // +X face
    Vec3f normal = new Vec3f(1, 0, 0);
    int a = addVertex(new Vec3f(w, -h, l), normal);
    int b = addVertex(new Vec3f(w, -h, -l), normal);
    int c = addVertex(new Vec3f(w, h, -l), normal);
    int d = addVertex(new Vec3f(w, h, l), normal);
    addTriangle(a, b, c);
    addTriangle(a, c, d);

    // -X face
    normal = new Vec3f(-1, 0, 0);
    a = addVertex(new Vec3f(-w, -h, l), normal);
    b = addVertex(new Vec3f(-w, -h, -l), normal);
    c = addVertex(new Vec3f(-w, h, -l), normal);
    d = addVertex(new Vec3f(-w, h, l), normal);
    addTriangle(b, a, d);
    addTriangle(b, d, c);

    // +Y face
    normal = new Vec3f(0, 1, 0);
    a = addVertex(new Vec3f(-w, h, l), normal);
    b = addVertex(new Vec3f(w, h, l), normal);
    c = addVertex(new Vec3f(w, h, -l), normal);
    d = addVertex(new Vec3f(-w, h, -l), normal);
    addTriangle(a, b, c);
    addTriangle(a, c, d);

    // -Y face
    normal = new Vec3f(0, -1, 0);
    a = addVertex(new Vec3f(-w, -h, l), normal);
    b = addVertex(new Vec3f(w, -h, l), normal);
    c = addVertex(new Vec3f(w, -h, -l), normal);
    d = addVertex(new Vec3f(-w, -h, -l), normal);
    addTriangle(b, a, d);
    addTriangle(b, d, c);

    // +Z face
    normal = new Vec3f(0, 0, 1);
    a = addVertex(new Vec3f(-w, -h, l), normal);
    b = addVertex(new Vec3f(w, -h, l), normal);
    c = addVertex(new Vec3f(w, h, l), normal);
    d = addVertex(new Vec3f(-w, h, l), normal);
    addTriangle(a, b, c);
    addTriangle(a, c, d);

    // -Z face
    normal = new Vec3f(0, 0, -1);
    a = addVertex(new Vec3f(-w, -h, -l), normal);
    b = addVertex(new Vec3f(w, -h, -l), normal);
    c = addVertex(new Vec3f(w, h, -l), normal);
    d = addVertex(new Vec3f(-w, h, -l), normal);
    addTriangle(b, a, d);
    addTriangle(b, d, c);
  }
}