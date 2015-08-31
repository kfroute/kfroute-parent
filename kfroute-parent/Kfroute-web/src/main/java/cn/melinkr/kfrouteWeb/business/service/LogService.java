package cn.melinkr.kfrouteWeb.business.service;

import org.aspectj.lang.JoinPoint;

/**
 * @author: joki
 * @time: 2015-08-07 16:25
 * @version: 1.0
 * 日志记录服务接口
 *
 */
public interface LogService{
	 //无参的日志方法
    public void log();
    //有参的日志方法
    public void logArg(JoinPoint point);
    //有参有返回值的方法
    public void logArgAndReturn(JoinPoint point,Object returnObj);
}
