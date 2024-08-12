package com.aurionpro.test;

import com.aurionpro.model.EmailValidator;
import com.aurionpro.model.PasswordValidator;
import com.aurionpro.model.ValidationContext;

public class ValidatorTest {

	public static void main(String[] args) {
		
		
		String email="vihj";
		String password="123";
		  ValidationContext context = new ValidationContext();
	        context.setValidator(new EmailValidator());
	        String emailResult = context.validate(email);
	        if(!emailResult.equals("true")) {
	        	System.out.println(emailResult);
	        	 
	        }
	        context.setValidator(new PasswordValidator());
	        String passwordResult = context.validate(password);
	        if(!passwordResult.equals("true")) {
	        	System.out.println(passwordResult);
	        	
	        }

	}

}
