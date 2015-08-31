/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：Latn.java <br>
 * 创建时间：2015-5-29-下午07:37:19 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.melinkr.platform.payment;

public class Latn {
	private Integer aiid;
	private Integer parentId;
	private Integer level;
	private Integer userCount;
	private String name;
	private String description;
	private String createdTime;
	
	public Integer getAiid() {
		return aiid;
	}
	public void setAiid(Integer aiid) {
		this.aiid = aiid;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	@Override
	public String toString() {
		return "Latn [aiid=" + aiid + ", parentId=" + parentId + ", level="
				+ level + ", userCount=" + userCount + ", name=" + name
				+ ", description=" + description + ", createdTime="
				+ createdTime + "]";
	}
	
	
}
