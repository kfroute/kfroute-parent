/* 
 * Copyrights 尚果科技支付平台研发项目组 @ 2015， 尚果科技支付 SEOCOO Computer Co., Ltd.<br>
 * 项目名称： 尚果科技支付平台研发项目组 <br>
 * 文件名称：PaymentAction.java <br>
 * 创建时间：2015-5-19-下午18:49:14 <br>
 * 创建用户：永生<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
  
package cn.melinkr.platform.payment;

import java.util.Map;


public class PaymentResult {
    /**
     * 支付成功
     */
    public final static String PAY_SUCCESS="SUCCESS";
    /**
     * 支付失败
     */
    public final static String PAY_FAIL="FAIL";
    
    private String uptranseq;
    /**
     * 商户编码
     */
    private String merchantCode;
    /**
     * 商户秘钥
     */
    private String merchantKey;
    /**
     * 数据库商户信息
     */
    private Merchant merchant;
    /**
     * 交易时间
     */
    private String tranDate;
    /**
     * 返回的支付情况编码
     */
    private String retnCode;
    /**
     * 返回的支付情况信息
     */
    private String retnInfo;
    /**
     * 交易流水号
     */
    private String tranSeq;
    /**
     * 订单流水号
     */
    private String orderNumber;
    /**
     * 支付总额
     */
    private String totalPrice;
    /**
     * 消息摘要
     */
    private String sign;
    /**
     * 响应字符串
     */
    private String responseMsg;
    /**
     * 响应字符串
     */
    private String responseCode;
    /**
     * 原字符
     */
    private String jsonReqMsg;
    
    /**
     * 回调相应字符串
     */
    private Map<String, String> reqParams;
    
    
    /**
     * 商户标识
     */
    private String merID;
    /**
     * 订单标识(原)
     */
    private String orderID;
    /**
     * 交易日期时间, YYYYMMDDHHmmSS
     */
    private String orderTime;
    /**
     *止付结果，标识是否成功或失败的原因
     */
    private String rspCode;
    /**
     * 对于止付结果的描述信息。
     */
    private String rspDesc;

    //1基金支付，0正常支付
    private String isFund="0";
    
    // 订单系统冻结金额
    private String freezeAmt;
    
    //1页面回调 0服务器回调
    private String isPageCall="0";
    //原始请求流水号
    private String oriTransID;
    //冻结金额
    private String freezeAmount;
    //冻结到期日
    private String freezeExpire;
    //冻结时间
    private String freezeDateTime;
    //冻结订单号
    private String freezeOrderID ;
    
    //余额宝购机参数start--------------
    //1余额宝，0正常支付
    private String isAli="0";
    //支付宝授权订单号
    private String auth_no;
    //支付宝资金操作流水号
    private String operation_id;
    //商户本次资金操作的请求流水号
    private String out_request_no;
    //冻结金额
    private String amount;
    //操作流水状态 INIT：初始PROCESSING：处理中 SUCCESS：成功FAIL：失败 CLOSED：关闭
    private String ali_status;
    //操作时间
    private String gmt_create;
    //收款方支付宝账号
    private String payee_user_id;
    //付款方支付宝账号
    private String payer_logon_id;
    //付款方支付宝账号
    private String payer_user_id;
    
    //余额宝购机参数end----------------------
    
    
    /**
     * 支付是否成功
     * @return true成功，false失败
     * @see [类、类#方法、类#成员]
     */
    public boolean isSuccess(){
        return PAY_SUCCESS.equals(this.retnCode);
    }
    public boolean isSspSuccess(){
        return "000000".equals(this.rspCode);
    }
    public String getUptranseq() {
        return uptranseq;
    }
    public void setUptranseq(String uptranseq) {
        this.uptranseq = uptranseq;
    }
    public String getTranDate() {
        return tranDate;
    }
    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }
    public String getRetnCode() {
        return retnCode;
    }
    public void setRetnCode(String retnCode) {
        this.retnCode = retnCode;
    }
    public String getRetnInfo() {
        return retnInfo;
    }
    public void setRetnInfo(String retnInfo) {
        this.retnInfo = retnInfo;
    }
    public String getTranSeq() {
        return tranSeq;
    }
    public void setTranSeq(String tranSeq) {
        this.tranSeq = tranSeq;
    }
 
    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public String getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getMerchantCode() {
        return merchantCode;
    }
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }
    public String getResponseMsg() {
    	
    	//构建返回报文
    	StringBuffer sb=new StringBuffer();
    	sb.append("<xml>");
    	sb.append("<return_code>"+rspCode+"</return_code>");
    	sb.append("<return_msg>"+rspDesc+"</return_msg>");
    	sb.append("</xml>");
    	
        return sb.toString();
    }
    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
    public String getMerchantKey() {
        return merchantKey;
    }
    public void setMerchantKey(String merchantKey) {
        this.merchantKey = merchantKey;
    }

    public Map<String, String> getReqParams() {
        return reqParams;
    }
    public void setReqParams(Map<String, String> reqParams) {
        this.reqParams = reqParams;
    }
    public String getJsonReqMsg() {
        return jsonReqMsg;
    }
    public void setJsonReqMsg(String jsonReqMsg) {
        this.jsonReqMsg = jsonReqMsg;
    }
    public Merchant getMerchant() {
        return merchant;
    }
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
    
    public String getMerID() {
		return merID;
	}
	public void setMerID(String merID) {
		this.merID = merID;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getRspCode() {
		return rspCode;
	}
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}
	public String getRspDesc() {
		return rspDesc;
	}
	public void setRspDesc(String rspDesc) {
		this.rspDesc = rspDesc;
	}
	
	public String getIsPageCall() {
		return isPageCall;
	}
	public void setIsPageCall(String isPageCall) {
		this.isPageCall = isPageCall;
	}
	public String getIsFund() {
		return isFund;
	}
	public void setIsFund(String isFund) {
		this.isFund = isFund;
	}
	
	public String getOriTransID() {
		return oriTransID;
	}
	public void setOriTransID(String oriTransID) {
		this.oriTransID = oriTransID;
	}
	public String getFreezeAmount() {
		return freezeAmount;
	}
	public void setFreezeAmount(String freezeAmount) {
		this.freezeAmount = freezeAmount;
	}
	public String getFreezeExpire() {
		return freezeExpire;
	}
	public void setFreezeExpire(String freezeExpire) {
		this.freezeExpire = freezeExpire;
	}
	public String getFreezeDateTime() {
		return freezeDateTime;
	}
	public void setFreezeDateTime(String freezeDateTime) {
		this.freezeDateTime = freezeDateTime;
	}
	public String getFreezeOrderID() {
		return freezeOrderID;
	}
	public void setFreezeOrderID(String freezeOrderID) {
		this.freezeOrderID = freezeOrderID;
	}
	
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
	public String getFreezeAmt() {
		return freezeAmt;
	}
	public void setFreezeAmt(String freezeAmt) {
		this.freezeAmt = freezeAmt;
	}
	
	public String getIsAli() {
		return isAli;
	}
	public void setIsAli(String isAli) {
		this.isAli = isAli;
	}
	public String getAuth_no() {
		return auth_no;
	}
	public void setAuth_no(String auth_no) {
		this.auth_no = auth_no;
	}
	public String getOperation_id() {
		return operation_id;
	}
	public void setOperation_id(String operation_id) {
		this.operation_id = operation_id;
	}
	public String getOut_request_no() {
		return out_request_no;
	}
	public void setOut_request_no(String out_request_no) {
		this.out_request_no = out_request_no;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getAli_status() {
		return ali_status;
	}
	public void setAli_status(String ali_status) {
		this.ali_status = ali_status;
	}
	
	public String getGmt_create() {
		return gmt_create;
	}
	public void setGmt_create(String gmt_create) {
		this.gmt_create = gmt_create;
	}
	public String getPayee_user_id() {
		return payee_user_id;
	}
	public void setPayee_user_id(String payee_user_id) {
		this.payee_user_id = payee_user_id;
	}
	public String getPayer_logon_id() {
		return payer_logon_id;
	}
	public void setPayer_logon_id(String payer_logon_id) {
		this.payer_logon_id = payer_logon_id;
	}
	public String getPayer_user_id() {
		return payer_user_id;
	}
	public void setPayer_user_id(String payer_user_id) {
		this.payer_user_id = payer_user_id;
	}
	public String getDebugInfo(){
        StringBuilder builder=new StringBuilder();
        builder.append("订单：");
        builder.append(this.orderNumber);
        builder.append(";支付金额：");
        builder.append(this.totalPrice);
       return builder.toString();
    }
    
}
