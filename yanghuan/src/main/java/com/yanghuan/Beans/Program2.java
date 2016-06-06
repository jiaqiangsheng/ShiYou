package com.yanghuan.Beans;

import java.io.Serializable;
import java.util.List;

public class Program2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//主演
	private String person;
	//节目图片
	private String programing;
	//节目名
	private String programName;
	//节目类型
	private String programType;
	 
	private List<Program> list;
	
	public Program2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Program2(String programing, String programName,
			String programType,List<Program> list) {
		super();
		
		this.programing = programing;
		this.programName = programName;
		this.programType = programType;
		
		this.list = list;
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
	
	public List<Program> getList() {
		return list;
	}
	public void setList(List<Program> list) {
		this.list = list;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}


}
