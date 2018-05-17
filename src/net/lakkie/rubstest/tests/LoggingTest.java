package net.lakkie.rubstest.tests;

import java.util.Date;

import net.lakkie.rubs.util.RUBSLogger;
import net.lakkie.rubstest.RUBSModuleTest;

public class LoggingTest extends RUBSModuleTest {

	public LoggingTest() {
		super("logging");
	}

	public void run(String[] args) throws Exception {
		
		RUBSLogger.logger().info("Beginning log test...");

		RUBSLogger.logger().warning("This is a warning message");
		RUBSLogger.logger().info("This is an information message");
		RUBSLogger.logger().severe("This is a severe message");
		RUBSLogger.logger().config("This is a config message");
		
		RUBSLogger.logger().info("Finish log at " + RUBSLogger.prefixFormat.format(new Date()));
		
	}

}
