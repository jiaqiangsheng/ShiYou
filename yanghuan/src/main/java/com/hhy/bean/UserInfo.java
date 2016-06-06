package com.hhy.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable{
	private int uid;
	private String url;
	private String uname;
	private String usign;
	private String star;
	private String usex;
	private String uaddress;
	private int mcareNumber;
	private int ocareNumber;
	private String flag;
	private int flag1;

	public int getFlag1() {
		return flag1;
	}

	public void setFlag1(int flag1) {
		this.flag1 = flag1;
	}

	@SerializedName("otherInfoList")//设置此属性下面的list可以重新起名，否则必须web端的一模一样
	private List<OtherInfoBean> otherInfoList1;//otherInfoList

	public List<OtherInfoBean> getBeanList() {
		return otherInfoList1;
	}

	public void setBeanList(List<OtherInfoBean> beanList) {
		otherInfoList1 = beanList;
	}

	public int getMcareNumber() {
		return mcareNumber;
	}

	public void setMcareNumber(int mcareNumber) {
		this.mcareNumber = mcareNumber;
	}

	public int getOcareNumber() {
		return ocareNumber;
	}

	public void setOcareNumber(int ocareNumber) {
		this.ocareNumber = ocareNumber;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
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

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public UserInfo() {
		// TODO Auto-generated constructor stub
	}

	public UserInfo(int uid,String url, String uname, String usign) {
		this.uid = uid;
		this.url = url;
		this.uname = uname;
		this.usign = usign;
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
		return "UserInfo{" +
				"uid=" + uid +
				", url='" + url + '\'' +
				", uname='" + uname + '\'' +
				", usign='" + usign + '\'' +
				", star='" + star + '\'' +
				", usex='" + usex + '\'' +
				", uaddress='" + uaddress + '\'' +
				", mcareNumber=" + mcareNumber +
				", ocareNumber=" + ocareNumber +
				", flag='" + flag + '\'' +
				", mBeanList=" + otherInfoList1 +
				'}';
	}
}
