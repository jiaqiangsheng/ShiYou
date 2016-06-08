package com.lxx.bean;

import java.io.Serializable;

public class CommentBean implements Serializable{
	
	public CommentBean( int cpid, int cqid, int cuid, String ccontext) {
		super();
		this.cpid = cpid;
		this.cqid = cqid;
		this.cuid = cuid;
		this.ccontext = ccontext;
	}
	public int getCpid() {
		return cpid;
	}
	public void setCpid(int cpid) {
		this.cpid = cpid;
	}
	public int getCqid() {
		return cqid;
	}
	public void setCqid(int cqid) {
		this.cqid = cqid;
	}
	public int getCuid() {
		return cuid;
	}
	public void setCuid(int cuid) {
		this.cuid = cuid;
	}
	public String getCcontext() {
		return ccontext;
	}
	public void setCcontext(String ccontext) {
		this.ccontext = ccontext;
	}
	int cpid;//说说id
	int cqid;//发表说说人的id
	int cuid;//评论人的uid;
	String ccontext;
}
