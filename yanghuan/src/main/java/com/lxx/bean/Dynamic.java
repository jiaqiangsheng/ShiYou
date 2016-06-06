package com.lxx.bean;

import java.util.List;

public class Dynamic {
	private int fid;
	private int fuid;
	private String fdata;
	private String fcontext ;
	private List<String> imageListString;
	private User user;
	public Dynamic(int fid, int fuid, String fdata, String fcontext, List<String> imageListString, User user) {
		super();
		this.fid = fid;
		this.fuid = fuid;
		this.fdata = fdata;
		this.fcontext = fcontext;
		this.imageListString = imageListString;
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getFuid() {
		return fuid;
	}
	public void setFuid(int fuid) {
		this.fuid = fuid;
	}
	public String getFdata() {
		return fdata;
	}
	public void setFdata(String fdata) {
		this.fdata = fdata;
	}
	public String getFcontext() {
		return fcontext;
	}
	public void setFcontext(String fcontext) {
		this.fcontext = fcontext;
	}
	public List<String> getImageListString() {
		return imageListString;
	}
	public void setImageListString(List<String> imageListString) {
		this.imageListString = imageListString;
	}
	

}
