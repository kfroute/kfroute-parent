package cn.melinkr.platform.busi.server.busiService;

import java.util.List;

import cn.melinkr.platform.kfroute.RouterSysLog;

import com.melinkr.micro.exception.ServiceException;

public interface IRouterMsgLogBusiService {
	public boolean saveRouteMsgLog(List<RouterSysLog> wrouterSysLog) throws ServiceException;
}
