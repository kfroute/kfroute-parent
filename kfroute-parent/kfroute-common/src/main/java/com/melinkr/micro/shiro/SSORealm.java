package com.melinkr.micro.shiro;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import cn.melinkr.platform.auth.LoginMsg;

import com.alibaba.fastjson.JSON;
import com.melinkr.micro.auth.service.AuthRemoteService;
import com.melinkr.micro.util.HttpUtils;

public class SSORealm extends AuthorizingRealm {
	
	private AuthRemoteService authRemoteService;
	
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		LoginMsg loginMsg = (LoginMsg) principals.getPrimaryPrincipal();
		String loginNo = loginMsg.getLoginNo();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Collection<String> roleNames = getRoleNames(loginNo);
		if (roleNames == null) {
			roleNames = new HashSet<String>();
		}
		Collection<String> permissionNames = getPermissionNames(loginNo);
		if (permissionNames == null) {
			permissionNames = new HashSet<String>();
		}
		for (String roleName : roleNames) {
			info.addRole(roleName);
		}
		info.addStringPermissions(permissionNames);
		return info;
	}

	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		if (token instanceof UsernamePasswordToken) {
			UsernamePasswordToken upt = (UsernamePasswordToken) token;
			char[] pwd = upt.getPassword();
			String uname = upt.getUsername();
			Map<String, String> params = new HashMap<String, String>();
			params.put("ticket", uname);
			String responseText = rest(authRemoteService.getAuthUrl()
					+ "/anon/getUserByTicket", params);
			if (StringUtils.hasText(responseText)) {
				ShiroUser su = JSON.parseObject(responseText, ShiroUser.class);
				if (su != null) {
					return new SimpleAuthenticationInfo(su, pwd, getName());
				}
			}
		}
		return null;
	}

	protected Collection<String> getRoleNames(String loginNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("loginNo", loginNo);
		Collection<String> permissions = new HashSet<String>();
		String data = rest(authRemoteService.getAuthUrl() + "/anon/getRoleNames", params);
		String[] array = JSON.parseObject(data, String[].class);
		for (int i = 0; array != null && i < array.length; i++) {
			String role = array[i];
			if(StringUtils.hasText(role)){
				permissions.add(role);
			}
		}
		return permissions;
	}

	protected Collection<String> getPermissionNames(String loginNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("loginNo", loginNo);
		Collection<String> permissions = new HashSet<String>();
		String data = rest(authRemoteService.getAuthUrl() + "/anon/getPermissionNames", params);
		String[] array = JSON.parseObject(data, String[].class);
		for (int i = 0; array != null && i < array.length; i++) {
			String p = array[i];
			if(StringUtils.hasText(p)){
				permissions.add(p);
			}
		}
		return permissions;
	}

	private String rest(String url, Map<String, String> params) {
		try {
			return new String(HttpUtils.post(url, params), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public AuthRemoteService getAuthRemoteService() {
		return authRemoteService;
	}

	public void setAuthRemoteService(AuthRemoteService authRemoteService) {
		this.authRemoteService = authRemoteService;
	}

}
