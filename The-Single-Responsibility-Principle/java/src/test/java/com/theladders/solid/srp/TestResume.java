package com.theladders.solid.srp;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.http.HttpSession;
import com.theladders.solid.srp.job.JobRepository;
import com.theladders.solid.srp.job.application.JobApplicationRepository;
import com.theladders.solid.srp.job.application.SuccessfulApplication;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfileRepository;
import com.theladders.solid.srp.resume.ActiveResumeRepository;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeRepository;

public class TestResume {
	
	 private static final int INVALID_JOB_ID        = 555;
	  private static final String SHARED_RESUME_NAME = "A Resume";
	  private static final int JOBSEEKER_WITH_RESUME = 777;
	  private static final int INCOMPLETE_JOBSEEKER  = 888;
	  private static final int APPROVED_JOBSEEKER    = 1010;

	  private ApplyController            controller;
	  private JobRepository              jobRepository;
	  private ResumeRepository           resumeRepository;
	  private JobApplicationRepository   jobApplicationRepository;
	  private JobseekerProfileRepository jobseekerProfileRepository;
	  private ActiveResumeRepository     activeResumeRepository;

	  private SuccessfulApplication      existingApplication;
	 
	
	 @Test
	  public void resumeIsSaved()
	  {
	    Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true);
	    HttpSession session = new HttpSession(JOBSEEKER);

	    Map<String, String> parameters = new HashMap<>();
	    parameters.put("jobId","5");

	    HttpRequest request = new HttpRequest(session, parameters);

	    HttpResponse response = new HttpResponse();

	    controller.handle(request, response, SHARED_RESUME_NAME);

	    assertTrue(resumeRepository.contains(new Resume(SHARED_RESUME_NAME)));
	  }

	  @Test
	  public void resumeIsMadeActive()
	  {
	    Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true);
	    HttpSession session = new HttpSession(JOBSEEKER);

	    Map<String, String> parameters = new HashMap<>();
	    parameters.put("jobId","5");
	    parameters.put("makeResumeActive", "yes");

	    HttpRequest request = new HttpRequest(session, parameters);

	    HttpResponse response = new HttpResponse();

	    controller.handle(request, response, "Save Me Seymour");

	    assertEquals(new Resume("Save Me Seymour"), activeResumeRepository.activeResumeFor(APPROVED_JOBSEEKER));
	  }

}
