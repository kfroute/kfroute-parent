package cn.melinkr.platform.kfroute;

import java.util.List;

public class Order {

	private String merchantCode;
	private OrderBase orderBase;
	List<OrderItem> itemList;
	
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public OrderBase getOrderBase() {
		return orderBase;
	}
	public void setOrderBase(OrderBase orderBase) {
		this.orderBase = orderBase;
	}
	public List<OrderItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}
}
