package cn.melinkr.platform.busi.server.busiService.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.melinkr.platform.busi.server.busiDao.IRouterMsgLogDao;
import cn.melinkr.platform.busi.server.busiService.IRouterMsgLogBusiService;
import cn.melinkr.platform.kfroute.RouterSysLog;
import cn.melinkr.platform.kfroute.RouterUseMsg;

import com.melinkr.micro.exception.ServiceException;

public class RouterMsgLogBusiServiceImpl implements IRouterMsgLogBusiService{
	private IRouterMsgLogDao routerMsgLogDao;

	@Override
	public boolean saveRouteMsgLog(List<RouterSysLog> wrouterSysLog)  throws ServiceException{
		// TODO Auto-generated method stub
		try {
			RouterSysLog routerSysLog = wrouterSysLog.get(0);
			RouterUseMsg routerUseMsg = routerMsgLogDao.queryUseRouterMsgByMac(routerSysLog.getMac());
			if(routerUseMsg==null){
				throw new SQLException("FAIL-021~路由器使用信息不存在");
			}
			routerUseMsg.setTotalForeignTraffic(routerSysLog.getTotalForeignTraffic()+"");
			routerUseMsg.setTotalTraffic(routerSysLog.getTotalTraffic()+"");
			routerUseMsg.setUpdateTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			routerMsgLogDao.updateUseRouterMsg(routerUseMsg);
			return routerMsgLogDao.saveRouteMsgLog(wrouterSysLog);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(e.getMessage().startsWith("FAIL-")){//如果数据层抛出可控制异常，直接抛到上一层
				throw new ServiceException(e.getMessage());
			}else{
				throw new ServiceException("FAIL-006~路由器日志数据入库失败，详情：【"+e.getMessage()+"】");
			}
			
			
		}
	}

	public IRouterMsgLogDao getRouterMsgLogDao() {
		return routerMsgLogDao;
	}

	public void setRouterMsgLogDao(IRouterMsgLogDao routerMsgLogDao) {
		this.routerMsgLogDao = routerMsgLogDao;
	}
	

}
