package com.theladders.solid.lsp;

import java.util.HashMap;
import java.util.Map;

// Just a stub. Nothing to see here.

public class EnvironmentFactory
{
	public static Environment getEnvironmentFor(String hostName)
	{
		HashMap<Object, Object> envMap = new HashMap<Object, Object>();
		Environment env = new Environment(envMap);

		env.putEnvData("isSSL", "true");
		env.putEnvData("home", "http://" + hostName);
		env.putEnvData("secureHome", "https://" + hostName);

		return env;
	}
	
	public static DynamicEnvironment getSecuredDynamicEnv(Environment baseEnv, Map<String, String> propKeyMap){
		HashMap<Object, Object> dynEnvMap = new HashMap<Object, Object>();
		DynamicEnvironment dynEnv = new DynamicEnvironment(baseEnv, propKeyMap, dynEnvMap);
		
		return dynEnv;
	}
	
	public static DynamicEnvironment getInSecuredDynamicEnv(Environment baseEnv, Map<String, String> propKeyMap){
		HashMap<Object, Object> dynEnvMap = new HashMap<Object, Object>();
		DynamicEnvironment dynEnv = new DynamicEnvironment(baseEnv, propKeyMap, dynEnvMap);
		
		return dynEnv;
	}
	
	public static DynamicEnvironment getNoSslDynamicEnv(Environment baseEnv, Map<String, String> propKeyMap){
		HashMap<Object, Object> dynEnvMap = new HashMap<Object, Object>();
		DynamicEnvironment dynEnv = new DynamicEnvironment(baseEnv, propKeyMap, dynEnvMap);
		
		return dynEnv;
	}
}
