package cn.melinkr.platform.kfroute;

import java.io.Serializable;

/**订单详情实体类*/
/**
 * @author renyang
 *
 */
public class OrderItem implements Serializable{
	private Integer id;
	private String orderNumber;/**订单号*/
	private String goodsName;/**菜名*/
	private String goodsType;
	private Integer num;
	private Double price;
	private String merchantCode;
	private String goodsCode;
	
	
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderNumber=" + orderNumber
				+ ", goodsName=" + goodsName + ", goodsType=" + goodsType
				+ ", num=" + num + ", price=" + price + ", merchantCode="
				+ merchantCode + ", goodsCode=" + goodsCode + "]";
	}
	
	
	
	
}
