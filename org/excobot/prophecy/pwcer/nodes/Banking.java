package org.excobot.prophecy.pwcer.nodes;

import org.excobot.bot.script.Condition;
import org.excobot.game.api.methods.media.Bank;
import org.excobot.game.api.methods.media.animable.GameObjects;
import org.excobot.game.api.methods.media.animable.actor.Players;
import org.excobot.game.api.methods.scene.Camera;
import org.excobot.game.api.methods.scene.Movement;
import org.excobot.game.api.util.Time;
import org.excobot.game.api.wrappers.media.animable.object.GameObject;
import org.excobot.prophecy.pwcer.constants.Constants;
import org.excobot.prophecy.pwcer.data.Data;
import org.excobot.prophecy.pwcer.interfaces.Node;
import org.excobot.prophecy.pwcer.methods.Conditions;

public class Banking implements Node {

	@Override
	public boolean validate() {
		return Conditions.needsBanking() && Conditions.isAtBank();
	}

	@Override
	public void execute() {
		Data.botState = "Banking..";
		GameObject bank = GameObjects.getNearest("Bank booth");
		if (bank != null) {
			if (Bank.isOpen()) {
				Bank.depositAllExcept(Constants.AXE_NAMES);
			} else {
				if (bank.isOnGameScreen()) {
					if (bank.interact("Bank")) {
						Time.sleep(300, 400);
						Time.sleep(new Condition() {

							@Override
							public boolean validate() {
								return Players.getLocal().isMoving();
							}
							
						}, 3000);
					}
				} else {
					Camera.turnTo(bank);
					if (!bank.isOnGameScreen()) {
						Movement.walkTileMM(bank);
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
	}

}
