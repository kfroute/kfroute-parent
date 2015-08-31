package cn.kfroute.platform.payment.paymentAccount.service.impl;

import java.util.List;

import cn.kfroute.platform.payment.paymentAccount.dao.inf.PaymentAccountDao;
import cn.kfroute.platform.payment.paymentAccount.service.inf.PaymentAccountService;
import cn.seocoo.platform.payment.PaymentAccount;

public class PaymentAccountServiceImpl implements PaymentAccountService{
	private PaymentAccountDao paymentAccountDao;
	
	@Override
	public List<PaymentAccount> findAll() {
		return paymentAccountDao.findAll();
	}

	@Override
	public PaymentAccount find(PaymentAccount pa) {
		return paymentAccountDao.find(pa);
	}

	@Override
	public void add(PaymentAccount pa) {
		paymentAccountDao.add(pa);
		
	}

	@Override
	public void update(PaymentAccount pa) {
		paymentAccountDao.update(pa);
		
	}

	@Override
	public void delete(PaymentAccount pa) {
		paymentAccountDao.delete(pa);
		
	}

	public PaymentAccountDao getPaymentAccountDao() {
		return paymentAccountDao;
	}

	public void setPaymentAccountDao(PaymentAccountDao paymentAccountDao) {
		this.paymentAccountDao = paymentAccountDao;
	}
	
	

}
