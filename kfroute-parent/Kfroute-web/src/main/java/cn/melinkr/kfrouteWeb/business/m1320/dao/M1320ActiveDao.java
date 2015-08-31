package cn.melinkr.kfrouteWeb.business.m1320.dao;

import java.sql.SQLException;
import java.util.List;

import cn.melinkr.platform.kfroute.ServerPortPool;
import cn.melinkr.platform.kfroute.SourceServerMsg;
import cn.melinkr.platform.kfroute.SourceServerPortMsg;

public interface M1320ActiveDao{
	public SourceServerMsg queryServerById(int id) throws SQLException;
	public List<ServerPortPool> queryPortPool() throws SQLException;
	public boolean insertServerPortList(List<SourceServerPortMsg> sourceServerPortList) throws SQLException;
	public boolean updateServerMsg(SourceServerMsg sourceServerMsg) throws SQLException;
}
