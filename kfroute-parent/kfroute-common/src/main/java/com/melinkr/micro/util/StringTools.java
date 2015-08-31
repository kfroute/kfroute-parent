package com.melinkr.micro.util;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringTools {

	public  static boolean isEmpty(String obj){
		
		if(obj!=null&&!"".equals(obj)){
			return false;
		}
		return true;
	}
	public static void main(String[] args) throws Exception{
		//String str="{\"msgBody\": [{\"PARAMS\": [{\"mac\":\"240A64FE432B\",\"wanIp\":\"192.168.1.1\",\"startupTimestamp\":\"2015-07-08 15:00:00\",\"remainedRam\":\"86343\",\"resourceIp\":\"2.2.2.2\",\"resourcePort\":\"10001\",\"clientConnNum\":\"24\",\"requestUrlNum\":\"123\",\"totalTraffic\":\"3423432\",\"totalForeignTraffic\":\"1234533\",\"prevTraffic\":\"3323432\",\"prevTotalForeignTraffic\":\"1134533\",\"circleTotalTraffic\":\"100000\",\"circleForeignTotalTraffic\":\"100000\"}]}],\"msgHead\": {\"ServiceCode\": \"uploadRouteMsg\", \"SrcSysID\": \"0001\",\"SrcSysSign\": \"0a2883e039909e776094edbed063bb88\",\"transactionID\": \"1436232616993\"}}";
		String str = "{\"msgBody\": [{\"PARAMS\": [{\"serverPortSysLogList\": [{\"portCircleTotalIncoming\": 34323423,\"portCircleTotalOutgoing\": 342342432,\"portPrevTotalIncoming\": 343242334,\"portPrevTotalOutgoing\": 3432432432,\"portTotalIncoming\": 4323234232,\"portTotalOutgoing\": 3432342432,\"serverId\": 200034,\"serverIp\": \"2.2.2.2\",\"serverPort\": 10034,\"slogId\": 0},{\"portCircleTotalIncoming\": 34323423,\"portCircleTotalOutgoing\": 342342432,\"portPrevTotalIncoming\": 343242334,\"portPrevTotalOutgoing\": 3432432432,\"portTotalIncoming\": 4323234232,\"portTotalOutgoing\": 3432342432,\"serverId\": 200034,\"serverIp\": \"2.2.2.2\",\"serverPort\": 10035,\"slogId\": 0}], \"serverSysLog\": {\"serverId\": \"23\",\"serverIp\": \"2.2.2.2\",\"cpuStatus\": \"64%\",\"memStatus\": \"已使用：20M，剩余20M\",\"diskStatus\": \"剩余20G\",\"currentConnNum\": \"89\",\"totalIncoming\": \"34232434\",\"totalOutgoing\": \"34232434\",\"prevTotalIncoming\": \"34232434\",\"prevTotalOutgoing\": \"34232434\",\"circleTotalIncoming\": \"34232434\",\"circleTotalOutgoing\": \"34232434\",\"processStatus\": \"0\",\"processDetail\": \"\"}}]}],\"msgHead\":{\"ServiceCode\": \"uploadSourceServerMsg\",\"SrcSysID\": \"0001\",\"SrcSysSign\": \"0a2883e039909e776094edbed063bb88\",\"transactionID\": \"1436232616993\"}}";
		System.out.println(str.length());
		System.out.println( Md5.md5Digest("123456").toLowerCase());
		byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        System.out.println(s); 
        String _s1 = "eyJtc2dCb2R5IjogW3siUEFSQU1TIjogW3sibWFjIjoiMjQwQTY0RkU0MzJCIiwid2FuSXAiOiIxOTIuMTY4LjEuMSIsInN0YXJ0dXBUaW1lc3RhbXAiOiIyMDE1LTA3LTA4IDE1OjAwOjAwIiwicmVtYWluZWRSYW0iOiI4NjM0MyIsInJlc291cmNlSXAiOiIyLjIuMi4yIiwicmVzb3VyY2VQb3J0IjoiMTAwMDEiLCJjbGllbnRDb25uTnVtIjoiMjQiLCJyZXF1ZXN0VXJsTnVtIjoiMTIzIiwidG90YWxUcmFmZmljIjoiMzQyMzQzMiIsInRvdGFsRm9yZWlnblRyYWZmaWMiOiIxMjM0NTMzIiwicHJldlRyYWZmaWMiOiIzMzIzNDMyIiwicHJldlRvdGFsRm9yZWlnblRyYWZmaWMiOiIxMTM0NTMzIiwiY2lyY2xlVG90YWxUcmFmZmljIjoiMTAwMDAwIiwiY2lyY2xlRm9yZWlnblRvdGFsVHJhZmZpYyI6IjEwMDAwMCJ9XX1dLCJtc2dIZWFkIjogeyJTZXJ2aWNlQ29kZSI6ICJ1cGxvYWRSb3V0ZU1zZyIsICJTcmNTeXNJRCI6ICIwMDAxIiwiU3JjU3lzU2lnbiI6ICIwYTI4ODNlMDM5OTA5ZTc3NjA5NGVkYmVkMDYzYmI4OCIsInRyYW5zYWN0aW9uSUQiOiAiMTQzNjIzMjYxNjk5MyJ9fQ==";
        System.out.println(_s1.length());
        BASE64Decoder decoder = new BASE64Decoder();  
        try {  
            byte[] b11 = decoder.decodeBuffer(_s1);  
            str = new String(b11, "utf-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        System.out.println(str);
	}
}
