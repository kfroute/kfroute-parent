package cn.melinkr.kfrouteWeb.business.m1320.service;

import java.util.Map;

import cn.melinkr.platform.kfroute.RouterMsg;
import cn.melinkr.platform.kfroute.SourceServerMsg;
import cn.melinkr.platform.kfroute.SourceServerMsgHis;

import com.melinkr.micro.exception.ServiceException;

public interface M1320Service{
	public boolean doServerFileImp(String fileName,String opNote) throws ServiceException;
	public Map queryServer(Map queryMap,Integer page,Integer pageSize) throws ServiceException;
	public Map queryServerPortDetail(Map queryMap,Integer page,Integer pageSize) throws ServiceException;
	public boolean addServer(SourceServerMsg sourceServerMsg) throws ServiceException;//添加服务器到数据库
	
	public SourceServerMsg queryServer(SourceServerMsg serverMsg) throws ServiceException;//查询服务器信息
	public boolean updateServer(SourceServerMsg serverMsg) throws ServiceException;//修改服务器信息
	public boolean delServer(SourceServerMsg serverMsg) throws ServiceException;//删除服务器信息
	
	public boolean addServerHis(SourceServerMsgHis serverMsgHis) throws ServiceException;//通过SourceServerMsgHis对象保存历史信息
	public boolean addServerHisByMap(Map addParamMap) throws ServiceException;//通过map保存历史信息，原数据从正表中获取
	

}
