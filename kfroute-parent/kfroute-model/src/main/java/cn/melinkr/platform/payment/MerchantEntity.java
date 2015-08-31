/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：MerchantEntity.java <br>
 * 创建时间：2015-5-29-下午04:51:12 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.melinkr.platform.payment;

public class MerchantEntity {
	private Integer id;
	private String merchantCode;
	private String merchantName;
	private String merchantAddr;
	private String linkInfo;
	private Integer status;
	private String manage;
	private String latnId;
	private String provinceId;
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
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getMerchantAddr() {
		return merchantAddr;
	}
	public void setMerchantAddr(String merchantAddr) {
		this.merchantAddr = merchantAddr;
	}
	public String getLinkInfo() {
		return linkInfo;
	}
	public void setLinkInfo(String linkInfo) {
		this.linkInfo = linkInfo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getManage() {
		return manage;
	}
	public void setManage(String manage) {
		this.manage = manage;
	}
	public String getLatnId() {
		return latnId;
	}
	public void setLatnId(String latnId) {
		this.latnId = latnId;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	@Override
	public String toString() {
		return "MerchantEntity [id=" + id + ", merchantCode=" + merchantCode
				+ ", merchantName=" + merchantName + ", merchantAddr="
				+ merchantAddr + ", linkInfo=" + linkInfo + ", status="
				+ status + ", manage=" + manage + ", latnId=" + latnId
				+ ", provinceId=" + provinceId + "]";
	}
	
	
	
}
