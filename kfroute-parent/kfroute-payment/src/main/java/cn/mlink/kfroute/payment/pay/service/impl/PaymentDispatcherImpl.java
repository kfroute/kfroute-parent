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

package cn.kfroute.platform.payment.pay.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.kfroute.platform.payment.pay.service.inf.PaymentDispatcher;
import cn.kfroute.platform.payment.pay.service.inf.PaymentService;
import cn.seocoo.platform.payment.Merchant;
import cn.seocoo.platform.payment.Payment;
import cn.seocoo.platform.payment.PaymentResult;

import com.odchina.micro.alipay.util.PayUtils;
import com.odchina.micro.util.PaymentUtil;
import com.odchina.micro.util.SpringHelper;




 /**
 * 用于支付和回调服务分发。
 * @author  liys
 * @version  [版本号, 2015-4-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */

public class PaymentDispatcherImpl implements PaymentDispatcher{
	
	private static final Logger logger = Logger.getLogger(PaymentDispatcherImpl.class);
    
    public String payDispatcher(Payment payment,Merchant merchant) {
        // TODO Auto-generated method stub
        PaymentService paymentService=getPaymentService(payment.getPaymentPlatform());
        return paymentService.toPay(payment,merchant);
    }

    
    /**
     * 根据支付平台名称判断
     * 使用对应支付服务跳转
     * @param platForm
     * @return
     * @throws PaymentException
     * @see [类、类#方法、类#成员]
     */
    private PaymentService getPaymentService(String platForm){
        String paymentServiceBeanId=PaymentUtil.getPaymentService(platForm);
       return (PaymentService)SpringHelper.getSpringHelper().getBean(paymentServiceBeanId);
    }

    /**
     * 后台通知获取处理业务bean
     * 使用对应支付服务跳转
     * @param platForm
     * @return
     * @throws PaymentException
     * @see [类、类#方法、类#成员]
     */
    private PaymentService getPaymentService(Map<String, Object> map){
        String paymentServiceBeanId=PaymentUtil.getPaymentService(map);
       return (PaymentService)SpringHelper.getSpringHelper().getBean(paymentServiceBeanId);
    }

	/* 
	 * 后台通知支付回调
	 */
	@Override
	public PaymentResult callbackDispatcher(Map<String, Object> map) {
		// TODO Auto-generated method stub
		   PaymentService paymentService=getPaymentService(map);
	       
		   return paymentService.callBack(map);
	}


	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.pay.service.inf.PaymentDispatcher#writeBackDispatcher(java.util.Map, cn.seocoo.platform.payment.PaymentResult) 
	 */
	@Override
	public void writeBackDispatcher(HttpServletResponse response,Map<String, Object> map,
			PaymentResult paymentResult) {
		PaymentService paymentService=getPaymentService(map);
		paymentService.writeback(response,paymentResult);
		
	}


	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.pay.service.inf.PaymentDispatcher#callInitiator(javax.servlet.http.HttpServletResponse, java.util.Map) 
	 */
	@Override
	public void callInitiator(Map<String, Object> map) {
		PaymentService paymentService=getPaymentService(map);
		paymentService.callInitiator(map);
		
	}


	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.pay.service.inf.PaymentDispatcher#callBackPageDispatcher(java.util.Map) 
	 */
	@Override
	public String callBackPageDispatcher(HttpServletRequest request) {
		Map map = PayUtils.getParameterMap(request);
		PaymentService paymentService = getPaymentService(map);
		return paymentService.callBackPage(request);
		
	}
    
}
