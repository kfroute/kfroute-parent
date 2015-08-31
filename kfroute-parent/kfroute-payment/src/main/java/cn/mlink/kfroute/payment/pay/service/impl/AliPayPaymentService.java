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
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.kfroute.platform.payment.merchantPayment.dao.inf.MerchantPaymentDao;
import cn.kfroute.platform.payment.pay.service.inf.PaymentService;
import cn.kfroute.platform.payment.paymentAccount.dao.inf.PaymentAccountDao;
import cn.kfroute.platform.payment.server.dao.inf.PayDao;
import cn.kfroute.platform.payment.server.dao.inf.PayInfoDao;
import cn.kfroute.platform.payment.server.dao.inf.PayLogDao;
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
import cn.seocoo.platform.payment.alipay.PayRequest;

import com.alibaba.fastjson.JSON;
import com.odchina.micro.alipay.util.AlipayCore;
import com.odchina.micro.util.DateUtils;
import com.odchina.micro.util.HttpUtils;
import com.odchina.micro.util.PaymentUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.tydic.framework.base.dao.EntityManagerImpl;
import com.tydic.framework.util.DateUtil;

public class AliPayPaymentService  extends EntityManagerImpl<Object, Integer> implements PaymentService{
	
	private static final Logger logger = Logger.getLogger(AliPayPaymentService.class);
	
	private PayInfoDao payInfoDao;
	private static PaymentAccountDao paymentAccountDao;
	private  MerchantPaymentDao merchantPaymentDao;
	private PayDao payDao;
	private PayLogDao payLogDao;
	@Override
	public String toPay(Payment payment, Merchant merchant) {
		// TODO Auto-generated method stub
		payInfoToDb(payment);
		System.out.println("这里是支付请求组装");
		String str = packagRequest(payment,merchant);
		return str;
	}
	
	@Override
	public PaymentResult callBack(Map<String, Object> map) {
		updateOrder(map);
		updatePayInfo(map);
		finishPayLog(map);
		PaymentResult paymentResult = packagPaymentResult(map);
		return paymentResult;
	}
	
	private PaymentResult packagPaymentResult(Map<String, Object> map){
		PaymentResult paymentResult = new PaymentResult();
		paymentResult.setTotalPrice(map.get("total_fee").toString());
		return paymentResult;
	}
	
	private void updateOrder(Map<String, Object> map){
		PayOrderBase payOrderBase = new PayOrderBase();
		payOrderBase.setOrderNumber(map.get("out_trade_no").toString());
		payOrderBase.setBackStatus("1");
		payOrderBase.setStatus(1);
		payDao.updatePayOrderBase(payOrderBase);
	}
	
	
	public void updatePayInfo(Map<String, Object> map){
		PayInfo payInfo = new PayInfo();
		payInfo.setPaydate(DateUtils.parse(map.get("gmt_payment").toString()));
		payInfo.setOrderNumber(map.get("out_trade_no").toString());
		payInfo.setPayStatus("2");
		payInfo.setRetnISeq(map.get("trade_no").toString());
		payInfoDao.updatePayInfo(payInfo);
	}
	
	private PayLog finishPayLog(Map<String, Object> map){
		PayLog payLog = new PayLog();
		try{
			payLog.setCreateDate(DateUtil.getCurrentDate());
			payLog.setMsg(map.get("backParams").toString());
			payLog.setOrderNumber(map.get("out_trade_no").toString());
			payLog.setPayType("2");
			
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
			payInfo.setPaydate(payment.getPayTime());
			payInfo.setPayStatus(OrderStatus.WAITPAY.getStatus()+"");
			payInfo.setTransSeq(payment.getTranSeq());
			payInfo.setTotalPrice((double)payment.getTotalPrice());
		}catch(Exception e){
			logger.error("所需字段未填写！", e);
		}
		return payInfo;
	}
	
	private PayLog transToPayLog(Payment payment,StringBuffer stringBuffer){
		PayLog payLog = new PayLog();
		try{
			payLog.setCreateDate(DateUtil.getCurrentDate());
			payLog.setMsg(stringBuffer.toString());
			payLog.setOrderNumber(payment.getOrderNumber());
			payLog.setPayType("1");
			
			PaymentAccount paymentAccount = new PaymentAccount();
			paymentAccount.setMerchantCode(payment.getMerchantCode());	
			paymentAccount.setBankCode(PaymentUtil.PAYMENT_PLATFORM_ALIPAY);
			paymentAccount = paymentAccountDao.findOne(paymentAccount);
			String notifyUrl = paymentAccount.getNotifyUrl();
			
			payLog.setUrl(notifyUrl);
		}catch(Exception e){
			logger.error("所需字段未填写！", e);
		}
		return payLog;
	}
	
	//private 
	
