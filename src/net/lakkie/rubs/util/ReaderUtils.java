package net.lakkie.rubs.util;

import java.io.File;
import java.nio.file.Paths;

public class ReaderUtils {

	private static final File root;
	
	private ReaderUtils() {
	}
	
	public static File getRoot() {
		return root;
	}
	
	static {
		root = new File(Paths.get(".").toAbsolutePath().toString());
	}
	
}
