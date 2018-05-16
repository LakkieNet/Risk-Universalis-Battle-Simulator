package net.lakkie.rubs.storage;

public enum UnitType {

	INFANTRY(0f), CAVALRY(0f), ARMOR(0f);

	private final float baseMorale;
	
	private UnitType(float baseMorale) {
		this.baseMorale = baseMorale;
	}
	
	public float getBaseMorale(UnitActionType actionType) {
		return this.baseMorale + actionType.getMoraleEffect();
	}
	
}
