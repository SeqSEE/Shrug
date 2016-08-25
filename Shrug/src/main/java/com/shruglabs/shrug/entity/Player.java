package com.shruglabs.shrug.entity;

import static org.lwjgl.opengl.GL11.*;

import java.awt.geom.Rectangle2D;

import com.shruglabs.shrug.CollisionDetector;
import com.shruglabs.shrug.Shrug;
import com.shruglabs.shrug.client.TextureManager;

public class Player extends Entity {

	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;

	public Player(float f) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		getInput();
		drawPlayer();
	}


	private void getInput() {
		float[] v1 = (float[]) getVertices(this)[0];
		float[] v2 = (float[]) getVertices(this)[1];
		float[] v3 = (float[]) getVertices(this)[2];
		float[] v4 = (float[]) getVertices(this)[3];
		if (this.up) {
			float speed = 0.0015625f;
			if (this.left || this.right) {
				speed = 0.00078125f;
			}
			if (v3[1] < 0.94665f) {
				v1[1] += speed;
				v2[1] += speed;
				v3[1] += speed;
				v4[1] += speed;
			}
			setVertices(this, v1, v2, v3, v4);
			this.up = false;
		}
		if (this.down) {
			float speed = 0.0015625f;
			if (this.left || this.right) {
				speed = 0.00078125f;
			}
			if (v1[1] > -0.94665f) {
				v1[1] -= speed;
				v2[1] -= speed;
				v3[1] -= speed;
				v4[1] -= speed;
			}
			setVertices(this, v1, v2, v3, v4);
			this.down = false;
		}
		if (this.left) {
			float speed = 0.0015625f;
			if (this.up || this.down) {
				speed = 0.00078125f;
			}
			if (v1[0] > -0.94665f) {
				v1[0] -= speed;
				v2[0] -= speed;
				v3[0] -= speed;
				v4[0] -= speed;
			}
			setVertices(this, v1, v2, v3, v4);
			this.left = false;
		}
		if (this.right) {
			float speed = 0.0015625f;
			if (this.up || this.down) {
				speed = 0.00078125f;
			}
			if (v3[0] < 0.94665f) {
				v1[0] += speed;
				v2[0] += speed;
				v3[0] += speed;
				v4[0] += speed;
			}
			setVertices(this, v1, v2, v3, v4);
			this.right = false;
		}
	}

	private void drawPlayer() {
		TextureManager.textures.get("player_1").bind();
		glBegin(GL_QUADS);
		{
			float[] v1 = (float[]) Shrug.shrug.getPlayer().getVertices(this)[0];
			float[] v2 = (float[]) Shrug.shrug.getPlayer().getVertices(this)[1];
			float[] v3 = (float[]) Shrug.shrug.getPlayer().getVertices(this)[2];
			float[] v4 = (float[]) Shrug.shrug.getPlayer().getVertices(this)[3];
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

	public static void createPlayer() {
		Player player = new Player(10.0f);
		player.up = false;
		player.down = false;
		player.left = false;
		player.right = false;
		float[] vertex1 = new float[] { 0.05f, 0.05f };
		float[] vertex2 = new float[] { 0.05f, -0.05f };
		float[] vertex3 = new float[] { -0.05f, -0.05f };
		float[] vertex4 = new float[] { -0.05f, 0.05f };
		player.setVertices(player, vertex1, vertex2, vertex3, vertex4);
		float x = vertex1[0] + vertex3[0], y = vertex1[1] + vertex3[1];
		float width = 0.1f, height = width;
		setCollisionBox(player, new Rectangle2D.Float(x, y, width, height));
		Shrug.shrug.setPlayer(player);
		System.out.println("Player entered world.");
	}


}
