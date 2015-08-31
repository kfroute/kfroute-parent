package cn.melinkr.platform.auth;

import java.sql.Timestamp;
/**
 * 
 * @author: zhangyl
 * @time: 2015-07-13 11:28
 * @version: 1.0
 * @copyright:melinkr
 * 功能菜单对象
 */

public class FuncCode extends BaseEntity {
	private String functionCode;//FUNCTION_CODE
	private String mainCode;//MAIN_CODE
	private String parentCode;//PARENT_CODE
	private String parentCodes;//PARENT_CODEs
	private String functionName;//FUNCTION_NAME
	private String commandRight;//COMMAND_RIGHT
	private String fromUrl;//FORM_URL
	private String opName;//OP_NAME
	private String urlLink;//URL_LINK
	private String iconLink;//ICON_LINK
	private String version;//VERSION
	private String funcOrder;//FUNC_ORDER
	private String funcType;//FUNC_TYPE
	private String permission;
	private String funcNote;//FUNC_NOTE
	private String creater;//CREATER
	private Timestamp createTime;//CREATE_TIME
	
	public static enum FuncCodeType {
        menu("菜单"), button("按钮");

        private final String info;
        private FuncCodeType(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }
	
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	
	public String getMainCode() {
		return mainCode;
	}
	public void setMainCode(String mainCode) {
		this.mainCode = mainCode;
	}
	
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	public String getCommandRight() {
		return commandRight;
	}
	public void setCommandRight(String commandRight) {
		this.commandRight = commandRight;
	}

	public String getFromUrl() {
		return fromUrl;
	}
	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}
	
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	
	public String getUrlLink() {
		return urlLink;
	}
	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}
	
	public String getIconLink() {
		return iconLink;
	}
	public void setIconLink(String iconLink) {
		this.iconLink = iconLink;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getFuncOrder() {
		return funcOrder;
	}
	public void setFuncOrder(String funcOrder) {
		this.funcOrder = funcOrder;
	}
	
	public String getFuncType() {
		return funcType;
	}
	public void setFuncType(String funcType) {
		this.funcType = funcType;
	}
	
	public String getFuncNote() {
		return funcNote;
	}
	public void setFuncNote(String funcNote) {
		this.funcNote = funcNote;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
	public String getParentCodes() {
		return parentCodes;
	}
	public void setParentCodes(String parentCodes) {
		this.parentCodes = parentCodes;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String makeSelfAsParentCodes() {
        return getParentCodes() + getFunctionCode() + "/";
    }
	@Override
	public String toString() {
		return "FuncCode [functionCode=" + functionCode + ", mainCode="
				+ mainCode + ", parentCode=" + parentCode + ", parentCodes="
				+ parentCodes + ", functionName=" + functionName
				+ ", commandRight=" + commandRight + ", fromUrl=" + fromUrl
				+ ", opName=" + opName + ", urlLink=" + urlLink + ", iconLink="
				+ iconLink + ", version=" + version + ", funcOrder="
				+ funcOrder + ", funcType=" + funcType + ", permission="
				+ permission + ", funcNote=" + funcNote + ", creater="
				+ creater + ", createTime=" + createTime + "]";
	}
	
	
	
	

}
