package com.yqbaba.framework.util;

import java.util.UUID;

public class GUID {

	public static String get() {
		UUID uuid = UUID.randomUUID();
		StringBuffer sb = new StringBuffer();
		get(sb, uuid.getMostSignificantBits());
		get(sb, uuid.getLeastSignificantBits());
		return sb.toString();
	}

	private static StringBuffer get(StringBuffer sb, long bits) {
		int count = 13;
		while (count-- > 0) {
			long low = bits & 0x1FL;
			if (low < 10L) {
				sb.append((char) ('0' + low));
			} else {
				sb.append((char) ('A' + (low - 10)));
			}

			bits >>>= 5;
		}

		return sb;
	}

}
