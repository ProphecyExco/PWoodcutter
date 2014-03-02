package org.excobot.prophecy.pwcer.constants;

import org.excobot.game.api.wrappers.scene.Tile;

public enum Trees {
	
	SEERS_VILLAGE_MAPLES("Maple tree", new Tile(2726, 3491, 0), new Tile(2725, 3500, 0)),
	
	NORTHERN_MAGIC_TREES("Magic tree", new Tile(2726, 3490, 0), new Tile(2726, 3480, 0), new Tile(2727, 3470, 0), 
			new Tile(2726, 3458, 0), new Tile(2730, 3449, 0), new Tile(2730, 3437, 0), new Tile(2718, 3426, 0),
			new Tile(2707, 3428, 0), new Tile(2694, 3424, 0)),
			
	SOUTHERN_MAGIC_TREES("Magic tree", new Tile(2726, 3490, 0), new Tile(2726, 3480, 0), new Tile(2727, 3470, 0), 
			new Tile(2726, 3458, 0), new Tile(2730, 3449, 0), new Tile(2730, 3437, 0), new Tile(2718, 3426, 0), new Tile(2715, 3416, 0),
			new Tile(2715, 3401, 0), new Tile(2703, 3392, 0), new Tile(2702, 3398, 0)),
			
	SEERS_VILLAGE_YEWS("Yew", new Tile(2726, 3491, 0), new Tile(2724, 3485, 0), new Tile(2726, 3477, 0), new Tile(2721, 3468, 0),
			new Tile(2717, 3462, 0), new Tile(2710, 3462, 0)),
			
	SEERS_VILLAGE_WILLOWS("Willow", new Tile(2725, 3491, 0), new Tile(2719, 3500, 0), new Tile(2712, 3510, 0)),
	
	SEERS_VILLAGE_OAKS("Oak", new Tile(2725, 3491, 0), new Tile(2719, 3480, 0)),
			
	CATHERBY_YEWS("Yew", new Tile(2809, 3439, 0), new Tile(2801, 3432, 0), new Tile(2788, 3432, 0), new Tile(2783, 3429, 0),
			new Tile(2770, 3430, 0), new Tile(2758, 3431, 0)),
			
	CATHERBY_WILLOWS("Willow", new Tile(2809, 3439, 0), new Tile(2801, 3432, 0), new Tile(2788, 3432, 0), new Tile(2783, 3429, 0));
	
	private String name;
	private Tile[] path;
	Trees(String name, Tile ... path) {
		this.name = name;
		this.path = path;
	}
	
	@Override
	public String toString() {
		return name().charAt(0)+name().substring(1).toLowerCase().replace("_", " ");
	}
	
	public final String getName() {
		return name;
	}
	
	public final Tile[] getPath() {
		return path;
	}

}
