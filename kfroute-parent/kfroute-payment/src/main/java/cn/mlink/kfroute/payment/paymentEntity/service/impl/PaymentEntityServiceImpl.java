package cn.kfroute.platform.payment.paymentEntity.service.impl;

import java.util.List;

import cn.kfroute.platform.payment.paymentEntity.dao.inf.PaymentEntityDao;
import cn.kfroute.platform.payment.paymentEntity.service.inf.PaymentEntityService;
import cn.seocoo.platform.payment.PaymentEntity;

public class PaymentEntityServiceImpl implements PaymentEntityService {
	private PaymentEntityDao paymentEntityDao;
	
	@Override
	public List<PaymentEntity> findAll() {
		return paymentEntityDao.findAll();
	}

	@Override
	public PaymentEntity find(PaymentEntity p) {
		return paymentEntityDao.find(p);
	}

	@Override
	public void add(PaymentEntity p) {
		paymentEntityDao.add(p);

	}

	@Override
	public void update(PaymentEntity p) {
		paymentEntityDao.update(p);

	}

	@Override
	public void delete(PaymentEntity p) {
		paymentEntityDao.delete(p);

	}

	public PaymentEntityDao getPaymentEntityDao() {
		return paymentEntityDao;
	}

	public void setPaymentEntityDao(PaymentEntityDao paymentEntityDao) {
		this.paymentEntityDao = paymentEntityDao;
	}
	

}
