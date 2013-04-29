package com.theladders.solid.isp.oldjob.stubs;

import java.util.List;

import com.theladders.solid.isp.oldjob.IJobDetails;

public class JobDetails implements IJobDetails{
	
	private Experience experience;
	private String title;
	private Industry industry;
	private List<Discipline> disciplines;
	private String description;
	
	
	@Override
	public Experience getExperience() {
		// TODO Auto-generated method stub
		return experience;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public Industry getIndustry() {
		// TODO Auto-generated method stub
		return industry;
	}

	@Override
	public List<Discipline> getDisciplines() {
		// TODO Auto-generated method stub
		return disciplines;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

}
