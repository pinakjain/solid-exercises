package com.theladders.solid.ocp.user;

import com.theladders.solid.ocp.resume.ConfidentialPhrase;

public class MailingAddress extends ConfidentialPhrase{
	
	private final String mailAddress;
	
	public MailingAddress (String mailAddress){
		this.mailAddress = mailAddress;
	}
}
