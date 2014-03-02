package org.excobot.prophecy.pwcer.nodes;

import org.excobot.bot.script.Condition;
import org.excobot.game.api.methods.media.animable.GroundItems;
import org.excobot.game.api.methods.media.animable.actor.Players;
import org.excobot.game.api.methods.scene.Camera;
import org.excobot.game.api.methods.scene.Movement;
import org.excobot.game.api.methods.tab.Inventory;
import org.excobot.game.api.util.Time;
import org.excobot.game.api.wrappers.media.animable.GroundItem;
import org.excobot.prophecy.pwcer.constants.Constants;
import org.excobot.prophecy.pwcer.interfaces.Node;

public class LootNest implements Node {

	@Override
	public boolean validate() {
		return GroundItems.getNearest(Constants.NEST_FILTER) != null && !Inventory.isFull();
	}

	@Override
	public void execute() {
		final GroundItem nest = GroundItems.getNearest(Constants.NEST_FILTER);
		if (nest != null) {
			if (nest.isOnGameScreen()) {
				if (nest.interact("Take")) {
					Time.sleep(400, 500);
					Time.sleep(new Condition() {

						@Override
						public boolean validate() {
							return Players.getLocal().isMoving();
						}
						
					}, 3000);
				}
			} else {
				Camera.turnTo(nest);
				if (!nest.isOnGameScreen()) {
					Movement.walkTileMM(nest);
					Time.sleep(400, 500);
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

}
