package net.lakkie.rubstest.tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;

import net.lakkie.rubs.readers.battle.BattleReader;
import net.lakkie.rubs.util.CipherUtils;
import net.lakkie.rubs.util.RUBSLogger;
import net.lakkie.rubs.util.ReaderUtils;
import net.lakkie.rubstest.RUBSModuleTester;

public class BattleCompilerTest extends RUBSModuleTester {

	public BattleCompilerTest() {
		super("battle_compiler");
	}

	public void run(String[] args) throws Exception {
		String input = args[0];
		String output = args[1];
		RUBSLogger.logger().info("Reading: " + input);
		RUBSLogger.logger().info("Writing: " + output);
		RUBSLogger.logger().info("Beginning battle reading...");
		BattleReader reader = new BattleReader(input);
		RUBSLogger.logger().info("Getting cipher...");
		Cipher cipher = CipherUtils.getCipher(Cipher.ENCRYPT_MODE);
		RUBSLogger.logger().info("Opening output stream...");
		File file = new File(ReaderUtils.getRoot(), output);
		if (!file.exists()) {
			file.createNewFile();
		}
		ObjectOutputStream out = new ObjectOutputStream(new CipherOutputStream(new FileOutputStream(file), cipher));
		out.writeObject(reader.battle);
		out.close();
		RUBSLogger.logger().info("Successfully wrote battle!");
	}

}
