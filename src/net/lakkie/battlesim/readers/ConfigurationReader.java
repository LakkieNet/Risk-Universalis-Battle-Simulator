package net.lakkie.battlesim.readers;

import java.io.File;
import java.io.FileNotFoundException;

import net.lakkie.acl.parser.ACLParser;
import net.lakkie.acl.parser.IACLReader;

public abstract class ConfigurationReader implements IACLReader {

	public ConfigurationReader(String path) {
		try {
			ACLParser parser = new ACLParser(new File(ReaderUtils.getRoot(), path));
			parser.setReader(this);
			parser.readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
