package com.jqs.example.beans;

import java.io.Serializable;

public class BookNovel implements Serializable {
	//xsname,xsauthor,xsvalue,xspage,xspicture,xsintroduce,dhnumber
	private int xsid;
	private String xsname;
	private String xsauthor;
	private String xsvalue;
	private String xspage;
	private String xspicture;
	private String xsintroduce;
	private String dhnumber;
	public BookNovel(int xsid, String xsname, String xsauthor, String xsvalue,
			String xspage, String xspicture, String xsintroduce, String dhnumber) {
		super();
		this.xsid = xsid;
		this.xsname = xsname;
		this.xsauthor = xsauthor;
		this.xsvalue = xsvalue;
		this.xspage = xspage;
		this.xspicture = xspicture;
		this.xsintroduce = xsintroduce;
		this.dhnumber = dhnumber;
	}
	public int getXsid() {
		return xsid;
	}
	public void setXsid(int xsid) {
		this.xsid = xsid;
	}
	public String getXsname() {
		return xsname;
	}
	public void setXsname(String xsname) {
		this.xsname = xsname;
	}
	public String getXsauthor() {
		return xsauthor;
	}
	public void setXsauthor(String xsauthor) {
		this.xsauthor = xsauthor;
	}
	public String getXsvalue() {
		return xsvalue;
	}
	public void setXsvalue(String xsvalue) {
		this.xsvalue = xsvalue;
	}
	public String getXspage() {
		return xspage;
	}
	public void setXspage(String xspage) {
		this.xspage = xspage;
	}
	public String getXspicture() {
		return xspicture;
	}
	public void setXspicture(String xspicture) {
		this.xspicture = xspicture;
	}
	public String getXsintroduce() {
		return xsintroduce;
	}
	public void setXsintroduce(String xsintroduce) {
		this.xsintroduce = xsintroduce;
	}
	public String getDhnumber() {
		return dhnumber;
	}
	public void setDhnumber(String dhnumber) {
		this.dhnumber = dhnumber;
	}
	
	

}
