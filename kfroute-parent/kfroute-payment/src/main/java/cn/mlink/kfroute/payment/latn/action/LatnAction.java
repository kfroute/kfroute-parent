/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：LatnAction.java <br>
 * 创建时间：2015-5-29-下午07:51:19 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.latn.action;

import java.util.List;

import cn.kfroute.platform.payment.latn.service.inf.LatnService;
import cn.seocoo.platform.payment.Latn;

import com.odchina.micro.base.BaseAction;

public class LatnAction extends BaseAction {
	private LatnService latnService;
	private List<Latn> provinceList;
	private List<Latn> cityList;
	
	public String findAll(){
		provinceList=latnService.findAll();
		return "findAll";
	}
	
	public String findList(){
		int aiid=Integer.parseInt(this.request.getParameter("aiid"));
		Latn latn=new Latn();
		latn.setAiid(aiid);
		cityList=latnService.findList(latn);
		return "findList";
		
	}
	
	

	public LatnService getLatnService() {
		return latnService;
	}

	public void setLatnService(LatnService latnService) {
		this.latnService = latnService;
	}

	public List<Latn> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Latn> provinceList) {
		this.provinceList = provinceList;
	}

	public List<Latn> getCityList() {
		return cityList;
	}

	public void setCityList(List<Latn> cityList) {
		this.cityList = cityList;
	}
	
	
}
