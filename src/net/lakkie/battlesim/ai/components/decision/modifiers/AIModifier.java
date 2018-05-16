package net.lakkie.battlesim.ai.components.decision.modifiers;

import net.lakkie.battlesim.ai.components.decision.AIMoraleModifierType;
import net.lakkie.battlesim.storage.PositionedUnit;
import net.lakkie.battlesim.storage.RUBSBattle;
import net.lakkie.battlesim.util.BooleanCallback;

public abstract class AIModifier implements BooleanCallback {

	public final AIMoraleModifierType type;
	public final String id;
	
	public AIModifier(String id, AIMoraleModifierType type) {
		this.id = id;
		this.type = type;
	}
	
	public abstract boolean isActive(RUBSBattle battle, PositionedUnit unit);
	
	public boolean get(Object... args) {
		return this.isActive((RUBSBattle) args[0], (PositionedUnit) args[1]);
	}
	
}
