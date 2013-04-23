package com.theladders.solid.ocp.user;

import com.theladders.solid.ocp.resume.ConfidentialPhrase;

public class PhoneNumber extends ConfidentialPhrase {
	
private final String phoneNumber;
	
	public PhoneNumber (String phoneNumber){
		super();
		this.phoneNumber = phoneNumber;
	}

}
