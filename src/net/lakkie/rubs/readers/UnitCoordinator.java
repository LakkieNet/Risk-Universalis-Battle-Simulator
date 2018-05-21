package net.lakkie.rubs.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import net.lakkie.acl.parser.ACLParser;
import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.rubs.util.ReaderUtils;
import net.lakkie.acl.parser.IACLReader;

public class UnitCoordinator implements IACLReader {

	private static final UnitCoordinator instance = new UnitCoordinator("Assets/Configs/UnitCoordination.txt");
	public final Map<String, UnitImager> imagers = new HashMap<String, UnitImager>();

	public UnitCoordinator(String path) {
		try {
			ACLParser parser = new ACLParser(
					new File(ReaderUtils.getRoot(), path));
			parser.setReader(this);
			parser.readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("unit_type")) {
			machine.readSection(new UnitImager(this));
		}
	}

	public UnitImager getImager(String id) {
		return this.imagers.get(id);
	}

	public static UnitCoordinator getInstance() {
		return instance;
	}

}
