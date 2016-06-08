package com.lxx.bean;

import java.io.Serializable;

public class ReplyBean implements Serializable {
	int ruid;//回复人的id
	int rqid;//发表评论人的id
	int rcid;//评论id,区分哪条评论
	String ccontext;//回复内容
	public ReplyBean(int ruid, int rqid, int rcid, String ccontext, String rdate) {
		super();
		this.ruid = ruid;
		this.rqid = rqid;
		this.rcid = rcid;
		this.ccontext = ccontext;
		this.rdate = rdate;
	}
	public int getRuid() {
		return ruid;
	}
	public void setRuid(int ruid) {
		this.ruid = ruid;
	}
	public int getRqid() {
		return rqid;
	}
	public void setRqid(int rqid) {
		this.rqid = rqid;
	}
	public int getRcid() {
		return rcid;
	}
	public void setRcid(int rcid) {
		this.rcid = rcid;
	}
	public String getCcontext() {
		return ccontext;
	}
	public void setCcontext(String ccontext) {
		this.ccontext = ccontext;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	String rdate;//回复的日期
	

}
