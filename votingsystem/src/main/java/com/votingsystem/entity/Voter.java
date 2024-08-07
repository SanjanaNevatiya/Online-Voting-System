package com.votingsystem.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@NamedQuery(name = "Voter.findByUsername", query = "SELECT a FROM Voter a WHERE a.username = :username")
public class Voter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Name Cannot be blank")
	@Pattern(regexp = "a-zA-Z+$", message = "only characters allowed")
	@Column(name = "voter_name")
	private String VoterName;
	 @Column(name = "dob")
	private Date DOB;
	 @Column(name = "gender")
	private String gender;
	 @Column(name = "username") 
	private String username;
	 @Column(name = "password")
	private String password;
	 @Column(name = "address")
	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVoterName() {
		return VoterName;
	}

	public void setVoterName(String voterName) {
		VoterName = voterName;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Voter(int id,
			@NotBlank(message = "Name Cannot be blank") @Pattern(regexp = "a-zA-Z+$", message = "only characters allowed") String voterName,
			Date dOB, String gender, String username, String password, String address) {
		super();
		this.id = id;
		VoterName = voterName;
		DOB = dOB;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.address = address;
	}

	public Voter() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Voter [id=" + id + ", VoterName=" + VoterName + ", DOB=" + DOB + ", gender=" + gender + ", username="
				+ username + ", password=" + password + ", address=" + address + "]";
	}

	

}
