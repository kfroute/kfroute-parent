package cn.melinkr.kfrouteWeb.business.m1310.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.melinkr.kfrouteWeb.business.m1310.dao.M1310Dao;
import cn.melinkr.platform.kfroute.RouterMsg;
import cn.melinkr.platform.kfroute.RouterMsgHis;

import com.melinkr.micro.auth.entity.ChnGroupMsg;
import com.tydic.framework.base.dao.EntityManagerImpl;

public class M1310DaoImpl extends EntityManagerImpl<RouterMsg, Integer>  implements M1310Dao{
	private static final Logger logger = Logger
		.getLogger(M1310DaoImpl.class);
	@Override
	public boolean impDeviceFileToDb(List<RouterMsg> routerMsgList) throws SQLException  {
		// TODO Auto-generated method stub
		entityDao.saveBatch("routerManage.insertBatchDevice", routerMsgList.toArray());
		return true;
	}
	@Override
	public Map queryDevice(Map queryMap, Integer offset,
			Integer pageSize) throws SQLException {
		// TODO Auto-generated method stub
		queryMap.put("offset", offset);
		queryMap.put("pageSize", pageSize);
		Integer count = (Integer)entityDao.findMap("routerManage.findDeviceListCount",queryMap);
		Integer filterCount = (Integer)entityDao.findMap("routerManage.findDeviceListFilterCount",queryMap);
		List<RouterMsg> routerMsgList = entityDao.findBy("routerManage.findDeviceList",queryMap);
		Map result = new HashMap();
		result.put("total", count);
		result.put("filterTotal", filterCount);
		result.put("rows", routerMsgList);
		return result;
	}
	@Override
	public ChnGroupMsg findGroupIdByName(String groupName) throws SQLException {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("groupName", groupName);
		return (ChnGroupMsg)entityDao.findMap("routerManage.findGroupIdByName", map);
	}
	@Override
	public boolean addDevice(RouterMsg routerMsg) throws SQLException{
		// TODO Auto-generated method stub
		entityDao.save("routerManage.insertBatchDevice", routerMsg);
		return true;
	}
	@Override
	public boolean addDeviceHis(RouterMsgHis routerMsgHis) throws SQLException{
		// TODO Auto-generated method stub
		entityDao.save("routerManage.insertDeviceHis", routerMsgHis);
		return true;
	}
	@Override
	public boolean addDeviceBatchHis(List<RouterMsgHis> routerMsgHisList) throws SQLException{
		// TODO Auto-generated method stub
		entityDao.saveBatch("routerManage.insertDeviceHis", routerMsgHisList.toArray());
		return true;
	}
	@Override
	public RouterMsg queryDevice(RouterMsg routerMsg) throws SQLException {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("routerMsg", routerMsg);
		return entityDao.findObj("routerManage.findDevice", map);
	}
	@Override
	public boolean updateDevice(RouterMsg routerMsg) throws SQLException {
		// TODO Auto-generated method stub
		entityDao.update("routerManage.updateDeviceMsg", routerMsg);
		return true;
	}
	@Override
	public boolean addDeviceHisByMap(Map addParamMap) throws SQLException {
		// TODO Auto-generated method stub
		entityDao.save("routerManage.insertDeviceHisByMap", addParamMap);
		return true;
	}
	@Override
	public boolean addDeviceBatchHisByMap(List<Map> addParamsList)
			throws SQLException {
		entityDao.saveBatch("routerManage.insertDeviceHisByMap", addParamsList.toArray());
		return true;
	}
	@Override
	public boolean delDevice(RouterMsg routerMsg) throws SQLException {
		// TODO Auto-generated method stub
		entityDao.update("routerManage.delDevice",routerMsg);
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<RouterMsg> queryDeviceByIdList(@SuppressWarnings("rawtypes") Map queryMap)
			throws SQLException {
		// TODO Auto-generated method stub
		return entityDao.find("routerManage.findDeviceByIdList", queryMap);
	}
	/**
	 * 通过id列表备份数据
	 */
	@Override
	public boolean addDeviceHisByIdList(Map addParamMap) throws SQLException {
		// TODO Auto-generated method stub
		entityDao.save("routerManage.insertDeviceHisByIdList",addParamMap);
		return true;
	}
	@Override
	public boolean updateBatchDevice(Map updateParam)
			throws SQLException {
		// TODO Auto-generated method stub
		 entityDao.update("routerManage.updateBatchDeviceMsg", updateParam);
		 return true;
	}

}
