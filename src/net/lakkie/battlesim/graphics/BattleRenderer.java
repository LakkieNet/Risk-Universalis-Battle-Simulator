package net.lakkie.battlesim.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

import net.lakkie.battlesim.ai.RUBSBattleAI;
import net.lakkie.battlesim.storage.PositionedUnit;
import net.lakkie.battlesim.storage.RUBSBattle;
import net.lakkie.battlesim.storage.UnitActionType;
import net.lakkie.battlesim.storage.UnitGroup;
import net.lakkie.battlesim.storage.Vector2;
import net.lakkie.battlesim.util.BooleanCallback;

public class BattleRenderer implements MouseListener, MouseMotionListener {

	private RUBSBattle battle;
	private Canvas canvas;
	private Runnable drawer = this::draw;
	private Vector2 viewportSize;
	private Vector2 lastPos = new Vector2();
	private Vector2 offset = new Vector2();
	private BooleanCallback targetPaths;
	private RUBSBattleAI ai;
	
	public BattleRenderer(RUBSBattle battle, Canvas canvas, Vector2 viewportSize, BooleanCallback targetPaths) {
		this.battle = battle;
		this.canvas = canvas;
		this.viewportSize = viewportSize;
		this.targetPaths = targetPaths;
		this.ai = new RUBSBattleAI(this.battle);
		GraphicalGameLoop.getInstance().getDraws().add(this.drawer);
		for (PositionedUnit unit : this.battle.getAttacking().getUnits()) {
			unit.actionType = UnitActionType.ATTACKING;
		}
		for (PositionedUnit unit : this.battle.getDefending().getUnits()) {
			unit.actionType = UnitActionType.DEFENDING;
			unit.pos = unit.pos.add(new Vector2(this.viewportSize.x - unit.getRenderWidth() - 40, (this.viewportSize.y + unit.getRenderHeight()) / 2));
		}
		this.canvas.addMouseListener(this);
		this.canvas.addMouseMotionListener(this);
		this.ai.init();
	}
	
	public void draw() {
		BufferStrategy bs = this.canvas.getBufferStrategy();
		if (bs == null) {
			this.canvas.createBufferStrategy(3);
			bs = this.canvas.getBufferStrategy();
		}
		
		this.ai.tick();
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
		
		this.drawArmy(g, this.battle.getAttacking());
		this.drawArmy(g, this.battle.getDefending());
		
		if (this.targetPaths.get()) {
			this.ai.drawTargetPaths(g, this.offset);
		}
		
		g.dispose();
		bs.show();
	}
	
	private void drawArmy(Graphics2D g, UnitGroup army) {
		for (PositionedUnit unit : army.getUnits()) {
			unit.draw(g, this.offset);
		}
	}
	
	public void close() {
		GraphicalGameLoop.getInstance().getDraws().remove(this.drawer);
	}

	public void mouseDragged(MouseEvent e) {
		Vector2 currPos = new Vector2(e.getX(), e.getY());
		this.offset = this.offset.add(currPos.subtract(this.lastPos));
		this.lastPos = currPos;
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		this.lastPos = new Vector2(e.getX(), e.getY());
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
	
}
