package cn.melinkr.kfrouteWeb.business.m1320.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.melinkr.kfrouteWeb.business.m1320.dao.M1320ActiveDao;
import cn.melinkr.platform.kfroute.ServerPortPool;
import cn.melinkr.platform.kfroute.SourceServerMsg;
import cn.melinkr.platform.kfroute.SourceServerPortMsg;

import com.melinkr.micro.dao.common.BaseDao;

/**
 * @author: joki
 * @time: 2015-08-07 16:25
 * @version: 1.0
 * 服务器激活数据库操作模块
 *
 */
public class M1320ActiveDaoImpl extends BaseDao  implements M1320ActiveDao{
	private static final Logger logger = Logger
		.getLogger(M1320ActiveDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public SourceServerMsg queryServerById(int id) throws SQLException{
		// TODO Auto-generated method stub
		Map queryMap = new HashMap();
		
		SourceServerMsg _souceServerMsg = new SourceServerMsg();
		_souceServerMsg.setServerId(id);
		queryMap.put("serverMsg", _souceServerMsg);
		SourceServerMsg souceServerMsg = (SourceServerMsg)this.queryForObject("serverManage.findServer",queryMap);
		return souceServerMsg;
	}

	@Override
	public List<ServerPortPool> queryPortPool() throws SQLException {
		// TODO Auto-generated method stub
		List<ServerPortPool> poolList= (List<ServerPortPool>)this.queryForList("serverManage.findPortList");
		return poolList;
	}

	@Override
	public boolean insertServerPortList(List<SourceServerPortMsg> sourceServerPortList) throws SQLException {
		// TODO Auto-generated method stub
		this.batchInsert("serverManage.insertServerPortList", sourceServerPortList);
		return true;
	}

	@Override
	public boolean updateServerMsg(SourceServerMsg sourceServerMsg)
			throws SQLException{
		// TODO Auto-generated method stub
		int ret = this.update("serverManage.updateServerMsg", sourceServerMsg);
		return ret>0;
	}
	

}
