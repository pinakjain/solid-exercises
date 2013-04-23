package com.theladders.solid.ocp.user;

import com.theladders.solid.ocp.resume.ConfidentialPhrase;

public class ContactInfo extends ConfidentialPhrase{
	
	private final String contactInfo;
	
	public ContactInfo (String contactInfo){
		super();
		this.contactInfo = contactInfo;
	}
	

}