	public String packagRequest(Payment payment, Merchant merchant){
		//更新订单支付方式
		updateOrderBase(payment);
		PayRequest payRequest = getRequest(payment,merchant);
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<form id='payForm' name='payForm' action='https://mapi.alipay.com/gateway.do' method='post'>");
		stringBuffer.append("<input type='text' name='body' style='display:none' value='"+payRequest.getBody()+"'/><BR>");
		stringBuffer.append("<input type='text' name='extend_param' style='display:none' value='"+payRequest.getExtendParam()+"'/><BR>");
		stringBuffer.append("<input type='text' name='subject' style='display:none' value='"+payRequest.getSubject()+"' /><BR>");
		stringBuffer.append("<input type='text' name='sign_type' style='display:none' value='"+payRequest.getSignType()+"'/><BR>");
		stringBuffer.append("<input type='text' name='notify_url' style='display:none' value='"+payRequest.getNotifyUrl()+"'/><BR>");
		stringBuffer.append("<input type='text' name='out_trade_no' style='display:none' value='"+payRequest.getOutTradeNo()+"'/><BR>");
		stringBuffer.append("<input type='text' name='return_url' style='display:none' value='"+payRequest.getReturnUrl()+"'/><BR>");
		stringBuffer.append("<input type='text' name='sign' style='display:none' value='"+payRequest.getSign()+"'/><BR>");
		stringBuffer.append("<input type='text' name='buyer_id' style='display:none' value='"+payRequest.getBuyerId()+"'/><BR>");
		stringBuffer.append("<input type='text' name='total_fee' style='display:none' value='"+payRequest.getTotalFee()+"'/><BR>");
		stringBuffer.append("<input type='text' name='error_notify_url' style='display:none' value='"+payRequest.getErrorNotifyUrl()+"'/><BR>");
		stringBuffer.append("<input type='text' name='service' style='display:none' value='"+payRequest.getService()+"'/><BR>");
		stringBuffer.append("<input type='text' name='partner' style='display:none' value='"+payRequest.getPartner()+"'/><BR>");
		stringBuffer.append("<input type='text' name='seller_id' style='display:none' value='"+payRequest.getSellerId()+"'/><BR>");
		stringBuffer.append("<input type='text' name='payment_type' style='display:none' value='"+payRequest.getPaymentType()+"'/><BR>");
		stringBuffer.append("<input type='text' name='extra_common_param' style='display:none' value='"+payRequest.getExtraCommonParam()+"'/><BR>");
		stringBuffer.append("<input type='text' name='qr_pay_mode' style='display:none' value='"+payRequest.getQrPayMode()+"'/><BR>");
		stringBuffer.append("<input type='submit' value='确认' style='display:none' /></form>");
		stringBuffer.append("<script>document.forms['payForm'].submit();</script>");
		//将发起请求参数入库
		PayLog payLog = transToPayLog(payment,stringBuffer);
		payLogDao.insertPayLog(payLog);
		return stringBuffer.toString();
	}
	
	private void updateOrderBase(Payment payment){
		
		PayOrderBase payOrderBase = new PayOrderBase();
		payOrderBase.setMerchantCode(payment.getMerchantCode());
		payOrderBase.setOrderNumber(payment.getOrderNumber());
		payOrderBase.setBankCode(PaymentUtil.PAYMENT_PLATFORM_ALIPAY);
		payDao.updatePayOrderBase(payOrderBase);
	}
	
