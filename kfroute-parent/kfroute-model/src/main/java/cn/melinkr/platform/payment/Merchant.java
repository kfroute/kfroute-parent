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

 /**
 * 商户模型
 * <功能详细描述>
 * 
 * @author  yanjp
 * @version  [版本号, 2013-9-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */

public class Merchant {
    /**
     * 单个商户统一编码
     */
    private String merchantCode;
    
    /**
     * 每个商户的认证密钥
     */
    private String merchantKey;
    //设置请求IP
    private String requestIp;
    
    public Merchant(){
    }
    
    public Merchant(String merchantCode){
        this.merchantCode=merchantCode;
    }
    public String getMerchantCode() {
        return merchantCode;
    }
    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }
    public String getMerchantKey() {
        return merchantKey;
    }

    public void setMerchantKey(String merchantKey) {
        this.merchantKey = merchantKey;
    }
    

	public String getRequestIp() {
		if(requestIp!=null&&requestIp!=""&&requestIp.startsWith("134")){
			return "202.102.221.70";
		}
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}
}
