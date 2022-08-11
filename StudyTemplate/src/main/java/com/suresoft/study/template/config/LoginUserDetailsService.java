package com.suresoft.study.template.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.suresoft.study.template.jpa.domain.Member;
import com.suresoft.study.template.jpa.repository.MemberRepository;

/**
 *
 * UI 로부터 입력받은 ID 값을 RTMS 시스템 내부에 관리하고있는 사용자 정보를 가져와 처리하는 서비스 로직
 * 
 * @category Spring Service
 * @author Choi Yeon Ho
 */

@Service
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		Member userAccount = memberRepository.findOneByUserId(userId);

		if (userAccount == null ) {
			throw new UsernameNotFoundException("Login Fail!");
		}

		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(userAccount.getAuth()));

		return new LoginUserDetails(userAccount.getuserId(), userAccount.getPW(), authorities);
	}
}
