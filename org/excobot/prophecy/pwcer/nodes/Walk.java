package org.excobot.prophecy.pwcer.nodes;

import org.excobot.bot.script.Condition;
import org.excobot.game.api.methods.media.animable.actor.Players;
import org.excobot.game.api.methods.scene.Movement;
import org.excobot.game.api.util.Time;
import org.excobot.game.api.wrappers.scene.Tile;
import org.excobot.prophecy.pwcer.data.Data;
import org.excobot.prophecy.pwcer.interfaces.Node;
import org.excobot.prophecy.pwcer.methods.Conditions;
import org.excobot.prophecy.pwcer.methods.Walking;

public class Walk implements Node {

	@Override
	public boolean validate() {
		return Conditions.needsBanking() && !Conditions.isAtBank() || !Conditions.isAtTrees();
	}

	@Override
	public void execute() {
		Data.botState = "Walking..";
		final Tile t = Walking.getNextTile(Data.tree.getPath(), (Conditions.needsBanking() ? false : true));
		if (t != null && Movement.walkTileMM(t)) {
			Time.sleep(300, 400);
			Time.sleep(new Condition() {

				@Override
				public boolean validate() {
					return t.distance(Players.getLocal()) > 5;
				}
				
			}, 3000);
		}
	}

}
