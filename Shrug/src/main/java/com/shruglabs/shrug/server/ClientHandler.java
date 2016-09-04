package com.shruglabs.shrug.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class ClientHandler implements Runnable {

	protected Socket clientSocket = null;
	protected String serverText = null;
	private ShrugServer server;
	

	public ClientHandler(ShrugServer server, String serverText) {
		this.server = server;
		this.serverText = serverText;
		
	}

	public void run() {
		try {
			this.clientSocket = this.server.serverSocket.accept();
		} catch (IOException e) {
			System.out.println("Shrug$Accept failed: " + this.server.serverSocket.getLocalPort());
		}
		try {
			InputStream input = this.clientSocket.getInputStream();
			OutputStream output = this.clientSocket.getOutputStream();
			output.write((this.serverText).getBytes());
			output.close();
			input.close();
			System.out.println("Shrug$ " + this.serverText);
		} catch (IOException e) {
			// report exception somewhere.
			e.printStackTrace();
		}
	}

}
