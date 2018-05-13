package net.lakkie.battlesim.ai;

import net.lakkie.battlesim.storage.PositionedUnit;
import net.lakkie.battlesim.storage.Vector2f;

public class AIUnitOperator {

	public RUBSBattleAI ai;
	
	public AIUnitOperator(RUBSBattleAI ai) {
		this.ai = ai;
	}
	
	public void initUnit(PositionedUnit unit) {
		this.ai.pathfinder.searchAttackPath(unit);
		unit.posExact = new Vector2f(unit.pos);
	}

	public void tickUnit(PositionedUnit unit) {
		float speed = 3.0f;
		Vector2f delta = new Vector2f();
		float slope = ((float)(unit.aiTargetPos.y) - unit.posExact.y)/((float)(unit.aiTargetPos.x) - unit.posExact.x);
		delta.x = unit.aiTargetPos.x > unit.posExact.x ? slope * speed : -slope * speed;
		delta.y = unit.aiTargetPos.y > unit.posExact.y ? 1f * speed : -1f * speed;
		
		unit.pos = unit.posExact.round();
	}
	
	public void slowTickUnit(PositionedUnit unit) {
		this.ai.pathfinder.searchAttackPath(unit);
	}

}
