/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：PayOrderServiceImpl.java <br>
 * 创建时间：2015-5-28-下午09:35:31 <br>
 * 创建用户：renyang<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.server.service.impl;

import cn.kfroute.platform.payment.server.dao.inf.PayDao;
import cn.kfroute.platform.payment.server.service.inf.PayOrderService;
import cn.seocoo.platform.payment.PayOrderBase;

public class PayOrderServiceImpl implements PayOrderService {

	private PayDao payDao;
	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.server.service.inf.PayService#findByObject(cn.seocoo.platform.payment.PayOrderBase) 
	 */
	@Override
	public PayOrderBase findByObject(PayOrderBase payOrderBase) {
		// TODO Auto-generated method stub
		return payDao.findByObject(payOrderBase);
	}
	public PayDao getPayDao() {
		return payDao;
	}
	public void setPayDao(PayDao payDao) {
		this.payDao = payDao;
	}
	
	

}
