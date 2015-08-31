package cn.kfroute.platform.payment.merchantPayment.service.inf;

import java.util.List;

import cn.seocoo.platform.payment.MerchantPayment;

public interface MerchantPaymentService {
	public List<MerchantPayment> findAll();

	public MerchantPayment find(MerchantPayment mp);

	public void add(MerchantPayment mp);

	public void delete(MerchantPayment mp);

	public void update(MerchantPayment mp);
	
	public List<MerchantPayment> findList(MerchantPayment mp);
}
