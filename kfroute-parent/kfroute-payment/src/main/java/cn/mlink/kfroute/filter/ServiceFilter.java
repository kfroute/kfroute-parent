package cn.kfroute.platform.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.subject.WebSubject;

import com.odchina.micro.shiro.ShiroUser;
import com.odchina.micro.util.SystemConfigUtil;

public class ServiceFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	public static ShiroUser queryCurrentShiroUser() {
		//ThreadContext.bind(SecurityUtils.getSubject()); 
		try {
		WebSubject ws = (WebSubject) SecurityUtils.getSubject();
		return (ShiroUser) ws.getPrincipal();
		} catch (Exception e) {
		return null;
		}
	}
}
