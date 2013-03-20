/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.scene.primitives;

import java.util.ArrayList;

import javax.media.opengl.GL2;

import jgl.math.vector.Vec3f;

/**
 * Any simple geometric object.
 * 
 * @author justin
 */
abstract class Primitive {

  public class Vertex {

    public Vec3f position, normal;

    public Vertex(Vec3f position, Vec3f normal) {
      this.position = position;
      this.normal = normal;
    }

    private void draw(GL2 gl) {
      gl.glNormal3f(normal.x, normal.y, normal.z);
      gl.glVertex3f(position.x, position.y, position.z);
    }
  }

  public class Triangle {

    public final int i, j, k;

    public Triangle(int i, int j, int k) {
      this.i = i;
      this.j = j;
      this.k = k;
    }

    private void draw(GL2 gl) {
      vertices.get(i).draw(gl);
      vertices.get(j).draw(gl);
      vertices.get(k).draw(gl);
    }
  }

  public ArrayList<Vertex>   vertices  = new ArrayList<Vertex>();
  public ArrayList<Triangle> triangles = new ArrayList<Triangle>();

  public void draw(GL2 gl) {
    gl.glBegin(GL2.GL_TRIANGLES);
    for (Triangle triangle : triangles) {
      triangle.draw(gl);
    }
    gl.glEnd();
  }

  /** Adds the vertex and returns its index. */
  protected int addVertex(Vec3f position, Vec3f normal) {
    vertices.add(new Vertex(position, normal));
    return vertices.size() - 1;
  }

  protected void addTriangle(int i, int j, int k) {
    triangles.add(new Triangle(i, j, k));
  }
}
