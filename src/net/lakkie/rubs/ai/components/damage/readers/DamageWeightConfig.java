package net.lakkie.rubs.ai.components.damage.readers;

import java.util.HashMap;
import java.util.Map;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.rubs.readers.ConfigurationReader;

public class DamageWeightConfig extends ConfigurationReader {

	private Map<String, DamageWeight> weight;

	public DamageWeightConfig() {
		super("Assets/Configs/AI/UnitDamageWeight.txt");
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (this.weight == null) {
			this.weight = new HashMap<String, DamageWeight>();
		}
		if (token.equals("damage_weight")) {
			DamageWeight dw = new DamageWeight();
			machine.readSection(dw);
			this.weight.put(dw.id, dw);
		}
	}
	
	public DamageWeight getWeight(String id) {
		return this.weight.get(id);
	}

}
