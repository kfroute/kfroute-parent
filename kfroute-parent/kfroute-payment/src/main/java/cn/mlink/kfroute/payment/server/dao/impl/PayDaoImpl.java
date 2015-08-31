package cn.kfroute.platform.payment.server.dao.impl;

import java.util.List;
import java.util.Map;

import cn.kfroute.platform.payment.server.dao.inf.PayDao;
import cn.seocoo.platform.payment.PayOrderBase;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class PayDaoImpl extends EntityManagerImpl<PayOrderBase,Integer> implements  PayDao {

	@Override
	public void insertPayOrderBase(PayOrderBase payOrderBase) {
	  
		entityDao.save("OrderBase.saveOrderBase", payOrderBase);
		
	}

	@Override
	public PayOrderBase findByObject(PayOrderBase payOrderBase) {
		// TODO Auto-generated method stub
		List<PayOrderBase> list = entityDao.find("OrderBase.query", payOrderBase);
		if(list == null||list.isEmpty()){
			return null;	
		}
		return list.get(0);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.server.dao.inf.PayDao#updatePayOrderBase(cn.seocoo.platform.payment.PayOrderBase) 
	 */
	@Override
	public void updatePayOrderBase(PayOrderBase payOrderBase) {
		entityDao.update("OrderBase.updateOrderBase", payOrderBase);
		
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.server.dao.inf.PayDao#findOrderStatus(java.util.List) 
	 */
	@Override
	public List<PayOrderBase> findOrderStatus(Map map) {
		return entityDao.find("OrderBase.queryOrderStatus", map);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.server.dao.inf.PayDao#findListByObject(cn.seocoo.platform.payment.PayOrderBase) 
	 */
	@Override
	public List<PayOrderBase> findListByObject(PayOrderBase payOrderBase) {
		// TODO Auto-generated method stub
		return entityDao.find("OrderBase.query", payOrderBase);
	}

}
