package com.suresoft.study.template.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 
 * 스프링 Security 설정을 정의 하는 Class
 * 각 페이지 인증 적용 여부를 담당하며 페이지 권한 설정을 위해서는 이 클레스를 수정
 *
 * @category Spring Config - Security Implementation
 * @author Choi Yeon Ho
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		// Root 페이지에 대해서만 기본  접속 가능 페이지로 설정
		web.ignoring().antMatchers("/lib/**", "/css/**", "/image/**", "/mainPNG.png");		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// 상단 Root 페이지를 제외한 모든 페이지 인증 필요페이지로 설정
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/login", "/LoginControll/register", "/LoginControll/showUser", 
		"/SettingControll/findImg", "/ProjectControll/projectListAll", "/ProjectControll/projectInfo",
		"/LicenseControll/licence","/join","/member/jdbc/memberJoin","/member/jpa/isMemberOverlapped","/member/isPwAvailable").permitAll()
		//user session이 남아있으면 접근 불가
		//.antMatchers("/join","/member/jdbc/memberJoin","/member/jpa/isMemberOverlapped","/member/isPwAvailable").anonymous()
		.antMatchers("/Setting").hasAuthority("ROLE_ADMIN")		
		.antMatchers("/**").authenticated();
		
		// 인증이 필요한경우 권한이 없을때, 로그인페이지를 호출 그리고 성공시 getUsersTable URL 호출
		// http.formLogin().loginProcessingUrl("/login").defaultSuccessUrl("/getUsersTable");
		http.formLogin().loginPage("/login").defaultSuccessUrl("/", true);
		// 로그아웃 을 호출한 경우 처리되는 URL -> 성공후 Root 페이지로
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
				.deleteCookies("JSESSIONID");
		
		http.sessionManagement().maximumSessions(100)
		.maxSessionsPreventsLogin(false)
		.expiredUrl("/login")
		.sessionRegistry(sessionRegistry());
		
//		http.securityContext().securityContextRepository(new NullSecurityContextRepository());
		
		// Csrf TorkenDate Cookie로 유지
		// 중간 페이지 이동할 때 발생하는 Csrf 오류 방지
		//http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		http.csrf().disable();
		
		http.addFilterAfter(ajaxSessionTimeoutFilter(), ExceptionTranslationFilter.class);
		
	}

	/**
	 * LDAP 서버 연동 데이터 가져오기 Example : ldap://localhost:10389/dc=example,dc=com
	 * Bind DN or user : uid=admin,ou=system ( 거의 기본셋팅 ) Bind Password : secret
	 * userSearchBase : 유저 조직별 구분
	 * roleAttributes : role
	 */
//	@Configuration
//	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
//
//		@Override
//		public void init(AuthenticationManagerBuilder auth) throws Exception {
//			auth.ldapAuthentication().userSearchBase("ou=people").userSearchFilter("(uid={0})")
//			.contextSource().url("ldap://localhost:10389/dc=example,dc=com").managerDn("uid=admin,ou=system")
//			.managerPassword("secret").and().rolePrefix("ROLE_").groupRoleAttribute("cn");
//		}
//
//	}
	
	@Bean
	public static AjaxSessionTimeoutFilter ajaxSessionTimeoutFilter(){
		return new AjaxSessionTimeoutFilter();
	}
	
	@Bean
    SessionRegistry sessionRegistry() {			
        return new SessionRegistryImpl();
    }
	
    @Bean
    public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {		//(5) 
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }	
}
