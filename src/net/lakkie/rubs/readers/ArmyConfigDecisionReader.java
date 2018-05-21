package net.lakkie.rubs.readers;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.rubs.ai.AIArmyDecision;

public class ArmyConfigDecisionReader extends ConfigurationReader {

	private AIArmyDecision decision;

	public ArmyConfigDecisionReader() {
		super("Assets/Configs/AI/AIUnitDecision.txt");
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("decision")) {
			machine.enterSection();
		}
		if (token.equals("id")) {
			this.decision = AIArmyDecision.valueOf(machine.readString());
		}
		if (token.equals("friendly_name")) {
			this.decision.friendlyName = machine.readString();
		}
		if (token.equals("target_multiplier")) {
			this.decision.targetMultiplier = (float) machine.readDouble();
		}
	}

}
