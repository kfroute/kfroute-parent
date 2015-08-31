package cn.melinkr.kfrouteWeb.business.m1310.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;

import cn.melinkr.platform.kfroute.RouterMsg;
import cn.melinkr.platform.kfroute.RouterMsgHis;

import com.melinkr.micro.auth.entity.ChnGroupMsg;
import com.melinkr.micro.exception.ServiceException;

public interface M1310Dao{
	public boolean impDeviceFileToDb(List<RouterMsg> routerMsgList) throws SQLException;
	public Map queryDevice(Map queryMap,Integer page,Integer pageSize) throws SQLException;
	public ChnGroupMsg findGroupIdByName(String groupName) throws SQLException;
	public boolean addDevice(RouterMsg routerMsg) throws SQLException;
	public boolean updateDevice(RouterMsg routerMsg) throws SQLException;
	public boolean updateBatchDevice(Map updateParam) throws SQLException;//批量修改设备数据
	public RouterMsg queryDevice(RouterMsg routerMsg) throws SQLException;
	public boolean addDeviceHis(RouterMsgHis routerMsgHis) throws SQLException;
	public boolean addDeviceBatchHis(List<RouterMsgHis> routerMsgHisList) throws SQLException;
	public boolean addDeviceHisByMap(Map addParamMap) throws SQLException;
	public boolean addDeviceBatchHisByMap(List<Map> addParamsList) throws SQLException;
	public boolean delDevice(RouterMsg routerMsg) throws SQLException;
	public List<RouterMsg> queryDeviceByIdList(@SuppressWarnings("rawtypes") Map queryMap)throws SQLException;
	public boolean addDeviceHisByIdList(Map addParamMap) throws SQLException;
}
