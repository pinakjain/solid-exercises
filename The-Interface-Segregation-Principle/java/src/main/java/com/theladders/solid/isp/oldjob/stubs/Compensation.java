package com.theladders.solid.isp.oldjob.stubs;

import com.theladders.solid.isp.oldjob.ICompensation;

public class Compensation implements ICompensation {
	
	private String compensation;
	private String salary;
	private String bonus;
	private String other;
	
	@Override
	public String getCompensation() {
		// TODO Auto-generated method stub
		return compensation;
	}

	@Override
	public String getCompensationSalary() {
		// TODO Auto-generated method stub
		return salary;
	}

	@Override
	public String getCompensationBonus() {
		// TODO Auto-generated method stub
		return bonus;
	}

	@Override
	public String getCompensationOther() {
		// TODO Auto-generated method stub
		return other;
	}

}
