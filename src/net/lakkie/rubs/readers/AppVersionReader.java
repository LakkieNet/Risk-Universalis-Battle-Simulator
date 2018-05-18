package net.lakkie.rubs.readers;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;

public class AppVersionReader extends ConfigurationReader {

	public static final AppVersionReader inst = new AppVersionReader();

	public String id, name;
	
	public AppVersionReader() {
		super("Assets/Configs/AppVersion.txt");
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("version")) {
			machine.enterSection();
		}
		if (token.equals("id")) {
			this.id = machine.readString();
		}
		if (token.equals("name")) {
			this.name = machine.readString();
		}
	}
	
}
