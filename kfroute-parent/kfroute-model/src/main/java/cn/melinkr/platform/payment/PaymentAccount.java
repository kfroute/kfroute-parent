package cn.melinkr.platform.payment;

public class PaymentAccount {
	private Integer id;
	private String paymentAccount;
	private String relationAccount;
	private String bankCode;
	private String bankName;
	private String security;
	private String bankUrl;
	private String banktDesc;
	private String merchantCode;
	private String merchantName;
	private Integer status;
	private String paymentType;
	private Double fee;
	private Double reduceFee;
	private Integer counterFee;
	private String notifyUrl;
	private String callBackUrl;
	private String merchantUrl;
	private String returnUrl;
	private String errorNotifyUrl;
	private String payPartner;
	private String payPaternerKey;
	private String appkey;
	private String appId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPaymentAccount() {
		return paymentAccount;
	}
	public void setPaymentAccount(String paymentAccount) {
		this.paymentAccount = paymentAccount;
	}
	public String getRelationAccount() {
		return relationAccount;
	}
	public void setRelationAccount(String relationAccount) {
		this.relationAccount = relationAccount;
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
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public String getBankUrl() {
		return bankUrl;
	}
	public void setBankUrl(String bankUrl) {
		this.bankUrl = bankUrl;
	}
	public String getBanktDesc() {
		return banktDesc;
	}
	public void setBanktDesc(String banktDesc) {
		this.banktDesc = banktDesc;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Double getReduceFee() {
		return reduceFee;
	}
	public void setReduceFee(Double reduceFee) {
		this.reduceFee = reduceFee;
	}
	public Integer getCounterFee() {
		return counterFee;
	}
	public void setCounterFee(Integer counterFee) {
		this.counterFee = counterFee;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getMerchantUrl() {
		return merchantUrl;
	}
	public void setMerchantUrl(String merchantUrl) {
		this.merchantUrl = merchantUrl;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getCallBackUrl() {
		return callBackUrl;
	}
	public void setCallBackUrl(String callBackUrl) {
		this.callBackUrl = callBackUrl;
	}
	
	
	public String getErrorNotifyUrl() {
		return errorNotifyUrl;
	}
	public void setErrorNotifyUrl(String errorNotifyUrl) {
		this.errorNotifyUrl = errorNotifyUrl;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public String getPayPartner() {
		return payPartner;
	}
	public void setPayPartner(String payPartner) {
		this.payPartner = payPartner;
	}
	public String getPayPaternerKey() {
		return payPaternerKey;
	}
	public void setPayPaternerKey(String payPaternerKey) {
		this.payPaternerKey = payPaternerKey;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	@Override
	public String toString() {
		return "PaymentAccount [id=" + id + ", paymentAccount="
				+ paymentAccount + ", relationAccount=" + relationAccount
				+ ", bankCode=" + bankCode + ", bankName=" + bankName
				+ ", security=" + security + ", bankUrl=" + bankUrl
				+ ", banktDesc=" + banktDesc + ", merchantCode=" + merchantCode
				+ ", merchantName=" + merchantName + ", status=" + status
				+ ", paymentType=" + paymentType + ", fee=" + fee
				+ ", reduceFee=" + reduceFee + ", counterFee=" + counterFee
				+ ", notifyUrl=" + notifyUrl + ", callBackUrl=" + callBackUrl
				+ ", merchantUrl=" + merchantUrl + ", returnUrl=" + returnUrl
				+ ", errorNotifyUrl=" + errorNotifyUrl + ", payPartner="
				+ payPartner + ", payPaternerKey=" + payPaternerKey
				+ ", appkey=" + appkey + ", appId=" + appId + "]";
	}
	
	
	
}
