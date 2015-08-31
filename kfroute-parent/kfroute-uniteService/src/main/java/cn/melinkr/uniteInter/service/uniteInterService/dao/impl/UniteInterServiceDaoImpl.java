package cn.melinkr.uniteInter.service.uniteInterService.dao.impl;

import java.util.List;

import cn.melinkr.platform.unite.UniteInterServiceEntity;
import cn.melinkr.uniteInter.service.uniteInterService.dao.inf.UniteInterServiceDao;

import com.tydic.framework.base.dao.EntityManagerImpl;

public class UniteInterServiceDaoImpl extends EntityManagerImpl<UniteInterServiceEntity, Integer> implements UniteInterServiceDao{

	@Override
	public List<UniteInterServiceEntity> findAll() {
		return entityDao.findAll("pubInterService.query");
	}
	
}
