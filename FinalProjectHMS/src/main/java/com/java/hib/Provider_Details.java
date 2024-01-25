package com.java.hib;

import java.io.Serializable;
import java.util.Date;

public class Provider_Details implements Serializable {
	
	private String providerid;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String email;
	private String licenseNumber;
	private String qualification;
	private String username;
	private String phoneno;
	private String gender;
	private String speciality;
	private String status;
	private String address;
	private String isActive;
	public Provider_Details() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Provider_Details(String providerid, String firstName, String lastName, Date dateOfBirth, String email,
			String licenseNumber, String qualification, String username, String phoneno, String gender,
			String speciality, String status, String address, String isActive) {
		super();
		this.providerid = providerid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.licenseNumber = licenseNumber;
		this.qualification = qualification;
		this.username = username;
		this.phoneno = phoneno;
		this.gender = gender;
		this.speciality = speciality;
		this.status = status;
		this.address = address;
		this.isActive = isActive;
	}
	public String getProviderid() {
		return providerid;
	}
	public void setProviderid(String providerid) {
		this.providerid = providerid;
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
}
