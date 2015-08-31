package cn.kfroute.platform.payment.server.dao.inf;

import cn.seocoo.platform.payment.PayInfo;

public interface PayInfoDao {

	public void insertPayInfo(PayInfo payInfo);
	
	public void updatePayInfo(PayInfo payInfo);
}
