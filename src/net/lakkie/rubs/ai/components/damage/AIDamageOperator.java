package net.lakkie.rubs.ai.components.damage;

import net.lakkie.rubs.ai.RUBSBattleAI;
import net.lakkie.rubs.ai.components.AIBattleComponent;
import net.lakkie.rubs.storage.PositionedUnit;

public class AIDamageOperator extends AIBattleComponent {

	public AIDamageOperator(RUBSBattleAI ai) {
		super(ai);
	}

	public void initUnit(PositionedUnit unit) {
		this.tickUnit(unit);
	}

	public void slowTickUnit(PositionedUnit unit) {
		
	}

	public void tickUnit(PositionedUnit unit) {
		
	}
	
}
