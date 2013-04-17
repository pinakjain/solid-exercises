package com.theladders.solid.srp.resume;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.ResumeNotFoundException;
import com.theladders.solid.srp.jobseeker.Jobseeker;

public class ResumeManager
{
  private final ResumeRepository resumeRepository;

  public ResumeManager(ResumeRepository resumeRepository)
  {
    this.resumeRepository = resumeRepository;
  }

  public Resume saveResume(Jobseeker jobseeker,
                           String fileName)
  {

    Resume resume = new Resume(fileName);
    resumeRepository.saveResume(jobseeker.getId(), resume);

    return resume;
  }
  
  public void makeActiveResume(Jobseeker jobseeker, Resume resume) throws ResumeNotFoundException{
	  if(resume == null){
		  throw new ResumeNotFoundException("No resume found");
	  }
	  jobseeker.activeResume(resume);
  }
  
  public Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
		  Jobseeker jobseeker,
		  HttpRequest request)
  {	

	  Resume resume;
	  if (!"existing".equals(request.getParameter("whichResume")))
	  {
		  resume = saveResume(jobseeker, newResumeFileName);

		  if (resume != null && "yes".equals(request.getParameter("makeResumeActive")))
		  {
			  try {
				  makeActiveResume(jobseeker, resume);
			  } catch (ApplicationFailureException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  }
		  }
	  }
	  else
	  {
		  resume = jobseeker.getActiveResume();
	  }

	  return resume;
  }
}
