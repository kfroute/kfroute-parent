package cn.melinkr.uniteInter.service.uniteInterService.action;

import java.util.List;

import cn.melinkr.platform.unite.UniteInterServiceEntity;
import cn.melinkr.uniteInter.service.uniteInterService.service.inf.UniteInterService;

public class UniteInterServiceAction {
	private UniteInterService uniteInterService;
	private List<UniteInterServiceEntity> uniteInterServiceEntityList;
	
	public String uniteInterService(){
		uniteInterServiceEntityList=uniteInterService.findAll();
		System.out.println("11");
		return "findAllsuccess";
	}

	public UniteInterService getUniteInterService() {
		return uniteInterService;
	}

	public void setUniteInterService(UniteInterService uniteInterService) {
		this.uniteInterService = uniteInterService;
	}

	public List<UniteInterServiceEntity> getUniteInterServiceList() {
		return uniteInterServiceEntityList;
	}

	public void setSeocooServiceList(List<UniteInterServiceEntity> uniteInterServiceEntityList) {
		this.uniteInterServiceEntityList = uniteInterServiceEntityList;
	}
	
	
	
}
