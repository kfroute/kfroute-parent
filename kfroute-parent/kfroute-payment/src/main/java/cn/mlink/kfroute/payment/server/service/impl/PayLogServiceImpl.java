/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：PayLogServiceImpl.java <br>
 * 创建时间：2015-5-28-下午07:52:35 <br>
 * 创建用户：renyang<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.server.service.impl;

import cn.kfroute.platform.payment.server.dao.inf.PayLogDao;
import cn.kfroute.platform.payment.server.service.inf.PayLogService;
import cn.seocoo.platform.payment.PayLog;

public class PayLogServiceImpl implements PayLogService {

	private PayLogDao payLogDao;
	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.server.service.inf.PayLogService#findByObject(cn.seocoo.platform.payment.PayLog) 
	 */
	@Override
	public PayLog findByObject(PayLog payLog) {

		return payLogDao.findByObject(payLog);
	}
	
	public PayLogDao getPayLogDao() {
		return payLogDao;
	}
	public void setPayLogDao(PayLogDao payLogDao) {
		this.payLogDao = payLogDao;
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.server.service.inf.PayLogService#insertPayLog(cn.seocoo.platform.payment.PayLog) 
	 */
	@Override
	public void insertPayLog(PayLog payLog) {

		payLogDao.insertPayLog(payLog);
		
	}
}
