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
@NamedQuery(name = "Admin.findByUsername", query = "SELECT a FROM Admin a WHERE a.username = :username")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Name Cannot be Empty")
    @Size(min = 4, max = 15, message = "Name should have 4 to 15 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "only characters allowed")
    private String name;
    private String address;
    private Date dob;
    private String gender;
    private String username;
    private String password;

    // Constructors, getters, setters, and toString method
    public Admin() {
        super();
    }

    public Admin(int id,
            @NotBlank(message = "Name Cannot be Empty") @Size(min = 4, max = 15, message = "Name should have 4 to 15 characters") @Pattern(regexp = "^[a-zA-Z]+$", message = "only characters allowed") String name,
            String address, Date dob, String gender, String username, String password) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

    @Override
    public String toString() {
        return "Admin [id=" + id + ", name=" + name + ", address=" + address + ", dob=" + dob + ", gender=" + gender
                + ", username=" + username + ", password=" + password + "]";
    }
}
