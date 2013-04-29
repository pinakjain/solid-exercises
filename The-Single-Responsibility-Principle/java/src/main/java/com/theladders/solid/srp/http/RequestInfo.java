package com.theladders.solid.srp.http;

import com.theladders.solid.srp.jobseeker.Jobseeker;

public class RequestInfo {
	
	private Jobseeker jobseeker;
	private int jobId;
	private String filename;
	private boolean currentResume;
	private boolean makeResumeActive;
	
	public RequestInfo(Jobseeker jobseeker, int jobId, String filename, boolean currentResume, boolean makeResumeActive) {
		this.jobseeker = jobseeker;
		this.jobId = jobId;
		this.filename = filename;
		this.currentResume = currentResume;
		this.makeResumeActive = makeResumeActive;
	}
	
	public Jobseeker getJobseeker() {
		return jobseeker;
	}
	public void setJobseeker(Jobseeker jobseeker) {
		this.jobseeker = jobseeker;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public boolean isCurrentResume() {
		return currentResume;
	}
	public void setCurrentResume(boolean currentResume) {
		this.currentResume = currentResume;
	}
	public boolean isMakeResumeActive() {
		return makeResumeActive;
	}
	public void setMakeResumeActive(boolean makeResumeActive) {
		this.makeResumeActive = makeResumeActive;
	}

}
