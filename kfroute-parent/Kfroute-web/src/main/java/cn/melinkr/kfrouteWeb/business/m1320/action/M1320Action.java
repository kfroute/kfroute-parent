package cn.melinkr.kfrouteWeb.business.m1320.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.melinkr.kfrouteWeb.business.m1320.service.M1320Service;
import cn.melinkr.platform.kfroute.JQueryDataFormBean;
import cn.melinkr.platform.kfroute.SourceServerMsg;
import cn.melinkr.platform.kfroute.SourceServerPortMsg;

import com.alibaba.fastjson.JSON;
import com.melinkr.micro.auth.entity.LoginMsg;
import com.melinkr.micro.auth.util.Constants;
import com.melinkr.micro.base.BaseAction;
import com.melinkr.micro.exception.ServiceException;

public class M1320Action extends BaseAction{

	private M1320Service m1320Service;
	private String retCode;
	private String retMsg;
	private String sEcho = "";// 记录操作的次数  每次加1
	private int iDisplayStart;// 起始
	private int iDisplayLength;// size
	private SourceServerMsg serverMsg;
	private SourceServerPortMsg serverPortMsg;

	
	public String main() {
		return "success";
	}
	/**
	 * 执行设备文件导入数据库操作
	 * @return
	 * @throws Exception 
	 */
	public String doServerImp(){
		String fileName = (String)request.getSession().getAttribute("m1320UploadFileName");
		if(fileName==null||fileName.equals("")){
			retCode = "132001";
			retMsg = "导入文件信息丢失，请重新导入！";
			return "fail";
		}
			
		boolean flag = false;
		String opNote = request.getParameter("imp_note");
		try {
			flag = m1320Service.doServerFileImp(fileName,opNote);
			retCode = "0000";
			retMsg = "操作成功";
			request.getSession().removeAttribute("m1320UploadFileName");
		}catch(ServiceException e){
			e.printStackTrace();
			String errorMsg = e.getMessage()==null?"":e.getMessage();
			String[] errorMsgArr = errorMsg.split("~");
			if(errorMsgArr==null||errorMsgArr.length==1){//系统发生了未知异常
				retCode = "132000";
				retMsg = "数据导入发生未知异常，请联系管理员或稍后重试！";
			}else{
				retCode = errorMsgArr[0];
				retMsg = errorMsgArr[1];
			}	
			flag=false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retCode = "132000";
			retMsg = "数据导入发生未知异常，请联系管理员或稍后重试！";
			request.getSession().removeAttribute("m1320UploadFileName");
			flag = false;
		}
		return flag?"success":"fail";
	}
	/**
	 * 批量查询服务器信息
	 * @return
	 */
	public String queryServer(){
		response.setContentType("text/html; charset=UTF-8");
		
		LoginMsg loginMsg = (LoginMsg)request.getAttribute(Constants.CURRENT_USER);
		String startTimestamp = request.getParameter("startTimestamp");
		String endTimestamp = request.getParameter("endTimestamp");
		if(loginMsg==null)
			return "fail";
		String loginNo = loginMsg.getLoginNo();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			
			Map queryMap = new HashMap();
			String orderSql = getSortSqlStr();//获取排序字符串
			if(orderSql!=null&&!orderSql.equals("")){
				queryMap.put("order", orderSql);
			}
			String searchSql = getSearchSqlStr();//获取搜索字符串
			if(searchSql!=null&&!searchSql.equals("")){
				queryMap.put("search", searchSql);
			}
			
			queryMap.put("loginNo", loginNo);
			queryMap.put("startTimestamp", startTimestamp);
			queryMap.put("endTimestamp", endTimestamp);
			queryMap.put("serverMsg", serverMsg);
			Map resultMap = m1320Service.queryServer(queryMap, iDisplayStart, iDisplayLength);
			JQueryDataFormBean retBean = new JQueryDataFormBean();
			
			retBean.setRows((List)resultMap.get("rows"));
			retBean.setITotalRecords((Integer)resultMap.get("total"));
			retBean.setITotalDisplayRecords((Integer)resultMap.get("filterTotal"));
			retBean.setSEcho(sEcho);
			String res=JSON.toJSONString(retBean);
			out.println(res);
		}  catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMsg = e.getMessage()==null?"":e.getMessage();
			String[] errorMsgArr = errorMsg.split("~");
			if(errorMsgArr==null||errorMsgArr.length==1){//系统发生了未知异常
				retCode = "132000";
				retMsg = "系统发生未知异常，请联系管理员或稍后重试！";
			}else{
				retCode = errorMsgArr[0];
				retMsg = errorMsgArr[1];
			}	
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retCode = "132000";
			retMsg = "系统发生未知异常，请联系管理员或稍后重试！";
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
		return "success";
	}
	/**
	 * 批量查询服务器shadowsocks端口信息
	 * @return
	 */
	public String queryServerPortDetail(){
		response.setContentType("text/html; charset=UTF-8");
		
		LoginMsg loginMsg = (LoginMsg)request.getAttribute(Constants.CURRENT_USER);
		if(loginMsg==null)
			return "fail";
		String loginNo = loginMsg.getLoginNo();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			
			Map queryMap = new HashMap();
			String orderSql = getSortSqlStr();//获取排序字符串
			if(orderSql!=null&&!orderSql.equals("")){
				queryMap.put("order", orderSql);
			}
			String searchSql = getSearchSqlStr();//获取搜索字符串
			if(searchSql!=null&&!searchSql.equals("")){
				queryMap.put("search", searchSql);
			}
			if(serverPortMsg==null||serverPortMsg.getSourceIp()==null||serverPortMsg.getSourceIp().equals("")){
				throw new ServiceException("132011~ip不能为空");
			}
			queryMap.put("loginNo", loginNo);
			queryMap.put("serverPortMsg", serverPortMsg);
			Map resultMap = m1320Service.queryServerPortDetail(queryMap, iDisplayStart, iDisplayLength);
			JQueryDataFormBean retBean = new JQueryDataFormBean();
			
			retBean.setRows((List)resultMap.get("rows"));
			retBean.setITotalRecords((Integer)resultMap.get("total"));
			retBean.setITotalDisplayRecords((Integer)resultMap.get("filterTotal"));
			retBean.setSEcho(sEcho);
			String res=JSON.toJSONString(retBean);
			out.println(res);
		}  catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMsg = e.getMessage()==null?"":e.getMessage();
			String[] errorMsgArr = errorMsg.split("~");
			if(errorMsgArr==null||errorMsgArr.length==1){//系统发生了未知异常
				retCode = "132000";
				retMsg = "系统发生未知异常，请联系管理员或稍后重试！";
			}else{
				retCode = errorMsgArr[0];
				retMsg = errorMsgArr[1];
			}	
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retCode = "132000";
			retMsg = "系统发生未知异常，请联系管理员或稍后重试！";
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
		return "success";
	}
	public String addServer() {
		response.setContentType("text/html; charset=UTF-8");

		LoginMsg loginMsg = (LoginMsg) request
				.getAttribute(Constants.CURRENT_USER);
		if (loginMsg == null){
			retCode="132003";
			retMsg="用户登录已经失效或非法访问";
			return "fail";
		}
			
		String loginNo = loginMsg.getLoginNo();
		try {
			serverMsg.setCreateBy(loginNo);
			serverMsg.setSysStatus(0);
			boolean flag = m1320Service.addServer(serverMsg);
			if (flag) {
				retCode = "0000";
			} else {
				retCode = "132007";
				retMsg = "服务器未能正常入库，请联系管理员或稍后重试！";
				return "fail";
			}
		}  catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMsg = e.getMessage()==null?"":e.getMessage();
			String[] errorMsgArr = errorMsg.split("~");
			if(errorMsgArr==null||errorMsgArr.length==1){//系统发生了未知异常
				retCode = "132000";
				retMsg = "系统发生未知异常，请联系管理员或稍后重试！";
			}else{
				retCode = errorMsgArr[0];
				retMsg = errorMsgArr[1];
			}	
			return "fail";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retCode = "131000";
			retMsg = "系统发生未知异常，异常原因【" + e.getMessage() + "】,请联系管理员或稍后重试！";
			return "fail";
		} 
		return "success";
	}
	public String updateServer() {
		response.setContentType("text/html; charset=UTF-8");

		LoginMsg loginMsg = (LoginMsg) request
				.getAttribute(Constants.CURRENT_USER);
		if (loginMsg == null){
			retCode="132003";
			retMsg="用户登录已经失效或非法访问";
			return "fail";
		}
			
		String loginNo = loginMsg.getLoginNo();
		try {
			Map queryMap = new HashMap();
			serverMsg = m1320Service.queryServer(serverMsg);
		}  catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMsg = e.getMessage()==null?"":e.getMessage();
			String[] errorMsgArr = errorMsg.split("~");
			if(errorMsgArr==null||errorMsgArr.length==1){//系统发生了未知异常
				retCode = "132000";
				retMsg = "系统发生未知异常，请联系管理员或稍后重试！";
			}else{
				retCode = errorMsgArr[0];
				retMsg = errorMsgArr[1];
			}	
			return "fail";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retCode = "132000";
			retMsg = "系统发生未知异常，异常原因【" + e.getMessage() + "】,请联系管理员或稍后重试！";
			return "fail";
		} 
		return "success";
	}
	public String queryServerDetail() {
		response.setContentType("text/html; charset=UTF-8");

		LoginMsg loginMsg = (LoginMsg) request
				.getAttribute(Constants.CURRENT_USER);
		if (loginMsg == null){
			retCode="132003";
			retMsg="用户登录已经失效或非法访问";
			return "fail";
		}
			
		String loginNo = loginMsg.getLoginNo();
		try {
			Map queryMap = new HashMap();
			serverMsg = m1320Service.queryServer(serverMsg);
		}  catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMsg = e.getMessage()==null?"":e.getMessage();
			String[] errorMsgArr = errorMsg.split("~");
			if(errorMsgArr==null||errorMsgArr.length==1){//系统发生了未知异常
				retCode = "132000";
				retMsg = "系统发生未知异常，请联系管理员或稍后重试！";
			}else{
				retCode = errorMsgArr[0];
				retMsg = errorMsgArr[1];
			}	
			return "fail";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retCode = "132000";
			retMsg = "系统发生未知异常，异常原因【" + e.getMessage() + "】,请联系管理员或稍后重试！";
			return "fail";
		} 
		return "success";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String doUpdateServer() {
		response.setContentType("text/html; charset=UTF-8");

		LoginMsg loginMsg = (LoginMsg) request
				.getAttribute(Constants.CURRENT_USER);
		if (loginMsg == null){
			retCode="132003";
			retMsg="用户登录已经失效或非法访问";
			return "fail";
		}
			
		String loginNo = loginMsg.getLoginNo();
		try {
			
			Map addParam = new HashMap();
			addParam.put("hisType", "1");
			addParam.put("hisBy",loginNo);
			addParam.put("ipAddress",serverMsg.getIpAddress());
			m1320Service.addServerHisByMap(addParam);
			serverMsg.setUpdateBy(loginNo);
			boolean flag = m1320Service.updateServer(serverMsg);
			if (flag) {
				retCode = "0000";
				serverMsg = m1320Service.queryServer(serverMsg);
			} else {
				retCode = "132007";
				retMsg = "服务器未能正常入库，请联系管理员或稍后重试！";
				return "fail";
			}
		}  catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMsg = e.getMessage()==null?"":e.getMessage();
			String[] errorMsgArr = errorMsg.split("~");
			if(errorMsgArr==null||errorMsgArr.length==1){//系统发生了未知异常
				retCode = "132000";
				retMsg = "系统发生未知异常，请联系管理员或稍后重试！";
			}else{
				retCode = errorMsgArr[0];
				retMsg = errorMsgArr[1];
			}	
			return "fail";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retCode = "132000";
			retMsg = "系统发生未知异常，异常原因【" + e.getMessage() + "】,请联系管理员或稍后重试！";
			return "fail";
		} 
		return "success";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String delServer() {
		response.setContentType("text/html; charset=UTF-8");

		LoginMsg loginMsg = (LoginMsg) request
				.getAttribute(Constants.CURRENT_USER);
		if (loginMsg == null){
			retCode="131003";
			retMsg="用户登录已经失效或非法访问";
			return "fail";
		}
			
		String loginNo = loginMsg.getLoginNo();
		PrintWriter out = null;

		try {
			out = response.getWriter();
			Map addParam = new HashMap();
			addParam.put("hisType", "2");
			addParam.put("hisBy",loginNo);
			addParam.put("ipAddress",serverMsg.getIpAddress());
			m1320Service.addServerHisByMap(addParam);
			boolean flag = m1320Service.delServer(serverMsg);
			if (flag) {
				retCode = "0000";
			} else {
				retCode = "1320010";
				retMsg = "服务器删除失败，请联系管理员或稍后重试！";
				return "fail";
			}
			out.println("{\"retCode\":\""+retCode+"\",\"retMsg\":\""+retMsg+"\"}");
		}  catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMsg = e.getMessage()==null?"":e.getMessage();
			String[] errorMsgArr = errorMsg.split("~");
			if(errorMsgArr==null||errorMsgArr.length==1){//系统发生了未知异常
				retCode = "132000";
				retMsg = "系统发生未知异常，请联系管理员或稍后重试！";
			}else{
				retCode = errorMsgArr[0];
				retMsg = errorMsgArr[1];
			}	
			out.println("{\"retCode\":\""+retCode+"\",\"retMsg\":\""+retMsg+"\"}");
			return "fail";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retCode = "131000";
			retMsg = "系统发生未知异常，异常原因【" + e.getMessage() + "】,请联系管理员或稍后重试！";
			out.println("{\"retCode\":\""+retCode+"\",\"retMsg\":\""+retMsg+"\"}");
			return "fail";
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		return "success";
	}
	public M1320Service getM1320Service() {
		return m1320Service;
	}
	public void setM1320Service(M1320Service m1320Service) {
		this.m1320Service = m1320Service;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public String getSEcho() {
		return sEcho;
	}
	public void setSEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	
	public int getIDisplayStart() {
		return iDisplayStart;
	}
	public void setIDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public int getIDisplayLength() {
		return iDisplayLength;
	}
	public void setIDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public SourceServerMsg getServerMsg() {
		return serverMsg;
	}
	public void setServerMsg(SourceServerMsg serverMsg) {
		this.serverMsg = serverMsg;
	}
	public SourceServerPortMsg getServerPortMsg() {
		return serverPortMsg;
	}
	public void setServerPortMsg(SourceServerPortMsg serverPortMsg) {
		this.serverPortMsg = serverPortMsg;
	}
	
}
