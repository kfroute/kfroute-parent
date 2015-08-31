package cn.melinkr.kfrouteWeb.business.m1310.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.melinkr.kfrouteWeb.business.m1310.service.M1310Service;
import cn.melinkr.platform.kfroute.JQueryDataFormBean;
import cn.melinkr.platform.kfroute.RouterMsg;

import com.alibaba.fastjson.JSON;
import com.melinkr.micro.auth.entity.LoginMsg;
import com.melinkr.micro.auth.util.Constants;
import com.melinkr.micro.base.BaseAction;
import com.melinkr.micro.exception.ServiceException;

public class M1310Action extends BaseAction {

	private M1310Service m1310Service;
	private String retCode;
	private String retMsg;
	private String sEcho = "";// 记录操作的次数 每次加1
	private int iDisplayStart;// 起始
	private int iDisplayLength;// size
	private RouterMsg routerMsg;
	private String macList;
	private String deviceList;
	public String main() {
		return "success";
	}

	/**
	 * 执行设备文件导入数据库操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doDeviceImp() {
		String fileName = (String) request.getSession().getAttribute(
				"m1310UploadFileName");
		if (fileName == null || fileName.equals("")) {
			retCode = "131001";
			retMsg = "导入文件信息丢失，请重新导入！";
			return "fail";
		}

		boolean flag = false;
		String opNote = request.getParameter("imp_note");
		try {
			flag = m1310Service.doDeviceFileImp(fileName, opNote);
			retCode = "0000";
			retMsg = "操作成功";
			request.getSession().removeAttribute("m1310UploadFileName");
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retCode = "131000";
			retMsg = "数据导入发生未知异常，请联系管理员或稍后重试！";
			request.getSession().removeAttribute("m1310UploadFileName");
			flag = false;
		}
		return flag ? "success" : "fail";
	}

	public String queryDevice() {
		response.setContentType("text/html; charset=UTF-8");

		LoginMsg loginMsg = (LoginMsg) request
				.getAttribute(Constants.CURRENT_USER);
		String startTimestamp = request.getParameter("startTimestamp");
		String endTimestamp = request.getParameter("endTimestamp");
		if (loginMsg == null)
			return "fail";
		String loginNo = loginMsg.getLoginNo();
		PrintWriter out = null;
		try {
			out = response.getWriter();

			Map queryMap = new HashMap();
			String orderSql = getSortSqlStr();// 获取排序字符串
			if (orderSql != null && !orderSql.equals("")) {
				queryMap.put("order", orderSql);
			}
			String searchSql = getSearchSqlStr();// 获取搜索字符串
			if (searchSql != null && !searchSql.equals("")) {
				queryMap.put("search", searchSql);
			}

			queryMap.put("loginNo", loginNo);
			queryMap.put("startTimestamp", startTimestamp);
			queryMap.put("endTimestamp", endTimestamp);
			queryMap.put("routerMsg", routerMsg);
			Map resultMap = m1310Service.queryDevice(queryMap, iDisplayStart,
					iDisplayLength);
			JQueryDataFormBean retBean = new JQueryDataFormBean();

			retBean.setRows((List) resultMap.get("rows"));
			retBean.setITotalRecords((Integer) resultMap.get("total"));
			retBean.setITotalDisplayRecords((Integer) resultMap
					.get("filterTotal"));
			retBean.setSEcho(sEcho);
			String res = JSON.toJSONString(retBean);
			out.println(res);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMsg = e.getMessage()==null?"":e.getMessage();
			String[] errorMsgArr = errorMsg.split("~");
			if(errorMsgArr==null||errorMsgArr.length==1){//系统发生了未知异常
				retCode = "131000";
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

	public String addDevice() {
		response.setContentType("text/html; charset=UTF-8");

		LoginMsg loginMsg = (LoginMsg) request
				.getAttribute(Constants.CURRENT_USER);
		if (loginMsg == null){
			retCode="131003";
			retMsg="用户登录已经失效或非法访问";
			return "fail";
		}
			
		String loginNo = loginMsg.getLoginNo();
		try {
			routerMsg.setCreateBy(loginNo);
			boolean flag = m1310Service.addDevice(routerMsg);
			if (flag) {
				retCode = "0000";
			} else {
				retCode = "131007";
				retMsg = "设备未能正常入库，请联系管理员或稍后重试！";
				return "fail";
			}
		}  catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMsg = e.getMessage()==null?"":e.getMessage();
			String[] errorMsgArr = errorMsg.split("~");
			if(errorMsgArr==null||errorMsgArr.length==1){//系统发生了未知异常
				retCode = "131000";
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
	public String updateDevice() {
		response.setContentType("text/html; charset=UTF-8");

		LoginMsg loginMsg = (LoginMsg) request
				.getAttribute(Constants.CURRENT_USER);
		if (loginMsg == null){
			retCode="131003";
			retMsg="用户登录已经失效或非法访问";
			return "fail";
		}
			
		String loginNo = loginMsg.getLoginNo();
		try {
			Map queryMap = new HashMap();
			routerMsg = m1310Service.queryDevice(routerMsg);
		}  catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMsg = e.getMessage()==null?"":e.getMessage();
			String[] errorMsgArr = errorMsg.split("~");
			if(errorMsgArr==null||errorMsgArr.length==1){//系统发生了未知异常
				retCode = "131000";
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String batchUpdateDevice() {
		response.setContentType("text/html; charset=UTF-8");

		//String deviceList = request.getParameter("deviceList");
		if(deviceList==null||deviceList.equals("")){
			retCode = "131011";
			retMsg = "请选择设备后重试！";
			return "fail";
		}
		try {
			Map queryMap = new HashMap();
			queryMap.put("deviceList", deviceList);
			Map retMap = m1310Service.queryDeviceByIdList(queryMap);
			macList = (String)retMap.get("macList");
			routerMsg = (RouterMsg)retMap.get("routerMsg");//用于在界面呈现初始数据
		}  catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMsg = e.getMessage()==null?"":e.getMessage();
			String[] errorMsgArr = errorMsg.split("~");
			if(errorMsgArr==null||errorMsgArr.length==1){//系统发生了未知异常
				retCode = "131000";
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String doBatchUpdateDevice() {
		response.setContentType("text/html; charset=UTF-8");

		LoginMsg loginMsg = (LoginMsg) request
				.getAttribute(Constants.CURRENT_USER);
		if (loginMsg == null){
			retCode="131003";
			retMsg="用户登录已经失效或非法访问";
			return "fail";
		}
			
		String loginNo = loginMsg.getLoginNo();
		try {
			
			Map addParam = new HashMap();
			addParam.put("hisType", "1");
			addParam.put("hisBy",loginNo);
			addParam.put("idList",deviceList);
			m1310Service.addDeviceHisByIdList(addParam);//批量保存历史记录
			routerMsg.setUpdateObj(loginNo);
			if(routerMsg.getBelongType()!=2){//非代理商渠道，代理商值设置为空
				routerMsg.setGroupId("00");
			}
			Map updateParam = new HashMap();
			updateParam.put("idList", deviceList);
			updateParam.put("routerMsg",routerMsg);
			
			
			boolean flag = m1310Service.updateBatchDevice(updateParam);
			if (flag) {
				retCode = "0000";
			} else {
				retCode = "131007";
				retMsg = "设备未能正常入库，请联系管理员或稍后重试！";
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String doUpdateDevice() {
		response.setContentType("text/html; charset=UTF-8");

		LoginMsg loginMsg = (LoginMsg) request
				.getAttribute(Constants.CURRENT_USER);
		if (loginMsg == null){
			retCode="131003";
			retMsg="用户登录已经失效或非法访问";
			return "fail";
		}
			
		String loginNo = loginMsg.getLoginNo();
		try {
			
			Map addParam = new HashMap();
			addParam.put("hisType", "1");
			addParam.put("hisBy",loginNo);
			addParam.put("mac",routerMsg.getMac());
			m1310Service.addDeviceHisByMap(addParam);
			routerMsg.setUpdateObj(loginNo);
			boolean flag = m1310Service.updateDevice(routerMsg);
			if (flag) {
				retCode = "0000";
				routerMsg = m1310Service.queryDevice(routerMsg);
			} else {
				retCode = "131007";
				retMsg = "设备未能正常入库，请联系管理员或稍后重试！";
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String delDevice() {
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
			addParam.put("mac",routerMsg.getMac());
			m1310Service.addDeviceHisByMap(addParam);
			boolean flag = m1310Service.delDevice(routerMsg);
			if (flag) {
				retCode = "0000";
			} else {
				retCode = "1310010";
				retMsg = "设备删除失败，请联系管理员或稍后重试！";
				return "fail";
			}
			out.println("{\"retCode\":\""+retCode+"\",\"retMsg\":\""+retMsg+"\"}");
		}  catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMsg = e.getMessage()==null?"":e.getMessage();
			String[] errorMsgArr = errorMsg.split("~");
			if(errorMsgArr==null||errorMsgArr.length==1){//系统发生了未知异常
				retCode = "131000";
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
	public M1310Service getM1310Service() {
		return m1310Service;
	}

	public void setM1310Service(M1310Service m1310Service) {
		this.m1310Service = m1310Service;
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

	public RouterMsg getRouterMsg() {
		return routerMsg;
	}

	public void setRouterMsg(RouterMsg routerMsg) {
		this.routerMsg = routerMsg;
	}

	public String getMacList() {
		return macList;
	}

	public void setMacList(String macList) {
		this.macList = macList;
	}

	public String getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(String deviceList) {
		this.deviceList = deviceList;
	}
	
}
