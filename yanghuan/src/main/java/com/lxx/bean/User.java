package com.lxx.bean;

import java.io.Serializable;

public class User implements Serializable {
	private int uid;
	private String upicture;
	private String name;
	private String usex;
	
	private String uaddress;
	public User(int uid, String upicture, String name, String usex, String uaddress, String usign, String star,
			String ustate) {
		super();
		this.uid = uid;
		this.upicture = upicture;
		this.name = name;
		this.usex = usex;
		this.uaddress = uaddress;
		this.usign = usign;
		this.star = star;
		this.ustate = ustate;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUpicture() {
		return upicture;
	}
	public void setUpicture(String upicture) {
		this.upicture = upicture;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public String getUsign() {
		return usign;
	}
	public void setUsign(String usign) {
		this.usign = usign;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getUstate() {
		return ustate;
	}
	public void setUstate(String ustate) {
		this.ustate = ustate;
	}
	private String usign;
	private String star;
	private String ustate;
}
