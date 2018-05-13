package net.lakkie.battlesim.readers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.acl.parser.IACLReader;

public class RUBSImage implements IACLReader {

	private String name;
	private final Map<Integer, BufferedImage> images = new HashMap<Integer, BufferedImage>();
	private int lastResolution = 0;
	private ImageLoader parent;
	
	public RUBSImage(ImageLoader parent) {
		this.parent = parent;
	}
	
	public void onToken(String token, ACLParserMachine machine) {
		// Unnecessary but saves resources and helps readability
		if (token.equals("name")) {
			this.name = machine.readString();
		}
		if (token.equals("resolution")) {
			machine.enterSection();
		}
		if (token.equals("quality")) {
			this.lastResolution = machine.readInt();
		}
		if (token.equals("path")) {
			this.images.put(this.lastResolution, this.readImage(machine.readString()));
		}
	}
	
	public void onFinish() {
		this.parent.images.put(this.name, this);
	}
	
	private BufferedImage readImage(String path) {
		try {
			return ImageIO.read(new File(ReaderUtils.getRoot(), path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public BufferedImage get(int resolution) {
		return this.images.get(resolution);
	}
	
	public String getName() {
		return this.name;
	}

}
