package com.java.hib;

public class Insurance_Plans {
	private int plan_Id;
	private String insurance_Id;
	private double premium_Amount;
	private double coverage_Amount;
	private String cover_Disease;
	public Insurance_Plans() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Insurance_Plans(int plan_Id, String insurance_Id, double premium_Amount, double coverage_Amount,
			String cover_Disease) {
		super();
		this.plan_Id = plan_Id;
		this.insurance_Id = insurance_Id;
		this.premium_Amount = premium_Amount;
		this.coverage_Amount = coverage_Amount;
		this.cover_Disease = cover_Disease;
	}
	public int getPlan_Id() {
		return plan_Id;
	}
	public void setPlan_Id(int plan_Id) {
		this.plan_Id = plan_Id;
	}
	public String getInsurance_Id() {
		return insurance_Id;
	}
	public void setInsurance_Id(String insurance_Id) {
		this.insurance_Id = insurance_Id;
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
	public String getCover_Disease() {
		return cover_Disease;
	}
	public void setCover_Disease(String cover_Disease) {
		this.cover_Disease = cover_Disease;
	}
	
}
