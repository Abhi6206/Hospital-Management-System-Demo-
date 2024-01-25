package com.java.hib;

import java.util.Date;

public class Insurance_Details {
	private String insurance_Id;
	private String insurance_Name;
	private String type;
	private Date premiumStart;
	private Date premiumEnd;
	private int minPeriod;
	private int maxPeriod;
	private Date launchDate;
	private String status;
	public String getInsurance_Id() {
		return insurance_Id;
	}
	public void setInsurance_Id(String insurance_Id) {
		this.insurance_Id = insurance_Id;
	}
	public String getInsurance_Name() {
		return insurance_Name;
	}
	public void setInsurance_Name(String insurance_Name) {
		this.insurance_Name = insurance_Name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getPremiumStart() {
		return premiumStart;
	}
	public void setPremiumStart(Date premiumStart) {
		this.premiumStart = premiumStart;
	}
	public Date getPremiumEnd() {
		return premiumEnd;
	}
	public void setPremiumEnd(Date premiumEnd) {
		this.premiumEnd = premiumEnd;
	}
	public int getMinPeriod() {
		return minPeriod;
	}
	public void setMinPeriod(int minPeriod) {
		this.minPeriod = minPeriod;
	}
	public int getMaxPeriod() {
		return maxPeriod;
	}
	public void setMaxPeriod(int maxPeriod) {
		this.maxPeriod = maxPeriod;
	}
	public Date getLaunchDate() {
		return launchDate;
	}
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
