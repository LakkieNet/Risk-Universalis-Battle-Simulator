package net.lakkie.battlesim.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.lakkie.battlesim.ai.RUBSBattleAI;

public class RUBSBattle implements Serializable {

	private static final long serialVersionUID = 4952078846796887076L;
	private UnitGroup attacking, defending;
	private String name, comment;
	public transient RUBSBattleAI ai;

	public RUBSBattle(UnitGroup attacking, UnitGroup defending) {
		this.attacking = attacking;
		this.defending = defending;
	}

	public List<PositionedUnit> getAllUnits() {
		List<PositionedUnit> result = new ArrayList<PositionedUnit>(
				this.attacking.getUnits());
		result.addAll(this.defending.getUnits());
		return result;
	}

	public PositionedUnit getNearestUnit(PositionedUnit unit,
			boolean passFriendly) {
		PositionedUnit lastClosest = null;
		int lastDistance = Integer.MAX_VALUE;
		if (unit.actionType == UnitActionType.DEFENDING) {
			for (PositionedUnit other : this.attacking.getUnits()) {
				int distance = this.getDistance(unit, other);
				if (distance <= lastDistance) {
					lastDistance = distance;
					lastClosest = other;
				}
			}
		}
		if (unit.actionType == UnitActionType.ATTACKING) {
			for (PositionedUnit other : this.defending.getUnits()) {
				int distance = this.getDistance(unit, other);
				if (distance <= lastDistance) {
					lastDistance = distance;
					lastClosest = other;
				}
			}
		}
		return lastClosest;
	}

	public int getDistance(PositionedUnit a, PositionedUnit b) {
		int dx = Math.abs(a.pos.x - b.pos.x);
		int dy = Math.abs(a.pos.y - b.pos.y);
		int distance = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		return distance;
	}

	public UnitGroup getAttacking() {
		return this.attacking;
	}

	public UnitGroup getDefending() {
		return this.defending;
	}

	public void setAttacking(UnitGroup attacking) {
		this.attacking = attacking;
	}

	public void setDefending(UnitGroup defending) {
		this.defending = defending;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public RUBSBattle copy() {
		RUBSBattle result = new RUBSBattle(this.attacking.copy(), this.defending.copy());
		result.name = this.name;
		result.comment = this.comment;
		return result;
	}

}
