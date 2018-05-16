package net.lakkie.battlesim.ai.components.damage;

import net.lakkie.battlesim.ai.RUBSBattleAI;
import net.lakkie.battlesim.ai.components.AIBattleComponent;
import net.lakkie.battlesim.storage.PositionedUnit;

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
