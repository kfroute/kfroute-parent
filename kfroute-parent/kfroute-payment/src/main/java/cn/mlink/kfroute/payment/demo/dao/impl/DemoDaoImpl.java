package cn.kfroute.platform.payment.demo.dao.impl;

import java.util.List;

import cn.kfroute.platform.payment.demo.dao.inf.DemoDao;
import cn.seocoo.platform.wifi.Demo;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class DemoDaoImpl extends EntityManagerImpl<Demo, Integer> implements DemoDao{

	@Override
	public List<Demo> findAll() {
		// TODO Auto-generated method stub
		 return entityDao.findAll("demo.query");
	}

}
