package com.java.hib;

import java.util.Map;
import java.util.Random;

import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class AdminLoginImpl implements AdminLoginDAO{
	
	SessionFactory sf;
	Session session;
	
	public static int generateOtp() {
		Random r = new Random(System.currentTimeMillis());
		return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}

	@Override
	public String adminSignIn(AdminLogin admin) {
		String encr = EncryptPassword.getCode(admin.getPassword());
		Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(AdminLogin.class);
		cr.add(Restrictions.eq("username", admin.getUsername()));
		cr.setProjection(Projections.rowCount());
		long userNameCount = (long) cr.uniqueResult();
		if(userNameCount > 0) {
			return "User Already exit";
		}
		
		admin.setPassword(encr.trim());
		session.beginTransaction();
		int otp = generateOtp();
		admin.setOtp(String.valueOf(otp));
		session.save(admin);
		session.getTransaction().commit();
		String subject = "Welcome to My app";
		String body = "Welcome to Mr/Miss  " + admin.getName() + "\r\n" + "Your OTP Generated Successfully..."
				+ "\r\n" + "OTP is " + otp;
		MailSend.mailOtp(admin.getEmail(), "Otp Send Successfully...", body);
		
		return "AdminLogin.jsp?faces-redirect=true";	
	}
	
	@Override
	public String loginDao(AdminLogin adminauth) {
		sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(AdminLogin.class);
		cr.add(Restrictions.eq("username",adminauth.getUsername()));
		cr.add(Restrictions.eq("password",EncryptPassword.getCode(adminauth.getPassword())));
		
		cr.setProjection(Projections.rowCount());
		long count = (long) cr.uniqueResult();
		AdminLogin admin=searchAdminName(adminauth.getUsername());
		if(count == 1) {
			String name=admin.getName();
			Map<String,Object> sessionMap =
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			sessionMap.put("name", name);
			
			return"Menu.jsp?faces-redirect=true";
		}
		
		else {
			Map<String,Object> sessionMap =
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			sessionMap.put("passWordErr3", "Enter a valid UserName and Password.");
//			String fullname=adminauth.getName();
//					sessionMap.put("provFullName", fullname);
//					System.out.println(fullname);
			return "PhrmaLogin.jsp?faces-redirect=true";
		}
	}
	public AdminLogin searchAdminName(String name) {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Criteria cr = session.createCriteria(AdminLogin.class);
		cr.add(Restrictions.eq("username", name));
		AdminLogin claim=(AdminLogin)cr.uniqueResult();
		return claim;
	}

}
