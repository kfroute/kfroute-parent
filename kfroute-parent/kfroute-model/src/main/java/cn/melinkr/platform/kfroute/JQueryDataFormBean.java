package cn.melinkr.platform.kfroute;

import java.util.List;

/**
 * 
 * @author jquery dataform ajax的返回类型，此处进行拼接
 *
 */
public class JQueryDataFormBean {
	private List rows;
	private String sEcho;
	private int iTotalRecords;//总记录数
	private int iTotalDisplayRecords;

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public String getSEcho() {
		return sEcho;
	}

	public void setSEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public int getITotalRecords() {
		return iTotalRecords;
	}

	public void setITotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getITotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setITotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	
	
}
