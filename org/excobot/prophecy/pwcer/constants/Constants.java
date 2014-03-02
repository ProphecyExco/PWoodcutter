package org.excobot.prophecy.pwcer.constants;

import org.excobot.game.api.methods.media.animable.actor.Players;
import org.excobot.game.api.util.impl.Filter;
import org.excobot.game.api.wrappers.media.animable.GroundItem;
import org.excobot.game.api.wrappers.media.animable.object.GameObject;
import org.excobot.prophecy.pwcer.data.Data;

public class Constants {
	
	public static final String[] AXE_NAMES = {"Dragon axe", "Rune axe", "Adamant axe", "Mithril axe", "Black axe", "Steel axe", "Iron axe", "Bronze axe"};
	
	public static final Filter<GameObject> TREE_FILTER = new Filter<GameObject>() {

		@Override
		public boolean accept(GameObject ob) {
			return ob != null && ob.getName().toLowerCase().equals(Data.tree.getName().toLowerCase()) && ob.getLocation().distance(Data.tree.getPath()[Data.tree.getPath().length-1]) <= 16;
		}
		
	};
	
	public static final Filter<GroundItem> NEST_FILTER = new Filter<GroundItem>() {

		@Override
		public boolean accept(GroundItem i) {
			return i != null && i.getName().toLowerCase().contains("nest") && i.getLocation().distance(Players.getLocal()) <= 17;
		}
		
	};

}
