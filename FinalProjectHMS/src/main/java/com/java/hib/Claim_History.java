package com.java.hib;

import java.util.Date;

public class Claim_History {

	private int claimHistoryId;
	private String claim_Id;
	private String providerId;
	private int subscription_Id;
	private String prescription_Id;
	private int visit_Id;
	private String uhId;
	private Date ClaimAppliedOn;
	private Date fromDate;
	private Date toDate;
	private double reimbursementAmount;
	private double patientResponsibility;
	private Status status;
	private String comments;
	public Claim_History() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Claim_History(int claimHistoryId, String claim_Id, String providerId, int subscription_Id,
			String prescription_Id, int visit_Id, String uhId, Date claimAppliedOn, Date fromDate, Date toDate,
			double reimbursementAmount, double patientResponsibility, Status status, String comments) {
		super();
		this.claimHistoryId = claimHistoryId;
		this.claim_Id = claim_Id;
		this.providerId = providerId;
		this.subscription_Id = subscription_Id;
		this.prescription_Id = prescription_Id;
		this.visit_Id = visit_Id;
		this.uhId = uhId;
		ClaimAppliedOn = claimAppliedOn;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.reimbursementAmount = reimbursementAmount;
		this.patientResponsibility = patientResponsibility;
		this.status = status;
		this.comments = comments;
	}
	public int getClaimHistoryId() {
		return claimHistoryId;
	}
	public void setClaimHistoryId(int claimHistoryId) {
		this.claimHistoryId = claimHistoryId;
	}
	public String getClaim_Id() {
		return claim_Id;
	}
	public void setClaim_Id(String claim_Id) {
		this.claim_Id = claim_Id;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public int getSubscription_Id() {
		return subscription_Id;
	}
	public void setSubscription_Id(int subscription_Id) {
		this.subscription_Id = subscription_Id;
	}
	public String getPrescription_Id() {
		return prescription_Id;
	}
	public void setPrescription_Id(String prescription_Id) {
		this.prescription_Id = prescription_Id;
	}
	public int getVisit_Id() {
		return visit_Id;
	}
	public void setVisit_Id(int visit_Id) {
		this.visit_Id = visit_Id;
	}
	public String getUhId() {
		return uhId;
	}
	public void setUhId(String uhId) {
		this.uhId = uhId;
	}
	public Date getClaimAppliedOn() {
		return ClaimAppliedOn;
	}
	public void setClaimAppliedOn(Date claimAppliedOn) {
		ClaimAppliedOn = claimAppliedOn;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public double getReimbursementAmount() {
		return reimbursementAmount;
	}
	public void setReimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}
	public double getPatientResponsibility() {
		return patientResponsibility;
	}
	public void setPatientResponsibility(double patientResponsibility) {
		this.patientResponsibility = patientResponsibility;
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
	
	
}