	public PayRequest getRequest(Payment payment, Merchant merchant){
		
		PayOrderBase payOrderBase = new PayOrderBase();
		
		payOrderBase.setOrderNumber(payment.getOrderNumber());
		payOrderBase.setMerchantCode(payment.getMerchantCode());
		payOrderBase = payDao.findByObject(payOrderBase);
		String body = payOrderBase.getOrderRemark();
		
		String extendParam = payment.getMerchantCode();
		
		String subject = payment.getMerchantCode();
		
		String signType = "MD5";
		
		MerchantPayment merchantPayment = new MerchantPayment();
		merchantPayment.setMerchantCode(payment.getMerchantCode());
		merchantPayment.setPaymentCode(PaymentUtil.PAYMENT_PLATFORM_ALIPAY);
		merchantPayment = merchantPaymentDao.findOne(merchantPayment);
		String sellerAccountName = merchantPayment.getPaymentAccount();
		
		PaymentAccount paymentAccount = new PaymentAccount();
		//paymentAccount.setMerchantCode(payment.getMerchantCode());	
		paymentAccount.setPaymentAccount(sellerAccountName);
		paymentAccount = paymentAccountDao.findOne(paymentAccount);
		String notifyUrl = paymentAccount.getNotifyUrl();
		
		String outTradeNo = payment.getOrderNumber();
		
		String returnUrl = paymentAccount.getReturnUrl();
		
		//String sign = "";
		
		String buyerId = "";
		
		String totalFee = (double)payment.getTotalPrice()/100+"";
		
		String errorNotifyUrl = paymentAccount.getErrorNotifyUrl();
			
		String service = "create_direct_pay_by_user";
		
		String partner = paymentAccount.getPayPartner();
		
		String sellerId = paymentAccount.getPayPartner();
		
		String paymentType = "1";
		
		String qrPayMode = "";
		
		String extraCommonParam = payment.getMerchantCode();
		
		Map<String,String> map = new HashMap<String,String>();
		
		if(body!=null&&!body.isEmpty()){
			map.put("body", body);
		}
		if(buyerId!=null&&!buyerId.isEmpty()){
			map.put("buyer_id", buyerId);
		}
		if(errorNotifyUrl!=null&&!errorNotifyUrl.isEmpty()){
			map.put("error_notify_url", errorNotifyUrl);
		}
		if(extendParam!=null&&!extendParam.isEmpty()){
			map.put("extend_param", extendParam);
		}
		if(extraCommonParam!=null&&!extraCommonParam.isEmpty()){
			map.put("extra_common_param", extraCommonParam);
		}
		if(notifyUrl!=null&&!notifyUrl.isEmpty()){
			map.put("notify_url", notifyUrl);
		}
		if(outTradeNo!=null&&!outTradeNo.isEmpty()){
			map.put("out_trade_no", outTradeNo);
		}
		if(partner!=null&&!partner.isEmpty()){
			map.put("partner", partner);
		}
		if(paymentType!=null&&!paymentType.isEmpty()){
			map.put("payment_type", paymentType);
		}
		if(qrPayMode!=null&&!qrPayMode.isEmpty()){
			map.put("qr_pay_mode", qrPayMode);
		}
		if(returnUrl!=null&&!returnUrl.isEmpty()){
			map.put("return_url", returnUrl);
		}
		if(sellerId!=null&&!sellerId.isEmpty()){
			map.put("seller_id", sellerId);
		}
		if(service!=null&&!service.isEmpty()){
			map.put("service", service);
		}
		if(subject!=null&&!subject.isEmpty()){
			map.put("subject", subject);
		}
		if(totalFee!=null&&!totalFee.isEmpty()){
			map.put("total_fee", totalFee);
		}
		
		String sign = AlipayCore.buildAlipayMysign(map,paymentAccount.getSecurity());
		
		PayRequest payRequest = new PayRequest();
		payRequest.setBody(body);
		payRequest.setBuyerId(buyerId);
		payRequest.setErrorNotifyUrl(errorNotifyUrl);
		payRequest.setExtendParam(extendParam);
		payRequest.setNotifyUrl(notifyUrl);
		payRequest.setOutTradeNo(outTradeNo);
		payRequest.setPartner(partner);
		payRequest.setPaymentType(paymentType);
		payRequest.setQrPayMode(qrPayMode);
		payRequest.setReturnUrl(returnUrl);
		payRequest.setSellerId(sellerId);
		payRequest.setService(service);
		payRequest.setSignType(signType);
		payRequest.setSubject(subject);
		payRequest.setTotalFee(totalFee);
		payRequest.setExtraCommonParam(extraCommonParam);
		payRequest.setSign(sign);
		
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
	
	
	public static void main(String[] args){
		Map map = new HashMap();
		map.put("errorNotifyUrl", "www.baidu.com");
		map.put("notifyUrl", "www.baidu.com");
		map.put("outTradeNo", "1111111111");
		map.put("partner", "2088412523706213");
		map.put("paymentType", "1");
		map.put("qrPayMode", "1");
		map.put("returnUrl", "www.baidu.com");
		map.put("service", "create_direct_pay_by_user");
		map.put("subject", "123123123");
		map.put("totalFee", "23");

		String sign = AlipayCore.buildAlipayMysign(map,"l2rkl7r5plpaxfyg7ahsoykpkvqxrsvw");
		
		System.out.println(sign);
	}

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.pay.service.inf.PaymentService#writeback(cn.seocoo.platform.payment.PaymentResult) 
	 */
	@Override
	public void writeback(HttpServletResponse response,PaymentResult backPayResult) {

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

	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.pay.service.inf.PaymentService#callInitiator(javax.servlet.http.HttpServletResponse, java.util.Map) 
	 */
	@Override
	public void callInitiator(Map map) {

		//NotifyData notify = parseNotifyData(map.get("notify_data").toString());
		PayOrderBase payOrderBase = new PayOrderBase();
		payOrderBase.setOrderNumber(map.get("out_trade_no").toString());
		payOrderBase = payDao.findByObject(payOrderBase);
		
		Map requestMap = new HashMap();
		requestMap.put("orderNumber", payOrderBase.getOrderNumber());
		requestMap.put("status", "SUCCESS");
		String resStr = null;
		try {
			resStr = new String(HttpUtils.post(payOrderBase.getCallBackUrl(), requestMap), "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("发起http请求异常",e);
		}
		lastPayLog(map,JSON.toJSONString(map));
		logger.info("*******************返回字符串是："+resStr+"***********************");
		//List<ResponseData> list = new ArrayList<ResponseData>();
		//ResponseData[] array = JSON.parseObject(resStr, ResponseData[].class);
		
	}
	
	private PayLog lastPayLog(Map map,String param){
		PayLog payLog = new PayLog();
		try{
			payLog.setCreateDate(DateUtil.getCurrentDate());
			payLog.setMsg(param);
			payLog.setOrderNumber(map.get("out_trade_no").toString());
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
		// TODO Auto-generated method stub
		return null;
	}
	
}
