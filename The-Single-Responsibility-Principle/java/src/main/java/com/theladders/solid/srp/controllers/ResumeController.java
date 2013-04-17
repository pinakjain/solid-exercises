package com.theladders.solid.srp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.applicationViews.ErrorView;
import com.theladders.solid.srp.applicationViews.ResumeCompletionView;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;

public class ResumeController {

	private final ResumeManager           resumeManager;

	public ResumeController(ResumeManager resumeManager)
	{
		this.resumeManager = resumeManager;
	}
	
	public HttpResponse saveResumeHandler(HttpRequest request,
			HttpResponse response,
			String origFileName)
	{
		Jobseeker jobseeker = request.getSession().getJobseeker();
		//	  JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);
		Map<String, Object> model = new HashMap<>();
		List<String> errList = new ArrayList<>();

		try
		{
			saveResume(jobseeker, origFileName);
		}
		catch (Exception e)
		{
			errList.add("We could not save your resume.");
			ErrorView.provideErrorView(response, errList, model);
			return response;
		}

		ResumeCompletionView.provideResumeCompletionView(response, model);
		return response;
	}

	public HttpResponse makeActiveResumeHandler(HttpRequest request, HttpResponse response, String origFileName) {
		Jobseeker jobseeker = request.getSession().getJobseeker();
		//	  JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);
		Map<String, Object> model = new HashMap<>();
		List<String> errList = new ArrayList<>();

		try
		{
			makeActiveResumeProcessor(jobseeker, origFileName);
		}
		catch (Exception e)
		{
			errList.add("We could make the given resume active.");
			ErrorView.provideErrorView(response, errList, model);
			return response;
		}

		ResumeCompletionView.provideResumeCompletionView(response, model);
		return response;
	}

	private Resume saveResume(Jobseeker jobseeker, String fileName)
	{
		return resumeManager.saveResume(jobseeker, fileName);
	}

	private void makeActiveResumeProcessor(Jobseeker jobseeker, String fileName)
	{
		Resume resume = new Resume(fileName);
		resumeManager.makeActiveResume(jobseeker, resume);
	}

}
