package com.theladders.solid.srp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.applicationViews.ApplySuccessView;
import com.theladders.solid.srp.applicationViews.ErrorView;
import com.theladders.solid.srp.applicationViews.InvalidJobView;
import com.theladders.solid.srp.applicationViews.ResumeCompletionView;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.processors.JobApplicationProcessor;
import com.theladders.solid.srp.processors.JobSearchProcessor;
import com.theladders.solid.srp.processors.JobSeekerProfileProcessor;


public class JobApplicationController
{
	private final JobApplicationProcessor jobApplicationProcessor;
	private final JobSeekerProfileProcessor jobSeekerProfileProcessor;
	private final JobSearchProcessor jobSearchProcessor;
	
	public JobApplicationController(JobApplicationProcessor jobApplicationProcessor, JobSeekerProfileProcessor jobSeekerProfileProcessor, JobSearchProcessor jobSearchProcessor)
	{
		this.jobApplicationProcessor = jobApplicationProcessor;
		this.jobSeekerProfileProcessor = jobSeekerProfileProcessor;
		this.jobSearchProcessor = jobSearchProcessor;
	}

	public HttpResponse jobApplicationHandler(HttpRequest request, HttpResponse response)
	{
		Jobseeker jobseeker = request.getSession().getJobseeker();
		JobseekerProfile profile = jobSeekerProfileProcessor.getProfile(jobseeker);

		String jobIdString = request.getParameter("jobId");
		int jobId = Integer.parseInt(jobIdString);
		Job job = jobSearchProcessor.searchJob(jobId);
		
		String origFileName = request.getParameter("fileName");
		String currentResume = request.getParameter("whichResume");
		String activeResume = request.getParameter("makeResumeActive");
		

		if (job == null)
		{
			InvalidJobView.provideInvalidJobView(response, jobId);
			return response;
		}

		Map<String, Object> model = new HashMap<>();
		List<String> errList = new ArrayList<>();

		try
		{
			jobApplicationProcessor.apply(currentResume, activeResume, jobseeker, job, origFileName);
		}
		catch (Exception e)
		{
			errList.add("We could not process your application.");
			ErrorView.provideErrorView(response, errList, model);
			return response;
		}

		model.put("jobId", job.getJobId());
		model.put("jobTitle", job.getTitle());

		if (!jobseeker.isPremium() && (profile.getStatus().equals(ProfileStatus.INCOMPLETE) ||
				profile.getStatus().equals(ProfileStatus.NO_PROFILE) ||
				profile.getStatus().equals(ProfileStatus.REMOVED)))
		{
			ResumeCompletionView.provideResumeCompletionView(response, model);
			return response;
		}

		ApplySuccessView.provideApplySuccessView(response, model);
		return response;
	}

}
