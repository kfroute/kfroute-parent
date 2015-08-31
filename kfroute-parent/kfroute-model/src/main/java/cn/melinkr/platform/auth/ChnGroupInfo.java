package cn.melinkr.platform.auth;


/**
 * 
 * @author: zhangyl
 * @time: 2015-07-13 16:28
 * @version: 1.0
 * @copyright:melinkr
 * 组织渠道关系
 */

public class ChnGroupInfo  extends BaseEntity{
	private String groupId;//GROUP_ID
	private String parentGroupId;//PARENT_GROUP_ID
	private String denormLevel;//DENORM_LEVEL
	private String parentLevel;//PARENT_LEVEL
	private String currentLevel;//CURRENT_LEVEL
	

	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	

	public String getParentGroupId() {
		return parentGroupId;
	}
	public void setParentGroupId(String parentGroupId) {
		this.parentGroupId = parentGroupId;
	}
	

	public String getDenormLevel() {
		return denormLevel;
	}
	public void setDenormLevel(String denormLevel) {
		this.denormLevel = denormLevel;
	}
	
	public String getParentLevel() {
		return parentLevel;
	}
	public void setParentLevel(String parentLevel) {
		this.parentLevel = parentLevel;
	}
	
	public String getCurrentLevel() {
		return currentLevel;
	}
	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}
	@Override
	public String toString() {
		return "ChnGroupInfo [groupId=" + groupId + ", parentGroupId="
				+ parentGroupId + ", denormLevel=" + denormLevel
				+ ", parentLevel=" + parentLevel + ", currentLevel="
				+ currentLevel + "]";
	}
	
	

	
	
}
