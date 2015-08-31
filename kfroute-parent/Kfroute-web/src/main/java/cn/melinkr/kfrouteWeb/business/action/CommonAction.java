package cn.melinkr.kfrouteWeb.business.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import cn.melinkr.kfrouteWeb.business.service.CommonService;
import cn.melinkr.platform.kfroute.SelectFormBean;

import com.alibaba.fastjson.JSON;
import com.melinkr.micro.auth.entity.LoginMsg;
import com.melinkr.micro.auth.util.Constants;
import com.melinkr.micro.base.BaseAction;
import com.melinkr.micro.exception.ServiceException;

public class CommonAction extends BaseAction {
	
	
	private CommonService commonService;

	public String main() {
		System.out.println("2222");
		return "success";
	}

	/**
	 * 查询代理商列表
	 */
	public String  queryAgentSelection() {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			LoginMsg loginMsg = (LoginMsg)request.getAttribute(Constants.CURRENT_USER);
			int loginType;
			if(loginMsg==null){//用户为空，返回空数据
				out.println("{\"retCode\":\"0003\",\"retMsg\":\"用户状态已经失效，请重新登录\"}");
				return "fail";
			}
			if(loginMsg.getChnGroupMsg().getGroupId()==null||loginMsg.getChnGroupMsg().getGroupId().equals("")||loginMsg.getChnGroupMsg().getGroupId().equals("0")){//工号为自有工号
				if(loginMsg.getPowerRight()==0){
					loginType = Constants.SUPER_ADMIN;
				}else if(loginMsg.getPowerRight()==1){
					loginType = Constants.ADMIN;
				}else{
					loginType = Constants.NOMAL;
				}
			}else{//工号为代理商工号
				loginType = Constants.AGENT;
			}
			List<SelectFormBean> list = commonService.queryAgentSelection(loginMsg.getLoginNo(), loginType);
			String res=JSON.toJSONString(list);
			out.println(res);
		}catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			out.println("{\"retCode\":\"0004\",\"retMsg\":\"Service执行异常【"+e1.getMessage()+"】\"}");
			if(out!=null){
				out.flush();
				out.close();
			}
			return "fail";
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			out.println("{\"retCode\":\"0002\",\"retMsg\":\"系统执行异常【"+e1.getMessage()+"】\"}");
			if(out!=null){
				out.flush();
				out.close();
			}
			return "fail";
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
		
		return "success";
	}
	/**
	 * 查询服务器分组列表
	 */
	public String  queryServerGroupSelection() {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			LoginMsg loginMsg = (LoginMsg)request.getAttribute(Constants.CURRENT_USER);
			int loginType;
			if(loginMsg==null){//用户为空，返回空数据
				out.println("{\"retCode\":\"0003\",\"retMsg\":\"用户状态已经失效，请重新登录\"}");
				return "fail";
			}
			
			List<SelectFormBean> list = commonService.queryServerGroupSelection();
			String res=JSON.toJSONString(list);
			out.println(res);
		}catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			out.println("{\"retCode\":\"0004\",\"retMsg\":\"Service执行异常【"+e1.getMessage()+"】\"}");
			if(out!=null){
				out.flush();
				out.close();
			}
			return "fail";
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			out.println("{\"retCode\":\"0002\",\"retMsg\":\"系统执行异常【"+e1.getMessage()+"】\"}");
			if(out!=null){
				out.flush();
				out.close();
			}
			return "fail";
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
		
		return "success";
	}
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	
}
