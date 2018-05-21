package net.lakkie.rubs.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import net.lakkie.acl.parser.ACLParser.ACLParserMachine;
import net.lakkie.rubs.readers.ConfigurationReader;

public class CipherUtils extends ConfigurationReader {

	private static final CipherUtils inst = new CipherUtils();
	private String mode, pattern;

	private CipherUtils() {
		super("Assets/Configs/CipherDetails.txt");
	}

	public void onToken(String token, ACLParserMachine machine) {
		if (token.equals("cipher")) {
			machine.enterSection();
		}
		if (token.equals("mode")) {
			this.mode = machine.readString();
		}
		if (token.equals("cipher_pattern")) {
			this.pattern = machine.readString();
		}
	}

	public static Cipher getCipher(int cipherMode) {
		try {
			Cipher cipher = Cipher.getInstance(inst.mode);
			cipher.init(cipherMode, new SecretKeySpec(inst.pattern.getBytes(), inst.mode));
			return cipher;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
