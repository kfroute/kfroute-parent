package cn.melinkr.kfrouteWeb.business.dao;

import java.sql.SQLException;
import java.util.List;

import cn.melinkr.platform.kfroute.SelectFormBean;

public interface CommonDao{
	public  List<SelectFormBean> findAgentMap(String loginNo) throws SQLException;
	public  List<SelectFormBean> findAllAgentMap() throws SQLException;
	public List<SelectFormBean> queryServerGroupSelection() throws SQLException;
}
