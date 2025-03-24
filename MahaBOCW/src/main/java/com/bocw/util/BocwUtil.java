package com.bocw.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BocwUtil {
	static WebDriver driver;
	static JavascriptExecutor js = (JavascriptExecutor) driver;
	static WebDriverWait wait;
	
	public BocwUtil(WebDriver driver) {
		this.driver=driver;
		js = (JavascriptExecutor) driver;
		
	}
	
	//Method to get the Page Title
	public String getTitle() {
		return driver.getTitle();
	}
	
	//Method to get the current URL of the Page
	public String getCurrentPageURL() {
		return driver.getCurrentUrl();
	}
	
	//Method to click on element using JavaScript
	public static void doClickByJs(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}
	
	//Method to scroll down
	public static void scrollDown(WebDriver driver)	{	
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
			
	// Method to scroll down until specific element
	public static void scrollToElements(List<WebElement> elements, WebDriver driver) {
		// Check if the list is not empty
	    if (!elements.isEmpty()) {
	        WebElement firstElement = elements.get(0); // Get the first element
	        js.executeScript("arguments[0].scrollIntoView(true);", firstElement);
	    }
	}
		
	// Method to scroll down until specific element
	public void scrollToElement(WebElement elements, WebDriver driver) {
	    
	        js.executeScript("arguments[0].scrollIntoView(true);", elements);
	    
	}
	
	//Select option from Mat Drop-down
	public void selectMatDropDown(By Locator, String optionText) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Click on the dropdown to open it
        WebElement dropdown = driver.findElement(Locator);
        dropdown.click();

        //Wait for the dropdown options to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']")));

        //Find the option and click on it based on its text
        String optionXpath = "//mat-option//span[contains(text(),'" + optionText + "')]";
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));
        option.click();

        //System.out.println("Selected option: " + optionText);
		
	}
	
	/**
     * change color
     * @param color
     * @param element
     */
    public static void changeColor(String color,WebElement element)
    {
    	
 	   js = (JavascriptExecutor)driver;
 	   js.executeScript("arguments[0].style.backgroundColor = '"+color+"'", element);
 	   try {
 		   Thread.sleep(2000);
 	   	} catch (InterruptedException e) {
	 		// TODO Auto-generated catch block
	 		e.printStackTrace();
 	   }
    }
    
    
    /**
     * flash 
     * @param driver
     */
	public static void flash(WebElement element)
    {
 	   js = (JavascriptExecutor)driver;
 	   String bgColor = element.getCssValue("backgroundColor");
 	   for(int i=0;i<1;i++)
 	   {
 		  changeColor("rgb(0,200,0)", element);
 		  changeColor(bgColor, element);
 	   }
    }

	/**
     * Method for to scroll up page
     */
    public void scrollPageUp()
    {
    	js =(JavascriptExecutor)driver;
    	js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }
	
	/**
	 * this method for get locator
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator)
	{
		WebElement element=driver.findElement(locator);
//		if(highlight.equals("true"))
//		{
//			BocwUtil.flash(element);
//		}
		return element;
	}
	
	/**
	 * this method for sendkeys
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value)
	{
		getElement(locator).sendKeys(value);
	}
	
	/**
	 * this method is for click on element
	 */
	public void doClick(By locator)
	{
		getElement(locator).click();
	}
	
	//This method use for the get the dropdown list 
	public List<String> getOptionsFromDropDown(By dropDownlocator, By optionlocators, String value) throws Exception
	{
		Thread.sleep(1000);
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropDownlocator));  // Locate the dropdown element
		List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElements(dropdown.findElements(optionlocators))); // Get all options
		List<String> optionsList = new ArrayList<String>();
		
		for(WebElement option: options)
		{
			String text= option.getText();
		//	System.out.println(text);
			optionsList.add(text);
			if (option.getText().equals(value)) { // Match the desired value
                option.click(); // Click on the matched option
                break;
            }
		}
		return optionsList;			
	}	
	
	//Family Details: This method is used to enter the Date
	public void selectDateByJS(WebElement element, String dateValue) throws Exception 
	{
		Thread.sleep(2000);
		js = (JavascriptExecutor) driver; //Casting driver into java script
		js.executeScript("arguments[0].setAttribute('value','"+ dateValue +"');",element);
	}
	
	
	//Family Details: This method verify if the Dialoge box is displayed to enter family Details 
	public boolean isDialogBoxDisplayed(WebDriver driver, By locator) {
	    try {
	        // Check if the element is displayed
	        WebElement dialogBox = driver.findElement(locator);
	        return dialogBox.isDisplayed();
	    } catch (NoSuchElementException e) {
	        // Element is not found, dialog box is not displayed
	        return false;
	    } catch (StaleElementReferenceException e) {
	        // In case the element is no longer attached to the DOM
	        return false;
	    }
	}
	
	// Method to handle the Nominee checkbox
	public void handleNomineeCheckbox(WebDriver driver, By locator) {
	    try {
	        // Locate the Nominee checkbox
	        By nomineeCheckbox = locator;
	        
	        // Check if the Nominee checkbox is displayed
	        if (driver.findElements(nomineeCheckbox).size() > 0) {
	            WebElement checkbox = driver.findElement(nomineeCheckbox);
	            
	            // If the checkbox is visible and not already selected, click it
	            if (checkbox.isDisplayed() && !checkbox.isSelected()) {
	                checkbox.click();
	                System.out.println("Nominee checkbox selected.");
	            }
	        } else {
	           // System.out.println("Nominee checkbox is not displayed for this member.");
	        }
	        
	    } catch (Exception e) {
	        System.out.println("Error handling the Nominee checkbox: " + e.getMessage());
	    }
	}

	
	
}
