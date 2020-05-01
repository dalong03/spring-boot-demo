package com.fb.springbootdemo.base;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -4956238857537565084L;
	private ErrorCodeEnum errorCode;

	public ServiceException() {
	}

	public ServiceException(ErrorCodeEnum errorCode) {
		this.errorCode = errorCode;
	}

	public ServiceException(ErrorCodeEnum errorCode, String externMsg) {
		this.errorCode = errorCode;
		String msg = errorCode.getMsg();
		msg = msg.replace("{}", externMsg);
		errorCode.setMsg(msg);
	}

	public ErrorCodeEnum getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodeEnum errorCode) {
		this.errorCode = errorCode;
	}

}
