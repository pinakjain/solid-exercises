package com.theladders.solid.srp.processors;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;

public class JobApplicationProcessor {
	
	private final JobApplicationSystem    jobApplicationSystem;
	private final ResumeManager           resumeManager;
	
	public JobApplicationProcessor(JobApplicationSystem jobApplicationSystem, ResumeManager resumeManager)
	{
		this.jobApplicationSystem = jobApplicationSystem;
		this.resumeManager = resumeManager;
	}
	
	public void apply(boolean currentResume, boolean activeResume, Jobseeker jobseeker, Job job, String fileName)
	{
		Resume resume = resumeManager.saveNewOrRetrieveExistingResume(fileName,jobseeker, currentResume, activeResume);
		UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
		JobApplicationResult applicationResult = jobApplicationSystem.apply(application);

		if (applicationResult.failure())
		{
			throw new ApplicationFailureException(applicationResult.toString());
		}
	}

}
