package com.theladders.solid.srp.job;


public class JobSearchManager
{
  private final JobRepository repository;

  public JobSearchManager(JobRepository repository)
  {
    this.repository = repository;
  }

  public Job getJob(int jobId)
  {
    return repository.getJob(jobId);
  }
}
