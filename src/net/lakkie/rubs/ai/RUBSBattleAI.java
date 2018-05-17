package net.lakkie.rubs.ai;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import net.lakkie.rubs.ai.components.AIBattleComponent;
import net.lakkie.rubs.ai.components.damage.AIDamageOperator;
import net.lakkie.rubs.ai.components.decision.AIDecisionOperator;
import net.lakkie.rubs.ai.components.pathfinder.AIPathfinder;
import net.lakkie.rubs.storage.PositionedUnit;
import net.lakkie.rubs.storage.RUBSBattle;
import net.lakkie.rubs.storage.Vector2i;

public class RUBSBattleAI {

	public static final String ID_PATHFINDER = "pathfinder";
	public static final String ID_DAMAGE = "damageoperator";
	public static final String ID_DECISION = "decision";
	
	public RUBSBattle battle;
	public Map<String, AIBattleComponent> components = new HashMap<String, AIBattleComponent>();
	private long lastChange;

	public RUBSBattleAI(RUBSBattle battle) {
		this.battle = battle;
		this.battle.ai = this;
		this.battle.getAttacking().ai = this;
		this.battle.getDefending().ai = this;
		this.components.put(ID_PATHFINDER, new AIPathfinder(this));
		this.components.put(ID_DAMAGE, new AIDamageOperator(this));
		this.components.put(ID_DECISION, new AIDecisionOperator(this));
	}

	public void slowTick() {
		for (PositionedUnit unit : this.battle.getAllUnits()) {
			for (AIBattleComponent comp : this.components.values()) {
				comp.slowTickUnit(unit);
			}
		}
	}

	public void tick() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - this.lastChange >= 1000) {
			this.lastChange = currentTime;
			this.slowTick();
		}
		for (PositionedUnit unit : this.battle.getAllUnits()) {
			for (AIBattleComponent comp : this.components.values()) {
				comp.tickUnit(unit);
			}
		}
	}

	public void init() {
		this.lastChange = System.currentTimeMillis();
		for (PositionedUnit unit : this.battle.getAllUnits()) {
			unit.ai = this;
			for (AIBattleComponent comp : this.components.values()) {
				comp.initUnit(unit);
			}
		}
	}

	public void drawTargetPaths(Graphics2D g, Vector2i offset) {
		g.setColor(Color.red);
		g.setStroke(new BasicStroke(2.0f));
		for (PositionedUnit unit : this.battle.getAttacking().getUnits()) {
			drawTargetPath(g, offset, unit);
		}
		for (PositionedUnit unit : this.battle.getDefending().getUnits()) {
			drawTargetPath(g, offset, unit);
		}
	}

	private void drawTargetPath(Graphics2D g, Vector2i offset, PositionedUnit unit) {
		g.drawLine(offset.x + unit.pos.x + unit.getRenderWidth() / 2, offset.y + unit.pos.y + unit.getRenderWidth() / 2 - 3, offset.x + unit.aiTargetPos.x,
				offset.y + unit.aiTargetPos.y);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Object> T getComponent(Class<T> type) {
		for (AIBattleComponent comp : this.components.values()) {
			if (comp.getClass() == type) {
				return (T) comp;
			}
		}
		return null;
	}

}
