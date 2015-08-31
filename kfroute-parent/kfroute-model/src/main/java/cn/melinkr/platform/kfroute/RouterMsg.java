package cn.melinkr.platform.kfroute;

import java.sql.Timestamp;

import cn.melinkr.platform.util.TimestampFormatUtil;

public class RouterMsg {
	private int rIdNo;
	private String mac;
	private String modelType;
	private String version;
	private String groupId;
	private String groupName;
	private int belongType;
	private String chipModel;
	private String basicFreq;
	private String ram;
	private String flash;
	private String brandName;
	private Timestamp updateTimestamp;
	private String updateObj;
	private Timestamp createTimestamp;
	private String createBy;
	private String createAccept;
	private String note;
	private String opNote;
	   
	
	public int getRIdNo() {
		return rIdNo;
	}


	public void setRIdNo(int rIdNo) {
		this.rIdNo = rIdNo;
	}


	public String getMac() {
		return mac;
	}


	public void setMac(String mac) {
		this.mac = mac;
	}


	public String getModelType() {
		return modelType;
	}


	public void setModelType(String modelType) {
		this.modelType = modelType;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	



	
	public String getGroupId() {
		return groupId;
	}


	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}


	public int getBelongType() {
		return belongType;
	}


	public void setBelongType(int belongType) {
		this.belongType = belongType;
	}


	public String getChipModel() {
		return chipModel;
	}


	public void setChipModel(String chipModel) {
		this.chipModel = chipModel;
	}


	public String getBasicFreq() {
		return basicFreq;
	}


	public void setBasicFreq(String basicFreq) {
		this.basicFreq = basicFreq;
	}


	public String getRam() {
		return ram;
	}


	public void setRam(String ram) {
		this.ram = ram;
	}


	public String getFlash() {
		return flash;
	}


	public void setFlash(String flash) {
		this.flash = flash;
	}


	public String getBrandName() {
		return brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}





	public String getUpdateTimestamp() {
		return TimestampFormatUtil.format(updateTimestamp);//updateTimestamp==null?null:updateTimestamp.toString();
	}


	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}


	public String getUpdateObj() {
		return updateObj;
	}


	public void setUpdateObj(String updateObj) {
		this.updateObj = updateObj;
	}


	public String getCreateTimestamp() {
		return TimestampFormatUtil.format(createTimestamp);//createTimestamp==null?null:createTimestamp.toString();
	}


	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}


	public String getCreateAccept() {
		return createAccept;
	}


	public void setCreateAccept(String createAccept) {
		this.createAccept = createAccept;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}
	
	

	public String getOpNote() {
		return opNote;
	}


	public void setOpNote(String opNote) {
		this.opNote = opNote;
	}


	public int getrIdNo() {
		return rIdNo;
	}


	public void setrIdNo(int rIdNo) {
		this.rIdNo = rIdNo;
	}
	

	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getCreateBy() {
		return createBy;
	}


	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}


	@Override
	public String toString() {
		return "RouterMsg [rIdNo=" + rIdNo + ", mac=" + mac + ", modelType="
				+ modelType + ", version=" + version + ", groupId=" + groupId
				+ ", groupName=" + groupName + ", belongType=" + belongType
				+ ", chipModel=" + chipModel + ", basicFreq=" + basicFreq
				+ ", ram=" + ram + ", flash=" + flash + ", brandName="
				+ brandName + ", updateTimestamp=" + updateTimestamp
				+ ", updateObj=" + updateObj + ", createTimestamp="
				+ createTimestamp + ", createBy=" + createBy
				+ ", createAccept=" + createAccept + ", note=" + note
				+ ", opNote=" + opNote + "]";
	}

}
