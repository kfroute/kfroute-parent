/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：ResponseData.java <br>
 * 创建时间：2015-5-23-下午05:31:42 <br>
 * 创建用户：renyang<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.melinkr.platform.payment.alipay;

public class ResponseData {

	private String orderNumber;
	private String isRecieved;
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getIsRecieved() {
		return isRecieved;
	}
	public void setIsRecieved(String isRecieved) {
		this.isRecieved = isRecieved;
	}
	
	
}
