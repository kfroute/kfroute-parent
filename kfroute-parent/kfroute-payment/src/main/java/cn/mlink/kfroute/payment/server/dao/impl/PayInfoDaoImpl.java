package cn.kfroute.platform.payment.server.dao.impl;

import cn.kfroute.platform.payment.server.dao.inf.PayInfoDao;
import cn.seocoo.platform.payment.PayInfo;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class PayInfoDaoImpl extends EntityManagerImpl<PayInfo,Integer> implements  PayInfoDao {

	@Override
	public void insertPayInfo(PayInfo payInfo) {
		entityDao.save("PayInfo.savePayInfo", payInfo);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.server.dao.inf.PayInfoDao#updatePayInfo(cn.seocoo.platform.payment.PayInfo) 
	 */
	@Override
	public void updatePayInfo(PayInfo payInfo) {

		entityDao.update("PayInfo.updatePayInfo", payInfo);
		
	}

}
