package cn.geoip.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

public class CityService {
	public static void main(String[] args) throws IOException, GeoIp2Exception{
		WebServiceClient client = new WebServiceClient.Builder(42, "license_key").build();

		InetAddress ipAddress = InetAddress.getByName("124.113.167.159");

		// Do the lookup
		CityResponse response = client.city(ipAddress);

		Country country = response.getCountry();
		System.out.println(country.getIsoCode());            // 'US'
		System.out.println(country.getName());               // 'United States'
		System.out.println(country.getNames().get("zh-CN")); // '美国'

		Subdivision subdivision = response.getMostSpecificSubdivision();
		System.out.println(subdivision.getName());       // 'Minnesota'
		System.out.println(subdivision.getIsoCode());    // 'MN'

		City city = response.getCity();
		System.out.println(city.getName());       // 'Minneapolis'

		Postal postal = response.getPostal();
		System.out.println(postal.getCode());       // '55455'

		Location location = response.getLocation();
		System.out.println(location.getLatitude());        // 44.9733
		System.out.println(location.getLongitude()); 
	}
}
