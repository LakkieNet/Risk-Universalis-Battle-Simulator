package net.lakkie.rubs.readers;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;

public class GraphicsPropertiesReader extends ConfigurationReader {

	private static final GraphicsPropertiesReader instance = new GraphicsPropertiesReader();
	public int fps;
	
	public GraphicsPropertiesReader() {
		super("Assets/Configs/GraphicsProperties.txt");
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("graphical_properties")) {
			machine.enterSection();
		}
		if (token.equals("frames_per_second")) {
			this.fps = machine.readInt();
		}
	}
	
	public static GraphicsPropertiesReader getInstance() {
		return instance;
	}

}
