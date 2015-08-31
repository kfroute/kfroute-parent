/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：LatnDao.java <br>
 * 创建时间：2015-5-29-下午07:42:47 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.latn.dao.inf;

import java.util.List;

import cn.seocoo.platform.payment.Latn;

public interface LatnDao {
	/**查询省*/
	public List<Latn> findAll();
	/**查询全部*/
	public List<Latn> findTheAll();
	/**查询市*/
	public List<Latn> findList(Latn latn);
	
	public Latn find(Latn latn);
}
