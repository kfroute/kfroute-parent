package cn.kfroute.platform.payment.systemconfig.action;

import java.util.List;

import cn.kfroute.platform.payment.systemconfig.service.inf.SystemConfigService;
import cn.seocoo.platform.wifi.SystemConfig;

public class SystemConfigAction {
	private SystemConfigService systemConfigService;
	private List<SystemConfig> systemConfigList;
	
	public String systemConfig(){
		systemConfigList=systemConfigService.getAllSystemConfig();
		System.out.println("2222");
		return "success";
	}
	
	public SystemConfigService getSystemConfigService(){
		return systemConfigService;
	}
	
	public void setSystemConfigService(SystemConfigService scs){
		this.systemConfigService=scs;
	}
	
	public List<SystemConfig> getSystemConfigList(){
		return systemConfigList;
	}
	
	public void setSystemConfigList(List<SystemConfig> list){
		this.systemConfigList=list;
	}
	
}
