/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.loaders.obj;

import java.util.ArrayList;
import java.util.List;

import jgl.math.vector.Vec2f;
import jgl.math.vector.Vec3f;

public class Tokenizer {

  public static Vec3f vec3f(String line) {
    float[] v = { 0, 0, 0 };
    floats(line, v);
    return new Vec3f(v);
  }
  
  public static Vec2f vec2f(String line) {
    float[] v = { 0, 0 };
    floats(line, v);
    return new Vec2f(v);
  }

  public static float[] rgb(String line) {
    float[] color = { 1, 1, 1 };
    int found = floats(line, color);
    if (found == 1) color[1] = color[2] = color[0];
    return color;
  }
  
  public static int int1(String line) {
    List<String> strings = strings(line);
    return (strings.isEmpty()) ? 1 : Integer.parseInt(strings.get(0));
  }
  
  public static float float1(String line) {
    float[] values = { 0 };
    floats(line, values);
    return values[0];
  }

  public static int floats(String line, float[] out) {
    int start = 0;
    int end = 0;
    int found = 0;

    while (start < line.length() && found < out.length) {
      while (start < line.length() && line.charAt(start) == ' ')
        start++;
      end = start + 1;
      while (end < line.length() && line.charAt(end) != ' ')
        end++;
      if (start < line.length()) {
        out[found++] = Float.parseFloat(line.substring(start, end));
        start = end;
      }
    }

    return found;
  }

  public static String string(String line) {
    List<String> strings = strings(line);
    return strings.isEmpty() ? null : strings.get(0);
  }

  public static List<String> strings(String line) {
    ArrayList<String> values = new ArrayList<String>();

    int start = 0;
    int end = 0;
    while (start < line.length()) {
      while (start < line.length() && line.charAt(start) == ' ')
        start++;
      end = start + 1;
      while (end < line.length() && line.charAt(end) != ' ')
        end++;
      if (start < line.length()) {
        values.add(line.substring(start, end));
        start = end;
      }
    }

    return values;
  }
}
