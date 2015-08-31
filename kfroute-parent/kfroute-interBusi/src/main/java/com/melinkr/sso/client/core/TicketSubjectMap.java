package com.melinkr.sso.client.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.subject.Subject;

/**
 * @author: zhangyl
 * @time: 2015-07-28 16:10
 * @version: 1.0
 * 存储ticket和subject的对象关系，统一退出的时候，调用获取subject实现退出，同时清除map中的数据
 */
public class TicketSubjectMap {
	private Map<String,Subject> _map = new HashMap<String,Subject>();
	public void addSubject(String ticket,Subject subject){
		_map.put(ticket, subject);
	}
	public void delSubject(String ticket,Subject subject){
		_map.remove(ticket);
	}
	public Subject getSubject(String ticket){
		return _map.get(ticket);
	}
}
