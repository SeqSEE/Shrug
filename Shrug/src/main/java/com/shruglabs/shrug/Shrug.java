package com.shruglabs.shrug;

import com.shruglabs.shrug.client.ShrugClient;
import com.shruglabs.shrug.server.ShrugServer;

/*
 * This Java source file was auto generated by running 'gradle buildInit --type java-library'
 * by 'SeqSEE' with Gradle 2.9
 *
 * @author SeqSEE
 */
public class Shrug {

	private ShrugServer server;
	private ShrugClient client;
	public static Shrug shrug;

	public static void main(String[] args) {
		if (args.length == 0) {
			shrug = new Shrug();
			shrug.startClient(args);
			
			
		} else {
			if (args[0].equals("--server")) {
				shrug = new Shrug();
				shrug.startServer(true);
			} else {
				System.out.println("Invalid arguments!");
				return;
			}
		}

	}

	public void startServer(boolean dedicated) {
		if (dedicated) {
			shrug.setServer(new ShrugServer());
			new Thread(shrug.getServer()).run();
			System.out.println("Shrug$ Done!");
		} else {
			System.out.println("shrug$ Initializing Integrated Server");
			shrug.setServer(new ShrugServer());
			shrug.getServer().setPort(37756);
			new Thread(shrug.getServer()).run();
			System.out.println("shrug$ Done!");
		}
	}

	public void startClient(String[] args) {
		if(args.length == 0){
			shrug.setClient(new ShrugClient());
			shrug.getClient().run();
			
		}
		

	}

	private void setClient(ShrugClient client) {
		shrug.client = client;

	}

	public ShrugServer getServer() {
		return shrug.server;
	}

	public void setServer(ShrugServer server) {
		shrug.server = server;
	}

	public ShrugClient getClient() {
		return shrug.client;
	}

}
