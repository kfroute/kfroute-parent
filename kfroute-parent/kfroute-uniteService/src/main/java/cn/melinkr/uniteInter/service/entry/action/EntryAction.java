package cn.melinkr.uniteInter.service.entry.action;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import cn.melinkr.platform.unite.InteractiveLog;
import cn.melinkr.platform.unite.Message;
import cn.melinkr.platform.unite.MsgHead;
import cn.melinkr.platform.unite.Reslut;
import cn.melinkr.platform.unite.UniteInterServiceEntity;
import cn.melinkr.platform.util.Md5;
import cn.melinkr.uniteInter.service.entry.service.inf.InteractiveLogService;
import cn.melinkr.uniteInter.service.uniteInterService.service.inf.UniteInterService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.melinkr.micro.base.BaseAction;
import com.melinkr.micro.constant.Constant;
import com.melinkr.micro.memcache.MemCachedClientEnhance;
import com.melinkr.micro.util.BapUtil;
import com.melinkr.micro.util.SpringHelper;
import com.melinkr.micro.util.StringUtil;
import com.melinkr.micro.util.SystemConfigUtil;

/**
 * 
 * @author: zhangyl
 * @time: 2015-07-06 23:20
 * @version:1.0
 * 接口访问统一入口，通过该入口调用请求的资源
 * 1. 第三方资源，将请求转发到第三方地址
 * 2. 文件下载，下载问题
 * 3. 表单提交（暂未实现）
 */
public class EntryAction extends BaseAction{
	private UniteInterService uniteInterService;
	private InteractiveLogService interactiveLogService;
	private static MemCachedClientEnhance memCacheEnhance = null;
	static {
		memCacheEnhance = (MemCachedClientEnhance) SpringHelper
				.getSpringHelper().getBean("memcachedClientEnhance");
	}
	
	
	/**
	 * 常规接口
	 * @throws IOException
	 */
	public void interfaceController() throws IOException{
		String _method = request.getMethod();
		String str="";
		if(_method=="GET"){//普通get请求通过base64为加密进行传输
//			BASE64Decoder decoder = new BASE64Decoder();  
//            try {  
//            	String _value = request.getParameter("value");
//            	System.out.println(">>>>>>>>>"+_value);
//            	if(_value!=null&&_value!=""){
//            		 byte[] b = decoder.decodeBuffer(_value);  
//                     str = new String(b, "utf-8");  
//            	}else{
//            		this.writeBack("{\"errorCode\":\"003\",\"errorMsg\":\"请求异常\"}");
//        			return;
//            	}
//               
//            } catch (Exception e) {  
//                e.printStackTrace(); 
//                this.writeBack("{\"errorCode\":\"003\",\"errorMsg\":\"请求异常\"}");
//    			return;
//            }  
			str = request.getParameter("value");
		}else{
			str=this.readRequestMsg(request);
		}

		//将string 转成对象
		Message msg=null;
		try{
			msg = JSONObject.parseObject(str, Message.class);
		}catch(Exception e){
			e.printStackTrace();
			this.writeBack("{\"errorCode\":\"003\",\"errorMsg\":\"请求异常\"}");
			return;
		}
		//记录上行日志
		this.saveUpLog(msg, str);
		//验证请求是否正常
		if(!validate(msg)){
			this.writeBack("{\"errorCode\":\"004\",\"errorMsg\":\"请求异常\"}");
			return;
		}
		UniteInterServiceEntity sse=handleUniteInterService(msg);
		//验证该方法是已否注册
		if(sse==null||StringUtil.isEmpty(sse.getServiceUrl())){
			Reslut reslut=new Reslut();
			reslut.setResultCode(Constant.FAIL);
			reslut.setResultMsg(Constant.SERVICE_NOT_FOUNDE);
			String res=JSON.toJSONString(reslut);
			this.writeBack(this.callBack(res, msg));
			return ;
		}
		String url=sse.getServiceUrl();
		
		if("2".equals(sse.getServiceType())){
			//文件类型接口
			BapUtil.httpSendFile(url,JSON.toJSONString(msg),response);
		}else if("3".equals(sse.getServiceType())){
			//form 表单提交
	
		}else{
			//调用第三方接口
			long a=System.currentTimeMillis();
			String res=	BapUtil.httpSendJson(url,JSON.toJSONString(msg));
			long b =System.currentTimeMillis();
			//记录下行日志
			saveDownLog(msg, res,(int)(b-a));
			//回写第三方结果
			this.writeBack(res);
		}
		
	}
	
