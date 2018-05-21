package net.lakkie.rubs.ai.components.decision.modifiers.impl;

import net.lakkie.rubs.ai.components.decision.modifiers.AIModifier;
import net.lakkie.rubs.storage.PositionedUnit;
import net.lakkie.rubs.storage.RUBSBattle;

public class CasualtyModifier extends AIModifier {

	private float baseValue;
	
	public CasualtyModifier() {
		super("casualty");
		this.baseValue = Float.parseFloat(this.db.get("base_value"));
	}

	public float getEffect(RUBSBattle battle, PositionedUnit unit) {
		float multiplier = 1f - ((float)(unit.infantry + unit.cavalry + unit.armor) / (float)(unit.originalTotal));
		return this.baseValue * multiplier;
	}

}
