package net.lakkie.battlesim.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import net.lakkie.battlesim.menus.MenuArmyEditor;
import net.lakkie.battlesim.storage.PositionedUnit;
import net.lakkie.battlesim.storage.UnitGroup;
import net.lakkie.battlesim.storage.Vector2i;

public class UnitGroupEditor implements MouseListener, MouseMotionListener {

	private UnitGroup army;
	private Canvas canvas;
	private Runnable drawRun = this::draw;
	private Vector2i offset = new Vector2i();
	private PositionedUnit currentDrag = null;
	private boolean isMoving = false;
	private Vector2i lastPosition = new Vector2i();
	private int mx, my;
	private TitledBorder border;
	private MenuArmyEditor editor;

	public UnitGroupEditor(UnitGroup army, Canvas canvas, TitledBorder border, MenuArmyEditor editor) {
		this.army = army;
		this.canvas = canvas;
		this.border = border;
		this.editor = editor;
		this.canvas.addMouseListener(this);
		this.canvas.addMouseMotionListener(this);
		GraphicalGameLoop.getInstance().getDraws().add(this.drawRun);
	}

	public void draw() {
		BufferStrategy bs = this.canvas.getBufferStrategy();
		if (bs == null) {
			this.canvas.createBufferStrategy(3);
			bs = this.canvas.getBufferStrategy();
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		g.setColor(Color.white);
		g.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());

		for (PositionedUnit unit : this.army.getUnits()) {
			unit.draw(g, this.offset);
		}

		g.dispose();
		bs.show();
	}

	public UnitGroup getArmy() {
		return this.army;
	}

	public Canvas getCanvas() {
		return this.canvas;
	}

	public void onEditFinish() {
		GraphicalGameLoop.getInstance().getDraws().remove(this.drawRun);
	}

	private PositionedUnit getUnitByPosition() {
		for (PositionedUnit unit : this.army.getUnits()) {
			// Rendered position
			Rectangle unitRect = new Rectangle(unit.pos.x + this.offset.x,
					unit.pos.y + this.offset.y, unit.getRenderWidth(),
					unit.getRenderHeight());
			Rectangle unitMouse = new Rectangle(this.mx, this.my, 1, 1);
			if (unitMouse.intersects(unitRect)) {
				return unit;
			}
		}
		return null;
	}

	public void mouseDragged(MouseEvent e) {
		this.mx = e.getX();
		this.my = e.getY();
		Vector2i newPosition = new Vector2i(e.getX(), e.getY());
		Vector2i deltaPosition = this.lastPosition.subtract(newPosition);
		if (this.isMoving) {
			this.offset = this.offset.subtract(deltaPosition);
		}
		if (this.currentDrag != null) {
			this.currentDrag.pos = this.currentDrag.pos.subtract(deltaPosition);
		}
		this.lastPosition = newPosition;
	}

	public void mouseMoved(MouseEvent e) {
		this.mx = e.getX();
		this.my = e.getY();
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		Vector2i pos = new Vector2i(e.getX(), e.getY());
		this.lastPosition = pos;
		switch (e.getButton()) {
			case MouseEvent.BUTTON1 :
				this.isMoving = true;
				if (this.border != null) {
					this.border.setBorder(new BevelBorder(BevelBorder.LOWERED));
					this.editor.repaint();
				}
				break;
			case MouseEvent.BUTTON2 :
				PositionedUnit unit = this.getUnitByPosition();
				if (unit != null) {
					this.army.removeUnit(unit);
				}
			case MouseEvent.BUTTON3 :
				PositionedUnit clicked = this.getUnitByPosition();
				if (clicked == null) {
					return;
				}
				this.currentDrag = clicked;
				break;
		}
	}

	public void mouseReleased(MouseEvent e) {
		this.currentDrag = null;
		this.isMoving = false;
		if (this.border != null) {
			this.border.setBorder(new BevelBorder(BevelBorder.RAISED));
			this.editor.repaint();
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
