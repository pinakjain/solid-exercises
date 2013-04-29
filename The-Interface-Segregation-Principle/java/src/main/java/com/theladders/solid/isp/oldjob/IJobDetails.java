package com.theladders.solid.isp.oldjob;

import java.util.List;

import com.theladders.solid.isp.oldjob.stubs.Discipline;
import com.theladders.solid.isp.oldjob.stubs.Experience;
import com.theladders.solid.isp.oldjob.stubs.Industry;

public interface IJobDetails {
	
	 /**
	   * Returns an object that represents the number of years of experience
	   * that are required for this job.
	   * @return experience object
	   */
	  Experience getExperience();
	  
	  /**
	   * Get this job's title.
	   *
	   * @return the title for this job.
	   */
	  String getTitle();
	  
	  /**
	   * Get the Industry for this job.
	   *
	   * @return the Industry for this job.
	   */
	  Industry getIndustry();
	  
	  /**
	   * Retrieves a list of disciplines for this job.
	   *
	   * @return List of Disciplines
	   */
	  List<Discipline> getDisciplines();
	  
	  
	  /**
	   * Refactored so it can be used by both job and JobReq
	   * @return fullJobDescription()
	   *
	   */
	  String getDescription();
}
