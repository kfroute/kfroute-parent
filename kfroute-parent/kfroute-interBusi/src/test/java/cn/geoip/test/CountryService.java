package cn.geoip.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

public class CountryService {
	public static void main(String[] args) throws IOException, GeoIp2Exception{
		WebServiceClient client = new WebServiceClient.Builder(42, "000000000000").build();

		InetAddress ipAddress = InetAddress.getByName("128.101.101.101");

		// Do the lookup
		CountryResponse response = client.country(ipAddress);

		Country country = response.getCountry();
		System.out.println(country.getIsoCode());            // 'US'
		System.out.println(country.getName());               // 'United States'
		System.out.println(country.getNames().get("zh-CN")); // '美国'
	}
}
