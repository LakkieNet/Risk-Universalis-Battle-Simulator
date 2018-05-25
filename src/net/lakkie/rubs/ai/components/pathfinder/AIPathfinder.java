package net.lakkie.rubs.ai.components.pathfinder;

import net.lakkie.rubs.ai.RUBSBattleAI;
import net.lakkie.rubs.ai.components.AIBattleComponent;
import net.lakkie.rubs.ai.components.pathfinder.readers.MovementPropertiesConfig;
import net.lakkie.rubs.storage.PositionedUnit;
import net.lakkie.rubs.storage.UnitType;
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
		Vector2f deltaRaw = new Vector2f(Vector2i.random(targetUnit.pos.distance(unit.pos) / 10 + 50, BasicUtils.getRandom())).add(targetUnit.posExact.subtract(unit.posExact));
		Vector2i delta = deltaRaw.multiply(new Vector2f(multiplier)).round();
		unit.aiTargetPos = unit.pos.add(delta);
	}

	public void tickUnit(PositionedUnit unit) {
		float speed;
		UnitType type = unit.getType();
		if (type == UnitType.INFANTRY) {
			speed = MovementPropertiesConfig.inst.infantrySpeed;
		} else if (type == UnitType.CAVALRY) {
			speed = MovementPropertiesConfig.inst.cavalrySpeed;
		} else {
			speed = MovementPropertiesConfig.inst.armorSpeed;
		}

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
		Vector2f deltaRaw = new Vector2f(Vector2i.random(targetUnit.pos.distance(unit.pos) / 10 + 50, BasicUtils.getRandom())).add(targetUnit.posExact.subtract(unit.posExact));
		Vector2i delta = deltaRaw.multiply(new Vector2f(multiplier)).round();
		unit.aiTargetPos = unit.pos.add(delta);
	}

}
