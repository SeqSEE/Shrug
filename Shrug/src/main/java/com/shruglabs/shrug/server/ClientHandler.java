package com.shruglabs.shrug.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class ClientHandler implements Runnable {

	protected Socket clientSocket = null;
	protected String serverText = null;
	

	public ClientHandler(Socket clientSocket, String serverText) {
		this.clientSocket = clientSocket;
		this.serverText = serverText;
		
	}

	public void run() {
		try {
			InputStream input = clientSocket.getInputStream();
			OutputStream output = clientSocket.getOutputStream();
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
