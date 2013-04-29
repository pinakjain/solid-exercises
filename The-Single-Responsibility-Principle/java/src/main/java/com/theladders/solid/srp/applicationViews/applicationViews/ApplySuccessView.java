package com.theladders.solid.srp.applicationViews;

import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

public class ApplySuccessView {
	
	public static void provideApplySuccessView(HttpResponse response, Map<String, Object> model) 
	{
		Result result = new Result("success", model);
		response.setResult(result);
	}


}
