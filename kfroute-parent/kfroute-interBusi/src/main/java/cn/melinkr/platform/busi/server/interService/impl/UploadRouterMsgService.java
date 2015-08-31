package cn.melinkr.platform.busi.server.interService.impl;

import java.util.List;

import org.apache.log4j.Logger;

import cn.melinkr.platform.busi.server.busiService.IRouterMsgLogBusiService;
import cn.melinkr.platform.busi.server.interService.PubBusiService;
import cn.melinkr.platform.kfroute.RouterSysLog;
import cn.melinkr.platform.unite.Reslut;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tydic.framework.base.exception.ServiceException;

/**
 * 
 * @author: zhangyl
 * @time: 2015-07-07 17:40
 * @version: 1.0
 * 路由器设备实时状态上报业务处理程序
 * 代理模式，代理真实的service业务逻辑
 */
public class UploadRouterMsgService implements PubBusiService{
	private static final Logger logger = Logger.getLogger(UploadRouterMsgService.class);
	private IRouterMsgLogBusiService routerMsgLogBusiService;
	
	@Override
	public Object sevice(String param,String ip) {//service的具体业务在此处进行具体调用
		// TODO Auto-generated method stub
		logger.info("param:"+param);
		List<RouterSysLog> msgBodyList=JSONObject.parseArray(param, RouterSysLog.class);
		
		Reslut reslut=new Reslut();
		if(msgBodyList==null||msgBodyList.size()==0){
			reslut.setResultCode("FAIL-001");
			reslut.setResultMsg("参数解析错误");
		}else{
			try{
				boolean flag = routerMsgLogBusiService.saveRouteMsgLog(msgBodyList);
				if(flag){
					reslut.setResultCode("success");
					reslut.setResultMsg("数据上报成功");
				}else{
					reslut.setResultCode("FAIL-002");
					reslut.setResultMsg("数据入库异常，请检查接口日志！");
				}
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
	public IRouterMsgLogBusiService getRouterMsgLogBusiService() {
		return routerMsgLogBusiService;
	}
	public void setRouterMsgLogBusiService(IRouterMsgLogBusiService routerMsgLogBusiService) {
		this.routerMsgLogBusiService = routerMsgLogBusiService;
	}
	
}
