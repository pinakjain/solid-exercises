package com.theladders.solid.ocp.user;

import com.theladders.solid.ocp.resume.ConfidentialPhrase;

public class EmailAddress extends ConfidentialPhrase {

	private final String email;
	
	public EmailAddress (String email){
		this.email = email;
	}
}
