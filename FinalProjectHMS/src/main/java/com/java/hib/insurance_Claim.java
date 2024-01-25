package com.java.hib;

import java.util.Date;

public class insurance_Claim {
 
	private String claim_Id;
	private String prescription_Id;
	private String providerid;
	private int subscription_Id;
	private String uHID;
	private int visit_ID;
	private Date admit_date;
	private Date disc_date;
	private double bill_Amount;
	private double mis_Amount;
	private String disease;
	private Date claimAppliedOn;
	private Status status;
	private String comments;
	public insurance_Claim() {
		super();
		// TODO Auto-generated constructor stub
	}
	public insurance_Claim(String claim_Id, String prescription_Id, String providerid, int subscription_Id, String uHID,
			int visit_ID, Date admit_date, Date disc_date, double bill_Amount, double mis_Amount, String disease,
			Date claimAppliedOn, Status status, String comments) {
		super();
		this.claim_Id = claim_Id;
		this.prescription_Id = prescription_Id;
		this.providerid = providerid;
		this.subscription_Id = subscription_Id;
		this.uHID = uHID;
		this.visit_ID = visit_ID;
		this.admit_date = admit_date;
		this.disc_date = disc_date;
		this.bill_Amount = bill_Amount;
		this.mis_Amount = mis_Amount;
		this.disease = disease;
		this.claimAppliedOn = claimAppliedOn;
		this.status = status;
		this.comments = comments;
	}
	public String getClaim_Id() {
		return claim_Id;
	}
	public void setClaim_Id(String claim_Id) {
		this.claim_Id = claim_Id;
	}
	public String getPrescription_Id() {
		return prescription_Id;
	}
	public void setPrescription_Id(String prescription_Id) {
		this.prescription_Id = prescription_Id;
	}
	public String getProviderid() {
		return providerid;
	}
	public void setProviderid(String providerid) {
		this.providerid = providerid;
	}
	public int getSubscription_Id() {
		return subscription_Id;
	}
	public void setSubscription_Id(int subscription_Id) {
		this.subscription_Id = subscription_Id;
	}
	public String getuHID() {
		return uHID;
	}
	public void setuHID(String uHID) {
		this.uHID = uHID;
	}
	public int getVisit_ID() {
		return visit_ID;
	}
	public void setVisit_ID(int visit_ID) {
		this.visit_ID = visit_ID;
	}
	public Date getAdmit_date() {
		return admit_date;
	}
	public void setAdmit_date(Date admit_date) {
		this.admit_date = admit_date;
	}
	public Date getDisc_date() {
		return disc_date;
	}
	public void setDisc_date(Date disc_date) {
		this.disc_date = disc_date;
	}
	public double getBill_Amount() {
		return bill_Amount;
	}
	public void setBill_Amount(double bill_Amount) {
		this.bill_Amount = bill_Amount;
	}
	public double getMis_Amount() {
		return mis_Amount;
	}
	public void setMis_Amount(double mis_Amount) {
		this.mis_Amount = mis_Amount;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public Date getClaimAppliedOn() {
		return claimAppliedOn;
	}
	public void setClaimAppliedOn(Date claimAppliedOn) {
		this.claimAppliedOn = claimAppliedOn;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	//----------------------------------------------------------
	private Date startdate;
	private Date enddate;

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	
	//----------------------------------------------------
	private String matchingType;

	public String getMatchingType() {
		return matchingType;
	}

	public void setMatchingType(String matchingType) {
		this.matchingType = matchingType;
	}

}

   