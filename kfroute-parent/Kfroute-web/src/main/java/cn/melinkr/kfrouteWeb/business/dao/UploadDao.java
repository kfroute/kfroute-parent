package cn.melinkr.kfrouteWeb.business.dao;

import com.melinkr.micro.auth.entity.ChnGroupMsg;

public interface UploadDao{
	public  Object findRouterMsgByMac(String mac) throws Exception;
	public  Object findSourceServerMsgByIp(String ip) throws Exception;
	public ChnGroupMsg findGroupIdByName(String groupName) throws Exception;
}
