package com.theladders.solid.ocp.user;

import java.util.ArrayList;
import java.util.List;

import com.theladders.solid.ocp.resume.ConfidentialPhraseCategory;

public class User
{
	private final int id;
//	private final Name name;
//	private final ContactInfo contactInfo;
//	private final EmailAddress emailAddress;
//	private final MailingAddress mailingAddress;
//	private final PhoneNumber phoneNumber;
//	private final WorkExperience workExperience;

	
	public User(int id){
		this.id = id;
	}

//	public User(int id, Name name, ContactInfo contactInfo,
//			EmailAddress emailAddress, MailingAddress mailingAddress,
//			PhoneNumber phoneNumber, WorkExperience workExperience) {
//		this.id = id;
//		this.name = name;
//		this.contactInfo = contactInfo;
//		this.emailAddress = emailAddress;
//		this.mailingAddress = mailingAddress;
//		this.phoneNumber = phoneNumber;
//		this.workExperience = workExperience;
//	}



	public int getId()
	{
		return id;
	}
	
	public List<String> getAllConfidentialPharseCategoryNames(){
		List<String> allConfidentialPharseCategoryNames = new ArrayList<String>();
		for(ConfidentialPhraseCategory category : ConfidentialPhraseCategory.values()){
			allConfidentialPharseCategoryNames.add(category.name());
		}
		return allConfidentialPharseCategoryNames;	
	}
	
	public List<String> getUserContactConfidentialPharseCategoryNames(){
		List<String> allConfidentialPharseCategoryNames = new ArrayList<String>();
		for(UserContactConfidentialPhraseCategory category : UserContactConfidentialPhraseCategory.values()){
			allConfidentialPharseCategoryNames.add(category.name());
		}
		return allConfidentialPharseCategoryNames;	
	}
	
//	public List<String> getAllConfidentialPharseCategoryNames(){
//		List<String> allConfidentialPharseCategoryNames = new ArrayList<String>();
//		allConfidentialPharseCategoryNames.add(this.name.getConfidentialPhraseCategoryName());
//		allConfidentialPharseCategoryNames.add(this.contactInfo.getConfidentialPhraseCategoryName());
//		allConfidentialPharseCategoryNames.add(this.emailAddress.getConfidentialPhraseCategoryName());
//		allConfidentialPharseCategoryNames.add(this.mailingAddress.getConfidentialPhraseCategoryName());
//		allConfidentialPharseCategoryNames.add(this.phoneNumber.getConfidentialPhraseCategoryName());
//		allConfidentialPharseCategoryNames.add(this.workExperience.getConfidentialPhraseCategoryName());
//		return allConfidentialPharseCategoryNames;
//	}
//	
//	public List<String> getUserContactConfidentialPharseCategoryNames(){
//		List<String> userContactConfidentialPharseCategoryNames = new ArrayList<String>();
//		userContactConfidentialPharseCategoryNames.add(this.contactInfo.getConfidentialPhraseCategoryName());
//		userContactConfidentialPharseCategoryNames.add(this.emailAddress.getConfidentialPhraseCategoryName());
//		userContactConfidentialPharseCategoryNames.add(this.mailingAddress.getConfidentialPhraseCategoryName());
//		userContactConfidentialPharseCategoryNames.add(this.phoneNumber.getConfidentialPhraseCategoryName());
//		return userContactConfidentialPharseCategoryNames;
//	}
}
