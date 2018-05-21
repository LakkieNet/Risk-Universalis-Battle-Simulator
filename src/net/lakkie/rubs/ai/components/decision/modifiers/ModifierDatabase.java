package net.lakkie.rubs.ai.components.decision.modifiers;

import java.util.HashMap;
import java.util.Map;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.rubs.readers.ConfigurationReader;

public class ModifierDatabase extends ConfigurationReader {

	private Map<String, String> values;
	private String id;

	ModifierDatabase(String path) {
		super(path);
	}

	public void onToken(String token, ACLParserMachine machine) {
		// XXX Error with initializing, investigate further
		if (this.values == null) {
			this.values = new HashMap<String, String>();
		}
		if (token.equals("modifier_db")) {
			machine.enterSection();
		}
		if (token.equals("id")) {
			this.id = machine.readString();
		}
		if (token.startsWith("db_")) {
			this.values.put(token.substring(3), machine.readString());
		}
	}

	public String get(String key) {
		return this.values.get(key);
	}

	public String getId() {
		return this.id;
	}
	
	public String toString() {
		return String.format("[id=%s,values=%s]", this.id, this.values);
	}

}