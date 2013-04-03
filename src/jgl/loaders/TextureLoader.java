/*******************************************************************************
 *  Copyright (C) 2013 Justin Stoecker. The MIT License.
 *******************************************************************************/
package jgl.loaders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.media.opengl.GL;

import jgl.core.Texture;

import com.jogamp.common.nio.Buffers;

/**
 * Loads images into textures.
 * 
 * @author justin
 */
public class TextureLoader {

  public static Texture loadImage(GL gl, BufferedImage image) {
    int w = image.getWidth();
    int h = image.getHeight();
    boolean alpha = image.getColorModel().hasAlpha();
    int channels = alpha ? 4 : 3;
    ByteBuffer data = Buffers.newDirectByteBuffer(w * h * channels);

    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        int pixel = image.getRGB(x, h - y - 1);
        data.put((byte) ((pixel >> 16) & 0xFF));
        data.put((byte) ((pixel >> 8) & 0xFF));
        data.put((byte) ((pixel) & 0xFF));
        if (alpha)
          data.put((byte) ((pixel >> 24) & 0xFF));
      }
    }

    data.rewind();

    Texture texture = new Texture(Texture.Target.TEXTURE_2D);
    texture.bind(gl);
    gl.glPixelStorei(GL.GL_UNPACK_ALIGNMENT, 1);
    texture.set(gl, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
    texture.set(gl, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);
    texture.set(gl, GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
    texture.set(gl, GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);

    if (alpha)
      texture.setData2D(gl, GL.GL_RGBA, w, h, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, data);
    else
      texture.setData2D(gl, GL.GL_RGB, w, h, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, data);

    return texture;
  }

  public static Texture loadFile(GL gl, String fileName) {
    try {
      BufferedImage image = ImageIO.read(new File(fileName));
      return loadImage(gl, image);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Texture loadResource(GL gl, String resourceName) {
    try {
      InputStream input = TextureLoader.class.getClassLoader().getResourceAsStream(resourceName);
      BufferedImage image = ImageIO.read(input);
      return loadImage(gl, image);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
