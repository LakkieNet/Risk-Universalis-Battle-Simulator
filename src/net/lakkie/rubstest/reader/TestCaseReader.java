package net.lakkie.rubstest.reader;

import java.util.List;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.rubs.readers.ConfigurationReader;
import net.lakkie.rubstest.RUBSModuleTester;

public class TestCaseReader extends ConfigurationReader {

	private RUBSModuleTester testModule;
	private String[] args;

	public TestCaseReader(String path) {
		super(path);
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("test_case")) {
			machine.enterSection();
		}
		if (token.equals("classpath")) {
			String classpath = machine.readString();
			try {
				this.loadModule(classpath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (token.equals("arguments")) {
			List<String> argList = machine.readStringList();
			this.args = argList.toArray(new String[argList.size()]);
		}

	}

	public void loadModule(String classpath) throws Exception {
		Class<?> clazz = Class.forName(classpath);
		// Initialize without constructor arguments
		Object obj = clazz.getConstructor().newInstance();
		this.testModule = (RUBSModuleTester) obj;
	}

	public String[] getArguments() {
		return this.args;
	}

	public RUBSModuleTester getTestModule() {
		return this.testModule;
	}

}
