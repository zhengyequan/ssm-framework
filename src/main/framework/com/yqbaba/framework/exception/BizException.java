package com.yqbaba.framework.exception;

public class BizException extends RuntimeException {

	private static final long serialVersionUID = 8637333626392519697L;

	private final Enum<?> errorCode;
	private final Object[] params;

	public BizException(Enum<?> errorCode, Object... params) {
		this.errorCode = errorCode;
		this.params = params;
	}

	public BizException(Enum<?> errorCode) {
		this(errorCode, (Object[]) null);
	}

	public Enum<?> getErrorCode() {
		return errorCode;
	}

	public Object[] getParams() {
		return params;
	}

	public String getMessage() {
		return errorCode.toString();
	}

	public String toString() {
		return getMessage();
	}

	public synchronized Throwable fillInStackTrace() {
		return this;
	}
}
