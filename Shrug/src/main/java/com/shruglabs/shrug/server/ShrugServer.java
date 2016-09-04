package com.shruglabs.shrug.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.shruglabs.shrug.Reference;
import com.shruglabs.shrug.Shrug;

public class ShrugServer implements Runnable {

	public static int connectionCount;
	private static ShrugServer server;

	private Scanner console;
	ServerSocket serverSocket;
	private int port;
	private boolean serverClose;
	public void start() {
		System.out.println("Shrug$ Shrug " + Reference.VER);
		server.init();
		System.out.println("Shrug$ Listening for a connection");
		new Thread(new ClientHandler(server, Shrug.class.getProtectionDomain().getCodeSource().getLocation().toString())).run();

	}

	public void init() {

		if (!server.serverClose) {
			while (server.port == 0) {
				server.console = new Scanner(System.in);
				System.out.println("Shrug$ Enter a port between 1025-62000");
				System.out.print("Shrug$ ");
				if (server.console.hasNext() && server.console.next().equalsIgnoreCase("quit"))
					server.serverClose = true;
				if (server.console.hasNextInt()) {
					server.port = server.console.nextInt();

					if (server.port < 1024)
						server.init();
					if (server.port > 65535)
						server.init();
				}

			}

			System.out.println("Shrug$ Port set to " + server.port);
			System.out.println("Shrug$ Initializing Server Socket");
			try {
				server.serverSocket = new ServerSocket(server.port);
			} catch (IOException e) {
				System.out.println("Shrug$ Could not listen on port " + server.port);
			}

			if (server.serverSocket != null)
				System.out.println("Shrug$ Server socket created on: " + server.serverSocket.getLocalPort());

		}

	}

	public void listen() {
		if (!server.serverClose) {
			
				System.out.println("Shrug$ Listening for a connection");
				new Thread(new ClientHandler(server, Shrug.class.getProtectionDomain().getCodeSource().getLocation().toString())).run();
			
		}
	}

	@Override
	public void run() {
		server = this;
		server.start();
	}

	public void setPort(int port) {
		server = this;
		server.port = port;
	}

}
