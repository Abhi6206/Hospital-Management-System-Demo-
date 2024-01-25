package com.java.hib;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class InsuranceController {
		 
	private insurance_Claim insurance;
    private InsuranceImpl daoImpl=new InsuranceImpl();
    SessionFactory sf;
	Session session;
    
    
	public insurance_Claim getInsurance() {
		return insurance;
	}
	public void setInsurance(insurance_Claim insurance) {
		this.insurance = insurance;
	}
	public InsuranceImpl getDaoImpl() {
		return daoImpl;
	}
	public void setDaoImpl(InsuranceImpl daoImpl) {
		this.daoImpl = daoImpl;
	}
	
	
	
	private boolean checkProviderExistsInDatabase(String providerid) {

		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();

		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(insurance_Claim.class);
			criteria.add(Restrictions.eq("providerid", providerid));
			List<insurance_Claim> resultList = criteria.list();

			return !resultList.isEmpty();
		} finally {
			session.close();
		}
	}

	private boolean checkUHIDExistsInDatabase(String uHID) {

		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();

		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(insurance_Claim.class);
			criteria.add(Restrictions.eq("uHID", uHID));
			List<insurance_Claim> resultList = criteria.list();

			return !resultList.isEmpty();
		} finally {
			session.close();
		}
	}

	private boolean checkDiseaseExistsInDatabase(String disease) {

		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();

		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(insurance_Claim.class);
			criteria.add(Restrictions.eq("disease", disease));
			List<insurance_Claim> resultList = criteria.list();

			return !resultList.isEmpty();
		} finally {
			session.close();
		}
	}

	private boolean checkClaimExistsInDatabase(String claim_Id) {

		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
  
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(insurance_Claim.class);
			criteria.add(Restrictions.eq("claim_Id", claim_Id));
			List<insurance_Claim> resultList = criteria.list();

			return !resultList.isEmpty();
		} finally {
			session.close();
		}
	}
	public Criteria controllersearchClaimNew(Date startdate, Date enddate, String providerid, String claim_Id, String uHID,
			String disease, String matchingType) {
	
			sf = SessionHelper.getConnection();
			Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			session = sf.openSession();
			Criteria criteria = session.createCriteria(insurance_Claim.class);
			if (providerid == null || providerid.trim().isEmpty()) {
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "providerid is required.", null);
		        FacesContext.getCurrentInstance().addMessage("searchForm:providerid", message);
//		        return null;
		    }
		    if (claim_Id == null || claim_Id.trim().isEmpty()) {
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "claim_Id is required.", null);
		        FacesContext.getCurrentInstance().addMessage("searchForm:claim_Id", message);
//		        return null;
		    } 
		    if (uHID == null || uHID.trim().isEmpty()) {
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "uHID is required.", null);
		        FacesContext.getCurrentInstance().addMessage("searchForm:uHID", message);
//		        return null;
		    }
		    if (disease == null || disease.trim().isEmpty()) {
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "disease is required.", null);
		        FacesContext.getCurrentInstance().addMessage("searchForm:disease", message);
