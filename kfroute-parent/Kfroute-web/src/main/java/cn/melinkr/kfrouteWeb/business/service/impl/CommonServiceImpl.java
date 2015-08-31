package cn.melinkr.kfrouteWeb.business.service.impl;

import java.util.List;

import cn.melinkr.kfrouteWeb.business.dao.CommonDao;
import cn.melinkr.kfrouteWeb.business.service.CommonService;
import cn.melinkr.platform.kfroute.SelectFormBean;

import com.melinkr.micro.auth.util.Constants;
import com.melinkr.micro.exception.ServiceException;

/**
 * 
 * @author zhangyl at 2015-08-18 18:13 公共查询服务
 * 
 */

public class CommonServiceImpl implements CommonService {

	private CommonDao commonDao;

	/**
	 * 查询代理商选择框
	 * 1. 超级管理员可以查看到所有代理商
	 * 2. 代理商可以查看其下面的所有代理商
	 * 3. 普通工号不能查看代理商
	 */
	@Override
	public List<SelectFormBean> queryAgentSelection(String loginNo,
			int loginType) throws ServiceException {
		// TODO Auto-generated method stub
		try{
			if(loginType <=Constants.ADMIN){//超级管理员可以查看到所有代理商
				return commonDao.findAllAgentMap();
			}else if(loginType ==Constants.AGENT){//代理商可以查看其下面的所有代理商
				return commonDao.findAgentMap(loginNo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public List<SelectFormBean> queryServerGroupSelection() throws ServiceException {
		// TODO Auto-generated method stub
		try{
			return commonDao.queryServerGroupSelection();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	
	
}
