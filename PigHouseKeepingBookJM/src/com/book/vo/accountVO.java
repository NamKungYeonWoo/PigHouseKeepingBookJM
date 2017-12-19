package com.book.vo;

public class accountVO {
	
	/*º¯¼ö*/
	String aid;
	String mid;
	String mdate;
	String content;
	int balance;
	int code;
	String inout;
	String cname;

	public accountVO() {
	}

	public accountVO(String aid, String mid, String mdate, String content, int balance, int code, String inout, String cname) {
		super();
		this.aid = aid;
		this.mid = mid;
		this.mdate = mdate;
		this.content = content;
		this.balance = balance;
		this.code = code;
		this.inout = inout;
		this.cname = cname;
	}

	public accountVO(String mid, String mdate, String content, int balance, int code, String inout) {
		super();
		this.mid = mid;
		this.mdate = mdate;
		this.content = content;
		this.balance = balance;
		this.code = code;
		this.inout = inout;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getInout() {
		return inout;
	}

	public void setInout(String inout) {
		this.inout = inout;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
	
	
	
	
	

}
