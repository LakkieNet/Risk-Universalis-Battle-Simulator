package net.lakkie.rubstest.tests;

import net.lakkie.rubs.ai.RUBSBattleAI;
import net.lakkie.rubs.ai.components.decision.AIDecisionOperator;
import net.lakkie.rubs.storage.ExampleUnitGroup;
import net.lakkie.rubs.storage.PositionedUnit;
import net.lakkie.rubs.storage.RUBSBattle;
import net.lakkie.rubs.util.RUBSLogger;
import net.lakkie.rubstest.RUBSModuleTester;

public class MoraleTest extends RUBSModuleTester {

	public MoraleTest() {
		super("morale");
	}

	public void run(String[] args) throws Exception {
		RUBSLogger.logger().info("Beginning morale test...");
		RUBSBattle exampleBattle = new RUBSBattle(new ExampleUnitGroup(), new ExampleUnitGroup());
		RUBSBattleAI ai = new RUBSBattleAI(exampleBattle);
		RUBSLogger.logger().info("Battle test: " + exampleBattle);
		RUBSLogger.logger().info("Printing units' morales:");
		for (PositionedUnit unit : exampleBattle.getAllUnits()) {
			RUBSLogger.logger().info(String.format("\tUnit %s has morale rate of %s", unit, ai.getComponent(AIDecisionOperator.class).calculateMorale(unit)));
		}
		RUBSLogger.logger().info("Finish morale");
	}

}
