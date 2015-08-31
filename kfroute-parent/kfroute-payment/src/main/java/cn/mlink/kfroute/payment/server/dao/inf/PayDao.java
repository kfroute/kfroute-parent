package cn.kfroute.platform.payment.server.dao.inf;

import java.util.List;
import java.util.Map;

import cn.seocoo.platform.payment.PayOrderBase;

public interface PayDao {

	public void insertPayOrderBase(PayOrderBase payOrderBase);
	
	public PayOrderBase findByObject(PayOrderBase payOrderBase);
	
	public List<PayOrderBase> findListByObject(PayOrderBase payOrderBase);
	
	public void updatePayOrderBase(PayOrderBase payOrderBase);
	
	public List<PayOrderBase> findOrderStatus(Map map);
}
