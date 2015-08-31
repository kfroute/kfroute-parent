package cn.melinkr.platform.busi.server.busiService.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.melinkr.platform.busi.server.busiDao.IQueryAvailableServerIpDao;
import cn.melinkr.platform.busi.server.busiService.IQueryAvailableServerIpBusiService;
import cn.melinkr.platform.kfroute.ServerAvailableIp;

import com.melinkr.micro.exception.ServiceException;
import com.melinkr.micro.geoip.entity.GeoIpLocation;
import com.melinkr.micro.util.GeoIpUtil;
import com.melinkr.micro.util.LatiLongDistanceUtil;

public class QueryAvailableServerIpBusiServiceImpl implements
		IQueryAvailableServerIpBusiService {

	private IQueryAvailableServerIpDao queryAvailableServerIpDao;

	public IQueryAvailableServerIpDao getQueryAvailableServerIpDao() {
		return queryAvailableServerIpDao;
	}

	public void setQueryAvailableServerIpDao(
			IQueryAvailableServerIpDao queryAvailableServerIpDao) {
		this.queryAvailableServerIpDao = queryAvailableServerIpDao;
	}

	@Override
	public List<ServerAvailableIp> queryServerIpList(Map paramMap)
			throws ServiceException {
		// TODO Auto-generated method stub
		List<ServerAvailableIp> ipList = null;
		try {
			ipList = queryAvailableServerIpDao.queryServerIpList(paramMap);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			if (e1.getMessage().startsWith("FAIL-")) {// 如果数据层抛出可控制异常，直接抛到上一层
				throw new ServiceException(e1.getMessage());
			} else {
				throw new ServiceException("FAIL-009~查询服务器可用ip失败，详情：【"
						+ e1.getMessage() + "】");
			}
		}
		if (ipList == null || ipList.size() == 0) {
			throw new ServiceException("FAIL-010~没有可用的ip，系统发生严重异常");
		}
		List<ServerAvailableIp> returnIpList = new ArrayList<ServerAvailableIp>();
		List<ServerAvailableIp> returnSosIpList = new ArrayList<ServerAvailableIp>();
		Random random = new Random();
		int total = ipList.size();
		if (total < 5) {
			for (int i = 0; i < total; i++) {

				ServerAvailableIp serverAvailableIp = ipList.get(i);
				try {
					String routerip = (String) paramMap.get("routerIp");
					GeoIpLocation geoIpLocation = GeoIpUtil
							.getLocationBean(routerip);
					double routeLatitude = geoIpLocation.getLocation()
							.getLatitude();
					double routeLongitude = geoIpLocation.getLocation()
							.getLongitude();
					double ServerLatitude = serverAvailableIp.getLatitude();
					double ServerLongitude = serverAvailableIp.getLongitude();
					String distance = LatiLongDistanceUtil.getDistanceKm(
							routeLongitude, routeLatitude, ServerLongitude,
							ServerLatitude);
					serverAvailableIp.setDistance(distance);
					returnIpList.add(serverAvailableIp);
				} catch (Exception e) {
					e.printStackTrace();
					Map returnIpMap = new HashMap();
					if (serverAvailableIp.getBelongGroup() == 0) {// 美国分组
						returnIpMap.put("serverIp",
								serverAvailableIp.getServerIp());
						returnIpMap.put("distance", 0);// 当ip地址的经纬度算不出来的时候，取美国分组的任意服务器
						returnSosIpList.add(serverAvailableIp);
					}

				}
			}
		} else {// 超过5个
			for (int i = 0; i < 5; i++) {
				int ramdomField = ipList.size();
				int chooseId = random.nextInt(ramdomField);
				ServerAvailableIp serverAvailableIp = ipList.get(chooseId);
				try {
					String routerip = (String) paramMap.get("routerIp");
					GeoIpLocation geoIpLocation = GeoIpUtil
							.getLocationBean(routerip);
					double routeLatitude = geoIpLocation.getLocation()
							.getLatitude();
					double routeLongitude = geoIpLocation.getLocation()
							.getLongitude();
					double ServerLatitude = serverAvailableIp.getLatitude();
					double ServerLongitude = serverAvailableIp.getLongitude();
					String distance = LatiLongDistanceUtil.getDistanceKm(
							routeLongitude, routeLatitude, ServerLongitude,
							ServerLatitude);
					serverAvailableIp.setDistance(distance);
					returnIpList.add(serverAvailableIp);
					ipList.remove(chooseId);
				} catch (Exception e) {
					e.printStackTrace();
					Map returnIpMap = new HashMap();
					if (serverAvailableIp.getBelongGroup() == 0) {// 美国分组
						returnIpMap.put("serverIp",
								serverAvailableIp.getServerIp());
						returnIpMap.put("distance", 0);// 当ip地址的经纬度算不出来的时候，取美国分组的任意服务器
						returnSosIpList.add(serverAvailableIp);
					}

				}
			}
		}
		if (returnIpList.size() == 0) {// 如果没有找到ip，异常导致的情况
			returnIpList.add(returnSosIpList.get(random.nextInt(returnSosIpList
					.size())));
		}
		return returnIpList;
	}

}
