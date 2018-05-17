package net.lakkie.rubs.ai.components.decision.modifiers;

import java.util.ArrayList;
import java.util.List;

import net.lakkie.rubs.storage.PositionedUnit;
import net.lakkie.rubs.storage.RUBSBattle;

public abstract class AIModifier {

	public static final List<AIModifier> modifiers = new ArrayList<AIModifier>();
	
	public final String id;
	public final ModifierDatabase db;

	public AIModifier(String id) {
		this.id = id;
		this.db = ModifierRegistry.getById(this.id);
		modifiers.add(this);
	}

	public abstract float getEffect(RUBSBattle battle, PositionedUnit unit);

	public static AIModifier getModifier(String id) {
		for (AIModifier modifier : modifiers) {
			if (modifier.id.equals(id)) {
				return modifier;
			}
		}
		return null;
	}
	
	static {
		// Init modifiers
		new WeatherModifier();
	}
	
}
