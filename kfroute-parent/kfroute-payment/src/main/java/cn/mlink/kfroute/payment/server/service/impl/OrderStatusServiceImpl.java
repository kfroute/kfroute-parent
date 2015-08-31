/* 
 * Copyrights 尚果科技平台研发项目组 @ 2015，尚果科技 SEOCOO Computer Co., Ltd.<br>
 * 项目名称：尚果科技-平台开发组 <br>
 * 文件名称：OrderStatusServiceImpl.java <br>
 * 创建时间：2015-5-28-上午10:24:06 <br>
 * 创建用户：admin<br>
 * Description： 
 * History：<br> [ Author Date Version Content ]<br>
 *  
 */
package cn.kfroute.platform.payment.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.kfroute.platform.payment.server.dao.inf.PayDao;
import cn.kfroute.platform.payment.server.service.inf.Service;
import cn.seocoo.platform.payment.PayOrderBase;
import cn.seocoo.platform.unite.Reslut;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class OrderStatusServiceImpl implements Service {

	private static final Logger logger = Logger.getLogger(PayServiceImpl.class);
	private PayDao payDao;
	

	@Override
	public Object sevice(String param) {
		List<PayOrderBase> orderBaseList=JSONObject.parseArray(param, PayOrderBase.class);
		List<String> orderNumberList=new ArrayList<String>();
		List<String> merchantCodeList=new ArrayList<String>();
		for(PayOrderBase pob:orderBaseList){
			orderNumberList.add(pob.getOrderNumber());
			merchantCodeList.add(pob.getMerchantCode());
		}
		Map<String,List<String>> map=new HashMap<String,List<String>>();
		map.put("orderNumberList", orderNumberList);
		map.put("merchantCodeList", merchantCodeList);
		List<PayOrderBase> returnList=payDao.findOrderStatus(map);
		//查询菜单
		Reslut reslut=new Reslut();
		reslut.setResultCode("SUCCESS");
		reslut.setResultData(returnList);
		String msg=JSON.toJSONString(reslut);
		return msg;
	}
	
	
	public PayDao getPayDao() {
		return payDao;
	}
	public void setPayDao(PayDao payDao) {
		this.payDao = payDao;
	}


}
