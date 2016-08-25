package com.shruglabs.shrug.client;

import java.util.HashMap;

public class TextureManager {

	public static HashMap<String, Texture> textures = new HashMap<>();

	public static void initTextures() {
		textures.put("tallgrass_1", new Texture("/textures/tallgrass_1.png"));
		textures.put("tallgrass_2", new Texture("/textures/tallgrass_2.png"));
		textures.put("grass", new Texture("/textures/grass.png"));
		textures.put("player_1", new Texture("/textures/player.png"));
		textures.put("apple", new Texture("/textures/apple.png"));
	}

}
