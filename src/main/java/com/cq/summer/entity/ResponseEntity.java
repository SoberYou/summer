package com.cq.summer.entity;

import java.io.Serializable;

public class ResponseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 新增数据id(用于新增数据接口，返回新数据id)
	 */
	private Long id;

	/**
	 * 返回成功判断
	 */
	private boolean success;

	/**
	 * 响应状态码
	 */
	private String code;

	/**
	 * 相应数据
	 */
	private Object data;

	/**
	 * 提示语句
	 */
	private String message;

	public static ResponseEntity ok(Object o) {
		ResponseEntity r = new ResponseEntity();
		r.setSuccess(true);
		r.setCode("200");
		r.setData(o);
		return r;
	}

	public static ResponseEntity ok(String msg) {
		ResponseEntity r = new ResponseEntity();
		r.setSuccess(true);
		r.setCode("200");
		r.setMessage(msg);
		return r;
	}

	public static ResponseEntity error() {
		ResponseEntity r = new ResponseEntity();
		r.setSuccess(false);
		r.setCode("500");
		return r;
	}

	public static ResponseEntity error(String msg) {
		ResponseEntity r = new ResponseEntity();
		r.setSuccess(false);
		r.setCode("500");
		r.setMessage(msg);
		return r;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
