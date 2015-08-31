/* 
 * Copyrights 药博商城微信公众平台研发项目组 @ 2012，药博 WZT Computer Co., Ltd.<br>
 * 项目名称：药博商城微信公众平台 <br>
 * 文件名称：PaymentService.java <br>
 * 创建时间：2015-4-2-下午09:27:30 <br>
 * 创建用户：永生<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.pay.service.inf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.seocoo.platform.payment.Merchant;
import cn.seocoo.platform.payment.Payment;
import cn.seocoo.platform.payment.PaymentResult;


/**
* 网银支付接口
* <功能详细描述>
* 
* @author  yanjp
* @version  [版本号, 2013-9-6]
* @see  [相关类/方法]
* @since  [产品/模块版本]
*/

public interface PaymentService {
    
    /**
     * 跳转到支付对应的支付平台
     * @param payment
     * @return
     * @throws PaymentException
     * @see [类、类#方法、类#成员]
     */
    public String toPay(Payment payment, Merchant merchant);

	/** 
	 * @param map
	 * @return 
	 */
	public PaymentResult callBack(Map<String, Object> map);
      
    
	public void writeback(HttpServletResponse response,PaymentResult backPayResult);
	
	public void callInitiator(Map map);
	
	public String callBackPage(HttpServletRequest request);
}
