package net.lakkie.battlesim.readers;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;

public class GraphicsProperties extends ConfigurationReader {

	private static final GraphicsProperties instance = new GraphicsProperties();
	public int fps;
	
	public GraphicsProperties() {
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
	
	public static GraphicsProperties getInstance() {
		return instance;
	}

}
