package net.lakkie.battlesim.ai.components.pathfinder;

import java.util.Random;

import net.lakkie.battlesim.ai.RUBSBattleAI;
import net.lakkie.battlesim.ai.components.AIBattleComponent;
import net.lakkie.battlesim.storage.PositionedUnit;
import net.lakkie.battlesim.storage.Vector2f;
import net.lakkie.battlesim.storage.Vector2i;

public class AIPathfinder extends AIBattleComponent {

	public AIPathfinder(RUBSBattleAI ai) {
		super(ai);
	}

	public void initUnit(PositionedUnit unit) {
		PositionedUnit targetUnit = this.ai.battle.getNearestUnit(unit, false);
		unit.aiTargetPos = targetUnit.pos.add(Vector2i.random(targetUnit.pos.distance(unit.pos) / 10 + 25, new Random()));
		unit.posExact = new Vector2f(unit.pos);
	}

	public void tickUnit(PositionedUnit unit) {
		float speed = 3.0f;

		if (unit.aiTargetPos.y > unit.posExact.y) {
			unit.posExact.y += speed;
		}
		if (unit.aiTargetPos.y < unit.posExact.y) {
			unit.posExact.y -= speed;
		}
		if (unit.aiTargetPos.x > unit.posExact.x) {
			unit.posExact.x += speed;
		}
		if (unit.aiTargetPos.x < unit.posExact.x) {
			unit.posExact.x -= speed;
		}
		unit.pos = unit.posExact.round();
	}

	public void slowTickUnit(PositionedUnit unit) {
		PositionedUnit targetUnit = this.ai.battle.getNearestUnit(unit, false);
		unit.aiTargetPos = targetUnit.pos.add(Vector2i.random(targetUnit.pos.distance(unit.pos) / 10 + 25, new Random()));
	}

}
