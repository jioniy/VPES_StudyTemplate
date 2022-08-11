package com.suresoft.study.template.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.suresoft.study.template.jpa.domain.Member;

/**
 * @author Choi Yeon Ho
 */

public interface MemberRepository extends JpaRepository<Member, String> {

	@Query("SELECT m FROM Member m WHERE m.name = :name")
	public Member findOneByName(@Param("name") String name);

	@Query("SELECT m FROM Member m WHERE m.userId = :userId")
	public Member findOneByUserId(@Param("userId") String userId);

	@Query("SELECT m FROM Member m WHERE m.id = :id")
	public Member fineOneById(@Param("id") String id);

	@Query("SELECT m FROM Member m WHERE m.ismaster != 0")
	public List<Member> findByAllMemberListExceptMaster();

	@Modifying
	@Transactional
	@Query("UPDATE Member m set m.id = id, m.department = :department, m.position = :position, m.rank = :rank," + "m.auth = :auth, m.ismaster = :ismaster where m.userId = :userId")
	public void updateByUserId(@Param("userId") String userId, @Param("department") String department, @Param("position") String position, @Param("rank") String rank, @Param("auth") String auth, @Param("ismaster") int ismaster);

	@Modifying
	@Transactional
	@Query("UPDATE Member m set m.id = id, m.pw = :pw, m.department = :department, m.position = :position, m.rank = :rank," + "m.auth = :auth, m.ismaster = :ismaster where m.userId = :userId")
	public void updateByUserIdPW(@Param("userId") String userId, @Param("pw") String pw, @Param("department") String department, @Param("position") String position, @Param("rank") String rank, @Param("auth") String auth, @Param("ismaster") int ismaster);

	
	
	
}