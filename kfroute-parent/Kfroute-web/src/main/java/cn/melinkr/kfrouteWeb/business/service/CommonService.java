package cn.melinkr.kfrouteWeb.business.service;

import java.util.List;

import cn.melinkr.platform.kfroute.SelectFormBean;

import com.melinkr.micro.exception.ServiceException;

public interface CommonService{
	public List<SelectFormBean> queryAgentSelection(String loginNo,int loginType) throws ServiceException;
	public List<SelectFormBean> queryServerGroupSelection() throws ServiceException;
}
