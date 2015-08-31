/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：ShortMsgDaoImpl.java <br>
 * 创建时间：2015-5-27-下午02:34:13 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.shortMsg.dao.impl;

import java.util.List;

import cn.kfroute.platform.payment.shortMsg.dao.inf.ShortMsgDao;
import cn.seocoo.platform.payment.ShortMsg;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class ShortMsgDaoImpl extends EntityManagerImpl<ShortMsg,String> implements ShortMsgDao {

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.shortMsg.dao.inf.ShortMsgDao#findAll(java.lang.String) 
	 */
	@Override
	public List<ShortMsg> findAll(String merchantCode) {
		return entityDao.find("shortMsg.queryAll", merchantCode);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.shortMsg.dao.inf.ShortMsgDao#find(cn.seocoo.platform.payment.ShortMsg) 
	 */
	@Override
	public ShortMsg find(ShortMsg shortMsg) {
		return entityDao.findObj("shortMsg.query", shortMsg);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.shortMsg.dao.inf.ShortMsgDao#add(cn.seocoo.platform.payment.ShortMsg) 
	 */
	@Override
	public void add(ShortMsg shortMsg) {
		entityDao.save("shortMsg.insert", shortMsg);

	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.shortMsg.dao.inf.ShortMsgDao#update(cn.seocoo.platform.payment.ShortMsg) 
	 */
	@Override
	public void update(ShortMsg shortMsg) {
		entityDao.update("shortMsg.update", shortMsg);

	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.shortMsg.dao.inf.ShortMsgDao#delete(cn.seocoo.platform.payment.ShortMsg) 
	 */
	@Override
	public void delete(ShortMsg shortMsg) {
		entityDao.remove("shortMsg.delete", shortMsg);

	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.shortMsg.dao.inf.ShortMsgDao#updateStatus(int) 
	 */
	@Override
	public void updateStatus(String merchantCode) {
		entityDao.update("shortMsg.updateStatus", merchantCode);
		
	}

}
