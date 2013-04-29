package com.theladders.solid.isp.oldjob;

import java.util.Date;
import java.util.List;

import com.theladders.solid.isp.oldjob.stubs.Discipline;
import com.theladders.solid.isp.oldjob.stubs.Experience;
import com.theladders.solid.isp.oldjob.stubs.Industry;
import com.theladders.solid.isp.oldjob.stubs.Region;

public interface JobCommon
{
  
  /**
   * Get the date this job was entered into the system.
   *
   * @return the Date the job was entered.
   */
  Date getEntryDate();

  String getLocation();

  int getOldJobId();

  /**
   * Get the date this job was published.
   *
   * @return the Date the job was published.
   */
  Date getPublicationDate();

  /**
   * Get the region for this job.
   *
   * @return the region for this job.
   */
  Region getRegion();

  /**
   * Get the "reportsTo" field.
   *
   * @return reportsTo
   */
  String getReportsTo();

  int getSubscriberId();


  /**
   * @return returns true if this job was posted anonymously
   */
  boolean isAnonymous();

  boolean isConfidential();

  boolean isExclusive();

  /**
   * Is this job a JobReq? JobReqs are jobs entered into our site directly by recruiters.
   *
   * @return true if job is a JobReq, false otherwise.
   */
  boolean isJobReq();

  boolean isReimbursable();

  /**
   * @return The last time this job was updated
   */
  Date getUpdateTime();

  
}
