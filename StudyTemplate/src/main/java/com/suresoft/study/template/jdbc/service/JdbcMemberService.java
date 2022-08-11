package com.suresoft.study.template.jdbc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.suresoft.study.template.jdbc.dao.MemberTableDAO;
import com.suresoft.study.template.jdbc.dto.MemberRequestDTO;
import com.suresoft.study.template.jpa.domain.Member;

/**
 * @author Choi Yeon Ho
 */

@Service
public class JdbcMemberService {
	private static Logger log = LoggerFactory.getLogger("MemberControllerLog");

	@Autowired
	private JdbcTemplate jdbc;

	/**
	 * 
	 * @return
	 */
	public List<Member> getMemberList() {
		List<Member> retVal = new ArrayList<Member>();

		MemberTableDAO memberTableDAO = null;
		try {
			memberTableDAO = new MemberTableDAO(jdbc.getDataSource().getConnection());

			retVal = memberTableDAO.selectAllMemberList();

			// DB Close
			memberTableDAO.closeConnection();

		} catch (SQLException e) {
			log.error("SQLException", e);
		}
		
		return retVal;
	}
	
	public Member getMemberByUserId(String userId) {
		Member retVal = new Member();
		
		MemberTableDAO memberTableDAO = null;
		try {
			memberTableDAO = new MemberTableDAO(jdbc.getDataSource().getConnection());

			retVal = memberTableDAO.selectMemberByUserId(userId);

			// DB Close
			memberTableDAO.closeConnection();

		} catch (SQLException e) {
			log.error("SQLException", e);
		}
		
		return retVal;
	}
	
	public String insertMember(MemberRequestDTO memberRequestDTO) {
		MemberTableDAO memberTableDAO = null;
		String check_value="";
		try {
			memberTableDAO = new MemberTableDAO(jdbc.getDataSource().getConnection());

			check_value = memberTableDAO.insertMember(memberRequestDTO);
			
			System.out.println(check_value);
			
			// DB Close
			memberTableDAO.closeConnection();
		} catch (SQLException e) {
			log.error("SQLException", e);
		}
		return check_value;
		
		
	}
}
