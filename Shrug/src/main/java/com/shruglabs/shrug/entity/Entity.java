package com.shruglabs.shrug.entity;

import java.awt.geom.Rectangle2D;

public abstract class Entity {

	public Rectangle2D.Float collisionBox;
	private Object[] vertices;
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;

	public abstract void update();

	/**
	 * @return the vertices as an Object Array of Float Arrays
	 */
	public Object[] getVertices(Entity entity) {
		return entity.vertices;
	}

	/**
	 * 
	 * @param entity
	 * @param v1
	 * @param v2
	 * @param v3
	 * @param v4
	 */
	public void setVertices(Entity entity, float[] v1, float[] v2, float[] v3, float[] v4) {
		Object[] vertices = new Object[] { v1, v2, v3, v4 };
		entity.vertices = vertices;
		if(entity.collisionBox != null)
			updateCollisionBox(entity);
	}

	/**
	 * @return the collisionBox
	 */
	public static Rectangle2D.Float getCollisionBox(Entity entity) {
		return entity.collisionBox;
	}

	/**
	 * @param collisionBox
	 *            the collisionBox to set
	 */
	public static void setCollisionBox(Entity entity, Rectangle2D.Float box) {
		entity.collisionBox = box;
		
	}

	/**
	 * 
	 */
	public static void updateCollisionBox(Entity entity) {
		float[] topRight = (float[]) entity.getVertices(entity)[0];
		float[] bottomLeft = (float[]) entity.getVertices(entity)[2];
		getCollisionBox(entity).x = topRight[0] + bottomLeft[0];
		getCollisionBox(entity).y = topRight[1] + bottomLeft[1];
	}

}
