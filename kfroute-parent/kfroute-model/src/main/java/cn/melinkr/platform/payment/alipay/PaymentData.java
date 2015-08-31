/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：PaymentData.java <br>
 * 创建时间：2015-5-26-下午03:50:16 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.melinkr.platform.payment.alipay;

import java.util.List;

import cn.melinkr.platform.payment.PaymentEntity;

public class PaymentData {
	private String serviceType;
	private String orderNumber;
	private String platformOrderNumber;
	private String merchantName;
	private String merchantCode;
	private String totalPrice;
	private String callType;
	private String paymentName;
	private List<PaymentEntity> paymentList; 
	
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public List<PaymentEntity> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<PaymentEntity> paymentList) {
		this.paymentList = paymentList;
	}
	public String getPlatformOrderNumber() {
		return platformOrderNumber;
	}
	public void setPlatformOrderNumber(String platformOrderNumber) {
		this.platformOrderNumber = platformOrderNumber;
	}
	
}
