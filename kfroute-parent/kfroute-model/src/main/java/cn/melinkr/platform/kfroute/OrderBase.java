package cn.melinkr.platform.kfroute;


import java.io.Serializable;
import java.util.Date;

/**订单实体类*/
public class OrderBase  implements Serializable{
	private Integer id;
	private String orderNumber;
	private String merchantCode;
	private String merchantName;
	private String source;
	private String boxMac;
	private String phoneMac;
	private String tableName;
	private Date orderDate;
	private Integer orderSum;
	private Double price;
	private String mark;
	private Integer payType;
	private Integer status;
	
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getBoxMac() {
		return boxMac;
	}
	public void setBoxMac(String boxMac) {
		this.boxMac = boxMac;
	}
	public String getPhoneMac() {
		return phoneMac;
	}
	public void setPhoneMac(String phoneMac) {
		this.phoneMac = phoneMac;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Integer getOrderSum() {
		return orderSum;
	}
	public void setOrderSum(Integer orderSum) {
		this.orderSum = orderSum;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
	
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "OrderBase [id=" + id + ", orderNumber=" + orderNumber
				+ ", merchantCode=" + merchantCode + ", merchantName="
				+ merchantName + ", source=" + source + ", boxMac=" + boxMac
				+ ", phoneMac=" + phoneMac + ", tableName=" + tableName
				+ ", orderDate=" + orderDate + ", orderSum=" + orderSum
				+ ", price=" + price + ", mark=" + mark + ", payType="
				+ payType + ", status=" + status + "]";
	}
	
	
	
	
}
