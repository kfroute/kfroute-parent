package cn.melinkr.platform.busi.server.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import sun.misc.Service;
import cn.melinkr.platform.busi.server.interService.PubBusiService;
import cn.melinkr.platform.payment.PayReslut;
import cn.melinkr.platform.unite.Message;
import cn.melinkr.platform.unite.MsgHead;
import cn.melinkr.platform.util.Md5;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.melinkr.micro.base.BaseAction;
import com.melinkr.micro.constant.Constant;
import com.melinkr.micro.util.BapUtil;
import com.melinkr.micro.util.SpringHelper;
import com.melinkr.micro.util.StringUtil;
import com.melinkr.micro.util.SystemConfigUtil;

/**
 * 
 * @author: zhangyl
 * @time: 2015-07-07 17:10
 * @version: 1.0
 * 接口业务处理入口action，根据serviceCode来进行服务调度
 */
public class MasterAction extends BaseAction{
	/**
	 * 主控制器
	 * @throws IOException
	 */
	public void masterController() throws IOException{
		
		String str=this.readRequestMsg(request);
		//将string 转成对象
		Message msg=JSONObject.parseObject(str, Message.class);
		
		//获取对应的处理对象
		String prefix=msg.getMsgHead().getServiceCode();
		//获取spring对象
		PubBusiService service=(PubBusiService) SpringHelper.getSpringHelper().getBean(prefix+"Service");
		//调用业务处理类
		Object res=service.sevice(msg.getMsgBody().get(0).getParams(),msg.getRemoteIp());
		//回写结果
		if(res instanceof String){
			//添加头尾信息
			String reslut=callBack((String)res,msg);
			//返回是文本
			this.writeBack(reslut);
		}if(res instanceof File){
			//返回文件
			this.writeBack((File)res);
		}
		
		
	}
	
	/**
	 *  form 表单提交
	 * @throws ServiceException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public String  sendForword() throws ServiceException, ServletException, IOException{
		String  str="http://www.baidu.com";
		String param=request.getParameter("params");
		//参数转换成Map
		if(StringUtil.isEmpty(param)){
			throw new ServiceException("未传递任何参数");
		}
		
		//参数解析
		Map<String,String> params=this.parseParam(param);
		
		String prefix=params.get("seviceType");
		if(StringUtil.isEmpty(prefix)){
			throw new ServiceException("未找到请求业务");
		}
		PubBusiService service=(PubBusiService) SpringHelper.getSpringHelper().getBean(prefix+"Service");
		
		PayReslut payReslut =(PayReslut) service.sevice(param,getRemoteAddress(request));
		if(payReslut.getData() == ""){
			//request.setAttribute("data", payReslut.getData());
			request.setAttribute("paymentData", payReslut.getData());
			return "error";
		}
		if(payReslut.isOrderPaid()){
			request.setAttribute("paymentData", payReslut.getData());
			if(params.get("callType").equals(Constant.CALL_WAP)){
				return "orderPaidWap";
			}else{
				return "orderPaidWeb";
			}			
		}
		if(payReslut.getType().equals(Constant.REQUST_RETIRN_JUMP)){
			response.sendRedirect(payReslut.getUrl());
			return null;
		}else{
			request.setAttribute("paymentData", payReslut.getData());
			return payReslut.getReturnCode();
		}
		
		
	}
	
	/**
	 * 解析请求报文
	 * @param param
	 * @return
	 */
	private Map<String, String> parseParam(String param) {
		String[] keyValue=param.split("&");
		
		Map<String,String> map=new HashMap<String, String>();
		for (int i = 0; i < keyValue.length; i++) {
			String value="";
			if(keyValue[i].split("=").length>1){
				value=keyValue[i].split("=")[1];
			}
			map.put(keyValue[i].split("=")[0],value );
		}
		return map;
	}

	/**
	 * 回调
	 * @param res
	 * @param msg
	 * @return
	 */
	public String callBack(String res,Message msg){
		MsgHead  msgHead=new MsgHead();
		MsgHead _msgHead = msg.getMsgHead();
		String serviceCode = _msgHead.getServiceCode();
		String srcSysID = _msgHead.getSrcSysID();
		String transactionID = System.currentTimeMillis()+"";
		String key = SystemConfigUtil.getSingleProperty("sign_key").getPropertyValue();
		String md5_value="";
		try {
			md5_value = Md5.MD5(serviceCode+"-"+srcSysID+"-"+transactionID+"-"+key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msgHead.setSrcSysID(srcSysID);
		msgHead.setServiceCode(serviceCode);
		msgHead.setTransactionID(transactionID);
		msgHead.setSrcSysSign(md5_value);
		String header=JSON.toJSONString(msgHead);
		return BapUtil.getWebSvcContent(res, header);
	}	
	/**从requet中获取IP*/
	public String getRemoteAddress(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }  
}
