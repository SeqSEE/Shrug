package com.shruglabs.shrug.client.render;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

import com.shruglabs.shrug.Shrug;

public class Texture {

	private int id;
    private int width;
    private int height;
    
    public Texture(String filename){
    	System.out.println("shrug$ Binding " + filename);
        BufferedImage bi;
        InputStream in;
		in = Shrug.class.getResourceAsStream(filename);
        try{
            bi = ImageIO.read(in);
            in.close();
            width = bi.getWidth();
            height = bi.getHeight();
            int[] pixels_raw = new int[width * height * 4];
            pixels_raw = bi.getRGB(0, 0, width, height, null, 0, width);
            ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);
            for (int i = 0; i < width; i++){
                for(int j = 0; j < height; j++){
                    int pixel = pixels_raw[i * width +j];
                    pixels.put((byte) ((pixel >> 16) & 0xFF));
                    pixels.put((byte) ((pixel >> 8) & 0xFF));
                    pixels.put((byte) (pixel & 0xFF));
                    pixels.put((byte) ((pixel >> 24) & 0xFF));
                }
            }
            pixels.flip();
            id = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, id);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA,
             GL_UNSIGNED_BYTE, pixels);

        }catch(IOException e){
            System.out.println("Texture " + filename + " not found!");
        }
    }
        
    public void bind(){
        glBindTexture(GL_TEXTURE_2D, id);
    }
	
}
