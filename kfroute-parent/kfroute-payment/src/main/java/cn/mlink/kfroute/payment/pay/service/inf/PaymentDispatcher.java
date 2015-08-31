package cn.kfroute.platform.payment.pay.service.inf;

/* 
 * Copyrights 药博商城微信公众平台研发项目组 @ 2012，药博 WZT Computer Co., Ltd.<br>
 * 项目名称：药博商城微信公众平台 <br>
 * 文件名称：PaymentDispatcher.java <br>
 * 创建时间：2015-4-2-下午09:27:30 <br>
 * 创建用户：永生<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.seocoo.platform.payment.Merchant;
import cn.seocoo.platform.payment.Payment;
import cn.seocoo.platform.payment.PaymentResult;


public interface PaymentDispatcher {

	/**
	 * 支付分发
	 * @param payment
	 * @param merchant
	 * @return
	 */
	   public String payDispatcher(Payment payment, Merchant merchant);

	/** 支付后台通知
	 * @param map
	 * @return 
	 */
	public PaymentResult callbackDispatcher(Map<String, Object> map);
	
	public void writeBackDispatcher(HttpServletResponse response,Map<String, Object> map,PaymentResult paymentResult);
	
	public void callInitiator(Map<String, Object> map);
	
	public String callBackPageDispatcher(HttpServletRequest request);
}
