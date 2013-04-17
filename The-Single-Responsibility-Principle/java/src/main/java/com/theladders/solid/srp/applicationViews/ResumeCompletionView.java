package com.theladders.solid.srp.applicationViews;

import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

public class ResumeCompletionView {
	
	public static void provideResumeCompletionView(HttpResponse response, Map<String, Object> model) 
	{
		Result result = new Result("completeResumePlease", model);
		response.setResult(result);
	}

}
