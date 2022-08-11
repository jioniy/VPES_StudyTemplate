package com.suresoft.study.template.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.suresoft.study.template.common.constant.StudyConstant;
import com.suresoft.study.template.common.enums.ResultCode;
import com.suresoft.study.template.jdbc.dto.MemberRequestDTO;
import com.suresoft.study.template.jdbc.service.JdbcMemberService;
import com.suresoft.study.template.jpa.domain.Member;
import com.suresoft.study.template.jpa.service.MemberService;

/**
 * @author Choi Yeon Ho
 */

@Controller
@RequestMapping("/member")
public class MemberController {
	private static Logger log = LoggerFactory.getLogger("MemberControllerLog");

	@Autowired
	MemberService memberService;
	
	@Autowired
	JdbcMemberService jdbcMemberService;

	/**
	 * GET MEMBER LIST
	 * 
	 * JPA Way
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/jpa/memberlist", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getMemberListByJPA(Principal principal) {
		HashMap<String, Object> retVal = new HashMap<String, Object>();

		List<Member> memberList = this.memberService.getMemberList();
		log.info("Member List size - " + memberList.size());

		HashMap<String, Object> conditionData = new HashMap<String, Object>();
		conditionData.put("MEMBERLIST", memberList);

		retVal.put(StudyConstant.KEY_STR_RESULT_CODE, ResultCode.OK.name());
		retVal.put(StudyConstant.KEY_STR_RESULT_DATA, conditionData);
		retVal.put(StudyConstant.KEY_STR_RESULT_MESSAGE, "Data load success");
		return new ResponseEntity<HashMap<String, Object>>(retVal, HttpStatus.OK);
	}
	
	/**
	 * GET MEMBER LIST
	 * 
	 * JPA Way - Vue
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/jpa/memberlistVue", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> getMemberListByJPA2(Principal principal) {
		HashMap<String, Object> retVal = new HashMap<String, Object>();

		List<Member> memberList = this.memberService.getMemberList();
		log.info("Member List size - " + memberList.size());

		HashMap<String, Object> conditionData = new HashMap<String, Object>();
		conditionData.put("MEMBERLIST", memberList);

		retVal.put(StudyConstant.KEY_STR_RESULT_CODE, ResultCode.OK.name());
		retVal.put(StudyConstant.KEY_STR_RESULT_DATA, conditionData);
		retVal.put(StudyConstant.KEY_STR_RESULT_MESSAGE, "Data load success");
		return new ResponseEntity<HashMap<String, Object>>(retVal, HttpStatus.OK);
	}
	
	/**
	 * GET MEMBER LIST
	 * 
	 * JDBC Way
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/jdbc/memberlist", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getMemberListByJDBC(Principal principal) {
		HashMap<String, Object> retVal = new HashMap<String, Object>();

		List<Member> memberList = this.jdbcMemberService.getMemberList();		
		log.info("Member List size - " + memberList.size());

		HashMap<String, Object> conditionData = new HashMap<String, Object>();
		conditionData.put("MEMBERLIST", memberList);

		retVal.put(StudyConstant.KEY_STR_RESULT_CODE, ResultCode.OK.name());
		retVal.put(StudyConstant.KEY_STR_RESULT_DATA, conditionData);
		retVal.put(StudyConstant.KEY_STR_RESULT_MESSAGE, "Data load success");
		return new ResponseEntity<HashMap<String, Object>>(retVal, HttpStatus.OK);
	}
	
	/**
	 * CREATE MEMBER
	 * 
	 * JDBC Way
	 * 
	 * @param MemberRequestDTO
	 * @return
	 * */
	
	@RequestMapping(value = "/jdbc/memberJoin", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> createMember(MemberRequestDTO memberRequestDTO) {
		HashMap<String, Object> retVal = new HashMap<String, Object>();
		
		//비밀번호 암호화
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		memberRequestDTO.setPw(pwEncoder.encode(memberRequestDTO.getPw()));
		
		String msg = this.jdbcMemberService.insertMember(memberRequestDTO);

		retVal.put(StudyConstant.KEY_STR_RESULT_MESSAGE, msg);
		return new ResponseEntity<HashMap<String, Object>>(retVal, HttpStatus.OK);
	}
	
	/**
	 * Check overlapped by userId
	 * 
	 * JDBC Way
	 * 
	 * @param userId
	 * @return
	 * */
	@RequestMapping(value = "/jpa/isMemberOverlapped", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> checkOverlappedUseId(@RequestParam(value = "userId") String userId) {
		HashMap<String, Object> retVal = new HashMap<String, Object>();

		Member exist_member = this.memberService.getMemberByUserId(userId);
		
		if(exist_member==null) {
			retVal.put(StudyConstant.KEY_STR_RESULT_MESSAGE, "unoverlapped");
		}else {
			retVal.put(StudyConstant.KEY_STR_RESULT_MESSAGE, "overlapped");
		}
		
		return new ResponseEntity<HashMap<String, Object>>(retVal, HttpStatus.OK);
	}
	
	/**
	 * Check pw words validation
	 * 
	 * 
	 *
	 * @param pw
	 * @return
	 * */
	@RequestMapping(value = "/isPwAvailable", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> isPwAvailable(@RequestParam(value = "pw") String pw) {
		HashMap<String, Object> retVal = new HashMap<String, Object>();

		boolean pw_valid = this.memberService.checkPwValidation(pw);
		
		if(pw_valid==true) {
			retVal.put(StudyConstant.KEY_STR_RESULT_MESSAGE, "available");
		}else {
			retVal.put(StudyConstant.KEY_STR_RESULT_MESSAGE, "unavailable");
		}
		
		return new ResponseEntity<HashMap<String, Object>>(retVal, HttpStatus.OK);
	}
	
	/**
	 * GET CURRENT MEMBER
	 * 
	 * JPA Way
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/jpa/member", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getCurrentMemberByJPA(Principal principal) {
		HashMap<String, Object> retVal = new HashMap<String, Object>();
		
		Member current_member = memberService.getMemberByUserId(principal.getName());
		
		HashMap<String, Object> conditionData = new HashMap<String, Object>();
		conditionData.put("MYINFO",  current_member);

		retVal.put(StudyConstant.KEY_STR_RESULT_CODE, ResultCode.OK.name());
		retVal.put(StudyConstant.KEY_STR_RESULT_DATA, conditionData);
		retVal.put(StudyConstant.KEY_STR_RESULT_MESSAGE, "Data load success");
		return new ResponseEntity<HashMap<String, Object>>(retVal, HttpStatus.OK);
	}
	
	
}
