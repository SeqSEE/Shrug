/**
 *  Shrug is a lightweight open source Java game
 */
package com.shruglabs.shrug;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.shruglabs.shrug.client.KeyboardHandler;
import com.shruglabs.shrug.client.TextureManager;
import com.shruglabs.shrug.client.WindowHandler;
import com.shruglabs.shrug.entity.Food;
import com.shruglabs.shrug.entity.Player;
import com.shruglabs.shrug.world.TallGrass;

/**
 * @author SeqSEE
 *
 */
public class Shrug {

	public static Shrug shrug;
	private long window;
	public static WindowHandler windowHandler;
	private KeyboardHandler keyCallback;
	private static Player player;
	public HashMap<String, Food> foods = new HashMap<>();
	public Iterator<Entry<String, Food>> it;
	public List<TallGrass> grass = new ArrayList<TallGrass>();
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		shrug = new Shrug();
		shrug.run();
	}

	public void run() {
		System.out.println("Demensia v" + Reference.VER);
		try {
			init();
			loop();

		} finally {

		}
	}

	private void init() {
		System.out.println("Initializing");
		GLFWErrorCallback.createPrint(System.err).set();
		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");

		windowHandler = new WindowHandler();
		windowHandler.setDemensions(640, 640);
		windowHandler.setColor(new float[] { 1.0f, 1.0f, 1.0f, 0.0f });
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		window = glfwCreateWindow(windowHandler.getDemensions()[0], windowHandler.getDemensions()[1], "", NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidmode.width() - windowHandler.getDemensions()[0]) / 2,
				(vidmode.height() - windowHandler.getDemensions()[1]) / 2);
		glfwMakeContextCurrent(window);
		keyCallback = new KeyboardHandler();
		glfwSetKeyCallback(window, keyCallback);
		glfwSwapInterval(1);
		glfwShowWindow(window);
		GL.createCapabilities();
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		TextureManager.initTextures();
		for(int i =0; i < 1000; i++){
			TallGrass.createGrass();
		}
		
	}

	private void loop() {
		while (!glfwWindowShouldClose(window)) {
			keyCallback.keys(keyCallback, window);
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			windowHandler.drawBackground();
			for(TallGrass grass : this.grass){
				grass.drawGrass();
			}
			if (!foods.isEmpty()) {
				it = this.foods.entrySet().iterator();
				while (it.hasNext()) {
					Entry<String, Food> entry = it.next();
					entry.getValue().update();
				}
			}
			if (player != null)
				player.update();
			glfwSwapBuffers(window);
			glfwPollEvents();
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player p) {
		player = p;

	}

}
