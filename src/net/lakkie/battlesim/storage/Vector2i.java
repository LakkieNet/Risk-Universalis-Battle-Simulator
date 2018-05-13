package net.lakkie.battlesim.storage;

import java.io.Serializable;
import java.util.Random;

public class Vector2i implements Serializable {

	private static final long serialVersionUID = -2168539960660080468L;
	public int x, y;
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2i(int value) {
		this.x = value;
		this.y = value;
	}
	
	public Vector2i() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2i add(Vector2i other) {
		return new Vector2i(this.x + other.x, this.y + other.y);
	}
	
	public Vector2i subtract(Vector2i other) {
		return new Vector2i(this.x - other.x, this.y - other.y);
	}
	
	public Vector2i multiply(Vector2i other) {
		return new Vector2i(this.x * other.x, this.y * other.y);
	}
	
	public Vector2i divide(Vector2i other) {
		return new Vector2i(this.x / other.x, this.y / other.y);
	}
	
	public String toString() {
		return String.format("[x=%s,y=%s]", this.x, this.y);
	}
	
	public static Vector2i random(int posnegRange, Random rand) {
		return new Vector2i(rand.nextInt(posnegRange * 2) - posnegRange, rand.nextInt(posnegRange * 2) - posnegRange);
	}
	
}
