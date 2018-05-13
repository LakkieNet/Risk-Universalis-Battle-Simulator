package net.lakkie.battlesim;

import java.awt.Font;
import java.text.DecimalFormat;

public class BasicUtils {

	private static final DecimalFormat commaFormat = new DecimalFormat(
			"###,###,###,###");
	private static final DecimalFormat slopeFormat = new DecimalFormat(
			"###,##0.00");
	private static final Font unitInfoFont = new Font("Arial", Font.PLAIN, 8);
	private static final Font commentFont = new Font("Arial", Font.PLAIN, 15);

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

}
