package net.lakkie.rubs.readers;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;

public class ImageLoader extends ConfigurationReader {

	private static final ImageLoader instance = new ImageLoader("Assets/Configs/ImageLoader.txt");
	
	final Map<String, RUBSImage> images = new HashMap<String, RUBSImage>();
	
	public ImageLoader(String path) {
		super(path);
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("image")) {
			RUBSImage image = new RUBSImage(this);
			machine.readSection(image);
			System.out.println("Read");
		}
	}
	
	public RUBSImage getImage(String name) {
		return this.images.get(name);
	}
	
	public BufferedImage getSpecificImage(String name, int resolution) {
		return this.getImage(name).get(resolution);
	}

	public static ImageLoader getInstance() {
		return instance;
	}

}
