package com.suresoft.study.template.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suresoft.study.template.jdbc.dto.MemberRequestDTO;
import com.suresoft.study.template.jpa.domain.Member;

public class MemberTableDAO {
	private static Logger log = LoggerFactory.getLogger("MemberControllerLog");

	Connection connection;

	/**
	 * 
	 * @param connection
	 */
	public MemberTableDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Member> selectAllMemberList() throws SQLException {
		List<Member> retVal = new ArrayList<Member>();

		StringBuffer query = new StringBuffer();
		query.append("  SELECT  	\n");
		query.append("        m.id, 	\n");
		query.append("        m.auth, 	\n");
		query.append("        m.department, 	\n");
		query.append("        m.name, 	\n");
		query.append("        m.position, 	\n");
		query.append("        m.pw, 	\n");
		query.append("        m.rank, 	\n");
		query.append("        m.user_id, 	\n");
		query.append("        m.ismaster 	\n");
		query.append("    FROM member m	\n");

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(query.toString());
			rs = ps.executeQuery();
			log.info(query.toString());

			while (rs.next()) {
				Member member = new Member();

				member.setId(rs.getString("id"));
				member.setAuth(rs.getString("auth"));
				member.setDepartment(rs.getString("department"));
				member.setName(rs.getString("name"));
				member.setPosition(rs.getString("position"));
				member.setPW(rs.getString("pw"));
				member.setRank(rs.getString("rank"));
				member.setuserId(rs.getString("user_id"));
				member.setIsmaster(rs.getInt("ismaster"));

				retVal.add(member);
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}

		return retVal;
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Member selectMemberByUserId(String userId) throws SQLException {
		Member retVal = new Member();

		StringBuffer query = new StringBuffer();
		query.append("  SELECT  	\n");
		query.append("        m.id, 	\n");
		query.append("        m.auth, 	\n");
		query.append("        m.department, 	\n");
		query.append("        m.name, 	\n");
		query.append("        m.position, 	\n");
		query.append("        m.pw, 	\n");
		query.append("        m.rank, 	\n");
		query.append("        m.user_id, 	\n");
		query.append("        m.ismaster 	\n");
		query.append("    FROM member m	\n");
		
		if (userId != null) {
			query.append("   WHERE m.user_id = ?	\n");			
		}

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(query.toString());
			if (userId != null) {
				ps.setString(1, userId);				
			}
			
			rs = ps.executeQuery();
			log.info(query.toString());

			while (rs.next()) {
				Member member = new Member();

				member.setId(rs.getString("id"));
				member.setAuth(rs.getString("auth"));
				member.setDepartment(rs.getString("department"));
				member.setName(rs.getString("name"));
				member.setPosition(rs.getString("position"));
				member.setPW(rs.getString("pw"));
				member.setRank(rs.getString("rank"));
				member.setuserId(rs.getString("user_id"));
				member.setIsmaster(rs.getInt("ismaster"));

				retVal = member;
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}

		return retVal;
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String insertMember(MemberRequestDTO memberRequestDTO) throws SQLException {
		
		StringBuffer query = new StringBuffer();
		query.append("  INSERT INTO 	\n");
		query.append("        member	\n");
		query.append("        VALUES (?,?,?,?,?,?,?,?,?) 	\n");
		String id = UUID.randomUUID().toString().replace("-", "");
		PreparedStatement ps = null;
		
		try {
			connection.setAutoCommit(false);
			
			ps = connection.prepareStatement(query.toString());
			
			ps.setString(1, id);//id - default
	        ps.setString(2, "ROLE_NORMAL");//auth - default
	        ps.setString(3, memberRequestDTO.getDepartment());//department
	        ps.setInt(4, 2);//ismaster - default
	        ps.setString(5, memberRequestDTO.getName());//name
	        ps.setString(6, memberRequestDTO.getPosition());//position
	        ps.setString(7, memberRequestDTO.getPw());//pw
	        ps.setString(8, memberRequestDTO.getRank());//rank
	        ps.setString(9, memberRequestDTO.getuserId());//userId
	        
	        ps.execute();
	        
			log.info(query.toString());

			ps.close();

		} catch (SQLException e) {
			if (ps != null) {
				ps.close();
			}
			return "insert - SQLException";
		} catch (Exception e) {
			if (ps != null) {
				ps.close();
			}
			return "insert - Exception";
		}
		connection.commit();
		
		return "insert success";
	}

	/**
	 * 
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
}
