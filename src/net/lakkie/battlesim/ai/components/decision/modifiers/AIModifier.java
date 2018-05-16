package net.lakkie.battlesim.ai.components.decision.modifiers;

import java.util.ArrayList;
import java.util.List;

import net.lakkie.battlesim.storage.PositionedUnit;
import net.lakkie.battlesim.storage.RUBSBattle;

public abstract class AIModifier {

	public static final List<AIModifier> modifiers = new ArrayList<AIModifier>();
	
	public final String id;

	public AIModifier(String id) {
		this.id = id;
		modifiers.add(this);
	}

	public abstract float getEffect(RUBSBattle battle, PositionedUnit unit);

}
