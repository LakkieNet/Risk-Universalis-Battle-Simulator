package net.lakkie.rubs.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import net.lakkie.rubs.args.RUBSArgumentParser;

public class RUBSLogger extends Logger {

	public static final SimpleDateFormat logFormat = new SimpleDateFormat("yyyy-MM-dd-ss-SSS");
	public static final SimpleDateFormat prefixFormat = new SimpleDateFormat("kk-mm-ss");
	public static final File logDirectory = new File(ReaderUtils.getRoot(), "Logs/");
	private static final RUBSLogger logger = new RUBSLogger();

	private FileWriter writer;
	private File file;
	private String logID;
	private boolean firstLog = true;

	private RUBSLogger() {
		super("net.lakkie.rubs", null);
		this.logID = generateLogName();
		try {
			if (!RUBSArgumentParser.noLog) {
				if (!logDirectory.exists()) {
					logDirectory.mkdirs();
				}
				this.file = new File(logDirectory, this.logID + ".log");
				if (!this.file.exists()) {
					this.file.createNewFile();
				}
				this.writer = new FileWriter(this.file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.info("Creating log on " + this.getName() + ": " + this.logID);
	}

	public void log(LogRecord record) {
		String message = String.format("(%s %s): %s", prefixFormat.format(new Date()), record.getLevel().getName(), record.getMessage());
		System.out.println(message);
		try {
			if (this.firstLog) {
				this.firstLog = false;
			} else {
				if (!RUBSArgumentParser.noLog) {
					this.writer.write(System.lineSeparator());
				}
			}
			if (!RUBSArgumentParser.noLog) {
				this.writer.write(message);
				this.writer.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FileWriter getWriter() {
		return this.writer;
	}

	public static Logger logger() {
		return logger;
	}

	public static String generateLogName() {
		return logFormat.format(new Date());
	}

}
