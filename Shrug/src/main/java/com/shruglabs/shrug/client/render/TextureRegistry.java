package com.shruglabs.shrug.client.render;

import java.util.HashMap;


public class TextureRegistry {
	

	public static HashMap<String, Texture> textures = new HashMap<>();

	public static void initTextures() {
		textures.put("shruglabs", new Texture("/textures/shruglabs_logo.png"));
		textures.put("player_1", new Texture("/textures/player.png"));
		textures.put("apple", new Texture("/textures/apple.png"));
		textures.put("grass", new Texture("/textures/grass.png"));
		textures.put("tallgrass_1", new Texture("/textures/tallgrass_1.png"));
		textures.put("tallgrass_2", new Texture("/textures/tallgrass_2.png"));
		

	}

}
