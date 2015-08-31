package cn.melinkr.kfrouteWeb.business.service.impl;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;

import cn.melinkr.kfrouteWeb.business.dao.LogDao;
import cn.melinkr.kfrouteWeb.business.service.LogService;
import cn.melinkr.platform.kfroute.LoginOpr;

import com.melinkr.micro.auth.entity.LoginMsg;
import com.melinkr.micro.auth.util.Constants;
import com.melinkr.micro.exception.ServiceException;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author: joki
 * @time: 2015-08-20 16:25
 * @version: 1.0 日志记录服务
 */

public class LogServiceImpl implements LogService {
	private LogDao logDao;

	@Override
	public void log() {
		System.out.println("*************Log*******************");
	}

	// 有参无返回值的方法
	public void logArg(JoinPoint point) {
		// 此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
		Object[] args = point.getArgs();
		System.out.println("目标参数列表：");
		if (args != null) {
			for (Object obj : args) {
				System.out.println(obj + ",");
			}
			System.out.println();
		}
	}

	// 有参并有返回值的方法
	public void logArgAndReturn(JoinPoint point, Object returnObj) {
		// 此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
		Map<String, Object> session = (Map<String, Object>) ActionContext
				.getContext().getSession();
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		// HttpServletResponse response = (HttpServletResponse)
		// ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		String methodName = point.getSignature().getName();
		String className = point.getTarget().toString();
		Object[] args = point.getArgs();
		String operName = "";
		LoginMsg loginMsg = null;
		int operType=0;
		loginMsg = (LoginMsg) request.getAttribute(Constants.CURRENT_USER);
		
		if (methodName.startsWith("add") || methodName.startsWith("save")
				|| methodName.startsWith("insert")) {
			operName = "增加";
			operType = 1;
		} else if (methodName.startsWith("update")) {
			operName = "修改";
			operType=3;
		} else if (methodName.startsWith("del")
				|| methodName.startsWith("remove")) {
			operName = "删除";
			operType = 2;
		} else {
			operName = "未定义";
		}
		String reqeustURL = request.getRequestURL().toString();
		String operCode = reqeustURL.substring(
				reqeustURL.indexOf(request.getContextPath())
						+ request.getContextPath().length()+1,
				reqeustURL.lastIndexOf("/"));
		String result = "成功";
		if (returnObj instanceof Boolean) {
			result = (Boolean) returnObj ? "成功" : "失败";
		} else {
			result = (returnObj == null) ? "失败" : "成功";
		}
		System.out.println(operCode);
		LoginOpr loginOpr = new LoginOpr();
		loginOpr.setOperCode(operCode);
		loginOpr.setOperIp(getRemoteAddress(request));
		loginOpr.setOperName(loginMsg.getLoginNo());
		loginOpr.setOperType(operType);
		loginOpr.setOperNote(loginMsg.getRealName() + "-对【" + operCode
				+ "】模块进行了【" + operName + "】操作，调用方法["+className+"." + methodName + "],操作结果："
				+ result);
		// System.out.println(loginMsg.toString());
		// System.out.println(methodName);
		// System.out.println("执行结果是：" + returnObj);
		try {
			logDao.saveOperLog(loginOpr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/** 从requet中获取IP */
	public String getRemoteAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public LogDao getLogDao() {
		return logDao;
	}

	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

}
