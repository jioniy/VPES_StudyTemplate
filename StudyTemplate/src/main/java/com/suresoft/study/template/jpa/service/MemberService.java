package com.suresoft.study.template.jpa.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suresoft.study.template.jpa.domain.Member;
import com.suresoft.study.template.jpa.repository.MemberRepository;

/**
 * @author Choi Yeon Ho
 */

@Service
public class MemberService {
	private static Logger log = LoggerFactory.getLogger("MemberControllerLog");

	@Autowired
	MemberRepository memberRepository;

	/**
	 * 
	 * @return
	 */
	public List<Member> getMemberList() {
		log.info("Select Member List by JPA Way.");
		return this.memberRepository.findAll();
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public Member getMemberByUserId(String userId) {
		log.info("Select Member by User Id Parameter.");
		return this.memberRepository.findOneByUserId(userId);
	}
	
	public boolean checkPwValidation(String pw) {
		//영문 숫자 모두 포함하는지 확인
		if (pw.matches(".*[A-Za-z].*")&&pw.matches(".*[0-9].*")&&!pw.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣].*")) {//영문자 숫자 포함, 한글 미포함 여부 확인
			return true;
		}else {
			return false;
		}
		
	}
}

