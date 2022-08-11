package com.suresoft.study.template.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**  
*
* 페스워드 등 암호화된 정보를 취급하기 위해 구현하는 암호화 모듈
* 기본적으로 스프링에서 사용하는 BCryptPasswordEncoder 를 사용 하므로 사용할일은 없을것이다.
* 만약 기본 인코딩 기술이아닌 SHA 같은 강력한 암호화 기술을 사용하고자 한다면 encode() 함수를 재 정의 할 것
* 
* @category Helper
* @author Choi Yeon Ho
*/

public class RtmsPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		// 여기서는 이렇게 처리하였지만 예를들어 sha-2 / sha-3 같은 해시를 접목시킬 수 있다.
		// 여기서는 간단히 EN-을 붙여 확인하는 용도!
		return "" + rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// rawPassword 현재 들어온 값 | encodedPassword 매칭되는 계정에 있는 값
		return encodedPassword.equals(encode(rawPassword));
	}

}
