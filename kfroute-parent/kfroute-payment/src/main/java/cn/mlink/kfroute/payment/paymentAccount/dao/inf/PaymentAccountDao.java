package cn.kfroute.platform.payment.paymentAccount.dao.inf;

import java.util.List;

import cn.seocoo.platform.payment.PaymentAccount;

public interface PaymentAccountDao {
	public List<PaymentAccount> findAll();

	public PaymentAccount find(PaymentAccount pa);
	
	public List<PaymentAccount> findByObject(PaymentAccount pa);

	public void add(PaymentAccount pa);

	public void update(PaymentAccount pa);

	public void delete(PaymentAccount pa);
	
	public PaymentAccount findOne(PaymentAccount pa);

}
