package cn.melinkr.platform.busi.server.busiService.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import cn.melinkr.platform.busi.server.busiDao.IRouterOnlineNotifyDao;
import cn.melinkr.platform.busi.server.busiService.IRouterOnlineNotifyBusiService;
import cn.melinkr.platform.kfroute.RouterUseMsg;
import cn.melinkr.platform.kfroute.RouterUseMsgHis;
import cn.melinkr.platform.kfroute.ServerPortUseMsg;
import cn.melinkr.platform.kfroute.ServerPortUseMsgHis;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.melinkr.micro.exception.ServiceException;
import com.melinkr.micro.geoip.entity.GeoIpLocation;
import com.melinkr.micro.util.GeoIpUtil;

public class RouterOnlineNotifyBusiServiceImpl implements IRouterOnlineNotifyBusiService{

	private IRouterOnlineNotifyDao  routerOnlineNotifyDao;
	@Override
	public boolean updateUseRouterMsg(Map queryParams) throws ServiceException {
		// TODO Auto-generated method stub
		String routerMac = (String)queryParams.get("mac");
		DateFormat formt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		RouterUseMsg _routerUseMsg=null;
		try {
			_routerUseMsg = routerOnlineNotifyDao.queryUseRouterMsgByMac(routerMac);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(e.getMessage().startsWith("FAIL-")){//如果数据层抛出可控制异常，直接抛到上一层
				throw new ServiceException(e.getMessage());
			}else{
				throw new ServiceException("FAIL-017~路由器使用信息查询失败，详情：【"+e.getMessage()+"】");
			}
		}
		if(_routerUseMsg==null){
			throw new ServiceException("FAIL-018~查询的路由器mac【"+routerMac+"】没有激活");
		}
		RouterUseMsgHis routerUseMsgHis = new RouterUseMsgHis();
		if(_routerUseMsg.getOfflineTimestamp()==null||_routerUseMsg.getOfflineTimestamp().equals("")){
			_routerUseMsg.setOfflineTimestamp(formt.format(new Date()));
		}
		
		routerUseMsgHis.setRouterUseMsg(_routerUseMsg);
		routerUseMsgHis.setHisNote("路由器设备上线备份");
		routerUseMsgHis.setHisType(1);
		try {
			routerOnlineNotifyDao.insertUseRouterMsgHis(routerUseMsgHis);
		} catch (SQLException e) {//日志记录异常仅后台告警，不应影响业务逻辑
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//路由器通知上线，服务器的端口数据也相应进行更新
		try {
			ServerPortUseMsg serverPortUseMsg = routerOnlineNotifyDao.queryUseServerPortMsgByIpPort(_routerUseMsg.getResourceIp(), Integer.parseInt(_routerUseMsg.getResourcePort()));
			if(serverPortUseMsg==null){//服务器端口第一次使用
				serverPortUseMsg = new ServerPortUseMsg();
				serverPortUseMsg.setServerIp(_routerUseMsg.getResourceIp());
				serverPortUseMsg.setServerPort(Integer.parseInt(_routerUseMsg.getResourcePort()));
				serverPortUseMsg.setPortStatus(2);
				serverPortUseMsg.setOpenTimestamp(new Timestamp(System.currentTimeMillis()));
				routerOnlineNotifyDao.saveUseServerPortMsg(serverPortUseMsg);
			}else{//之后的每次使用
				if(serverPortUseMsg.getCloseTimestamp()==null||serverPortUseMsg.getCloseTimestamp().equals("")){//如果端口还没有自动失效就 被其他用户占用，则在以占用时间作为上次连接下线时间
					serverPortUseMsg.setCloseTimestamp(new Timestamp(System.currentTimeMillis()));
					
				}
				ServerPortUseMsgHis serverPortUseMsgHis = new ServerPortUseMsgHis();
				serverPortUseMsgHis.setServerPortUseMsg(serverPortUseMsg);
				serverPortUseMsgHis.setHisNote("路由器设备上线端口未正常关闭数据移历史表");
				serverPortUseMsgHis.setHisType(1);
				routerOnlineNotifyDao.saveUseServerPortMsgHis(serverPortUseMsgHis);
				serverPortUseMsg.setPortStatus(2);
				serverPortUseMsg.setOpenTimestamp(new Timestamp(System.currentTimeMillis()));
				serverPortUseMsg.setCloseTimestamp(null);
				serverPortUseMsg.setPortTotalIncoming(0);
				serverPortUseMsg.setPortTotalIncoming(0);
				routerOnlineNotifyDao.updateUseServerPortMsg(serverPortUseMsg);
			}
			//routerOnlineNotifyDao.updateUseRouterMsg(_routerUseMsg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(e.getMessage().startsWith("FAIL-")){//如果数据层抛出可控制异常，直接抛到上一层
				throw new ServiceException(e.getMessage());
			}else{
				throw new ServiceException("FAIL-019~路由器端口信息更新失败，详情：【"+e.getMessage()+"】");
			}
		}
		GeoIpLocation geoIpLocation=null;
		Double routeLatitude = null;
		Double routeLongitude = null;
		try {
			geoIpLocation = GeoIpUtil.getLocationBean(_routerUseMsg.getRealIp());
			routeLatitude = geoIpLocation.getLocation().getLatitude();
			routeLongitude = geoIpLocation.getLocation().getLongitude();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (GeoIp2Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		_routerUseMsg.setRunStatus(2);

		_routerUseMsg.setUpdateTimestamp(formt.format(new Date()));
		_routerUseMsg.setOnlineTimestamp(formt.format(new Date()));
		_routerUseMsg.setOfflineTimestamp(null);
		_routerUseMsg.setLatitude(routeLatitude);
		_routerUseMsg.setLongitude(routeLongitude);
		try {
			routerOnlineNotifyDao.updateUseRouterMsg(_routerUseMsg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(e.getMessage().startsWith("FAIL-")){//如果数据层抛出可控制异常，直接抛到上一层
				throw new ServiceException(e.getMessage());
			}else{
				throw new ServiceException("FAIL-019~路由器端口信息更新失败，详情：【"+e.getMessage()+"】");
			}
		}
		
		return true;
	}
	public IRouterOnlineNotifyDao getRouterOnlineNotifyDao() {
		return routerOnlineNotifyDao;
	}
	public void setRouterOnlineNotifyDao(
			IRouterOnlineNotifyDao routerOnlineNotifyDao) {
		this.routerOnlineNotifyDao = routerOnlineNotifyDao;
	}
	
}
