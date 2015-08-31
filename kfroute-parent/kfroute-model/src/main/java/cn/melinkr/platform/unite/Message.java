package cn.melinkr.platform.unite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.melinkr.platform.util.Md5;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author: zhangyl
 * @time: 2015-07-06 23:20
 * @version: 1.0
 * 请求报文信息
 * ReqHead:请求头部信息
 * ReqBody:请求具体参数信息
 *
 */
public class Message {
	@JSONField(name="msgBody")
	private List<MsgBody> msgBody;//请求头
	@JSONField(name="msgHead")
	private MsgHead msgHead;//请求体
	private String remoteIp;
	public List<MsgBody> getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(List<MsgBody> msgBody) {
		this.msgBody = msgBody;
	}
	public MsgHead getMsgHead() {
		return msgHead;
	}
	public void setMsgHead(MsgHead msgHead) {
		this.msgHead = msgHead;
	}
	public static void main(String[] args) throws Exception{
		Message msg = new Message();
		System.out.println(new Random().nextInt(1));
		List<MsgBody> msgBodys= new ArrayList<MsgBody>();
		MsgHead msgHead = new MsgHead();
		msgHead.setServiceCode("helloWorld");
		msgHead.setSrcSysID("0001");
		String transactionID = System.currentTimeMillis()+"";
		System.out.println(transactionID);
		msgHead.setTransactionID(transactionID);
		String sign = Md5.MD5("routerOnlineNotify-0001-1439347340985-"+"09d08113-74b4-4ada-a04e-17358b6d0203");//88888888为密钥
		System.out.println(Md5.md5Digest("routerOnlineNotify-0001-1439347340985-09d08113-74b4-4ada-a04e-17358b6d0203").toString());
		System.out.println(Md5.MD5("123456"));
		System.out.println(sign);
		System.out.println("++++++++++>"+Md5.md5Digest("${default.password}"));
		
		System.out.println(Md5.md5Digest("123456"));
		System.out.println(Md5.MD5("123456"));
		System.out.println(Md5.byteArrayToHexString(Md5.md5Digest("a".getBytes("UTF-8"))));
		System.out.println(Md5.MD5("a"));
		
		msgHead.setSrcSysSign(sign);
		msg.setMsgHead(msgHead);
		MsgBody msgBody= new MsgBody();
		List<Param> params = new ArrayList<Param>();
		Param param = new Param();
		param.setId("id1");
		param.setValue("1");
		params.add(param);
		JSONObject json=new JSONObject();
		msgBody.setParams(json.toJSONString(params));
		msgBodys.add(msgBody);
		msg.setMsgBody(msgBodys);
		
		json.put("result",msg);
		System.out.println(json.toString());
	}
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	
	
	
	
}
class Param{
	private String id;
	private String value;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
