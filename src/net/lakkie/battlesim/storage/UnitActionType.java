package net.lakkie.battlesim.storage;

public enum UnitActionType {

	ATTACKING(0, -30f), DEFENDING(1, 30f), GENERIC(2, 0f);
	
	private final int id;
	private final float moraleEffect;
	
	private UnitActionType(int id, float moraleEffect) {
		this.id = id;
		this.moraleEffect = moraleEffect;
	}
	
	public int getId() {
		return this.id;
	}
	
	public float getMoraleEffect() {
		return this.moraleEffect;
	}
	
}
