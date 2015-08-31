/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：MerchantServiceImpl.java <br>
 * 创建时间：2015-5-29-下午05:02:33 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.merchant.service.impl;

import java.util.List;

import cn.kfroute.platform.payment.merchant.dao.inf.MerchantDao;
import cn.kfroute.platform.payment.merchant.service.inf.MerchantService;
import cn.seocoo.platform.payment.MerchantEntity;

public class MerchantServiceImpl implements MerchantService {
	private MerchantDao merchantDao;
	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.merchant.service.inf.MerchantService#findAll() 
	 */
	@Override
	public List<MerchantEntity> findAll() {
		return merchantDao.findAll();
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.merchant.service.inf.MerchantService#find(cn.seocoo.platform.payment.MerchantEntity) 
	 */
	@Override
	public MerchantEntity find(MerchantEntity me) {
		return merchantDao.find(me);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.merchant.service.inf.MerchantService#add(cn.seocoo.platform.payment.MerchantEntity) 
	 */
	@Override
	public void add(MerchantEntity me) {
		merchantDao.add(me);

	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.merchant.service.inf.MerchantService#delete(cn.seocoo.platform.payment.MerchantEntity) 
	 */
	@Override
	public void delete(MerchantEntity me) {
		merchantDao.delete(me);

	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.merchant.service.inf.MerchantService#update(cn.seocoo.platform.payment.MerchantEntity) 
	 */
	@Override
	public void update(MerchantEntity me) {
		merchantDao.update(me);

	}

	public MerchantDao getMerchantDao() {
		return merchantDao;
	}

	public void setMerchantDao(MerchantDao merchantDao) {
		this.merchantDao = merchantDao;
	}

	
	
	
}
