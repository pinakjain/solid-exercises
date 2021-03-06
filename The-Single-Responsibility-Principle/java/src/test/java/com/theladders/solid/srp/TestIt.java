package com.theladders.solid.srp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import com.theladders.solid.srp.controllers.JobApplicationController;
import com.theladders.solid.srp.controllers.ResumeController;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.http.HttpSession;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobRepository;
import com.theladders.solid.srp.job.JobSearchManager;
import com.theladders.solid.srp.job.application.JobApplicationRepository;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.SuccessfulApplication;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.JobseekerProfileRepository;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.processors.JobApplicationProcessor;
import com.theladders.solid.srp.processors.JobSearchProcessor;
import com.theladders.solid.srp.processors.JobSeekerProfileProcessor;
import com.theladders.solid.srp.processors.ResumeProcessor;
import com.theladders.solid.srp.resume.ActiveResumeRepository;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.resume.ResumeRepository;

public class TestIt
{
	private static final int INVALID_JOB_ID        = 555;
	private static final String SHARED_RESUME_NAME = "A Resume";
	private static final int JOBSEEKER_WITH_RESUME = 777;
	private static final int INCOMPLETE_JOBSEEKER  = 888;
	private static final int APPROVED_JOBSEEKER    = 1010;

	private JobApplicationController   jobApplicationController;
	private ResumeController 		   resumeController;	
	private JobRepository              jobRepository;
	private ResumeRepository           resumeRepository;
	private JobApplicationRepository   jobApplicationRepository;
	private JobseekerProfileRepository jobseekerProfileRepository;
	private ActiveResumeRepository     activeResumeRepository;
	private SuccessfulApplication      existingApplication;

	@Test
	public void requestWithValidJob()
	{
		Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true);
		HttpSession session = new HttpSession(JOBSEEKER);

		Map<String, String> parameters = new HashMap<>();
		parameters.put("jobId","5");
		parameters.put("fileName", SHARED_RESUME_NAME);

		HttpRequest request = new HttpRequest(session, parameters);

		HttpResponse response = new HttpResponse();

		jobApplicationController.jobApplicationHandler(request, response);

