/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：LatnDaoImpl.java <br>
 * 创建时间：2015-5-29-下午07:44:35 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.latn.dao.impl;

import java.util.List;

import cn.kfroute.platform.payment.latn.dao.inf.LatnDao;
import cn.seocoo.platform.payment.Latn;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class LatnDaoImpl extends EntityManagerImpl<Latn,Integer> implements LatnDao {

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.latn.dao.inf.LatnDao#findAll() 
	 */
	@Override
	public List<Latn> findAll() {
		return entityDao.findAll("latn.queryAll");
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.latn.dao.inf.LatnDao#findList(cn.seocoo.platform.payment.Latn) 
	 */
	@Override
	public List<Latn> findList(Latn latn) {
		return entityDao.find("latn.queryList", latn);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.latn.dao.inf.LatnDao#find(cn.seocoo.platform.payment.Latn) 
	 */
	@Override
	public Latn find(Latn latn) {
		return entityDao.findObj("latn.query", latn);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.latn.dao.inf.LatnDao#findTheAll() 
	 */
	@Override
	public List<Latn> findTheAll() {
		return entityDao.findAll("latn.queryTheAll");
	}

}
