package org.excobot.prophecy.pwcer.methods;

import org.excobot.game.api.methods.tab.Skills;
 
public final class ExpTable {

    
    public static final int getExperienceToNextLevel(final Skills skill) {
    	return getExpForLevel(skill.getRealLevel() + 1) - getExpForLevel(skill.getRealLevel());
    }
    
    public static final int getExpFromLastLevel(final Skills skill) {
    	return skill.getExperience() - getExpForLevel(skill.getRealLevel());
    }

    public static final int getExpGap(final int from, final int to) {
        int exp = 0;
        for (int i = 0; i < to - from; i++) {
            exp += (Math.floor(i + from + 300.0 * Math.pow(2.0, (i + from) / 7.0)) / 4.0);
        }
        return exp;
    }

    public static final int getExpForLevel(final int lvl) {
        return getExpGap(1, lvl);
    }
}