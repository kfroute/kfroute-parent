package cn.melinkr.kfrouteWeb.business.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.melinkr.kfrouteWeb.business.dao.CommonDao;
import cn.melinkr.platform.kfroute.SelectFormBean;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class CommonDaoImpl extends EntityManagerImpl<SelectFormBean, Integer>  implements CommonDao{
	private static final Logger logger = Logger
		.getLogger(CommonDaoImpl.class);
	
	@Override
	public List<SelectFormBean> findAgentMap(String loginNo) throws SQLException {
		// TODO Auto-generated method stub
		Map queryMap = new HashMap();
		queryMap.put("loginNo", loginNo);
		return entityDao.find("routerCommon.findAgent",queryMap);
	}

	@Override
	public List<SelectFormBean> findAllAgentMap() throws SQLException {
		// TODO Auto-generated method stub
		return entityDao.findAll("routerCommon.findAllAgent");
	}

	@Override
	public List<SelectFormBean> queryServerGroupSelection() throws SQLException {
		// TODO Auto-generated method stub
		return entityDao.findAll("routerCommon.findAllServerGroup");
	}

}
