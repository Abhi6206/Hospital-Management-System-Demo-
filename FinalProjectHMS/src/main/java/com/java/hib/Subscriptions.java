package com.java.hib;

import java.util.Date;

public class Subscriptions {

	private int subscription_Id;
	private int plan_id;
	private String insurance_id;
	private double premium_Amount;
	private double coverage_Amount;
	private String paymode;
	private String email;
	private Date registrationDate;
	private double initial_amount;
	private String status;
	private String uhid;
	public int getSubscription_Id() {
		return subscription_Id;
	}
	public void setSubscription_Id(int subscription_Id) {
		this.subscription_Id = subscription_Id;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public String getInsurance_id() {
		return insurance_id;
	}
	public void setInsurance_id(String insurance_id) {
		this.insurance_id = insurance_id;
	}
	public double getPremium_Amount() {
		return premium_Amount;
	}
	public void setPremium_Amount(double premium_Amount) {
		this.premium_Amount = premium_Amount;
	}
	public double getCoverage_Amount() {
		return coverage_Amount;
	}
	public void setCoverage_Amount(double coverage_Amount) {
		this.coverage_Amount = coverage_Amount;
	}
	public String getPaymode() {
		return paymode;
	}
	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public double getInitial_amount() {
		return initial_amount;
	}
	public void setInitial_amount(double initial_amount) {
		this.initial_amount = initial_amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUhid() {
		return uhid;
	}
	public void setUhid(String uhid) {
		this.uhid = uhid;
	}
	
	
		
}
