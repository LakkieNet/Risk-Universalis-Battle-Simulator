package net.lakkie.battlesim.storage;

public enum UnitActionType {

	ATTACKING(0), DEFENDING(1), GENERIC(2);
	
	private final int id;
	
	private UnitActionType(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
}
