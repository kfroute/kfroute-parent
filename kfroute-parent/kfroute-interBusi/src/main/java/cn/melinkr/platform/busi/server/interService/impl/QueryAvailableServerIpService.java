package cn.melinkr.platform.busi.server.interService.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.melinkr.platform.busi.server.busiService.IQueryAvailableServerIpBusiService;
import cn.melinkr.platform.busi.server.interService.PubBusiService;
import cn.melinkr.platform.kfroute.ServerAvailableIp;
import cn.melinkr.platform.unite.Reslut;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author: zhangyl
 * @time: 2015-07-10 10:22
 * @version: 1.0
 * 资源服务器端口初始化操作
 * 代理模式，代理真实的service业务逻辑
 */
public class QueryAvailableServerIpService implements PubBusiService{
	private static final Logger logger = Logger.getLogger(QueryAvailableServerIpService.class);
	private IQueryAvailableServerIpBusiService queryAvailableServerIpBusiService;
	
	@Override
	public Object sevice(String param,String ip) {//service的具体业务在此处进行具体调用
		// TODO Auto-generated method stub
		logger.info("param:"+param);
		Map paramMap = JSONObject.parseObject(param, Map.class);
		Reslut reslut=new Reslut();
		if(paramMap==null){//参数不为空，长度为1
			reslut.setResultCode("FAIL-001");
			reslut.setResultMsg("参数解析错误");
		}else{
			try{
				paramMap.put("routerIp", ip);
				List<ServerAvailableIp> returnIpList = queryAvailableServerIpBusiService.queryServerIpList(paramMap);
				reslut.setResultCode("success");
				reslut.setResultData(returnIpList);
			}catch(Exception e){
				logger.error(e.getMessage());
				String errorMsg = e.getMessage()==null?"":e.getMessage();
				String[] errorMsgArr = errorMsg.split("~");
				if(errorMsgArr==null||errorMsgArr.length==1){//系统发生了未知异常
					reslut.setResultCode("FAIL-002");
					reslut.setResultMsg(e.getMessage());
				}else{
					reslut.setResultCode(errorMsgArr[0]);
					reslut.setResultMsg(errorMsgArr[1]);
				}	
			}
			
		}
		String msg=JSON.toJSONString(reslut);
		return msg;
	}

	public IQueryAvailableServerIpBusiService getQueryAvailableServerIpBusiService() {
		return queryAvailableServerIpBusiService;
	}

	public void setQueryAvailableServerIpBusiService(
			IQueryAvailableServerIpBusiService queryAvailableServerIpBusiService) {
		this.queryAvailableServerIpBusiService = queryAvailableServerIpBusiService;
	}

	
	
	
}
