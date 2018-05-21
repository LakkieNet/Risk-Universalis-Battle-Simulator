package net.lakkie.rubs.ai.components.decision.modifiers.impl;

import net.lakkie.rubs.ai.components.decision.modifiers.AIModifier;
import net.lakkie.rubs.storage.PositionedUnit;
import net.lakkie.rubs.storage.RUBSBattle;

public class WeatherModifier extends AIModifier {

	private float baseModifier;
	
	public WeatherModifier() {
		super("weather");
		this.baseModifier = Float.parseFloat(this.db.get("base_modifier"));
	}

	public float getEffect(RUBSBattle battle, PositionedUnit unit) {
		return this.baseModifier * battle.weather.getMoraleScalar();
	}

}
