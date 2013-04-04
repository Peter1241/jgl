/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.cameras;

import jgl.math.Maths;

class MotionParameter {
  float value       = 0;
  int   dir         = 0;
  float velocity    = 0;
  float damping     = 0.9f;
  float maxValue    = Float.POSITIVE_INFINITY;
  float maxVelocity = 2;

  MotionParameter() {
  }

  MotionParameter(float maxValue) {
    this.maxValue = maxValue;
  }

  boolean update() {
    float velocityMagnitude = Math.abs(velocity);
    if (dir == 0 && velocityMagnitude == 0)
      return false;

    velocity = Maths.clamp((velocity + dir * 0.1f) * damping, -maxVelocity, maxVelocity);
    value = Maths.clamp(value + velocity, -maxValue, maxValue);

    if (velocityMagnitude < 0.001)
      velocity = 0;

    return true;
  }
}
