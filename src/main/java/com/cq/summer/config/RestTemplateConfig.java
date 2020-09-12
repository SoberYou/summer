package com.cq.summer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

//	@Value("${restTemplatereadTimeOut}")
//	private Integer readTimeOut;
//
//	@Value("${restTemplateConnectTimeout}")
//	private Integer connectTimeout;

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		RestTemplate restTemplate = new RestTemplate(factory);
		return restTemplate;
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		//可用配置文件
		factory.setReadTimeout(5000);// ms
		factory.setConnectTimeout(5000);// ms
		return factory;
	}
}
