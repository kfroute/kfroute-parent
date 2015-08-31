package cn.melinkr.platform.busi.server.busiDao;

import java.sql.SQLException;

import cn.melinkr.platform.kfroute.RouterUseMsg;
import cn.melinkr.platform.kfroute.RouterUseMsgHis;
import cn.melinkr.platform.kfroute.ServerPortUseMsg;
import cn.melinkr.platform.kfroute.ServerPortUseMsgHis;

public interface IRouterOnlineNotifyDao {
	public boolean updateUseRouterMsg(RouterUseMsg routerUseMsg) throws SQLException;
	public RouterUseMsg queryUseRouterMsgByMac(String routerMac) throws SQLException;
	public boolean insertUseRouterMsgHis(RouterUseMsgHis routerUseMsgHis) throws SQLException;
	public ServerPortUseMsg queryUseServerPortMsgByIpPort(String serverIp,int serverPort) throws SQLException;
	public boolean saveUseServerPortMsg(ServerPortUseMsg serverPortUseMsg) throws SQLException;
	public boolean saveUseServerPortMsgHis(ServerPortUseMsgHis serverPortUseMsgHis) throws SQLException;
	public boolean updateUseServerPortMsg(ServerPortUseMsg serverPortUseMsg) throws SQLException;

}
