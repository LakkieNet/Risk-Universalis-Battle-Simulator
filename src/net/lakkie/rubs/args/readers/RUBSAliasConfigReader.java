package net.lakkie.rubs.args.readers;

import java.util.ArrayList;
import java.util.List;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.rubs.readers.ConfigurationReader;

public class RUBSAliasConfigReader extends ConfigurationReader {

	public List<RUBSAliasReader> aliases;
	
	public RUBSAliasConfigReader() {
		super("Assets/Configs/ArgumentAliases.txt");
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (this.aliases == null) {
			this.aliases = new ArrayList<RUBSAliasReader>();
		}
		if (token.equals("alias")) {
			RUBSAliasReader alias = new RUBSAliasReader();
			machine.readSection(alias);
			this.aliases.add(alias);
		}
	}

}
