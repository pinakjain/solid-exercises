package com.theladders.solid.dip;

import java.util.HashMap;
import java.util.Map;

public class PropertiesDao {
	
	public static String getImagePrefix() {
		return "http://somecdnprodiver.com/static/images/careerAdvice/";
	}
	
	public static String getImagePathPropertyName(){
		return "miniImagePath";
	}

	public static Map<String, String> getcategoryImageMap() {
		Map<String, String> categoryImageMap = new HashMap<>();
		categoryImageMap.put("Interviewing", "interviewing_thumb.jpg");
		categoryImageMap.put("Job Search", "job_search_thumb.jpg");
		categoryImageMap.put("Networking", "networking_thumb.jpg");
		categoryImageMap.put("Personal Branding", "personalBranding_thumb.jpg");
		categoryImageMap.put("Resume", "resume_thumb.jpg");
		categoryImageMap.put("Salary", "salary_thumb.jpg");
		categoryImageMap.put("Assessment", "salary_thumb.jpg");
		categoryImageMap.put("On the Job", "salary_thumb.jpg");
		return categoryImageMap;
		
	}

}
