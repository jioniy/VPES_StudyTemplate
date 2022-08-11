package com.suresoft.study.template.jdbc.dto;

public class MemberRequestDTO {
	
	private String name;
	private String userId;
	private String pw;
	private String department;//부서
	private String rank;//직급
	private String position;//직책
	
	public void setuserId(String userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPw(String pw) {
		this.pw = pw;
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

	
	public String getuserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getPw() {
		return pw;
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
}
