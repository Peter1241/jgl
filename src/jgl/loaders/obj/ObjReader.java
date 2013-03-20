/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.loaders.obj;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jgl.loaders.obj.ObjModel.Material;
import jgl.loaders.obj.ObjModel.Vertex;
import jgl.math.geometry.Box;
import jgl.math.vector.Vec3f;

/**
 * Reads wavefront .obj models.
 * 
 * @author justin
 */
public class ObjReader {

  private final String    objPath;
  private final MtlReader mtlReader;

  Vec3f                   min;
  Vec3f                   max;
  ObjModel                model;
  int                     smoothGroup;
  private Material        defaultMaterial;
  private Material        material;
  private List<String>    groups = Collections.emptyList();

  public ObjReader(String objPath, String mtlPath, String texPath) {
    this.objPath = objPath;
    this.mtlReader = new MtlReader(this, mtlPath, texPath);
  }

  public ObjReader(String objPath) {
    this(objPath, objPath, objPath);
  }

  private void parseLine(String line) throws IOException {
    if (line.startsWith("v ")) parsePosition(line.substring(1));
    else if (line.startsWith("vn ")) parseNormal(line.substring(2));
    else if (line.startsWith("vt ")) parseTexCoord(line.substring(2));
    else if (line.startsWith("f ")) parseFace(line.substring(1));
    else if (line.startsWith("s ")) parseSmoothGroup(line.substring(1));
    else if (line.startsWith("g ")) parseGroup(line.substring(1));
    else if (line.startsWith("mtllib ")) parseMaterialLib(line.substring(6));
    else if (line.startsWith("usemtl ")) parseUseMaterial(line.substring(6));
  }

  private void parsePosition(String line) {
    Vec3f p = new Vec3f(Tokenizer.vec3f(line));
    if (p.x < min.x) min.x = p.x;
    if (p.x > max.x) max.x = p.x;
    if (p.y < min.y) min.y = p.y;
    if (p.y > max.y) max.y = p.y;
    if (p.z < min.z) min.z = p.z;
    if (p.z > max.z) max.z = p.z;
    model.positions.add(p);
  }

  private void parseNormal(String line) {
    model.normals.add(Tokenizer.vec3f(line));
  }

  private void parseTexCoord(String line) {
    model.texCoords.add(Tokenizer.vec2f(line));
  }

  private void parseFace(String line) {
    ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    for (String strFace : Tokenizer.strings(line)) {
      int slash1 = strFace.indexOf('/');
      int slash2 = strFace.indexOf('/', slash1 + 1);

      // negative indices = face vertices do not have vertex normal / texCoords
      int v = -1;
      int vt = -1;
      int vn = -1;

      // subtract 1 to make indices 0-based (they start at 1 in the file)
      if (slash1 < 0) {
        v = Integer.parseInt(strFace) - 1;
      } else {
        if (slash2 < 0) {
          v = Integer.parseInt(strFace.substring(0, slash1)) - 1;
          vt = Integer.parseInt(strFace.substring(slash1 + 1)) - 1;
        } else {
          if (slash1 + 1 == slash2) {
            v = Integer.parseInt(strFace.substring(0, slash1)) - 1;
            vn = Integer.parseInt(strFace.substring(slash2 + 1)) - 1;
          } else {
            v = Integer.parseInt(strFace.substring(0, slash1)) - 1;
            vt = Integer.parseInt(strFace.substring(slash1 + 1, slash2)) - 1;
            vn = Integer.parseInt(strFace.substring(slash2 + 1)) - 1;
          }
        }
      }

      vertices.add(model.new Vertex(v, vt, vn));
    }

    Material faceMaterial = (material == null) ? defaultMaterial : material;
    model.totalVertices += vertices.size();
    model.faces.add(model.new Face(faceMaterial, smoothGroup, vertices, groups));
  }

  private void parseSmoothGroup(String line) {
    String str = Tokenizer.string(line);
    smoothGroup = (str.equals("on") ? 1 : (str.equals("off") ? 0 : Integer.parseInt(str)));
  }

  private void parseGroup(String line) {
    groups = Tokenizer.strings(line);
    model.groups.addAll(groups);
  }

  private void parseMaterialLib(String line) throws IOException {
    for (String libName : Tokenizer.strings(line))
      mtlReader.read(libName);
  }

  private void parseUseMaterial(String line) {
    material = mtlReader.materials.get(Tokenizer.string(line));
  }

  public ObjModel read(String objName) {
    InputStream in = getResource(objPath, objName);
    if (in == null) {
      System.err.println("(ObjReader) could not open " + objPath + "/" + objName);
    } else {
      try {
        ObjModel model = new ObjModel();
        this.model = model;
        smoothGroup = 1;
        mtlReader.init();
        defaultMaterial = model.new Material("Default Material", false);
        model.materials.add(defaultMaterial);
        min = new Vec3f(Float.POSITIVE_INFINITY);
        max = new Vec3f(Float.NEGATIVE_INFINITY);

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = br.readLine()) != null)
          parseLine(line.trim());
        br.close();

        model.bounds = new Box(min, max);
        model.calcNormals();
        this.model = null;
        return model;
      } catch (IOException e) {
        System.err.println("(ObjReader) error reading " + objName);
        System.err.println(e.getMessage());
        return null;
      }
    }
    return null;
  }

  static InputStream getResource(String path, String name) {
    InputStream in = ObjReader.class.getResourceAsStream(path + "/" + name);
    if (in == null) {
      File f = new File(path, name);
      if (f.exists()) {
        try {
          return f.toURI().toURL().openStream();
        } catch (IOException e) {
        }
      }
    }
    return in;
  }
}
