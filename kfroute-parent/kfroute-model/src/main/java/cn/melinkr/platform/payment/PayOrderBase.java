package cn.melinkr.platform.payment;

import java.util.Date;

public class PayOrderBase {

	private Integer id;
	private String orderNumber;
	private Double totalPrice;
	private Double orderPrice;
	private String bankCode;
	private String bankName;
	private String channel;
	private Integer orderType;
	private String payment;
	private Integer status;
	private Date orderDate;
	private String orderRemark;
	private String userId;
	private String userName;
	private String userType;
	private String openId;
	private String latnId;
	private String merchantCode;
	private String merchantName;
	private String areaId;
	private String couponCode;
	private String couponType;
	private Double couponAmount;
	private String recommend;
	private String callBackUrl;
	private String backStatus;
	private String platformOrderNumber;
	private boolean isExist;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getLatnId() {
		return latnId;
	}
	public void setLatnId(String latnId) {
		this.latnId = latnId;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	
	public Double getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(Double couponAmount) {
		this.couponAmount = couponAmount;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public String getCallBackUrl() {
		return callBackUrl;
	}
	public boolean isExist() {
		return isExist;
	}
	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}
	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}
	public String getBackStatus() {
		return backStatus;
	}
	public void setBackStatus(String backStatus) {
		this.backStatus = backStatus;
	}
	public String getPlatformOrderNumber() {
		return platformOrderNumber;
	}
	public void setPlatformOrderNumber(String platformOrderNumber) {
		this.platformOrderNumber = platformOrderNumber;
	}
	
	@Override
	public String toString() {
		return "PayOrderBase [id=" + id + ", orderNumber=" + orderNumber
				+ ", totalPrice=" + totalPrice + ", orderPrice=" + orderPrice
				+ ", bankCode=" + bankCode + ", bankName=" + bankName
				+ ", channel=" + channel + ", orderType=" + orderType
				+ ", payment=" + payment + ", status=" + status
				+ ", orderDate=" + orderDate + ", orderRemark=" + orderRemark
				+ ", userId=" + userId + ", userName=" + userName
				+ ", userType=" + userType + ", openId=" + openId + ", latnId="
				+ latnId + ", merchantCode=" + merchantCode + ", merchantName="
				+ merchantName + ", areaId=" + areaId + ", couponCode="
				+ couponCode + ", couponType=" + couponType + ", couponAmount="
				+ couponAmount + ", recommend=" + recommend + ", callBackUrl="
				+ callBackUrl + ", backStatus=" + backStatus
				+ ", platformOrderNumber=" + platformOrderNumber + ", isExist="
				+ isExist + "]";
	}
	
	
	
}
