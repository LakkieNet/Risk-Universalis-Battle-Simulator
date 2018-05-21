package net.lakkie.rubs.ai;

import net.lakkie.rubs.readers.ArmyConfigDecisionReader;

public enum AIArmyDecision {

	RETREAT, STILL, ATTACK;

	public float targetMultiplier = 1f;
	public String friendlyName;
	
	static {
		new ArmyConfigDecisionReader();
	}
	
}
