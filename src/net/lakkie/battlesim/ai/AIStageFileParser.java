package net.lakkie.battlesim.ai;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.battlesim.readers.ConfigurationReader;

public class AIStageFileParser extends ConfigurationReader {

	public AIStageFileParser() {
		super("Assets/Configs/AI/AIArmyStages.txt");
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("stage")) {
			machine.readSection(new AIStageParser());
		}
	}

}
