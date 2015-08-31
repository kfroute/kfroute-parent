package cn.melinkr.platform.busi.server.busiDao;

import java.sql.SQLException;
import java.util.List;

import cn.melinkr.platform.kfroute.RouterSysLog;
import cn.melinkr.platform.kfroute.RouterUseMsg;

public interface IRouterMsgLogDao {
	public boolean saveRouteMsgLog(List<RouterSysLog> wrouterSysLog) throws SQLException;
	public RouterUseMsg queryUseRouterMsgByMac(String routerMac) throws SQLException;
	public boolean updateUseRouterMsg(RouterUseMsg routerUseMsg) throws SQLException;
}
