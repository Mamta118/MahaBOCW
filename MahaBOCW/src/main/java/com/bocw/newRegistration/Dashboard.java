package com.bocw.newRegistration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.bocw.base.BocwBase;
import com.bocw.util.BocwUtil;

public class Dashboard extends BocwBase{
	
	//WebDriver driver;
	BocwUtil bocwUtil;
	
	public Dashboard() {
		PageFactory.initElements(driver, this);
		bocwUtil = new BocwUtil(driver);
	}
	
	
	public String getPageTitle() {
		return bocwUtil.getTitle();
		
	}
	
	public String getCurrentPageURL() {
		return bocwUtil.getCurrentPageURL();
	}

}
