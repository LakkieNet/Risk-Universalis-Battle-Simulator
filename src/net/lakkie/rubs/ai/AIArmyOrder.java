package net.lakkie.rubs.ai;

public enum AIArmyOrder {

	FULL_RETREAT, FALLBACK, STALEMATE, FULL_OUT_ATTACK, GENERAL_ADVANCE;
	
	public float retreatFactor, attackFactor, stillFactor;
	public float distanceMultiplier, speedMultiplier, mSpread, bSpread;
	public String friendlyName;
	
	static {
		new AIStageFileParser();
	}
	
	
}
