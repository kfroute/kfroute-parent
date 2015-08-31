/* 
 * Copyrights 尚果科技支付平台研发项目组 @ 2015， 尚果科技支付 SEOCOO Computer Co., Ltd.<br>
 * 项目名称： 尚果科技支付平台研发项目组 <br>
 * 文件名称：PaymentAction.java <br>
 * 创建时间：2015-5-19-下午18:49:14 <br>
 * 创建用户：永生<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */

package cn.kfroute.platform.payment.pay.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.kfroute.platform.payment.merchantPayment.dao.inf.MerchantPaymentDao;
import cn.kfroute.platform.payment.pay.service.inf.PaymentService;
import cn.kfroute.platform.payment.paymentAccount.dao.inf.PaymentAccountDao;
import cn.kfroute.platform.payment.server.dao.inf.PayDao;
import cn.kfroute.platform.payment.server.dao.inf.PayInfoDao;
import cn.kfroute.platform.payment.server.dao.inf.PayLogDao;
import cn.kfroute.platform.payment.server.service.inf.PayGeneralService;
import cn.seocoo.platform.payment.Merchant;
import cn.seocoo.platform.payment.MerchantPayment;
import cn.seocoo.platform.payment.OrderStatus;
import cn.seocoo.platform.payment.PayInfo;
import cn.seocoo.platform.payment.PayLog;
import cn.seocoo.platform.payment.PayOrderBase;
import cn.seocoo.platform.payment.Payment;
import cn.seocoo.platform.payment.PaymentAccount;
import cn.seocoo.platform.payment.PaymentResult;
import cn.seocoo.platform.payment.alipay.NotifyData;
import cn.seocoo.platform.payment.alipay.PayWapRequest;
import cn.seocoo.platform.payment.alipay.ResponseData;

import com.alibaba.fastjson.JSON;
import com.odchina.micro.alipay.util.AlipayCore;
import com.odchina.micro.alipay.util.PayUtils;
import com.odchina.micro.util.DateUtils;
import com.odchina.micro.util.HttpUtils;
import com.odchina.micro.util.PaymentUtil;
import com.odchina.micro.util.SystemConfigUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.tydic.framework.base.dao.EntityManagerImpl;
import com.tydic.framework.util.DateUtil;

public class AliPayWapPaymentService  extends EntityManagerImpl<Object, Integer> implements PaymentService{
	
	private static final Logger logger = Logger.getLogger(AliPayWapPaymentService.class);
	
	private PayInfoDao payInfoDao;
	private PaymentAccountDao paymentAccountDao;
	private  MerchantPaymentDao merchantPaymentDao;
	private PayDao payDao;
	private PayLogDao payLogDao;
	private PayGeneralService payGeneralService;
	
	@Override
	public String toPay(Payment payment, Merchant merchant) {

		payInfoToDb(payment);
		PayWapRequest pwRq = new PayWapRequest();
		try{
			pwRq = packageAuthRequest(payment);
		}catch(Exception e){
			logger.error("wap支付数据入库出错", e);
			return "";
		}
		
		String token = isAuth(pwRq, payment);
		if(token.equals("")){
			return "";
		}else{
			System.out.println("这里是支付请求组装");
			String  str = packagRequest(payment,token);
			return str;
		}
	}

