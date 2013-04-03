/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.math;

import jgl.math.vector.Vec3f;

/**
 * Math convenience methods.
 * 
 * @author justin
 */
public final class Maths {

  public static final float E  = (float) Math.E;
  public static final float PI = (float) Math.PI;

  public static float sin(float a) {
    return (float) Math.sin(a);
  }

  public static float cos(float a) {
    return (float) Math.cos(a);
  }

  public static float tan(float a) {
    return (float) Math.tan(a);
  }

  public static float sqrt(float a) {
    return (float) Math.sqrt(a);
  }

  public static float asin(float a) {
    return (float) Math.asin(a);
  }

  public static float acos(float a) {
    return (float) Math.acos(a);
  }

  public static float atan(float a) {
    return (float) Math.atan(a);
  }

  public static float atan2(float y, float x) {
    return (float) Math.atan2(y, x);
  }

  public static float ceil(float a) {
    return (float) Math.ceil(a);
  }

  public static float floor(float a) {
    return (float) Math.floor(a);
  }

  public static float toRadians(float angdeg) {
    return (float) Math.toRadians(angdeg);
  }

  public static float toDegrees(float angrad) {
    return (float) Math.toDegrees(angrad);
  }

  public static double clamp(double val, double min, double max) {
    return (val < min) ? min : (val > max) ? max : val;
  }

  public static float clamp(float val, float min, float max) {
    return (val < min) ? min : (val > max) ? max : val;
  }

  public static int clamp(int val, int min, int max) {
    return (val < min) ? min : (val > max) ? max : val;
  }

  public static float random() {
    return (float) Math.random();
  }

  /** Converts spherical coordinates to Cartesian using a y-up orientation. */
  public static Vec3f sphericalToCartesian(float radius, float altitude, float azimuth, boolean yUp) {
    float x, y, z;
    if (yUp) {
      x = (float) (radius * Math.cos(altitude) * Math.sin(azimuth));
      y = (float) (radius * Math.sin(altitude));
      z = (float) (radius * Math.cos(azimuth) * Math.cos(altitude));
    } else {
      x = (float) (radius * Math.cos(altitude) * Math.sin(azimuth));
      y = -(float) (radius * Math.cos(azimuth) * Math.cos(altitude));
      z = (float) (radius * Math.sin(altitude));
    }
    return new Vec3f(x, y, z);
  }

  /** Converts Cartesian coordinates to spherical (radius, altitude, azimuth). */
  public static Vec3f cartesianToSpherical(float x, float y, float z, boolean yUp) {
    float radius, altitude, azimuth;
    radius = (float) Math.sqrt(x * x + y * y + z * z);
    if (yUp) {
      altitude = (float) Math.asin(y / radius);
      azimuth = (float) Math.atan2(x, z);
      if (azimuth < 0) azimuth += PI * 2;
    } else {
      altitude = (float) Math.asin(z / radius);
      azimuth = (float) Math.atan2(x, -y);
      if (azimuth < 0) azimuth += PI * 2;
    }
    return new Vec3f(radius, altitude, azimuth);
  }
}
