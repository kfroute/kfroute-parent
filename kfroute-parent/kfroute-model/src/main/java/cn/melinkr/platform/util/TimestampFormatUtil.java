package cn.melinkr.platform.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TimestampFormatUtil {
	public static  DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String format(Timestamp timestmp){
		if(timestmp==null){
			return null;
		}else{
			return sdf.format(timestmp);
		}
	} 
}	
