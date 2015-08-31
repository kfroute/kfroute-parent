package cn.kfroute.platform.payment.paymentEntity.dao.inf;

import java.util.List;

import cn.seocoo.platform.payment.PaymentEntity;

public interface PaymentEntityDao {
	public List<PaymentEntity> findAll();

	public PaymentEntity find(PaymentEntity p);

	public void add(PaymentEntity p);

	public void update(PaymentEntity p);

	public void delete(PaymentEntity p);

}
