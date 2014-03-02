package org.excobot.prophecy.pwcer.methods;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import org.excobot.game.api.methods.Calculations;
import org.excobot.game.api.methods.tab.Skills;
import org.excobot.game.api.wrappers.scene.Tile;
import org.excobot.prophecy.pwcer.data.Data;
public class Methods {
	
	public static final Tile getNearbyTile(Tile t) {
		for (int x = -1; x < 2; x++)
			for (int y = -1; y < 2; y++) {
				Tile temp = new Tile(t.getX() + x, t.getY() + y, t.getPlane());
				if (!temp.equals(t) && Calculations.canReach(temp))
					return temp;
			}
		return null;
	}
	
	public static final String runTime(long i) {
	    DecimalFormat nf = new DecimalFormat("00");
	    long millis = System.currentTimeMillis() - i;
	    long hours = millis / (1000 * 60 * 60);
	    millis -= hours * (1000 * 60 * 60);
	    long minutes = millis / (1000 * 60);
	    millis -= minutes * (1000 * 60);
	    long seconds = millis / 1000;
	    	return nf.format(hours) + ":" + nf.format(minutes) + ":" + nf.format(seconds);
	}
	
	public static final String countDown(long i) {
	    DecimalFormat nf = new DecimalFormat("00");
	    long millis = i;
	    long hours = millis / (1000 * 60 * 60);
	    millis -= hours * (1000 * 60 * 60);
	    long minutes = millis / (1000 * 60);
	    millis -= minutes * (1000 * 60);
	    long seconds = millis / 1000;
	    	return nf.format(hours) + ":" + nf.format(minutes) + ":" + nf.format(seconds);
	}
	
	public static final String formatNumber(int start) {
        DecimalFormat nf = new DecimalFormat("0.0");
        double i = start;
        if(i >= 1000000) {
            return nf.format((i / 1000000)) + "m";
        }
        if(i >=  1000) {
            return nf.format((i / 1000)) + "k";
        }
        return ""+start;
    }

	public static final String perHour(int gained) {
		return formatNumber((int) ((gained) * 3600000D / (System.currentTimeMillis() - Data.runTime)));
	}
	
	private static final int getPerHour(final int value) {
		return (int) (value * 3600000D / (System.currentTimeMillis() - Data.runTime));
	}
	
	public static final long ttl(int gained, Skills skill) {
		  int currentLevel = skill.getRealLevel();
		  int currentExp = skill.getExperience(); 
		  int nextLevelExp = ExpTable.getExpForLevel(currentLevel + 1); 
		  int expToNextLevel = nextLevelExp - currentExp;
		return (long) (((double) expToNextLevel * 3600000.0) / (double) getPerHour(gained));
	}
	
	public static final Image getImage(String url) {
		try { return ImageIO.read(new URL(url)); } 
		catch(IOException e) { return null; }
	}

}
