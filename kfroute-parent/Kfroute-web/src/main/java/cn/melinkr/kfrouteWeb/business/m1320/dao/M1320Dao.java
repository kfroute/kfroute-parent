package cn.melinkr.kfrouteWeb.business.m1320.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;

import com.melinkr.micro.exception.ServiceException;

import cn.melinkr.platform.kfroute.RouterMsg;
import cn.melinkr.platform.kfroute.ServerBelongGroup;
import cn.melinkr.platform.kfroute.SourceServerMsg;
import cn.melinkr.platform.kfroute.SourceServerMsgHis;

public interface M1320Dao{
	public boolean impServerFileToDb(List<SourceServerMsg> sourceServerList) throws SQLException;
	public Map queryServer(Map queryMap,Integer page,Integer pageSize) throws SQLException;
	public Map queryServerPortDetail(Map queryMap,Integer page,Integer pageSize) throws SQLException;
	public boolean addServer(SourceServerMsg sourceServerMsg) throws SQLException;
	
	public SourceServerMsg queryServer(SourceServerMsg serverMsg) throws SQLException;//查询服务器信息
	public boolean updateServer(SourceServerMsg serverMsg) throws SQLException;//修改服务器信息
	public boolean delServer(SourceServerMsg serverMsg) throws SQLException;//删除服务器信息
	
	public boolean addServerHis(SourceServerMsgHis serverMsgHis) throws SQLException;//通过SourceServerMsgHis对象保存历史信息
	public boolean addServerHisByMap(Map addParamMap) throws SQLException;//通过map保存历史信息，原数据从正表中获取
	
	public List<ServerBelongGroup> queryBelongGroup() throws SQLException;//查询服务器信息
}
