package org.excobot.prophecy.pwcer.classes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.excobot.game.api.methods.tab.Skills;
import org.excobot.prophecy.pwcer.data.Colors;
import org.excobot.prophecy.pwcer.methods.ExpTable;
import org.excobot.prophecy.pwcer.methods.Methods;

public class ExpPainting {
	
	private static final int WIDTH = 485;
	
	private final HashMap<Skills, Integer> expMap = new HashMap<Skills, Integer>();
	private final HashMap<Skills, Point> rectangleMap = new HashMap<>();
	
	public ExpPainting() {
		init();
	}
	
	private void init() {
		for (Skills skill : Skills.values())
			expMap.put(skill, skill.getExperience());
	}
	
	private void update() {
		for (Skills skill : Skills.values())
			if (getGained(skill) != 0 && !rectangleMap.containsKey(skill))
				rectangleMap.put(skill, new Point(8, 342 + rectangleMap.size() * 21));
	}
	
	private final Point getSkillPoint(final Skills skill) {
		return rectangleMap.get(skill);
	}
	
	public final int getGained(final Skills skill) {
		return skill.getExperience() - expMap.get(skill);
	}
	
	public final Skills[] getTrainedSkills() {
		ArrayList<Skills> skillList = new ArrayList<>();
		for (Skills skill : Skills.values())
			if (getGained(skill) != 0)
				skillList.add(skill);
		return skillList.toArray(new Skills[skillList.size()]);
	}
	
	private final String getPercentageString(final Skills skill) {
	    DecimalFormat nf = new DecimalFormat("0.0");
		return nf.format((double)(ExpTable.getExpFromLastLevel(skill)*100)/ExpTable.getExperienceToNextLevel(skill));
	}
	
	public final double getPercentage(final Skills skill) {
		return (double)((ExpTable.getExpFromLastLevel(skill)*100)/ExpTable.getExperienceToNextLevel(skill));
	}
	
	private final Rectangle getSkillBar(final Skills skill) {
		return new Rectangle(getSkillPoint(skill).x + 1, getSkillPoint(skill).y + 5, WIDTH, 19);
	}
	
	private final Rectangle getPercentageBar(final Skills skill) {
		return new Rectangle(getSkillPoint(skill).x + 1, getSkillPoint(skill).y + 5, (int) ((WIDTH) * (getPercentage(skill)/100)), 19);
	}
	
	private void drawSkill(final Graphics g, final Skills skill) {
		Rectangle bg = getSkillBar(skill);
		Rectangle perc = getPercentageBar(skill);
		g.setColor(Colors.getSkillColor(80));
		g.fillRect(bg.x, bg.y, bg.width, bg.height);
		g.setColor(Colors.getSkillColor(200));
		g.fillRect(perc.x, perc.y, perc.width, perc.height);
		g.setColor(Color.BLACK);
		g.drawRect(bg.x, bg.y, bg.width, bg.height);
		g.setColor(Color.black);
		g.drawString((skill.name().charAt(0)+skill.name().substring(1).toLowerCase())+": % to next level ("+(skill.getRealLevel()+1)+"): "+getPercentageString(skill)+"% - Exp (hr): "+Methods.formatNumber(this.getGained(skill))+" ("+Methods.perHour(this.getGained(skill))+") - TTL: "+Methods.countDown(Methods.ttl(getGained(skill), skill)), bg.x + 4, bg.y + 15);
	}
	
	public void draw(final Graphics g) {
		update();
		for (Skills skill : getTrainedSkills())
			drawSkill(g, skill);
	}

}
