package net.lakkie.battlesim.ai;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import net.lakkie.battlesim.storage.PositionedUnit;
import net.lakkie.battlesim.storage.RUBSBattle;
import net.lakkie.battlesim.storage.Vector2;

public class RUBSBattleAI {

	public RUBSBattle battle;
	public AIPathFinder pathfinder;
	public AIUnitOperator unitOperator;
	private long lastChange;

	public RUBSBattleAI(RUBSBattle battle) {
		this.battle = battle;
		this.battle.ai = this;
		this.battle.getAttacking().ai = this;
		this.pathfinder = new AIPathFinder(this);
		this.unitOperator = new AIUnitOperator(this);
	}
	
	public void slowTick() {
		for (PositionedUnit unit : this.battle.getAllUnits()) {
			this.unitOperator.slowTickUnit(unit);
		}
	}
	
	public void tick() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - this.lastChange >= 2000) {
			this.lastChange = currentTime;
			this.slowTick();
		}
		for (PositionedUnit unit : this.battle.getAllUnits()) {
			this.unitOperator.tickUnit(unit);
		}
	}
	
	public void init() {
		this.lastChange = System.currentTimeMillis();
		for (PositionedUnit unit : this.battle.getAllUnits()) {
			unit.ai = this;
			this.unitOperator.initUnit(unit);
		}
	}
	
	public void drawTargetPaths(Graphics2D g, Vector2 offset) {
		g.setColor(Color.red);
		g.setStroke(new BasicStroke(2.0f));
		for (PositionedUnit unit : this.battle.getAttacking().getUnits()) {
			drawTargetPath(g, offset, unit);
		}
		for (PositionedUnit unit : this.battle.getDefending().getUnits()) {
			drawTargetPath(g, offset, unit);
		}
	}
	
	private void drawTargetPath(Graphics2D g, Vector2 offset, PositionedUnit unit) {
		g.drawLine(offset.x + unit.pos.x + unit.getRenderWidth() / 2, offset.y + unit.pos.y + unit.getRenderWidth() / 2 - 3, offset.x + unit.aiTargetPos.x, offset.y + unit.aiTargetPos.y);
	}
	
	public float calculateMorale(PositionedUnit unit) {
		return 50.0f;
	}
	
}
