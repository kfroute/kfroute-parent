package cn.melinkr.platform.unite;

import java.io.Serializable;

public class UniteInterServiceEntity  implements Serializable{
	private Integer id;
	private String serviceCode;
	private String serviceUrl;
	private String serviceType;
	private Integer status;
	private String method;
	private String nameSpace;
	private String serviceDesc;
	private Integer timeout;
	
	
	@Override
	public String toString() {
		return "SeocooServiceEntity [id=" + id + ", serviceCode=" + serviceCode
				+ ", serviceUrl=" + serviceUrl + ", serviceType=" + serviceType
				+ ", status=" + status + ", method=" + method + ", nameSpace="
				+ nameSpace + ", serviceDesc=" + serviceDesc + ", timeout="
				+ timeout + "]";
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getServiceCode() {
		return serviceCode;
	}


	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}


	public String getServiceUrl() {
		return serviceUrl;
	}


	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}


	public String getServiceType() {
		return serviceType;
	}


	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public String getNameSpace() {
		return nameSpace;
	}


	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}


	public String getServiceDesc() {
		return serviceDesc;
	}


	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}


	public Integer getTimeout() {
		return timeout;
	}


	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	
	
	
}
