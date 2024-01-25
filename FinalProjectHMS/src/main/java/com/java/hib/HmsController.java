package com.java.hib;

import java.util.Date;
import java.util.Map;

import java.text.ParseException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HmsController {

    private SessionFactory sf;
    private Session session;

    private Insurance_Details insDetails;
    private insurance_Claim insClaim;
    private Patient_enrollment patient;
    private Provider_Details provider;
    private Insurance_Plans insPlan;
    private Claim_History claimHistory;
    private HmsDAOImpl dao;
    
    private int claimHistoryId;
	private String prescription_Id;
	private int visit_Id;
	private String claim_Id;
	private String providerId;
	private int subscription_Id;
	private String uhId;
	private Date ClaimAppliedOn;
	private Date fromDate;
	private Date toDate;
	private double reimbursementAmount;
	private double patientResponsibility;
	private Status status;
	private String comments;

	

    public int getClaimHistoryId() {
		return claimHistoryId;
	}

	public void setClaimHistoryId(int claimHistoryId) {
		this.claimHistoryId = claimHistoryId;
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

	public SessionFactory getSf() {
        return sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Insurance_Details getInsDetails() {
        return insDetails;
    }

    public void setInsDetails(Insurance_Details insDetails) {
        this.insDetails = insDetails;
    }

    public insurance_Claim getInsClaim() {
        return insClaim;
    }

    public void setInsClaim(insurance_Claim insClaim) {
        this.insClaim = insClaim;
    }

    public Patient_enrollment getPatient() {
        return patient;
    }

    public void setPatient(Patient_enrollment patient) {
        this.patient = patient;
    }

    public Provider_Details getProvider() {
        return provider;
    }

    public void setProvider(Provider_Details provider) {
        this.provider = provider;
    }

    public Insurance_Plans getInsPlan() {
        return insPlan;
    }

    public void setInsPlan(Insurance_Plans insPlan) {
        this.insPlan = insPlan;
    }

    public Claim_History getClaimHistory() {
        return claimHistory;
    }

    public void setClaimHistory(Claim_History claimHistory) {
        this.claimHistory = claimHistory;
    }

    public HmsDAOImpl getDao() {
        return dao;
    }

    public void setDao(HmsDAOImpl dao) {
        this.dao = dao;
    }

    public HmsController() {
        dao = new HmsDAOImpl();
    }

    public String addClaimHistoryCon() throws NamingException {
        System.out.println("Hacker");
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        insurance_Claim clm = (insurance_Claim) sessionMap.get("req");
        if (addValidation(clm)) {
            return new HmsDAOImpl().addClaimHistory(clm);
        }
        return "";
    }

    public String addClaimHistoryControl() throws NamingException {
        System.out.println("Hacker");
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        insurance_Claim claim = (insurance_Claim) sessionMap.get("ins");
        if (addValidation(claim)) {
            return new HmsDAOImpl().addClaimHistory(claim);
        }
        return "";
    }

    public boolean addValidation(insurance_Claim insClaim) {
        FacesContext context = FacesContext.getCurrentInstance();
        HmsDAOImpl daoNew = new HmsDAOImpl();
        String[] trickyComment = { "Tricky", "Bad Record", "Misleaded" };
        int subsId = insClaim.getSubscription_Id();
        String insClaimId = insClaim.getClaim_Id();
        System.out.println("Subs Id: " + subsId);
        System.out.println(subsId + " " + insClaimId);
        System.out.println(insClaim);
        Insurance_Details insDetails = daoNew.getInsuranceObject(subsId);
        boolean flag = true;
        System.out.println("Insurance Details in Controller " + insDetails.getStatus());
        if (!insClaim.getDisease().equals(daoNew.getDisease(insClaim.getSubscription_Id()))) {
            context.addMessage("form:disease",
                    new FacesMessage("Patient disease is not matching with the Insurance plan covered disease"));
            flag = false;
        }

        if (!daoNew.providerIdIsActive(insClaim.getProviderid()).equals("YES")) {
            context.addMessage("form:providerid", new FacesMessage("This provider Id is not active"));
            flag = false;
        }

        if (!insClaim.getAdmit_date().after(insDetails.getPremiumStart())) {
            context.addMessage("form:admit_date", new FacesMessage("Your premium has not started yet"));
            flag = false;
        }

        if (insClaim.getAdmit_date().after(insDetails.getPremiumEnd())) {
            context.addMessage("form:admit_date", new FacesMessage("Your premium has expired"));
            flag = false;
        }
        
        if(daoNew.getSubscriptionStatus(insClaim.getSubscription_Id()).equals("Inactive")) {
        	context.addMessage("form:subsId", new FacesMessage("Subscription is Inactive"));
            flag = false;
        }

        Claim_History claims = daoNew.getClaimHistoryObject(insClaimId);
        if (claims != null) {
            String hisClaimId = claims.getClaim_Id();
            double claimableAmount = daoNew.getCoverageAmount(subsId) - claims.getReimbursementAmount();
            double bill_mis_Amount = insClaim.getBill_Amount() + insClaim.getMis_Amount();
            double needToPay = bill_mis_Amount - claimableAmount;
            System.out.println("claim" + claims.getComments());
            if (insClaimId.equals(hisClaimId)) {

                if (claims.getComments().equals("Tricky")) {
                    context.addMessage("form:comments", new FacesMessage("Tricky Record Found In Claim History"));
                    flag = false;
                } 

                if (claims.getFromDate().equals(insClaim.getAdmit_date())) {
                    context.addMessage("form:admit_date",
                            new FacesMessage("Amount already claimed for this date (Duplicate Claim)"));
                    flag = false;
                }

                if (claimableAmount < bill_mis_Amount) {
                    context.addMessage("form:bill_Amount",
                            new FacesMessage("You can claim only " + claimableAmount + " amount, rest " + needToPay
                                    + " amount you have to pay including GST"));
                    flag = false;
                }

            }

            if (!insClaim.getAdmit_date().before(insDetails.getPremiumEnd())) {
                context.addMessage("form:admit_date", new FacesMessage("Your premium has expired"));
                flag = false;
            }

        }
        System.out.println("in controller");
        return flag;
    }
    
    //sonali's code
    
    public String searchClaimHistory(HmsController claimHistoryController) throws ParseException {
		Claim_History claimHistoryNew = new Claim_History();
		HmsDAOImpl daoImpl = new HmsDAOImpl();

		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		if (claimHistoryController != null) {
			// validate claimId
			if (claimHistoryController.getClaim_Id() != null && !claimHistoryController.getClaim_Id().isEmpty()) {
				if (!claimHistoryController.getClaim_Id().matches("^[cC]\\d{3,}$")) {
					System.out.println("Hitting claimId");
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Such claim id doesn't exists",
							null);
					FacesContext.getCurrentInstance().addMessage("searchForm:claimId", message);
					return null; // Returning null to stay on the same page
				}
				sessionMap.put("searchclaimId", claimHistoryController.getClaim_Id());
				claimHistoryNew.setClaim_Id(claimHistoryController.getClaim_Id());
			}
			// validate Uhid
			if (claimHistoryController.getUhId() != null && !claimHistoryController.getUhId().isEmpty()) {
				if (!claimHistoryController.getUhId().matches("^IN\\d{4}$")) {
					System.out.println("Hitting uhid");
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "UHID doesn't exists", null);
					FacesContext.getCurrentInstance().addMessage("searchForm:uhid", message);
					return null; // Returning null to stay on the same page
				}
				sessionMap.put("searchuhid", claimHistoryController.getUhId());
				claimHistoryNew.setUhId(claimHistoryController.getUhId());
			}
			// validate status
			if (claimHistoryController.getStatus() != null) {
				System.out.println("Status:"+ claimHistoryController.getStatus());
				sessionMap.put("status", claimHistoryController.getStatus());
				claimHistoryNew.setStatus(claimHistoryController.getStatus());
				System.out.println("Status23:"+ claimHistoryController.getStatus());

			}

			// Validate Date Range
			if (claimHistoryController.getFromDate() != null && claimHistoryController.getToDate() == null) {
				claimHistoryNew.setFromDate(claimHistoryController.getFromDate());
			}

			if (claimHistoryController.getFromDate() == null && claimHistoryController.getToDate() != null) {
				claimHistoryNew.setToDate(claimHistoryController.getToDate());
			}

			if (claimHistoryController.getFromDate() != null && claimHistoryController.getToDate() != null) {
				if (claimHistoryController.getFromDate() != null && claimHistoryController.getToDate() != null
						&& claimHistoryController.getFromDate().after(claimHistoryController.getToDate())) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"End Date should be greater than Begin Date", null);
					FacesContext.getCurrentInstance().addMessage("form:toDate", message);
					return null; // Or return an appropriate outcome based on your requirements
				}
				claimHistoryNew.setFromDate(claimHistoryController.getFromDate());
				claimHistoryNew.setToDate(claimHistoryController.getToDate());
			}
			return daoImpl.searchClaims(claimHistoryNew);
		}
		return "";
	}

}
