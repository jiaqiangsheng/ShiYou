package com.lxx.bean;

import java.io.Serializable;
import java.util.List;

public class SendDynamic implements Serializable {
	private int fuid;

	private String fcontext ;
	private List<String> imageListString;
	public SendDynamic(int fuid, String fcontext, List<String> imageListString) {
		super();

		this.fuid = fuid;

		this.fcontext = fcontext;
		this.imageListString = imageListString;

	}


	public int getFuid() {
		return fuid;
	}
	public void setFuid(int fuid) {
		this.fuid = fuid;
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
