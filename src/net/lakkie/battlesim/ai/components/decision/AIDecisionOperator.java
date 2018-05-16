package net.lakkie.battlesim.ai.components.decision;

import net.lakkie.battlesim.ai.RUBSBattleAI;
import net.lakkie.battlesim.ai.components.AIBattleComponent;
import net.lakkie.battlesim.ai.components.decision.modifiers.AIModifier;
import net.lakkie.battlesim.storage.PositionedUnit;
import net.lakkie.battlesim.storage.UnitType;

public class AIDecisionOperator extends AIBattleComponent {

	public AIDecisionOperator(RUBSBattleAI ai) {
		super(ai);
	}

	public void initUnit(PositionedUnit unit) {
		this.tickUnit(unit);
	}

	public void slowTickUnit(PositionedUnit unit) {
		
	}

	public void tickUnit(PositionedUnit unit) {
		
	}
	
	public float calculateMorale(PositionedUnit unit) {
		UnitType type = unit.getType();
		float moraleResult = type.getBaseMorale(unit.actionType);
		for (AIModifier modifier : AIModifier.modifiers) {
			moraleResult += modifier.getEffect(this.ai.battle, unit);
		}
		return moraleResult;
	}

}
