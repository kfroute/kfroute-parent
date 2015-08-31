package cn.melinkr.platform.payment.alipay;

public class PayWapRequest {

	private String reqData;
	private String service;
	private String secId;
	private String partner;
	private String reqId;
	private String sign;
	private String format;
	private String payExpire;
	private String v;
	
	public String getReqData() {
		return reqData;
	}
	public void setReqData(String reqData) {
		this.reqData = reqData;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getSecId() {
		return secId;
	}
	public void setSecId(String secId) {
		this.secId = secId;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	public String getPayExpire() {
		return payExpire;
	}
	public void setPayExpire(String payExpire) {
		this.payExpire = payExpire;
	}
	
	
}
