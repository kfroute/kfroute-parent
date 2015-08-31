package cn.melinkr.platform.unite;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author: zhangyl
 * @time: 2015-07-06 23:15
 * @version: 1.0
 * 接口请求报文头
 * ServiceCode：接口名称
 * SrcSysID：系统识别id
 * transactionID:数据验证id,取时间戳
 * srcSysSign：校验值，计算规则为：srcSysSign+transactionID+srcSysSign+密钥的MD5值
 *
 */
public class MsgHead {

	@JSONField(name="ServiceCode")
	private String serviceCode;
	@JSONField(name="SrcSysID")
	private String srcSysID;
	@JSONField(name="transactionID")
	private String transactionID;
	@JSONField(name="SrcSysSign")
	private String srcSysSign;
	
	public MsgHead() {
	}
	public MsgHead(String serviceCode, String srcSysID, String srcSysPassWord,
			String srcSysSign, String transactionID) {
		super();
		this.serviceCode = serviceCode;
		this.srcSysID = srcSysID;
		this.srcSysSign = srcSysSign;
		this.transactionID = transactionID;
	}
	
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getSrcSysID() {
		return srcSysID;
	}
	public void setSrcSysID(String srcSysID) {
		this.srcSysID = srcSysID;
	}
	public String getSrcSysSign() {
		return srcSysSign;
	}
	public void setSrcSysSign(String srcSysSign) {
		this.srcSysSign = srcSysSign;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	
}
