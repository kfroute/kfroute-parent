package cn.melinkr.platform.payment;

import java.util.Date;

public class PayLog {

	private String orderNumber;
	private String msg;
	private String payType;
	private Date createDate;
	private String url;
	private String platformOrderNumber;
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPlatformOrderNumber() {
		return platformOrderNumber;
	}
	public void setPlatformOrderNumber(String platformOrderNumber) {
		this.platformOrderNumber = platformOrderNumber;
	}
	
}
