package com.theladders.solid.srp.processors;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchManager;

public class JobSearchProcessor {
	
	private final JobSearchManager jobSearchManager;

	public JobSearchProcessor(JobSearchManager jobSearchManager) {
		this.jobSearchManager = jobSearchManager;
	}
	
	public Job searchJob(int jobId){
	    return jobSearchManager.getJob(jobId);
	}

}
