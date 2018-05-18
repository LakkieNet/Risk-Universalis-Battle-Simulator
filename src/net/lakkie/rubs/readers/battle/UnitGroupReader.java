package net.lakkie.rubs.readers.battle;

import java.util.List;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.acl.parser.IACLReader;
import net.lakkie.rubs.storage.PositionedUnit;
import net.lakkie.rubs.storage.UnitActionType;
import net.lakkie.rubs.storage.UnitGroup;

public class UnitGroupReader implements IACLReader {

	public UnitGroup group;
	public UnitActionType type;

	public UnitGroupReader(UnitActionType type) {
		this.group = new UnitGroup();
		this.type = type;
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("unit")) {
			List<String> valuesList = machine.readStringList();
			String[] values = valuesList.toArray(new String[valuesList.size()]);
			if (values.length != 5) {
				// Invalid unit
				return;
			}
			PositionedUnit unit = new PositionedUnit(Integer.parseInt(values[0]), Integer.parseInt(values[1]), this.type);
			unit.infantry = Integer.parseInt(values[2]);
			unit.cavalry = Integer.parseInt(values[3]);
			unit.armor = Integer.parseInt(values[4]);
			this.group.addUnit(unit);
		}
	}

}
