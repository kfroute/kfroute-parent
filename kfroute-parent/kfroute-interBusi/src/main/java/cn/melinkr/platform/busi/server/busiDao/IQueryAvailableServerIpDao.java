package cn.melinkr.platform.busi.server.busiDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.melinkr.platform.kfroute.ServerAvailableIp;

public interface IQueryAvailableServerIpDao {
	public List<ServerAvailableIp> queryServerIpList(Map paramMap) throws SQLException;
}
