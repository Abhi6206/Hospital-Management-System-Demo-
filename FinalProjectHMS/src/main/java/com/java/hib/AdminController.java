package com.java.hib;

import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class AdminController {
	private AdminLogin admin;
	private AdminLoginImpl adminDao;

	public AdminLogin getAdmin() {
		return admin;
	}

	public void setAdmin(AdminLogin admin) {
		this.admin = admin;
	}

	public AdminLoginImpl getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminLoginImpl adminDao) {
		this.adminDao = adminDao;
	}

	public String adminSignInValidate() {
		if (addValid()) {
			return adminDao.adminSignIn(admin);
		}
		return "";
	}
	
	public String adminLoginValidate() {
		if(addValid2()) {
			return adminDao.loginDao(admin);
		}
		return "";
	}
	
	public boolean addValid2() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
		
		boolean flag = true;
		if(admin.getUsername().length() <= 0) {
			context.addMessage("form:username", new FacesMessage("Username cannot be empty"));
			flag = false;
		}
		
		if(admin.getPassword().length() <= 0) {
			context.addMessage("form:passInput", new FacesMessage("Password cannot be empty"));
			flag = false;
		}
		return flag;
		
	}

	public boolean addValid() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();

		String username = "^[^\\s]{8,16}$";
		String email = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		String password = "^(?=.*[A-Z])(?=.*[a-z0-9])(?=.*[^a-zA-Z0-9]).{8,18}$";

		boolean flag = true;
		if (admin.getName().length() <= 0) {
			context.addMessage("form:name", new FacesMessage("User Name cannot be empty"));
			flag = false;
		}
		
		if (admin.getEmail().length() <= 0) {
			context.addMessage("form:email", new FacesMessage("Email Cannot Be Empty"));
			System.out.println("Email Failed...");
			flag = false;
		}
		if (!Pattern.matches(email, admin.getEmail())) {
			context.addMessage("form:email", new FacesMessage("Invalid Email."));
			System.out.println("Email Failed...");
			flag = false;
		}
		
		if (!Pattern.matches(username, admin.getUsername())) {
			context.addMessage("form:username", new FacesMessage("Username Contains min 8 characters."));
			System.out.println("UserName Failed...");
			flag = false;
		}

		if (admin.getPassword().length() <= 0) {
			context.addMessage("form:passInput", new FacesMessage("Password Cannot be Empty..."));
			System.out.println("Password Null Failed...");
			flag = false;
		} else if (!Pattern.matches(password, admin.getPassword())) {
			context.addMessage("form:passInput", new FacesMessage("Invalid Password format.Hint: @Abcde1234"));
			System.out.println("Password failed.");
			flag = false;
		}

		// SECURITYQUESTION
		if (admin.getSecurityQuestion() == null || admin.getSecurityQuestion().isEmpty()) {
			context.addMessage("form:securityQuestion", new FacesMessage("Select atleast one security question."));
			System.out.println("Select security question failed...");
			flag = false;
		}
		// SECURITYANSWER
		if (admin.getSecurityAnswer().length() <= 0) {
			context.addMessage("form:ans", new FacesMessage("Please give your answer"));
			flag = false;
		}
		return flag;

	}
}
