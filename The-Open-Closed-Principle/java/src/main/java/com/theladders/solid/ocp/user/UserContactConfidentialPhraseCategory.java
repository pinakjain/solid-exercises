package com.theladders.solid.ocp.user;

public enum UserContactConfidentialPhraseCategory {

	MailingAddress(79),
	PhoneNumber(78),
	EmailAddress(77),
	ContactInfo(80);

	@SuppressWarnings("unused")
	private int id;

	private UserContactConfidentialPhraseCategory(int id)
	{
		this.id = id;

	}
}