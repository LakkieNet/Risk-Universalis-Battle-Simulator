package net.lakkie.battlesim.ai;

import net.lakkie.battlesim.BasicUtils;
import net.lakkie.battlesim.storage.PositionedUnit;
import net.lakkie.battlesim.storage.Vector2;

public class AIUnitOperator {

	public RUBSBattleAI ai;
	
	public AIUnitOperator(RUBSBattleAI ai) {
		this.ai = ai;
	}
	
	public void initUnit(PositionedUnit unit) {
		this.ai.pathfinder.searchAttackPath(unit);
	}

	public void tickUnit(PositionedUnit unit) {
		float dx = unit.aiTargetPos.x - unit.pos.x;
		float dy = unit.aiTargetPos.y - unit.pos.y;
		float slope = dy / dx;
		float speedFactor = 2.5f;
		int offsetY = (int)(slope * speedFactor * BasicUtils.getSignMultiplier(dx));
		int offsetX = (int)(speedFactor * BasicUtils.getSignMultiplier(dy));
		unit.pos = unit.pos.add(new Vector2(offsetX, offsetY));
	}
	
	public void slowTickUnit(PositionedUnit unit) {
		this.ai.pathfinder.searchAttackPath(unit);
	}

}
