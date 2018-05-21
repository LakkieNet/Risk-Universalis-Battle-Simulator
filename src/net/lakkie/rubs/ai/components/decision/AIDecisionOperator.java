package net.lakkie.rubs.ai.components.decision;

import net.lakkie.rubs.ai.AIArmyDecision;
import net.lakkie.rubs.ai.AIArmyOrder;
import net.lakkie.rubs.ai.RUBSBattleAI;
import net.lakkie.rubs.ai.components.AIBattleComponent;
import net.lakkie.rubs.ai.components.decision.modifiers.AIModifier;
import net.lakkie.rubs.storage.PositionedUnit;
import net.lakkie.rubs.storage.UnitType;
import net.lakkie.rubs.util.BasicUtils;
import net.lakkie.rubs.util.MathUtils;

public class AIDecisionOperator extends AIBattleComponent {

	public AIDecisionOperator(RUBSBattleAI ai) {
		super(ai);
	}

	public void initUnit(PositionedUnit unit) {
	}

	public void postInitUnit(PositionedUnit unit) {
		this.slowTickUnit(unit);
	}

	public void slowTickUnit(PositionedUnit unit) {
		AIArmyOrder order = unit.getArmy(this.ai.battle).order;
		float randomSelection = (float) (BasicUtils.getRandom().nextInt(600) - 300);
		randomSelection += unit.getMorale();
		// Order is retreat, still, attack

		float normalizedSelection = (randomSelection / 300f + 1) / 2f;

		if (normalizedSelection <= order.retreatFactor) {
			unit.decision = AIArmyDecision.RETREAT;
			return;
		}
		if (normalizedSelection <= order.retreatFactor + order.stillFactor) {
			unit.decision = AIArmyDecision.STILL;
			return;
		}
		if (normalizedSelection <= order.retreatFactor + order.stillFactor + order.attackFactor) {
			unit.decision = AIArmyDecision.ATTACK;
			return;
		}
	}

	public void tickUnit(PositionedUnit unit) {

	}

	public float calculateMorale(PositionedUnit unit) {
		UnitType type = unit.getType();
		float moraleResult = type.getBaseMorale(unit.actionType);
		for (AIModifier modifier : AIModifier.modifiers) {
			moraleResult += modifier.getEffect(this.ai.battle, unit);
		}
		return MathUtils.cap(moraleResult, -300f, 300f);
	}

}
