package net.lakkie.battlesim;

public class NamingUtils {

	public static String getBattleName(String name) {
		name = name.toLowerCase();
		// Assure "the" stays lowercase
		if (name.startsWith("the ")) {
			name = name.replaceFirst("the ", "");
			name = "the " + capitalizeWordSequence(name);
		} else {
			name = capitalizeWordSequence(name);
		}
		return "Battle of " + name;
	}

	public static String getRootBattleName(String evalName) {
		return capitalizeWordSequence(evalName.toLowerCase().replaceFirst("battle of ", ""));
	}

	public static String getBattleIdentifier(String name) {
		return name.toLowerCase().replaceAll("\\s", "");
	}

	public static String capitalize(String word) {
		if (word.length() < 1) {
			return word;
		}
		char[] chars = word.toLowerCase().toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);
		return new String(chars);
	}

	public static String capitalizeWordSequence(String words) {
		String[] wordArray;
		if (!words.equals(words.replaceFirst("\\s", ""))) {
			wordArray = words.split("\\s+");
		} else {
			wordArray = new String[]{words};
		}
		StringBuilder result = new StringBuilder();
		for (String word : wordArray) {
			result.append(capitalize(word));
			result.append(' ');
		}
		return new String(result);
	}

}
