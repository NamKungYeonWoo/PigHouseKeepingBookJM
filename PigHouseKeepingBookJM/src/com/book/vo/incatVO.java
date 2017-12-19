package com.book.vo;

public class incatVO {
	
	/*변수*/
	int code;
	String name;

	/*생성자*/
	public incatVO() {
	}

	public incatVO(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	/*getter, setter*/
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
