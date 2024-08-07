package com.votingsystem.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@NamedQuery(name = "Candidate.findByUsername", query = "SELECT a FROM Candidate a WHERE a.username = :username")
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Name Cannot be Empty")
	@Size(min = 4, max = 15, message = "Name should have 4 to 15 characters")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "only characters allowed")
	private String name;
	private Date DOB;
	private String gender;
	private String party;
	private String position;
	private String expierence;
	private String address;
	private String contact;
	private String username;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getExpierence() {
		return expierence;
	}

	public void setExpierence(String expierence) {
		this.expierence = expierence;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	public Candidate(int id,
			@NotBlank(message = "Name Cannot be Empty") @Size(min = 4, max = 15, message = "Name should have 4 to 15 characters") @Pattern(regexp = "^[a-zA-Z]+$", message = "only characters allowed") String name,
			Date dOB, String gender, String party, String position, String expierence, String address, String contact,
			String username, String password) {
		super();
		this.id = id;
		this.name = name;
		DOB = dOB;
		this.gender = gender;
		this.party = party;
		this.position = position;
		this.expierence = expierence;
		this.address = address;
		this.contact = contact;
		this.username = username;
		this.password = password;
	}

	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", name=" + name + ", DOB=" + DOB + ", gender=" + gender + ", party=" + party
				+ ", position=" + position + ", expierence=" + expierence + ", address=" + address + ", contact="
				+ contact + ", username=" + username + ", password=" + password + "]";
	}

}
