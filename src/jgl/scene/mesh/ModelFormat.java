/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker
 *  The MIT License. See LICENSE in project root.
 *******************************************************************************/
package jgl.scene.mesh;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public enum ModelFormat {
  P(0, 0, -1, -1, -1),
  PN(24, 0, 12, -1, -1),
  PT(20, 0, -1, 12, -1),
  PC(24, 0, -1, -1, 12),
  PNT(32, 0, 12, 24, -1),
  PNC(36, 0, 12, -1, 24),
  PTC(32, 0, -1, 12, 20),
  PNTC(44, 0, 12, 24, 32);

  public final int stride;
  final int        pOffset;
  final int        nOffset;
  final int        tOffset;
  final int        cOffset;

  private ModelFormat(int stride, int pOffset, int nOffset, int tOffset, int cOffset) {
    this.stride = stride;
    this.pOffset = pOffset;
    this.nOffset = nOffset;
    this.tOffset = tOffset;
    this.cOffset = cOffset;
  }

  void setState(GL2 gl) {
    gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
    gl.glVertexPointer(3, GL.GL_FLOAT, stride, pOffset);

    if (nOffset != -1) {
      gl.glEnableClientState(GL2.GL_NORMAL_ARRAY);
      gl.glNormalPointer(GL.GL_FLOAT, stride, nOffset);
    }

    if (tOffset != -1) {
      gl.glEnableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
      gl.glTexCoordPointer(2, GL.GL_FLOAT, stride, tOffset);
    }

    if (cOffset != -1) {
      gl.glEnableClientState(GL2.GL_COLOR_ARRAY);
      gl.glColorPointer(3, GL.GL_FLOAT, stride, cOffset);
    }
  }

  void unsetState(GL2 gl) {
    gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
    if (nOffset != -1) gl.glDisableClientState(GL2.GL_NORMAL_ARRAY);
    if (tOffset != -1) gl.glDisableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
    if (cOffset != -1) gl.glDisableClientState(GL2.GL_COLOR_ARRAY);
  }
}
