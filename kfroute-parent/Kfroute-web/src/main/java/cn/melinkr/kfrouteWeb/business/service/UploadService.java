package cn.melinkr.kfrouteWeb.business.service;

import cn.melinkr.platform.kfroute.RouterMsg;

public interface UploadService{
	public int[] checkM1310UploadFile(String fileName) throws Exception;
	public int[] checkM1320UploadFile(String fileName) throws Exception;
	public  RouterMsg findRouterMsgByMac(String mac) throws Exception;
}
