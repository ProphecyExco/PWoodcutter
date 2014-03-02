package org.excobot.prophecy.pwcer.nodes;

import org.excobot.bot.script.Condition;
import org.excobot.game.api.methods.media.animable.GameObjects;
import org.excobot.game.api.methods.media.animable.actor.Players;
import org.excobot.game.api.methods.scene.Movement;
import org.excobot.game.api.methods.tab.Inventory;
import org.excobot.game.api.util.Time;
import org.excobot.game.api.wrappers.media.animable.object.GameObject;
import org.excobot.prophecy.pwcer.constants.Constants;
import org.excobot.prophecy.pwcer.data.Data;
import org.excobot.prophecy.pwcer.interfaces.Node;
import org.excobot.prophecy.pwcer.methods.Conditions;

public class Chop implements Node {

	@Override
	public boolean validate() {
		return Players.getLocal().getAnimation() == -1 && Conditions.isAtTrees() && !Inventory.isFull();
	}

	@Override
	public void execute() {
		Data.botState = "Chopping..";
		GameObject tree = GameObjects.getNearest(Constants.TREE_FILTER);
		if (tree != null) {
			if (tree.isOnGameScreen()) {
				if (tree.interact("Chop down")) {
					Time.sleep(300, 400);
					Time.sleep(new Condition() {

						@Override
						public boolean validate() {
							return Players.getLocal().isMoving();
						}
						
					}, 3000);
					Time.sleep(750, 1000);
				}
			} else {
				Movement.walkTileMM(tree);
				Time.sleep(300, 400);
				Time.sleep(new Condition() {

					@Override
					public boolean validate() {
						return Players.getLocal().isMoving();
					}
						
				}, 3000);
			}
		}
	}

}
