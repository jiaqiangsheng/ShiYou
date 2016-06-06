package com.yanghuan.Beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class chathistory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  int luid;
	private int ltid ;
	private int ltvid;
	private Date ltdate;
	private List<wschat> wsid;
	public chathistory(int luid, int ltid, int ltvid, Date ltdate, List<wschat> wsid) {
		super();
		this.luid = luid;
		this.ltid = ltid;
		this.ltvid = ltvid;
		this.ltdate = ltdate;
		this.wsid = wsid;
	}
	public int getLuid() {
		return luid;
	}
	public void setLuid(int luid) {
		this.luid = luid;
	}
	public int getLtid() {
		return ltid;
	}
	public void setLtid(int ltid) {
		this.ltid = ltid;
	}
	public int getLtvid() {
		return ltvid;
	}
	public void setLtvid(int ltvid) {
		this.ltvid = ltvid;
	}
	public Date getLtdate() {
		return ltdate;
	}
	public void setLtdate(Date ltdate) {
		this.ltdate = ltdate;
	}
	public List<wschat> getWsid() {
		return wsid;
	}
	public void setWsid(List<wschat> wsid) {
		this.wsid = wsid;
	}
	@Override
	public String toString() {
		return "chathistory [luid=" + luid + ", ltid=" + ltid + ", ltvid=" + ltvid + ", ltdate=" + ltdate + ", wsid="
				+ wsid + "]";
	}
	
	
	

}
