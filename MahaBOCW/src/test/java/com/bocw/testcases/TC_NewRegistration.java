package com.bocw.testcases;

import java.io.ObjectInputFilter.Config;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.bocw.base.BocwBase;
import com.bocw.newRegistration.BocwLoginPage;
import com.bocw.newRegistration.Dashboard;
import com.bocw.newRegistration.NewRegistration;
import com.bocw.util.BocwUtil;
import com.bocw.util.Constants;
import com.bocw.util.ExcelUtility;
import com.sun.org.apache.bcel.internal.classfile.Constant;

public class TC_NewRegistration extends BocwBase {
	
	NewRegistration newRegistration;
	BocwLoginPage bocwLogin;
	Dashboard dashboard;
	/*
	 * Constructor is created to initialize the super class which is our BocwBase
	 * class super keyword is used to initialize the parent class
	 */
	
	public TC_NewRegistration()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp() throws Exception {
		BrowserInitializationBocw();
		bocwLogin = new BocwLoginPage(); // BocwLoginPage class object
		newRegistration = new NewRegistration(); //NewRegistration class object	
		dashboard = new Dashboard();  // Dashboard class object
		bocwLogin.login(prop.getProperty("phoneNumber"), prop.getProperty("password"));		
		
	}
	
	@Ignore
	@Test
	public void validateWarningMessageForExistingMobileNumber_Test() throws InterruptedException {
		System.out.println(Constants.BLUE +"Verify that the validation message is displayed when the user enters an existing Mobile number."+ Constants.RESET);

		newRegistration.clickOnNewRegistrationButton("Kalyan","787867564534", "7507849328" ); //Entering Existing Mobile Number
		newRegistration.clickOnNextButton(1);
		
		String expectedWarningMessage = newRegistration.validationWarningMessage();
		
		if(expectedWarningMessage.contains("Phone number")) {
			System.out.println("Warning Message Validation Passed");
		} else {
			System.out.println("Warning Message Validation Failed");
		}
	
	}
	
	@Ignore
	@Test
	public void validateWarningMessageForExistingAadharNumber_Test() throws InterruptedException {
		
		System.out.println(Constants.BLUE +"Verify that the validation message is displayed when the user enters an existing Aadhaar number."+ Constants.RESET);
		
		newRegistration.clickOnNewRegistrationButton("Kalyan","676767676767", "7507949328" ); //Entering Existing Aadhaar Number
		newRegistration.clickOnNextButton(1);
		
		String expectedWarningMessage = newRegistration.validationWarningMessage();
		
		if(expectedWarningMessage.contains("Aadhaar")) {
			System.out.println("Warning Message Validation Passed");
		} else {
			System.out.println("Warning Message Validation Failed");
		}
	
	}

