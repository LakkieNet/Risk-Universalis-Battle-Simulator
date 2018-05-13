package net.lakkie.battlesim.graphics;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.lakkie.battlesim.readers.GraphicsProperties;

public class GraphicalGameLoop implements Runnable {

	private static final GraphicalGameLoop instance;

	private List<Runnable> draws = new CopyOnWriteArrayList<Runnable>();

	private GraphicalGameLoop() {
		new Thread(this).start();
	}

	public void run() {
		long renderDelay = 1000 / GraphicsProperties.getInstance().fps;
		long lastRender = System.currentTimeMillis();
		while (true) {
			long curr = System.currentTimeMillis();
			if (curr >= lastRender + renderDelay) {
				lastRender = curr;
				this.draw();
			}
		}
	}

	public void draw() {
		for (Runnable draw : this.draws) {
			draw.run();
		}
	}

	public List<Runnable> getDraws() {
		return this.draws;
	}

	public static GraphicalGameLoop getInstance() {
		return instance;
	}

	static {
		instance = new GraphicalGameLoop();
	}

}
