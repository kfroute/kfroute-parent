package cn.melinkr.kfrouteWeb.business.dao;

import java.sql.SQLException;

import cn.melinkr.platform.kfroute.LoginOpr;

public interface LogDao{
	public void saveOperLog(LoginOpr loginOpr) throws SQLException;
}
