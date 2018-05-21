package net.lakkie.rubs.storage;

import net.lakkie.rubs.NamingUtils;

public enum BattleWeatherType {

	STORM(-1.5f), SNOW(-0.25f), RAIN(-1f), CLOUDY(-0.5f), CLEAR(0.5f), SUNNY(1f);
	
	private final float moraleScalar;
	
	private BattleWeatherType(float moraleScalar) {
		this.moraleScalar = moraleScalar;
	}
	
	public float getMoraleScalar() {
		return this.moraleScalar;
	}
	
	public String toString() {
		return NamingUtils.capitalizeWordSequence(this.name());
	}
	
}
