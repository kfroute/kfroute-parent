package cn.kfroute.platform.payment.server.dao.impl;

import cn.kfroute.platform.payment.server.dao.inf.PayLogDao;
import cn.seocoo.platform.payment.PayLog;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class PayLogDaoImpl extends EntityManagerImpl<PayLog,Integer> implements  PayLogDao {

	@Override
	public void insertPayLog(PayLog payLog) {
		
		entityDao.save("PayLogInfo.savePayLogInfo", payLog);
		
	}

	@Override
	public PayLog findByObject(PayLog payLog) {
		return entityDao.findObj("PayLogInfo.findPayLogInfo", payLog);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.server.dao.inf.PayLogDao#updatePayLog(cn.seocoo.platform.payment.PayLog) 
	 */
	@Override
	public void updatePayLog(PayLog payLog) {

		entityDao.update("PayLogInfo.updatePayLogInfo", payLog);
		
	}





}
