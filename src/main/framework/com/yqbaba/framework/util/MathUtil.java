package com.yqbaba.framework.util;

import java.math.BigDecimal;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

public class MathUtil {

	public static Integer parseInteger(Object o) {
		if (o == null) {
			return null;
		}

		if (o instanceof Integer) {
			return (Integer) o;
		}

		String text = o.toString();
		if (StringUtils.isBlank(text)) {
			return null;
		}

		return Integer.valueOf(text);
	}

	public static BigDecimal parseBigDecimal(Object o) {
		if (o == null) {
			return null;
		}

		if (o instanceof BigDecimal) {
			return (BigDecimal) o;
		}

		String text = o.toString();
		if (StringUtils.isBlank(text)) {
			return null;
		}

		return new BigDecimal(text);
	}

	public static String trim(BigDecimal value) {
		if (value == null) {
			return "";
		}

		String s = value.toString();
		if (value.scale() <= 0) {
			return s;
		}

		int i;
		for (i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			if (c != '0') {
				if (c == '.') {
					i--;
				}

				break;
			}
		}

		return s.substring(0, i + 1);
	}

	public static String percent(BigDecimal value) {
		if (value == null) {
			return "";
		}

		return trim(value.multiply(new BigDecimal(100)));
	}

	public static int rand(int n) {
		return new Random().nextInt(n);
	}

	public static long yuan2fen(BigDecimal yuan) {
		if (yuan == null) {
			return -1;
		}
		return yuan.multiply(new BigDecimal(100)).intValue();
	}

	public static BigDecimal fen2yuan(long fen) {
		if (fen < 0) {
			return null;
		}
		return new BigDecimal(fen).divide(new BigDecimal(100)).setScale(2);
	}

}