	private PayWapRequest packageAuthRequest(Payment payment){
		
		String subject = payment.getOrderNumber();
		String outTradeNo = payment.getPlatformOrderNumber();
		String totalFee = (double)payment.getTotalPrice()/100+"";
		
		MerchantPayment merchantPayment = new MerchantPayment();
		merchantPayment.setMerchantCode(payment.getMerchantCode());
		merchantPayment.setPaymentCode(PaymentUtil.PAYMENT_PLATFORM_ALIPAYWAP);
		merchantPayment = merchantPaymentDao.findOne(merchantPayment);
		String paymentAccountName = merchantPayment.getPaymentAccount();
		
		PaymentAccount paymentAccount = new PaymentAccount();
		//paymentAccount.setMerchantCode(payment.getMerchantCode());	
		paymentAccount.setPaymentAccount(paymentAccountName);
		paymentAccount = paymentAccountDao.findOne(paymentAccount);
		String callBackUrl = null;
		if(paymentAccount.getCallBackUrl() == null||paymentAccount.getCallBackUrl().isEmpty()){
			callBackUrl = SystemConfigUtil.getSingleProperty("call_back_url").getPropertyValue();
		}else{
			callBackUrl = paymentAccount.getCallBackUrl();
		}
		String notifyUrl = paymentAccount.getNotifyUrl();
		
		String sellerAccountName = paymentAccount.getRelationAccount();
		PayOrderBase payOrderBase = new PayOrderBase();
		payOrderBase.setOrderNumber(payment.getOrderNumber());
		payOrderBase = payDao.findByObject(payOrderBase);
		//String outUser = payOrderBase.getUserId();
		String outUser = "weweqwerertytyui";
		
		String merchantUrl = paymentAccount.getMerchantUrl();
		String payExpire = "3600";
		String agentId = "efefsdf";
		String reqData = "<direct_trade_create_req><subject>"+subject+"</subject><out_trade_no>"+outTradeNo+"</out_trade_no><total_fee>"+totalFee+"</total_fee><seller_account_name>"+
		                 sellerAccountName+"</seller_account_name><call_back_url>"+callBackUrl+"</call_back_url><notify_url>"+notifyUrl+"</notify_url><out_user>"+outUser+"</out_user><merchant_url>"+merchantUrl+"</merchant_url><pay_expire>"+payExpire+"</pay_expire><agent_id>"+agentId+"</agent_id></direct_trade_create_req>";
		                 
		String service = "alipay.wap.trade.create.direct";
		
		String secId = "MD5";
		
		String partner = paymentAccount.getPayPartner();
		
		String reqId = UUID.randomUUID().toString().replace("-", ""); 
		
		String format = "xml";
		
		String v = "2.0";
		
		Map<String,String> map = new HashMap<String,String>();
		
		if(format!=null&&!format.isEmpty()){
			map.put("format", format);
		}
		if(partner!=null&&!partner.isEmpty()){
			map.put("partner", partner);
		}
		if(reqData!=null&&!reqData.isEmpty()){
			map.put("req_data", reqData);
		}
		if(reqId!=null&&!reqId.isEmpty()){
			map.put("req_id", reqId);
		}
		if(secId!=null&&!secId.isEmpty()){
			map.put("sec_id", secId);
		}
		if(service!=null&&!service.isEmpty()){
			map.put("service", service);
		}
		if(v!=null&&!v.isEmpty()){
			map.put("v", v);
		}
		
		String sign = AlipayCore.buildAlipayMysign(map,paymentAccount.getSecurity());
		
		PayWapRequest payRequest = new PayWapRequest();
		payRequest.setFormat(format);
		payRequest.setPartner(partner);
		payRequest.setReqData(reqData);
		payRequest.setReqId(reqId);
		payRequest.setSecId(secId);
		payRequest.setService(service);
		payRequest.setSign(sign);
		payRequest.setV(v);
		
		return payRequest;
	}
	
