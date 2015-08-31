package cn.melinkr.platform.kfroute;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginOpr {
	private String operId;
	private int beOperType;
	private String beOperObj;
	private String operName;
	private String operCode;
	private int payType;
	private double payMoney;
	private Timestamp operTime;
	private int operType;
	private String operIp;
	private String operNote;
	private int operDate = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date()));
	private String yearMonth = new SimpleDateFormat("yyyyMM").format(new Date());
	
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public String getOperCode() {
		return operCode;
	}
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
	public Timestamp getOperTime() {
		return operTime;
	}
	public void setOperTime(Timestamp operTime) {
		this.operTime = operTime;
	}
	public String getOperIp() {
		return operIp;
	}
	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}
	public String getOperNote() {
		return operNote;
	}
	public void setOperNote(String operNote) {
		this.operNote = operNote;
	}
	
	public String getYearMonth() {
		return yearMonth;
	}

	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}
	public int getOperDate() {
		return operDate;
	}
	public void setOperDate(int operDate) {
		this.operDate = operDate;
	}
	
	public int getBeOperType() {
		return beOperType;
	}
	public void setBeOperType(int beOperType) {
		this.beOperType = beOperType;
	}
	public String getBeOperObj() {
		return beOperObj;
	}
	public void setBeOperObj(String beOperObj) {
		this.beOperObj = beOperObj;
	}
	
	public int getOperType() {
		return operType;
	}
	public void setOperType(int operType) {
		this.operType = operType;
	}
	@Override
	public String toString() {
		return "LoginOpr [operId=" + operId + ", beOperType=" + beOperType
				+ ", beOperObj=" + beOperObj + ", operName=" + operName
				+ ", operCode=" + operCode + ", payType=" + payType
				+ ", payMoney=" + payMoney + ", operTime=" + operTime
				+ ", operIp=" + operIp + ", operNote=" + operNote
				+ ", operDate=" + operDate + ", yearMonth=" + yearMonth + "]";
	}
	

	
}
