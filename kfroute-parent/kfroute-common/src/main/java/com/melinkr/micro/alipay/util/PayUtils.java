
 /*
 * 文 件 名:  PayUtils.java
 * 版    权:  Tydic Technologies Co., Ltd. Copyright 1993-2012,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yanjp
 * 修改时间:  2013-9-7
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
  
package com.melinkr.micro.alipay.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tydic.framework.util.PropertyUtil;
import com.tydic.framework.util.SecretUtils;


 /**
 * 支付工具类
 * @author  yanjp
 * @version  [版本号, 2013-9-7]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */

final public class PayUtils {
    public static final Logger LOGGER=LoggerFactory.getLogger(PayUtils.class);
    /**
     * 回调地址Ip
     */
    public final static String PAYMENT_CALLBACK_URL="PAYMENT_CALLBACK_URL";
    public final static int DEFAULT_SEED=1000000000;
    /**
     * 创建交易流水号
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public final static String buildTranSeq() {
        StringBuilder builder = new StringBuilder();
        // 日期(格式:20080524)
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        builder.append(format.format(new Date()));
        //避免同时提交交易流水号冲突
        double rand=Math.random();
        builder.append((int)(rand*DEFAULT_SEED));
        //商城编码
        builder.append("SC");
        return builder.toString();
    }
    
    /**
     * 前台显示逻辑
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public final static String getCallbackPageURL(){
        String url=PropertyUtil.getProperty(PAYMENT_CALLBACK_URL);
        return url+"/alipay/callback/index.html";
    }
    /**
     *  后台处理逻辑
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public final static String getCallbackURL(){
        String url=PropertyUtil.getProperty(PAYMENT_CALLBACK_URL);
        return url+"/pay!callback.do";
    }
    /**
     * 支付平台IP配置在属性表
     * @return
     * @see [类、类#方法、类#成员]
     */
    public final static String  getPaymentPlatIp(){
        return "202.102.221.70";
    }
    
    /**
     * 获取HttpServletRequest
     * 中的值转换为Map
     * 如果是数组，用逗号分隔。
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Map<String,String> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + "|";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        String referer=request.getHeader("REFERER");
        returnMap.put(PAY_REFERER, referer);
        return returnMap;
    }
    public static String PAY_REFERER="PAY_REFERER";
    /**
     * 获取支付平台回调地址
     * <功能详细描述>
     * @param callbackMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String  getReferer(Map callbackMap){
        if(callbackMap.get("PAY_REFERER")==null){
            return null;
        }
        return String.valueOf(callbackMap.get("PAY_REFERER"));
    }
   
    /**
     * 分转换为元
     * @param integer
     * @return
     * @see [类、类#方法、类#成员]
     */
    public final static String toYuan(Integer price){
        BigDecimal   b   =   new   BigDecimal(price); 
        BigDecimal   b100   =   new   BigDecimal(100); 
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.##");
        return  df.format(b.divide(b100,2,BigDecimal.ROUND_HALF_UP));
    }
    
    /**
     * 分转换为元
     * @param integer
     * @return
     * @see [类、类#方法、类#成员]
     */
    public final static String toYuanWith2(Integer price){
        BigDecimal   b   =   new   BigDecimal(price); 
        BigDecimal   b100   =   new   BigDecimal(100); 
        return String.valueOf(b.divide(b100,2,BigDecimal.ROUND_HALF_UP)) ;
    }
    /**
     * 元转分
     * @param integer
     * @return
     * @see [类、类#方法、类#成员]
     */
    public final static String toFen(String price){
        BigDecimal   b   =   new   BigDecimal(price); 
        BigDecimal   b100   =   new   BigDecimal(100); 
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.##");         
        return  df.format(b.multiply(b100));
    }
    /**
     * 用=号链接key和value值
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public final static String[] mapToString(Map<String, String> map){
        List<String> tmp=new ArrayList<String>();
       Iterator<Map.Entry<String, String>>it=  map.entrySet().iterator();
       while (it.hasNext()) {
           Map.Entry<String, String> entry=it.next();
           String m=entry.getKey()+"="+entry.getValue();
           tmp.add(m);
       }
       String[] strs=new String[tmp.size()];
       for (int i = 0; i < strs.length; i++) {
           strs[i]=tmp.get(i);
       }
       return strs;
    }

    /**
     * 用特殊字符串链接数组
     * <功能详细描述>
     * @param strs
     * @param splitChar
     * @return
     * @see [类、类#方法、类#成员]
     */
    public final static String split(String[] strs,String splitChar){
        StringBuilder builder=new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            if(i!=0){
                builder.append(splitChar);
            }
            builder.append(strs[i]);
        }
        return builder.toString();
    }
    
    /**
     * 对商户秘钥进行解密
     * @param secretKey
     * @return
     * @see [类、类#方法、类#成员]
     */
    public final static String getSecretKey(String secretKey){
        String providerSecret=PropertyUtil.getProperty("providerSecret");
        String key=String.valueOf(SecretUtils.decrypt(secretKey, providerSecret));
        return key;
    }
    /**
     * 分期付款能力平台地址
     * @return
     * @see [类、类#方法、类#成员]
     */
    public final static String getBapPayUrl(String bapPay){
      String systemConfig=PropertyUtil.getProperty(bapPay);
      if(systemConfig==null){
          LOGGER.error("systemconfig.properties文件缺少:"+bapPay+"这个属性配置");
          return "";
      }
      return  systemConfig;  
    }
   
    
}