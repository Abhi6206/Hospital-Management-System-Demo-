package com.java.hib;

import java.io.Serializable;
import java.util.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 


public class Patient_enrollment implements Serializable {
	
  
	private String uHID;
	private String firstName;
	private String lastName;
    private Date dob;
    private String gender;
    private String userName;
    private String phone_no;
    private String email;
    private String status;
    private String cause;
    private String address;
    private String medHistory;
	public Patient_enrollment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Patient_enrollment(String uHID, String firstName, String lastName, Date dob, String gender, String userName,
			String phone_no, String email, String status, String cause, String address, String medHistory) {
		super();
		this.uHID = uHID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.userName = userName;
		this.phone_no = phone_no;
		this.email = email;
		this.status = status;
		this.cause = cause;
		this.address = address;
		this.medHistory = medHistory;
	}
	public String getuHID() {
		return uHID;
	}
	public void setuHID(String uHID) {
		this.uHID = uHID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMedHistory() {
		return medHistory;
	}
	public void setMedHistory(String medHistory) {
		this.medHistory = medHistory;
	}

}