package com.yanghuan.Beans;

import java.io.Serializable;

public class Prochat implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int proid;
	private String proname;
	private String protype;
	private String propicture;
	private String jxprogram;
	public Prochat(int proid, String proname, String protype, String propicture, String jxprogram) {
		super();
		this.proid = proid;
		this.proname = proname;
		this.protype = protype;
		this.propicture = propicture;
		this.jxprogram = jxprogram;
	}
	public int getProid() {
		return proid;
	}
	public void setProid(int proid) {
		this.proid = proid;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public String getProtype() {
		return protype;
	}
	public void setProtype(String protype) {
		this.protype = protype;
	}
	public String getPropicture() {
		return propicture;
	}
	public void setPropicture(String propicture) {
		this.propicture = propicture;
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
		return "prochat [proid=" + proid + ", proname=" + proname + ", protype=" + protype + ", propicture="
				+ propicture + ", jxprogram=" + jxprogram + "]";
	}
}
