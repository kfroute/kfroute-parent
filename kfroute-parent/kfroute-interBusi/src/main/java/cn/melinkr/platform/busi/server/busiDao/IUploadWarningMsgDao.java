package cn.melinkr.platform.busi.server.busiDao;

import java.sql.SQLException;

import cn.melinkr.platform.kfroute.WarningMsg;

public interface IUploadWarningMsgDao {
	public boolean insertWarningMsg(WarningMsg warningMsg) throws SQLException;

}
