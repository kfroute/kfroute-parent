package cn.melinkr.platform.busi.server.busiService;

import java.util.List;

import cn.melinkr.platform.kfroute.ServerLog;

import com.melinkr.micro.exception.ServiceException;

public interface ISourceServerMsgLogBusiService {
	public boolean saveServerMsgLog(List<ServerLog> serverLogList) throws ServiceException;
}
