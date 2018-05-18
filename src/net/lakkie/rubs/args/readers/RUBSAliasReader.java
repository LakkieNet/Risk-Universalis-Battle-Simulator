package net.lakkie.rubs.args.readers;

import java.util.List;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.acl.parser.IACLReader;

public class RUBSAliasReader implements IACLReader {

	public String argument;
	public List<String> aliases;

	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("arg")) {
			this.argument = machine.readString();
		}
		if (token.equals("aliases")) {
			this.aliases = machine.readStringList();
		}
	}

}
