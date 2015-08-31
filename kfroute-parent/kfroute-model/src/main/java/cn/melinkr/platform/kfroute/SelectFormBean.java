package cn.melinkr.platform.kfroute;

public class SelectFormBean {
	private String value;
	private String name;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "SelectFormBean [value=" + value + ", name=" + name + "]";
	}
	

}
