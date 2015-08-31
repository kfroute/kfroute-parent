package cn.melinkr.uniteInter.service.entry.service.inf;

import java.util.List;

import cn.melinkr.platform.unite.InteractiveLog;

public interface InteractiveLogService {
	public List<InteractiveLog> findAll();

	public InteractiveLog find(InteractiveLog iLog);

	public void add(InteractiveLog iLog);

	public void update(InteractiveLog iLog);

	public void delete(InteractiveLog iLog);
}