	private String isAuth(PayWapRequest pwRq,Payment payment){
		
		Map requestMap = new HashMap();
		requestMap.put("req_data", pwRq.getReqData());
		requestMap.put("service", pwRq.getService());
		requestMap.put("sec_id", pwRq.getSecId());
		requestMap.put("partner", pwRq.getPartner());
		requestMap.put("req_id", pwRq.getReqId());
		requestMap.put("sign", pwRq.getSign());
		requestMap.put("format", pwRq.getFormat());
		requestMap.put("v", pwRq.getV());
		
		//记录请求日志
		
		String authLog=JSON.toJSONString(requestMap);
		PayLog payLog=new PayLog();
		payLog.setMsg(authLog);
		payLog.setOrderNumber(payment.getOrderNumber());
		payLog.setCreateDate(new Date());
		payLog.setPlatformOrderNumber(payment.getPlatformOrderNumber());
		payLog.setPayType("4");
		payLogDao.insertPayLog(payLog);
		
		String resStr = null;
		try {
			resStr = new String(HttpUtils.post("http://wappaygw.alipay.com/service/rest.htm", requestMap), "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("发起http请求异常",e);
		}
		try {
			resStr = java.net.URLDecoder.decode(resStr, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("url解码异常",e);
		}
		if(resStr.indexOf("request_token")!= -1){
			int a = resStr.indexOf("request_token");
			int b = resStr.lastIndexOf("request_token");
			String subString = resStr.substring(a+14, b-2);
			payLog.setPayType("5");
			//记录回执
			payLog.setMsg(resStr);
			payLogDao.insertPayLog(payLog);
			return subString;
		}
		return "";
	}
	
	@Override
	public PaymentResult callBack(Map<String, Object> map) {

		NotifyData notify = parseNotifyData(map.get("notify_data").toString());
		finishPayLog(notify,map.get("backParams").toString());
		updateOrder(notify);
		updatePayInfo(notify);
		PaymentResult paymentResult = packagPaymentResult(notify);
		return paymentResult;
	}
	
	private void updateOrder(NotifyData notify){
		
		PayOrderBase payOrderBase = new PayOrderBase();
		payOrderBase.setPlatformOrderNumber(notify.getOutTradeNo());
		payOrderBase.setBackStatus("1");
		payOrderBase.setStatus(1);
		payDao.updatePayOrderBase(payOrderBase);
		
	}
	
	private void updatePayInfo(NotifyData notify){
		
		PayInfo payInfo = new PayInfo();
		payInfo.setPaydate(DateUtils.parse(notify.getGmtPayment()));
		String platformOrderNumber = notify.getOutTradeNo();
		PayOrderBase payOrderBase = new PayOrderBase();
		payOrderBase.setPlatformOrderNumber(platformOrderNumber);
		payInfo.setPayStatus("2");
		//payInfo.setTotalPrice(Double.parseDouble(notify.getTotalFee()));
		payInfo.setRetnISeq(notify.getTradeNo());
		payInfoDao.updatePayInfo(payInfo);
		
	}
	
	private PaymentResult packagPaymentResult(NotifyData notify){
		PaymentResult paymentResult = new PaymentResult();
		paymentResult.setTotalPrice(notify.getTotalFee());
		//paymentResult.setMerchantCode(notify.get)
		return paymentResult;
		
	}
	
	private void finishPayLog(NotifyData notify,String paramUrl){
		
		PayLog payLog = new PayLog();
		try{
			payLog.setCreateDate(DateUtil.getCurrentDate());
			payLog.setMsg(paramUrl);
			payLog.setPlatformOrderNumber(notify.getOutTradeNo());
			String platFormOrderNumber = notify.getOutTradeNo();
			PayOrderBase payOrderBase = new PayOrderBase();
			payOrderBase.setPlatformOrderNumber(platFormOrderNumber);
			payOrderBase = payDao.findByObject(payOrderBase);
			payLog.setOrderNumber(payOrderBase.getOrderNumber());
			payLog.setPayType("2");
			
			PayLog payLog1 = new PayLog();
			payLog1 = payLogDao.findByObject(payLog);
			String notifyUrl = payLog1.getUrl();
			payLog.setUrl(notifyUrl);
			payLogDao.insertPayLog(payLog);
		}catch(Exception e){
			logger.error("所需字段未填写！", e);
		}
	}
	
	private NotifyData parseNotifyData(String notifyData){
		XStream xStream=new XStream(new DomDriver());
		NotifyData notify = new NotifyData();
		xStream.processAnnotations(notify.getClass());
		notify = (NotifyData) xStream.fromXML(notifyData);
		return notify;
	}
	
	public void payInfoToDb(Payment payment){
		
		PayInfo payInfo = transToPayInfo(payment);
		payInfoDao.insertPayInfo(payInfo);
	}
	
	
	private PayInfo transToPayInfo(Payment payment){
		PayInfo payInfo = new PayInfo();
		try{
			payInfo.setMerchantCode(payment.getMerchantCode());
			payInfo.setOrderNumber(payment.getOrderNumber());
			payInfo.setPlatformOrderNumber(payment.getPlatformOrderNumber());
			payInfo.setPaydate(payment.getPayTime());
			payInfo.setPayStatus(OrderStatus.WAITPAY.getStatus()+"");
			payInfo.setTransSeq(payment.getTranSeq());
			payInfo.setTotalPrice((double)payment.getTotalPrice());
		}catch(Exception e){
			logger.error("所需字段未填写！", e);
		}
		return payInfo;
	}
	
	public PayLog transToPayLog(Payment payment,StringBuffer stringBuffer){
		PayLog payLog = new PayLog();
		try{
			payLog.setCreateDate(DateUtil.getCurrentDate());
			payLog.setMsg(stringBuffer.toString());
			payLog.setPayType("1");
			payLog.setOrderNumber(payment.getOrderNumber());
			payLog.setPlatformOrderNumber(payment.getPlatformOrderNumber());
			
			PaymentAccount paymentAccount = new PaymentAccount();
			paymentAccount.setMerchantCode(payment.getMerchantCode());	
			paymentAccount = paymentAccountDao.findOne(paymentAccount);
			String notifyUrl = paymentAccount.getNotifyUrl();
			
			payLog.setUrl(paymentAccount.getNotifyUrl());
		}catch(Exception e){
			logger.error("所需字段未填写！", e);
		}
		return payLog;
	}
	
	public String packagRequest(Payment payment, String token){
		updateOrderBase(payment);
		PayWapRequest payRequest = getRequest(payment,token);
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<form id='payForm' name='payForm' action='http://wappaygw.alipay.com/service/rest.htm' method='get'>");
		stringBuffer.append("<input type='text' name='req_data' style='display:none' value='"+payRequest.getReqData()+"'/><BR>");
		stringBuffer.append("<input type='text' name='service' style='display:none' value='"+payRequest.getService()+"'/><BR>");
		stringBuffer.append("<input type='text' name='sec_id' style='display:none' value='"+payRequest.getSecId()+"'/><BR>");
		stringBuffer.append("<input type='text' name='partner' style='display:none' value='"+payRequest.getPartner()+"'/><BR>");
		stringBuffer.append("<input type='text' name='sign' style='display:none' value='"+payRequest.getSign()+"'/><BR>");
		stringBuffer.append("<input type='text' name='format' style='display:none' value='"+payRequest.getFormat()+"'/><BR>");
		stringBuffer.append("<input type='text' name='v' style='display:none' value='"+payRequest.getV()+"'/><BR>");
		stringBuffer.append("<input type='submit' value='确认' style='display:none' /></form>");
		stringBuffer.append("<script>document.forms['payForm'].submit();</script>");
		PayLog payLog = transToPayLog(payment,stringBuffer);
		payLogDao.insertPayLog(payLog);
		return stringBuffer.toString();
	}
	
	private void updateOrderBase(Payment payment){
		
		String platformOrderNumber = payment.getPaymentPlatform();
		PayOrderBase payOrderBase = new PayOrderBase();
		payOrderBase.setPlatformOrderNumber(platformOrderNumber);
		payOrderBase.setBankCode(PaymentUtil.PAYMENT_PLATFORM_ALIPAYWAP);
		payDao.updatePayOrderBase(payOrderBase);
	}

	public PayWapRequest getRequest(Payment payment, String token){
		
		MerchantPayment merchantPayment = new MerchantPayment();
		merchantPayment.setMerchantCode(payment.getMerchantCode());
		merchantPayment.setPaymentCode(PaymentUtil.PAYMENT_PLATFORM_ALIPAYWAP);
		merchantPayment = merchantPaymentDao.findOne(merchantPayment);
		String paymentAccountName = merchantPayment.getPaymentAccount();
		
		PaymentAccount paymentAccount = new PaymentAccount();	
		paymentAccount.setPaymentAccount(paymentAccountName);
		paymentAccount = paymentAccountDao.findOne(paymentAccount);
		
		PayOrderBase payOrderBase = new PayOrderBase();
		payOrderBase.setPlatformOrderNumber(payment.getPlatformOrderNumber());
		payOrderBase = payDao.findByObject(payOrderBase);
	
		String reqData = "<auth_and_execute_req><request_token>"+token+"</request_token></auth_and_execute_req>";
		                 
		String service = "alipay.wap.auth.authAndExecute";
		
		String secId = "MD5";
		
		String partner = paymentAccount.getPayPartner();
		
		String format = "xml";
		
		String v = "2.0";
		
		Map<String,String> map = new HashMap<String,String>();
		
		if(reqData!=null&&!reqData.isEmpty()){
			map.put("req_data", reqData);
		}
		if(partner!=null&&!partner.isEmpty()){
			map.put("partner", partner);
		}
		if(reqData!=null&&!reqData.isEmpty()){
			map.put("req_data", reqData);
		}
		if(secId!=null&&!secId.isEmpty()){
			map.put("sec_id", secId);
		}
		if(service!=null&&!service.isEmpty()){
			map.put("service", service);
		}
		if(v!=null&&!v.isEmpty()){
			map.put("v", v);
		}
		if(format!=null&&!format.isEmpty()){
			map.put("format", format);
		}
		
		String sign = AlipayCore.buildAlipayMysign(map,paymentAccount.getSecurity());

		PayWapRequest payRequest = new PayWapRequest();
		payRequest.setFormat(format);
		payRequest.setPartner(partner);
		payRequest.setReqData(reqData);
		payRequest.setSecId(secId);
		payRequest.setService(service);
		payRequest.setSign(sign);
		payRequest.setV(v);
		payRequest.setFormat(format);
		
		return payRequest;
	}

	public PayInfoDao getPayInfoDao() {
		return payInfoDao;
	}

	public void setPayInfoDao(PayInfoDao payInfoDao) {
		this.payInfoDao = payInfoDao;
	}

	public MerchantPaymentDao getMerchantPaymentDao() {
		return merchantPaymentDao;
	}

	public void setMerchantPaymentDao(MerchantPaymentDao merchantPaymentDao) {
		this.merchantPaymentDao = merchantPaymentDao;
	}

	public PaymentAccountDao getPaymentAccountDao() {
		return paymentAccountDao;
	}

	public void setPaymentAccountDao(PaymentAccountDao paymentAccountDao) {
		this.paymentAccountDao = paymentAccountDao;
	}

	public PayDao getPayDao() {
		return payDao;
	}

	public void setPayDao(PayDao payDao) {
		this.payDao = payDao;
	}

	public PayLogDao getPayLogDao() {
		return payLogDao;
	}

	public void setPayLogDao(PayLogDao payLogDao) {
		this.payLogDao = payLogDao;
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.pay.service.inf.PaymentService#callInitiator(javax.servlet.http.HttpServletResponse, java.util.Map) 
	 */
	@Override
	public void callInitiator( Map map) {
		
		NotifyData notify = parseNotifyData(map.get("notify_data").toString());
		PayOrderBase payOrderBase = new PayOrderBase();
		payOrderBase.setPlatformOrderNumber(notify.getOutTradeNo());
		payOrderBase = payDao.findByObject(payOrderBase);	
		Map requestMap = new HashMap();
		if(payOrderBase ==null){
			return;
		}
		requestMap.put("orderNumber", payOrderBase.getOrderNumber());
		requestMap.put("status", "SUCCESS");
		String resStr = null;
		lastPayLog(map,JSON.toJSONString(map));
		try {
			resStr = new String(HttpUtils.post(payOrderBase.getCallBackUrl(), requestMap), "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("发起http请求异常",e);
		}
		List<ResponseData> list = new ArrayList<ResponseData>();
		ResponseData[] array = JSON.parseObject(resStr, ResponseData[].class);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.pay.service.inf.PaymentService#writeback(javax.servlet.http.HttpServletResponse, cn.seocoo.platform.payment.PaymentResult) 
	 */
	@Override
	public void writeback(HttpServletResponse response,
			PaymentResult backPayResult) {
		
		response.setContentType("text/html; charset=GBK");
        // // 给支付平台反馈相应消息
        PrintWriter printWriter;
		try {
			printWriter = response.getWriter();
			printWriter.write(backPayResult.getResponseMsg());
	        printWriter.flush();
	        printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("获取response输出流异常",e);
		}//
        logger.info("ACK_RESPONSE_UPTRANSEQ_" + backPayResult.getOrderNumber() + "_" + backPayResult.getResponseMsg());
		
	}
	
	
	private PayLog lastPayLog(Map map,String param){
		PayLog payLog = new PayLog();
		try{
			payLog.setCreateDate(DateUtil.getCurrentDate());
			payLog.setMsg(param);
			String platFormOrderNumber = map.get("out_trade_no").toString();
			PayOrderBase payOrderBase = new PayOrderBase();
			payOrderBase.setPlatformOrderNumber(platFormOrderNumber);
			payOrderBase = payDao.findByObject(payOrderBase);
			payLog.setOrderNumber(payOrderBase.getOrderNumber());
			payLog.setPlatformOrderNumber(platFormOrderNumber);
			payLog.setPayType("3");
			
			PayLog payLog1 = new PayLog();
			payLog1 = payLogDao.findByObject(payLog);
			String notifyUrl = payLog1.getUrl();
			payLog.setUrl(notifyUrl);
			payLogDao.insertPayLog(payLog);
		}catch(Exception e){
			logger.error("所需字段未填写！", e);
		}
		return payLog;
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.pay.service.inf.PaymentService#callBackPage(java.util.Map) 
	 */
	@Override
	public String callBackPage(HttpServletRequest request) {
		
		Map map = PayUtils.getParameterMap(request);	
	    if(payGeneralService.verifyParams(map)){
	    	System.out.println("=============================ishere=======================");
	    	logger.info("=============================ishere=======================");
	    	logger.debug("=============================ishere=======================");
	    	logger.warn("=============================ishere=======================");
	    	logger.info("=============================ishere=======================");
	    	String platformOrderNumber = map.get("out_trade_no").toString();
	    	PayOrderBase payOrderBase = new PayOrderBase();
	    	payOrderBase.setPlatformOrderNumber(platformOrderNumber);
	    	payOrderBase = payDao.findByObject(payOrderBase);
		    String status = map.get("result").toString();
		     
		    payOrderBase = payDao.findByObject(payOrderBase);	    
		    request.setAttribute("orderNumber",payOrderBase.getOrderNumber());
		    request.setAttribute("status",status);
		    request.setAttribute("totalPrice",payOrderBase.getTotalPrice());
		    return "paySuccess";
		}else{
			System.out.println("=============================ishere222222222=======================");
	    	logger.info("=============================ishere222222222=======================");
	    	logger.debug("=============================ishere222222222=======================");
	    	logger.warn("=============================ishere222222222=======================");
			request.setAttribute("orderNumber","");
		    request.setAttribute("status","secrityRisk");
		    request.setAttribute("totalPrice","");
		    return "paySuccess";
		}
	}

	public PayGeneralService getPayGeneralService() {
		return payGeneralService;
	}

	public void setPayGeneralService(PayGeneralService payGeneralService) {
		this.payGeneralService = payGeneralService;
	}
	
	
	
}
