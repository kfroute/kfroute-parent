package cn.melinkr.platform.busi.server.busiService.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cn.melinkr.platform.busi.server.busiDao.IUploadTestMsgAndGetServerPortDao;
import cn.melinkr.platform.busi.server.busiService.IUploadTestMsgAndGetServerPortBusiService;
import cn.melinkr.platform.kfroute.RouterMsg;
import cn.melinkr.platform.kfroute.RouterShadowSocksBean;
import cn.melinkr.platform.kfroute.RouterUseMsg;
import cn.melinkr.platform.kfroute.RouterUseMsgHis;
import cn.melinkr.platform.kfroute.ServerPortUseMsg;
import cn.melinkr.platform.kfroute.ServerPortUseMsgHis;
import cn.melinkr.platform.kfroute.SourceServerPortMsg;

import com.melinkr.micro.exception.ServiceException;

public class UploadTestMsgAndGetServerPortBusiServiceImpl implements IUploadTestMsgAndGetServerPortBusiService{

	private IUploadTestMsgAndGetServerPortDao  uploadTestMsgAndGetServerPortDao;
	@Override
	public RouterShadowSocksBean uploadTestMsgAndGetServerPort(RouterUseMsg routerUseMsg) throws ServiceException {
		// TODO Auto-generated method stub
		String routerMac = routerUseMsg.getMac();
		RouterUseMsg _routerUseMsg=null;
		try {
			_routerUseMsg = uploadTestMsgAndGetServerPortDao.queryUseRouterMsgByMac(routerMac);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(e.getMessage().startsWith("FAIL-")){//如果数据层抛出可控制异常，直接抛到上一层
				throw new ServiceException(e.getMessage());
			}else{
				throw new ServiceException("FAIL-011~路由器使用信息查询失败，详情：【"+e.getMessage()+"】");
			}
		}
		List<SourceServerPortMsg> sourceServerPortMsgList=null;
		try {
			sourceServerPortMsgList = uploadTestMsgAndGetServerPortDao.queryUsableServerPortMsg(routerUseMsg.getResourceIp());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(e.getMessage().startsWith("FAIL-")){//如果数据层抛出可控制异常，直接抛到上一层
				throw new ServiceException(e.getMessage());
			}else{
				throw new ServiceException("FAIL-012~服务器可用端口信息查询失败，详情：【"+e.getMessage()+"】");
			}
		}
		if(sourceServerPortMsgList==null||sourceServerPortMsgList.size()==0){
			throw new ServiceException("FAIL-013~查询的服务器ip【"+routerUseMsg.getResourceIp()+"】不存在或者没有可用端口");
		}
		Random random = new Random();
		SourceServerPortMsg chooseSourceServerPort = 
			sourceServerPortMsgList.get(random.nextInt(sourceServerPortMsgList.size()));//随机获取一个可用端口
		DateFormat formt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(_routerUseMsg==null){//路由器初始使用，直接获取相关信息，保存到对象存入数据库
			RouterMsg routeBaseMsg=null;
			try {
				routeBaseMsg = uploadTestMsgAndGetServerPortDao.queryRouterBaseMsgByMac(routerMac);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(e.getMessage().startsWith("FAIL-")){//如果数据层抛出可控制异常，直接抛到上一层
					throw new ServiceException(e.getMessage());
				}else{
					throw new ServiceException("FAIL-014~路由器基础信息查询失败，详情：【"+e.getMessage()+"】");
				}
			}
			if(routeBaseMsg==null){
				throw new ServiceException("FAIL-015~该设备对应的mac信息不存在！");
			}
			_routerUseMsg = routerUseMsg;
			_routerUseMsg.setRunStatus(1);
			_routerUseMsg.setActiveTime(formt.format(new Date()));
			_routerUseMsg.setResourcePort(chooseSourceServerPort.getPort()+"");
			_routerUseMsg.setUpdateTimestamp(formt.format(new Date()));
			_routerUseMsg.setRouterId(routeBaseMsg.getRIdNo());
			try {
				uploadTestMsgAndGetServerPortDao.insertUseRouterMsg(_routerUseMsg);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(e.getMessage().startsWith("FAIL-")){//如果数据层抛出可控制异常，直接抛到上一层
					throw new ServiceException(e.getMessage());
				}else{
					throw new ServiceException("FAIL-016~路由器测试数据入库失败，详情：【"+e.getMessage()+"】");
				}
			}
		}else{//路由器已经激活修改相关参数信息
			RouterUseMsgHis routerUseMsgHis = new RouterUseMsgHis();
			routerUseMsgHis.setRouterUseMsg(_routerUseMsg);
			routerUseMsgHis.setHisNote("设备重新获取服务器端口备份");
			routerUseMsgHis.setHisType(2);
			try {
				uploadTestMsgAndGetServerPortDao.insertUseRouterMsgHis(routerUseMsgHis);
			} catch (SQLException e) {//日志记录异常仅后台告警，不应影响业务逻辑
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {//重新分配端口时，将之前的端口释放
				ServerPortUseMsg serverPortUseMsg = uploadTestMsgAndGetServerPortDao.queryUseServerPortMsgByIpPort(_routerUseMsg.getResourceIp(), Integer.parseInt(_routerUseMsg.getResourcePort()));
				if(serverPortUseMsg!=null){
					if(serverPortUseMsg.getCloseTimestamp()==null||serverPortUseMsg.getCloseTimestamp().equals("")){//如果端口还没有自动失效就 被其他用户占用，则在以占用时间作为上次连接下线时间
						serverPortUseMsg.setCloseTimestamp(new Timestamp(System.currentTimeMillis()));
					}
					serverPortUseMsg.setPortStatus(0);
					serverPortUseMsg.setOpenTimestamp(null);
					serverPortUseMsg.setCloseTimestamp(null);
					serverPortUseMsg.setPortTotalIncoming(0);
					serverPortUseMsg.setPortTotalIncoming(0);
					uploadTestMsgAndGetServerPortDao.updateUseServerPortMsg(serverPortUseMsg);
				}
				_routerUseMsg.setResourcePort(chooseSourceServerPort.getPort()+"");
				_routerUseMsg.setRunStatus(1);
				_routerUseMsg.setUpdateTimestamp(formt.format(new Date()));
				uploadTestMsgAndGetServerPortDao.updateUseRouterMsg(_routerUseMsg);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(e.getMessage().startsWith("FAIL-")){//如果数据层抛出可控制异常，直接抛到上一层
					throw new ServiceException(e.getMessage());
				}else{
					throw new ServiceException("FAIL-016~路由器测试数据入库失败，详情：【"+e.getMessage()+"】");
				}
			}
		}
		RouterShadowSocksBean routerShadowSocksBean = new RouterShadowSocksBean();
		routerShadowSocksBean.setServer(chooseSourceServerPort.getSourceIp());
		routerShadowSocksBean.setServer_port(chooseSourceServerPort.getPort());
		routerShadowSocksBean.setPassword(chooseSourceServerPort.getPassword());
		routerShadowSocksBean.setLocal_port(1080);
		routerShadowSocksBean.setMethod("aes-256-cfb");
		return routerShadowSocksBean;
	}
	public IUploadTestMsgAndGetServerPortDao getUploadTestMsgAndGetServerPortDao() {
		return uploadTestMsgAndGetServerPortDao;
	}
	public void setUploadTestMsgAndGetServerPortDao(
			IUploadTestMsgAndGetServerPortDao uploadTestMsgAndGetServerPortDao) {
		this.uploadTestMsgAndGetServerPortDao = uploadTestMsgAndGetServerPortDao;
	}
	public static void main(String[] args){
		DateFormat formt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(formt.format(new Date()));
	}

}
