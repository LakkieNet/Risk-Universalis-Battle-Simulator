package net.lakkie.rubstest.tests;

import net.lakkie.rubs.readers.battle.BattleReader;
import net.lakkie.rubs.util.RUBSLogger;
import net.lakkie.rubstest.RUBSModuleTester;

public class BattleReaderTest extends RUBSModuleTester {

	public BattleReaderTest() {
		super("battle_reader");
	}

	public void run(String[] args) throws Exception {
		RUBSLogger.logger().info("Beginning battle reader test...");
		if (args.length != 1) {
			throw new IllegalArgumentException("Battle reader test requires battle file parameter to be passed in");
		}
		String path = args[0];
		RUBSLogger.logger().info("Reading battle: " + path);
		BattleReader reader = new BattleReader(path);
		RUBSLogger.logger().info("Reader result: " + reader.battle);
	}
	
}
