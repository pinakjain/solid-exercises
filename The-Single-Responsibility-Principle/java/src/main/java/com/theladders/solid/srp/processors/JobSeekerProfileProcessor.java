package com.theladders.solid.srp.processors;

import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;

public class JobSeekerProfileProcessor {
	
	private final JobseekerProfileManager jobseekerProfileManager;

	public JobSeekerProfileProcessor(JobseekerProfileManager jobseekerProfileManager) {
		this.jobseekerProfileManager = jobseekerProfileManager;
	}
	
	public JobseekerProfile getProfile(Jobseeker jobseeker) {
	    return  jobseekerProfileManager.getJobSeekerProfile(jobseeker);
	}

}
