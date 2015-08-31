package com.melinkr.micro.enums;

public enum ReduceType {
	MONEY("减现金",1),TICKET("返券",2),NOTHING("不减返",0);
	private String name;
	private int index;
	private ReduceType(String name,int index){
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
	
	public String toString(){
		return "ReduceType[index="+this.index+",name="+this.name+"]";
	}
	
	
}