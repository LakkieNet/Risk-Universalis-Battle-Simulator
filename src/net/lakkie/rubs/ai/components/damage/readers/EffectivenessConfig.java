package net.lakkie.rubs.ai.components.damage.readers;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.rubs.readers.ConfigurationReader;

public class EffectivenessConfig extends ConfigurationReader {

	private Map<String, EffectivenessProfile>
	
	public EffectivenessConfig() {
		super("Assets/Configs/AI/UnitEffectiveness.txt");
	}

	public void onToken(String token, ACLParserMachine machine) {
		
	}

}