	/**
	 *  form 表单
	 * @throws ServiceException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void  sendForword() throws ServiceException, ServletException, IOException{
		
		String  seviceType=request.getParameter("seviceType");
		UniteInterServiceEntity use=handleUniteInterService(seviceType);
		//验证该方法是已否注册
		if(use==null||StringUtil.isEmpty(use.getServiceUrl())){
			throw  new ServiceException("未找到注册方法");
		}
		String params=getParameters();
		params=URLEncoder.encode(params,"utf-8");
		//转发到对应服务
		response.sendRedirect(use.getServiceUrl()+"?params="+params);
		//response.sendRedirect("http://127.0.0.1:8087/payment/service/home!sendForword.do"+"?params="+params);
	}
	
	/**
	 * 保存上行日志
	 * @param msg
	 * @param str
	 */
	public void saveUpLog(Message msg,String reqMsg){
		
		MsgHead mh=msg.getMsgHead();
		InteractiveLog iLogUp=new InteractiveLog();
		iLogUp.setTransactionID(mh.getTransactionID());
		iLogUp.setSrcSysID(mh.getSrcSysID());
		iLogUp.setServiceCode(mh.getServiceCode());
		String ip=getRemoteAddress(this.request);
		msg.setRemoteIp(ip);
		iLogUp.setIp(ip);
		//iLogUp.setMac(getMACAddress(ip));
		iLogUp.setType(1);
		iLogUp.setContentMsg(reqMsg);
		interactiveLogService.add(iLogUp);//记录完毕
	}
	
	/**
	 * 保存下行日志
	 * @param msg
	 * @param str
	 */
	public void saveDownLog(Message msg,String respMsg,int costs){
		
		MsgHead mh=msg.getMsgHead();
		InteractiveLog iLogdown=new InteractiveLog();
		iLogdown.setTransactionID(mh.getTransactionID());
		iLogdown.setSrcSysID(mh.getSrcSysID());
		iLogdown.setServiceCode(mh.getServiceCode());
		String ip=getRemoteAddress(this.request);
		iLogdown.setIp(ip);
		//iLogUp.setMac(getMACAddress(ip));
		iLogdown.setType(2);
		iLogdown.setCosts(costs);
		iLogdown.setContentMsg(respMsg);
		interactiveLogService.add(iLogdown);//记录完毕
	}
	
