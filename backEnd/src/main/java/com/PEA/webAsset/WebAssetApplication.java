package com.PEA.webAsset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
public class WebAssetApplication extends SpringBootServletInitializer{
	private static final Logger logger = LoggerFactory.getLogger(WebAssetApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(WebAssetApplication.class, args);

		System.out.println("\t\t\t----------------\tstarted\t-----------------");
	}
	@Bean
	CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}

}
