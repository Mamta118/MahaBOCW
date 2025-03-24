package com.bocw.newRegistration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bocw.base.BocwBase;

public class BocwLoginPage extends BocwBase{
	
	@FindBy(xpath="//div[@class='mat-mdc-form-field-infix ng-tns-c8-0']//input[@placeholder='Please Enter Phone Number']")
	WebElement phoneNumber;
	
	@FindBy(xpath="//div[@class='mat-mdc-form-field-infix ng-tns-c8-1']//input[@placeholder='Please Enter Password']")
	WebElement password;
	
	@FindBy(xpath="//div[@class='full-width button-flex']")
	WebElement submit;
	

	public BocwLoginPage(){
		PageFactory.initElements(driver, this);
		
	}
	
	public String VerifyPageTitle() {
		return driver.getTitle();  //Title of the page will be in string hence return type should be String
	}
	
	public void login(String pNumber, String pwd) throws Exception {
		
		phoneNumber.sendKeys(pNumber);
		password.sendKeys(pwd);
		try {
		submit.click();
		}
		catch(Exception e){
			submit.click();
		}
		System.out.println("Sucessfully Signed in to Site");
		Thread.sleep(2000);
			
	}
	
}