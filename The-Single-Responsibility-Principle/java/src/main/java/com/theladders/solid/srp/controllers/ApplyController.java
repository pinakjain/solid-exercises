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
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;

public class ApplyController
{
	private final JobseekerProfileManager jobseekerProfileManager;
	private final JobSearchService        jobSearchService;
	private final JobApplicationSystem    jobApplicationSystem;
	private final ResumeManager           resumeManager;
	
	public ApplyController(JobseekerProfileManager jobseekerProfileManager,
			JobSearchService jobSearchService,
			JobApplicationSystem jobApplicationSystem,
			ResumeManager resumeManager)
	{
		this.jobseekerProfileManager = jobseekerProfileManager;
		this.jobSearchService = jobSearchService;
		this.jobApplicationSystem = jobApplicationSystem;
		this.resumeManager = resumeManager;
	}

	public HttpResponse applyHandler(HttpRequest request,
			HttpResponse response,
			String origFileName)
	{
		Jobseeker jobseeker = request.getSession().getJobseeker();
		JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);

		String jobIdString = request.getParameter("jobId");
		int jobId = Integer.parseInt(jobIdString);

		Job job = jobSearchService.getJob(jobId);

		if (job == null)
		{
			InvalidJobView.provideInvalidJobView(response, jobId);
			return response;
		}

		Map<String, Object> model = new HashMap<>();

		List<String> errList = new ArrayList<>();

		try
		{
			applyProcessor(request, jobseeker, job, origFileName);
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

	private void applyProcessor(HttpRequest request,
			Jobseeker jobseeker,
			Job job,
			String fileName)
	{
		String currentResume = request.getParameter("whichResume");
		String activeResume = request.getParameter("makeResumeActive");
		Resume resume = resumeManager.saveNewOrRetrieveExistingResume(fileName,jobseeker, currentResume, activeResume);
		UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
		JobApplicationResult applicationResult = jobApplicationSystem.apply(application);

		if (applicationResult.failure())
		{
			throw new ApplicationFailureException(applicationResult.toString());
		}
	}

}
