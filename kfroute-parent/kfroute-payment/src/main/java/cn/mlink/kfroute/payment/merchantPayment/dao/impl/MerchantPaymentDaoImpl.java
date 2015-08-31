package cn.kfroute.platform.payment.merchantPayment.dao.impl;

import java.util.List;

import cn.kfroute.platform.payment.merchantPayment.dao.inf.MerchantPaymentDao;
import cn.seocoo.platform.payment.MerchantPayment;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class MerchantPaymentDaoImpl extends EntityManagerImpl<MerchantPayment,Integer> implements MerchantPaymentDao {

	@Override
	public List<MerchantPayment> findAll() {
		return entityDao.findAll("merchantPayment.queryAll");
	}

	@Override
	public MerchantPayment find(MerchantPayment mp) {
		return entityDao.findObj("merchantPayment.query", mp);
	}

	@Override
	public void add(MerchantPayment mp) {
		entityDao.save("merchantPayment.insert", mp);

	}

	@Override
	public void delete(MerchantPayment mp) {
		entityDao.remove("merchantPayment.delete", mp);

	}

	@Override
	public void update(MerchantPayment mp) {
		entityDao.update("merchantPayment.update", mp);

	}

	@Override
	public MerchantPayment findOne(MerchantPayment mp) {
		return  entityDao.findObj("merchantPayment.query", mp);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.merchantPayment.dao.inf.MerchantPaymentDao#findByObject(cn.seocoo.platform.payment.MerchantPayment) 
	 */
	@Override
	public List<MerchantPayment> findByObject(MerchantPayment mp) {
		// TODO Auto-generated method stub
		return  entityDao.find("merchantPayment.query", mp);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.merchantPayment.dao.inf.MerchantPaymentDao#findList(cn.seocoo.platform.payment.MerchantPayment) 
	 */
	@Override
	public List<MerchantPayment> findList(MerchantPayment mp) {
		return entityDao.find("merchantPayment.queryList", mp);
	}

}
