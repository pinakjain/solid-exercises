package com.theladders.solid.ocp.user;

import com.theladders.solid.ocp.resume.ConfidentialPhrase;

@Deprecated
public class CompanyName extends ConfidentialPhrase{
	
	private final String companyName;
	
	public CompanyName (String companyName){
		this.companyName = companyName;
	}
	
	

}