package com.theladders.solid.ocp.resume;

public class ConfidentialPhrase
{
  private boolean isConfidential;
  private String confidentialPhraseCategoryName;

  public boolean isConfidential()
  {
    return isConfidential;
  }

  public void setConfidential(boolean isConfidential)
  {
    this.isConfidential = isConfidential;
  }
  
  public String getConfidentialPhraseCategoryName(){
	  return this.confidentialPhraseCategoryName;
  }
}
