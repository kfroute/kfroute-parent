package cn.melinkr.kfrouteWeb.business.m1320.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.melinkr.kfrouteWeb.business.m1320.dao.M1320Dao;
import cn.melinkr.platform.kfroute.ServerBelongGroup;
import cn.melinkr.platform.kfroute.SourceServerMsg;
import cn.melinkr.platform.kfroute.SourceServerMsgHis;
import cn.melinkr.platform.kfroute.SourceServerPortMsg;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class M1320DaoImpl extends EntityManagerImpl<SourceServerMsg, Integer>  implements M1320Dao{
	private static final Logger logger = Logger
		.getLogger(M1320DaoImpl.class);
	@Override
	public boolean impServerFileToDb(List<SourceServerMsg> sourceServerList) throws SQLException {
		// TODO Auto-generated method stub
		entityDao.saveBatch("serverManage.insertBatchServer", sourceServerList.toArray());
		return true;
	}
	@Override
	public Map queryServer(Map queryMap, Integer offset,
			Integer pageSize) throws SQLException{
		// TODO Auto-generated method stub
		queryMap.put("offset", offset);
		queryMap.put("pageSize", pageSize);
		Integer count = (Integer)entityDao.findMap("serverManage.findServerListCount",queryMap);
		Integer filterCount = (Integer)entityDao.findMap("serverManage.findServerListFilterCount",queryMap);
		List<SourceServerMsg> souceServerMsgList = entityDao.findBy("serverManage.findServerList",queryMap);
		Map result = new HashMap();
		result.put("total", count);
		result.put("filterTotal", filterCount);
		result.put("rows", souceServerMsgList);
		return result;
	}
	@Override
	public Map queryServerPortDetail(Map queryMap, Integer offset,
			Integer pageSize) throws SQLException{
		// TODO Auto-generated method stub
		queryMap.put("offset", offset);
		queryMap.put("pageSize", pageSize);
		Integer count = (Integer)entityDao.findMap("serverManage.findServerPortListCount",queryMap);
		Integer filterCount = (Integer)entityDao.findMap("serverManage.findServerPortListFilterCount",queryMap);
		List<SourceServerPortMsg> souceServerPortMsgList = entityDao.find("serverManage.findServerPortList",queryMap);
		Map result = new HashMap();
		result.put("total", count);
		result.put("filterTotal", filterCount);
		result.put("rows", souceServerPortMsgList);
		return result;
	}
	@Override
	public boolean addServer(SourceServerMsg sourceServerMsg) throws SQLException{
		// TODO Auto-generated method stub
		entityDao.save("serverManage.insertBatchServer", sourceServerMsg);
		return true;
	}
	
	@Override
	public boolean addServerHis(SourceServerMsgHis serverMsgHis) throws SQLException{
		// TODO Auto-generated method stub
		entityDao.save("serverManage.insertServerHis", serverMsgHis);
		return true;
	}

	@Override
	public SourceServerMsg queryServer(SourceServerMsg serverMsg) throws SQLException {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("serverMsg", serverMsg);
		return entityDao.findObj("serverManage.findServer", map);
	}
	@Override
	public boolean updateServer(SourceServerMsg serverMsg) throws SQLException {
		// TODO Auto-generated method stub
		entityDao.update("serverManage.updateServerMsg", serverMsg);
		return true;
	}
	@Override
	public boolean addServerHisByMap(Map addParamMap) throws SQLException {
		// TODO Auto-generated method stub
		entityDao.save("serverManage.insertServerHisByMap", addParamMap);
		return true;
	}
	@Override
	public boolean delServer(SourceServerMsg serverMsg) throws SQLException {
		// TODO Auto-generated method stub
		entityDao.update("serverManage.delServer",serverMsg);
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ServerBelongGroup> queryBelongGroup() throws SQLException {
		// TODO Auto-generated method stub
		return entityDao.find("serverManage.findBelongGroup",null);
	}

}
