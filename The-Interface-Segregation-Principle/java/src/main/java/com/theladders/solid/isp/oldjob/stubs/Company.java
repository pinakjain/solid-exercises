package com.theladders.solid.isp.oldjob.stubs;

import com.theladders.solid.isp.oldjob.ICompany;


public class Company implements ICompany {
	
	private String name;
	private int size;

	@Override
	public String getCompany() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Integer getCompanySize() {
		// TODO Auto-generated method stub
		return size;
	}
	
	

}
