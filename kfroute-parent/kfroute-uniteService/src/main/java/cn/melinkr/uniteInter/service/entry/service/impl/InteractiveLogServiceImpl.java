package cn.melinkr.uniteInter.service.entry.service.impl;

import java.util.List;

import cn.melinkr.platform.unite.InteractiveLog;
import cn.melinkr.uniteInter.service.entry.dao.inf.InteractiveLogDao;
import cn.melinkr.uniteInter.service.entry.service.inf.InteractiveLogService;

public class InteractiveLogServiceImpl implements InteractiveLogService {
	private InteractiveLogDao interactiveLogDao;
	
	@Override
	public List<InteractiveLog> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InteractiveLog find(InteractiveLog iLog) {
		return interactiveLogDao.find(iLog);
	}

	@Override
	public void add(InteractiveLog iLog) {
		interactiveLogDao.add(iLog);

	}

	@Override
	public void update(InteractiveLog iLog) {
		interactiveLogDao.update(iLog);

	}

	@Override
	public void delete(InteractiveLog iLog) {
		// TODO Auto-generated method stub

	}

	public InteractiveLogDao getInteractiveLogDao() {
		return interactiveLogDao;
	}

	public void setInteractiveLogDao(InteractiveLogDao interactiveLogDao) {
		this.interactiveLogDao = interactiveLogDao;
	}

}
