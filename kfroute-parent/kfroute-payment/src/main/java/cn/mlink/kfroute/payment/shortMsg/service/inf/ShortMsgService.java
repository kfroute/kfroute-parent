/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：ShortMsgService.java <br>
 * 创建时间：2015-5-27-下午02:33:41 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.shortMsg.service.inf;

import java.util.List;

import cn.seocoo.platform.payment.ShortMsg;

public interface ShortMsgService {
	public List<ShortMsg> findAll(String merchantCode);

	public ShortMsg find(ShortMsg shortMsg);

	public void add(ShortMsg shortMsg);

	public void update(ShortMsg shortMsg);

	public void delete(ShortMsg shortMsg);
	
	public void updateStatus(String merchantCode);
}
