package net.lakkie.rubs.ai.components;



import net.lakkie.rubs.ai.RUBSBattleAI;
import net.lakkie.rubs.storage.PositionedUnit;

public abstract class AIBattleComponent {

	public RUBSBattleAI ai;

	public AIBattleComponent(RUBSBattleAI ai) {
		this.ai = ai;
	}

	public abstract void initUnit(PositionedUnit unit);

	public abstract void slowTickUnit(PositionedUnit unit);

	public abstract void tickUnit(PositionedUnit unit);

	public void postInitUnit(PositionedUnit unit) {
	}
	
	/**
	 * A tick that comes before {@link #slowTickUnit(PositionedUnit)} that happens once per tick.
	 */
	public void singleTick() {
	}

}
