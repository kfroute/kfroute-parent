/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：MerchantService.java <br>
 * 创建时间：2015-5-29-下午05:01:59 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.merchant.service.inf;

import java.util.List;

import cn.seocoo.platform.payment.MerchantEntity;

public interface MerchantService {
	public List<MerchantEntity> findAll();

	public MerchantEntity find(MerchantEntity me);

	public void add(MerchantEntity me);

	public void delete(MerchantEntity me);

	public void update(MerchantEntity me);
}
