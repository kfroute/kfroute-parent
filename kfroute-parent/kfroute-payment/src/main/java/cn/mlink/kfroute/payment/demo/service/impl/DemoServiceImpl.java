package cn.kfroute.platform.payment.demo.service.impl;

import java.util.List;

import cn.kfroute.platform.payment.demo.dao.inf.DemoDao;
import cn.kfroute.platform.payment.demo.service.inf.DemoService;
import cn.seocoo.platform.wifi.Demo;

public class DemoServiceImpl implements DemoService {
	private DemoDao demoDao;

	@Override
	public List<Demo> findAll() {
		// TODO Auto-generated method stub
		return demoDao.findAll();
	}

	public DemoDao getDemoDao() {
		return demoDao;
	}

	public void setDemoDao(DemoDao demoDao) {
		this.demoDao = demoDao;
	}

}
