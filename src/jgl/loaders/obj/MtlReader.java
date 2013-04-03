/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.loaders.obj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import jgl.loaders.obj.ObjModel.Material;

public class MtlReader {

  private final String  mtlPath;
  private final String  texPath;
  Map<String, Material> materials = new HashMap<String, Material>();
  Material              material;
  ObjReader             objReader;

  public MtlReader(ObjReader objReader, String mtlPath, String texPath) {
    this.mtlPath = mtlPath;
    this.texPath = texPath;
    this.objReader = objReader;
  }

  public void init() {
    materials.clear();
    material = null;
  }

  public void read(String mtllibName) throws IOException {
    InputStream in = ObjReader.getResource(mtlPath, mtllibName);
    if (in != null) {
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      Material material = null;
      while ((line = br.readLine()) != null)
        parseLine(line.trim());
      br.close();
    }
  }

  private void parseLine(String line) {
    if (line.startsWith("newmtl ")) {
      parseNewMaterial(line.substring(6));
    } else if (material != null) {
      if (line.startsWith("Ka ")) parseAmbient(line.substring(2));
      else if (line.startsWith("Kd ")) parseDiffuse(line.substring(2));
      else if (line.startsWith("Ks ")) parseSpecular(line.substring(2));
      else if (line.startsWith("Ns ")) parseShininess(line.substring(2));
      else if (line.startsWith("illum ")) parseIllum(line.substring(5));
      else if (line.startsWith("d ")) parseAlpha(line.substring(1));
      else if (line.startsWith("map_Kd ")) parseTexture(line.substring(6));
    }
  }

  private void parseNewMaterial(String line) {
    String name = Tokenizer.string(line);
    material = objReader.model.new Material(name, false);
    materials.put(name, material);
  }

  private float[] parseColor(String line) {
    if (line.startsWith("spectral")) {
      return null;
    } else if (line.startsWith("xyz")) {
      return null;
    } else {
      return Tokenizer.rgb(line);
    }
  }

  private void parseAmbient(String line) {
    float[] color = parseColor(line);
    if (color != null) material.setAmbient(color);
  }

  private void parseDiffuse(String line) {
    float[] color = parseColor(line);
    if (color != null) material.setDiffuse(color);
  }

  private void parseSpecular(String line) {
    float[] color = parseColor(line);
    if (color != null) material.setSpecular(color);
  }

  private void parseShininess(String line) {
    material.setShininess((int) (Tokenizer.float1(line) / 1000f * 128));
  }

  private void parseIllum(String line) {
    material.illum = Tokenizer.int1(line);
  }

  private void parseAlpha(String line) {
    material.setAlpha(Tokenizer.float1(line));
  }

  private void parseTexture(String line) {
    material.textureName = Tokenizer.string(line);
  }
}
