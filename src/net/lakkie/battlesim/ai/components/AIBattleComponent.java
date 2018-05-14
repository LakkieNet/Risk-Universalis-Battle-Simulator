package net.lakkie.battlesim.ai.components;

import net.lakkie.battlesim.ai.RUBSBattleAI;
import net.lakkie.battlesim.storage.PositionedUnit;

public abstract class AIBattleComponent {

	public RUBSBattleAI ai;

	public AIBattleComponent(RUBSBattleAI ai) {
		this.ai = ai;
	}

	public abstract void initUnit(PositionedUnit unit);

	public abstract void slowTickUnit(PositionedUnit unit);

	public abstract void tickUnit(PositionedUnit unit);

}
