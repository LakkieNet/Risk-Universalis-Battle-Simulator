package net.lakkie.rubs.ai.components.pathfinder.readers;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.rubs.readers.ConfigurationReader;

public class MovementPropertiesConfig extends ConfigurationReader {

	public static final MovementPropertiesConfig inst = new MovementPropertiesConfig();
	public float infantrySpeed, cavalrySpeed, armorSpeed;
	
	public MovementPropertiesConfig() {
		super("Assets/Configs/MovementProperties.txt");
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("movement_properties")) {
			machine.enterSection();
		}
		if (token.equals("infantry_speed")) {
			this.infantrySpeed = (float) machine.readDouble();
		}
		if (token.equals("cavalry_speed")) {
			this.cavalrySpeed = (float) machine.readDouble();
		}
		if (token.equals("armor_speed")) {
			this.armorSpeed = (float) machine.readDouble();
		}
	}

}
