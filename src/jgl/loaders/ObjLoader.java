package jgl.loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jgl.geometry.Geometry.Primitive;
import jgl.geometry.Mesh.MeshPart;
import jgl.geometry.Mesh;
import jgl.geometry.VertexPNT;
import jgl.geometry.VertexPNT.GeometryPNT;
import jgl.math.vector.Vec2f;
import jgl.math.vector.Vec3f;
import jgl.math.vector.Vec4f;
import jgl.shading.Material;
import jgl.shading.PhongMaterial;

/**
 * Loads OBJ models and materials into a mesh.
 * 
 * @author justin
 */
public class ObjLoader {

  private List<Vec3f>                       v         = new ArrayList<Vec3f>();
  private List<Vec2f>                       vt        = new ArrayList<Vec2f>();
  private List<Vec3f>                       vn        = new ArrayList<Vec3f>();
  private List<Vertex>                      verts     = new ArrayList<Vertex>();
  private List<Triangle>                    triangles = new ArrayList<Triangle>();
  private StringSplitter                    splitter  = new StringSplitter();
  private Map<Integer, Map<String, Vertex>> maps      = new HashMap<Integer, Map<String, Vertex>>();
  private List<Map<String, Material>>       mtlLibs   = new ArrayList<Map<String, Material>>();
  private Map<String, Vertex>               map;
  private int                               smoothGroup;
  private int                               iv;
  private int                               ivt;
  private int                               ivn;
  private File                              root;
  private List<MeshPart>                    parts     = new ArrayList<MeshPart>();
  private MeshPart                          currentPart;
  
