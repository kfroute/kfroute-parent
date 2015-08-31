/* 
 * Copyrights 尚果科技支付平台研发项目组 @ 2015， 尚果科技支付 SEOCOO Computer Co., Ltd.<br>
 * 项目名称： 尚果科技支付平台研发项目组 <br>
 * 文件名称：PaymentAction.java <br>
 * 创建时间：2015-5-19-下午18:49:14 <br>
 * 创建用户：永生<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.melinkr.platform.payment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.melinkr.platform.kfroute.OrderBase;


public class Payment {

	/**
	 * 在线支付 1 在线支付 2 贷到付款 3 分期付款 6 用户自提
	 */
	public static final String PAYMENT_MODE_ONLINE = "1";
	/**
	 * 
	 * 货到付款
	 */
	public static final String PAYMENT_MODE_CASH = "2";
	/**
	 * 分期付款
	 */
	public static final String PAYMENT_MODE_INSTALLMENT = "3";
	/**
	 * 用户自提
	 */
	public static final String PAYMENT_MODE_MENTION = "4";

	/**
	 * 商户号
	 */
	private String merchantCode;
	/**
	 * 商户秘钥
	 */
	private String merchantKey;

	/**
	 * 银行编码
	 */
	private String bankCode;
	/**
	 * 需要支付的订单编码
	 */
	private String orderNumber;

	/**
	 * 订单提交日期
	 */
	private Date orderDate;
	/**
	 * 总金额用于支付的（分）
	 */
	private Integer totalPrice;
	/**
	 * 展示给用户的单位（元）
	 */
	private String showTotalPrice;

	/**
	 * 分期付期数1、2、3、4、5
	 */
	private Integer installmentTimes;
	/**
	 * 支付方式
	 */
	private String paymentMode;
	/**
	 * 支付支付平台
	 */
	private String paymentPlatform;

	/**
	 * 业务类型
	 */
	private String busiCode;
	/**
	 * 交易流水号
	 */
	private String tranSeq;

	private String clientIp;
	
	private Date payTime;
	
	private OrderBase orderBase;
	
	private String openId;
	
	private String platformOrderNumber;
	
	private String paramUrl;
	
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getInstallmentTimes() {
		return installmentTimes;
	}

	public void setInstallmentTimes(Integer installmentTimes) {
		this.installmentTimes = installmentTimes;
	}

	public String getPaymentPlatform() {
		return paymentPlatform;
	}

	public void setPaymentPlatform(String paymentPlatform) {
		this.paymentPlatform = paymentPlatform;
	}

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getShowTotalPrice() {
		return showTotalPrice;
	}

	public void setShowTotalPrice(String showTotalPrice) {
		this.showTotalPrice = showTotalPrice;
	}
	
	public String getParamUrl() {
		return paramUrl;
	}

	public void setParamUrl(String paramUrl) {
		this.paramUrl = paramUrl;
	}

	public String getMerchantKey() {
		return merchantKey;
	}

	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}

	public String getTranSeq() {
		return tranSeq;
	}

	public void setTranSeq(String tranSeq) {
		this.tranSeq = tranSeq;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public OrderBase getOrderBase() {
		return orderBase;
	}

	public void setOrderBase(OrderBase orderBase) {
		this.orderBase = orderBase;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPlatformOrderNumber() {
		return platformOrderNumber;
	}

	public void setPlatformOrderNumber(String platformOrderNumber) {
		this.platformOrderNumber = platformOrderNumber;
	}

	@Override
	public String toString() {
		return "Payment [merchantCode=" + merchantCode + ", merchantKey="
				+ merchantKey + ", bankCode=" + bankCode + ", orderNumber="
				+ orderNumber + ", orderDate=" + orderDate + ", totalPrice="
				+ totalPrice + ", showTotalPrice=" + showTotalPrice
				+ ", installmentTimes=" + installmentTimes + ", paymentMode="
				+ paymentMode + ", paymentPlatform=" + paymentPlatform
				+ ", busiCode=" + busiCode + ", tranSeq=" + tranSeq
				+ ", clientIp=" + clientIp + ", payTime=" + payTime
				+ ", orderBase=" + orderBase + ", openId=" + openId + "]";
	}
	
	

}
