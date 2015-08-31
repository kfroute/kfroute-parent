package cn.melinkr.platform.busi.server.busiService;

import cn.melinkr.platform.kfroute.RouterShadowSocksBean;
import cn.melinkr.platform.kfroute.RouterUseMsg;

import com.melinkr.micro.exception.ServiceException;

public interface IUploadTestMsgAndGetServerPortBusiService {
	public RouterShadowSocksBean uploadTestMsgAndGetServerPort(RouterUseMsg routerUseMsg) throws ServiceException;
}
