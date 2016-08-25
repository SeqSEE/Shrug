package com.shruglabs.shrug;

import java.awt.geom.Rectangle2D;

public class CollisionDetector {
	
	
	public static boolean rectangle2rectangle(Rectangle2D.Float cBox1, Rectangle2D.Float cBox2){
		return cBox1.intersects(cBox2);
	}

}
