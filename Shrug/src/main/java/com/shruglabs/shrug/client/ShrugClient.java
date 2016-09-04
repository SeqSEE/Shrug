package com.shruglabs.shrug.client;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.shruglabs.shrug.Reference;
import com.shruglabs.shrug.client.input.KeyboardHandler;
import com.shruglabs.shrug.client.render.TextureRegistry;
import com.shruglabs.shrug.client.render.WindowHandler;
import com.shruglabs.shrug.client.render.gui.MainDialog;

public class ShrugClient implements Runnable{
	

	public WindowHandler windowHandler;
	private long window;
	private KeyboardHandler keyCallback;
	public static ShrugClient client;
	
	public void start() {
		System.out.println("Shrug shrug$ -" + Reference.VER);
		try {
			init();
			loop();
		} finally {

		}

	}
	
	public void close(){
		glfwSetWindowShouldClose(window, true);
	}
	
	private void init() {
		System.out.println("shrug$ Client Initializing");
		client = this;
		GLFWErrorCallback.createPrint(System.err).set();
		System.out.println("shrug$ Initializing GLFW");
		if (!glfwInit())
			throw new IllegalStateException("shrug$ Unable to initialize GLFW");
		System.out.println("shrug$ Setting up window");
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		windowHandler = new WindowHandler();
		windowHandler.setDemensions(640, 640);
		windowHandler.setColor(new float[] { 1.0f, 1.0f, 1.0f, 0.0f });
		window = glfwCreateWindow(windowHandler.getDemensions()[0], windowHandler.getDemensions()[1], "", NULL, NULL);

		if (window == NULL)
			throw new RuntimeException("shrug$ Failed to create the GLFW window");

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
		System.out.println("shrug$ Initializing Textures");
		TextureRegistry.initTextures();
		new MainDialog();
		
		

	}

	private void loop() {
		while (!glfwWindowShouldClose(window)) {
			keyCallback.keys(keyCallback, window);
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			windowHandler.drawSplash();
			glfwSwapBuffers(window);
			glfwPollEvents();
		}

	}

	public void touchServer() {
		new Thread(new ServerHandler()).run();

	}

	@Override
	public void run() {
		client = this;
		client.start();
		
	}


}
