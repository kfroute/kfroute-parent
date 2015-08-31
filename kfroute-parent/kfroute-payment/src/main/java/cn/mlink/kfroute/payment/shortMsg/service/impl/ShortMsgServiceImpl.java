/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：ShortMsgServiceImpl.java <br>
 * 创建时间：2015-5-27-下午02:40:03 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.shortMsg.service.impl;

import java.util.List;

import cn.kfroute.platform.payment.shortMsg.dao.inf.ShortMsgDao;
import cn.kfroute.platform.payment.shortMsg.service.inf.ShortMsgService;
import cn.seocoo.platform.payment.ShortMsg;

public class ShortMsgServiceImpl implements ShortMsgService {
	private ShortMsgDao shortMsgDao;
	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.shortMsg.service.inf.ShortMsgService#findAll(java.lang.String) 
	 */
	@Override
	public List<ShortMsg> findAll(String merchantCode) {
		return shortMsgDao.findAll(merchantCode);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.shortMsg.service.inf.ShortMsgService#find(cn.seocoo.platform.payment.ShortMsg) 
	 */
	@Override
	public ShortMsg find(ShortMsg shortMsg) {
		return shortMsgDao.find(shortMsg);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.shortMsg.service.inf.ShortMsgService#add(cn.seocoo.platform.payment.ShortMsg) 
	 */
	@Override
	public void add(ShortMsg shortMsg) {
		shortMsgDao.add(shortMsg);

	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.shortMsg.service.inf.ShortMsgService#update(cn.seocoo.platform.payment.ShortMsg) 
	 */
	@Override
	public void update(ShortMsg shortMsg) {
		shortMsgDao.update(shortMsg);

	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.shortMsg.service.inf.ShortMsgService#delete(cn.seocoo.platform.payment.ShortMsg) 
	 */
	@Override
	public void delete(ShortMsg shortMsg) {
		shortMsgDao.delete(shortMsg);

	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.shortMsg.service.inf.ShortMsgService#updateStatus(int) 
	 */
	@Override
	public void updateStatus(String merchantCode) {
		shortMsgDao.updateStatus(merchantCode);
		
	}
	
	public ShortMsgDao getShortMsgDao() {
		return shortMsgDao;
	}

	public void setShortMsgDao(ShortMsgDao shortMsgDao) {
		this.shortMsgDao = shortMsgDao;
	}

	
	
	

}
