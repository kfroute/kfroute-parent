package cn.melinkr.platform.busi.server.busiService;

import java.util.List;
import java.util.Map;

import cn.melinkr.platform.kfroute.ServerAvailableIp;

import com.melinkr.micro.exception.ServiceException;

/**
 * 
 * @author: zhangyl
 * @time: 2015-08-13 15:10
 * @version: 1.0
 * 路由器设备初始化业务，获取服务器ip列表，选择性能好的ip地址作为服务器
 *
 */
public interface IQueryAvailableServerIpBusiService {
	public List<ServerAvailableIp> queryServerIpList(Map paramMap) throws ServiceException;
}
