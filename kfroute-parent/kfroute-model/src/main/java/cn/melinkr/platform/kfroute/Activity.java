package cn.melinkr.platform.kfroute;

public class Activity {
	private Integer id;
	private String merchantCode;
	private Integer discount;
	private Integer isReduce;
	private Double reduceTotal;
	private Integer reduceType;
	private String reduceStr;
	private Double reduceAmount;
	private String activityDesc;
	private String picUrl;
	
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
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Integer getIsReduce() {
		return isReduce;
	}
	public void setIsReduce(Integer isReduce) {
		this.isReduce = isReduce;
	}

	public Integer getReduceType() {
		return reduceType;
	}
	public void setReduceType(Integer reduceType) {
		this.reduceType = reduceType;
	}
	
	public Double getReduceTotal() {
		return reduceTotal;
	}
	public void setReduceTotal(Double reduceTotal) {
		this.reduceTotal = reduceTotal;
	}
	public Double getReduceAmount() {
		return reduceAmount;
	}
	public void setReduceAmount(Double reduceAmount) {
		this.reduceAmount = reduceAmount;
	}
	public String getActivityDesc() {
		return activityDesc;
	}
	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}
	public String getReduceStr() {
		return reduceStr;
	}
	public void setReduceStr(String reduceStr) {
		this.reduceStr = reduceStr;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	@Override
	public String toString() {
		return "Activity [id=" + id + ", merchantCode=" + merchantCode
				+ ", discount=" + discount + ", isReduce=" + isReduce
				+ ", reduceTotal=" + reduceTotal + ", reduceType=" + reduceType
				+ ", reduceStr=" + reduceStr + ", reduceAmount=" + reduceAmount
				+ ", activityDesc=" + activityDesc + ", picUrl=" + picUrl + "]";
	}
	
	
	
	

	
	
	
}
