package cn.melinkr.platform.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.melinkr.plateform.busi.systemconfig.service.inf.SystemConfigService;
import cn.melinkr.platform.kfroute.SystemConfig;

import com.melinkr.micro.util.SpringHelper;
import com.melinkr.micro.util.SystemConfigUtil;


public class ContextListener implements ServletContextListener {

	private static final Logger logger = Logger
			.getLogger(ContextListener.class);

	private ServletContext servletContext;

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent event) {
		servletContext = event.getServletContext();
		//初始化spring
		this.initSpringHelper(event);
		// 初始化数据库系统参数
		initSystemConfig();
		// 初始化全局配置
		initServletConfig();
	}

	/**
	 * 初始化应用级别参数
	 */
	private void initServletConfig() {
		// 全局保存资源路径
		SystemConfig systemConfig = SystemConfigUtil.getSingleProperty("resourcePath_default");
		String resourcePath=systemConfig==null?"/":systemConfig.getPropertyValue();
		//String resourcePath="http://127.0.0.1/payment";
		servletContext.setAttribute("resourcePath", resourcePath);
	}

	/**
	 * 初始化springHelper <功能详细描述>
	 * 
	 * @param context
	 * @see [类、类#方法、类#成员]
	 */
	public void initSpringHelper(ServletContextEvent event) {
		ApplicationContext springContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(event.getServletContext());
		SpringHelper.getSpringHelper().initApplicationContext(springContext);
	}
	/**
	 * 初始化数据库系统参数
	 */
	private void initSystemConfig() {
		SystemConfigService systemConfigService = (SystemConfigService) SpringHelper
				.getSpringHelper().getBean("systemConfigService");
		// 获取所有的系统配置
		List<SystemConfig> systemConfigList = systemConfigService
				.getAllSystemConfig();
		Map<String, List<SystemConfig>> systemConfigMap = SystemConfigUtil
				.getSystemConfigMap();
		for (SystemConfig systemConfig : systemConfigList) {
			String key = systemConfig.getPropertyKey();
			List<SystemConfig> list = systemConfigMap.get(key);
			if (list == null) {
				list = new ArrayList<SystemConfig>();
				systemConfigMap.put(key, list);
			}
			list.add(systemConfig);
		}
		SystemConfigUtil.setSystemConfigMap(systemConfigMap);
	}

}
