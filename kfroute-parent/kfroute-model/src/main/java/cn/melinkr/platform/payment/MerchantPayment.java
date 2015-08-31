package cn.melinkr.platform.payment;

public class MerchantPayment {
	private Integer id;
	private String merchantCode;
	private String merchantName;
	private String paymentCode;
	private String paymentName;
	private String paymentUrl;
	private String paymentDesc;
	private String paymentAccount;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	public String getPaymentUrl() {
		return paymentUrl;
	}
	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}
	public String getPaymentDesc() {
		return paymentDesc;
	}
	public void setPaymentDesc(String paymentDesc) {
		this.paymentDesc = paymentDesc;
	}
	public String getPaymentAccount() {
		return paymentAccount;
	}
	public void setPaymentAccount(String paymentAccount) {
		this.paymentAccount = paymentAccount;
	}
	
	@Override
	public String toString() {
		return "MerchantPayment [id=" + id + ", merchantCode=" + merchantCode
				+ ", merchantName=" + merchantName + ", paymentCode="
				+ paymentCode + ", paymentName=" + paymentName
				+ ", paymentUrl=" + paymentUrl + ", paymentDesc=" + paymentDesc
				+ ", paymentAccount=" + paymentAccount + "]";
	}
	
	
	
}
