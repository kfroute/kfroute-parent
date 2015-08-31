/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：LatnService.java <br>
 * 创建时间：2015-5-29-下午07:44:04 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.latn.service.inf;

import java.util.List;

import cn.seocoo.platform.payment.Latn;

public interface LatnService {
	/**查询省*/
	public List<Latn> findAll();
	/**查询市*/
	public List<Latn> findList(Latn latn);
	/**查询全部*/
	public List<Latn> findTheAll();
	
	public Latn find(Latn latn);
}
