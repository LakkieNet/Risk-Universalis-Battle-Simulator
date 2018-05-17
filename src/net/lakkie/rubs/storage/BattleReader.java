package net.lakkie.rubs.storage;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.rubs.readers.ConfigurationReader;

public class BattleReader extends ConfigurationReader {

	public BattleReader(String path) {
		super(path);
	}

	public void onToken(String token, ACLParserMachine machine) {
		
	}

}
