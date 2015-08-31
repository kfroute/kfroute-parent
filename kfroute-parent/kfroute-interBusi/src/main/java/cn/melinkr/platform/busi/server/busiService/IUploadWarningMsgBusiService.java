package cn.melinkr.platform.busi.server.busiService;

import java.util.Map;

import cn.melinkr.platform.kfroute.WarningMsg;

import com.melinkr.micro.exception.ServiceException;

public interface IUploadWarningMsgBusiService {
	public boolean insertWarningMsg(WarningMsg warningMsg) throws ServiceException;
}
