/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：MerchantAction.java <br>
 * 创建时间：2015-5-29-下午05:15:46 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.merchant.action;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONObject;
import cn.kfroute.platform.payment.latn.service.inf.LatnService;
import cn.kfroute.platform.payment.merchant.service.inf.MerchantService;
import cn.seocoo.platform.payment.Latn;
import cn.seocoo.platform.payment.MerchantEntity;

import com.odchina.micro.base.BaseAction;

public class MerchantAction extends BaseAction {
	private MerchantService merchantService;
	private List<MerchantEntity> merchantList;
	private List<Latn> latnList;
	private LatnService latnService;
	
	public String findAll(){
		merchantList=merchantService.findAll();
		latnList=latnService.findTheAll();
		return "findAll";
	}
	
	public void find() throws IOException{
		MerchantEntity m1=new MerchantEntity();
		m1.setId(Integer.parseInt(this.request.getParameter("id")));
		MerchantEntity me=merchantService.find(m1);
		JSONObject json=new JSONObject();
		json.put("result", "1");
		json.put("merchant", me);
		this.sendMessages(json.toString());
	}
	
	
	/**
	 * @throws IOException 
	 * */
	public void add() throws IOException{
		JSONObject json=new JSONObject();
		int id=Integer.parseInt(this.request.getParameter("id"));
		String type=this.request.getParameter("type");
		String merchantCode=this.request.getParameter("merchantCode");
		String merchantName=this.request.getParameter("merchantName");
		String merchantAddr=this.request.getParameter("merchantAddr");
		String linkInfo=this.request.getParameter("linkInfo");
		String manage=this.request.getParameter("manage");
		String provinceId=this.request.getParameter("provinceId");
		String latnId=this.request.getParameter("latnInfo");
		int status=Integer.parseInt(this.request.getParameter("status"));
		MerchantEntity me=new MerchantEntity();
		me.setMerchantCode(merchantCode);
		if(type.equalsIgnoreCase("add")){
			MerchantEntity m1=merchantService.find(me);
			if(m1!=null){
				json.put("result", "-1");
			}else{
				me.setMerchantName(merchantName);
				me.setMerchantAddr(merchantAddr);
				me.setLinkInfo(linkInfo);
				me.setProvinceId(provinceId);
				me.setLatnId(latnId);
				me.setStatus(status);
				me.setManage(manage);
				merchantService.add(me);
				json.put("result", "1");
			}
			
		}else if(type.equalsIgnoreCase("update")){
			me.setId(id);
			me.setMerchantName(merchantName);
			me.setMerchantAddr(merchantAddr);
			me.setLinkInfo(linkInfo);
			me.setProvinceId(provinceId);
			me.setLatnId(latnId);
			me.setStatus(status);
			me.setManage(manage);
			merchantService.update(me);
			json.put("result", "1");
		}
		this.sendMessages(json.toString());
		
	}
	
	public void delete() throws IOException{
		int id=Integer.parseInt(this.request.getParameter("id"));
		MerchantEntity me=new MerchantEntity();
		me.setId(id);
		merchantService.delete(me);
		JSONObject json=new JSONObject();
		json.put("result", "1");
		this.sendMessages(json.toString());
		
	}
	

	
	public MerchantService getMerchantService() {
		return merchantService;
	}
	public void setMerchantService(MerchantService merchantService) {
		this.merchantService = merchantService;
	}
	public List<MerchantEntity> getMerchantList() {
		return merchantList;
	}
	public void setMerchantList(List<MerchantEntity> merchantList) {
		this.merchantList = merchantList;
	}

	public List<Latn> getLatnList() {
		return latnList;
	}

	public void setLatnList(List<Latn> latnList) {
		this.latnList = latnList;
	}

	public LatnService getLatnService() {
		return latnService;
	}

	public void setLatnService(LatnService latnService) {
		this.latnService = latnService;
	}
	
	
	
}
