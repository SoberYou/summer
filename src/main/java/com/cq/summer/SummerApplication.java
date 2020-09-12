package com.cq.summer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.logging.Filter;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SummerApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(SummerApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SummerApplication.class);
	}

}

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//public class SummerApplication extends WebMvcConfigurerAdapter {
//
//	public static void main(String[] args) {
//		SpringApplication.run(SummerApplication.class, args);
//	}
//
//	@Bean
//	public FilterRegistrationBean httpFilter(){
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//
//		//指定对应的filter类
//		registrationBean.setFilter(new HttpFilter());
//
//		//定义需要拦截的url
//		registrationBean.addUrlPatterns("/threadLocal/*");
//
//		return registrationBean;
//	}
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
//	}
//}
