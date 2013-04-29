package com.theladders.solid.srp.applicationViews;

import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

public class ErrorView {
	
	public static void provideErrorView(HttpResponse response, List<String> errList, Map<String, Object> model) 
	{
		Result result = new Result("error", model, errList);
		response.setResult(result);
	}

}
