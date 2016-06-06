package com.yanghuan.Beans;

import java.io.Serializable;

public class Program implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//节目图片
	private String programing;
	//节目名
	private String programName;
	//节目类型
	private String programType;
	//主演
	private String person;
	
	public Program() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public String getPrograming() {
		return programing;
	}

	public void setPrograming(String programing) {
		this.programing = programing;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramType() {
		return programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
	
	

}
