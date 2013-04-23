package com.theladders.solid.ocp.user;

import com.theladders.solid.ocp.resume.ConfidentialPhrase;

public class WorkExperience extends ConfidentialPhrase{
	
	private final String workExperience;
	
	public WorkExperience (String workExperience){
		super();
		this.workExperience = workExperience;
	}
}
