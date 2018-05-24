package net.lakkie.rubs.ai.components.damage.readers;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.acl.parser.IACLReader;

public class EffectivenessProfile implements IACLReader {

	public String id;
	public float infantryBase, cavalryBase, armorBase;
	
	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("id")) {
			this.id = machine.readString();
		}
		if (token.equals("infantry_base")) {
			this.infantryBase = (float) machine.readDouble();
		}
		if (token.equals("cavalry_base")) {
			this.cavalryBase = (float) machine.readDouble();
		}
		if (token.equals("armor_base")) {
			this.armorBase = (float) machine.readDouble();
		}
	}

}
