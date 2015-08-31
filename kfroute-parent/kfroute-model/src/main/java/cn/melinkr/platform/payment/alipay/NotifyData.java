package cn.melinkr.platform.payment.alipay;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("notify")
public class NotifyData {

	@XStreamAlias("payment_type")
    @XStreamAsAttribute
	private String paymentType;
	
	@XStreamAlias("subject")
    @XStreamAsAttribute
	private String subject;
	
	@XStreamAlias("trade_no")
    @XStreamAsAttribute
	private String tradeNo;
	
	@XStreamAlias("buyer_email")
    @XStreamAsAttribute
	private String buyerEmail;
	
	@XStreamAlias("gmt_create")
    @XStreamAsAttribute
	private String gmtCreate;
	
	@XStreamAlias("notify_type")
    @XStreamAsAttribute
	private String notifyType;
	
	@XStreamAlias("quantity")
    @XStreamAsAttribute
	private String quantity;
	
	@XStreamAlias("out_trade_no")
    @XStreamAsAttribute
	private String outTradeNo;
	
	@XStreamAlias("notify_time")
    @XStreamAsAttribute
	private String notifyTime;
	
	@XStreamAlias("seller_id")
    @XStreamAsAttribute
	private String sellerId;
	
	@XStreamAlias("trade_status")
    @XStreamAsAttribute
	private String tradeStatus;
	
	@XStreamAlias("is_total_fee_adjust")
    @XStreamAsAttribute
	private String isTotalFeeAdjust;
	
	@XStreamAlias("total_fee")
    @XStreamAsAttribute
	private String totalFee;
	
	@XStreamAlias("gmt_payment")
    @XStreamAsAttribute
	private String gmtPayment;
	
	@XStreamAlias("seller_email")
    @XStreamAsAttribute
	private String sellerEmail;
	
	@XStreamAlias("gmt_close")
    @XStreamAsAttribute
	private String gmtClose;
	
	@XStreamAlias("price")
    @XStreamAsAttribute
	private String price;
	
	@XStreamAlias("buyer_id")
    @XStreamAsAttribute
	private String buyerId;
	
	@XStreamAlias("notify_id")
    @XStreamAsAttribute
	private String notifyId;
	
	@XStreamAlias("use_coupon")
    @XStreamAsAttribute
	private String useCoupon;
	
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getBuyerEmail() {
		return buyerEmail;
	}
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	public String getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public String getNotifyType() {
		return notifyType;
	}
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getNotifyTime() {
		return notifyTime;
	}
	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public String getIsTotalFeeAdjust() {
		return isTotalFeeAdjust;
	}
	public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
		this.isTotalFeeAdjust = isTotalFeeAdjust;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public String getGmtPayment() {
		return gmtPayment;
	}
	public void setGmtPayment(String gmtPayment) {
		this.gmtPayment = gmtPayment;
	}
	public String getSellerEmail() {
		return sellerEmail;
	}
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}
	public String getGmtClose() {
		return gmtClose;
	}
	public void setGmtClose(String gmtClose) {
		this.gmtClose = gmtClose;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getNotifyId() {
		return notifyId;
	}
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}
	public String getUseCoupon() {
		return useCoupon;
	}
	public void setUseCoupon(String useCoupon) {
		this.useCoupon = useCoupon;
	}
	
}
