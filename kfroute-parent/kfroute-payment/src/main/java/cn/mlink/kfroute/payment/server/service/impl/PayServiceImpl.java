package cn.kfroute.platform.payment.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.kfroute.platform.payment.merchantPayment.dao.inf.MerchantPaymentDao;
import cn.kfroute.platform.payment.paymentAccount.dao.inf.PaymentAccountDao;
import cn.kfroute.platform.payment.paymentEntity.dao.inf.PaymentEntityDao;
import cn.kfroute.platform.payment.server.dao.inf.PayDao;
import cn.kfroute.platform.payment.server.dao.inf.PayLogDao;
import cn.kfroute.platform.payment.server.service.inf.Service;
import cn.seocoo.platform.payment.MerchantPayment;
import cn.seocoo.platform.payment.PayLog;
import cn.seocoo.platform.payment.PayOrderBase;
import cn.seocoo.platform.payment.PayReslut;
import cn.seocoo.platform.payment.PaymentAccount;
import cn.seocoo.platform.payment.PaymentEntity;
import cn.seocoo.platform.payment.alipay.PaymentData;

import com.odchina.micro.constant.Constant;
import com.odchina.micro.util.DateUtils;
import com.tydic.framework.util.DateUtil;

public class PayServiceImpl implements Service {

	private static final Logger logger = Logger.getLogger(PayServiceImpl.class);
	
	private PayDao payDao;
    private PaymentAccountDao paymentAccountDao;
    private MerchantPaymentDao merchantPaymentDao;
    private PaymentEntityDao paymentEntityDao;
    private PayLogDao payLogDao;
	@Override
	public Object sevice(String param) {
		
		String platformOrderNumber = entryLog(param);
		Map<String,String> paramMap=this.parseParam(param);
		PayReslut payReslut = new PayReslut();
		if(paramMap.get("callType").equalsIgnoreCase(Constant.CALL_WAP)){
			payReslut.setReturnCode("wapBankList");
		}else if(paramMap.get("callType").equalsIgnoreCase(Constant.CALL_WEB)){
			payReslut.setReturnCode("webBankList");
		}
		payReslut.setType(Constant.REQUST_RETIRN_LOACL);
		PayOrderBase payOrderBase = new PayOrderBase();
		//Map<String,Object> resMap = new HashMap<String,Object>();
		try {
			payOrderBase = orderToDatebase(initOrderMap(paramMap),platformOrderNumber);
		} catch (Exception e) {
			payReslut.setData("");
			return payReslut;
		}
		if(payOrderBase.isExist()){
			payReslut.setOrderPaid(true);
		}
		PaymentData paymentData = generateParamData(paramMap,platformOrderNumber);
		payReslut.setData(paymentData);
		return payReslut;
	}
	
	private String entryLog(String param){
		PayLog payLog = new PayLog();
		Map<String,String> paramMap=this.parseParam(param);
		String platformOrderNumber = DateUtils.toLong(DateUtils.currentDate())+(int) (Math.random()*9000+1000)+"";
		try{
			payLog.setCreateDate(DateUtil.getCurrentDate());
			payLog.setMsg(param);
			payLog.setOrderNumber(paramMap.get("orderNumber"));
			
			payLog.setPlatformOrderNumber(platformOrderNumber);
			payLog.setPayType("0");
			
			PaymentAccount paymentAccount = new PaymentAccount();
			paymentAccount.setMerchantCode(paramMap.get("merchantCode"));
			paymentAccount.setBankCode("待确定");
			paymentAccount = paymentAccountDao.findOne(paymentAccount);
			String notifyUrl = paymentAccount.getNotifyUrl();
			
			payLog.setUrl(notifyUrl);
		}catch(Exception e){
			logger.error("所需字段未填写！", e);
		}
		payLogDao.insertPayLog(payLog);
		return platformOrderNumber;
	}
	
	private boolean isAvailable(Map<String,String> paramMap){
		
		return false;
	}
	
