package com.shruglabs.shrug.entity;

import static org.lwjgl.opengl.GL11.*;

import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.UUID;

import com.shruglabs.shrug.CollisionDetector;
import com.shruglabs.shrug.Shrug;
import com.shruglabs.shrug.client.TextureManager;

public class Food extends Entity {
	String id;
	private int lifeSpan;
	float xPos;
	float yPos;

	public Food() {
		this.lifeSpan = 0;
		this.id = UUID.randomUUID().toString();

	}

	@Override
	public void update() {
		if (this.lifeSpan < 20000) {
			this.lifeSpan++;
			drawFood();
			if (Shrug.shrug.getPlayer() != null) {
				if (CollisionDetector.rectangle2rectangle(this.collisionBox, Shrug.shrug.getPlayer().collisionBox)) {
					Shrug.shrug.it.remove();
					Shrug.shrug.foods.remove(this.id);
				}
			}
			System.out.println(this.lifeSpan);
		} else {
			Shrug.shrug.it.remove();
			Shrug.shrug.foods.remove(this.id);
		}
	}

	private void drawFood() {
		TextureManager.textures.get("apple").bind();
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

	public static void createFood() {

		float[] vertex1 = new float[] { 0.0125f, 0.0125f };
		float[] vertex2 = new float[] { 0.0125f, -0.0125f };
		float[] vertex3 = new float[] { -0.0125f, -0.0125f };
		float[] vertex4 = new float[] { -0.0125f, 0.0125f };
		Food food = new Food();
		Random rand = new Random();
		float x = rand.nextBoolean() ? rand.nextFloat() * 2 : -rand.nextFloat() * 2;
		float y = rand.nextBoolean() ? rand.nextFloat() * 2 : -rand.nextFloat() * 2;
		food.setVertices(food, vertex1, vertex2, vertex3, vertex4);
		float xPos = vertex1[0] + vertex3[0];
		float yPos = vertex1[1] + vertex3[1];
		float width = vertex1[0] + vertex1[0], height = width;
		setCollisionBox(food, new Rectangle2D.Float(xPos, yPos, width, height));
		vertex1 = new float[] { vertex1[0] += x, vertex1[1] += y };
		vertex2 = new float[] { vertex2[0] += x, vertex2[1] += y };
		vertex3 = new float[] { vertex3[0] += x, vertex3[1] += y };
		vertex4 = new float[] { vertex4[0] += x, vertex4[1] += y };
		food.setVertices(food, vertex1, vertex2, vertex3, vertex4);
		Shrug.shrug.foods.put(food.id, food);
	}

}
