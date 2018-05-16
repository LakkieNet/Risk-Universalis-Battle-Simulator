package net.lakkie.rubs.graphics;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

import net.lakkie.rubs.menus.MenuArmyEditor;
import net.lakkie.rubs.storage.PositionedUnit;
import net.lakkie.rubs.storage.UnitGroup;
import net.lakkie.rubs.storage.Vector2i;
import net.lakkie.rubs.util.BooleanCallback;

public class DivisionPreviewRenderer
		implements
			MouseListener,
			MouseMotionListener {

	public Canvas canvas;
	public UnitGroup army;
	public Vector2i offset = new Vector2i(0, 0);
	public BooleanCallback keepOffset, debug;
	private Vector2i startPosition;
	private Vector2i currentPosition;
	private boolean hasMoved, isDragging;

	public DivisionPreviewRenderer(Canvas canvas, UnitGroup army,
			BooleanCallback keepOffset, BooleanCallback debug) {
		this.canvas = canvas;
		this.army = army;
		this.canvas.addMouseListener(this);
		this.canvas.addMouseMotionListener(this);
		this.keepOffset = keepOffset;
		this.debug = debug;
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
		if (!MenuArmyEditor.windows.containsKey(this.army)) {
			if (this.isDragging) {
				this.calculateOffset();
			}
			for (PositionedUnit unit : this.army.getUnits()) {
				unit.draw(g, this.offset);
			}
			// Test offset line
			if (this.startPosition != null && this.currentPosition != null
					&& this.debug.get()) {
				g.setColor(Color.red);
				g.setStroke(new BasicStroke(3.0f));
				g.drawLine(this.startPosition.x, this.startPosition.y,
						this.offset.x, this.offset.y);
			}
		}
		g.dispose();
		bs.show();
	}

	private void calculateOffset() {
		if (!this.hasMoved) {
			this.offset = new Vector2i(0);
			return;
		}
		this.offset = this.currentPosition.subtract(this.startPosition);
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if (this.offset == null) {
			this.startPosition = new Vector2i(e.getX(), e.getY());
		} else {
			this.startPosition = new Vector2i(e.getX(), e.getY())
					.subtract(this.offset);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (!this.keepOffset.get()) {
			this.hasMoved = false;
			this.startPosition = null;
			this.currentPosition = null;
			this.offset = new Vector2i(0);
		}
		this.isDragging = false;
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		this.isDragging = true;
		this.hasMoved = true;
		this.currentPosition = new Vector2i(e.getX(), e.getY());
	}

	public void mouseMoved(MouseEvent e) {

	}

}
