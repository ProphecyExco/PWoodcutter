package org.excobot.prophecy.pwcer.data;

import java.awt.Color;

public class Colors {
	
	private static final Color GREEN = new Color(55, 204, 67, 150);
	
	public static final Color getSkillColor(int alpha) {
		return new Color(GREEN.getRed(), GREEN.getGreen(), GREEN.getBlue(), alpha);
	}


}
