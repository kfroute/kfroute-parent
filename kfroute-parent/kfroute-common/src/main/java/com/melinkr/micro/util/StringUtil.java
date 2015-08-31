package com.melinkr.micro.util;


public class StringUtil {

	
	public static boolean isEmpty(String str){
		if(str!=null&&!"".equals(str)){
			return false;
		}
		return true;
	}
	
	
	public static long parseLong(String s, long defaultVal) {
		try{
			return Long.valueOf(s);
		}catch(Exception e) {
			return defaultVal;
		}
	}
	
	public static long parseLong(String s) {
		return parseLong(s, 0);
	}
	
	public static double parseDouble(String s, double defaultVal) {
		try{
			return Double.valueOf(s);
		}catch(Exception e) {
			return defaultVal;
		}
	}
	
	public static double parseDouble(String s) {
		return parseDouble(s, 0);
	}
	
	public static int parseInt(String s, int defaultVal) {
		try{
			return Integer.valueOf(s);
		}catch(Exception e) {
			return defaultVal;
		}
	}
	
	public static int parseInt(String s) {
		return parseInt(s, 0);
	}
	
	public static String markupEncode(String s) {
		if(s == null) {
			return "";
		}
		
		s = s.replaceAll("<", "&lt;");
		s = s.replaceAll(">", "&gt;");
		
		return s;
	}
	
	public static String replaceEmpty(String str) {
		return str == null ? "" : str;
	}
	
	/** 
	 * 判断是否包含字符串
	 * @param source 源字符串
	 * @param str 包含的字符
	 * @return
	 */
	public static boolean contain(String source, String str) {
		String[] strs = source.split(",");
		for (int i = 0; i < strs.length; i++) {
			if (str.equals(strs[i])) {
				return true;
			}
		}
		return false;
	}
	
	
}
