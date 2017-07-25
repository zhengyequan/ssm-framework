package com.yqbaba.test.error;

import com.yqbaba.framework.error.IError;

public enum TestError implements IError {

	TEST(-100);

	private final int code;

	private TestError(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
