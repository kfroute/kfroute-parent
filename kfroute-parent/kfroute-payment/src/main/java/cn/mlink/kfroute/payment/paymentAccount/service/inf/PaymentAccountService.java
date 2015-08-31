package cn.kfroute.platform.payment.paymentAccount.service.inf;

import java.util.List;

import cn.seocoo.platform.payment.PaymentAccount;

public interface PaymentAccountService {
	public List<PaymentAccount> findAll();

	public PaymentAccount find(PaymentAccount pa);

	public void add(PaymentAccount pa);

	public void update(PaymentAccount pa);

	public void delete(PaymentAccount pa);
}
