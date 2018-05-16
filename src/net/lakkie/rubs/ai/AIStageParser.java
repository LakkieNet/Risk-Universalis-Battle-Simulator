package net.lakkie.rubs.ai;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.acl.parser.IACLReader;

public class AIStageParser implements IACLReader {

	private AIArmyOrder editing;

	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("id")) {
			this.editing = AIArmyOrder.valueOf(machine.readString());
		}
		if (this.editing == null) {
			return;
		}
		if (token.equals("friendly_name")) {
			this.editing.friendlyName = machine.readString();
		}
		if (token.equals("retreat_factor")) {
			this.editing.retreatFactor = (float) machine.readDouble();
		}
		if (token.equals("attack_factor")) {
			this.editing.attackFactor = (float) machine.readDouble();
		}
		if (token.equals("still_factor")) {
			this.editing.stillFactor = (float) machine.readDouble();
		}
	}

}
