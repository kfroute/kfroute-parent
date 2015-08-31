package cn.geoip.test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class GeoIpTest { 
    public static void main(String[] args) throws Exception { 
        String license_key = "YOUR_LICENSE_KEY"; 
        String ip_address = "114.215.128.113"; 
 
        String url_str = "http://geoip.maxmind.com/e?l=" + license_key + "&i=" + ip_address; 
 
        URL url = new URL(url_str); 
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream())); 
        String inLine; 
         
        while ((inLine = in.readLine()) != null) { 
            // Alternatively use a CSV parser here.  
            Pattern p = Pattern.compile("\"([^\"]*)\"|(?<=,|^)([^,]*)(?:,|$)");  
            Matcher m = p.matcher(inLine); 
 
            ArrayList fields = new ArrayList(); 
            String f; 
            while (m.find()) { 
                f = m.group(1); 
                if (f!=null) { 
                    fields.add(f); 
                } 
                else { 
                    fields.add(m.group(2)); 
                } 
            } 
             
            String countrycode = (String)fields.get(0); 
            String countryname = (String)fields.get(1); 
            String regioncode = (String)fields.get(2); 
            String regionname = (String)fields.get(3); 
            String city = (String)fields.get(4); 
            String lat = (String)fields.get(5); 
            String lon = (String)fields.get(6); 
            String metrocode = (String)fields.get(7); 
            String areacode = (String)fields.get(8); 
            String timezone = (String)fields.get(9); 
            String continent = (String)fields.get(10); 
            String postalcode = (String)fields.get(11); 
            String isp = (String)fields.get(12); 
            String org = (String)fields.get(13); 
            String domain = (String)fields.get(14); 
            String asnum = (String)fields.get(15); 
            String netspeed = (String)fields.get(16); 
            String usertype = (String)fields.get(17); 
            String accuracyradius = (String)fields.get(18); 
            String countryconf = (String)fields.get(19); 
            String cityconf = (String)fields.get(20); 
            String regionconf = (String)fields.get(21); 
            String postalconf = (String)fields.get(22); 
            String error = (String)fields.get(23); 
            System.out.println(countrycode);
            System.out.println(countryname);
            System.out.println(regioncode);
            System.out.println(regionname);
            System.out.println(city);
            System.out.println(lat);
            System.out.println(lon);
            System.out.println(metrocode);
            System.out.println(areacode);
        } 
        
        in.close(); 
    } 
} 