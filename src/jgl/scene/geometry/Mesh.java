package jgl.scene.geometry;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;

import jgl.scene.shading.Material;

/**
 * Stores geometry and materials.
 * 
 * @author justin
 */
public class Mesh {
  
  public static class MeshPart {
    Material material;
    int      offset;
    int      numElements;

    public MeshPart(Material material, int offset, int numElements) {
      this.material = material;
      this.offset = offset;
      this.numElements = numElements;
    }
  }

  Geometry<?>    geometry;
  List<MeshPart> parts;
  boolean        indexed;

  public Mesh(Geometry<?> geometry, Material material) {
    this.geometry = geometry;
    indexed = geometry.numIndices() > 0;
    parts = new ArrayList<MeshPart>();
    parts.add(new MeshPart(material, 0, indexed ? geometry.numIndices() : geometry.numVertices()));
  }

  public Mesh(Geometry<?> geometry, List<MeshPart> parts) {
    this.geometry = geometry;
    this.parts = parts;
    indexed = geometry.numIndices() > 0;
  }

  public void drawArrays(GL2 gl) {
    geometry.getVertexType().startArrays(gl, geometry.getVertices());

    for (MeshPart part : parts) {
      part.material.enable(gl);
      if (indexed) {
        gl.glDrawElements(
            geometry.getPrimitive().glConstant, 
            part.numElements, 
            geometry.getIndexType().glConstant, 
            geometry.getIndices().position(part.offset));
      } else {
        gl.glDrawArrays(geometry.getPrimitive().glConstant, part.offset, part.numElements);
      }
      part.material.disable(gl);
    }
    
    geometry.getVertexType().endArrays(gl);
  }
  
  public void drawVBO(GL2 gl) {
  }
  
  public void dispose(GL2 gl) {
    for (MeshPart part : parts)
      part.material.dispose(gl);
    // delete vbo if they exist
  }
}
