package net.lakkie.rubs.ai.components.damage.readers;

import java.util.HashMap;
import java.util.Map;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.rubs.readers.ConfigurationReader;

public class EffectivenessConfig extends ConfigurationReader {

	private Map<String, EffectivenessProfile> profiles;

	public EffectivenessConfig() {
		super("Assets/Configs/AI/UnitEffectiveness.txt");
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (this.profiles == null) {
			this.profiles = new HashMap<String, EffectivenessProfile>();
		}
		if (token.equals("effectiveness")) {
			EffectivenessProfile profile = new EffectivenessProfile();
			machine.readSection(profile);
			this.profiles.put(profile.id, profile);
		}
	}

}
