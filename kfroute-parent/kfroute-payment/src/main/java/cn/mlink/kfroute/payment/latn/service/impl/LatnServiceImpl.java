/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：LatnServiceImpl.java <br>
 * 创建时间：2015-5-29-下午07:46:16 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.latn.service.impl;

import java.util.List;

import cn.kfroute.platform.payment.latn.dao.inf.LatnDao;
import cn.kfroute.platform.payment.latn.service.inf.LatnService;
import cn.seocoo.platform.payment.Latn;

public class LatnServiceImpl implements LatnService {
	private LatnDao latnDao;
	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.latn.service.inf.LatnService#findAll() 
	 */
	@Override
	public List<Latn> findAll() {
		return latnDao.findAll();
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.latn.service.inf.LatnService#findList(cn.seocoo.platform.payment.Latn) 
	 */
	@Override
	public List<Latn> findList(Latn latn) {
		return latnDao.findList(latn);
	}


	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.latn.service.inf.LatnService#find(cn.seocoo.platform.payment.Latn) 
	 */
	@Override
	public Latn find(Latn latn) {
		return latnDao.find(latn);
	}
	
	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.latn.service.inf.LatnService#findTheAll() 
	 */
	@Override
	public List<Latn> findTheAll() {
		return latnDao.findTheAll();
	}

	
	public LatnDao getLatnDao() {
		return latnDao;
	}
	
	public void setLatnDao(LatnDao latnDao) {
		this.latnDao = latnDao;
	}
	
}
