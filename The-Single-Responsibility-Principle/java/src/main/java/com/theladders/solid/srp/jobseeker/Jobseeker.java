package com.theladders.solid.srp.jobseeker;

import com.theladders.solid.srp.resume.Resume;

public class Jobseeker
{
  private final int id;
  private final boolean hasPremiumAccount;
  private Resume activeResume;

  public Jobseeker(int id, boolean hasPremiumAccount)
  {
    this.id = id;
    this.hasPremiumAccount = hasPremiumAccount;
  }
  
  public Jobseeker(int id, boolean hasPremiumAccount, Resume activeResume)
  {
    this.id = id;
    this.hasPremiumAccount = hasPremiumAccount;
    this.activeResume = activeResume;  
  }

  public boolean isPremium()
  {
    return hasPremiumAccount;
  }

  public int getId()
  {
    return id;
  }
  
  public void activeResume(Resume activeResume){
	  this.activeResume = activeResume;  
  }
  
  public Resume getActiveResume() {
	return activeResume;
  }

@Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Jobseeker other = (Jobseeker) obj;
    if (id != other.id)
      return false;
    return true;
  }
}
