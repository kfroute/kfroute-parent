package cn.kfroute.platform.payment.server.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.xml.rpc.ServiceException;

import cn.kfroute.platform.payment.merchantPayment.service.inf.MerchantPaymentService;
import cn.kfroute.platform.payment.server.service.inf.Service;
import cn.seocoo.platform.payment.PayReslut;
import cn.seocoo.platform.unite.Message;
import cn.seocoo.platform.unite.TcpCont;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.odchina.micro.base.BaseAction;
import com.odchina.micro.constant.Constant;
import com.odchina.micro.util.BapUtil;
import com.odchina.micro.util.SpringHelper;
import com.odchina.micro.util.StringUtil;

public class HomeAction extends BaseAction{
	private MerchantPaymentService merchantPaymentService;
	/**
	 * 主控制器
	 * @throws IOException
	 */
	public void homeController() throws IOException{
		
		String str=this.readRequestMsg(request);
		//将string 转成对象
		Message msg=JSONObject.parseObject(str, Message.class);
		
		//获取对应的处理对象
		String prefix=msg.getTcpCont().getServiceCode();
		//获取spring对象
		Service service=(Service) SpringHelper.getSpringHelper().getBean(prefix+"Service");
		//调用业务处理类
		Object res=service.sevice(msg.getSvcCont().get(0).getParams());
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
		Service service=(Service) SpringHelper.getSpringHelper().getBean(prefix+"Service");
		
		PayReslut payReslut =(PayReslut) service.sevice(param);
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
		TcpCont  tcpCont=new TcpCont();
		tcpCont.setSrcSysID(msg.getTcpCont().getSrcSysID());
		tcpCont.setServiceCode(msg.getTcpCont().getServiceCode());
		tcpCont.setSrcSysPassWord(msg.getTcpCont().getSrcSysPassWord());
		tcpCont.setSrcSysSign(msg.getTcpCont().getSrcSysSign());
		tcpCont.setTransactionID(msg.getTcpCont().getTransactionID());
		String header=JSON.toJSONString(tcpCont);
		return BapUtil.getWebSvcContent(res, header);
	}

	public MerchantPaymentService getMerchantPaymentService() {
		return merchantPaymentService;
	}

	public void setMerchantPaymentService(
			MerchantPaymentService merchantPaymentService) {
		this.merchantPaymentService = merchantPaymentService;
	}
	
}
