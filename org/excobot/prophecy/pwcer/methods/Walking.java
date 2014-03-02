package org.excobot.prophecy.pwcer.methods;

import org.excobot.game.api.methods.media.animable.actor.Players;
import org.excobot.game.api.wrappers.scene.Tile;

public class Walking {
	
	public static final Tile getNextTile(final Tile[] tiles, final boolean reverse) {
		if (reverse) {
			for (int i = tiles.length-1; i >= 0; i--) {
				if (tiles[i].distance(Players.getLocal()) <= 17)
					return tiles[i];
			}
		} else {
			for (int i = 0; i < tiles.length; i++) {
				if (tiles[i].distance(Players.getLocal()) <= 17)
					return tiles[i];
			}
		}
		return null;
	}

}
