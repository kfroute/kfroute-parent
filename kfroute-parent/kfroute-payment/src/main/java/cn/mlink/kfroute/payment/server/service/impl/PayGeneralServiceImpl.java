/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：PayGeneralServiceImpl.java <br>
 * 创建时间：2015-5-29-上午11:02:23 <br>
 * 创建用户：renyang<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.server.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.kfroute.platform.payment.merchantPayment.dao.inf.MerchantPaymentDao;
import cn.kfroute.platform.payment.paymentAccount.dao.inf.PaymentAccountDao;
import cn.kfroute.platform.payment.server.dao.inf.PayDao;
import cn.kfroute.platform.payment.server.service.inf.PayGeneralService;
import cn.seocoo.platform.payment.MerchantPayment;
import cn.seocoo.platform.payment.PayOrderBase;
import cn.seocoo.platform.payment.PaymentAccount;

import com.odchina.micro.alipay.util.AlipayCore;

public class PayGeneralServiceImpl implements PayGeneralService {
	private static final Logger logger = Logger.getLogger(PayGeneralServiceImpl.class);
	private PaymentAccountDao paymentAccountDao;
	private MerchantPaymentDao merchantPaymentDao;
	private PayDao payDao;
	/* (non-Javadoc) 
	 * @see cn.seocoo.platform.payment.server.service.inf.PayGeneralService#verifyParams(java.util.Map) 
	 */
	@Override
	public boolean verifyParams(Map map) {
		
		String outTradeNo = null;
	    
	    Map<String,String> paramMap = new HashMap<String,String>();
	    if(map.get("out_trade_no")!=null&&!map.get("out_trade_no").toString().isEmpty()){
	    	paramMap.put("out_trade_no", map.get("out_trade_no").toString());
	    	outTradeNo = map.get("out_trade_no").toString();
		}
	    if(map.get("result")!=null&&!map.get("result").toString().isEmpty()){
	    	paramMap.put("result", map.get("result").toString());
		}
	    if(map.get("trade_no")!=null&&!map.get("trade_no").toString().isEmpty()){
	    	paramMap.put("trade_no", map.get("trade_no").toString());
		}
	    if(map.get("PAY_REFERER")!=null&&!map.get("PAY_REFERER").toString().isEmpty()){
	    	paramMap.put("PAY_REFERER", map.get("PAY_REFERER").toString());
		}
	    if(map.get("request_token")!=null&&!map.get("request_token").toString().isEmpty()){
	    	paramMap.put("request_token", map.get("request_token").toString());
		}
	    
	    
	    PayOrderBase payOrderBase = new PayOrderBase();
	    payOrderBase.setPlatformOrderNumber(outTradeNo);  
	    payOrderBase = payDao.findByObject(payOrderBase);
	    
	    MerchantPayment merchantPayment = new MerchantPayment();
		merchantPayment.setMerchantCode(payOrderBase.getMerchantCode());
		merchantPayment.setPaymentCode("alipay");
		merchantPayment = merchantPaymentDao.findOne(merchantPayment);
		String sellerAccountName = merchantPayment.getPaymentAccount();
		
	    PaymentAccount paymentAccount = new PaymentAccount();
		paymentAccount.setMerchantCode(payOrderBase.getMerchantCode());	
		paymentAccount.setPaymentAccount(sellerAccountName);
		paymentAccount = paymentAccountDao.findOne(paymentAccount);

		String sign = AlipayCore.buildAlipayMysign(paramMap,paymentAccount.getSecurity());
		System.out.println("=============================mysign:=="+sign+"=========alipay Sign:=="+map.get("sign")+"==============");
    	logger.info("=============================mysign:=="+sign+"=========alipay Sign:=="+map.get("sign")+"==============");
    	logger.debug("=============================mysign:=="+sign+"=========alipay Sign:=="+map.get("sign")+"==============");
    	logger.warn("=============================mysign:=="+sign+"=========alipay Sign:=="+map.get("sign")+"==============");
		if(sign.equals(map.get("sign"))){
			return true;
		}
		return false;
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
	public PayDao getPayDao() {
		return payDao;
	}
	public void setPayDao(PayDao payDao) {
		this.payDao = payDao;
	}
	

}
