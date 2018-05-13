package net.lakkie.battlesim.storage;

public class Vector2f {

	public float x, y;

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2f(float v) {
		this.x = v;
		this.y = v;
	}

	public Vector2f(Vector2i vec) {
		this.x = (int) vec.x;
		this.y = (int) vec.y;
	}

	public Vector2f() {
		this.x = 0;
		this.y = 0;
	}

	public Vector2f add(Vector2f other) {
		return new Vector2f(this.x + other.x, this.y + other.y);
	}

	public Vector2f subtract(Vector2f other) {
		return new Vector2f(this.x - other.x, this.y - other.y);
	}

	public Vector2f multiply(Vector2f other) {
		return new Vector2f(this.x * other.x, this.y * other.y);
	}

	public Vector2f divide(Vector2f other) {
		return new Vector2f(this.x / other.x, this.y / other.y);
	}

	public String toString() {
		return String.format("[x=%s,y=%s]", this.x, this.y);
	}

	public Vector2i round() {
		return new Vector2i((int) this.x, (int) this.y);
	}

}
