package net.lakkie.rubs.util;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.Random;

public class BasicUtils {

	private static final DecimalFormat commaFormat = new DecimalFormat("###,###,###,###");
	private static final DecimalFormat slopeFormat = new DecimalFormat("###,##0.00");
	private static final Font unitInfoFont = new Font("Arial", Font.PLAIN, 8);
	private static final Font commentFont = new Font("Arial", Font.PLAIN, 15);
	private static final Random random = new Random();

	private BasicUtils() {
	}

	public static String formatCommas(int number) {
		return commaFormat.format(number);
	}

	public static DecimalFormat getCommaFormat() {
		return commaFormat;
	}

	public static Font getCommentFont() {
		return commentFont;
	}

	public static Font getUnitInfoFont() {
		return unitInfoFont;
	}

	public static DecimalFormat getSlopeFormat() {
		return slopeFormat;
	}

	public static float getSignMultiplier(float value) {
		if (value == 0) {
			return 0f;
		}
		if (value < 0) {
			return -1f;
		}
		if (value > 0) {
			return 1f;
		}
		return 0f;
	}

	public static String formatStringObject(Object o) {
		char[] before = o.toString().toCharArray();
		StringBuilder result = new StringBuilder();
		int level = 0;
		for (char c : before) {
			switch (c) {
				// Special cases
				case '[' :
					level++;
					result.append("[\n");
					result.append(repeatCharacters("\t", level));
					continue;
				case ']' :
					level--;
					result.append('\n');
					if (level > 0) {
						result.append(repeatCharacters("\t", level));
					}
					result.append(']');
					continue;
				case ',' :
					result.append(", ");
					continue;
				case '=' :
					result.append(" = ");
					continue;
				default  :
					result.append(c);
			}
		}
		return result.toString();
	}

	public static String repeatCharacters(String str, int amount) {
		StringBuilder result = new StringBuilder();
		if (amount <= 0) {
			throw new IllegalArgumentException("Amount must be > 0");
		}
		for (int i = 0; i < amount; i++) {
			result.append(str);
		}
		return result.toString();
	}
	
	public static Random getRandom() {
		return random;
	}

}
