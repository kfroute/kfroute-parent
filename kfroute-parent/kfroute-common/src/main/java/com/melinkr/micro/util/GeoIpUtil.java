package com.melinkr.micro.util;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;
import com.melinkr.micro.geoip.entity.GeoIpLocation;

public class GeoIpUtil {
	private static DatabaseReader reader = null;
	static{
		File database = new File(SystemConfigUtil.getSingleProperty("geoip_file_path").getPropertyValue());
		
		// This creates the DatabaseReader object, which should be reused across
		// lookups.
		
		try {
			reader = new DatabaseReader.Builder(database).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static GeoIpLocation getLocationBean(String ip) throws IOException, GeoIp2Exception{
		InetAddress ipAddress = InetAddress.getByName(ip);
		CityResponse response = reader.city(ipAddress);
		
		Country country = response.getCountry();
		Subdivision subdivision = response.getMostSpecificSubdivision();
		City city = response.getCity();
		Postal postal = response.getPostal();
		Location location = response.getLocation();
		GeoIpLocation geoIpLocation = new GeoIpLocation();
		
		geoIpLocation.setCountry(country);
		geoIpLocation.setCity(city);
		geoIpLocation.setSubdivision(subdivision);
		geoIpLocation.setPostal(postal);
		geoIpLocation.setLocation(location);
		return geoIpLocation;
	}
	
}
