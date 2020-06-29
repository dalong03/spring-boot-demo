package com.fb.springbootdemo.common;

public enum ErrorCodeEnum {

	/**
	 * 无效的码值
	 */
	INVALID_COMMON_CODE(1001, "无效的码值，请先定义【{}】的码值"),
	
	/**
	 * 码值不全
	 */
	INCOMPLETE_CODE(1002, "码值不全，请补充"),
	
	/**
	 * 无效的文件名称
	 */
	INVALID_FILE_NAME(1003, "无效的文件名称"),
	
	/**
	 * 表单名称有误
	 */
	INVALID_SHEET_NAME(1004, "表单名称有误"),
	
	/**
	 * excel文件的序号存在空值
	 */
	EXCEL_BLANK_VALUE(1005, "excel文件的序号存在空值");

	private ErrorCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private int code;// 错误码
	private String msg;// 错误描述

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
