package net.lakkie.rubs.util;

public class MathUtils {

	private MathUtils() {
	}

	public static float cap(float value, float min, float max) {
		if (value < min)
			return min;
		if (value > max)
			return max;
		return value;
	}

}
