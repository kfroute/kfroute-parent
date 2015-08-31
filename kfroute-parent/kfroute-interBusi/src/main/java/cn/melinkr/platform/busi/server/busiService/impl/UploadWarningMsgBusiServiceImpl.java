package cn.melinkr.platform.busi.server.busiService.impl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import cn.melinkr.platform.busi.server.busiDao.IRouterOnlineNotifyDao;
import cn.melinkr.platform.busi.server.busiDao.IUploadWarningMsgDao;
import cn.melinkr.platform.busi.server.busiService.IUploadWarningMsgBusiService;
import cn.melinkr.platform.kfroute.RouterUseMsg;
import cn.melinkr.platform.kfroute.WarningMsg;

import com.melinkr.micro.exception.ServiceException;

public class UploadWarningMsgBusiServiceImpl implements IUploadWarningMsgBusiService{

	private IUploadWarningMsgDao  uploadWarningMsgDao;
	
	@Override
	public boolean insertWarningMsg(WarningMsg warningMsg)
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return uploadWarningMsgDao.insertWarningMsg(warningMsg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(e.getMessage().startsWith("FAIL-")){//如果数据层抛出可控制异常，直接抛到上一层
				throw new ServiceException(e.getMessage());
			}else{
				throw new ServiceException("FAIL-020~告警数据入库失败，详情：【"+e.getMessage()+"】");
			}
		}
		
	}
	public IUploadWarningMsgDao getUploadWarningMsgDao() {
		return uploadWarningMsgDao;
	}

	public void setUploadWarningMsgDao(IUploadWarningMsgDao uploadWarningMsgDao) {
		this.uploadWarningMsgDao = uploadWarningMsgDao;
	}

	
	
	
	
}
