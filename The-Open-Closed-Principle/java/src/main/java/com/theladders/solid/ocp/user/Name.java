package com.theladders.solid.ocp.user;

import com.theladders.solid.ocp.resume.ConfidentialPhrase;

public class Name extends ConfidentialPhrase{
	
	private final String name;
	
	public Name (String name){
		super();
		this.name = name;
	}

}
