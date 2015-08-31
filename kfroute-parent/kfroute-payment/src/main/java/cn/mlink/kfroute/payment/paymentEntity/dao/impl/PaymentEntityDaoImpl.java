package cn.kfroute.platform.payment.paymentEntity.dao.impl;

import java.util.List;

import cn.kfroute.platform.payment.paymentEntity.dao.inf.PaymentEntityDao;
import cn.seocoo.platform.payment.PaymentEntity;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class PaymentEntityDaoImpl extends EntityManagerImpl<PaymentEntity,Integer> implements PaymentEntityDao {

	@Override
	public List<PaymentEntity> findAll() {
		return entityDao.findAll("paymentEntity.queryAll");
	}

	@Override
	public PaymentEntity find(PaymentEntity p) {
		return entityDao.findObj("paymentEntity.query", p);
	}

	@Override
	public void add(PaymentEntity p) {
		entityDao.save("paymentEntity.insert", p);

	}

	@Override
	public void update(PaymentEntity p) {
		entityDao.update("paymentEntity.update", p);

	}

	@Override
	public void delete(PaymentEntity p) {
		entityDao.remove("paymentEntity.delete", p);

	}

}
