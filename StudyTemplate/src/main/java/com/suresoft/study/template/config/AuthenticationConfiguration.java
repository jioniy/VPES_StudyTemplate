package com.suresoft.study.template.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**  
*
* 여러 설정이 가능하지만 인증 검사를 수행할시 사용할 Encoder를 설정하는데 활용 한다.
* 만약 다른 암호화 모듈을 적용하고자 한다면 passwordEncoder() 함수에서 반영하면 된다.
* 
* @category Spring Configuration
* @author Choi Yeon Ho
*/

@Configuration
public class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	PasswordEncoder passwordEncoder() {
		// 스프링에서 제공하는 기본 암호 인코더
		return new BCryptPasswordEncoder();
		// RTMS 커스텀 인코더를 사용하고있다 임시로. 필요시 사용... 근데 필요할일이 있을까?
		// return new RtmsPasswordEncoder();
	}
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}
