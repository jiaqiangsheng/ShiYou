package com.lxx.bean;

import java.io.Serializable;
import java.util.List;

public class SelectCommentBean implements Serializable{
	


	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
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
	public String getCdata() {
		return cdata;
	}
	public void setCdata(String cdata) {
		this.cdata = cdata;
	}
	int cid; //评论id
	int cpid;//说说id
	int cqid;//发表说说人的id
	int cuid;//评论人的uid;
	String ccontext;
	String uname;//名字
	public List<SelectReplyBean> getListSelectReplyBeans() {
		return listSelectReplyBeans;
	}
	public SelectCommentBean(int cid, int cpid, int cqid, int cuid, String ccontext, String uname,
			List<SelectReplyBean> listSelectReplyBeans, String upicture, String cdata) {
		super();
		this.cid = cid;
		this.cpid = cpid;
		this.cqid = cqid;
		this.cuid = cuid;
		this.ccontext = ccontext;
		this.uname = uname;
		this.listSelectReplyBeans = listSelectReplyBeans;
		this.upicture = upicture;
		this.cdata = cdata;
	}
	public void setListSelectReplyBeans(List<SelectReplyBean> listSelectReplyBeans) {
		this.listSelectReplyBeans = listSelectReplyBeans;
	}
	List<SelectReplyBean> listSelectReplyBeans;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpicture() {
		return upicture;
	}
	public void setUpicture(String upicture) {
		this.upicture = upicture;
	}
	String upicture;//头像
	String cdata;//消息推送时间显示
}
