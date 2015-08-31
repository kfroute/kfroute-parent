package com.melinkr.micro.util;

import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class ConfigurationFactory {

	private static Configuration configuration;

	public static Configuration getInstance() throws IOException {
		if (configuration == null) {
			configuration = new Configuration();
			configuration.setClassForTemplateLoading(ConfigurationFactory.class, "/freemarker/");
			configuration.setDefaultEncoding("UTF-8");
			configuration.setObjectWrapper(new DefaultObjectWrapper());
		}
		return configuration;
	}

}
