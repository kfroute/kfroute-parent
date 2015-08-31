package cn.melinkr.platform.unite;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author: zhangyl
 * @time: 2015-07-06 23:20
 * @version: 1.0
 * @copyright:melinkr
 * 请求报文体 
 *
 */
public class MsgBody {

	@JSONField(name="PARAMS")
	private String params;

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	
	
}
