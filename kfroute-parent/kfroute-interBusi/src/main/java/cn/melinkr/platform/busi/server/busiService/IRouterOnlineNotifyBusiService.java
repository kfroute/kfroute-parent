package cn.melinkr.platform.busi.server.busiService;

import java.util.Map;

import com.melinkr.micro.exception.ServiceException;

public interface IRouterOnlineNotifyBusiService {
	public boolean updateUseRouterMsg(Map queryParams) throws ServiceException;
}
