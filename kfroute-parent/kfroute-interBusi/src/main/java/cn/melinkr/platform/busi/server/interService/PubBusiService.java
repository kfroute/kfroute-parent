package cn.melinkr.platform.busi.server.interService;

import com.tydic.framework.base.exception.ServiceException;

/**
 * 
 * @author: zhangyl
 * @time: 2015-07-07 17:20
 * @version: 1.0
 * 统一服务调用接口，所有的业务调用实现服务都需要实现该接口
 */
public interface PubBusiService {
	public Object sevice(String param,String ip); 
}
