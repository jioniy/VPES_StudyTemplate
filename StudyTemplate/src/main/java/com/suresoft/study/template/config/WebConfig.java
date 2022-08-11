package com.suresoft.study.template.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 *
 * 기본적인 MVC 관련 설정이나 기타 리소스 관련 설정을 담당한다 상황에 맞춰 필요한 설정을 추가 해주면 된다
 * 
 * @category Spring Configuration
 * @author Choi Yeon Ho
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * 동적 Locale 적용을 위한 Resource Bundle 파일 설정 관련 파일은 여기 resource/static/messages/messages_ko_KR.properties
	 * 
	 * @return MessageSource
	 * @see resource/static/messages/messages_ko_KR.properties
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename("static/messages/messages");
		resourceBundleMessageSource.setDefaultEncoding("UTF-8");
		return resourceBundleMessageSource;
	}

	/**
	 * 현재 접속한 세션 기준으로 로케일을 설정 하는 역할
	 * 
	 * @return LocaleResolver
	 * @see resource/static/messages/messages_ko_KR.properties
	 */
	@Bean(name = "localeResolver")
	public LocaleResolver sessionLocaleResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		// 기본 로케일 강제 지정
		localeResolver.setDefaultLocale(new Locale("ko_KR"));
		return localeResolver;
	}

	/**
	 * UI 또는 세션에서 부터 로케일 정보를 받아 변경하는 인터럽트 로직
	 * 
	 * @return LocaleChangeInterceptor
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		// request에 language 파라미터로 설정된 값을 읽어들여 그 값으로 Locale 을 변경시킨다
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}

	/**
	 * 상단의 로케일 변경 인터럽트를 등록하는 역할
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	/**
	 * 구성 및 css 관련 파일들 저장 static 디렉토리를 리소스로 등록하여 static 하위 디렉토리 및 파일 정보를 사용할 수 있음
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/").setCachePeriod(3600).resourceChain(true).addResolver(new PathResourceResolver());
	}
}
