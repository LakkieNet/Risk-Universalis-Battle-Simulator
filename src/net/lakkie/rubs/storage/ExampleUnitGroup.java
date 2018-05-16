package net.lakkie.rubs.storage;

public class ExampleUnitGroup extends UnitGroup {

	private static final long serialVersionUID = -5667427932099206375L;

	public ExampleUnitGroup() {
		this.addUnit(new PositionedUnit(20, 20, UnitActionType.GENERIC));
		this.addUnit(new PositionedUnit(20, 60, UnitActionType.GENERIC));
	}
	
}
