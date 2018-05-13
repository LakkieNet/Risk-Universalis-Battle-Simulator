package net.lakkie.battlesim.storage;

import java.io.Serializable;
import java.util.Random;

public class Vector2 implements Serializable {

	private static final long serialVersionUID = -2168539960660080468L;
	public int x, y;
	
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2(int value) {
		this.x = value;
		this.y = value;
	}
	
	public Vector2() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2 add(Vector2 other) {
		return new Vector2(this.x + other.x, this.y + other.y);
	}
	
	public Vector2 subtract(Vector2 other) {
		return new Vector2(this.x - other.x, this.y - other.y);
	}
	
	public Vector2 multiply(Vector2 other) {
		return new Vector2(this.x * other.x, this.y * other.y);
	}
	
	public Vector2 divide(Vector2 other) {
		return new Vector2(this.x / other.x, this.y / other.y);
	}
	
	public String toString() {
		return String.format("[x=%s,y=%s]", this.x, this.y);
	}
	
	public static Vector2 random(int posnegRange, Random rand) {
		return new Vector2(rand.nextInt(posnegRange * 2) - posnegRange, rand.nextInt(posnegRange * 2) - posnegRange);
	}
	
}
