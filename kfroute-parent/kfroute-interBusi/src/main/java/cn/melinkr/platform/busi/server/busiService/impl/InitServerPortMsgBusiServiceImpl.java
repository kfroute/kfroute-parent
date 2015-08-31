package cn.melinkr.platform.busi.server.busiService.impl;

import java.sql.SQLException;
import java.util.Map;

import cn.melinkr.platform.busi.server.busiDao.IInitServerPortMsgDao;
import cn.melinkr.platform.busi.server.busiService.IInitServerPortMsgBusiService;
import cn.melinkr.platform.kfroute.ServerShadowSocksBean;

import com.melinkr.micro.exception.ServiceException;

public class InitServerPortMsgBusiServiceImpl implements IInitServerPortMsgBusiService{

	private IInitServerPortMsgDao  initServerPortMsgDao;
	@Override
	public ServerShadowSocksBean queryServerPortMsg(Map paramMap) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return initServerPortMsgDao.queryServerPortMsg(paramMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(e.getMessage().startsWith("FAIL-")){//如果数据层抛出可控制异常，直接抛到上一层
				throw new ServiceException(e.getMessage());
			}else{
				throw new ServiceException("FAIL-005~服务器端口初始化查询失败，详情：【"+e.getMessage()+"】");
			}
		}
	}
	public IInitServerPortMsgDao getInitServerPortMsgDao() {
		return initServerPortMsgDao;
	}
	public void setInitServerPortMsgDao(IInitServerPortMsgDao initServerPortMsgDao) {
		this.initServerPortMsgDao = initServerPortMsgDao;
	}
	

}
