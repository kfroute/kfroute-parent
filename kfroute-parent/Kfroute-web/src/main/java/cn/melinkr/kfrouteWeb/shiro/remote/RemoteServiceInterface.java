package cn.melinkr.kfrouteWeb.shiro.remote;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.melinkr.micro.auth.entity.FuncCode;
import com.melinkr.micro.auth.entity.LoginMsg;
import com.melinkr.micro.auth.entity.Role;

public interface RemoteServiceInterface {
	 public LoginMsg findLoginMsgByLoginNo(String loginNo);
	 /**
	     * 查询用户被赋予的角色和用户常见的角色.
	     * @param loginNo
	     * @return
	     */
		public Collection<String> getRoleNames(String loginNo) throws Exception;
	    
		/**
		 * 查询用户可进行的操作
		 * @param loginNo
		 * @return
		 */
		public Collection<String> getPermissionNames(String loginNo)throws Exception ;


		public LoginMsg getShiroUser(String loginNo)throws Exception;
		public List<FuncCode> findAllPermissions(long userId)throws Exception;
		public List<FuncCode> findAllPermissions(String loginNo)throws Exception;
		public Set<String> findAllPermissionNames(long userId)throws Exception;
		public Set<String> findAllPermissionNames(String loginNo)throws Exception;
		public Set<String> findAllPermissionNames(String loginNo,FuncCode rootFunc)throws Exception;
		public List<FuncCode> findAllPermissions(String loginNo,FuncCode rootFunc)throws Exception;
		

		
		 /**
	     * 根据角色编号得到角色标识符列表
	     * @param roleIds
	     * @return
	     */
	    public Set<String> findRoleNames(String... roleIds)throws Exception;
	    public List<Role> findRoleList(String... roleIds)throws Exception;
	    
	    /**
	     * 根据用户查询角色信息
	     * @param LoginNo
	     * @return
	     */
	    public Set<String> findRoleNamesByLoginNo(String loginNo)throws Exception;
	    public List<Role> findByLoginNo(String loginNo)throws Exception;
	    
	    /**
	     * 查询主功能菜单
	     * @param loginNo
	     * @return
	     * @throws Exception
	     */
	    public List<FuncCode> findAllTitleFunc(String loginNo) throws Exception;
	    /**
	     * 查询一级功能菜单
	     * @param loginNo
	     * @return
	     * @throws Exception
	     */
	    public List<FuncCode> findAllFirstLevelFunc(String loginNo,FuncCode parentFunc) throws Exception;
	    
	    /**
	     * 根据功能名称查询功能详情
	     * @param functionName
	     * @return
	     * @throws Exception
	     */
	    public FuncCode findFuncCodeByFuncCode(String functionCode) throws Exception;
}