  public Mesh load(File file) {
    try {
      this.root = file.getParentFile();
      return load(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

  private Mesh load(InputStream stream) {
    v.clear();
    vt.clear();
    vn.clear();
    verts.clear();
    triangles.clear();
    parts.clear();
    smoothGroup = 1;
    map = getMap();
    currentPart = new MeshPart(new PhongMaterial(), 0, 0);
    
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(stream));
      String line;
      while ((line = br.readLine()) != null) {
        parseLine(line);
      }
      stream.close();
      endCurrentPart();
      
      return createMesh();

    } catch (IOException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

  private Mesh createMesh() {
    GeometryPNT geom = new GeometryPNT(Primitive.TRIANGLES, verts.size(), triangles.size() * 3);
    VertexPNT v = new VertexPNT();

    if (vt.size() == 0) {
      System.out.println("TODO: no tex coord, return geomPN");
    }

    for (Vertex objVertex : verts) {
      v.position(objVertex.position);
      v.normal(objVertex.normal.normalized());
      v.texCoords(objVertex.texCoords);
      geom.putVertex(v);
    }

    for (Triangle objTriangle : triangles) {
      geom.putIndex(objTriangle.a);
      geom.putIndex(objTriangle.b);
      geom.putIndex(objTriangle.c);
    }
    
    return new Mesh(geom, parts);
  }
  
  private void endCurrentPart() {
    currentPart.numElements = triangles.size() * 3 - currentPart.offset;
    if (currentPart.numElements > 0) {
      parts.add(currentPart);
    }
  }

  private void parseLine(String line) {
    if (line.isEmpty())
      return;

    String[] tokens = splitter.split(line);
    if (tokens[0].equals("v")) {
      float x = Float.parseFloat(tokens[1]);
      float y = Float.parseFloat(tokens[2]);
      float z = Float.parseFloat(tokens[3]);
      v.add(new Vec3f(x, y, z));
    } else if (tokens[0].equals("vt")) {
      float x = Float.parseFloat(tokens[1]);
      float y = Float.parseFloat(tokens[2]);
      vt.add(new Vec2f(x, y));
    } else if (tokens[0].equals("vn")) {
      float x = Float.parseFloat(tokens[1]);
      float y = Float.parseFloat(tokens[2]);
      float z = Float.parseFloat(tokens[3]);
      vn.add(new Vec3f(x, y, z));
    } else if (tokens[0].equals("s")) {
      smoothGroup = tokens[1].equalsIgnoreCase("off") ? 0 : Integer.parseInt(tokens[1]);
      map = getMap();
    } else if (tokens[0].equals("f")) {
      parseFace(tokens);
    } else if (tokens[0].equals("usemtl")) {
      endCurrentPart();
      for (Map<String, Material> lib : mtlLibs) {
        Material libMaterial = lib.get(tokens[1]);
        if (libMaterial != null) {
          currentPart = new MeshPart(libMaterial, triangles.size() * 3, 0);
          break;
        }
      }
    } else if (tokens[0].equals("mtllib")) {
      for (int i = 1; i < tokens.length; i++) {
        Map<String, Material> lib = new MaterialLibraryLoader().load(new File(root, tokens[i]));
        if (lib != null)
          mtlLibs.add(lib);
      }
    }
  }

  private void parseFace(String[] tokens) {
    boolean useFaceNormal = false;

    Vertex[] faceVerts = new Vertex[tokens.length - 1];

    // add vertices
    for (int i = 1; i < tokens.length; i++) {
      parseIndices(tokens[i]);
      useFaceNormal = useFaceNormal || ivn < 0;

      // if no smoothing group or the vertex hasn't been seen, create a new one
      Vertex vertex = map.get(tokens[i]);
      if (smoothGroup == 0 || vertex == null) {
        vertex = new Vertex();
        vertex.position = v.get(iv);
        vertex.texCoords = ivt > 0 ? vt.get(ivt) : new Vec2f(0);
        vertex.normal = ivn > 0 ? vn.get(ivn) : new Vec3f(0);
        vertex.index = verts.size();
        verts.add(vertex);
        map.put(tokens[i], vertex);
      }

      faceVerts[i - 1] = vertex;
    }

    // add triangles
    for (int i = 0; i < faceVerts.length - 2; i++) {
      Triangle triangle = new Triangle();
      triangle.a = faceVerts[0].index;
      triangle.b = faceVerts[i + 1].index;
      triangle.c = faceVerts[i + 2].index;
      triangles.add(triangle);
    }

    // add normals if necessary
    if (useFaceNormal) {
      Vec3f a = faceVerts[0].position;
      Vec3f b = faceVerts[1].position;
      Vec3f c = faceVerts[2].position;
      Vec3f normal = (b.minus(a)).cross(c.minus(a)).normalized();
      for (Vertex v : faceVerts)
        v.normal.add(normal);
    }
  }

  private void parseIndices(String indices) {
    int i = indices.indexOf("/");
    if (i == -1) {
      // format: v
      iv = Integer.parseInt(indices) - 1;
      ivt = -1;
      ivn = -1;
    } else {
      int j = indices.lastIndexOf("/");
      if (i == j) {
        // format: v/vt
        iv = Integer.parseInt(indices.substring(0, i)) - 1;
        ivt = Integer.parseInt(indices.substring(j + 1)) - 1;
        ivn = -1;
      } else if (i == j - 1) {
        // format: v//vn
        iv = Integer.parseInt(indices.substring(0, i)) - 1;
        ivt = -1;
        ivn = Integer.parseInt(indices.substring(j + 1)) - 1;
      } else {
        // format: v/vt/vn
        iv = Integer.parseInt(indices.substring(0, i)) - 1;
        ivt = Integer.parseInt(indices.substring(i + 1, j)) - 1;
        ivn = Integer.parseInt(indices.substring(j + 1)) - 1;
      }
    }
  }

  private Map<String, Vertex> getMap() {
    // vertices are grouped into smoothing groups
    map = maps.get(smoothGroup);
    if (map == null) {
      map = new HashMap<String, Vertex>();
      maps.put(smoothGroup, map);
    }
    return map;
  }
}

class Vertex {
  Vec3f position;
  Vec2f texCoords;
  Vec3f normal;
  int   index;
}

class Triangle {
  int a, b, c;
}

class MaterialLibraryLoader {

  Map<String, Material> lib = new HashMap<String, Material>();
  PhongMaterial         material;

  Map<String, Material> load(File libFile) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(libFile));
      String line;
      while ((line = br.readLine()) != null) {
        parseLine(line);
      }
      br.close();

      return lib;

    } catch (IOException e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

  void parseLine(String line) {
    if (line.isEmpty())
      return;
    
    String[] tokens = new StringSplitter().split(line);
    if (tokens[0].equals("newmtl")) {
      lib.put(tokens[1], material = new PhongMaterial());
    } else if (tokens[0].equals("Ka")) {
      Float r = Float.parseFloat(tokens[1]);
      Float g = Float.parseFloat(tokens[2]);
      Float b = Float.parseFloat(tokens[3]);
      material.ambient = new Vec4f(r, g, b, 1);
    } else if (tokens[0].equals("Kd")) {
      Float r = Float.parseFloat(tokens[1]);
      Float g = Float.parseFloat(tokens[2]);
      Float b = Float.parseFloat(tokens[3]);
      material.diffuse = new Vec4f(r, g, b, 1);
    } else if (tokens[0].equals("Ks")) {
      Float r = Float.parseFloat(tokens[1]);
      Float g = Float.parseFloat(tokens[2]);
      Float b = Float.parseFloat(tokens[3]);
      material.specular = new Vec4f(r, g, b, 1);
    } else if (tokens[0].equals("Ns")) {
      material.shininess = (int)(128*(Float.parseFloat(tokens[1])/1000f));
    } else if (tokens[0].equals("map_Kd")) {
      // texture TODO
    }
  }
}

/** Java's string.split is too slow, and OBJ input is all whitespace delimited. */
class StringSplitter {
  private String[] buf = new String[256];

  public String[] split(String line) {
    int added = 0, start = 0, end = 0;
    char prev = ' ';

    for (int i = 0; i < line.length(); i++) {
      char cur = line.charAt(i);
      if (prev == ' ' || prev == '\t') {
        if (cur != ' ' && cur != '\t') {
          start = i;
        }
      } else {
        if (cur == ' ' || cur == '\t') {
          end = i;
          buf[added++] = line.substring(start, end);
        }
      }
      prev = cur;
    }

    if (end <= start)
      buf[added++] = line.substring(start);

    String[] vals = new String[added];
    System.arraycopy(buf, 0, vals, 0, vals.length);
    return vals;
  }
}
