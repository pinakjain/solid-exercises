package com.theladders.solid.srp.resume;

import com.theladders.solid.srp.job.application.ResumeNotFoundException;
import com.theladders.solid.srp.jobseeker.Jobseeker;

public class ResumeManager
{
	private final ResumeRepository resumeRepository;
	
	private final ActiveResumeRepository activeResumeRepository;

	public ResumeManager(ResumeRepository resumeRepository, ActiveResumeRepository activeResumeRepository)
	{
		this.resumeRepository = resumeRepository;
		this.activeResumeRepository = activeResumeRepository;
	}

	public Resume saveResume(Jobseeker jobseeker, String fileName) {

		if(fileName == null){
			throw new ResumeNotFoundException("No resume found");
		}
		Resume resume = new Resume(fileName);
		resumeRepository.saveResume(jobseeker.getId(), resume);
		
		if(activeResumeRepository.activeResumeFor(jobseeker.getId()) == null){
			activeResumeRepository.makeActive(jobseeker.getId(), resume);
		}
		
		return resume;
	}

	public void makeActiveResume(Jobseeker jobseeker, Resume resume) {
		if(resume == null){
			throw new ResumeNotFoundException("No resume found");
		}
		activeResumeRepository.makeActive(jobseeker.getId(), resume);
	}

	public Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
			Jobseeker jobseeker,
			boolean currentResume, boolean activeResume)
	{	

		Resume resume;
		if (!currentResume)
		{
			resume = saveResume(jobseeker, newResumeFileName);

			if (activeResume)
			{
				makeActiveResume(jobseeker, resume);
			}
		}
		else
		{
			resume = activeResumeRepository.activeResumeFor(jobseeker.getId());
		}

		return resume;
	}
}
