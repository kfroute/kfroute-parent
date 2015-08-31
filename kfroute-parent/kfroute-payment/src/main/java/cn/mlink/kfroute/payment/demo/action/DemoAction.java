package cn.kfroute.platform.payment.demo.action;

import java.util.List;

import cn.kfroute.platform.payment.demo.service.inf.DemoService;
import cn.seocoo.platform.wifi.Demo;

public class DemoAction {
	private DemoService demoService;
	private List<Demo> demoList;
	
	public String demo(){
//		demoList=demoService.findAll();
//		System.out.println(SystemConfigUtil.getSingleProperty("test").getPropertyValue());
		System.out.println(2222);
		return "demo";
	}

	public DemoService getDemoService() {
		return demoService;
	}

	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}

	public List<Demo> getDemoList() {
		return demoList;
	}

	public void setDemoList(List<Demo> demoList) {
		this.demoList = demoList;
	}
	
	
}
