/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：PayService.java <br>
 * 创建时间：2015-5-28-下午09:33:47 <br>
 * 创建用户：renyang<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.server.service.inf;

import cn.seocoo.platform.payment.PayOrderBase;

public interface PayOrderService {
	public PayOrderBase findByObject(PayOrderBase payOrderBase);
}
