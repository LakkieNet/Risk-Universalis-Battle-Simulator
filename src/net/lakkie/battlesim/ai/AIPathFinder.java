package net.lakkie.battlesim.ai;

import java.util.Random;

import net.lakkie.battlesim.storage.PositionedUnit;
import net.lakkie.battlesim.storage.Vector2i;

public class AIPathFinder {

	public RUBSBattleAI ai;
	
	public AIPathFinder(RUBSBattleAI ai) {
		this.ai = ai;
	}
	
	public void searchAttackPath(PositionedUnit unit) {
		PositionedUnit targetUnit = this.ai.battle.getNearestUnit(unit, false);
		unit.aiTargetPos = targetUnit.pos.add(Vector2i.random(100, new Random()));
	}
	
}