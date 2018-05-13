package net.lakkie.battlesim.ai;

public enum AIArmyOrder {

	FULL_RETREAT, FALLBACK, STALEMATE, FULL_OUT_ATTACK, GENERAL_ADVANCE;
	
	public float retreatFactor, attackFactor, stillFactor;
	public String friendlyName;
	
	static {
		new AIStageFileParser();
	}
	
	
}
