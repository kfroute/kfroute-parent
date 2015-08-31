package cn.kfroute.platform.payment.server.dao.inf;

import cn.seocoo.platform.payment.PayLog;

public interface PayLogDao {

	public void insertPayLog(PayLog payLog);
	
	public PayLog findByObject(PayLog payLog);
	
	public void updatePayLog(PayLog payLog);
}
