package com.yqbaba.test.error;

public enum TestError {

	TEST(0);

	private final int code;

	private TestError(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