		assertEquals("success", response.getResultType());
	}

	@Test
	public void requestWithValidJobByBasic()
	{
		Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, false);
		HttpSession session = new HttpSession(JOBSEEKER);

		Map<String, String> parameters = new HashMap<>();
		parameters.put("jobId","5");
		parameters.put("fileName", SHARED_RESUME_NAME);

		HttpRequest request = new HttpRequest(session, parameters);

		HttpResponse response = new HttpResponse();

		jobApplicationController.jobApplicationHandler(request, response);

		assertEquals("success", response.getResultType());
	}

	@Test
	public void applyUsingExistingResume()
	{
		Jobseeker JOBSEEKER = new Jobseeker(JOBSEEKER_WITH_RESUME, true);
		HttpSession session = new HttpSession(JOBSEEKER);

		Map<String, String> parameters = new HashMap<>();
		parameters.put("jobId","5");
		parameters.put("whichResume", "existing");
		parameters.put("fileName", null);

		HttpRequest request = new HttpRequest(session, parameters);

		HttpResponse response = new HttpResponse();

		jobApplicationController.jobApplicationHandler(request, response);

		assertEquals("success", response.getResultType());
	}

	@Test
	public void requestWithInvalidJob()
	{
		Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true);
		HttpSession session = new HttpSession(JOBSEEKER);

		Map<String, String> parameters = new HashMap<>();
		parameters.put("jobId", String.valueOf(INVALID_JOB_ID));
		parameters.put("fileName", SHARED_RESUME_NAME);

		HttpRequest request = new HttpRequest(session, parameters);

		HttpResponse response = new HttpResponse();

		jobApplicationController.jobApplicationHandler(request, response);

		assertEquals("invalidJob", response.getResultType());
	}

	@Test
	public void requestWithNoResume()
	{
		Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true);
		HttpSession session = new HttpSession(JOBSEEKER);

		Map<String, String> parameters = new HashMap<>();
		parameters.put("jobId", "5");
		parameters.put("fileName", null);

		HttpRequest request = new HttpRequest(session, parameters);

		HttpResponse response = new HttpResponse();

		jobApplicationController.jobApplicationHandler(request, response);

		assertEquals("error", response.getResultType());
	}

	@Test
	public void reapplyToJob()
	{
		Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true);
		HttpSession session = new HttpSession(JOBSEEKER);

		Map<String, String> parameters = new HashMap<>();
		parameters.put("jobId","15");
		parameters.put("fileName", SHARED_RESUME_NAME);

		HttpRequest request = new HttpRequest(session, parameters);

		HttpResponse response = new HttpResponse();

		jobApplicationController.jobApplicationHandler(request, response);

		assertEquals("error", response.getResultType());
	}

	@Test
	public void unapprovedBasic()
	{
		Jobseeker JOBSEEKER = new Jobseeker(INCOMPLETE_JOBSEEKER, false);
		HttpSession session = new HttpSession(JOBSEEKER);

		Map<String, String> parameters = new HashMap<>();
		parameters.put("jobId","5");
		parameters.put("fileName", SHARED_RESUME_NAME);

		HttpRequest request = new HttpRequest(session, parameters);

		HttpResponse response = new HttpResponse();

		jobApplicationController.jobApplicationHandler(request, response);

		assertEquals("completeResumePlease", response.getResultType());
	}

	@Test
	public void resumeIsSaved()
	{
		Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true);
		HttpSession session = new HttpSession(JOBSEEKER);

		Map<String, String> parameters = new HashMap<>();
		parameters.put("fileName", SHARED_RESUME_NAME);

		HttpRequest request = new HttpRequest(session, parameters);

		HttpResponse response = new HttpResponse();

		resumeController.saveResumeHandler(request, response);

		assertTrue(resumeRepository.contains(new Resume(SHARED_RESUME_NAME)));
	}

	@Test
	public void resumeIsMadeActive()
	{
		Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true);
		HttpSession session = new HttpSession(JOBSEEKER);

		Map<String, String> parameters = new HashMap<>();
		parameters.put("fileName", SHARED_RESUME_NAME);

		HttpRequest request = new HttpRequest(session, parameters);

		HttpResponse response = new HttpResponse();

		resumeController.makeActiveResumeHandler(request, response);

		assertEquals(new Resume(SHARED_RESUME_NAME), activeResumeRepository.activeResumeFor(APPROVED_JOBSEEKER));
	}

	@Before
	public void setup()
	{
		setupJobseekerProfileRepository();
		setupJobRepository();
		setupResumeRepository();
		setupActiveResumeRepository();
		setupJobApplicationRepository();
		setupJobApplicationController();
		setupResumeController();
	}

	private void setupJobseekerProfileRepository()
	{
		jobseekerProfileRepository = new JobseekerProfileRepository();

		addToJobseekerProfileRepository(APPROVED_JOBSEEKER, ProfileStatus.APPROVED);
		addToJobseekerProfileRepository(INCOMPLETE_JOBSEEKER, ProfileStatus.INCOMPLETE);
		addToJobseekerProfileRepository(JOBSEEKER_WITH_RESUME, ProfileStatus.APPROVED);
	}

	private void addToJobseekerProfileRepository(int id, ProfileStatus status)
	{
		JobseekerProfile profile = new JobseekerProfile(id, status);
		jobseekerProfileRepository.addProfile(profile);
	}

	private void setupJobRepository()
	{
		jobRepository = new JobRepository();

		addJobToRepository(5);
		addJobToRepository(15);
		addJobToRepository(51);
		addJobToRepository(57);
		addJobToRepository(501);
		addJobToRepository(1555);
		addJobToRepository(5012);
		addJobToRepository(50111);
	}

	private void addJobToRepository(int jobId)
	{
		if (jobId != INVALID_JOB_ID)
		{
			jobRepository.addJob(new Job(jobId));
		}
	}

	private void setupResumeRepository()
	{
		resumeRepository = new ResumeRepository();
		//		Resume jobSeekerWithResume = new Resume("Blammo");
		//		resumeRepository.saveResume(JOBSEEKER_WITH_RESUME, jobSeekerWithResume);
	}

	private void setupActiveResumeRepository()
	{
		activeResumeRepository = new ActiveResumeRepository();

		activeResumeRepository.makeActive(JOBSEEKER_WITH_RESUME, new Resume("Blammo"));
	}

	private void setupJobApplicationRepository()
	{
		jobApplicationRepository = new JobApplicationRepository();

		addToJobApplicationRepository();
	}

	private void addToJobApplicationRepository()
	{
		Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true);
		Job job = new Job(15);
		Resume resume = new Resume("foo");

		existingApplication = new SuccessfulApplication(JOBSEEKER, job, resume);

		jobApplicationRepository.add(existingApplication);
	}

	private void setupJobApplicationController()
	{
		JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
		JobSearchManager jobSearchManager = new JobSearchManager(jobRepository);
		JobApplicationSystem jobApplicationSystem = new JobApplicationSystem(jobApplicationRepository);
		ResumeManager resumeManager = new ResumeManager(resumeRepository, activeResumeRepository);
		
		JobSeekerProfileProcessor jobSeekerProfileProcessor = new JobSeekerProfileProcessor(jobseekerProfileManager);
		JobApplicationProcessor jobApplicationProcessor = new JobApplicationProcessor(jobApplicationSystem, resumeManager);
		JobSearchProcessor jobSearchProcessor = new JobSearchProcessor(jobSearchManager);
		
		jobApplicationController = new JobApplicationController(jobApplicationProcessor, jobSeekerProfileProcessor, jobSearchProcessor);
	}

	private void setupResumeController()
	{
		ResumeManager resumeManager = new ResumeManager(resumeRepository, activeResumeRepository);
		ResumeProcessor resumeProcessor = new ResumeProcessor(resumeManager);
		resumeController = new ResumeController(resumeProcessor);
	}
}
