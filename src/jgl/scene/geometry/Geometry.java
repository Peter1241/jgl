package jgl.scene.geometry;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL2;

/**
 * 
 * @author justin
 *
 */
public abstract class Geometry {

  FloatBuffer vertices;
  FloatBuffer normals;
  FloatBuffer texCoords;
  IntBuffer   faces;

  public void drawImmediate(GL2 gl) {
    int numFaces = faces.capacity() / 3;
    
    gl.glBegin(GL2.GL_TRIANGLES);
    for (int i = 0; i < numFaces; i++) {
      drawImmediateVertex(gl, faces.get());
      drawImmediateVertex(gl, faces.get());
      drawImmediateVertex(gl, faces.get());
    }
    gl.glEnd();
    
    normals.rewind();
    texCoords.rewind();
    vertices.rewind();
    faces.rewind();
  }
  
  private void drawImmediateVertex(GL2 gl, int i) {
    normals.position(i * 3);
    texCoords.position(i * 2);
    vertices.position(i * 3);
    gl.glNormal3fv(normals);
    gl.glTexCoord2fv(texCoords);
    gl.glVertex3fv(vertices);
  }
}