package com.suresoft.study.template.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**  
*
* UI 로부터 넘어온 데이터를 DB 데이터를 확인하고 존재한다면  Spring User 객체로 만들어주기위한 재정의 클래스
* 사용자의 정보를 담는 인터페이스
* 
* @category Security User
* @author Choi Yeon Ho
*/

public class LoginUserDetails extends User {
	private static final long serialVersionUID = 1L;

	private long no;
	
	public LoginUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
				
//		super(username, new BCryptPasswordEncoder().encode(password), authorities);
		super(username, password, authorities);
		
	}

	public long getNo() {
		return no;
	}	
}
