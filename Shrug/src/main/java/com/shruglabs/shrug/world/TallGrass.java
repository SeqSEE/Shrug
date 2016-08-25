package com.shruglabs.shrug.world;


import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2i;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.util.Random;

import com.shruglabs.shrug.Shrug;
import com.shruglabs.shrug.client.TextureManager;


public class TallGrass {
	
	
	
	private Object[] vertices;
	private String texture;
	/**
	 * 
	 * @param grass The instance of TallGrass
	 * @return Vertices as an Object[] containing 4 float[] with an x and y value
	 */
	public Object[] getVertices(TallGrass grass) {
		return grass.vertices;
	}

	/**
	 * 
	 * @param grass  The instance of TallGrass
	 * @param vertex1   x, y      Top-Right Corner
	 * @param vertex2   x, -y     Bottom-Right Corner
	 * @param vertex3   -x, -y    Bottom-Left Corner
	 * @param vertex4   -x, y     Top-Left Corner
	 */
	public void setVertices(TallGrass grass, float[] v1, float[] v2, float[] v3, float[] v4) {
		grass.vertices = new Object[] { v1, v2, v3, v4 };
	}
	
	public static void createGrass() {
		Random rand = new Random();
		
		float[] vertex1 = new float[] { 0.04f, 0.04f };
		float[] vertex2 = new float[] { 0.04f, -0.04f };
		float[] vertex3 = new float[] { -0.04f, -0.04f };
		float[] vertex4 = new float[] { -0.04f, 0.04f };
		TallGrass grass = new TallGrass();
		
		float x = rand.nextBoolean() ? rand.nextFloat() * 2 : -rand.nextFloat() * 2;
		float y = rand.nextBoolean() ? rand.nextFloat() * 2 : -rand.nextFloat() * 2;
		grass.setVertices(grass, vertex1, vertex2, vertex3, vertex4);
		vertex1 = new float[] { vertex1[0] += x, vertex1[1] += y };
		vertex2 = new float[] { vertex2[0] += x, vertex2[1] += y };
		vertex3 = new float[] { vertex3[0] += x, vertex3[1] += y };
		vertex4 = new float[] { vertex4[0] += x, vertex4[1] += y };
		grass.setVertices(grass, vertex1, vertex2, vertex3, vertex4);
		String name = rand.nextBoolean() ? "tallgrass_1" : "tallgrass_2";
		grass.texture = name;
		Shrug.shrug.grass.add(grass);
	}

	public void drawGrass() {
		TextureManager.textures.get(this.texture).bind();
		glBegin(GL_QUADS);
		{
			float[] v1 = (float[]) getVertices(this)[0];
			float[] v2 = (float[]) getVertices(this)[1];
			float[] v3 = (float[]) getVertices(this)[2];
			float[] v4 = (float[]) getVertices(this)[3];
			glTexCoord2i(0, 0);
			glVertex2f(v1[0], v1[1]);
			glTexCoord2i(0, 1);
			glVertex2f(v2[0], v2[1]);
			glTexCoord2i(1, 1);
			glVertex2f(v3[0], v3[1]);
			glTexCoord2i(1, 0);
			glVertex2f(v4[0], v4[1]);
		}
		glEnd();
		
	}
}
