package com.melinkr.micro.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.subject.WebSubject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.melinkr.micro.constant.Constant;
import com.melinkr.micro.shiro.ShiroUser;
import com.melinkr.micro.util.HttpUtils;
import com.melinkr.micro.util.SpringHelper;
import com.melinkr.micro.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	private static final long serialVersionUID = -7478111245993929289L;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected int iSortingCols;//排序的列数
	protected String sSearch = "";// 搜索的关键字

	private static final SerializeConfig config;
    
    static {
        config = new SerializeConfig();
        //config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式     
        //config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式 
    }
    
    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段         
        SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null         
        SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null         
        SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null         
        SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null
        SerializerFeature.PrettyFormat //格式化
        
        };
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	 /**
	  * 返回json格式的报文
	  * @param json 要返回的json报文
	  */
    public void sendMessages(String json) throws IOException {
		response.setContentType("text/json; charset=utf-8"); 
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(json);
    }

    public String getParameters(){
    	
    	String str="";
    	Map<String, String> param=HttpUtils.getParams(request);
    	Object s[] = param.keySet().toArray();
		for(int i = 0; i < param.size(); i++) {
			str+=s[i]+"="+param.get(s[i])+"&";
		}
		if(str.endsWith("&")){
			str=str.substring(0,str.length()-1);
		}
    	return str;
    }
    
	 /**
	  * 返回json格式的报文
	  * @param json 要返回的json报文
	  */
   public String Obj2Json(Object object)  {
	   
	   return JSONObject.toJSONString(object, config, features);
   }

   /**
	 * 读取request数据
	 * @param request
	 * @return
	 */
	public String readRequestMsg(HttpServletRequest request){
		java.io.BufferedReader bis = null;
		String line = null;
		String result = "";
		try {
			bis = new java.io.BufferedReader(new java.io.InputStreamReader(
					request.getInputStream(),"UTF-8"));
			while ((line = bis.readLine()) != null) {
				result += line + "\r\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 回写数据
	 * @param responseMsg
	 * @throws IOException
	 * 给调用平台反馈相应消息
	 */
	public void writeBack(String responseMsg) throws IOException{
		  response.setContentType("text/html; charset=UTF-8");
          PrintWriter printWriter = response.getWriter();//
          printWriter.write(responseMsg);
          printWriter.flush();
          printWriter.close();
	}
	/**
	 * 回写文件
	 * @param file
	 */
	public void writeBack(File file) {
		 //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        response.setContentType("multipart/form-data");  
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
        response.setHeader("Content-Disposition", "attachment;fileName="+file.getName());  
		ServletOutputStream out;
		try {
			FileInputStream inputStream = new FileInputStream(file);

			// 3.通过response获取ServletOutputStream对象(out)
			out = response.getOutputStream();

			int b = 0;
			byte[] buffer = new byte[512];
			while (b != -1) {
				b = inputStream.read(buffer);
				// 4.写到输出流(out)中
				out.write(buffer, 0, b);
			}
			inputStream.close();
			out.close();
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 回写流
	 * @param file
	 */
	public void writeBack(InputStream inputStream) {
		 //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        response.setContentType("multipart/form-data");  
		ServletOutputStream out;
		try {

			// 3.通过response获取ServletOutputStream对象(out)
			out = response.getOutputStream();

			int b = 0;
			byte[] buffer = new byte[512];
			while (b != -1) {
				b = inputStream.read(buffer);
				// 4.写到输出流(out)中
				out.write(buffer, 0, b);
			}
			inputStream.close();
			out.close();
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 获取登录用户信息
	 * @return
	 */
	public static ShiroUser queryCurrentShiroUser() {
			try {
			WebSubject ws = (WebSubject) SecurityUtils.getSubject();
			return (ShiroUser) ws.getPrincipal();
			} catch (Exception e) {
			return null;
			}
		}
	/**
	 * 获取登录用户商户号
	 * @return
	 */
	public String queryMerchantCode(){
		
		String merchantCode=(String) this.request.getSession().getAttribute(Constant.SESSION_MERCHANT);
		if(StringUtil.isEmpty(merchantCode)){
			merchantCode="9999";
		}
		return merchantCode;
	}
	public int getISortingCols() {
		return iSortingCols;
	}
	public void setISortingCols(int iSortingCols) {
		this.iSortingCols = iSortingCols;
	}
	public String getSSearch() {
		return sSearch;
	}
	public void setSSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	/**
	 * 获取排序信息
	 */
	public String getSortSqlStr(){
		String order = "";
		String sColumns = request.getParameter("sColumns");
		String[] sname = sColumns==null?null:request.getParameter("sColumns").split(",");
		if(iSortingCols>0){//需要排序
			for(int i=0;i<iSortingCols;i++){
				String col_num = request.getParameter("iSortCol_"+i);
				String sortType = request.getParameter("sSortDir_"+i);
				String col_name = sname[Integer.parseInt(col_num)];
				if(order.equals("")){
					order += col_name+" "+sortType;
				}else{
					order += ","+col_name+" "+sortType;
				}
			}
			
		}
		return order;
	}
	/**
	 * 获取过滤字符串
	 */
	public String getSearchSqlStr(){
		String searchSql = "";
		String sColumns = request.getParameter("sColumns");
		String[] sname = sColumns==null?null:request.getParameter("sColumns").split(",");//需要服务器端处理的列
		if(sSearch!=null&&!sSearch.equals("")){
			int iColumns = Integer.parseInt(request.getParameter("iColumns"));
			for(int i=0;i<iColumns;i++){
				boolean flag = Boolean.parseBoolean(request.getParameter("bSearchable_"+i));
				if(flag){//允许过滤
					String col_name = sname[i];
					if(searchSql.equals("")){
						searchSql += col_name+" like \"%"+sSearch+"%\"";
					}else{
						searchSql += " or "+col_name+" like \"%"+sSearch+"%\"";
					}
				}
			}
		}
		return searchSql;
	}
}
