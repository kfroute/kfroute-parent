package cn.melinkr.platform.busi.server.busiService.impl;

import java.sql.SQLException;
import java.util.List;

import cn.melinkr.platform.busi.server.busiDao.ISourceServerMsgLogDao;
import cn.melinkr.platform.busi.server.busiService.ISourceServerMsgLogBusiService;
import cn.melinkr.platform.kfroute.ServerLog;

import com.melinkr.micro.exception.ServiceException;


public class SourceServerMsgLogBusiServiceImpl implements ISourceServerMsgLogBusiService{
	private ISourceServerMsgLogDao sourceServerMsgLogDao;

	@Override
	public boolean saveServerMsgLog(List<ServerLog> serverLogList)  throws ServiceException{
		// TODO Auto-generated method stub
		
		try {
			sourceServerMsgLogDao.saveServerMsgLog(serverLogList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(e.getMessage().startsWith("FAIL-")){//如果数据层抛出不可控制异常，直接抛到上一层
				throw new ServiceException(e.getMessage());
			}else{
				throw new ServiceException("FAIL-007~服务器日志数据入库失败，详情：【"+e.getMessage()+"】");
			}
		}
		return true;
	}

	public ISourceServerMsgLogDao getSourceServerMsgLogDao() {
		return sourceServerMsgLogDao;
	}

	public void setSourceServerMsgLogDao(
			ISourceServerMsgLogDao sourceServerMsgLogDao) {
		this.sourceServerMsgLogDao = sourceServerMsgLogDao;
	}

	
	

}
