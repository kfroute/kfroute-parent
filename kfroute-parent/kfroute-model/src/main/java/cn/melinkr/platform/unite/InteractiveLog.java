package cn.melinkr.platform.unite;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InteractiveLog {
	private Integer id;
	private String transactionID;
	private String SrcSysID;
	private String ServiceCode;
	private String ip;
	private String contentMsg;
	private Date modifyDate;
	private Integer costs;
	private Integer type;
	private String yearMonth = new SimpleDateFormat("yyyyMM").format(new Date());
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getSrcSysID() {
		return SrcSysID;
	}
	public void setSrcSysID(String srcSysID) {
		SrcSysID = srcSysID;
	}
	public String getServiceCode() {
		return ServiceCode;
	}
	public void setServiceCode(String serviceCode) {
		ServiceCode = serviceCode;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getContentMsg() {
		return contentMsg;
	}
	public void setContentMsg(String contentMsg) {
		this.contentMsg = contentMsg;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getCosts() {
		return costs;
	}
	public void setCosts(Integer costs) {
		this.costs = costs;
	}
	
	
	public String getYearMonth() {
		return yearMonth;
	}
	@Override
	public String toString() {
		return "InteractiveLog [id=" + id + ", transactionID=" + transactionID
				+ ", SrcSysID=" + SrcSysID + ", ServiceCode=" + ServiceCode
				+ ", ip=" + ip + ", contentMsg=" + contentMsg + ", modifyDate="
				+ modifyDate + ", costs=" + costs + ", type=" + type + "]";
	}
	
	
	
	
}