	@Test(dataProvider = "getData", dataProviderClass = ExcelUtility.class)
	public void fillEmployeeDetail(String wfcLocation, String aadhaarNumber, String mobileNumber,
			String fName, String fathHusName, String lName , String genderValue,
			String maritalStatusValue, String educationValue, String categoryValue, String professionValue, 
			String pforUanValue ,String esicNumberValue, String emailValue,
			String talukaWorkplace) throws Exception {
		
		newRegistration.clickOnNewRegistrationButton(wfcLocation,aadhaarNumber, mobileNumber );
		newRegistration.clickOnNextButton(1); 
				
		//Expand Personal Details section
		newRegistration.clickOnExpansionPanel("Personal Details");
		
		//Fill Personal Details
		newRegistration.inputPersonalDetails(fName,fathHusName,lName , genderValue,
				maritalStatusValue, educationValue, categoryValue, professionValue, pforUanValue , esicNumberValue, emailValue);	
		
		//Expand Residential Details section
		newRegistration.clickOnExpansionPanel("Residential Address");
		Thread.sleep(2000);
		
		//Fill Details of Residential Address
		newRegistration.inputResidentialAddress("Owned","Pukka", "12/A", "Road no. 16", "Kalyan city", "Kalyan", "Shivaji Maharaj Statue", 
				"Akola", "Balapur", "Vidyutnagar Paras S.O", "02562", "244608");
				
		//Expand Residential Details section
		newRegistration.clickOnExpansionPanel("Permanent Address");
		
		//Decide whether the Permanent Address is the same
		boolean isAddressSame = true; //Set to true if both addresses are the same; otherwise keep false
		Thread.sleep(1000);
		
		//Check if the Permanent address is same as Residential address
		if(isAddressSame) {
			newRegistration.checkIfBothAddressAreSame();
		}
		else {
			
			//fill Permanent Address
			Thread.sleep(2000);
			newRegistration.inputPermanentAddress("Owned","Pukka", "3401/A", "Road no. 22", "Jalgaon", "Jalgaon", "ShivBharat Maharaj Statue","Maharashtra", 
					"Jalgaon City", "Shiv Colony", "425109", "Shiv Colony S.O", "02562", "244608");
		}
		
		//Add Family Details
		for(int i=0; i<4; i++) {
			newRegistration.clickOnAddMoreFamilyButton();
			//Generate a unique Aadhar number by appending 'i' to the base number
		    String uniqueAadharNumber = "9786798786" + i + i;
			newRegistration.inputFamilyDetails("Ashok", "Mali", "Ram", "Son", uniqueAadharNumber , "Majur" , "6th" , "Will Do");
		}
		
		newRegistration.clickOnNextButton(3); //Click on Next Button
		
		//Add Bank Details
		newRegistration.inputBankDetails(prop.getProperty("ifsc_code"));
		
		//Assert.assertEquals(newRegistration.getBankName(), "Thane District Central Co-operative Bank" , "Bank Name Mismatched" );
		
		newRegistration.clickOnNextButton(4); //Click on Next Button
		
		//Expand Present Employer Details section
		newRegistration.clickOnExpansionPanel("Present Employers Details");
		
		//Enter details of Present Employer Details
		newRegistration.inputPresentEmployerDetails("Amol Waghmare", "Sai Construction Pvt Ltd" , "Roads", "Kolshed khadi", "9897976681",
				talukaWorkplace, "Kalyan", "400606", "All type of Helpers", "87897");
		
		//Enters Details of 90 Days working certificate 
		newRegistration.clickOnExpansionPanel("Details of the 90 Days Working Certificate");
		
		//Selects the Issuer Type from the dropdown
		/*
		 * 1. Contractor/Developer
		 * 2. Gramsevak
		 * 3. Person with authorization by municipal corporation or municipal council
		 */
		String expectedTypeOfIssuer = "Contractor/Developer";
		if(newRegistration.selectTypeOfIssuer(expectedTypeOfIssuer).equalsIgnoreCase("Gramsevak")) {
			
			System.out.println("Selected Gramsevak Option from Type of Issuer dropdown ");
			newRegistration.inputGramsevakDetails("9897");
			
		} else if(newRegistration.selectTypeOfIssuer(expectedTypeOfIssuer).equalsIgnoreCase("Contractor/Developer")) {
			
			System.out.println("Selected Contractor/Developer option from Type of Issuer dropdown");
			newRegistration.inputContractorDeveloperDetails("MSRDC", "9898" , "356656");
		}
		
	
		//Expand the Record of 90 Days working certificate section
		newRegistration.clickOnExpansionPanel("Records of the 90 Days Working Certificate");
		//Fill the Ninety Working Days Details
		newRegistration.addRecordsOf90DaysWorkingCertificate("Contractor/Developer", "Aaba Mali", "9876645673");
		
		//Upload Applicant Photo
		newRegistration.uploadApplicatPhoto("C:\\Users\\mmahajan\\OneDrive\\Pictures\\TEST Images\\Female Profile Icon.jpg");
		
		//Upload Photo Id Proof
		newRegistration.uploadPhotoIdProof("Aadhaar card", "C:\\Users\\mmahajan\\OneDrive\\Pictures\\TEST Images\\dummy-aadhar-card.jpg");
		
		//Upload Age Proof
		newRegistration.uploadAgeProof("PAN Card", "C:\\Users\\mmahajan\\OneDrive\\Pictures\\TEST Images\\Dummy Pan Card.jpg");
		
		//Upload Photo copy of Bank Passbook
		newRegistration.uploadPassbook("Cancelled Cheque", "C:\\Users\\mmahajan\\OneDrive\\Pictures\\TEST Images\\Dummy Cancelled Cheque.jpeg");
		
		//Upload Certificate of 90 Days
		newRegistration.uploadWorkingCertificate("C:\\Users\\mmahajan\\OneDrive\\Pictures\\TEST Images\\form Details-002.pdf");
		
		//Upload Aadhaar Consent
		newRegistration.uploadAadhaarConsent("C:\\Users\\mmahajan\\OneDrive\\Pictures\\TEST Images\\Sample Form-BOCW.pdf");
		
		//Upload Self Declaration Letter
		newRegistration.uploadSelfDeclaration("C:\\Users\\mmahajan\\OneDrive\\Pictures\\TEST Images\\SampleJPGImage_20mbmb.jpg");
		
		//Click on Declaration Check box
		newRegistration.clickOnCheckbox();
		
		//Click on Save button
		newRegistration.clickOnSaveButton();
		Thread.sleep(2000);
		String dashboardPageURL = dashboard.getCurrentPageURL();
		Assert.assertEquals(dashboardPageURL, "https://mahabocw-dev.smartsight.in/bocw/form-entries", "Form Does not Saved due to some error");
				
	}	
		

}