//		        return null;
		    } 
		    if (startdate == null && enddate == null ) {
		        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Date is required.", null);
		        FacesContext.getCurrentInstance().addMessage("searchForm:enddate", message);
//		        return null;
		    }
			 
		
		    if (matchingType.equals("startsWith")) {

				if (providerid != null && !providerid.isEmpty()) {
					 if (providerid.length() < 2) {
					        FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					                "Minimum 2 characters are required for Provider ID.", null);
					        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
					        return null;
					    }	
					if (!providerid.matches("^P[0-9]+$")) {
					
						System.out.println("Hitting uhid");
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Providerid should start with  P followed by digits", null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						return null;
					}
					daoImpl.allsearchClaimNew(startdate, enddate, providerid, claim_Id, uHID, disease, matchingType);
			
				}
					 List<insurance_Claim> claimList4 = criteria.list();
					 //
					   boolean recordsFoundProv = !claimList4.isEmpty();
						boolean startsWithLettersProv = false;
						for (insurance_Claim claim : claimList4) {
						    if (claim.getProviderid().toLowerCase().startsWith(providerid.toLowerCase())) {
						    	startsWithLettersProv = true;
						        break;
						    }
						}
					    if (!recordsFoundProv || !startsWithLettersProv) {
					        // Error message for UHID
					    	FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					                "Matching records not found for the specified providerid.", null);
					        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
					        return null;
					    }

				
				if (claim_Id != null && !claim_Id.isEmpty()) {
					if (claim_Id.length() < 2) {
				        FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				                "Minimum 2 characters are required for Claim ID.", null);
				        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
				        return null;
				    }	
					if (!claim_Id.matches("^C[0-9]+$")) {
				
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Claim ID should starts with C and followed by numeric",
								null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						return null; // Returning null to stay on the same page
					}
					daoImpl.allsearchClaimNew(startdate, enddate, providerid, claim_Id, uHID, disease, matchingType);

					
					
				}
					 List<insurance_Claim> claimList3 = criteria.list();
					 //
					   boolean recordsFoundclaim = !claimList3.isEmpty();
						boolean startsWithLettersclaim = false;
						for (insurance_Claim claim : claimList3) {
						    if (claim.getClaim_Id().toLowerCase().startsWith(claim_Id.toLowerCase())) {
						    	startsWithLettersclaim = true;
						        break;
						    }
						}
					    if (!recordsFoundclaim || !startsWithLettersclaim) {
					        // Error message for UHID
					      	FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					                "Matching records not found for the specified claim Id.", null);
					        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
					        return null;
					    }
	 
				if (uHID != null && !uHID.isEmpty()) {
					if (!uHID.matches("^H[0-9]+$")) {
						if (uHID.length() < 2) {
					        FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					                "Minimum 2 characters are required for UHID.", null);
					        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
					        return null;
					    }
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "uHID ID should starts with H and followed by numeric",
								null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						return null; // Returning null to stay on the same page
					}
					daoImpl.allsearchClaimNew(startdate, enddate, providerid, claim_Id, uHID, disease, matchingType);

		
					System.out.println("Generated SQL: " + criteria.toString());
				}
				
					 List<insurance_Claim> claimList2 = criteria.list();
					 //
					   boolean recordsFounduHID = !claimList2.isEmpty();
					   boolean startsWithLettersuHID = false;
						for (insurance_Claim claim : claimList2) {
						    if (claim.getuHID().toLowerCase().startsWith(uHID.toLowerCase())) {
						    	startsWithLettersuHID = true;
						        break;
						    }
						}
					    if (!recordsFounduHID || !startsWithLettersuHID) {
					        // Error message for UHID
					    	FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					                "Matching records not found for the specified UHID.", null);
					        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
					        return null;
					    }
				
				
				    if (disease != null && !disease.isEmpty()) {
				    	if (disease.length() < 2) {
				            System.out.println("Hitting");
				            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				                    "Disease must contain minmun 2 character.", null);
				            FacesContext.getCurrentInstance().addMessage(null, message);
				            return null;
				        }
				    	if (!disease.matches("^[a-zA-Z ]+$")) {
				            System.out.println("Hitting");
				            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				                    "Disease should contain only characters.", null);
				            FacesContext.getCurrentInstance().addMessage(null, message);
				            return null;
				        }
						daoImpl.allsearchClaimNew(startdate, enddate, providerid, claim_Id, uHID, disease, matchingType);



				        List<insurance_Claim> claimList1 = criteria.list();

				        // Check if records are found
				        boolean recordsFound = !claimList1.isEmpty();
				        boolean startsWithLettersDes = false;
						for (insurance_Claim claim : claimList1) {
						    if (claim.getDisease().toLowerCase().startsWith(disease.toLowerCase())) {
						    	startsWithLettersDes = true;
						        break;
						    }
						}
				        if (!recordsFound || !startsWithLettersDes) {
				            FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				                    "Matching records not found for the specified Disease.", null);
				            FacesContext.getCurrentInstance().addMessage(null, errorMessage);
				            return null;
				        }
				    }
			}
			else if (matchingType.equals("contains")) {
				

				if (providerid != null && !providerid.isEmpty()) {
				 if (providerid.length() < 2) {
				        FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				                "Minimum 2 characters are required for Provider ID.", null);
				        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
				        return null;
				    }	
				if (!providerid.matches("^[P0-9]+$")) {
		
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Providerid must have  P followed by digits", null);
					FacesContext.getCurrentInstance().addMessage(null, message);
					return null;
				}
				daoImpl.allsearchClaimNew(startdate, enddate, providerid, claim_Id, uHID, disease, matchingType);


			}
				 List<insurance_Claim> claimList4 = criteria.list();

				    // Check if records are found
				    boolean recordsFound4 = !claimList4.isEmpty();

				    boolean startsWithLetters4 = claimList4.stream()
				            .anyMatch(claim -> claim.getProviderid().toLowerCase().contains(providerid.toLowerCase()));
				    
				    if (!recordsFound4 || !startsWithLetters4) {
				        FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				                "Matching records not found for the specified Provider ID.", null);
				        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
				        return null;
				    }
				    
				    if (claim_Id != null && !claim_Id.isEmpty()) {
				    	if (claim_Id.length() < 2) {
					        FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					                "Minimum 2 characters are required for Claim ID.", null);
					        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
					        return null;
					    }
						if (!claim_Id.matches("^[C0-9]+$")) {

							FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Claim ID should starts with C and followed by numeric",
									null);
							FacesContext.getCurrentInstance().addMessage(null, message);
							return null; // Returning null to stay on the same page
						}	
						daoImpl.allsearchClaimNew(startdate, enddate, providerid, claim_Id, uHID, disease, matchingType);


						System.out.println("Generated SQL: " + criteria.toString());
					}
				    
				    List<insurance_Claim> claimList3 = criteria.list();
					
				    // Check if records are found
				    boolean recordsFound3 = !claimList3.isEmpty();
		 
				    // Check if records start with the specified letters
				    boolean startsWithLetters3 = claimList3.stream()
				            .anyMatch(patient -> patient.getClaim_Id().toLowerCase().contains(claim_Id.toLowerCase()));
		 
				    if (!recordsFound3 || !startsWithLetters3) {
				        FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				                "Matching records not found for the specified Claim ID.", null);
				        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
				        return null;
				    }

				   
			        if (uHID != null && !uHID.isEmpty()) {
			        	if (uHID.length() < 2) {
					        FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					                "Minimum 2 characters are required for UHID.", null);
					        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
					        return null;
					    }
						if (!uHID.matches("^[H0-9]+$")) {
							FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "uHID ID should starts with H and followed by numeric",
									null);
							FacesContext.getCurrentInstance().addMessage(null, message);
							return null; // Returning null to stay on the same page
						}
						daoImpl.allsearchClaimNew(startdate, enddate, providerid, claim_Id, uHID, disease, matchingType);

					
						System.out.println("Generated SQL: " + criteria.toString());
					}
					
		List<insurance_Claim> claimList2 = criteria.list();
					
				    // Check if records are found
				    boolean recordsFound2 = !claimList2.isEmpty();
		 
				    // Check if records start with the specified letters
				    boolean startsWithLetters2 = claimList2.stream()
				            .anyMatch(patient -> patient.getuHID().toLowerCase().contains(uHID.toLowerCase()));
		 
				    if (!recordsFound2 || !startsWithLetters2) {
				        FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				                "Matching records not found for the specified UHID.", null);
				        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
				        return null;
				    }
				    
				if (disease != null && !disease.isEmpty()) {
					if (disease.length() < 2) {
				        FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				                "Minimum 2 characters are required for disease.", null);
				        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
				        return null;
				    }
					if (!disease.matches("^[a-zA-Z ]+$")) {
						System.out.println("Hitting");
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"disease must contain only characters.", null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						return null;
					}
					daoImpl.allsearchClaimNew(startdate, enddate, providerid, claim_Id, uHID, disease, matchingType);


				}
				 List<insurance_Claim> claimList1 = criteria.list();

				    boolean recordsFound1 = !claimList1.isEmpty();

				    boolean startsWithLetters1 = claimList1.stream()
				            .anyMatch(claim -> claim.getDisease().toLowerCase().contains(disease.toLowerCase()));
				    
				    if (!recordsFound1 || !startsWithLetters1) {
				        FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				                "Matching records not found for the specified Disease ID.", null);
				        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
				        return null;
				    }
			} 

			else {
				if (providerid != null && !providerid.isEmpty()) { 
					System.out.println("providerid: " + providerid);
					if (!providerid.matches("^P[0-9]+$")) {
						System.out.println("Hitting uhid");
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Providerid should starts with P and followed by numeric", null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						return null;
					}
					boolean provExists = checkProviderExistsInDatabase(providerid);
					if (!provExists) {
						System.out.println("record not found");
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Record with Provider ID " + providerid + " not found in the database.", null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						return null;
					}
					daoImpl.allsearchClaimNew(startdate, enddate, providerid, claim_Id, uHID, disease, matchingType);

					//sessionMap.put("searchProviderId", providerid);
				}

				if (claim_Id != null && !claim_Id.isEmpty()) {
					if (!claim_Id.matches("^C[0-9]+$")) {
						System.out.println("Hitting claim");
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Claim ID should starts with C and followed by numeric", null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						return null;
					}
					boolean claimExists = checkClaimExistsInDatabase(claim_Id);
					if (!claimExists) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Record with Claim ID " + claim_Id + " not found in the database.", null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						return null;
					}
					daoImpl.allsearchClaimNew(startdate, enddate, providerid, claim_Id, uHID, disease, matchingType);
				}
				if (uHID != null && !uHID.isEmpty()) {
					if (!uHID.matches("^H[0-9]+$")) {
						System.out.println("Hitting");
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"UHID should starts with H and followed by numeric.", null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						return null;
					}

					boolean uHIDExists = checkUHIDExistsInDatabase(uHID);
					if (!uHIDExists) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Record with UHID " + uHID + " not found in the database.", null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						return null;
					}
					daoImpl.allsearchClaimNew(startdate, enddate, providerid, claim_Id, uHID, disease, matchingType);
				}

				if (disease != null && !disease.isEmpty()) {
					if (!disease.matches("^[a-zA-Z ]+$")) {
						System.out.println("Hitting");
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"disease should contain only characters.", null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						return null;
					}
					boolean diseaseExists = checkDiseaseExistsInDatabase(disease);
					if (!diseaseExists) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Record with Disease " + disease + " not found in the database.", null);
						FacesContext.getCurrentInstance().addMessage(null, message);
						return null;
					}
					daoImpl.allsearchClaimNew(startdate, enddate, providerid, claim_Id, uHID, disease, matchingType);
					
				}
				//-------------- Date VAlidation-------------------
				Date currentDate = new Date();
			if (startdate != null && startdate.after(currentDate) && enddate == null) {
				        // handle the case where startdate is a future date
				        System.out.println("Start Date should not be a future date...........");
				        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				                "Start Date should not be a future date", null);
				        FacesContext.getCurrentInstance().addMessage("searchForm:startdate", message);
				        return null;
				    }
			else if (enddate != null && enddate.after(currentDate) && startdate == null) {
			        // handle the case where either startdate or enddate is a future date
			        System.out.println("Date validation hitting...........");
			        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
			                "End Date should not be a future date", null);
			        FacesContext.getCurrentInstance().addMessage("searchForm:enddate", message);
			        return null;
			    }
			else if ((startdate == null || startdate.toString().isEmpty()) && enddate != null) {
				    // handle the case where startdate is empty or null, and enddate is not
				    System.out.println("StartDate hitting...........");
				    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Enter StartDate", null);
				    FacesContext.getCurrentInstance().addMessage("searchForm:startdate", message);
				    return null;
			    }
			else if (startdate != null && (enddate == null || enddate.toString().isEmpty())) {
				    // handle the case where enddate is empty or null, and startdate is not
				    System.out.println("EndDate hitting...........");
				    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Enter EndDate", null);
				    FacesContext.getCurrentInstance().addMessage("searchForm:enddate", message);
				    return null;
				} 
			
			else if (startdate != null && enddate != null) {
					
					    // handle the case where either startdate or enddate is a future date
				    if (startdate.after(currentDate)) {
					    System.out.println("Start Date should not be a future date...........");
					    FacesMessage startDateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					                "Start Date should not be a future date", null);
					    FacesContext.getCurrentInstance().addMessage("searchForm:startdate", startDateMessage);
					    }
					if (enddate.after(currentDate)) {
					    System.out.println("End Date should not be a future date...........");
					    FacesMessage endDateMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					                "End Date should not be a future date", null);
					    FacesContext.getCurrentInstance().addMessage("searchForm:enddate", endDateMessage);
					    }
					 
				    if ((startdate.before(currentDate)) && (enddate.before(currentDate)) && startdate.equals(enddate)) {
				        // handle the case where startdate and enddate are equal
				        System.out.println("Date mismatch hitting...........");
				        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				                "Start Date and End Date should not be equal", null);
				        FacesContext.getCurrentInstance().addMessage(null, message);
				        return null;
				        } 
				    if ((startdate.before(currentDate)) && (enddate.before(currentDate)) && startdate.after(enddate)) {
				        // handle the case where enddate is before startdate
				        System.out.println("Date mismatch hitting...........");
				        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				                "End Date should not be before Start Date", null);
				        FacesContext.getCurrentInstance().addMessage(null, message);
				        return null;
				        }
			        if ((startdate.before(currentDate)) && (enddate.before(currentDate))) {
					    List<insurance_Claim> claimList = criteria.list();
						boolean recordsFound = false;
						for (insurance_Claim claim : claimList) {
						    if (claim.getAdmit_date().after(startdate) && claim.getAdmit_date().before(enddate)) {
						    	recordsFound = true;
						        break;
						    }
						}
					    if (!recordsFound) {
					        // handle the case where no records are found in the specified date range
					    	System.out.println("No records found for the specified date range.");
					        FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					                "No records found for the specified date range.", null);
					        FacesContext.getCurrentInstance().addMessage(null, errorMessage); // Fix here
					    } 
			        }
					daoImpl.allsearchClaimNew(startdate, enddate, providerid, claim_Id, uHID, disease, matchingType);
					
				}
				
			}
		
		return criteria;
	}
	
	
	// SORTING of  ################################################
				static String orderByproviderId = "sort";
				static String orderByclaimId = "sort";
				static String orderBysub = "sort";
				static String orderByadmitDate = "sort";
				static String orderByUHID = "sort";
				static String orderBybill = "sort";
				static String orderBydiscDate = "sort";
				static String orderByMisAmount = "sort";
				static String orderBydisease = "sort";
				static String orderBycomments = "sort";
				
				public String sortbyProviderID() {
					System.out.println("orderByproviderId : " + orderByproviderId);
					if (orderByproviderId.length() == 4) {
						orderByproviderId = "asc";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderByUHID = "sort";
						orderBybill = "sort";
						orderBydiscDate = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
			 
					} else if (orderByproviderId.equals("asc")) {
						orderByproviderId = "desc";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderByUHID = "sort";
						orderBybill = "sort";
						orderBydiscDate = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
					}
					return "FinalSearch.jsp?faces-redirect=true";
				}
			 
				public String sortbyClaimID() {
					System.out.println("orderByclaimId : " + orderByclaimId);
					if (orderByclaimId.length() == 4) {
						orderByclaimId = "asc";
						orderByproviderId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderByUHID = "sort";
						orderBybill = "sort";
						orderBydiscDate = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
					} else if (orderByclaimId.equals("asc")) {
						orderByclaimId = "desc";
						orderByproviderId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderByUHID = "sort";
						orderBybill = "sort";
						orderBydiscDate = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
					}
					return "FinalSearch.jsp?faces-redirect=true";
				}
				
				public String sortbySubscription() {
					System.out.println("orderBysub : " + orderBysub);
					if (orderBysub.length() == 4) {
						orderBysub = "asc";
						orderByclaimId = "sort";
						orderByproviderId = "sort";
						orderByadmitDate = "sort";
						orderByUHID = "sort";
						orderBybill = "sort";
						orderBydiscDate = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
					} else if (orderBysub.equals("asc")) {
						orderBysub = "desc";
						orderByclaimId = "sort";
						orderByproviderId = "sort";
						orderByadmitDate = "sort";
						orderByUHID = "sort";
						orderBybill = "sort";
						orderBydiscDate = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
					}
					return "FinalSearch.jsp?faces-redirect=true";
				}
				
				public String sortbyAdmitDate() {
					System.out.println("orderByadmitDate : " + orderByadmitDate);
					if (orderByadmitDate.length() == 4) {
						orderByadmitDate = "asc";
						orderByproviderId = "sort";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByUHID = "sort";
						orderBybill = "sort";
						orderBydiscDate = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
			 
					} else if (orderByadmitDate.equals("asc")) {
						orderByadmitDate = "desc";
						orderByproviderId = "sort";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByUHID = "sort";
						orderBybill = "sort";
						orderBydiscDate = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
					}
					return "FinalSearch.jsp?faces-redirect=true";
				}
			 
				public String sortbyUHID() {
					System.out.println("orderByUHID : " + orderByUHID);
					if (orderByUHID.length() == 4) {
						orderByUHID = "asc";
						orderByproviderId = "sort";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderBybill = "sort";
						orderBydiscDate = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
					} else if (orderByUHID.equals("asc")) {
						orderByUHID = "desc";
						orderByproviderId = "sort";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderBybill = "sort";
						orderBydiscDate = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
					}
					return "FinalSearch.jsp?faces-redirect=true";
				}
			 
				public String sortbyMisAmount() {
					System.out.println("orderByMisAmount : " + orderByMisAmount);
					if (orderByMisAmount.length() == 4) {
						orderByMisAmount = "asc";
						orderByproviderId = "sort";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderBybill = "sort";
						orderBydiscDate = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
					} else if (orderByMisAmount.equals("asc")) {
						orderByMisAmount = "desc";
						orderByproviderId = "sort";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderBybill = "sort";
						orderBydiscDate = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
					}
					return "FinalSearch.jsp?faces-redirect=true";
				}
				public String sortbyDiscDate() {
					System.out.println("orderBydiscDate : " + orderBydiscDate);
					if (orderBydiscDate.length() == 4) {
						orderBydiscDate = "asc";
						orderByproviderId = "sort";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderBybill = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
					} else if (orderBydiscDate.equals("asc")) {
						orderBydiscDate = "desc";
						orderByproviderId = "sort";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderBybill = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
					}
					return "FinalSearch.jsp?faces-redirect=true";
				}
				public String sortbyComments() {
					System.out.println("orderBycomments : " + orderBycomments);
					if (orderBycomments.length() == 4) {
						orderBycomments = "asc";
						orderByproviderId = "sort";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderBybill = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBydiscDate = "sort";
					} else if (orderBycomments.equals("asc")) {
						orderBycomments = "desc";
						orderByproviderId = "sort";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderBybill = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBydiscDate = "sort";
					}
					return "FinalSearch.jsp?faces-redirect=true";
				}
				public String sortbyDisease() {
					System.out.println("orderBydisease : " + orderBydisease);
					if (orderBydisease.length() == 4) {
						orderBydisease = "asc";
						orderByproviderId = "sort";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderBybill = "sort";
						orderByMisAmount = "sort";
						orderBydiscDate = "sort";
						orderBycomments = "sort";
					} else if (orderBydisease.equals("asc")) {
						orderBydisease = "desc";
						orderByproviderId = "sort";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderBybill = "sort";
						orderByMisAmount = "sort";
						orderBydiscDate = "sort";
						orderBycomments = "sort";
					}
					return "FinalSearch.jsp?faces-redirect=true";
				}
				public String sortbyBill() {
					System.out.println("orderBybill : " + orderBybill);
					if (orderBybill.length() == 4) {
						orderBybill = "asc";
						orderByproviderId = "sort";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderByUHID = "sort";
						orderBydiscDate = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
					} else if (orderBybill.equals("asc")) {
						orderBybill = "desc";
						orderByproviderId = "sort";
						orderByclaimId = "sort";
						orderBysub = "sort";
						orderByadmitDate = "sort";
						orderByUHID = "sort";
						orderBydiscDate = "sort";
						orderByMisAmount = "sort";
						orderBydisease = "sort";
						orderBycomments = "sort";
					}
					return "FinalSearch.jsp?faces-redirect=true";
				}

				public void handlingClaimSort(Criteria criteria) {
						if (orderByproviderId.equals("asc")) {
							System.out.println("Order by ProviderID " + orderByproviderId);
							criteria.addOrder(Order.asc("providerid"));
						} else if (orderByproviderId.equals("desc")) {
							criteria.addOrder(Order.desc("providerid"));
						}
						else if (orderByclaimId.equals("asc")) {
							System.out.println("Order by ClaimID " + orderByclaimId);
							criteria.addOrder(Order.asc("claim_Id"));
						} else if (orderByclaimId.equals("desc")) {
							criteria.addOrder(Order.desc("claim_Id"));
						}
						
						else if (orderBysub.equals("asc")) {
							System.out.println("Order by Subscription " + orderBysub);
							criteria.addOrder(Order.asc("subscription_Id"));
						} else if (orderBysub.equals("desc")) {
							criteria.addOrder(Order.desc("subscription_Id"));
						}
				 
						else if (orderByadmitDate.equals("asc")) {
							System.out.println("Order by Date" + orderByadmitDate);
							criteria.addOrder(Order.asc("admit_date"));
						} else if (orderByadmitDate.equals("desc")) {
							criteria.addOrder(Order.desc("admit_date"));
						}
				 
						else if (orderByUHID.equals("asc")) {
							System.out.println("Order by UHID" + orderByUHID);
							criteria.addOrder(Order.asc("uHID"));
						} else if (orderByUHID.equals("desc")) {
							criteria.addOrder(Order.desc("uHID"));
						}
						else if (orderBydiscDate.equals("asc")) {
							System.out.println("Order by UHID" + orderBydiscDate);
							criteria.addOrder(Order.asc("disc_date"));
						} else if (orderBydiscDate.equals("desc")) {
							criteria.addOrder(Order.desc("disc_date"));
						}
						else if (orderByMisAmount.equals("asc")) {
							System.out.println("Order by UHID" + orderByMisAmount);
							criteria.addOrder(Order.asc("mis_Amount"));
						} else if (orderByMisAmount.equals("desc")) {
							criteria.addOrder(Order.desc("mis_Amount"));
						}
						else if (orderBydisease.equals("asc")) {
							System.out.println("Order by UHID" + orderBydisease);
							criteria.addOrder(Order.asc("disease"));
						} else if (orderBydisease.equals("desc")) {
							criteria.addOrder(Order.desc("disease"));
						}
						else if (orderBycomments.equals("asc")) {
							System.out.println("Order by UHID" + orderBycomments);
							criteria.addOrder(Order.asc("comments"));
						} else if (orderBycomments.equals("desc")) {
							criteria.addOrder(Order.desc("comments"));
						}
						else if (orderBybill.equals("asc")) {
							System.out.println("Order by bill_Amount" + orderBybill);
							criteria.addOrder(Order.asc("bill_Amount"));
						} else if (orderBybill.equals("desc")) {
							criteria.addOrder(Order.desc("bill_Amount"));
						} else {
							System.out.println("done");
						}
					}
				 
}
