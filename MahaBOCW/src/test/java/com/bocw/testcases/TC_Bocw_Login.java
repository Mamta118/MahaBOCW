package com.bocw.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.bocw.base.BocwBase;
import com.bocw.newRegistration.BocwLoginPage;

public class TC_Bocw_Login extends BocwBase {
	
	BocwLoginPage bocwLoginBase;
	
	/*
	 * Constructor is created to initialize the super class which is our TestBase
	 * class super keyword is used to initialize the parent class
	 */
	public TC_Bocw_Login() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		BrowserInitializationBocw();
		bocwLoginBase = new BocwLoginPage(); // LoginPage class object
	}
	
	@Ignore
	@Test(priority=1)
	public void VerifyPageTitleTest() {
		String loginPageTitle = bocwLoginBase.VerifyPageTitle();
		System.out.println("Title of the page is " +loginPageTitle);
		Assert.assertEquals(loginPageTitle, "Maharashtra BOCW", "Title of the Page is incorrect");
	}
	
	@Test(priority=2)
	public void LoginToSite() throws Exception {
		Thread.sleep(5000);
		bocwLoginBase.login(prop.getProperty("phoneNumber"),prop.getProperty("password"));
	}

}
