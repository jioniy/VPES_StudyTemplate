package com.suresoft.study.template.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Choi Yeon Ho
 */

@Entity
@Table(name = "member")
public class Member implements Serializable {
	private static final long serialVersionUID = -6023100508850610579L;

	@Id
	@Column(name = "id", nullable = false)
	private String id;

	@Column(name = "userId", nullable = false)
	private String userId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "pw", nullable = false)
	private String pw;

	@Column(name = "auth", nullable = false)
	private String auth;

	@Column(name = "department")
	private String department;

	@Column(name = "rank")
	private String rank;

	@Column(name = "position")
	private String position;

	@NotNull
	@Column(name = "ismaster")
	private int ismaster;

	public void setId(String id) {
		this.id = id;
	}

	public void setuserId(String userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPW(String pw) {
		this.pw = pw;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getId() {
		return id;
	}

	public String getuserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getPW() {
		return pw;
	}

	public String getAuth() {
		return auth;
	}

	public String getDepartment() {
		return department;
	}

	public String getRank() {
		return rank;
	}

	public String getPosition() {
		return position;
	}

	public int getIsmaster() {
		return ismaster;
	}

	public void setIsmaster(int ismaster) {
		this.ismaster = ismaster;
	}

}
