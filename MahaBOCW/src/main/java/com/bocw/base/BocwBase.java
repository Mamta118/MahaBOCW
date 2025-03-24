package com.bocw.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BocwBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions action;
	
	/*
	 * TestBase is the constructor to read the config.properties file
	 */	
	public BocwBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\mmahajan\\EclipseWorkspace\\MahaBOCW\\src\\main\\java\\com\\bocw\\config\\config.properties.bocw");
			prop.load(ip);		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	/*
	 * 	This method initializes the browser
	 */
	public static void BrowserInitializationBocw() {
		String BrowserName = prop.getProperty("browser"); //reading the 'browser' property from the config.property file
		//System.out.println("Browser is " + "chrome");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
			
		/*
		 *	Rather than giving hard-coded time, we are taking value from com.util package
		 */
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); //Press ctrl and click on Util Class name, it will redirect to Util class  
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("devUrl"));
		
		
	}
}

