/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：MerchantDaoImpl.java <br>
 * 创建时间：2015-5-29-下午04:57:40 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.merchant.dao.impl;

import java.util.List;

import cn.kfroute.platform.payment.merchant.dao.inf.MerchantDao;
import cn.seocoo.platform.payment.MerchantEntity;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class MerchantDaoImpl extends EntityManagerImpl<MerchantEntity,Integer> implements MerchantDao {
	
	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.merchant.dao.inf.MerchantDao#findAll() 
	 */
	@Override
	public List<MerchantEntity> findAll() {
		return entityDao.findAll("merchant.queryAll");
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.merchant.dao.inf.MerchantDao#find(cn.seocoo.platform.payment.MerchantEntity) 
	 */
	@Override
	public MerchantEntity find(MerchantEntity me) {
		return entityDao.findObj("merchant.query", me);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.merchant.dao.inf.MerchantDao#add(cn.seocoo.platform.payment.MerchantEntity) 
	 */
	@Override
	public void add(MerchantEntity me) {
		entityDao.save("merchant.insert", me);

	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.merchant.dao.inf.MerchantDao#delete(cn.seocoo.platform.payment.MerchantEntity) 
	 */
	@Override
	public void delete(MerchantEntity me) {
		entityDao.remove("merchant.delete", me);

	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.merchant.dao.inf.MerchantDao#update(cn.seocoo.platform.payment.MerchantEntity) 
	 */
	@Override
	public void update(MerchantEntity me) {
		entityDao.update("merchant.update", me);

	}

}
