package com.yanghuan.Beans;

import java.io.Serializable;

public class wschat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int wsid;
	private String wsname;
	private String wstype;
	private String wspicture;
	private String jxprogram;
	public wschat(int wsid, String wsname, String wstype, String wspicture, String jxprogram) {
		super();
		this.wsid = wsid;
		this.wsname = wsname;
		this.wstype = wstype;
		this.wspicture = wspicture;
		this.jxprogram = jxprogram;
	}
	public int getWsid() {
		return wsid;
	}
	public void setWsid(int wsid) {
		this.wsid = wsid;
	}
	public String getWsname() {
		return wsname;
	}
	public void setWsname(String wsname) {
		this.wsname = wsname;
	}
	public String getWstype() {
		return wstype;
	}
	public void setWstype(String wstype) {
		this.wstype = wstype;
	}
	public String getWspicture() {
		return wspicture;
	}
	public void setWspicture(String wspicture) {
		this.wspicture = wspicture;
	}
	public String getJxprogram() {
		return jxprogram;
	}
	public void setJxprogram(String jxprogram) {
		this.jxprogram = jxprogram;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "wschat [wsid=" + wsid + ", wsname=" + wsname + ", wstype=" + wstype + ", wspicture=" + wspicture
				+ ", jxprogram=" + jxprogram + "]";
	}
	

}
