package com.theladders.solid.srp.processors;

import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;

public class ResumeProcessor {
	
	private final ResumeManager resumeManager;
	
	public ResumeProcessor(ResumeManager resumeManager){
		this.resumeManager = resumeManager;
	}
	
	public Resume save(Jobseeker jobseeker, String fileName)
	{
		return resumeManager.saveResume(jobseeker, fileName);
	}

	public void makeActive(Jobseeker jobseeker, String fileName)
	{
		Resume resume = new Resume(fileName);
		resumeManager.makeActiveResume(jobseeker, resume);
	}

}
