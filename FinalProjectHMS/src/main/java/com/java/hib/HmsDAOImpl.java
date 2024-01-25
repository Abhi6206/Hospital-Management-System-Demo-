package com.java.hib;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import javax.servlet.http.HttpServletRequest;

public class HmsDAOImpl {

	SessionFactory sf;
	Session session;

//	
//	public List<insurance_Claim> showInsuranceClaim() {
//		sf = SessionHelper.getConnection();
//		session = sf.openSession();
//		Criteria criteria = session.createCriteria(insurance_Claim.class);
//		List<insurance_Claim> req = criteria.list();
//		return req;
//	}
//
//	
//	public List<Insurance_Claim> getListOfInsClaim(int firstRow, int rowCount) {
//		Session session = SessionHelper.getConnection().openSession();
//
//		session.beginTransaction();
//
//		return session.createCriteria(Insurance_Claim.class).setFirstResult(firstRow).setMaxResults(rowCount).list();
//	}
//
//	public int countRows() {
//		SessionFactory sf = SessionHelper.getConnection();
//		Session session = sf.openSession();
//		try {
//			session.beginTransaction();
//			Criteria criteria = session.createCriteria(Insurance_Claim.class);
//			if (criteria != null) {
//				return criteria.list().size();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}

	public String getEmail(String uhid) {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Criteria cr = session.createCriteria(Patient_enrollment.class);
		cr.add(Restrictions.eq("uHID", uhid));
		Patient_enrollment pt = (Patient_enrollment) cr.uniqueResult();
		String email = pt.getEmail();
		return email;

	}

	public String addClaimHistory(insurance_Claim claim) {
	    sf = SessionHelper.getConnection();
	    session = sf.openSession();

	    Criteria cr = session.createCriteria(Claim_History.class);
	    cr.add(Restrictions.eq("claim_Id", claim.getClaim_Id()));
	    cr.add(Restrictions.eq("status", Status.ACCEPTED));

	    Claim_History acceptedClaim = (Claim_History) cr.uniqueResult();
	    double amount = (claim.getBill_Amount() + claim.getMis_Amount()) * 0.8;
	    double amount2 = (claim.getBill_Amount() + claim.getMis_Amount()) * 0.2;

	    Transaction transaction = session.beginTransaction();

	    Claim_History claimHistory = (acceptedClaim != null) ? acceptedClaim : new Claim_History();

	    // Existing amounts
	    double existingReimbursement = claimHistory.getReimbursementAmount();
	    double existingPatientResponsibility = claimHistory.getPatientResponsibility();

	    // New amounts
	    double newReimbursement = existingReimbursement + amount;
	    double newPatientResponsibility = existingPatientResponsibility + amount2;

	    // Set the updated amounts
	    claimHistory.setClaimAppliedOn(claim.getClaimAppliedOn());
	    claimHistory.setFromDate(claim.getAdmit_date());
	    claimHistory.setToDate(claim.getDisc_date());
	    claimHistory.setPrescription_Id(claim.getPrescription_Id());
	    claimHistory.setVisit_Id(claim.getVisit_ID());
	    claimHistory.setStatus(Status.ACCEPTED);
	    claimHistory.setReimbursementAmount(amount);
	    claimHistory.setPatientResponsibility(amount2);
	    claimHistory.setProviderId(claim.getProviderid());
	    claimHistory.setClaim_Id(claim.getClaim_Id());
	    claimHistory.setSubscription_Id(claim.getSubscription_Id());
	    claimHistory.setUhId(claim.getuHID());
	    claimHistory.setComments(claim.getComments());

	    if (acceptedClaim != null) {
	        // Only update amounts if session.update is executing
	        claimHistory.setReimbursementAmount(newReimbursement);
	        claimHistory.setPatientResponsibility(newPatientResponsibility);
	        session.update(claimHistory);
	    } else {
	        session.save(claimHistory);
	    }

	    transaction.commit();

	    String emailId = getEmail(claim.getuHID());
	    String body = "Claim Successful" + claim.getComments();
	    MailSend.mailOtp(emailId, "Claim Request Accepted", body);

	    session.close(); // Close the session when done
	    try {
	        Thread.sleep(3000);
	    } catch (InterruptedException e) {
	        // Handle InterruptedException if necessary
	    }
	    return "SearchClaimHistoryNew.jsp?faces-redirect=true";
	}


