package com.neom.framework.exception;

public class BusinessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ErrorMsg errorMsg;

	public BusinessException() {
		this.errorMsg = new ErrorMsg();
	}

	public BusinessException(String message) {
		super(message);
		this.errorMsg = new ErrorMsg();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		this.errorMsg = new ErrorMsg();
	}

	public BusinessException(String msgCode, String[] msgParams, String message, Throwable cause) {
		super(message, cause);
		this.errorMsg = new ErrorMsg(msgCode, msgParams);
	}

	public BusinessException(String msgCode, String[] msgParams, String message) {
		super(message);
		this.errorMsg = new ErrorMsg(msgCode, msgParams);
	}

	public BusinessException(String msgCode, String message, Throwable cause) {
		super(message, cause);
		this.errorMsg = new ErrorMsg(msgCode);
	}

	public BusinessException(String msgCode, String message) {
		super(message);
		this.errorMsg = new ErrorMsg(msgCode);
	}

	public BusinessException(String msgCode, String msgParam, String message, Throwable cause) {
		super(message, cause);
		this.errorMsg = new ErrorMsg(msgCode, msgParam);
	}

	public ErrorMsg getErrorMsg() {
		return this.errorMsg;
	}

	public void setErrorMsg(ErrorMsg errorMsg) {
		this.errorMsg = errorMsg;
	}
}
