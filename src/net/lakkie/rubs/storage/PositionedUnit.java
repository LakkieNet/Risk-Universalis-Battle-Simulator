package net.lakkie.rubs.storage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import net.lakkie.rubs.ai.AIArmyDecision;
import net.lakkie.rubs.ai.RUBSBattleAI;
import net.lakkie.rubs.ai.components.decision.AIDecisionOperator;
import net.lakkie.rubs.readers.UnitCoordinator;
import net.lakkie.rubs.util.BasicUtils;

public class PositionedUnit implements Serializable {

	private static final long serialVersionUID = -4009259352660549560L;
	public Vector2i pos;
	public int infantry = 1000, cavalry = 0, armor = 0;
	public UnitActionType actionType = UnitActionType.GENERIC;
	public transient RUBSBattleAI ai;
	public transient Vector2i aiTargetPos;
	public transient Vector2f posExact;
	public transient int originalTotal;
	public transient AIArmyDecision decision;

	public PositionedUnit(Vector2i pos, UnitActionType actionType) {
		this.pos = pos;
		this.actionType = actionType;
	}

	public PositionedUnit(int x, int y, UnitActionType actionType) {
		this.pos = new Vector2i(x, y);
		this.actionType = actionType;
	}

	public UnitType getType() {
		if (this.infantry > this.cavalry && this.infantry > this.armor) {
			return UnitType.INFANTRY;
		}
		if (this.cavalry > this.infantry && this.cavalry > this.armor) {
			return UnitType.CAVALRY;
		}
		if (this.armor > this.infantry && this.armor > this.cavalry) {
			return UnitType.ARMOR;
		}
		return UnitType.INFANTRY;
	}

	public BufferedImage getImage() {
		UnitType type = this.getType();
		switch (type) {
			case ARMOR :
				return UnitCoordinator.getInstance().getImager("armor").getById(this.actionType.getId());
			case CAVALRY :
				return UnitCoordinator.getInstance().getImager("cavalry").getById(this.actionType.getId());
			case INFANTRY :
				return UnitCoordinator.getInstance().getImager("infantry").getById(this.actionType.getId());
			default :
				return null;
		}
	}

	public void draw(Graphics g, Vector2i offset) {
		BufferedImage image = this.getImage();
		g.drawImage(image, this.pos.x + offset.x, this.pos.y + offset.y, image.getWidth() / 3, image.getHeight() / 3, null);
		String numbers = String.format("%s-%s-%s", BasicUtils.formatCommas(this.infantry), BasicUtils.formatCommas(this.cavalry),
				BasicUtils.formatCommas(this.armor));
		g.setColor(Color.black);
		g.setFont(BasicUtils.getUnitInfoFont());
		int posX = this.pos.x + offset.x;
		int posY = this.pos.y + offset.y - 3;
		g.drawString(numbers, posX, posY);
	}

	public int getSize() {
		return this.infantry + this.cavalry + this.armor;
	}

	public int getRenderWidth() {
		return this.getImage().getWidth() / 3;
	}

	public int getRenderHeight() {
		return this.getImage().getHeight() / 3;
	}

	public PositionedUnit copy() {
		PositionedUnit result = new PositionedUnit(this.pos, this.actionType);
		result.infantry = this.infantry;
		result.cavalry = this.cavalry;
		result.armor = this.armor;
		return result;
	}

	public float getMorale() {
		return this.ai == null ? 0f : this.ai.getComponent(AIDecisionOperator.class).calculateMorale(this);
	}

	public UnitGroup getArmy(RUBSBattle in) {
		return this.inArmy(in.getAttacking()) ? in.getAttacking() : in.getDefending();
	}
	
	public boolean inArmy(UnitGroup army) {
		return army.getUnits().contains(this);
	}
	
	public String toString() {
		return String.format("[pos=%s,infantry=%s,cavalry=%s,armor=%s]", this.pos, this.infantry, this.cavalry, this.armor);
	}

	public int getIntersectionArea(PositionedUnit unit) {
		Rectangle rectThis = new Rectangle(this.pos.x, this.pos.y, this.getRenderWidth(), this.getRenderHeight());
		Rectangle rectTarget = new Rectangle(unit.pos.x, unit.pos.y, unit.getRenderWidth(), unit.getRenderHeight());
		Dimension size = rectThis.intersection(rectTarget).getSize();
		return size.width * size.height;
	}
	
	public float getMoraleScalar() {
		return (this.getMorale() + 300) / 2;
	}

}
