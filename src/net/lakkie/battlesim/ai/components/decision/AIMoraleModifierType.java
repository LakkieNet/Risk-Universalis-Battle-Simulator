package net.lakkie.battlesim.ai.components.decision;

import net.lakkie.battlesim.util.BooleanCallback;

public enum AIMoraleModifierType {

	BAD_WEATHER(null, -30f);
	
	private AIMoraleModifierType(BooleanCallback isActive, float value) {
	}
	
}
