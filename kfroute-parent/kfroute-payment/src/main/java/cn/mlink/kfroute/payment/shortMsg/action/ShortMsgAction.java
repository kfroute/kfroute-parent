/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：ShortMsgAction.java <br>
 * 创建时间：2015-5-27-下午02:29:59 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.shortMsg.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;
import cn.kfroute.platform.payment.shortMsg.service.inf.ShortMsgService;
import cn.seocoo.platform.payment.ShortMsg;

import com.odchina.micro.base.BaseAction;

public class ShortMsgAction extends BaseAction {
	private ShortMsgService shortMsgService;
	private List<ShortMsg> shortMsgList;
	
	public String findAll(){
		String merchantCode=this.queryMerchantCode();
		shortMsgList=shortMsgService.findAll(merchantCode);
		return "findAll";
	}
	
	public void find() throws IOException{
		int id=Integer.parseInt(this.request.getParameter("id"));
		ShortMsg s1=new ShortMsg();
		s1.setId(id);
		ShortMsg s=shortMsgService.find(s1);
		JSONObject json=new JSONObject();
		JSONObject obj=JSONObject.fromObject(s);
		json.put("result", "1");
		json.put("shortMsg", obj);
		this.sendMessages(json.toString());
		
	}
	
	public void add() throws IOException{
		JSONObject json=new JSONObject();
		String merchantCode=this.queryMerchantCode();
		int id=Integer.parseInt(this.request.getParameter("id"));
		int status=Integer.parseInt(this.request.getParameter("status"));
		String msgName=this.request.getParameter("msgName");
		String shortMsg=this.request.getParameter("shortMsg");
		ShortMsg s=new ShortMsg();
		s.setMerchantCode(merchantCode);
		s.setMsgName(msgName);
		s.setShortMsg(shortMsg);
		s.setStatus(status);
		if(status==1){
			shortMsgService.updateStatus(merchantCode);
		}
		if(id==0){
			s.setCreatDate(new Date());
			shortMsgService.add(s);
			json.put("result", "1");
		}else{
			s.setUpdateDate(new Date());
			s.setId(id);
			shortMsgService.update(s);
			json.put("result", "1");
		}
		this.sendMessages(json.toString());
		
	}
	
	public void delete() throws IOException{
		int id=Integer.parseInt(this.request.getParameter("id"));
		ShortMsg s=new ShortMsg();
		s.setId(id);
		shortMsgService.delete(s);
		JSONObject json=new JSONObject();
		json.put("result", "1");
		this.sendMessages(json.toString());
	}

	public ShortMsgService getShortMsgService() {
		return shortMsgService;
	}

	public void setShortMsgService(ShortMsgService shortMsgService) {
		this.shortMsgService = shortMsgService;
	}

	public List<ShortMsg> getShortMsgList() {
		return shortMsgList;
	}

	public void setShortMsgList(List<ShortMsg> shortMsgList) {
		this.shortMsgList = shortMsgList;
	}

	
}
