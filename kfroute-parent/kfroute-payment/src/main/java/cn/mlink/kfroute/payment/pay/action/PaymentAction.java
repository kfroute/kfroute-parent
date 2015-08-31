/* 
 * Copyrights 尚果科技支付平台研发项目组 @ 2015， 尚果科技支付 SEOCOO Computer Co., Ltd.<br>
 * 项目名称： 尚果科技支付平台研发项目组 <br>
 * 文件名称：PaymentAction.java <br>
 * 创建时间：2015-5-19-下午18:45:14 <br>
 * 创建用户：永生<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.pay.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cn.kfroute.platform.payment.pay.service.inf.PaymentDispatcher;
import cn.kfroute.platform.payment.server.service.inf.PayGeneralService;
import cn.kfroute.platform.payment.server.service.inf.PayLogService;
import cn.kfroute.platform.payment.server.service.inf.PayOrderService;
import cn.seocoo.platform.payment.Merchant;
import cn.seocoo.platform.payment.Payment;
import cn.seocoo.platform.payment.PaymentResult;

import com.odchina.micro.alipay.util.PayUtils;
import com.tydic.framework.base.web.BaseAction;

public class PaymentAction extends BaseAction{
	private PaymentDispatcher paymentDispatcher ;
	private String jumpScript;
	private  Payment payment;
	private PayLogService payLogService;
	private PayOrderService payOrderService;
	private PayGeneralService payGeneralService;
	private static final Logger logger = Logger.getLogger(PaymentAction.class);
	/**
	 * 支付
	 * @return
	 */
	public String topay(){
		
		String url = request.getScheme() + "://";
		url += request.getHeader("host");
		url += request.getRequestURI();
		if (request.getQueryString() != null)
		url += "?" + request.getQueryString();
		payment.setParamUrl(url);
		System.out.println(payment.toString());
		//构建支付元素
		Merchant merchant=new Merchant();
		jumpScript=paymentDispatcher.payDispatcher(payment, merchant);
		return "toPay";
	}
	
	/**
	 * 后台回调
	 * @throws IOException 
	 */
	public void callback() throws IOException{
	
		String url = request.getScheme() + "://";
		url += request.getHeader("host");
		url += request.getRequestURI();
		if (request.getQueryString() != null)
		url += "?" + request.getQueryString();
		logger.info("======================页面回调参数callback====================info：" + url);
		logger.debug("======================页面回调参数callback===================debug：" + url);
		logger.error("======================页面回调参数callback====================error：" + url);
		System.out.println("======================页面回调参数callback====================out：" + url);
		Map map = PayUtils.getParameterMap(request);
		map.put("backParams", request.getParameterMap());
//		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
//	    while (it.hasNext()) {
//		    Map.Entry<String, String> entry = it.next();
//		    logger.info("***************************************"+"key= " + entry.getKey() + "******************************* and value= " + entry.getValue()+"***************************************");
//	    }
		PaymentResult paymentResult = paymentDispatcher.callbackDispatcher(map);
		
		/**
		 * 1.会写支付平台状态
		 * 2.调用发起方接口
		 */
		//会写支付平台状态
		paymentDispatcher.writeBackDispatcher(response,map,paymentResult);
		//writeBack(paymentResult);
		//调用发起方 1.查找orderbase 找到发起方回调路径 2.组装参数调用接口 3.记录返回结果
		//callInitiator(map);
		paymentDispatcher.callInitiator(map);
		
	}
	
	public String callBackPage(){
		String url = request.getScheme() + "://";
		url += request.getHeader("host");
		url += request.getRequestURI();
		if (request.getQueryString() != null)
		url += "?" + request.getQueryString();
		logger.info("======================页面回调参数Param====================info：" + url);
		logger.debug("======================页面回调参数Param===================debug：" + url);
		logger.error("======================页面回调参数Param====================error：" + url);
		System.out.println("======================页面回调参数Param====================out：" + url);
		Map map = PayUtils.getParameterMap(request);		
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
	    while (it.hasNext()) {
		    Map.Entry<String, String> entry = it.next();
		    logger.info("***************************************"+"key= " + entry.getKey() + "******************************* and value= " + entry.getValue()+"***************************************");
	    }	    
	    
	    String res = paymentDispatcher.callBackPageDispatcher(request);
	    return res;
	}
	
	
	/**
	 * 检查订单是否支付成功
	 * @throws IOException 
	 */
	public void checkPayResult() throws IOException{
		
	}
	/**
	 * 支付成功跳转
	 */
	public String paySuccess(){
		
		return "paySuccess";
	}
	
	/**
	 * 反馈支付平台
	 * @param backPayResult
	 * @throws IOException
	 */
	public void writeBack(PaymentResult backPayResult) throws IOException{
		  response.setContentType("text/html; charset=GBK");
          // // 给支付平台反馈相应消息
          PrintWriter printWriter = response.getWriter();//
          printWriter.write(backPayResult.getResponseMsg());
          printWriter.flush();
          printWriter.close();
          logger.info("ACK_RESPONSE_UPTRANSEQ_" + backPayResult.getOrderNumber() + "_" + backPayResult.getResponseMsg());
	}
	
	
	/**
	 * 读取request数据
	 * @param request
	 * @return
	 */
	public String readRequestMsg(HttpServletRequest request){
		java.io.BufferedReader bis = null;
		String line = null;
		String result = "";
		try {
			bis = new java.io.BufferedReader(new java.io.InputStreamReader(
					request.getInputStream()));
			while ((line = bis.readLine()) != null) {
				result += line + "\r\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public PaymentDispatcher getPaymentDispatcher() {
		return paymentDispatcher;
	}
	public void setPaymentDispatcher(PaymentDispatcher paymentDispatcher) {
		this.paymentDispatcher = paymentDispatcher;
	}
	public String getJumpScript() {
		return jumpScript;
	}
	public void setJumpScript(String jumpScript) {
		this.jumpScript = jumpScript;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public PayGeneralService getPayGeneralService() {
		return payGeneralService;
	}

	public void setPayGeneralService(PayGeneralService payGeneralService) {
		this.payGeneralService = payGeneralService;
	}

	public PayLogService getPayLogService() {
		return payLogService;
	}


	public PayOrderService getPayOrderService() {
		return payOrderService;
	}

	public void setPayOrderService(PayOrderService payOrderService) {
		this.payOrderService = payOrderService;
	}

	public void setPayLogService(PayLogService payLogService) {
		this.payLogService = payLogService;
	}
	
}