	public String sendRejectMsg(insurance_Claim claim) {
		Claim_History cl1 = new Claim_History();
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Transaction transaction = session.beginTransaction();

		double amount =(claim.getBill_Amount() + claim.getMis_Amount()) * 0.8;
		double amount2 = (claim.getBill_Amount() + claim.getMis_Amount()) * 0.2;

		cl1.setReimbursementAmount(amount);
		cl1.setPatientResponsibility(amount2);
		cl1.setClaimAppliedOn(claim.getClaimAppliedOn());
		cl1.setFromDate(claim.getAdmit_date());
		cl1.setToDate(claim.getDisc_date());
		cl1.setPrescription_Id(claim.getPrescription_Id());
		cl1.setVisit_Id(claim.getVisit_ID());
		cl1.setStatus(Status.REJECTED); // Use the enum directly
		cl1.setProviderId(claim.getProviderid());
		cl1.setClaim_Id(claim.getClaim_Id());
		cl1.setSubscription_Id(claim.getSubscription_Id());
		cl1.setUhId(claim.getuHID());
		cl1.setComments(claim.getComments());

		session.save(cl1);
		transaction.commit();
		String emailId = getEmail(claim.getuHID());
		String body = "Your claim has been rejected because " + claim.getComments();
		MailSend.mailOtp(emailId, "Claim Rejected", body);

		// Introduce a 3-second delay (3000 milliseconds)
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// Handle InterruptedException if necessary
		}

