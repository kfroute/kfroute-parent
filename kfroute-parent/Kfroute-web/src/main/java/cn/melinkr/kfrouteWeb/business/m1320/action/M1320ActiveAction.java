package cn.melinkr.kfrouteWeb.business.m1320.action;

import java.io.PrintWriter;

import cn.melinkr.kfrouteWeb.business.m1320.service.M1320ActiveService;

import com.melinkr.micro.auth.entity.LoginMsg;
import com.melinkr.micro.auth.util.Constants;
import com.melinkr.micro.base.BaseAction;

/**
 * @author: joki
 * @time: 2015-08-07 16:25
 * @version: 1.0
 * 服务器激活功能
 *
 */
public class M1320ActiveAction extends BaseAction{

	private M1320ActiveService m1320ActiveService;
	private String serverIdList;//待处理服务器ID列表
	
	public String activeServer(){
		response.setContentType("text/html; charset=UTF-8");
		LoginMsg loginMsg = (LoginMsg)request.getAttribute(Constants.CURRENT_USER);
		if(loginMsg==null)
			return "fail";
		String loginNo = loginMsg.getLoginNo();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(serverIdList==null||serverIdList.equals("")){
				out.println("{\"retCode\":\"132004\",\"retMsg\":\"请选择服务器后重试！\"}");
				return "fail";
			}
			String[] actvieResult = m1320ActiveService.doActiveServer(serverIdList,loginNo);
			if(actvieResult==null){
				out.println("{\"retCode\":\"132005\",\"retMsg\":\"系统执行异常！\"}");
				return "fail";
			}
			out.println("{\"retCode\":\"0000\",\"retMsg\":{\"success\":"+actvieResult[0]+",\"fail\":"+actvieResult[1]+",\"failMsg\":\""+actvieResult[2]+"\",\"retObj\":"+actvieResult[3]+"}}");
		}catch(Exception e){
			out.println("{\"retCode\":\"132006\",\"retMsg\":\"系统执行异常！\"}");
			e.printStackTrace();
			return "fail";
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
		
		return "success";
	}

	public M1320ActiveService getM1320ActiveService() {
		return m1320ActiveService;
	}

	public void setM1320ActiveService(M1320ActiveService m1320ActiveService) {
		this.m1320ActiveService = m1320ActiveService;
	}

	public String getServerIdList() {
		return serverIdList;
	}

	public void setServerIdList(String serverIdList) {
		this.serverIdList = serverIdList;
	}
	
}
