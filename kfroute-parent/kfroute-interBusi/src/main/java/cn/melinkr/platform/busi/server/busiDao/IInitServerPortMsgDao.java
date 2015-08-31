package cn.melinkr.platform.busi.server.busiDao;

import java.sql.SQLException;
import java.util.Map;

import cn.melinkr.platform.kfroute.ServerShadowSocksBean;

public interface IInitServerPortMsgDao {
	public ServerShadowSocksBean queryServerPortMsg(Map queryParam) throws SQLException;
}
