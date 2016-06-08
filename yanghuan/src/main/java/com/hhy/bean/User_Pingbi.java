package com.hhy.bean;

import java.io.Serializable;

public class User_Pingbi implements Serializable{
	private int uid;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	private String url;
	private String uname;
	private String usign;
	private int flag;

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public User_Pingbi() {
		// TODO Auto-generated constructor stub
	}

	public User_Pingbi(int uid, String url, String uname, String usign, int flag) {
		this.uid = uid;
		this.url = url;
		this.uname = uname;
		this.usign = usign;
		this.flag = flag;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsign() {
		return usign;
	}

	public void setUsign(String usign) {
		this.usign = usign;
	}

	@Override
	public String toString() {
		return "User_Pingbi{" +
				"uid=" + uid +
				", url='" + url + '\'' +
				", uname='" + uname + '\'' +
				", usign='" + usign + '\'' +
				", flag=" + flag +
				'}';
	}
}
