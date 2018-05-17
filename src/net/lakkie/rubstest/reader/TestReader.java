package net.lakkie.rubstest.reader;

import java.util.List;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.rubs.readers.ConfigurationReader;

public class TestReader extends ConfigurationReader {

	private List<String> tests;

	public TestReader(String path) {
		super(path);
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("test_sequence")) {
			this.tests = machine.readStringList();
		}
	}
	
	public List<String> getTests() {
		return this.tests;
	}

}
