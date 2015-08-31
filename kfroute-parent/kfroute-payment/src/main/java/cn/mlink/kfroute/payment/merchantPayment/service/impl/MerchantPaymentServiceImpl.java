package cn.kfroute.platform.payment.merchantPayment.service.impl;

import java.util.List;

import cn.kfroute.platform.payment.merchantPayment.dao.inf.MerchantPaymentDao;
import cn.kfroute.platform.payment.merchantPayment.service.inf.MerchantPaymentService;
import cn.seocoo.platform.payment.MerchantPayment;

public class MerchantPaymentServiceImpl implements MerchantPaymentService {
	private MerchantPaymentDao merchantPaymentDao;
	
	@Override
	public List<MerchantPayment> findAll() {
		return merchantPaymentDao.findAll();
	}

	@Override
	public MerchantPayment find(MerchantPayment mp) {
		return merchantPaymentDao.find(mp);
	}

	@Override
	public void add(MerchantPayment mp) {
		merchantPaymentDao.add(mp);

	}

	@Override
	public void delete(MerchantPayment mp) {
		merchantPaymentDao.delete(mp);

	}

	@Override
	public void update(MerchantPayment mp) {
		merchantPaymentDao.update(mp);

	}

	public MerchantPaymentDao getMerchantPaymentDao() {
		return merchantPaymentDao;
	}

	public void setMerchantPaymentDao(MerchantPaymentDao merchantPaymentDao) {
		this.merchantPaymentDao = merchantPaymentDao;
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.merchantPayment.service.inf.MerchantPaymentService#findList(cn.seocoo.platform.payment.MerchantPayment) 
	 */
	@Override
	public List<MerchantPayment> findList(MerchantPayment mp) {
		return merchantPaymentDao.findList(mp);
	}
	
	

}
