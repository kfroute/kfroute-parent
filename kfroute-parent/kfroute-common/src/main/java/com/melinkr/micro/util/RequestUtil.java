package com.melinkr.micro.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tydic.wss.util.Const;

public class RequestUtil {

    public static final String ACTION_SUFFIX_PATTERN = ".+[.]do$";// Action结尾

    public static final String ACTION_SUFFIX = ".do";

    /**
     * 获取请求路径的根目录如/manange/user!add.do 返回为/manange/user.do
     * 
     * @param url
     * @return
     */
    public static String getRootUrl(String url) {
        if (url.indexOf(".do") != -1) {
            url = url.substring(0, url.indexOf(".do"));
        }

        if (url.indexOf(Const.SIGN) != -1) {
            url = url.substring(0, url.indexOf(Const.SIGN));
        }
        return addActionSuffix(url);
    }

    public static String subActionSuffix(String url) {
        if (url.matches(ACTION_SUFFIX_PATTERN)) {
            url = url.substring(0, url.indexOf(".do"));
        }
        return url;
    }

    public static String addActionSuffix(String url) {
        if (!url.matches(ACTION_SUFFIX_PATTERN)) {
            url += ".do";
        }
        return url;
    }

    /**
     * 获取请求路径的根目录如/manange/user!add.do 返回为add
     * 
     * @param url
     * @return
     */
    public static String getFunctionName(String url) {

        return url.substring(url.indexOf(Const.SIGN) + 1, url.indexOf(ACTION_SUFFIX));
    }

    /**
     * 返回全路径url
     * */
    public static String getFullURL(HttpServletRequest request) {
        String uri = getURI(request);
        String queryString = request.getQueryString();
        if (queryString == null) return uri;
        return uri + "?" + queryString;
    }

    /** 返回不带参数的URI */
    public static String getURI(HttpServletRequest request) {
        return request.getRequestURI();
    }

    public static boolean isAjax(HttpServletRequest request) {
        return request.getHeader("x-requested-with") != null
                && request.getHeader("x-requested-with").equalsIgnoreCase( // ajax超时处理
                        "XMLHttpRequest");
    }
    
    public static String getIp(HttpServletRequest request) {
    	String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {  
			ip = request.getRemoteAddr();  
	    } else {
	    	ip = request.getHeader("x-forwarded-for");  
	    }
		return ip;
    }
    
    public static String getIpRemote(HttpServletRequest request){
		String requestIp = request.getHeader("x-forwarded-for");
		if(requestIp == null || requestIp.length() == 0 || "unknown".equalsIgnoreCase(requestIp)) {
			requestIp = request.getHeader("Proxy-Client-IP");
		}
		if(requestIp == null || requestIp.length() == 0 || "unknown".equalsIgnoreCase(requestIp)) {
			requestIp = request.getHeader("WL-Proxy-Client-IP");
		}
		if(requestIp == null || requestIp.length() == 0 || "unknown".equalsIgnoreCase(requestIp)) {
			requestIp = request.getRemoteAddr();
		}
		return requestIp;
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
        return returnMap;
    }
}
