package net.lakkie.rubs.readers.battle;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.rubs.readers.ConfigurationReader;
import net.lakkie.rubs.storage.RUBSBattle;
import net.lakkie.rubs.storage.UnitActionType;
import net.lakkie.rubs.storage.UnitGroup;

public class BattleReader extends ConfigurationReader {

	public RUBSBattle battle;

	public BattleReader(String path) {
		super(path);
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (this.battle == null) {
			this.battle = new RUBSBattle(new UnitGroup(), new UnitGroup());
		}
		if (token.equals("name")) {
			this.battle.setName(machine.readString());
		}
		if (token.equals("comment")) {
			this.battle.setComment(String.join(System.lineSeparator(), machine.readStringList()));
		}
		if (token.equals("attacking_army")) {
			UnitGroupReader reader = new UnitGroupReader(UnitActionType.ATTACKING);
			machine.readSection(reader);
			this.battle.setAttacking(reader.group);
		}
		if (token.equals("defending_army")) {
			UnitGroupReader reader = new UnitGroupReader(UnitActionType.DEFENDING);
			machine.readSection(reader);
			this.battle.setDefending(reader.group);
		}
	}

}