	/**
	 * 验证请求
	 * @param msg
	 * @return
	 */
	private boolean validate(Message msg) {
		// TODO Auto-generated method stub
		MsgHead msgHead = msg.getMsgHead();
		String serviceCode = msgHead.getServiceCode();
		String srcSysID = msgHead.getSrcSysID();
		String transactionID = msgHead.getTransactionID();
		String key = SystemConfigUtil.getSingleProperty("sign_key").getPropertyValue();
		String md5_value="";
		try {
			md5_value = Md5.MD5(serviceCode+"-"+srcSysID+"-"+transactionID+"-"+key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(md5_value.equals(msgHead.getSrcSysSign())){
			return true;
		}
		return false;
	}

	/**
	 * 获取第三地址
	 * @param msg
	 * @return
	 */
	public UniteInterServiceEntity handleUniteInterService(Message msg){
		//获取调用接口的第三方地址
		Object obj = memCacheEnhance.get("ServiceCodes");
		Map<String,UniteInterServiceEntity> serviceMap=null;
		if(obj==null){
			List<UniteInterServiceEntity> uniteInterServiceList=uniteInterService.findAll();
			serviceMap = new HashMap<String,UniteInterServiceEntity>();
			for (int i = 0; i < uniteInterServiceList.size(); i++) {
				serviceMap.put(uniteInterServiceList.get(i).getServiceCode(),uniteInterServiceList.get(i));
				
			}
			 //30分钟后失效
			 int intevals=Integer.valueOf(SystemConfigUtil.getSingleProperty("cache_intevals").getPropertyValue());
			 boolean flag = memCacheEnhance.set("ServiceCodes",serviceMap, new Date(System.currentTimeMillis()+intevals*60*1000));
			// uniteInterServiceList=(List<UniteInterServiceEntity>) memCacheEnhance.get("ServiceCodes");
			 
		}else{
			 serviceMap=(Map<String,UniteInterServiceEntity>)obj;
		}
		//List<UniteInterServiceEntity>  uniteInterServiceList=uniteInterService.findAll();//不使用memoryCache时使用
		UniteInterServiceEntity use=serviceMap.get(msg.getMsgHead().getServiceCode());
//		for (int i = 0; i < uniteInterServiceList.size(); i++) {
//			if(uniteInterServiceList.get(i).getServiceCode().equals(msg.getMsgHead().getServiceCode())){
//				use=uniteInterServiceList.get(i);
//			}
//		}
		return use;
	}

	/**
	 * 获取第三地址
	 * @param msg
	 * @return
	 */
	public UniteInterServiceEntity handleUniteInterService(String serviceCode){
		//获取调用接口的第三方地址
		List<UniteInterServiceEntity> uniteInterServiceList=(List<UniteInterServiceEntity>) memCacheEnhance.get("ServiceCodes");
		if(uniteInterServiceList==null){
			uniteInterServiceList=uniteInterService.findAll();
			 //5分钟后失效
			 int intevals=Integer.valueOf(SystemConfigUtil.getSingleProperty("cache_intevals").getPropertyValue());
			 memCacheEnhance.set("ServiceCodes",uniteInterServiceList, new Date(System.currentTimeMillis()+intevals*60*1000));
		}
		UniteInterServiceEntity use=null;
		for (int i = 0; i < uniteInterServiceList.size(); i++) {
			if(uniteInterServiceList.get(i).getServiceCode().equals(serviceCode)){
				use=uniteInterServiceList.get(i);
			}
		}
		return use;
	}
	
	
	/**
	 * 回调结果
	 * @param res
	 * @param msg
	 * @return
	 */
	public String callBack(String res,Message msg){
		MsgHead  msgHead=new MsgHead();
		msgHead.setSrcSysID(msg.getMsgHead().getSrcSysID());
		msgHead.setServiceCode(msg.getMsgHead().getServiceCode());
		msgHead.setTransactionID(msg.getMsgHead().getTransactionID());
		msgHead.setSrcSysSign(msg.getMsgHead().getServiceCode());
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
	
	/**根据IP获取MAC地址*/
    public String getMACAddress(String ip) {  
        String str = "";  
        String macAddress = "";  
        try {  
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);  
            InputStreamReader ir = new InputStreamReader(p.getInputStream());  
            LineNumberReader input = new LineNumberReader(ir);  
            for (int i = 1; i < 100; i++) {  
                str = input.readLine();  
                if (str != null) {  
                    if (str.indexOf("MAC Address") > 1) {  
                        macAddress = str.substring(  
                                str.indexOf("MAC Address") + 14, str.length());  
                        break;  
                    }  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace(System.out);  
        }  
        return macAddress;  
    }  

	public UniteInterService getUniteInterService() {
		return uniteInterService;
	}

	public void setUniteInterService(UniteInterService uniteInterService) {
		this.uniteInterService = uniteInterService;
	}

	public InteractiveLogService getInteractiveLogService() {
		return interactiveLogService;
	}

	public void setInteractiveLogService(InteractiveLogService interactiveLogService) {
		this.interactiveLogService = interactiveLogService;
	}
	
	
	

}
