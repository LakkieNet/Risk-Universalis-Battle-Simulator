package net.lakkie.rubs.readers;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;

public class DamagePropertiesReader extends ConfigurationReader {

	public static final DamagePropertiesReader inst = new DamagePropertiesReader();
	
	public int damageTickMillis;
	
	public DamagePropertiesReader() {
		super("Assets/Configs/AI/DamageProperties.txt");
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("damage_properties")) {
			machine.enterSection();
		}
		if (token.equals("damage_tick_millis")) {
			this.damageTickMillis = machine.readInt();
		}
	}

}
