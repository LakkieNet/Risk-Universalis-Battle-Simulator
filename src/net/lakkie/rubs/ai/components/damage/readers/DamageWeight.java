package net.lakkie.rubs.ai.components.damage.readers;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.acl.parser.IACLReader;

public class DamageWeight implements IACLReader {

	public String id;
	public float infantryWeight, cavalryWeight, armorWeight;
	
	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("id")) {
			this.id = machine.readString();
		}
		if (token.equals("infantry_weight")) {
			this.infantryWeight = (float) machine.readDouble();
		}
		if (token.equals("cavalry_weight")) {
			this.cavalryWeight = (float) machine.readDouble();
		}
		if (token.equals("armor_weight")) {
			this.armorWeight = (float) machine.readDouble();
		}
	}

}
