package cn.melinkr.platform.busi.server.busiDao;

import java.sql.SQLException;
import java.util.List;

import cn.melinkr.platform.kfroute.ServerLog;

public interface ISourceServerMsgLogDao {
	public boolean saveServerMsgLog(List<ServerLog> serverLogList) throws SQLException;
}
