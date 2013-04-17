package com.theladders.solid.srp.applicationViews;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

public class InvalidJobView {
	
	
	public static void provideInvalidJobView(HttpResponse response, int jobId)
	{
		Map<String, Object> model = new HashMap<>();
		model.put("jobId", jobId);

		Result result = new Result("invalidJob", model);
		response.setResult(result);
	}

}
