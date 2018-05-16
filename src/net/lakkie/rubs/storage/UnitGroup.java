package net.lakkie.rubs.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.lakkie.rubs.ai.AIArmyOrder;
import net.lakkie.rubs.ai.RUBSBattleAI;

public class UnitGroup implements Serializable {

	private static final long serialVersionUID = 9071012123543856152L;
	private final List<PositionedUnit> units = new ArrayList<PositionedUnit>();
	public transient AIArmyOrder order;
	public transient RUBSBattleAI ai;
	
	public void addUnit(PositionedUnit unit) {
		this.units.add(unit);
	}
	
	public void removeUnit(PositionedUnit unit) {
		this.units.remove(unit);
	}
	
	public List<PositionedUnit> getUnits() {
		return units;
	}
	
	public int getTotalTroops() {
		int total = 0;
		for (PositionedUnit unit : this.units) {
			total += unit.getSize();
		}
		return total;
	}
	
	public int getTotalInfantry() {
		int total = 0;
		for (PositionedUnit unit : this.units) {
			total += unit.infantry;
		}
		return total;
	}
	
	public int getTotalCavalry() {
		int total = 0;
		for (PositionedUnit unit : this.units) {
			total += unit.cavalry;
		}
		return total;
	}
	
	public int getTotalArmor() {
		int total = 0;
		for (PositionedUnit unit : this.units) {
			total += unit.armor;
		}
		return total;
	}

	public UnitGroup copy() {
		UnitGroup result = new UnitGroup();
		for (PositionedUnit unit : this.units) {
			result.units.add(unit.copy());
		}
		return result;
	}
	
}
