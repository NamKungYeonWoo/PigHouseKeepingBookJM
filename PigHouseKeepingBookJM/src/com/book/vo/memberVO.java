package com.book.vo;

public class memberVO {

	/*º¯¼ö*/
	String mid;
	String password;
	String name;
	String email;
	
	public memberVO() {
	}

	public memberVO(String mid, String password, String name, String email) {
		this.mid = mid;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
