package cn.melinkr.platform.busi.server.busiService;

import java.util.Map;

import cn.melinkr.platform.kfroute.ServerShadowSocksBean;

import com.melinkr.micro.exception.ServiceException;

public interface IInitServerPortMsgBusiService {
	public ServerShadowSocksBean queryServerPortMsg(Map paramMap) throws ServiceException;

}
