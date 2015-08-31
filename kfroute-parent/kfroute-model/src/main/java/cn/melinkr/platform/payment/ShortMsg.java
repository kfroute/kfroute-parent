/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：ShortMsgMould.java <br>
 * 创建时间：2015-5-27-下午02:25:34 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.melinkr.platform.payment;

import java.util.Date;

public class ShortMsg {
	private Integer id;
	private String merchantCode;
	private String msgName;
	private String shortMsg;
	private Date creatDate;
	private Date updateDate;
	private Integer status;
	
	
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
	public String getMsgName() {
		return msgName;
	}
	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}
	public String getShortMsg() {
		return shortMsg;
	}
	public void setShortMsg(String shortMsg) {
		this.shortMsg = shortMsg;
	}
	public Date getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "ShortMsg [id=" + id + ", merchantCode=" + merchantCode
				+ ", msgName=" + msgName + ", shortMsg=" + shortMsg
				+ ", creatDate=" + creatDate + ", updateDate=" + updateDate
				+ ", status=" + status + "]";
	}
	
	
	
}
