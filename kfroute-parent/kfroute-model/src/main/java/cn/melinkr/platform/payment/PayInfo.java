package cn.melinkr.platform.payment;

import java.util.Date;

public class PayInfo {

	private String orderNumber;
	private String transSeq;
	private String retnISeq;
	private Double totalPrice;
	private String merchantCode;
	private String payStatus;
	private Date paydate;
	private Date callbackDate;
	private String platformOrderNumber;
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getTransSeq() {
		return transSeq;
	}
	public void setTransSeq(String transSeq) {
		this.transSeq = transSeq;
	}
	public String getRetnISeq() {
		return retnISeq;
	}
	public void setRetnISeq(String retnISeq) {
		this.retnISeq = retnISeq;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public Date getPaydate() {
		return paydate;
	}
	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}
	public Date getCallbackDate() {
		return callbackDate;
	}
	public void setCallbackDate(Date callbackDate) {
		this.callbackDate = callbackDate;
	}
	public String getPlatformOrderNumber() {
		return platformOrderNumber;
	}
	public void setPlatformOrderNumber(String platformOrderNumber) {
		this.platformOrderNumber = platformOrderNumber;
	}
	
}
