package cn.melinkr.kfrouteWeb.business.m1320.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cn.melinkr.kfrouteWeb.business.m1320.dao.M1320ActiveDao;
import cn.melinkr.kfrouteWeb.business.m1320.service.M1320ActiveService;
import cn.melinkr.platform.kfroute.ServerPortPool;
import cn.melinkr.platform.kfroute.SourceServerMsg;
import cn.melinkr.platform.kfroute.SourceServerPortMsg;

import com.alibaba.fastjson.JSON;
import com.melinkr.micro.exception.ServiceException;

/**
 * @author: joki
 * @time: 2015-08-07 16:25
 * @version: 1.0
 * 服务器激活业务操作模块
 *
 */

public class M1320ActiveServiceImpl implements M1320ActiveService {

	private M1320ActiveDao m1320ActiveDao;
	
	/**
	 * 通过对id列表的分割，后台id数组，从数据库中查询服务器信息，存在并且未激活，则执行激活操作
	 * 激活操作为：从端口池中获取端口数据，每个端口生成一个随机的uuid为密码
	 * 返回字符串数组  [0]：成功个数 [1]：失败个数   [2]：提示信息
	 */
	@Override
	public String[] doActiveServer(String idList,String loginNo) throws ServiceException {
		// TODO Auto-generated method stub
		String[] idArray = idList.split(",");
		int success=0;
		int fail =0;
		String msg = "";
		List<SourceServerMsg> serverUpdateList = new ArrayList<SourceServerMsg>();
		for(String id:idArray){
			List<SourceServerPortMsg> sourceServerPortList = new ArrayList<SourceServerPortMsg>();
			int _id = Integer.parseInt(id);
			SourceServerMsg sourceServerMsg=null;
			try {
				sourceServerMsg = m1320ActiveDao.queryServerById(_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail++;
				msg+="["+id+"]编号服务器信息查询失败<br/>";
				continue;
			}
			if(sourceServerMsg!=null){//服务器不为空，执行密码生成操作
				if(sourceServerMsg.getSysStatus()==1){
					fail++;
					msg+="["+id+"]编号服务器已经激活<br/>";
					continue;
				}
				List<ServerPortPool> portPool =null;
				try {
					portPool = m1320ActiveDao.queryPortPool();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					fail++;
					msg+="["+id+"]端口信息查询失败<br/>";
					continue;
				}//查询端口池中提供的端口信息
				for(ServerPortPool port : portPool){
					SourceServerPortMsg sourceServerPortMsg = new SourceServerPortMsg();
					 UUID uuid = UUID.randomUUID();
					 sourceServerPortMsg.setSourceId(sourceServerMsg.getServerId());
					 sourceServerPortMsg.setSourceIp(sourceServerMsg.getIpAddress());
					 sourceServerPortMsg.setUseFlag(1);
					 sourceServerPortMsg.setTimeOut(300);
					 sourceServerPortMsg.setRunStatus(1);
					 sourceServerPortMsg.setPort(port.getPortValue());
					 sourceServerPortMsg.setPassword(uuid.toString());
					 sourceServerPortMsg.setUpdateDesc("服务器激活");
					 sourceServerPortMsg.setActiveTime(new Timestamp(System.currentTimeMillis()));
					 sourceServerPortList.add(sourceServerPortMsg);
				}
				boolean flag;
				try {
					flag = m1320ActiveDao.insertServerPortList(sourceServerPortList);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					fail++;
					msg+="["+id+"]端口信息入库失败<br/>";
					continue;
				}
				sourceServerMsg.setSysStatus(1);
				sourceServerMsg.setActiveTimestamp(new Timestamp(System.currentTimeMillis()));
				sourceServerMsg.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
				sourceServerMsg.setUpdateBy(loginNo);
				sourceServerMsg.setNote("["+loginNo+"]对该服务器进行激活操作");
				try {
					flag = m1320ActiveDao.updateServerMsg(sourceServerMsg);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					fail++;
					msg+="["+id+"]服务器信息更新失败<br/>";
					continue;
				}
				
				if(flag){
					serverUpdateList.add(sourceServerMsg);
					success++;
				}else{
					fail++;
					msg+="【"+id+"】端口信息入库失败<br/>";
				}
			}else{
				fail++;
				msg+="【"+id+"】对应的服务器信息为空<br/>";
			}
		}
		return new String[]{success+"",fail+"",msg,JSON.toJSONString(serverUpdateList)};
	}

	public M1320ActiveDao getM1320ActiveDao() {
		return m1320ActiveDao;
	}

	public void setM1320ActiveDao(M1320ActiveDao m1320ActiveDao) {
		this.m1320ActiveDao = m1320ActiveDao;
	}
	public static void main(String[] args){
		 UUID uuid = UUID.randomUUID();
		 System.out.println(uuid.toString());
	}
	
	
	
}
