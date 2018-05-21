package net.lakkie.rubs.args;

import java.io.File;
import java.util.Scanner;

import net.lakkie.rubs.args.readers.RUBSAliasConfigReader;
import net.lakkie.rubs.args.readers.RUBSAliasReader;
import net.lakkie.rubs.readers.AppVersionReader;
import net.lakkie.rubs.util.ReaderUtils;
import net.lakkie.rubstest.TestStarter;

public class RUBSArgumentParser {

	private static final RUBSAliasConfigReader configReader = new RUBSAliasConfigReader();
	public static boolean noLog = false;
	
	private RUBSArgumentParser() {
	}

	public static void startAppWithArgs(String[] args) {
		boolean runTests = false;
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (searchAlias(arg, "--nolog")) {
				noLog = true;
			}
			if (searchAlias(arg, "--help")) {
				try {
					System.out.println(readHelpInformation());
					System.exit(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (searchAlias(arg, "--version")) {
				System.out.printf("Risk Universalis Battle Simulator, version name %s, version ID %s.\n", AppVersionReader.inst.name, AppVersionReader.inst.id);
				System.exit(0);
			}
			if (searchAlias(arg, "--test")) {
				System.out.printf("Queued tests: %s\n", TestStarter.runner.getTests());
				runTests = true;
			}
			if (runTests) {
				try {
					TestStarter.performConfigTests();
					System.exit(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean searchAlias(String searching, String target) {
		if (target.equals(searching)) {
			return true;
		}
		for (RUBSAliasReader alias : configReader.aliases) {
			if (alias.argument.equals(target)) {
				for (String aliasOptions : alias.aliases) {
					if (aliasOptions.equals(searching)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static String readHelpInformation() throws Exception {
		StringBuilder result = new StringBuilder();
		File helpFile = new File(ReaderUtils.getRoot(), "Assets/Configs/HelpInformation.txt");
		Scanner scanner = new Scanner(helpFile);
		while (scanner.hasNextLine()) {
			result.append(scanner.nextLine());
			result.append(System.lineSeparator());
		}
		scanner.close();
		return result.toString();
	}

}
