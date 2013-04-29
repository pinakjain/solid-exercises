package com.theladders.solid.srp.processors;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.RequestInfo;
import com.theladders.solid.srp.jobseeker.Jobseeker;

public class RequestProcessor {
	
	public static RequestInfo processRequest(HttpRequest httpRequest){
		
		Jobseeker jobseeker = httpRequest.getSession().getJobseeker();
		int jobId = 0;
		if(httpRequest.getParameter("jobId") != null){
			jobId = Integer.parseInt(httpRequest.getParameter("jobId"));
		}
		
		String origFileName = null;
		if(httpRequest.getParameter("fileName") != null){
			origFileName = httpRequest.getParameter("fileName");
		}
		boolean currentResume = false;
		if(httpRequest.getParameter("whichResume") != null && httpRequest.getParameter("whichResume").equals("existing")){
			currentResume = true;
		}
		boolean activeResume = false;
		if(httpRequest.getParameter("makeResumeActive") != null && httpRequest.getParameter("makeResumeActive").equals("yes")){
			activeResume = true;
		}
		RequestInfo info = new RequestInfo(jobseeker, jobId, origFileName, currentResume, activeResume);
		
		return info;
	}

}
