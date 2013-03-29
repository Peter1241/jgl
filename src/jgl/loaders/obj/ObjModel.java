/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.loaders.obj;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import jgl.math.geometry.Box;
import jgl.math.vector.ConstVec3f;
import jgl.math.vector.Vec2f;
import jgl.math.vector.Vec3f;
import jgl.scene.mesh.Model;
import jgl.scene.mesh.ModelFormat;
import jgl.scene.mesh.ModelMaterial;
import jgl.scene.mesh.ModelPart;
import jgl.scene.mesh.Model.RenderMode;

import com.jogamp.common.nio.Buffers;

/**
 * A wavefront .obj model.
 * 
 * @author justin
 */
public class ObjModel {

  public Box            bounds;
  public List<Vec3f>    positions = new ArrayList<Vec3f>();
  public List<Vec3f>    normals   = new ArrayList<Vec3f>();
  public List<Vec2f>    texCoords = new ArrayList<Vec2f>();
  public List<Face>     faces     = new ArrayList<Face>();
  public List<Material> materials = new ArrayList<Material>();
  public Set<String>    groups    = new HashSet<String>();
  public int            totalVertices = 0;

  public ObjModel() {
  }

  public Model toModel() {

    ModelFormat format = (texCoords.size() > 0) ? ModelFormat.PNT : ModelFormat.PN;
    int totalTriangles = 0;
    FloatBuffer vertexData = Buffers.newDirectFloatBuffer(totalVertices * format.stride / 4);
    
    int verticesAdded = 0;

    class ExpandingArray {
      int[] a = new int[100];
      int size = 0;
      void add(int i) {
        if (size == a.length) a = Arrays.copyOf(a, a.length * 2);
        a[size++] = i;
      }
    }
    
    Map<Material, ExpandingArray> indices = new HashMap<Material, ExpandingArray>();
    for (Face face : faces) {
      ExpandingArray matIndices = indices.get(face.material);
      if (matIndices == null) {
        matIndices = new ExpandingArray();
        indices.put(face.material, matIndices);
      }
      
      int numTriangles = face.vertices.size() - 2;
      totalTriangles += numTriangles;
      for (int i = 0; i < numTriangles; i++) {
        matIndices.add(verticesAdded);
        matIndices.add(verticesAdded + i + 1);
        matIndices.add(verticesAdded + i + 2);
      }
      
      for (Vertex v : face.vertices) {
        vertexData.put(v.position().toArray());
        vertexData.put(v.normal().toArray());
        if (format == ModelFormat.PNT) {
          vertexData.put(v.texCoord().toArray());
        }
        verticesAdded++;
      }
    }
    vertexData.rewind();

    int offset = 0;
    List<ModelPart> parts = new ArrayList<ModelPart>();
    IntBuffer indexData = Buffers.newDirectIntBuffer(totalTriangles * 3);
    for (Entry<Material, ExpandingArray> materialMapping : indices.entrySet()) {
      ExpandingArray matIndices = materialMapping.getValue();
      int numIndices = matIndices.size;
      parts.add(new ModelPart(materialMapping.getKey(), offset, numIndices));
      indexData.put(matIndices.a, 0, matIndices.size);
      offset += numIndices;
    }
    indexData.rewind();
    
    return new Model(parts, vertexData, indexData, RenderMode.VBO, format, bounds);
  }

  public void calcNormals() {
    Map<Integer, Vec3f> sgNormals = new HashMap<Integer, Vec3f>();
    int prevGroup = 0;
    for (Face face : faces) {
      if (face.smoothGroup > 0) {

        if (face.smoothGroup != prevGroup) {
          sgNormals = new HashMap<Integer, Vec3f>();
          prevGroup = face.smoothGroup;
        }

        for (Vertex vertex : face.vertices) {
          Vec3f normal = sgNormals.get(vertex.v);
          if (normal == null) {
            normal = new Vec3f(face.normal());
            sgNormals.put(vertex.v, normal);
          } else {
            normal.add(face.normal());
          }
          vertex.normal = normal;
        }
      }
    }

    for (Face face : faces)
      for (Vertex vertex : face.vertices)
        vertex.normal = (vertex.normal == null) ? face.normal() : vertex.normal.normalize();
  }

  public void loadTextures(GL gl) {

  }

  public class Vertex {
    private final int v;
    private final int vt;
    private final int vn;
    private Vec3f     normal;

    public Vertex(int v, int vt, int vn) {
      this.v = v;
      this.vt = vt;
      this.vn = vn;
    }

    public Vec3f position() {
      return positions.get(v);
    }

    public Vec2f texCoord() {
      return vt < 0 ? new Vec2f(0) : texCoords.get(vt);
    }

    public Vec3f normal() {
      return vn < 0 ? normal : normals.get(vn);
    }
  }

  public class Face {
    public List<Vertex> vertices;
    public Vec3f        normal;
    public Material     material;
    public int          smoothGroup;
    public List<String> groups;

    public Face(Material material, int smoothGroup, List<Vertex> vertices, List<String> groups) {
      this.material = material;
      this.smoothGroup = smoothGroup;
      this.vertices = vertices;
      this.groups = groups;
    }

    public Vec3f normal() {
      if (normal == null) {
        normal = new Vec3f(0);
        for (int i = 0; i < vertices.size(); i++) {
          ConstVec3f a = vertices.get(i).position();
          ConstVec3f b = vertices.get((i + 1) % vertices.size()).position();
          normal.x += (a.y() - b.y()) * (a.z() + b.z());
          normal.y += (a.z() - b.z()) * (a.x() + b.x());
          normal.z += (a.x() - b.x()) * (a.y() + b.y());
        }
        normal.normalize();
      }

      return normal;
    }
  }

  public class Material extends ModelMaterial {
    public float  alpha;
    public int    illum = 1;
    public String textureName;

    public Material(String name, boolean containsTransparency) {
      super(name, containsTransparency);
    }

    public void setAlpha(float alpha) {
      this.alpha = alpha;
      this.diffuse[3] = alpha;
      this.ambient[3] = alpha;
      containsTransparency = alpha < 1;
    }
  }

  public void draw(GL2 gl) {
    for (Face face : faces) {
      face.material.apply(gl);
      gl.glBegin(GL2.GL_POLYGON);
      for (Vertex vertex : face.vertices) {
        Vec3f n = vertex.normal();
        gl.glNormal3f(n.x, n.y, n.z);
        Vec2f t = vertex.texCoord();
        if (t != null) {
          gl.glTexCoord2f(t.x, t.y);
        }
        Vec3f p = vertex.position();
        gl.glVertex3f(p.x, p.y, p.z);
      }
      gl.glEnd();
    }
  }
}
