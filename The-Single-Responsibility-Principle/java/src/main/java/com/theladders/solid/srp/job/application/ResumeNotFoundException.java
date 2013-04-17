package com.theladders.solid.srp.job.application;

public class ResumeNotFoundException extends RuntimeException {
	
	 public ResumeNotFoundException(String reason)
	  {
	    super(reason);
	  }
}
