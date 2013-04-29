package com.theladders.solid.srp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.applicationViews.ErrorView;
import com.theladders.solid.srp.applicationViews.ResumeCompletionView;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.http.RequestInfo;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.processors.RequestProcessor;
import com.theladders.solid.srp.processors.ResumeProcessor;

public class ResumeController {

	private final ResumeProcessor resumeProcessor;

	public ResumeController(ResumeProcessor resumeProcessor)
	{
		this.resumeProcessor = resumeProcessor;
	}
	
	public HttpResponse saveResumeHandler(HttpRequest request, HttpResponse response)
	{
		RequestInfo info = RequestProcessor.processRequest(request);
		
		Map<String, Object> model = new HashMap<>();
		List<String> errList = new ArrayList<>();
		try
		{
			resumeProcessor.save(info.getJobseeker(), info.getFilename());
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

	public HttpResponse makeActiveResumeHandler(HttpRequest request, HttpResponse response) {
		RequestInfo info = RequestProcessor.processRequest(request);
		
		Map<String, Object> model = new HashMap<>();
		List<String> errList = new ArrayList<>();
	
		try
		{
			resumeProcessor.makeActive(info.getJobseeker(), info.getFilename());
		}
		catch (Exception e)
		{
			errList.add("We not could make the given resume active.");
			ErrorView.provideErrorView(response, errList, model);
			return response;
		}

		ResumeCompletionView.provideResumeCompletionView(response, model);
		return response;
	}

	

}
