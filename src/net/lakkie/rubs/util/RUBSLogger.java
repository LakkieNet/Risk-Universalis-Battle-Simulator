package net.lakkie.rubs.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import net.lakkie.rubs.readers.ReaderUtils;

public class RUBSLogger extends Logger {

	private static final RUBSLogger logger = new RUBSLogger();
	private static final SimpleDateFormat format;
	
	private FileWriter writer;
	
	private RUBSLogger() {
		super("net.lakkie.rubs", null);
		try {
			this.writer = new FileWriter(new File(ReaderUtils.getRoot(), "Logs/" + generateLogName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void log(LogRecord record) {
		
	}
	
	public FileWriter getWriter() {
		return this.writer;
	}
	
	public static RUBSLogger getLogger() {
		return logger;
	}
	
	public static String generateLogName() {
		
	}
	
	static {
		format = new SimpleDateFormat("");
	}

}
