package cn.melinkr.kfrouteWeb.business.m1320.service;

import com.melinkr.micro.exception.ServiceException;

public interface M1320ActiveService{
	public String[] doActiveServer(String idList,String loginNo) throws ServiceException;
}
