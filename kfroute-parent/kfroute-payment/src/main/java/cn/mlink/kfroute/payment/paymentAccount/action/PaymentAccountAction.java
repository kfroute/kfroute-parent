package cn.kfroute.platform.payment.paymentAccount.action;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;
import cn.kfroute.platform.payment.merchantPayment.service.inf.MerchantPaymentService;
import cn.kfroute.platform.payment.paymentAccount.service.inf.PaymentAccountService;
import cn.kfroute.platform.payment.paymentEntity.service.inf.PaymentEntityService;
import cn.seocoo.platform.payment.MerchantPayment;
import cn.seocoo.platform.payment.PaymentAccount;
import cn.seocoo.platform.payment.PaymentEntity;

import com.odchina.micro.base.BaseAction;

public class PaymentAccountAction extends BaseAction {
	private PaymentAccountService paymentAccountService;
	private PaymentEntityService paymentEntityService;
	private List<PaymentAccount> paymentAccountList;
	private MerchantPaymentService merchantPaymentService;
	
	public String findAll(){
		paymentAccountList=paymentAccountService.findAll();
		return "findAll";
	}
	
	public void find() throws IOException{
		int id=Integer.parseInt(this.request.getParameter("id"));
		PaymentAccount pa1=new PaymentAccount();
		pa1.setId(id);
		PaymentAccount pa=paymentAccountService.find(pa1);
		JSONObject json=new JSONObject();
		json.put("result", "1");
		json.put("paymentAccount", pa);
		this.sendMessages(json.toString());
		
	}
	
	public void add() throws IOException{
		String merchantCode=this.request.getParameter("merchantCode");
		String paymentCode=this.request.getParameter("paymentCode");
		String paymentAccount=this.request.getParameter("paymentAccount");
		String relationAccount=this.request.getParameter("relationAccount");
		String security=this.request.getParameter("security");
		String paymentType=this.request.getParameter("paymentType");
		String feeStr=this.request.getParameter("fee");
		String reduceFeeStr=this.request.getParameter("reduceFee");
		String counterFeeStr=this.request.getParameter("counterFee");
		double fee=0;
		double reduceFee=0;
		int counterFee=0;
		if(feeStr!=null&&!feeStr.equalsIgnoreCase("")){
			fee=Double.parseDouble(feeStr);
		}
		if(reduceFeeStr!=null&&!reduceFeeStr.equalsIgnoreCase("")){
			reduceFee=Double.parseDouble(this.request.getParameter("reduceFee"));			
		}
		if(counterFeeStr!=null&&!counterFeeStr.equalsIgnoreCase("")){
			counterFee=Integer.parseInt(this.request.getParameter("counterFee"));			
		}
		String notifyUrl=this.request.getParameter("notifyUrl");
		String callBackUrl=this.request.getParameter("callBackUrl");
		String merchantUrl=this.request.getParameter("merchantUrl");
		String returnUrl=this.request.getParameter("returnUrl");
		String errorNotifyUrl=this.request.getParameter("errorNotifyUrl");
		String payPartner=this.request.getParameter("payPartner");
		String payPaternerKey=this.request.getParameter("payPaternerKey");
		String appkey=this.request.getParameter("appkey");
		String appId=this.request.getParameter("appId");
		
		String type=this.request.getParameter("type");
		PaymentEntity p1=new PaymentEntity();
		p1.setPaymentCode(paymentCode);
		PaymentEntity p=paymentEntityService.find(p1);
		PaymentAccount pa=new PaymentAccount();
		pa.setPaymentAccount(paymentAccount);
		pa.setRelationAccount(relationAccount);
		pa.setBankCode(p.getPaymentCode());
		pa.setBankName(p.getPaymentName());
		pa.setBanktDesc(p.getPaymentDesc());
		pa.setBankUrl(p.getPaymentUrl());
		pa.setSecurity(security);
		pa.setMerchantCode(merchantCode);
		pa.setPaymentType(paymentType);
		pa.setFee(fee);
		pa.setReduceFee(reduceFee);
		pa.setCounterFee(counterFee);
		pa.setNotifyUrl(notifyUrl);
		pa.setCallBackUrl(callBackUrl);
		pa.setMerchantUrl(merchantUrl);
		pa.setReturnUrl(returnUrl);
		pa.setErrorNotifyUrl(errorNotifyUrl);
		pa.setPayPartner(payPartner);
		pa.setPayPaternerKey(payPaternerKey);
		pa.setAppId(appId);
		pa.setAppkey(appkey);
		JSONObject json=new JSONObject();
		if(type.equalsIgnoreCase("add")){
			PaymentAccount pa2=paymentAccountService.find(pa);
			MerchantPayment mp1=new MerchantPayment();
			mp1.setMerchantCode(merchantCode);
			mp1.setPaymentCode(paymentCode);
			MerchantPayment mp=merchantPaymentService.find(mp1);
			if(mp!=null||pa2!=null){
				json.put("result", "-1");
			}else{
				paymentAccountService.add(pa);
				json.put("result", "1");
			}
		}else if(type.equalsIgnoreCase("update")){
			pa.setId(Integer.parseInt(this.request.getParameter("id")));
			paymentAccountService.update(pa);
			json.put("result", "1");
		}
		this.sendMessages(json.toString());
		
	}
	
	public void delete() throws IOException{
		int id=Integer.parseInt(this.request.getParameter("id"));
		PaymentAccount pa=new PaymentAccount();
		pa.setId(id);
		paymentAccountService.delete(pa);
		JSONObject json=new JSONObject();
		json.put("result", "1");
		this.sendMessages(json.toString());
		
	}
	
	
	public PaymentAccountService getPaymentAccountService() {
		return paymentAccountService;
	}

	public void setPaymentAccountService(PaymentAccountService paymentAccountService) {
		this.paymentAccountService = paymentAccountService;
	}


	public List<PaymentAccount> getPaymentAccountList() {
		return paymentAccountList;
	}


	public void setPaymentAccountList(List<PaymentAccount> paymentAccountList) {
		this.paymentAccountList = paymentAccountList;
	}

	public PaymentEntityService getPaymentEntityService() {
		return paymentEntityService;
	}

	public void setPaymentEntityService(PaymentEntityService paymentEntityService) {
		this.paymentEntityService = paymentEntityService;
	}

	public MerchantPaymentService getMerchantPaymentService() {
		return merchantPaymentService;
	}

	public void setMerchantPaymentService(
			MerchantPaymentService merchantPaymentService) {
		this.merchantPaymentService = merchantPaymentService;
	}
	
	
	
}
