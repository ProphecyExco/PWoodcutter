package org.excobot.prophecy.pwcer.nodes;

import org.excobot.bot.script.Condition;
import org.excobot.game.api.methods.media.animable.actor.Players;
import org.excobot.game.api.methods.scene.Movement;
import org.excobot.game.api.util.Time;
import org.excobot.game.api.wrappers.scene.Tile;
import org.excobot.prophecy.pwcer.data.Data;
import org.excobot.prophecy.pwcer.interfaces.Node;
import org.excobot.prophecy.pwcer.methods.Methods;

public class EvilTree implements Node {

	@Override
	public boolean validate() {
		return Players.getLocal().getInteracting() != null;
	}

	@Override
	public void execute() {
		Data.botState = "Avoiding evil tree..";
		Tile spot = Methods.getNearbyTile(Players.getLocal().getLocation());
		if (spot.isOnGameScreen())
			Movement.walkGameScreen(spot);
		else
			Movement.walkTileMM(spot);
		Time.sleep(300, 400);
		Time.sleep(new Condition() {

			@Override
			public boolean validate() {
				return Players.getLocal().isMoving();
			}
			
		}, 3000);
	}

}
