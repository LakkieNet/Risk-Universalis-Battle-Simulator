package net.lakkie.rubs.ai.components.decision.modifiers;

import java.util.ArrayList;
import java.util.List;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.rubs.readers.ConfigurationReader;

public class ModifierRegistry extends ConfigurationReader {

	public static final ModifierRegistry inst = new ModifierRegistry();

	public List<ModifierDatabase> dbs;

	private ModifierRegistry() {
		super("Assets/Configs/AI/MoraleModifiers/ModifierRegistry.txt");
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (this.dbs == null) {
			dbs = new ArrayList<ModifierDatabase>();
		}
		if (token.equals("modifiers")) {
			machine.enterSection();
		}
		if (token.equals("modifier")) {
			this.dbs.add(new ModifierDatabase(machine.readString()));
		}
	}

	public static ModifierDatabase getById(String id) {
		for (ModifierDatabase db : inst.dbs) {
			if (db.getId().equals(id)) {
				return db;
			}
		}
		return null;
	}

}
