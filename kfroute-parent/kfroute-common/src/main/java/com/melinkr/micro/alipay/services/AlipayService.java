package com.melinkr.micro.alipay.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.melinkr.micro.alipay.config.AlipayConfig;
import com.melinkr.micro.alipay.util.AlipaySubmit;

/* *
 *类名：AlipayService
 *功能：支付宝各接口构造类
 *详细：构造支付宝各接口请求参数
 *版本：3.2
 *修改日期：2011-03-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayService {
    
    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";

    /**
     * 构造即时到帐接口
     * @param sParaTemp 请求参数集合
     * @return 表单提交HTML信息
     */
    public static String create_direct_pay_by_user(Map<String, String> sParaTemp) {

//    	//增加基本配置
//        sParaTemp.put("service", "create_direct_pay_by_user");
//        sParaTemp.put("partner", AlipayConfig.partner);
//        sParaTemp.put("return_url", AlipayConfig.return_url);
//        sParaTemp.put("notify_url", AlipayConfig.notify_url);
//        sParaTemp.put("seller_email", AlipayConfig.seller_email);
//        sParaTemp.put("_input_charset", AlipayConfig.input_charset);

        String strButtonName = "确认";

        return AlipaySubmit.buildForm(sParaTemp, ALIPAY_GATEWAY_NEW, "get", strButtonName);
    }

    /**
     * 用于防钓鱼，调用接口query_timestamp来获取时间戳的处理函数
     * 注意：远程解析XML出错，与服务器是否支持SSL等配置有关
     * @return 时间戳字符串
     * @throws IOException
     * @throws DocumentException
     * @throws MalformedURLException
     */
    public static String query_timestamp() throws MalformedURLException,
                                                        DocumentException, IOException {

        //构造访问query_timestamp接口的URL串
        String strUrl = ALIPAY_GATEWAY_NEW + "service=query_timestamp&partner=" + AlipayConfig.partner;
        StringBuffer result = new StringBuffer();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(new URL(strUrl).openStream());

        List<Node> nodeList = doc.selectNodes("//alipay/*");

        for (Node node : nodeList) {
            // 截取部分不需要解析的信息
            if (node.getName().equals("is_success") && node.getText().equals("T")) {
                // 判断是否有成功标示
                List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
                for (Node node1 : nodeList1) {
                    result.append(node1.getText());
                }
            }
        }

        return result.toString();
    }
    
	public static String toAlipay(HttpServletRequest request,
			HttpServletResponse response, String billno, String subjectName, double titalPrice){
		////////////////////////////////////请求参数//////////////////////////////////////
		
		//必填参数//

		//请与贵网站订单系统中的唯一订单号匹配
		String out_trade_no = billno;
		//订单名称，显示在支付宝收银台里的“商品名称”里，显示在支付宝的交易管理的“商品名称”的列表里。
		String subject = subjectName;
		//订单描述、订单详细、订单备注，显示在支付宝收银台里的“商品描述”里
		String body = "";
		//订单总金额，显示在支付宝收银台里的“应付总额”里
		String amount = String.valueOf(titalPrice);
		String total_fee = amount;

		
		//扩展功能参数——默认支付方式//
		
		//默认支付方式，取值见“即时到帐接口”技术文档中的请求参数列表
		String paymethod = "";
		//默认网银代号，代号列表见“即时到帐接口”技术文档“附录”→“银行列表”
		String defaultbank = "";
		
		//扩展功能参数——防钓鱼//

		//防钓鱼时间戳
		String anti_phishing_key  = "";
		//获取客户端的IP地址，建议：编写获取客户端IP地址的程序
		String exter_invoke_ip= "";
		//注意：
		//1.请慎重选择是否开启防钓鱼功能
		//2.exter_invoke_ip、anti_phishing_key一旦被设置过，那么它们就会成为必填参数
		//3.开启防钓鱼功能后，服务器、本机电脑必须支持远程XML解析，请配置好该环境。
		//4.建议使用POST方式请求数据
		//示例：
		//anti_phishing_key = AlipayService.query_timestamp();	//获取防钓鱼时间戳函数
		//exter_invoke_ip = "202.1.1.1";
		
		//扩展功能参数——其他///
		
		//自定义参数，可存放任何内容（除=、&等特殊字符外），不会显示在页面上
		String extra_common_param = "";
		//默认买家支付宝账号
		String buyer_email = "";
		//商品展示地址，要用http:// 格式的完整路径，不允许加?id=123这类自定义参数
		//String show_url = "http://www.xxx.com/order/myorder.jsp";
		
		//扩展功能参数——分润(若要使用，请按照注释要求的格式赋值)//
		
		//提成类型，该值为固定值：10，不需要修改
		String royalty_type = "";
		//提成信息集
		String royalty_parameters ="";
		//注意：
		//与需要结合商户网站自身情况动态获取每笔交易的各分润收款账号、各分润金额、各分润说明。最多只能设置10条
		//各分润金额的总和须小于等于total_fee
		//提成信息集格式为：收款方Email_1^金额1^备注1|收款方Email_2^金额2^备注2
		//示例：
		//royalty_type = "10"
		//royalty_parameters	= "111@126.com^0.01^分润备注一|222@126.com^0.01^分润备注二"
		
		//////////////////////////////////////////////////////////////////////////////////
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("payment_type", "1");
        sParaTemp.put("out_trade_no", out_trade_no);
        sParaTemp.put("subject", subject);
        sParaTemp.put("body", body);
        sParaTemp.put("total_fee", total_fee);
        //sParaTemp.put("show_url", show_url);
        sParaTemp.put("paymethod", paymethod);
        sParaTemp.put("defaultbank", defaultbank);
        sParaTemp.put("anti_phishing_key", anti_phishing_key);
        sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
        sParaTemp.put("extra_common_param", extra_common_param);
        sParaTemp.put("buyer_email", buyer_email);
        sParaTemp.put("royalty_type", royalty_type);
        sParaTemp.put("royalty_parameters", royalty_parameters);
		
		//构造函数，生成请求URL
		String sHtmlText = create_direct_pay_by_user(sParaTemp);
		request.setAttribute("sHtmlText", sHtmlText);
		return "toAlipay";
	}
}
