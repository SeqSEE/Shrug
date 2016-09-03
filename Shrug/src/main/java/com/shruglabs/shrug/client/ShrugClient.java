package com.shruglabs.shrug.client;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.shruglabs.shrug.Reference;
import com.shruglabs.shrug.client.input.KeyboardHandler;
import com.shruglabs.shrug.client.render.TextureRegistry;
import com.shruglabs.shrug.client.render.WindowHandler;

public class ShrugClient implements Runnable{
	
	private static Socket serverSocket;
	private static BufferedReader in;
	private static PrintWriter out;
	private WindowHandler windowHandler;
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
		try {
			System.out.println("shrug$ Connecting to port 37756");
			serverSocket = new Socket("localhost", 37756);
			if (serverSocket.isConnected()){
				out = new PrintWriter(serverSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));	
			}
			else{
				out = null;
				in = null;
			}
			
		} catch (UnknownHostException e) {
			System.out.println("shrug$ Unknown host");
		} catch (IOException e) {
			System.out.println("shrug$ I/O Error");
		}
		try {
			String line;
			if (in != null)
				line = in.readLine();
			else
				line = "shrug$ No response from server";
			System.out.println("shrug$ " + line);

		} catch (IOException e) {
			System.out.println("shrug$ I/O Error");
		}

	}

	@Override
	public void run() {
		client = this;
		client.start();
		
	}


}
