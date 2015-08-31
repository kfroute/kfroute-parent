package cn.melinkr.platform.payment;

public class PaymentEntity {
	private Integer id;
	private String paymentCode;
	private String paymentName;
	private String paymentUrl;
	private String paymentDesc;
	private String terminalType;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	public String getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
	
	@Override
	public String toString() {
		return "PaymentEntity [id=" + id + ", paymentCode=" + paymentCode
				+ ", paymentName=" + paymentName + ", paymentUrl=" + paymentUrl
				+ ", paymentDesc=" + paymentDesc + ", terminalType="
				+ terminalType + "]";
	}
	
}
