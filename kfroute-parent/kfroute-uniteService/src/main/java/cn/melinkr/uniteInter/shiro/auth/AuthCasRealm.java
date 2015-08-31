package cn.melinkr.uniteInter.shiro.auth;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import cn.melinkr.uniteInter.shiro.remote.RemoteServiceInterface;

import com.melinkr.micro.auth.entity.LoginMsg;
import com.melinkr.micro.shiro.SsoCasRealm;

/**
 *  Realm充当了Shiro与应用安全数据间的“桥梁”或者“连接器”。
 *  也就是说，当对用户执行认证（登录）和授权（访问控制）验证时，
 *  Shiro会从应用配置的Realm中查找用户及其权限信息
 * @author hkj
 *
 */
public class AuthCasRealm extends SsoCasRealm {

	@Resource
	private RemoteServiceInterface remoteService;
    
	/**
	 * 查询用户拥有的角色
	 */
	protected Collection<String> getRoleNames(String loginNo) {
		Collection<String> roles = null;
		try {
			 roles =  remoteService.getRoleNames(loginNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roles;
	}
    /**
     * 查询用户可进行的操作
     */
	protected Collection<String> getPermissionNames(String loginNo) {
		Collection<String> permissionNames = null;
		try {
			permissionNames =  remoteService.getPermissionNames(loginNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return permissionNames;
	}

	/**
     * 获得当前用户的ShiroUser信息
     */
	protected LoginMsg getShiroUser(String loginNo) {
		LoginMsg loginMsg = null;
		try {
			loginMsg = remoteService.getShiroUser(loginNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginMsg;
	}
	@Override
	protected Set<String> findRoles(String loginNo) {
		// TODO Auto-generated method stub
		Set<String> roles = null;
		try {
			roles = remoteService.findRoleNamesByLoginNo(loginNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roles;
	}
	@Override
	protected Set<String> findPermissions(String loginNo) {
		// TODO Auto-generated method stub
		Set<String> permissions = null;
		try {
			permissions = remoteService.findAllPermissionNames(loginNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return permissions;
	}

	
}
