package cn.melinkr.platform.payment;

public class PayReslut {

	  private String returnCode;
	  private Object data;
	  private String type;
	  private String url;
	  private boolean orderPaid;
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isOrderPaid() {
		return orderPaid;
	}
	public void setOrderPaid(boolean orderPaid) {
		this.orderPaid = orderPaid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	  
	  
	  
}
