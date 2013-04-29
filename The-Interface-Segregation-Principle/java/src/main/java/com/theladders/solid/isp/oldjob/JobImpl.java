package com.theladders.solid.isp.oldjob;

import java.util.Date;
import java.util.List;

import com.theladders.solid.isp.oldjob.stubs.Company;
import com.theladders.solid.isp.oldjob.stubs.Compensation;
import com.theladders.solid.isp.oldjob.stubs.Discipline;
import com.theladders.solid.isp.oldjob.stubs.Experience;
import com.theladders.solid.isp.oldjob.stubs.Industry;
import com.theladders.solid.isp.oldjob.stubs.JobDetails;
import com.theladders.solid.isp.oldjob.stubs.Region;

public abstract class JobImpl implements JobCommon {
	private Company company;
	private Compensation compensation;
	private JobDetails details;
	private Date entryDate;
	private String location;
	private int oldJobId = 0;
	private Date publicationDate;
	private Region region;
	private String reportsTo;
	private int subscriberId = 0;
	private boolean anonymous = false;
	private boolean confidential = false;
	private boolean exclusive = false;
	private boolean reimbursable = false;
	private Date updateTime = null;

	

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getOldJobId() {
		return oldJobId;
	}

	public void setOldJobId(int oldJobId) {
		this.oldJobId = oldJobId;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Region getRegion() {
		return region;
	}

	public String getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(String reportsTo) {
		this.reportsTo = reportsTo;
	}

	public int getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public boolean isConfidential() {
		return confidential;
	}

	public void setConfidential(boolean confidential) {
		this.confidential = confidential;
	}

	public boolean isExclusive() {
		return exclusive;
	}

	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}

	public boolean isReimbursable() {
		return reimbursable;
	}

	public void setReimbursable(boolean reimbursable) {
		this.reimbursable = reimbursable;
	}

	/**
	 * WARNING: THIS IS NOT IMPLEMENTED EVERYWHERE. I have not implemented
	 * getUpdateTime to be returned from the DAO anywhere except the job search
	 * flow.
	 * 
	 * This method is used by search to set the update time which is used for
	 * version information.
	 * 
	 * @return the last time this job was updated.
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
