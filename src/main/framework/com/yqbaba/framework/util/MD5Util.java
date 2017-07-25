package com.yqbaba.framework.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static String digest(String str) {
		byte[] barr;

		try {
			barr = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		return digest(barr, 0, barr.length);
	}

	public static String digest(byte[] barr, int offset, int len) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		md.update(barr, offset, len);

		byte[] bytes = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);

			if (hex.length() == 1) {
				sb.append('0');
			}

			sb.append(hex);
		}

		return sb.toString();
	}

}
