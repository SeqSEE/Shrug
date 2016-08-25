package com.shruglabs.shrug.client;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import com.shruglabs.shrug.Shrug;
import com.shruglabs.shrug.entity.Food;
import com.shruglabs.shrug.entity.Player;
import com.shruglabs.shrug.world.TallGrass;


public class KeyboardHandler extends GLFWKeyCallback {

	public static boolean[] keys = new boolean[65536];

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		keys[key] = action != GLFW.GLFW_RELEASE;
	}

	@Override
	public void callback(long args) {
		super.callback(args);
	}

	@Override
	public void close() {
		super.close();
	}

	public boolean isDown(int key) {
		return keys[key];
	}

	public void keys(KeyboardHandler keyCallback, long window) {
		Shrug shrug = Shrug.shrug;
		if (keyCallback.isDown(GLFW_KEY_ESCAPE))
			glfwSetWindowShouldClose(window, true);
		if (shrug.getPlayer() != null) {
			if (keyCallback.isDown(GLFW_KEY_W))
				shrug.getPlayer().up = true;
			if (keyCallback.isDown(GLFW_KEY_D))
				shrug.getPlayer().right = true;
			if (keyCallback.isDown(GLFW_KEY_S))
				shrug.getPlayer().down = true;
			if (keyCallback.isDown(GLFW_KEY_A))
				shrug.getPlayer().left = true;
		}
		
		if (keyCallback.isDown(GLFW_KEY_SPACE)) {
			if (shrug.getPlayer() == null) {
				Player.createPlayer();
			}
		}
		
		if (keyCallback.isDown(GLFW_KEY_F))
			Food.createFood();
		if (keyCallback.isDown(GLFW_KEY_G))
			TallGrass.createGrass();
		
	}

}
