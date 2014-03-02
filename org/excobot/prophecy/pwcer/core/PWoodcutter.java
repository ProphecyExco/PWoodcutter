package org.excobot.prophecy.pwcer.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import org.excobot.bot.event.listeners.PaintListener;
import org.excobot.bot.script.GameScript;
import org.excobot.bot.script.Manifest;
import org.excobot.game.api.event.events.MessageEvent;
import org.excobot.game.api.event.listeners.MessageListener;
import org.excobot.game.api.methods.input.Mouse;
import org.excobot.prophecy.pwcer.classes.ExpPainting;
import org.excobot.prophecy.pwcer.classes.MouseTrail;
import org.excobot.prophecy.pwcer.core.ui.UI;
import org.excobot.prophecy.pwcer.data.Data;
import org.excobot.prophecy.pwcer.interfaces.Node;
import org.excobot.prophecy.pwcer.methods.Methods;
import org.excobot.prophecy.pwcer.nodes.Banking;
import org.excobot.prophecy.pwcer.nodes.Chop;
import org.excobot.prophecy.pwcer.nodes.EvilTree;
import org.excobot.prophecy.pwcer.nodes.Walk;

@Manifest(authors = { "Prophecy" }, name = "PWoodcutter")
public class PWoodcutter extends GameScript implements PaintListener, MessageListener, MouseListener {

	ArrayList<Node> nodes = new ArrayList<Node>();
	public static UI ui;
	public static ExpPainting expPaint;
	public static MouseTrail mouseTrail;
	
	@Override
	public boolean start() {
		Collections.addAll(nodes, new EvilTree(), new Banking(), new Chop(), new Walk());
		ui = new UI();
		expPaint = new ExpPainting();
		mouseTrail = new MouseTrail();
		Data.runTime = System.currentTimeMillis();
		return true;
	}
	
	@Override
	public int execute() {
		if (!ui.isRunning) {
			for (Node n : nodes)
				try {
					if (n != null && n.validate()) {
						n.execute();
						break;
					}
				} catch (Exception e) {}
		}
		return 10;
	}
	
	@Override
	public void onFinish() {
	}

	@Override
	public void repaint(Graphics g) {
		g.setColor(new Color(Color.DARK_GRAY.getRed(), Color.DARK_GRAY.getGreen(), Color.DARK_GRAY.getBlue(), 100));
		g.fillRect(330, 275, 186, 63);
		g.setColor(Color.black);
		g.drawRect(330, 275, 186, 63);
		g.setColor(Color.white);
		g.drawString("State: "+Data.botState, 340, 288);
		g.drawString("Location: "+Data.tree.toString(), 340, 303);
		g.drawString("Logs cut (hr): "+Methods.formatNumber(Data.logsCut)+" ("+Methods.perHour(Data.logsCut)+")", 340, 318);
		g.drawString("Runtime: "+Methods.runTime(Data.runTime), 340, 333);
		expPaint.draw(g);
		mouseTrail.add(new Point(Mouse.getLocation().x + 10, Mouse.getLocation().y + 20));
		mouseTrail.draw(g);
		g.drawImage(Data.cursor, Mouse.getX(), Mouse.getY(), null);
	}

	@Override
	public void messageReceived(MessageEvent m) {
		if (m.getMessage().contains("You get some"))
			Data.logsCut++;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent m) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
	

}
