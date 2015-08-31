package com.melinkr.micro.enums;

/**上传管理功能使用*/
public enum FileType {
	TAR("TAR包",1),SCRIPT("脚本",2);
	private String name;
	private int index;
	
	private FileType(String name,int index){
		this.name=name;
		this.index=index;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
