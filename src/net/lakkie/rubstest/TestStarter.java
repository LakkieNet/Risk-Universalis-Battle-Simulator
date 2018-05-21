package net.lakkie.rubstest;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import net.lakkie.rubs.util.ReaderUtils;
import net.lakkie.rubstest.reader.TestCaseReader;
import net.lakkie.rubstest.reader.TestReader;

public class TestStarter {

	public static final TestReader runner = new TestReader("Assets/Configs/Test/TestRunner.txt");
	
	private TestStarter() {
	}

	public static void performConfigTests() throws Exception {
		Map<String, TestCaseReader> tests = readTestConfigs();
		for (String testID : runner.getTests()) {
			TestCaseReader testCase = tests.get(testID);
			System.out.println("Start " + testID + " test:");
			testCase.getTestModule().run(testCase.getArguments());
		}
	}

	public static Map<String, TestCaseReader> readTestConfigs() {
		Map<String, TestCaseReader> result = new HashMap<String, TestCaseReader>();
		File testDir = new File(ReaderUtils.getRoot(), "Assets/Configs/Test/");
		File[] children = testDir.listFiles();
		for (File child : children) {
			if (child.getName().equals("TestRunner.txt")) {
				// Ignore profile
				continue;
			}
			Path relativePath = ReaderUtils.getRoot().toPath().relativize(child.toPath());
			TestCaseReader testCase = new TestCaseReader(relativePath.toString());
			result.put(testCase.getTestModule().getTestID(), testCase);
		}
		return result;
	}

}
