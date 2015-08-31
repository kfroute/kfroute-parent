package cn.melinkr.platform.auth;

import java.sql.Timestamp;
/**
 * 
 * @author: zhangyl
 * @time: 2015-07-14 16:56
 * @version: 1.0
 * @copyright:melinkr
 *
 */

public class Role  extends BaseEntity {
	private String roleId;
	private String roleName;
	private String creator;
	private Timestamp createTime;
	

	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Srolecode [roleId=" + roleId + ", roleName=" + roleName
				+ ", creator=" + creator + ", createTime=" + createTime + "]";
	}
	
	

}
