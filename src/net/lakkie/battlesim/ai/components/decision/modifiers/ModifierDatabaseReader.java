package net.lakkie.battlesim.ai.components.decision.modifiers;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.battlesim.readers.ConfigurationReader;

public class ModifierDatabaseReader extends ConfigurationReader {

	public ModifierDatabaseReader(String path) {
		super(path);
	}

	public void onToken(String token, ACLParserMachine machine) {
		
	}

}
