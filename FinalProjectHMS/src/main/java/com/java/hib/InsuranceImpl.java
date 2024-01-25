package com.java.hib;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import java.text.ParseException;

public class InsuranceImpl {
	SessionFactory sf;
	Session session;


	public List<insurance_Claim> showClaimDetails() {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Criteria criteria = session.createCriteria(insurance_Claim.class);
		List<insurance_Claim> claimList = criteria.list();
		return claimList;
	}

	public Criteria searchInsuranceByProviderid(String providerid) {
		sf = SessionHelper.getConnection();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("searchProviderId", providerid);
		session = sf.openSession();
		Criteria criteria = session.createCriteria(insurance_Claim.class);
		criteria.add(Restrictions.ilike("providerid", "%" + providerid + "%"));
		return criteria;
	}

	public Criteria searchInsuranceByclaim(String claim_Id) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("searchclaimId", claim_Id);
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Criteria criteria = session.createCriteria(insurance_Claim.class);
		criteria.add(Restrictions.ilike("claim_Id", "%" + claim_Id + "%"));
		return criteria;
	}

	public Criteria searchInsuranceByuhid(String uHID) {
		sf = SessionHelper.getConnection();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("searchuhId", uHID);
		session = sf.openSession();
		Criteria criteria = session.createCriteria(insurance_Claim.class);
		criteria.add(Restrictions.ilike("uHID", "%" + uHID + "%"));
		return criteria;
	}

	public Criteria searchInsuranceByDisease(String disease) {

		sf = SessionHelper.getConnection();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("searchdisease", disease);
		session = sf.openSession();
		Criteria criteria = session.createCriteria(insurance_Claim.class);
		criteria.add(Restrictions.ilike("disease", "%" + disease + "%"));
		return criteria;
	}

	public Criteria searchInsuranceByDisease1(String disease) {

		sf = SessionHelper.getConnection();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("searchdisease1", disease);
		session = sf.openSession();
		Criteria criteria = session.createCriteria(insurance_Claim.class);
		criteria.add(Restrictions.ilike("disease", disease + "%"));
		return criteria;
	}
	
	public Criteria searchInsuranceByDisease2(String disease) {

		sf = SessionHelper.getConnection();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("searchdisease2", disease);
		session = sf.openSession();
		Criteria criteria = session.createCriteria(insurance_Claim.class);
		criteria.add(Restrictions.eq("disease", disease));
		return criteria;
	}


	public Criteria getSalesByDateRange(Date startdate, Date enddate) {
		System.out.println("METHOD HIT");
		sf = SessionHelper.getConnection();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("startdate", startdate);
		sessionMap.put("enddate", enddate);
		session = sf.openSession();
		Criteria criteria = session.createCriteria(insurance_Claim.class);
		criteria.add(Restrictions.ge("admit_date", startdate));
		criteria.add(Restrictions.le("admit_date", enddate));  
		return criteria;
	}
	// ---------------Paging---------------

	public List<insurance_Claim> getListOfInsuranceClaim(int firstRow, int rowCount) {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		System.out.println("Method hitting.....");

		if (sessionMap.get("searchclaimId") != null) {
			String claim_Id = (String) sessionMap.get("searchclaimId");
			SessionFactory sf = SessionHelper.getConnection();
			Session session = sf.openSession();
			List<insurance_Claim> cdList = null;

			session.beginTransaction();
			Criteria criteria = searchInsuranceByclaim(claim_Id);
			InsuranceController jsf= new InsuranceController();
			jsf.handlingClaimSort(criteria);
			criteria.setFirstResult(firstRow);
			criteria.setMaxResults(rowCount);

			List<insurance_Claim> claimList = criteria.list();

			return claimList;
		}
		if (sessionMap.get("searchProviderId") != null) {
			String providerId = (String) sessionMap.get("searchProviderId");
			SessionFactory sf = SessionHelper.getConnection();
			Session session = sf.openSession();
			List<insurance_Claim> cdList = null;

			session.beginTransaction();
			Criteria criteria = searchInsuranceByProviderid(providerId);
			InsuranceController jsf= new InsuranceController();
			jsf.handlingClaimSort(criteria);
			criteria.setFirstResult(firstRow);
			criteria.setMaxResults(rowCount);

			List<insurance_Claim> claimList = criteria.list();

			return claimList;
		}

		if (sessionMap.get("searchuhId") != null) {
			String uhId = (String) sessionMap.get("searchuhId");
			SessionFactory sf = SessionHelper.getConnection();
			Session session = sf.openSession();
			List<insurance_Claim> cdList = null;

			session.beginTransaction();
			Criteria criteria = searchInsuranceByuhid(uhId);
			InsuranceController jsf= new InsuranceController();
			jsf.handlingClaimSort(criteria);
			criteria.setFirstResult(firstRow);
			criteria.setMaxResults(rowCount);

			List<insurance_Claim> claimList = criteria.list();

			return claimList;
		}
//-------------- For contain search---------------
		if (sessionMap.get("searchdisease") != null) {
			String disease = (String) sessionMap.get("searchdisease");
			SessionFactory sf = SessionHelper.getConnection();
			Session session = sf.openSession();
			List<insurance_Claim> cdList = null;

			session.beginTransaction();
			Criteria criteria = searchInsuranceByDisease(disease);
			InsuranceController jsf= new InsuranceController();
			jsf.handlingClaimSort(criteria);
			criteria.setFirstResult(firstRow);
			criteria.setMaxResults(rowCount);

			List<insurance_Claim> claimList = criteria.list();

			return claimList;
		}
		// ----------------startWith search--------------
		if (sessionMap.get("searchdisease1") != null) {
			String disease = (String) sessionMap.get("searchdisease1");
			SessionFactory sf = SessionHelper.getConnection();
			Session session = sf.openSession();
			List<insurance_Claim> cdList = null;

			session.beginTransaction();
			Criteria criteria = searchInsuranceByDisease1(disease);
			InsuranceController jsf= new InsuranceController();
			jsf.handlingClaimSort(criteria);
			criteria.setFirstResult(firstRow);
			criteria.setMaxResults(rowCount);

			List<insurance_Claim> claimList = criteria.list();

			return claimList;
		}
		//----------------Normal Search-----------
		if (sessionMap.get("searchdisease2") != null) {
			String disease = (String) sessionMap.get("searchdisease2");
			SessionFactory sf = SessionHelper.getConnection();
			Session session = sf.openSession();
			List<insurance_Claim> cdList = null;

			session.beginTransaction();
			Criteria criteria = searchInsuranceByDisease2(disease);
			InsuranceController jsf= new InsuranceController();
			jsf.handlingClaimSort(criteria);
			criteria.setFirstResult(firstRow);
			criteria.setMaxResults(rowCount);

			List<insurance_Claim> claimList = criteria.list();

			return claimList;
		}
		
		if (sessionMap.get("startdate") != null && sessionMap.get("enddate") != null) {
			Date startdate = (Date) sessionMap.get("startdate");
			Date enddate = (Date) sessionMap.get("enddate");
			SessionFactory sf = SessionHelper.getConnection();
			Session session = sf.openSession();
			List<insurance_Claim> cdList = null;

			session.beginTransaction();
			Criteria criteria = getSalesByDateRange(startdate, enddate);
			InsuranceController jsf= new InsuranceController();
			jsf.handlingClaimSort(criteria);
			criteria.setFirstResult(firstRow);
			criteria.setMaxResults(rowCount);

			List<insurance_Claim> claimList = criteria.list();

			return claimList;
		}
		return null;
	}


	public int countRows() throws ParseException {     // It is typically thrown when there is an error during the parsing
		                                              //(conversion from a string representation) of a date or number.
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();

		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(insurance_Claim.class);

			if (sessionMap.get("searchclaimId") != null) {
				String claim_Id = (String) sessionMap.get("searchclaimId");
				criteria.add(Restrictions.ilike("claim_Id", "%" + claim_Id + "%"));
			} else if (sessionMap.get("searchProviderId") != null) {
				String providerId = (String) sessionMap.get("searchProviderId");
				criteria.add(Restrictions.ilike("providerid", "%" + providerId + "%"));
			}else if (sessionMap.get("searchuhId") != null) {
				String uhId = (String) sessionMap.get("searchuhId");
				criteria.add(Restrictions.ilike("uHID", "%"+ uhId+"%" ));
			} else if (sessionMap.get("searchdisease") != null) {
				String disease = (String) sessionMap.get("searchdisease");
				criteria.add(Restrictions.ilike("disease", "%" + disease + "%"));
			} else if (sessionMap.get("searchdisease1") != null) {
				String disease = (String) sessionMap.get("searchdisease1");
				criteria.add(Restrictions.ilike("disease", disease + "%"));
			} else if (sessionMap.get("searchdisease2") != null) {
				String disease = (String) sessionMap.get("searchdisease2");
				criteria.add(Restrictions.eq("disease", disease));
			}else if (sessionMap.get("startdate") != null && sessionMap.get("enddate") != null) {
				Date startdate = (Date) sessionMap.get("startdate");
				Date enddate = (Date) sessionMap.get("enddate");
				criteria.add(Restrictions.ge("admit_date", startdate));
				criteria.add(Restrictions.le("admit_date", enddate));
			}

			List<insurance_Claim> filteredList = criteria.list();
			return filteredList.size();
		} catch (HibernateException e) {    // it generally interaction between your application 
			                                //and the Hibernate framework, especially with database operations
			e.printStackTrace();
			System.out.println("Hibernate Exception error Occure....");
		}catch (IllegalStateException e) {  //that a method has been invoked at an illegal or inappropriate 
			                                // time or in an inappropriate manner
			e.printStackTrace();
			System.out.println("IllegalState Exception error Occure....");
		}catch (NumberFormatException e) {   //is an exception that is thrown when a method is attempting to convert
			                                 // a string to one of the numeric types 
			e.printStackTrace();
			System.out.println("NumberFormat Exception error Occure....");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("General Exception error Occure....");
		}
		finally {
			session.close();
		}
		return 0;
	}


	public void clear() throws IOException {
		// the clear method is intended to log out the user by invalidating the session, 
		//       clearing specific session attributes, and redirecting to the same page.
		System.out.println("Clear");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		//FacesContext.getCurrentInstance().= various contextual information about the request, response, 
		//                                and other parameters in a JavaServer Faces (JSF) application.
		//getExternalContext().= provides access to the servlet environment.
		//invalidateSession();= Invalidates the current session OR discards the session-related data.
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ejb Impl", null);
		//getSessionMap().put("hibImpl", null);: This line sets the attribute named "hibImpl" in the 
		//                    session map to null. It's clearing or removing a specific attribute from the session.
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		// ec=  Stores the external context in a local variable ec
		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
		// getting the request from HttpServletRequest,
		//Redirects the user to the same URL, effectively refreshing the current page
	}

	// ---------------------- search UHID details-------------------------------
	public String SearchUHID(String uHID) {
		System.out.println("method is hitting in search");
		System.out.println(uHID);
		Session session = SessionHelper.getConnection().openSession();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Criteria criteria = session.createCriteria(Patient_enrollment.class);
		criteria.add(Restrictions.eq("uHID", uHID));
		Patient_enrollment patient = (Patient_enrollment) criteria.uniqueResult();
		sessionMap.put("patient", patient);
		return "UHIDDetails.jsp?faces-redirect=true";

	}

	// --------------------------search Provider details---------------------------
	public String SearchProvider(String providerid) {
		System.out.println("method is hitting in search");
		System.out.println(providerid);
		Session session = SessionHelper.getConnection().openSession();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Criteria criteria = session.createCriteria(Provider_Details.class);
		criteria.add(Restrictions.eq("providerid", providerid));
		Provider_Details provider = (Provider_Details) criteria.uniqueResult();
		sessionMap.put("provider", provider);
		return "ProviderDetails.jsp?faces-redirect=true";
	}

	// ******************************************************************************************
	// --------------------------- Final Search----------------------------


	public Criteria allsearchClaimNew(Date startdate, Date enddate, String providerid, String claim_Id, String uHID,
	String disease, String matchingType) {
		sf = SessionHelper.getConnection();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		session = sf.openSession();
		Criteria criteria = session.createCriteria(insurance_Claim.class);

		
		 if (matchingType.equals("startsWith")) {
			 
				if (providerid != null && !providerid.isEmpty()) {
					sessionMap.put("searchProviderId", providerid);
				}
				if (claim_Id != null && !claim_Id.isEmpty()) {
					sessionMap.put("searchclaimId", claim_Id);
				}
				if (uHID != null && !uHID.isEmpty()) {
					sessionMap.put("searchuhId", uHID);
				}
				  if (disease != null && !disease.isEmpty()) {
					  sessionMap.put("searchdisease1", disease);
				}
		}
		 else if (matchingType.equals("contains")) {
			 
			 if (providerid != null && !providerid.isEmpty()) {
				 sessionMap.put("searchProviderId", providerid);
				}
				if (claim_Id != null && !claim_Id.isEmpty()) {
					sessionMap.put("searchclaimId", claim_Id);
				}
				if (uHID != null && !uHID.isEmpty()) {
					sessionMap.put("searchuhId", uHID);
				}
				  if (disease != null && !disease.isEmpty()) {
					  sessionMap.put("searchdisease", disease);
				}
		 }
		 else {
				if (providerid != null && !providerid.isEmpty()) { 
					sessionMap.put("searchProviderId", providerid);
				}
				if (claim_Id != null && !claim_Id.isEmpty()) {
					sessionMap.put("searchclaimId", claim_Id);
				}
				if (uHID != null && !uHID.isEmpty()) {
					sessionMap.put("searchuhId", uHID);
				}
				  if (disease != null && !disease.isEmpty()) {
					  sessionMap.put("searchdisease2", disease);
				}
				  if (startdate != null && enddate != null) {
					  sessionMap.put("startdate", startdate);
				        sessionMap.put("enddate", enddate);
				  }
		 }
		return criteria;
	}

}