		return "SearchClaimHistoryNew.jsp?faces-redirect=true";
	}

	public String getSubscriptionStatus(int subsId) {
		sf = SessionHelper.getConnection();
		session = sf.openSession();

		Subscriptions subs = (Subscriptions) session.createCriteria(Subscriptions.class)
				.add(Restrictions.eq("subscription_Id", subsId)).uniqueResult();
		return subs.getStatus();
	}

	public String getDisease(int subsId) {
		sf = SessionHelper.getConnection();
		session = sf.openSession();

		Subscriptions subs = (Subscriptions) session.createCriteria(Subscriptions.class)
				.add(Restrictions.eq("subscription_Id", subsId)).uniqueResult();

		int planId = subs.getPlan_id();

		Insurance_Plans insPlan = (Insurance_Plans) session.createCriteria(Insurance_Plans.class)
				.add(Restrictions.eq("plan_Id", planId)).uniqueResult();

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("insPlan", insPlan);

		return insPlan.getCover_Disease();
	}

	public Claim_History getClaimHistoryObject(String claimId) {
	    sf = SessionHelper.getConnection();
	    session = sf.openSession();

	    Criteria criteria = session.createCriteria(Claim_History.class);
	    criteria.add(Restrictions.eq("claim_Id", claimId));
	    criteria.add(Restrictions.eq("status", Status.ACCEPTED));

	    return (Claim_History) criteria.uniqueResult();
	}


	public double getCoverageAmount(int subsId) {
		sf = SessionHelper.getConnection();
		session = sf.openSession();

		Subscriptions subs = (Subscriptions) session.createCriteria(Subscriptions.class)
				.add(Restrictions.eq("subscription_Id", subsId)).uniqueResult();

		return subs.getCoverage_Amount();
	}

	public String providerIdIsActive(String proId) {
		sf = SessionHelper.getConnection();
		session = sf.openSession();

		Provider_Details provider = (Provider_Details) session.createCriteria(Provider_Details.class)
				.add(Restrictions.eq("providerid", proId)).uniqueResult();

		System.out.println(provider.getIsActive());
		return provider.getIsActive();
	}

	public Insurance_Details getInsuranceObject(int subsId) {
		System.out.println("Entered");

		sf = SessionHelper.getConnection();
		session = sf.openSession();

		System.out.println("Second");

		Criteria subscriptionCriteria = session.createCriteria(Subscriptions.class);
		subscriptionCriteria.add(Restrictions.eq("subscription_Id", subsId));
		Subscriptions subs = (Subscriptions) subscriptionCriteria.uniqueResult();

		System.out.println(subsId);

		System.out.println("Subs Coverage amt " + subs.getCoverage_Amount());

		int planId = subs.getPlan_id();
		System.out.println(planId);

		Criteria insurancePlanCriteria = session.createCriteria(Insurance_Plans.class);
		insurancePlanCriteria.add(Restrictions.eq("plan_Id", planId));
		Insurance_Plans insPlan = (Insurance_Plans) insurancePlanCriteria.uniqueResult();

	//	System.out.println("Ins plan" + insPlan.getBenefits());

		String insuranceId = insPlan.getInsurance_Id();

		Criteria insuranceDetailsCriteria = session.createCriteria(Insurance_Details.class);
		insuranceDetailsCriteria.add(Restrictions.eq("insurance_Id", insuranceId));
		Insurance_Details inss = (Insurance_Details) insuranceDetailsCriteria.uniqueResult();

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("inss", inss);

		System.out.println("Ins Details: " + inss.getInsurance_Name());

		return inss;
	}


	public List<Claim_History> showClaimHistory() {
		return SessionHelper.getConnection().openSession().createCriteria(Claim_History.class).list();
	}

	public String searchByClaimId(String claimId) {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Criteria criteria = session.createCriteria(insurance_Claim.class);
		criteria.add(Restrictions.eq("claim_Id", claimId));
		insurance_Claim req = (insurance_Claim) criteria.uniqueResult();
		sessionMap.put("req", req);

		String uhid = req.getuHID();
		Criteria cr = session.createCriteria(Patient_enrollment.class);
		cr.add(Restrictions.eq("uHID", uhid));
		Patient_enrollment patient = (Patient_enrollment) cr.uniqueResult();
		sessionMap.put("patient", patient);

		return "ClaimRequest.jsp?faces-redirect=true";
	}

	public String showDetails(insurance_Claim ins) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("ins", ins);
		return "showAllDetails.jsp?faces-redirect=true";
	}

	// Sonali's code

	public void clear() throws IOException {
		System.out.println("Clear");
		// Invalidate the current session, effectively logging out the user and clearing
		// session attributes
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		// This line removes a specific session attribute named "claimHistoryImpl" from
		// the session map. The value associated with this attribute is set to null,
		// essentially clearing its content.
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("claimHistoryImpl", null);

		// Obtain the ExternalContext from the FacesContext
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

		// Redirect the user to the current request URI (Uniform Resource Identifier)
		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	}

	public String searchClaims(Claim_History claims) throws ParseException {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("claims", claims);

		FacesContext context = FacesContext.getCurrentInstance();
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();

		// A map (sessionMap) is obtained from the current JSF session. It's used to
		// store and retrieve values during the session.

		Criteria criteria = session.createCriteria(Claim_History.class);

		if (claims.getClaim_Id() != null && !claims.getClaim_Id().isEmpty()) {
			System.out.println("claimId: " + claims.getClaim_Id());
			criteria.add(Restrictions.eq("claim_Id", claims.getClaim_Id()));
			// sessionMap.put("claimId",claims.getClaimId() );
			System.out.println("Generated SQL: " + criteria.toString());
		}

		if (claims.getUhId() != null && !claims.getUhId().isEmpty()) {
			System.out.println("uhid: " + claims.getUhId());
			// if (!claims.getUhid().matches("^[a-zA-Z]+[0-9]+$")) {
			// criteria.add(Restrictions.like("uhid", "%" + claims.getUhid() + "%"));
			criteria.add(Restrictions.eq("uhId", claims.getUhId()));
			System.out.println("Generated SQL: " + criteria.toString());
		}

		if (claims.getStatus() != null) {
			criteria.add(Restrictions.eq("status", claims.getStatus()));
		}

		// Date validation
		if (claims.getFromDate() != null && claims.getToDate() == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Enter End Date", null);
			FacesContext.getCurrentInstance().addMessage("form:toDate", message);
		}
		if (claims.getFromDate() == null && claims.getToDate() != null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Enter Begin Date", null);
			FacesContext.getCurrentInstance().addMessage("form:fromDate", message);
		}
		if (claims.getFromDate() != null || claims.getToDate() != null) {
			// sessionMap.put("fromDate",claims.getFromDate());
			// sessionMap.put("toDate",claims.getToDate());
			criteria.add(Restrictions.ge("claimAppliedOn", claims.getFromDate()));
			criteria.add(Restrictions.le("claimAppliedOn", claims.getToDate()));
		}

		List<Claim_History> claimHistoryList = criteria.list();
		// handle emplty results
		if (claimHistoryList.isEmpty()) {
			System.out.println("Claim hsitory list empty " + 0);
			sessionMap.put("notFoundErr", "NO RECORDS FOUND ");
			return null;
		} else {
			System.out.println("Hitting claims list " + 1);
			sessionMap.put("claimHistoryList", claimHistoryList);
			session.close();
		}
		return "";
	}

	public List<Claim_History> getListOfClaimsHistory(int firstRow, int rowCount) {
		FacesContext context = FacesContext.getCurrentInstance();
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();

		// A map (sessionMap) is obtained from the current JSF session. It's used to
		// store and retrieve values during the session.
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

		// Begin a transaction
		session.beginTransaction();

		List<Claim_History> claimHistoryList = (List<Claim_History>) sessionMap.get("claimHistoryList");

		System.out.println("Original List Size: " + (claimHistoryList != null ? claimHistoryList.size() : 0));

		// Check if the original list is not null
		if (claimHistoryList != null) {
			// Create a sublist based on the pagination parameters
			int toIndex = Math.min(firstRow + rowCount, claimHistoryList.size());

			// Log the sublist range for debugging
			System.out.println("Sublist Range: " + firstRow + " to " + toIndex);

			List<Claim_History> paginatedList = claimHistoryList.subList(firstRow, toIndex);

			// Log the size of the paginated list for debugging
			System.out.println("Paginated List Size: " + paginatedList.size());

			// Commit the transaction and close the session
			session.getTransaction().commit();
			session.close();

			// Return the paginated list
			return paginatedList;
		} else {
			// If the original list is null, return an empty list
			session.close();
			return Collections.emptyList();
		}
	}

	public int countRows(Claim_History claims) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Claim_History.class);
			if (criteria != null) {

				if (claims.getClaim_Id() != null && !claims.getClaim_Id().isEmpty()) {
					System.out.println("claimId: " + claims.getClaim_Id());
					criteria.add(Restrictions.eq("claim_Id", claims.getClaim_Id()));
					System.out.println("Generated SQL: " + criteria.toString());
				}

				if (claims.getUhId() != null && !claims.getUhId().isEmpty()) {
					System.out.println("uhid: " + claims.getUhId());
					criteria.add(Restrictions.like("uhid", "%" + claims.getUhId() + "%"));
					System.out.println("Generated SQL: " + criteria.toString());
				}

				if (claims.getStatus() != null) {
					criteria.add(Restrictions.eq("status", claims.getStatus()));

				}

				// Date validation
				if (claims.getFromDate() != null && claims.getToDate() == null) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Enter To Date", null);
					FacesContext.getCurrentInstance().addMessage("form:toDate", message);
				}
				if (claims.getFromDate() == null && claims.getToDate() != null) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Enter From Date",
							null);
					FacesContext.getCurrentInstance().addMessage("form:fromDate", message);
				}
				if (claims.getFromDate() != null || claims.getToDate() != null) {
					// sessionMap.put("fromDate",claims.getFromDate());
					// sessionMap.put("toDate",claims.getToDate());
					criteria.add(Restrictions.ge("claimAppliedOn", claims.getFromDate()));
					criteria.add(Restrictions.le("claimAppliedOn", claims.getToDate()));
				}

				return criteria.list().size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	static String orderByClaimId = "test";
	static String orderByProviderId = "test";
	static String orderBySubscriptionId = "test";
	static String orderByUhid = "test";
	static String orderByFromDate = "test";
	static String orderByToDate = "test";
	static String orderByClaimAmount = "test";
	static String orderByStatus = "test";
	static String orderByReimbursementAmount = "test";
	static String orderByPatientResponsibility = "test";
	static String orderByClaimAppliedOn = "test";
	static String orderByPrescriptionId = "test";
	static String orderByVisitId = "test";

	public String sortByClaim_Id() {

		if (orderByClaimId.length() == 4) {
			orderByClaimId = "asc";

			orderByProviderId = "test";
			orderBySubscriptionId = "test";
			orderByUhid = "test";
			orderByFromDate = "test";
			orderByToDate = "test";
			orderByClaimAmount = "test";
			orderByStatus = "test";
			orderByReimbursementAmount = "test";
			orderByPatientResponsibility = "test";
			orderByClaimAppliedOn = "test";
			orderByPrescriptionId = "test";
			orderByVisitId = "test";

		} else if (orderByClaimId.equals("asc")) {
			orderByClaimId = "desc";

			orderByProviderId = "test";
			orderBySubscriptionId = "test";
			orderByUhid = "test";
			orderByFromDate = "test";
			orderByToDate = "test";
			orderByClaimAmount = "test";
			orderByStatus = "test";
			orderByReimbursementAmount = "test";
			orderByPatientResponsibility = "test";
			orderByClaimAppliedOn = "test";
			orderByPrescriptionId = "test";
			orderByVisitId = "test";

		}
		return "SearchClaimHistoryNew.jsp?faces-redirect=true";
	}

	
	
	
	
}
