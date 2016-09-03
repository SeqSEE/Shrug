package com.shruglabs.shrug.client.render;

import static org.lwjgl.opengl.GL11.*;

public class WindowHandler {


		private int width;
		private int height;
		private int xpos;
		private int ypos;
		private float[] color;

		/**
		 * @return the width
		 */
		public int getWidth() {
			return width;
		}

		/**
		 * @param width
		 *            the width to set
		 */
		public void setWidth(int width) {
			this.width = width;
		}

		/**
		 * @return the height
		 */
		public int getHeight() {
			return height;
		}

		/**
		 * @param height
		 *            the height to set
		 */
		public void setHeight(int height) {
			this.height = height;
		}

		/**
		 * @return the xpos
		 */
		public int getXpos() {
			return xpos;
		}

		/**
		 * @param xpos
		 *            the xpos to set
		 */
		public void setXpos(int xpos) {
			this.xpos = xpos;
		}

		/**
		 * @return the ypos
		 */
		public int getYpos() {

			return ypos;
		}

		/**
		 * @param ypos
		 *            the ypos to set
		 */
		public void setYpos(int ypos) {
			this.ypos = ypos;
		}

		/**
		 * @return the color Return the color as RGBA value {float Red, float Green,
		 *         float Blue, float Alpha} Returns {0.0f, 0.0f, 0.0f, 0.0f} if not
		 *         setup in initialization
		 */
		public float[] getColor() {
			if (color == null)
				setColor(new float[] { 0.7f, 0.7f, 0.7f, 1.0f });
			return color;
		}

		/**
		 * @param color
		 *            Color to set as RGBA value {float Red, float Green, float
		 *            Blue, float Alpha}
		 */
		public void setColor(float[] color) {
			this.color = color;
		}

		/**
		 * 
		 * @param width
		 * @param height
		 */
		public void setDemensions(int width, int height) {
			this.width = width;
			this.height = height;

		}

		/**
		 * 
		 * @return int array { width, height }
		 */
		public int[] getDemensions() {
			if (this.width == 0 || this.height == 0)
				setDemensions(600, 600);
			return new int[] { this.width, this.height };
		}

		public void drawSplash() {
			if (TextureRegistry.textures.get("shruglabs") != null) {
				TextureRegistry.textures.get("shruglabs").bind();
				glBegin(GL_QUADS);
				{

					glTexCoord2i(0, 0);
					glVertex2i(-1, 1);
					glTexCoord2i(0, 1);
					glVertex2i(-1, -1);
					glTexCoord2i(1, 1);
					glVertex2i(1, -1);
					glTexCoord2i(1, 0);
					glVertex2i(1, 1);
				}
				glEnd();
			}
		}

	
}
