package com.melinkr.micro.util;

import java.text.DecimalFormat;

public class LatiLongDistanceUtil {
	private static final double EARTH_RADIUS = 6378137;
	    private static double rad(double d)
	    {
	       return d * Math.PI / 180.0;
	    }
	    
	    /**
	     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	     * @param lng1
	     * @param lat1
	     * @param lng2
	     * @param lat2
	     * @return
	     */
	    public static String getDistance(double lng1, double lat1, double lng2, double lat2)
	    {
	    	DecimalFormat df = new DecimalFormat("0.00");
			double radLat1 = rad(lat1);
			double radLat2 = rad(lat2);
			double a = radLat1 - radLat2;
			double b = rad(lng1) - rad(lng2);
			double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
			Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
			s = s * EARTH_RADIUS;
			s = Math.round(s * 10000) / 10000;
			return df.format(s);
	    }
	    /**
	     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为千米
	     * @param lng1
	     * @param lat1
	     * @param lng2
	     * @param lat2
	     * @return
	     */
	    public static String getDistanceKm(double lng1, double lat1, double lng2, double lat2)
	    {
	    	DecimalFormat df = new DecimalFormat("#.00");
			double radLat1 = rad(lat1);
			double radLat2 = rad(lat2);
			double a = radLat1 - radLat2;
			double b = rad(lng1) - rad(lng2);
			double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
			Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
			s = s * EARTH_RADIUS;
			s = Math.round(s * 10000) / 10000000.00;
			return df.format(s);
	    }
	    
	    
	    /**
	     * @param args
	     */
	    public static void main(String[] args)
	    {
	    // TODO 自动生成方法存根
	        String distance = getDistanceKm(117.2808,31.8639,-122.4194,37.7749);
	        
	        System.out.println("Distance is:"+distance);

	    }
}
