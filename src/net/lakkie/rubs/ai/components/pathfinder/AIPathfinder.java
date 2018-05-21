package net.lakkie.rubs.ai.components.pathfinder;

import net.lakkie.rubs.ai.RUBSBattleAI;
import net.lakkie.rubs.ai.components.AIBattleComponent;
import net.lakkie.rubs.storage.PositionedUnit;
import net.lakkie.rubs.storage.Vector2f;
import net.lakkie.rubs.storage.Vector2i;
import net.lakkie.rubs.util.BasicUtils;

public class AIPathfinder extends AIBattleComponent {

	public AIPathfinder(RUBSBattleAI ai) {
		super(ai);
	}

	public void initUnit(PositionedUnit unit) {
		PositionedUnit targetUnit = this.ai.battle.getNearestUnit(unit, false);
		float multiplier = unit.decision == null ? 1f : unit.decision.targetMultiplier;
		Vector2f deltaRaw = new Vector2f(Vector2i.random(targetUnit.pos.distance(unit.pos) / 10 + 25, BasicUtils.getRandom()));
		Vector2i delta = deltaRaw.multiply(new Vector2f(multiplier)).round();
		unit.aiTargetPos = targetUnit.pos.add(delta);
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
		float multiplier = unit.decision == null ? 1f : unit.decision.targetMultiplier;
		Vector2f deltaRaw = new Vector2f(Vector2i.random(targetUnit.pos.distance(unit.pos) / 10 + 25, BasicUtils.getRandom()));
		Vector2i delta = deltaRaw.multiply(new Vector2f(multiplier)).round();
		unit.aiTargetPos = targetUnit.pos.add(delta);
	}

}
