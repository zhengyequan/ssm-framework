package com.yqbaba.framework.util;

import java.util.Collection;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

public class StringUtil extends StringUtils {

	public static boolean containsIgnoreCase(Collection<String> col, String str) {
		if (col == null || str == null) {
			return false;
		}

		for (String s : col) {
			if (str.equalsIgnoreCase(s)) {
				return true;
			}
		}

		return false;
	}

	public static boolean containsIgnoreCase(String[] arr, String str) {
		if (arr == null || str == null) {
			return false;
		}

		for (String s : arr) {
			if (str.equalsIgnoreCase(s)) {
				return true;
			}
		}

		return false;
	}

	public static String getUuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 少于或等于4个字母：直接显示 5到6个字母：中间的2个字母设置为星号 7到8个字母：中间的3个字母设置为星号
	 * 9到10个字母：中间的4个字母设置为星号 11个或以上的字母：中间的5个字母设置为星号
	 * 
	 * @param str
	 * @return
	 */
	public static String hiddenChar(String str) {
		if (isBlank(str)) {
			return "";
		}

		int length = str.length();
		if (length <= 4) {
			return str;
		}

		int mid = length / 2;
		if (length <= 6) {
			return str.substring(0, mid - 1) + "**" + str.substring(mid + 1);
		}

		if (length <= 8) {
			return str.substring(0, mid - 1) + "***" + str.substring(mid + 2);
		}

		if (length <= 10) {
			return str.substring(0, mid - 2) + "****" + str.substring(mid + 2);
		}

		return str.substring(0, mid - 2) + "*****" + str.substring(mid + 3);
	}

	public static String quotes(String str) {
		String quote = "\"";
		if (str == null) {
			str = StringUtils.EMPTY;
		}
		return quote + str + quote;
	}
}
