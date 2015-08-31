package cn.melinkr.kfrouteWeb.business.m1310.service;

import java.util.List;
import java.util.Map;

import cn.melinkr.platform.kfroute.RouterMsg;
import cn.melinkr.platform.kfroute.RouterMsgHis;

import com.melinkr.micro.exception.ServiceException;

public interface M1310Service{
	public boolean doDeviceFileImp(String fileName,String opNote) throws ServiceException;
	public Map queryDevice(Map queryMap,Integer page,Integer pageSize) throws ServiceException;
	public RouterMsg queryDevice(RouterMsg routerMsg) throws ServiceException;
	public boolean addDevice(RouterMsg routerMsg) throws ServiceException;
	public boolean updateDevice(RouterMsg routerMsg) throws ServiceException;
	public boolean updateBatchDevice(Map updateParam) throws ServiceException;//批量修改设备数据
	public boolean addDeviceHis(RouterMsgHis routerMsgHis) throws ServiceException;
	public boolean addDeviceBatchHis(List<RouterMsgHis> routerMsgHisList) throws ServiceException;
	public boolean addDeviceHisByMap(Map addParamMap) throws ServiceException;
	public boolean addDeviceBatchHisByMap(List<Map> addParamsList) throws ServiceException;
	public boolean delDevice(RouterMsg routerMsg) throws ServiceException;
	public Map queryDeviceByIdList(Map queryMap)throws ServiceException;
	public boolean addDeviceHisByIdList(Map addParamMap) throws ServiceException;
}
