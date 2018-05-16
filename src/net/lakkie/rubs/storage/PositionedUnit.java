package net.lakkie.rubs.storage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import net.lakkie.rubs.BasicUtils;
import net.lakkie.rubs.ai.RUBSBattleAI;
import net.lakkie.rubs.ai.components.decision.AIDecisionOperator;
import net.lakkie.rubs.readers.UnitCoordinator;

public class PositionedUnit implements Serializable {

	private static final long serialVersionUID = -4009259352660549560L;
	public Vector2i pos;
	public int infantry = 1000, cavalry = 0, armor = 0;
	public transient UnitActionType actionType;
	public transient RUBSBattleAI ai;
	public transient Vector2i aiTargetPos;
	public transient Vector2f posExact;

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
		g.drawString(numbers, this.pos.x + offset.x, this.pos.y + offset.y - 3);
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
		return this.ai == null ? 0f : ((AIDecisionOperator) this.ai.getComponent(RUBSBattleAI.ID_DECISION)).calculateMorale(this);
	}

}
