package cn.melinkr.platform.auth;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 * @author: zhangyl
 * @time: 2015-07-11 17:56
 * @version: 1.0
 * @copyright:melinkr
 *
 */

public class LoginMsg extends BaseEntity implements Serializable{

	private String loginNo;
	private String realName;
	private String password;
	private String salt;
	private String sex;
	private String phone;
	private String email;
	private boolean locked;
	private String creator;
	private Date createTime;
	private String updator;
	private Timestamp updateTime;
	private int powerRight;
	private String groupId;
	
	
	public String getLoginNo() {
		return loginNo;
	}

	public void setLoginNo(String loginNo) {
		this.loginNo = loginNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSex() {
		return sex;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}


	public int getPowerRight() {
		return powerRight;
	}

	public void setPowerRight(int powerRight) {
		this.powerRight = powerRight;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	//提供密码加盐的方式进行MD5加密
	public String getCredentialsSalt() {
	        return loginNo + salt;
	    }
	@Override
	public String toString() {
		return "LoginMsg [loginNo=" + loginNo
				+ ", realName=" + realName + ", password=" + password
				+ ", salt=" + salt + ", sex=" + sex + ", phone=" + phone
				+ ", email=" + email + ", locked=" + locked + ", creator="
				+ creator + ", createTime=" + createTime + ", updator="
				+ updator + ", updateTime=" + updateTime + ", powerRight="
				+ powerRight + ", groupId=" + groupId + "]";
	}
	
	

}
