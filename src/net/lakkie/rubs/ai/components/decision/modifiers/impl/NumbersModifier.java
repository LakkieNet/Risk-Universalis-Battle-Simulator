package net.lakkie.rubs.ai.components.decision.modifiers.impl;

import net.lakkie.rubs.ai.components.decision.modifiers.AIModifier;
import net.lakkie.rubs.storage.PositionedUnit;
import net.lakkie.rubs.storage.RUBSBattle;
import net.lakkie.rubs.storage.UnitActionType;
import net.lakkie.rubs.storage.UnitGroup;

public class NumbersModifier extends AIModifier {

	private float infantryWeight, cavalryWeight, armorWeight, multiplier;

	public NumbersModifier() {
		super("numbers");
		this.infantryWeight = Float.parseFloat(this.db.get("infantry_weight"));
		this.cavalryWeight = Float.parseFloat(this.db.get("cavalry_weight"));
		this.armorWeight = Float.parseFloat(this.db.get("armor_weight"));
		this.multiplier = Float.parseFloat(this.db.get("multiplier"));
	}

	public UnitGroup getOpposingArmy(PositionedUnit unit, RUBSBattle battle) {
		if (unit.actionType == UnitActionType.ATTACKING) {
			return battle.getDefending();
		} else {
			return battle.getAttacking();
		}
	}

	public UnitGroup getFriendlyArmy(PositionedUnit unit, RUBSBattle battle) {
		if (unit.actionType == UnitActionType.ATTACKING) {
			return battle.getAttacking();
		} else {
			return battle.getDefending();
		}
	}

	public float getEffect(RUBSBattle battle, PositionedUnit unit) {
		float effectiveInfantry = (float) (this.getOpposingArmy(unit, battle).getTotalInfantry()) * this.infantryWeight;
		float effectiveCavalry = (float) (this.getOpposingArmy(unit, battle).getTotalCavalry()) * this.cavalryWeight;
		float effectiveArmor = (float) (this.getOpposingArmy(unit, battle).getTotalArmor()) * this.armorWeight;
		float effectiveTotal = effectiveInfantry + effectiveCavalry + effectiveArmor;
		float selfTotal = this.getFriendlyArmy(unit, battle).getTotalInfantry() + this.getFriendlyArmy(unit, battle).getTotalCavalry()
				+ this.getFriendlyArmy(unit, battle).getTotalArmor();
		float result = 0f;
		if (selfTotal == effectiveTotal) {
			result = -10f;
		}
		if (selfTotal < effectiveTotal) {
			result = -(effectiveTotal / selfTotal) * this.multiplier;
		}
		if (selfTotal > effectiveTotal) {
			result = (selfTotal / effectiveTotal) * this.multiplier;
		}
		return result;
	}

}
