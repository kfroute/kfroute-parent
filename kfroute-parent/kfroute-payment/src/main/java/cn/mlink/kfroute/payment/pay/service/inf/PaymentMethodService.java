package cn.kfroute.platform.payment.pay.service.inf;

import java.util.List;

import cn.seocoo.platform.payment.PaymentMethod;

public interface PaymentMethodService {
	public List<PaymentMethod> queryMethod();
}
