package cn.kfroute.platform.payment.merchantPayment.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;
import cn.kfroute.platform.payment.merchantPayment.service.inf.MerchantPaymentService;
import cn.kfroute.platform.payment.paymentAccount.service.inf.PaymentAccountService;
import cn.kfroute.platform.payment.paymentEntity.service.inf.PaymentEntityService;
import cn.seocoo.platform.payment.MerchantPayment;
import cn.seocoo.platform.payment.PaymentAccount;
import cn.seocoo.platform.payment.PaymentEntity;

import com.odchina.micro.base.BaseAction;

public class MerchantPaymentAction extends BaseAction{
	private MerchantPaymentService merchantPaymentService;
	private PaymentEntityService paymentEntityService;
	private List<MerchantPayment> merchantPaymentList;
	private PaymentAccountService paymentAccountService;
	
	public String findTheAll(){
		merchantPaymentList=merchantPaymentService.findAll();
		return "findTheAll";
	}
	
	public String findAll(){
		merchantPaymentList=merchantPaymentService.findAll();
		return "findAll";
	}
	
	
	/**
	 * 
	 * */
	public void add() throws IOException{
		String merchantCode=this.request.getParameter("merchantCode");
		String merchantName=this.request.getParameter("merchantName");
		String paymentCode=this.request.getParameter("paymentCode");
		//String paymentAccount=this.request.getParameter("paymentAccount");
		String paymentAccount=(new Date()).getTime()+""+(int)Math.random()*100;
		String type=this.request.getParameter("type");
		MerchantPayment mp=new MerchantPayment();
		mp.setMerchantCode(merchantCode);
		mp.setMerchantName(merchantName);
		mp.setPaymentCode(paymentCode);
		mp.setPaymentAccount(paymentAccount);
		PaymentEntity p1=new PaymentEntity();
		p1.setPaymentCode(paymentCode);
		PaymentEntity p=paymentEntityService.find(p1);
		mp.setPaymentDesc(p.getPaymentDesc());
		mp.setPaymentName(p.getPaymentName());
		mp.setPaymentUrl(p.getPaymentUrl());
		PaymentAccount pa=new PaymentAccount();
		pa.setMerchantCode(merchantCode);
		pa.setMerchantName(merchantName);
		pa.setPaymentAccount(paymentAccount);
		pa.setBankCode(paymentCode);
		pa.setBankName(p.getPaymentName());
		pa.setBanktDesc(p.getPaymentDesc());
		pa.setBankUrl(p.getPaymentUrl());
		JSONObject json=new JSONObject();
		if(type.equalsIgnoreCase("add")){
			MerchantPayment mp2=merchantPaymentService.find(mp);
			if(mp2!=null){
				json.put("result", "-1");
			}else{
				paymentAccountService.add(pa);
				merchantPaymentService.add(mp);
				json.put("result", "1");
			}
		}else if(type.equalsIgnoreCase("update")){
			mp.setId(Integer.parseInt(this.request.getParameter("id")));
			paymentAccount=this.request.getParameter("paymentAccount");
			mp.setPaymentAccount(paymentAccount);
			pa.setPaymentAccount(paymentAccount);
			merchantPaymentService.update(mp);
			paymentAccountService.update(pa);
			json.put("result", "1");
		}
		this.sendMessages(json.toString());
		
	}
	
	public void find() throws IOException{
		int id=Integer.parseInt(this.request.getParameter("id"));
		MerchantPayment mp=new MerchantPayment();
		mp.setId(id);
		MerchantPayment merchantPayment=merchantPaymentService.find(mp);
		JSONObject json=new JSONObject();
		json.put("result", "1");
		json.put("merchantPayment", merchantPayment);
		this.sendMessages(json.toString());
		
	}
	
	public void delete() throws IOException{
		int id=Integer.parseInt(this.request.getParameter("id"));
		MerchantPayment mp=new MerchantPayment();
		mp.setId(id);
		merchantPaymentService.delete(mp);
		JSONObject json=new JSONObject();
		json.put("result", "1");
		this.sendMessages(json.toString());
		
	}
	

	public MerchantPaymentService getMerchantPaymentService() {
		return merchantPaymentService;
	}

	public void setMerchantPaymentService(
			MerchantPaymentService merchantPaymentService) {
		this.merchantPaymentService = merchantPaymentService;
	}

	public PaymentEntityService getPaymentEntityService() {
		return paymentEntityService;
	}

	public void setPaymentEntityService(PaymentEntityService paymentEntityService) {
		this.paymentEntityService = paymentEntityService;
	}

	public List<MerchantPayment> getMerchantPaymentList() {
		return merchantPaymentList;
	}

	public void setMerchantPaymentList(List<MerchantPayment> merchantPaymentList) {
		this.merchantPaymentList = merchantPaymentList;
	}

	public PaymentAccountService getPaymentAccountService() {
		return paymentAccountService;
	}

	public void setPaymentAccountService(PaymentAccountService paymentAccountService) {
		this.paymentAccountService = paymentAccountService;
	}
	
	
	
}
