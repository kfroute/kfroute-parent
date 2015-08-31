package com.melinkr.micro.shiro;

import java.util.Collection;
import java.util.HashSet;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public abstract class AbstractDbRealm extends AuthorizingRealm {
	
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		String loginNo = shiroUser.getloginNo();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Collection<String> roleNames = getRoleNames(loginNo);
		if (roleNames == null) {
			roleNames = new HashSet<String>();
		}
		Collection<String> permissionNames = getPermissionNames(loginNo);
		if (permissionNames == null) {
			permissionNames = new HashSet<String>();
		}
		
		// 为什么没用info.addRoles?
		for (String roleName : roleNames) {
			info.addRole(roleName);
		}
		info.addStringPermissions(permissionNames);
		return info;
	}

	protected abstract Collection<String> getRoleNames(String loginNo);

	protected abstract Collection<String> getPermissionNames(String loginNo);

	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		try {
			if (authcToken instanceof UsernamePasswordToken) {
				UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
				String loginNo = token.getUsername();
				ShiroUser su = getShiroUser(loginNo);
				return new SimpleAuthenticationInfo(su, getPassword(loginNo),
						getName());
			}
		} catch (Exception e) {
			throw new AuthenticationException(e);
		}
		return null;
	}

	protected abstract String getPassword(String loginNo);

	protected abstract ShiroUser getShiroUser(String loginNo);

}
