package com.suresoft.study.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudyTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyTemplateApplication.class, args);
	}
	
	/**
	 * 도움말의 비디오 읽어올 때 에러 발생 필터 처리
	 * */
	
	@Bean
	public ErrorPageFilter errorPageFilter() {
	    return new ErrorPageFilter();
	}

	@Bean
	public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
	    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	    filterRegistrationBean.setFilter(filter);
	    filterRegistrationBean.setEnabled(false);
	    return filterRegistrationBean;
	}
}
