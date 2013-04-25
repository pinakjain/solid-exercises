package com.theladders.solid.lsp;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class App
{
	private static final String hostName = "www.theladders.com/";

	public static void main(String[] args)
	{
		EnvSetupFilter filter = new EnvSetupFilter(hostName);

		boolean isSecure = ask("Is the HTTP request secure?");
		boolean loggedInUser = ask("Is a user logged into the site?");

		Environment env = filter.getEnvironment(isSecure, loggedInUser);

		System.out.println(env);
	}

	@SuppressWarnings("resource")
	public static boolean ask(String question)
	{
		System.out.println(question + " (true/false)");

		return new Scanner(System.in).nextBoolean();
	}

	/*
Test1
{secureHome=https://www.theladders.com/, home=http://www.theladders.com/, memberSiteHome=http://www.theladders.com/member/, secureMemberSiteHome=https://www.theladders.com/member/, falconSiteHome=http://www.theladders.com/, guestSiteHome=http://www.theladders.com/, isSSL=true, secureFalconSiteHome=https://www.theladders.com/, secureGuestSiteHome=https://www.theladders.com/}
Test2
{secureHome=https://www.theladders.com/, home=http://www.theladders.com/, memberSiteHome=http://www.theladders.com/member/, secureMemberSiteHome=https://www.theladders.com/member/, falconSiteHome=http://www.theladders.com/, guestSiteHome=http://www.theladders.com/, isSSL=true, secureFalconSiteHome=https://www.theladders.com/, secureGuestSiteHome=https://www.theladders.com/}
Test3
{secureHome=https://www.theladders.com/member/, home=http://www.theladders.com/member/, memberSiteHome=http://www.theladders.com/member/, secureMemberSiteHome=https://www.theladders.com/member/, falconSiteHome=http://www.theladders.com/, guestSiteHome=http://www.theladders.com/, isSSL=true, secureFalconSiteHome=https://www.theladders.com/, secureGuestSiteHome=https://www.theladders.com/}
Test4
{secureHome=https://www.theladders.com/member/, home=http://www.theladders.com/member/, memberSiteHome=http://www.theladders.com/member/, secureMemberSiteHome=https://www.theladders.com/member/, falconSiteHome=http://www.theladders.com/, guestSiteHome=http://www.theladders.com/, isSSL=true, secureFalconSiteHome=https://www.theladders.com/, secureGuestSiteHome=https://www.theladders.com/}

	 */

	@Test
	public void test1() throws Exception 
	{
		System.out.println("Test1");
		EnvSetupFilter filter = new EnvSetupFilter(hostName);

		boolean isSecure = false;
		boolean loggedInUser = false;

		Environment env = filter.getEnvironment(isSecure, loggedInUser);
		System.out.println(env.getEnvMap());
		
		assertEquals("https://www.theladders.com/", env.getEnvDataForKey("secureHome"));
	}

	@Test
	public void test2() throws Exception 
	{
		System.out.println("Test2");
		EnvSetupFilter filter = new EnvSetupFilter(hostName);

		boolean isSecure = true;
		boolean loggedInUser = false;

		Environment env = filter.getEnvironment(isSecure, loggedInUser);
		System.out.println(env.getEnvMap());
		
		assertEquals("https://www.theladders.com/", env.getEnvDataForKey("secureHome"));
	}
	
	@Test
	public void test3() throws Exception 
	{
		System.out.println("Test3");
		EnvSetupFilter filter = new EnvSetupFilter(hostName);

		boolean isSecure = false;
		boolean loggedInUser = true;

		Environment env = filter.getEnvironment(isSecure, loggedInUser);
		System.out.println(env.getEnvMap());
		
		assertEquals("https://www.theladders.com/member/", env.getEnvDataForKey("secureHome"));
	}
	
	@Test
	public void test4() throws Exception 
	{
		System.out.println("Test4");
		EnvSetupFilter filter = new EnvSetupFilter(hostName);

		boolean isSecure = true;
		boolean loggedInUser = true;

		Environment env = filter.getEnvironment(isSecure, loggedInUser);
		System.out.println(env.getEnvMap());
		
		assertEquals("https://www.theladders.com/member/", env.getEnvDataForKey("secureHome"));
	}
	
	
	@Test
	public void test5() throws Exception 
	{
		System.out.println("Test5");
		EnvSetupFilter filter = new EnvSetupFilter(hostName);

		boolean isSecure = false;
		boolean loggedInUser = false;

		Environment env = filter.getEnvironment(isSecure, loggedInUser);
		System.out.println(env.getEnvMap());
		
		assertEquals("http://www.theladders.com/", env.getEnvDataForKey("home"));
	}

	@Test
	public void test6() throws Exception 
	{
		System.out.println("Test6");
		EnvSetupFilter filter = new EnvSetupFilter(hostName);

		boolean isSecure = true;
		boolean loggedInUser = false;

		Environment env = filter.getEnvironment(isSecure, loggedInUser);
		System.out.println(env.getEnvMap());
		
		assertEquals("http://www.theladders.com/", env.getEnvDataForKey("home"));
	}
	
	@Test
	public void test7() throws Exception 
	{
		System.out.println("Test7");
		EnvSetupFilter filter = new EnvSetupFilter(hostName);

		boolean isSecure = false;
		boolean loggedInUser = true;

		Environment env = filter.getEnvironment(isSecure, loggedInUser);
		System.out.println(env.getEnvMap());
		
		assertEquals("http://www.theladders.com/member/", env.getEnvDataForKey("home"));
	}
	
	@Test
	public void test8() throws Exception 
	{
		System.out.println("Test8");
		EnvSetupFilter filter = new EnvSetupFilter(hostName);

		boolean isSecure = true;
		boolean loggedInUser = true;

		Environment env = filter.getEnvironment(isSecure, loggedInUser);
		System.out.println(env.getEnvMap());
	
		assertEquals("http://www.theladders.com/member/", env.getEnvDataForKey("home"));
	}

}