	private PaymentData generateParamData(Map<String,String> paramMap,String platformOrderNumber){
		//String terminalType = paramMap.get("terminalType");
		String terminalType = "wap";
		String merchantCode = paramMap.get("merchantCode");
		MerchantPayment merchantPayment = new MerchantPayment();
		merchantPayment.setMerchantCode(merchantCode);
		
		PaymentData paymentData = new PaymentData();
		paymentData.setMerchantCode(merchantCode);
		paymentData.setMerchantName(paramMap.get("merchantName"));
		paymentData.setServiceType(paramMap.get("serviceType"));
		paymentData.setTotalPrice(paramMap.get("totalPrice"));
		paymentData.setCallType(paramMap.get("callType"));
		paymentData.setPaymentName(paramMap.get("name"));
		paymentData.setOrderNumber(paramMap.get("orderNumber"));
		paymentData.setPlatformOrderNumber(platformOrderNumber);
		
		List<MerchantPayment> mpList = merchantPaymentDao.findByObject(merchantPayment);
		List<PaymentEntity> peList = paymentEntityDao.findAll();
		List<PaymentEntity> resList = new ArrayList<PaymentEntity>();
		for(PaymentEntity pe:peList){
			for(MerchantPayment mp:mpList){
				if(mp.getPaymentCode().equals(pe.getPaymentCode())&&terminalType.equals(pe.getTerminalType())){
					resList.add(pe);
					break;
				}
			}
		}
		paymentData.setPaymentList(resList);
		return paymentData;
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
	
	private PayOrderBase orderToDatebase(Map<String,String> map,String platformOrderNumber) throws Exception{
		PayOrderBase payOrderBase = new PayOrderBase();
		//判断订单是否存在
		int orderStatus = isExist(map);
		if(orderStatus == 1){
			payOrderBase.setExist(true);
			return payOrderBase;
		}else if(orderStatus == 0){
			updateOrderTime(map);
			payOrderBase.setExist(false);
			return payOrderBase;
		}else{
			String orderNumber = map.get("orderNumber");
			String totalPrice = map.get("totalPrice");
			String orderPrice = map.get("orderPrice");
			String bankCode = map.get("bankCode");
			String bankName = map.get("bankName");
			String channel = map.get("channel");
			String orderType = map.get("orderType");
			String payment = map.get("payment");
			String status = map.get("status");
			String orderDate = DateUtils.toString(DateUtils.currentDate());
			String orderRemark = map.get("orderRemark");
			String userId = map.get("userId");
			String userName = map.get("userName");
			String userType = map.get("userType");
			String openId = map.get("openId");
			String latnId = map.get("latnId");
			String merchantCode = map.get("merchantCode");
			String merchantName = map.get("merchantName");
			String areaId = map.get("areaId");
			String couponCode = map.get("couponCode");
			String couponType = map.get("couponType");
			String couponAmount = map.get("couponAmount");
			String recommend = map.get("recommend");
			
			try{
				
				payOrderBase.setBankCode(bankCode);
				payOrderBase.setBankName(bankName);
				payOrderBase.setChannel(channel);
				payOrderBase.setLatnId(latnId);
				payOrderBase.setMerchantCode(merchantCode);
				payOrderBase.setMerchantName(merchantName);
				payOrderBase.setOpenId(openId);
				payOrderBase.setOrderDate(DateUtils.parse(orderDate));
				payOrderBase.setOrderNumber(orderNumber);
				payOrderBase.setOrderPrice(Double.parseDouble(orderPrice));
				payOrderBase.setOrderRemark(orderRemark);
				payOrderBase.setOrderType(Integer.parseInt(orderType));
				payOrderBase.setPayment(payment);
				payOrderBase.setStatus(Integer.parseInt(status));
				payOrderBase.setTotalPrice(Double.parseDouble(totalPrice));
				payOrderBase.setUserId(userId);
				payOrderBase.setUserName(userName);
				payOrderBase.setUserType(userType);
				payOrderBase.setAreaId(areaId);
				payOrderBase.setCouponCode(couponCode);
				payOrderBase.setCouponAmount(Double.parseDouble(couponAmount));
				payOrderBase.setCouponType(couponType);
				payOrderBase.setRecommend(recommend);
				//payOrderBase.setCallBackUrl(callBackUrl);
				payOrderBase.setBackStatus("0");
				payOrderBase.setPlatformOrderNumber(platformOrderNumber);
				payOrderBase.setExist(false);
				
			}catch(Exception e){
				logger.error("必选数据未填写！",e);
			}
			payDao.insertPayOrderBase(payOrderBase);
			return payOrderBase;
		}
	}
	
	private void updateOrderTime(Map<String,String> map){
		PayOrderBase payOrderBase = new PayOrderBase();
		payOrderBase.setOrderDate(DateUtils.currentDate());
		payOrderBase.setMerchantCode(map.get("merchantCode"));
		payOrderBase.setOrderNumber(map.get("orderNumber"));
		payDao.updatePayOrderBase(payOrderBase);
	}
	
	private int isExist(Map<String,String> map){
		String orderNumber = map.get("orderNumber");
		String merchantCode = map.get("merchantCode");
		PayOrderBase payOrderBase = new PayOrderBase();
		payOrderBase.setOrderNumber(orderNumber);
		payOrderBase.setMerchantCode(merchantCode);
		PayOrderBase pb = payDao.findByObject(payOrderBase);
		if(pb == null){
			return 2;
		}else if(pb.getStatus()==1){
			return 1;
		}else if(pb.getStatus()==0){
			return 0;
		}
		return 3;
	}
	
	public Map initOrderMap(Map<String,String> map){
		if(!(map.get("orderNumber")!=null)){
			map.put("orderNumber", "");
		}
		if(!(map.get("totalPrice")!=null)){
			map.put("totalPrice", "0.0");
		}
		if(!(map.get("orderPrice")!=null)){
			map.put("orderPrice", "0.0");
		}
		if(!(map.get("bankCode")!=null)){
			map.put("bankCode", "");
		}
		if(!(map.get("bankName")!=null)){
			map.put("bankName", "");
		}
		if(!(map.get("channel")!=null)){
			map.put("channel", "");
		}
		if(!(map.get("orderType")!=null)){
			map.put("orderType", "0");
		}
		if(!(map.get("payment")!=null)){
			map.put("payment", "");
		}
		if(!(map.get("status")!=null)){
			map.put("status", "0");
		}
		if(!(map.get("orderDate")!=null)){
			map.put("orderDate", "");
		}
		if(!(map.get("orderRemark")!=null)){
			map.put("orderRemark", "");
		}
		if(!(map.get("userId")!=null)){
			map.put("userId", "");
		}
		if(!(map.get("userName")!=null)){
			map.put("userName", "");
		}
		if(!(map.get("userType")!=null)){
			map.put("userType", "");
		}
		if(!(map.get("openId")!=null)){
			map.put("openId", "");
		}
		if(!(map.get("latnId")!=null)){
			map.put("latnId", "");
		}
		if(!(map.get("merchantName")!=null)){
			map.put("merchantName", "");
		}
		if(!(map.get("areaId")!=null)){
			map.put("areaId", "");
		}
		if(!(map.get("couponCode")!=null)){
			map.put("couponCode", "");
		}
		if(!(map.get("couponType")!=null)){
			map.put("couponType", "");
		}
		if(!(map.get("couponAmount")!=null)){
			map.put("couponAmount", "0.0");
		}
		if(!(map.get("recommend")!=null)){
			map.put("recommend", "");
		}
		if(!(map.get("callBackUrl")!=null)){
			map.put("callBackUrl", "");
		}
		if(!(map.get("backStatus")!=null)){
			map.put("backStatus", "");
		}
		if(!(map.get("merchantCode")!=null)){
			map.put("merchantCode", "");
		}
		
		return map;
	}
	
	public PayDao getPayDao() {
		return payDao;
	}
	public void setPayDao(PayDao payDao) {
		this.payDao = payDao;
	}
	public PaymentAccountDao getPaymentAccountDao() {
		return paymentAccountDao;
	}
	public void setPaymentAccountDao(PaymentAccountDao paymentAccountDao) {
		this.paymentAccountDao = paymentAccountDao;
	}
	public MerchantPaymentDao getMerchantPaymentDao() {
		return merchantPaymentDao;
	}
	public void setMerchantPaymentDao(MerchantPaymentDao merchantPaymentDao) {
		this.merchantPaymentDao = merchantPaymentDao;
	}

	public PaymentEntityDao getPaymentEntityDao() {
		return paymentEntityDao;
	}

	public void setPaymentEntityDao(PaymentEntityDao paymentEntityDao) {
		this.paymentEntityDao = paymentEntityDao;
	}

	public PayLogDao getPayLogDao() {
		return payLogDao;
	}

	public void setPayLogDao(PayLogDao payLogDao) {
		this.payLogDao = payLogDao;
	}
	
}
