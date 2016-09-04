package com.shruglabs.shrug.client.input;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import com.shruglabs.shrug.Shrug;
import com.shruglabs.shrug.client.ShrugClient;
import com.shruglabs.shrug.client.render.gui.MainDialog;

public class KeyboardHandler extends GLFWKeyCallback{

	public static boolean[] press = new boolean[65536];
	public static boolean[] repeat = new boolean[65536];
	public static boolean[] release = new boolean[65536];

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		press[key] = action == GLFW.GLFW_PRESS;
		repeat[key] = action == GLFW.GLFW_REPEAT;
		release[key] = action == GLFW.GLFW_RELEASE;
	}

	@Override
	public void callback(long args) {
		super.callback(args);
	}

	@Override
	public void close() {
		super.close();
	}

	public boolean keyPress(int key) {
		return press[key] && !repeat[key];
	}
	public boolean keyRepeat(int key) {
		return repeat[key];
	}
	public boolean keyRelease(int key) {
		return release[key];
	}

	public void keys(KeyboardHandler keyCallback, long window) {
		if (keyCallback.keyPress(GLFW_KEY_ESCAPE)){
			glfwSetWindowShouldClose(window, true);
			
		}
			
		if (keyCallback.keyPress(GLFW_KEY_F1)){
			Shrug.shrug.startServer(false);
		    Shrug.shrug.getClient().touchServer();}

			
			
		} 

			
			

	}


