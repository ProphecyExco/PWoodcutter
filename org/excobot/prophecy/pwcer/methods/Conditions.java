package org.excobot.prophecy.pwcer.methods;

import org.excobot.game.api.methods.media.animable.actor.Players;
import org.excobot.game.api.methods.tab.Inventory;
import org.excobot.prophecy.pwcer.data.Data;

public class Conditions {
	
	public static final boolean isAtTrees() {
		return Data.tree.getPath()[Data.tree.getPath().length-1].distance(Players.getLocal().getLocation()) <= 16;
	}
	
	public static final boolean isAtBank() {
		return Data.tree.getPath()[0].distance(Players.getLocal().getLocation()) <= 16;
	}
	
	public static final boolean needsBanking() {
		return Inventory.isFull();
	}

}
