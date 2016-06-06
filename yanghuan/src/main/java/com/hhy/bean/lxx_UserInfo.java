package com.hhy.bean;

public class lxx_UserInfo {

	public int getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(int userPhoto) {
		this.userPhoto = userPhoto;
	}

	public String getUserQQ() {
		return userQQ;
	}

	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getDeleteButtonRes() {
		return deleteButtonRes;
	}

	public void setDeleteButtonRes(int deleteButtonRes) {
		this.deleteButtonRes = deleteButtonRes;
	}

	public int userPhoto;
	public String userQQ=null;
	public String userPassword=null;
	public int deleteButtonRes;
	public lxx_UserInfo(int userPhoto, String userQQ, String userPassword, int deleteButtonRes) {
		super();
		this.userPhoto = userPhoto;
		this.userQQ = userQQ;
		this.deleteButtonRes = deleteButtonRes;
		this.userPassword=userPassword;
	}
	
}
