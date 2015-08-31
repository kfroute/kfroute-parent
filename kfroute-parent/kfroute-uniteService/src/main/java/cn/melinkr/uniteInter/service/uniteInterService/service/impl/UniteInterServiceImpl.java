package cn.melinkr.uniteInter.service.uniteInterService.service.impl;

import java.util.List;

import cn.melinkr.platform.unite.UniteInterServiceEntity;
import cn.melinkr.uniteInter.service.uniteInterService.dao.inf.UniteInterServiceDao;
import cn.melinkr.uniteInter.service.uniteInterService.service.inf.UniteInterService;

public class UniteInterServiceImpl implements UniteInterService{
	private UniteInterServiceDao uniteInterServiceDao;

	@Override
	public List<UniteInterServiceEntity> findAll() {
		// TODO Auto-generated method stub
		return uniteInterServiceDao.findAll();
	}
	
	public UniteInterServiceDao getUniteInterServiceDao() {
		return uniteInterServiceDao;
	}
	
	public void setUniteInterServiceDao(UniteInterServiceDao uniteInterServiceDao) {
		this.uniteInterServiceDao = uniteInterServiceDao;
	}
	
	
}
