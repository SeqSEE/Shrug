package com.shruglabs.shrug.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerHandler implements Runnable {
	
	
	protected Socket serverSocket = null;
	protected static BufferedReader in = null;
	protected static PrintWriter out = null;
	
	

	@Override
	public void run() {
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

}
