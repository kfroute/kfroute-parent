/* 
*
* @author: zhangyl
* @time: 2015-07-07 14:20
* @version: 1.0
*
*/
package com.melinkr.micro.util;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.melinkr.platform.payment.PayOrderBase;

import com.tydic.wss.util.DateUtil;

public class PaymentUtil {
    /**
     * 支付统一后缀名
     */
    public static final String PAYMENT_SERVICE_SUFFIX = "PaymentService";
    
    /**
     * 微信支付
     */
    public static final String PAYMENT_PLATFORM_MICRO = "micro";
    
    /**
     * 支付宝支付
     */
    public static final String PAYMENT_PLATFORM_ALIPAY = "aliPay";
    
    
    /**
     * wap支付宝支付
     */
    public static final String PAYMENT_PLATFORM_ALIPAYWAP = "aliPayWap";
    
    
    /** 
     * 网银支付 
     */
    public static final String PAYMENT_PLATFORM_BANK = "bank";
    
    private static final Logger logger = Logger.getLogger(PaymentUtil.class);
    
    /**
     * 根据支付平台判断对应服务
     * @param platForm
     * @return
     * @see [类、类#方法、类#成员]
     */
    public final static String getPaymentService(String platForm) {
        String paymentServiceBeanId = null;
        //TODO:判断是否翼支付回调
        if (PAYMENT_PLATFORM_MICRO.equals(platForm)) {
            paymentServiceBeanId = PAYMENT_PLATFORM_MICRO + PAYMENT_SERVICE_SUFFIX;
        }
        
        if (PAYMENT_PLATFORM_ALIPAY.equals(platForm)) {
            paymentServiceBeanId = PAYMENT_PLATFORM_ALIPAY + PAYMENT_SERVICE_SUFFIX;
        }
        
        if(PAYMENT_PLATFORM_ALIPAYWAP.equals(platForm)){
        	paymentServiceBeanId = PAYMENT_PLATFORM_ALIPAYWAP + PAYMENT_SERVICE_SUFFIX;
        }
       
       
        return paymentServiceBeanId;
    }
    
    
    public final static String getPaymentService(Map<String, Object> param) {
        String paymentServiceBeanId = null;
        //TODO:判断是微信支付回调
        if (paymentServiceBeanId == null) {
            paymentServiceBeanId = isMicro(param);
        }
        
        if(paymentServiceBeanId == null){
        	paymentServiceBeanId = isAliWap(param);
        }
        
        if(paymentServiceBeanId == null){
        	paymentServiceBeanId = isAliWeb(param);
        }
        if (paymentServiceBeanId != null) {
            paymentServiceBeanId = paymentServiceBeanId + PAYMENT_SERVICE_SUFFIX;
        }
        return paymentServiceBeanId;
    }
    
    public static final String isAliWap(Map<String, Object> param){
    	logger.info("=========================alipay=========================");
    	if (param.containsKey("notify_data")&&param.containsKey("sign")) {
            return PAYMENT_PLATFORM_ALIPAYWAP;
        }
    	if(param.containsKey("out_trade_no")&&param.containsKey("request_token")){
    		return PAYMENT_PLATFORM_ALIPAYWAP;
    		
    	}
    	return null;
    }
    
    public static final String isAliWeb(Map<String, Object> param){
    	
    	if (param.containsKey("extra_common_param")&&PAYMENT_PLATFORM_ALIPAY.equals(param.get("extra_common_param"))) {
            return PAYMENT_PLATFORM_ALIPAY;
        }
    	return null;
    }
    /**
     * 微信支付
     * @param param
     * @return
     * @see [类、类#方法、类#成员]
     */
    public final static String isMicro(Map<String, Object> param) {
    	
        if (param.containsKey("platform")&&PAYMENT_PLATFORM_MICRO.equals(param.get("platform"))) {
            return PAYMENT_PLATFORM_MICRO;
        }
        return null;
    }
    
    /**
     * 生成支付流水号
     * @return
     */
	public static String creatReqSeq(){
		String appId=SystemConfigUtil.getSingleProperty("pay_order_channel").getPropertyValue();
		
		String seq=appId+DateUtil.fotmatDate14(new Date())+Integer.valueOf((int) ((Math.random()*9+1)*10000));
		
		return seq;
	} 
    
	   /**
     * 生成订单号
     * @return
     */
	public static String creatOrderNumber(){
		String appId=SystemConfigUtil.getSingleProperty("order_channel").getPropertyValue();
		
		String seq=appId+DateUtil.fotmatDate14(new Date())+Integer.valueOf((int) ((Math.random()*9+1)*10000));
		
		return seq;
	} 
}
