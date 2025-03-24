package com.bocw.newRegistration;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bocw.base.BocwBase;
import com.bocw.util.BocwUtil;
import com.bocw.util.Constants;

public class NewRegistration extends BocwBase {
	
	BocwUtil bocwUtil;
	Dashboard dashboard;

	//By Locators
	By newRegiButton = By.xpath("//button[@class='mdc-button mdc-button--raised mat-mdc-raised-button mat-primary mat-mdc-button-base']");
	By wfcLocation = By.xpath("//input[@placeholder='Select Nearest WFC Location']");
	By enterAadhaar = By.xpath("//input[@placeholder='Enter Aadhaar Number']");	
	By enterMobile = By.xpath("//input[@placeholder='Enter Currently used Mobile Number']");
	By nextButton = By.xpath("//div[@class='full-width button-position-end padding-top-2 ng-star-inserted']//button[@type='submit']");
	By warningSnackBarXpath = By.xpath("//div[@class='cdk-overlay-pane']//mat-snack-bar-container");
	By warningMessgeXpath = By.xpath("//div[@class='snackbar__message--message']");
	
	//Personal Details
	By firstName = By.xpath("//mat-form-field//input[@formcontrolname='firstName']");
	By husbandOrFatherName = By.xpath("//mat-form-field//input[@formcontrolname='fatherOrHusbandName']");
	By lastName = By.xpath("//mat-form-field//input[@formcontrolname='lastName']");
	By gender = By.xpath("//mat-select[@formcontrolname='gender']");
	By dropdown = By.xpath("//div[@role='listbox']");
	By dropdownOption = By.xpath("//div[@role='listbox']//mat-option");
	By maritalStatus = By.xpath("//mat-select[@formcontrolname='maritalStatus']");
	By education = By.xpath("//mat-select[@formcontrolname='education']");
	By category = By.xpath("//mat-select[@formcontrolname='category']");
	By profession = By.xpath("//input[@formcontrolname='profession']");
	By pforUanNumber = By.xpath("//input[@formcontrolname='pfOrUan']");
	By esicNumber = By.xpath("//input[@formcontrolname='esic_no']");
	By emailofEmp = By.xpath("//input[@formcontrolname='email']");
	By dob = By.xpath("//mat-label[contains(text(),'Date Of Birth')]");
	
	//Residential Address
	By residenceType = By.xpath("//mat-select[@formcontrolname='residenceType']");
	By houseType = By.xpath("//mat-select[@formcontrolname='houseType']");
	By houseNo = By.xpath("//input[@formcontrolname='houseOrBuildingNumber']");
	By road = By.xpath("//input[@formcontrolname='road']");
	By areaOrVillage = By.xpath("//input[@formcontrolname='areaOrVillage']");
	By city = By.xpath("//input[@formcontrolname='city']");
	By impPlace= By.xpath("//input[@formcontrolname='importantPlaceNearby']");
	By district = By.xpath("//input[@formcontrolname='districtName']");
	By taluka = By.xpath("//mat-select[@formcontrolname='talukaName']");
	By postOffice = By.xpath("//input[@formcontrolname='postOffice']");
	By pinCode = By.xpath("//input[@formcontrolname='pincode']");
	By stdCode = By.xpath("//input[@formcontrolname='stdCode']");
	By landline = By.xpath("//input[@formcontrolname='landlineNumber']");
	
	//Permanent Address
	By chechBoxPermAddress = By.xpath("//input[@id='prefillAddress']");
	By residenceType_Perm = By.xpath("(//mat-select[@formcontrolname='residenceType'])[2]");
	By houseType_Perm = By.xpath("(//mat-select[@formcontrolname='houseType'])[2]");
	By houseNo_Perm = By.xpath("(//input[@formcontrolname='houseOrBuildingNumber'])[2]");
	By road_Perm = By.xpath("(//input[@formcontrolname='road'])[2]");
	By areaOrVillage_Perm = By.xpath("(//input[@formcontrolname='areaOrVillage'])[2]");
	By city_Perm = By.xpath("(//input[@formcontrolname='city'])[2]");
	By impPlace_Perm = By.xpath("(//input[@formcontrolname='importantPlaceNearby'])[2]");
	By state_Perm = By.xpath("(//mat-select[@formcontrolname='state'])[2]");
	By district_Perm = By.xpath("(//input[@formcontrolname='districtName'])[2]");
	By taluka_Perm = By.xpath("//input[@formcontrolname='talukaName']");
	By postOffice_Perm = By.xpath("(//input[@formcontrolname='postOffice'])[2]");
	By pinCode_Perm = By.xpath("(//input[@formcontrolname='pincode'])[2]");
	By stdCode_Perm = By.xpath("(//input[@formcontrolname='stdCode'])[2]");
	By landline_Perm = By.xpath("(//input[@formcontrolname='landlineNumber'])[2]");
	
	//Add Family Details
	By addMoreFamily = By.xpath("(//button[@class='mdc-button mdc-button--raised mat-mdc-raised-button mat-primary mat-mdc-button-base'])[1]");
	By familyDetailsDialog = By.xpath("//mat-dialog-container[@role='dialog']");
	By firstName_fam = By.xpath("//app-family-details-dialog//input[@formcontrolname='firstName']");
	By surName_fam = By.xpath("//app-family-details-dialog//input[@formcontrolname='surname']");
	By husbandOrFatherName_fam = By.xpath("//app-family-details-dialog//input[@formcontrolname='fatherOrHusbandName']");
	By dob_fam = By.xpath("//app-family-details-dialog//input[@formcontrolname='dob']");
	By relation_fam = By.xpath("//app-family-details-dialog//mat-select[@formcontrolname='relation']");
	By aadhaar_fam = By.xpath("//app-family-details-dialog//input[@formcontrolname='aadhar_no']");
	By profession_fam = By.xpath("//app-family-details-dialog//input[@formcontrolname='profession']");
	By education_fam = By.xpath("//app-family-details-dialog//mat-select[@formcontrolname='education']");
	By mscit_fam = By.xpath("//app-family-details-dialog//mat-select[@formcontrolname='mscit']");
	By nominee_fam = By.xpath("//input[@formcontrolname='nominee']");
	By save_fam = By.xpath("//app-family-details-dialog//span[@class='mdc-button__label' and contains(text(), 'Save')]");
	
	//Add Bank Details
	By ifsc_code = By.xpath("//input[@formcontrolname='ifsc_code']");
	By search_ifsc = By.xpath("//button//span[contains(text(), 'Search')]");
	By bankName_Field = By.xpath("//input[@formcontrolname='bankName']");
	
	//Present Employer Details
	By contractorName_PresentEmpField = By.xpath("//input[@formcontrolname='contractorName']");
	By contractorCompanyName_PresentEmpField = By.xpath("//input[@formcontrolname='contractorCompanyName']");
	By typeOfWork_PresentEmpField = By.xpath("//mat-select[@formcontrolname='typeOfWork']");
	By workPlaceAddress_PresentEmpField = By.xpath("//input[@formcontrolname='workPlaceOrSiteAddress']");
	By mobileNumber_PresentEmpField = By.xpath("//input[@formcontrolname='mobileOrLandlineNumber']"); //Mobile Number
	By taluka_PresentEmpField = By.xpath("//mat-select[@formcontrolname='talukaOfWorkPlace']"); //Taluka of Workplace
	By city_PresentEmpField = By.xpath("//input[@formcontrolname='townOrCity']");
	By pincode_PresentEmpField = By.xpath("(//input[@formcontrolname='pincode'])[3]"); //Pin code
	By dateOfJoining_PresentEmpField = By.xpath("//input[@formcontrolname='joiningDate']"); //Date of joining
	By natureOfWork_PresentEmpField = By.xpath("//mat-select[@formcontrolname='natureOfWork']"); //Nature Of Worker
	By mnreg_PresentEmpField = By.xpath("//input[@formcontrolname='mnrega_registration_no']");	//MNREG Number
	
	//Details of 90 Days working Certificate
	By typeOfIssuer_Field = By.xpath("//mat-select[@formcontrolname='typeOfIssuer']");
	   	
	//Contractor/Developer
	By registeredWith_Field_Contractor = By.xpath("//mat-select[@placeholder='Select Type Of Registration Of Employer']");
	By regNumberOfIssuer_Field_Contractor = By.xpath("//input[@placeholder='Enter Registration No. Of Issuer']");
	By regCertificateIssueDate_Field_Contractor = By.xpath("//input[@placeholder='Select Registration Certificate Issue Date']");
	By dispatchNumber_Field_Contractor = By.xpath("//input[@formcontrolname='dispatchNumber']");
		
	//Gramsevak
	By dispatchDate_Field_Gramsevak = By.xpath("//input[@placeholder='Select Dispatch Date']");
	By dispatchNumber_Field_Gramsevak = By.xpath("//input[@formcontrolname='dispatchNumber']");
		
	//Person with authorization by municipal corporation or municipal council
	By dispatchDate_Field_Municipal = By.xpath("//input[@formcontrolname='registrationCertificateIssueDate']");
	By dispatchNumber_Field_Municipal = By.xpath("//input[@formcontrolname='dispatchNumber']");
	
	//Add Record of 90 Day's Working Cerificate
	By workRecordPanel = By.xpath("//mat-panel-title[contains(text(), 'Records of the 90 Days Working Certificate')]");
	By addWorkRecords_button = By.xpath("(//mat-icon[contains(text(), 'add')])[2]");
	
	By typeOfEmployer_EmployerField = By.xpath("//mat-select[@formcontrolname='typeOfEmployer']");
	By employerName_EmployerField = By.xpath("//input[@formcontrolname='fullName']");
	By mobileNumber_EmployerField = By.xpath("//input[@formcontrolname='mobileNumber']");
	By fromDate = By.xpath("//input[@formcontrolname='fromDate']");
	By toDate = By.xpath("//input[@formcontrolname='toDate']");
	
	By save_EmployerDetails = By.xpath("//button[@class='mdc-button mdc-button--raised mat-mdc-raised-button mat-primary mat-mdc-button-base']//span[@class='mdc-button__label' and contains(text(), ' Save ')]");
	By totalWorkingDay = By.xpath("//*[@id=\"cdk-accordion-child-13\"]/div/div[2]/div[1]");

	//Applicant Photo
	By applicatePhoto = By.xpath("//input[@id='photo']");
	
	//Photo Id Proof
	By proofId_Dropdown = By.xpath("(//mat-select[@role='combobox'])[19]");
	By photoId = By.xpath("//input[@id='photoIdProof']"); 
	
	//Proof of Age
	By proofAge_Dropdown = By.xpath("(//mat-select[@role='combobox'])[20]");
	By ageProof = By.xpath("//input[@id='ageProof']");
	
	//Copy of Bank Passbook
	By bankPassbook_Dropdown = By.xpath("(//mat-select[@role='combobox'])[21]");
	By bankPassbook = By.xpath("//input[@id='passBook']");
	
	//Working Certificate
	By workingCertificate = By.xpath("//input[@id='workCertificate']");
	
	//Aadhaar Consent
	By aadhaarConsent= By.xpath("//input[@id='aadharConsent']"); 
	
	//selfDeclaration
	By selfDeclaration = By.xpath("//input[@id='selfDeclaration']");
	
	//CheckBox
	By checkBox = By.xpath("(//input[@type='checkbox'])[2]");
	
	//Save Button
	By saveButton = By.xpath("//span[@class='mdc-button__label' and text( )=' Save ']");
	
	public NewRegistration() {
		PageFactory.initElements(driver, this);
		bocwUtil = new BocwUtil(driver);
	}
	
	
	//Click on New Registration Button
	public void clickOnNewRegistrationButton(String wfc, String aadhaarNumber, String mobileNumber) throws InterruptedException {
		
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement newRegistrationButton = driver.findElement(newRegiButton);
			newRegistrationButton.click();
			//System.out.println(Constants.GREEN + Constants.ANSI_BOLD + "Clicked on New Registration Button" +Constants.RESET);
			Thread.sleep(2000);
			WebElement wfcLocationWE = driver.findElement(wfcLocation);
			
		//Scroll to WFC location dropdown
			bocwUtil.scrollToElement(wfcLocationWE, driver);
		 
		//Select From city from Dropdown 
			bocwUtil.selectMatDropDown(wfcLocation, wfc);
		
		//Enter Aadhaar Number
			WebElement aadharNumberWE = driver.findElement(enterAadhaar);
			wait.until(ExpectedConditions.elementToBeClickable(enterAadhaar));
			aadharNumberWE.sendKeys(aadhaarNumber);
			
		//Enter Mobile Number
			WebElement mobileNumberWE = driver.findElement(enterMobile);
			wait.until(ExpectedConditions.elementToBeClickable(mobileNumberWE)).sendKeys(mobileNumber);
			
	}
	
	public boolean isErrorForExistingAadhaarDisplayed() {
        try {
        	WebElement snackBar = driver.findElement(warningSnackBarXpath);
			wait.until(ExpectedConditions.visibilityOf(snackBar));
			//WebElement warningMessageWE = driver.findElement(warningMessgeXpath);
            return snackBar.isDisplayed();
        } catch (Exception e) {
            return false; // No error message displayed
        }
    }
	
	//Click on Next Button
	public void clickOnNextButton(int i) throws InterruptedException {
			
			WebElement nextButtonClick = driver.findElement(By.xpath("(//button[@class='mat-stepper-next mdc-button mdc-button--raised mat-mdc-raised-button mat-primary mat-mdc-button-base' and @type='submit'])"+"["+i+"]"));
			bocwUtil.scrollToElement(nextButtonClick, driver);
			nextButtonClick.click();
			//bocwUtil.doClick(nextButton); 	
	}

	//Click on Expansion Arrow
	public void clickOnExpansionPanel(String expansionTitle) {
		try {
		   WebElement expansionPanel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-expansion-panel-header//mat-panel-title[contains(text(),'"+expansionTitle+"')]")));
		   expansionPanel.click();
		} catch(Exception e) {
		   WebElement expansionPanel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-expansion-panel-header//mat-panel-title//span[contains(text(),'"+expansionTitle+"')]")));
		   expansionPanel.click();
		}
	}
	
	public boolean isValidationDisplayed() {
		WebElement snackBar = driver.findElement(warningSnackBarXpath);
		wait.until(ExpectedConditions.visibilityOf(snackBar));
		return true;
	}
				
	//Validate toast Message for Existing Mobile Number
	public String validationWarningMessage() {
		String warningMessage =null;

		//Validate if toast message is displayed when user enter existing mobile number
		try {
			WebElement snackBar = driver.findElement(warningSnackBarXpath);
			wait.until(ExpectedConditions.visibilityOf(snackBar));
			WebElement warningMessageWE = driver.findElement(warningMessgeXpath);	
			
			if(snackBar.isDisplayed()) {
				System.out.println("Warning Message is displayed");	
			}
			else {
				System.out.println("Warning Message is not displayed");
			}
			
			warningMessage = snackBar.getText();
			
			// Splitting by newline and extracting the second line
	        String[] lines = warningMessage.split("\n");
	        if (lines.length > 1) {
	             // Prints: This Aadhaar is already linked to another worker.
			System.out.println("Displayed Warning Message is: " +lines[1]);	
	        }
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return warningMessage;
	}	
			
	//Fill Personal Details
	public void inputPersonalDetails(String fName, String fathHusName, String lName , String genderValue,
			String maritalStatusValue, String educationValue, String categoryValue, String professionValue, String pforUanValue ,String esicNumberValue, String emailValue) throws Exception {
		
		bocwUtil.doSendKeys(firstName, fName);
		bocwUtil.doSendKeys(husbandOrFatherName, fathHusName); 
		bocwUtil.doSendKeys(lastName, lName);	
		bocwUtil.doClick(gender);
		bocwUtil.getOptionsFromDropDown(dropdown,dropdownOption,genderValue);
		bocwUtil.doClick(maritalStatus);
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, maritalStatusValue);
		bocwUtil.doClick(education);
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, educationValue);
		bocwUtil.doClick(category);
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, categoryValue);
		bocwUtil.doSendKeys(profession, professionValue);
		bocwUtil.doSendKeys(pforUanNumber, pforUanValue);
		bocwUtil.doSendKeys(esicNumber, esicNumberValue);
		bocwUtil.doSendKeys(emailofEmp, emailValue);
		Thread.sleep(500);
		selectBDate("5","JAN","1997", dob); //Select Birth Date of Employee
		
	}
	
	//Fill the Residential Details
	public void inputResidentialAddress(String residenceTypeValue, String houseTypeValue, String houseNoValue,
			String roadValue, String areaValue, String cityValue,
			String impPlaceValue, String districtValue, String talukaValue,
			String postOfficeValue, String stdCodeValue, String landlineValue) throws Exception {
		
		bocwUtil.doClick(residenceType);
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, residenceTypeValue);
		bocwUtil.doClick(houseType);
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, houseTypeValue);
		bocwUtil.doSendKeys(houseNo, houseNoValue);
		bocwUtil.doSendKeys(road, roadValue);
		bocwUtil.doSendKeys(areaOrVillage, areaValue);
		bocwUtil.doSendKeys(city, cityValue);
		bocwUtil.doSendKeys(impPlace, impPlaceValue);
		bocwUtil.doClick(district);
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, districtValue);
		bocwUtil.doClick(taluka);
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, talukaValue);
		bocwUtil.doClick(postOffice);
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, postOfficeValue);
//		bocwUtil.doClick(pinCode);
//		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, pinCodeValue);
		bocwUtil.doSendKeys(stdCode,stdCodeValue);	
		bocwUtil.doSendKeys(landline,landlineValue);	
		
	}
	
	//This method select the checkbox if Resodential address is same as permanent address
	public void checkIfBothAddressAreSame() {
		
		wait.until(ExpectedConditions.elementToBeClickable(chechBoxPermAddress));
		bocwUtil.doClick(chechBoxPermAddress);	
		WebElement nextButton = driver.findElement(By.xpath("(//span[@class='mat-mdc-button-persistent-ripple mdc-button__ripple']//following::span[@class='mdc-button__label' and contains(text(), ' Next ')])[2]"));
		bocwUtil.scrollToElement(nextButton, driver);
		nextButton.click();
	}
	
	//Fill the Permanent Address Details
	public void inputPermanentAddress (String residenceTypeValue, String houseTypeValue, String houseNoValue,
			String roadValue, String areaValue, String cityValue,
			String impPlaceValue,String stateValue, String districtValue, 
			String talukaValue, String postOfficeValue, String pinCodeValue, 
			String stdCodeValue, String landlineValue) throws Exception {
		
		bocwUtil.doClick(residenceType_Perm);
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, residenceTypeValue);
		bocwUtil.doClick(houseType_Perm);
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, houseTypeValue);
		bocwUtil.doSendKeys(houseNo_Perm, houseNoValue);
		bocwUtil.doSendKeys(road_Perm, roadValue);
		bocwUtil.doSendKeys(areaOrVillage_Perm, areaValue);
		bocwUtil.doSendKeys(city_Perm, cityValue);
		bocwUtil.doSendKeys(impPlace_Perm, impPlaceValue);
		bocwUtil.doClick(state_Perm);
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, stateValue);
		bocwUtil.doSendKeys(district_Perm, districtValue);
		bocwUtil.doSendKeys(taluka_Perm, talukaValue);
		bocwUtil.doSendKeys(postOffice_Perm ,postOfficeValue);
		bocwUtil.doSendKeys(pinCode_Perm, pinCodeValue);
		bocwUtil.doSendKeys(stdCode_Perm,stdCodeValue);	
		bocwUtil.doSendKeys(landline_Perm,landlineValue);
	}
	
	//This method is used to click on Add More button to add family details
	public void clickOnAddMoreFamilyButton () {
		bocwUtil.doClick(addMoreFamily);
		
	}
	
	//Add family Details
	public void inputFamilyDetails(String f_Name, String f_surName, String f_middleName, 
			String f_relation, String f_aadhaar,
			String f_profession, String f_education, 
			String f_mscit) throws Exception {
		
		//WebElement nextButton = driver.findElement(By.xpath("//div[@class='full-width button-position-end padding-top-2 ng-star-inserted']//button[@type='submit']")); 
		
		if (bocwUtil.isDialogBoxDisplayed(driver, familyDetailsDialog)) {
			//System.out.println("Dialog box is displayed.");
		    bocwUtil.doSendKeys(firstName_fam, f_Name);
		    bocwUtil.doSendKeys(surName_fam, f_surName);
		    bocwUtil.doSendKeys(husbandOrFatherName_fam, f_middleName);
		    Thread.sleep(500);
		    bocwUtil.scrollToElement(driver.findElement(dob_fam), driver);
		    selectBDate("12","FEB","1990", dob_fam);
		    bocwUtil.doClick(relation_fam);
			bocwUtil.getOptionsFromDropDown(dropdown,dropdownOption,f_relation);
			bocwUtil.doSendKeys(aadhaar_fam, f_aadhaar);
			bocwUtil.doSendKeys(profession_fam, f_profession);
			bocwUtil.doClick(education_fam);
			bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, f_education);
			bocwUtil.scrollToElement(driver.findElement(mscit_fam), driver);
				//wait.until(ExpectedConditions.visibilityOfElementLocated(mscit_fam));
				//bocwUtil.doClick(mscit_fam);
				//bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, f_mscit);
			
		    bocwUtil.handleNomineeCheckbox(driver, nominee_fam);
		    bocwUtil.doClick(save_fam);   
		    
		} else {
		    System.out.println("Dialog box is not displayed.");
		}
						
	}
	
	//Add Bank Details
	public void inputBankDetails(String ifscCode) {
		
		//Enter IFSC code
		bocwUtil.doSendKeys(ifsc_code, ifscCode); 
		wait.until(ExpectedConditions.elementToBeClickable(search_ifsc));
		//Click on Search Button
		bocwUtil.doClick(search_ifsc);
		
	}
		
	//Verify if the Bank Name and other details pre-filled when searched for IFSC code
	public String getBankName() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(bankName_Field));		
		System.out.println(driver.findElement(bankName_Field).getText());
		return driver.findElement(bankName_Field).getText();
	}
		
	//Add Present Employer Details	 
	public void inputPresentEmployerDetails(String contractor_Name, String contractor_companyName, String typeOfWorkOption,
			String contractor_workPlace, String contractor_Mobile, String contractor_Taluka,
			String contractor_City, String contractor_Pincode, 
			String contractor_WorkNature, String contractor_MNREG) throws Exception { 
		
		//Enter Contractor Name
		bocwUtil.doSendKeys(contractorName_PresentEmpField, contractor_Name);
		
		//Enter Contractor Company Name
		bocwUtil.doSendKeys(contractorCompanyName_PresentEmpField, contractor_companyName);
		
		//Click on "Type of Work" filed
		bocwUtil.doClick(typeOfWork_PresentEmpField);
		//Select the option from "Type of Work" drop down
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, typeOfWorkOption);
		
		//Enter Work Place or site address
		bocwUtil.doSendKeys(workPlaceAddress_PresentEmpField, contractor_workPlace);
		
		//Enter Mobile Number of Contractor 
		bocwUtil.doSendKeys(mobileNumber_PresentEmpField, contractor_Mobile);
		
		//Click on Taluka field
		bocwUtil.scrollToElement(driver.findElement(taluka_PresentEmpField), driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(taluka_PresentEmpField));
		WebElement taluka_FieldElement = driver.findElement(taluka_PresentEmpField);
		bocwUtil.doClickByJs(taluka_FieldElement);
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, contractor_Taluka);
		
		//Enter Town or city
		bocwUtil.doSendKeys(city_PresentEmpField, contractor_City);
		
		//Enter Pincode
		wait.until(ExpectedConditions.visibilityOfElementLocated(pincode_PresentEmpField));
		WebElement pincode_FieldElement = driver.findElement(pincode_PresentEmpField);
		bocwUtil.doSendKeys(pincode_PresentEmpField, contractor_Pincode);	
		
		//Select Nature of Work
		wait.until(ExpectedConditions.visibilityOfElementLocated(natureOfWork_PresentEmpField));
		WebElement workNature_FieldElement = driver.findElement(natureOfWork_PresentEmpField);
		bocwUtil.doClickByJs(workNature_FieldElement);
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, contractor_WorkNature);
		
		//Enter MNREG Number
		bocwUtil.doSendKeys(mnreg_PresentEmpField, contractor_MNREG);
		
		//Select Date of Joining
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(dateOfJoining_PresentEmpField));
		WebElement dateOfJoining_FieldElement = driver.findElement(dateOfJoining_PresentEmpField);
		bocwUtil.doClickByJs(dateOfJoining_FieldElement);
		selectBDate("1", "JUN", "2022", dateOfJoining_PresentEmpField);
	}
	
	//Add Details of 90 Days working certificate	
	public String selectTypeOfIssuer(String issuerType) throws Exception {
		
		Thread.sleep(2000);	 
		//Clicks on Type of Issuer dropdown
		bocwUtil.doClick(typeOfIssuer_Field);
		//Select the Issuer option from the drop-down
		Thread.sleep(2000);
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, issuerType);
		
		return issuerType;
		
	}
	
	//Enter Details when 'Gramsevak' option is choose from the drop-down
	public void inputGramsevakDetails(String dispatch_value ) throws Exception {
		
		WebElement dispatchDate = wait.until(ExpectedConditions.elementToBeClickable(dispatchDate_Field_Gramsevak));
		//Click on Dispatch Date field
		bocwUtil.doClickByJs(dispatchDate); 
		selectBDate("5","JAN","2012", dispatchDate_Field_Gramsevak );
		//Enter Dispatch Number
		bocwUtil.doSendKeys(dispatchNumber_Field_Gramsevak, dispatch_value); //Enter Dispatch Number			
		
	}
	
	//Enter Details when 'Contractor/Developer' option is choose from the drop-down
	public void inputContractorDeveloperDetails(String registeredWith_Contractor_Value, String registrationNumber_Value,
			String dispatchNumber_Value) throws Exception {
		
		WebElement registredWith_Element = wait.until(ExpectedConditions.elementToBeClickable(registeredWith_Field_Contractor));
		bocwUtil.doClickByJs(registredWith_Element);   //Clicks on Registered With field	
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, registeredWith_Contractor_Value);	  //Selects option from Registered With Drop down
		bocwUtil.doSendKeys(regNumberOfIssuer_Field_Contractor, registrationNumber_Value);   //Enter input into registration number field
		
		WebElement registrationNumber_Element = wait.until(ExpectedConditions.elementToBeClickable(regCertificateIssueDate_Field_Contractor));
		bocwUtil.doClickByJs(registrationNumber_Element); //Click on Registration Certificate Issue Date
		selectBDate("8","DEC","2023", regCertificateIssueDate_Field_Contractor); //Select the given date from the calendar
		
		WebElement dispatchNumber_Element = wait.until(ExpectedConditions.elementToBeClickable(dispatchNumber_Field_Contractor));
		bocwUtil.doClickByJs(dispatchNumber_Element);
		bocwUtil.doSendKeys(dispatchNumber_Field_Contractor, dispatchNumber_Value);
				
	}
	
	//Enter Details when 'Person with authorization by municipal corporation or municipal council' option is choose from the drop-down
	
	
	//Enter Details of 'Records of the 90 Days Working Certificate' 
	public void addRecordsOf90DaysWorkingCertificate (String typeOfEmployer_Value, String employerName_Value,
			String mobileNumber_Value) throws Exception {
		
		WebElement workRecordPanel_Element = driver.findElement(workRecordPanel);
		bocwUtil.scrollToElement(workRecordPanel_Element, driver);
		Thread.sleep(1000);
		WebElement addButton_Element = driver.findElement(addWorkRecords_button);
		bocwUtil.doClickByJs(addButton_Element); //Click on Add button to create record
		
		//Confirm if the Dialogue Box to fill Employer Details are Opened
		bocwUtil.doClick(typeOfEmployer_EmployerField);
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, typeOfEmployer_Value);
		bocwUtil.doSendKeys(employerName_EmployerField, employerName_Value);
		bocwUtil.doSendKeys(mobileNumber_EmployerField, mobileNumber_Value);
		selectBDate("4","JUN", "2024", fromDate);
		selectBDate("4","OCT", "2024", toDate);
		
		bocwUtil.doClick(save_EmployerDetails);
		
		//Display Total Working Days 
		Thread.sleep(1000);
		WebElement totalWorkingDay_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(totalWorkingDay));
		String workingDays = totalWorkingDay_Element.getText();
		System.out.println(workingDays);
		try {
			clickOnNextButton(5); }
		catch(Exception e) {
			clickOnNextButton(5);
		}
			
	}
	
	//Upload Applicant Photo
	public void uploadApplicatPhoto(String applicantPhoto_Path) throws Exception {
		Thread.sleep(1000);
		bocwUtil.doSendKeys(applicatePhoto, applicantPhoto_Path);
		System.out.println("Successfully Uploaded Applicant Phot");
	}
	
	//Upload Photo Id Phot
	public void uploadPhotoIdProof(String document_Value, String photoId_Path) throws Exception
	{
		//Click on Type Of Document dropdown
		WebElement photoIdProof_Element = driver.findElement(proofId_Dropdown);
		bocwUtil.doClickByJs(photoIdProof_Element);
		//Select the document type from the dropdown
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, document_Value);
		bocwUtil.doSendKeys(photoId, photoId_Path);	
		System.out.println("Successfully Uploaded Photo ID proof");
	}
	
	//Upload Proof of Age
	public void uploadAgeProof(String document_Value, String ageProofDoc_Path) throws Exception {
		
		//Click on Type of Document dropdown
		WebElement ageProof_Element = driver.findElement(proofAge_Dropdown);
		bocwUtil.doClickByJs(ageProof_Element);
		//Select the document type from the dropdown
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, document_Value);
		bocwUtil.doSendKeys(ageProof, ageProofDoc_Path);
		System.out.println("Age Prood document is successfully uploaded");
	}
	
	//Upload Photo Copy of Passbook
	public void uploadPassbook(String bankPassBook_Value, String passbook_Path) throws Exception {
		
		//Click on Dropdown of Bank Passbook
		WebElement bankPassBook_Element = driver.findElement(bankPassbook_Dropdown);
		bocwUtil.doClickByJs(bankPassBook_Element);
		//Select the document type from dropdown
		bocwUtil.getOptionsFromDropDown(dropdown, dropdownOption, bankPassBook_Value);
		bocwUtil.doSendKeys(bankPassbook, passbook_Path);
		System.out.println("Passbook is successfully uploaded");
		
	}
	
	//Upload Certificate of 90 Days
	public void uploadWorkingCertificate(String certificate_Path) {
		
		bocwUtil.doSendKeys(workingCertificate, certificate_Path);
		System.out.println("Work Certificate is successfully Uploaded");
	}
	
	//Upload Aadhaar Consent
	public void uploadAadhaarConsent(String aadhaarConsent_Path) {
		
		bocwUtil.doSendKeys(aadhaarConsent, aadhaarConsent_Path);
		System.out.println("Aadhaar Consent is successfully Uploaded");
	}
	
	//Upload Self Declaration
	public void uploadSelfDeclaration(String selfDeclaration_Path) {
		
		bocwUtil.doSendKeys(selfDeclaration, selfDeclaration_Path);	
		System.out.println("Self Declaration is successfully Uploaded");
	}
	
	//This Methods Clicks on Declaration Check box
	public void clickOnCheckbox() {
		
		WebElement checkbox_Element = driver.findElement(checkBox);
		bocwUtil.scrollToElement(checkbox_Element, driver);
		bocwUtil.doClick(checkBox);
		System.out.println("Clicked on declaration checkbox");
	}
	
	//This method clicks on Save Button to submit the form
	public Dashboard clickOnSaveButton() {
		WebElement saveButtonElement = driver.findElement(saveButton);
		wait.until(ExpectedConditions.visibilityOf(saveButtonElement)).click();;
		System.out.println(Constants.GREEN+ "Employee Form Saved Successfully" + Constants.RESET);
		return new Dashboard();
	}
	

	
	//This method is used to select the birthdate from the calender
 	public void selectBDate(String day, String month, String year, By locator) throws Exception {
	        // Step 1: Open the calendar
		    Thread.sleep(2000);
	        WebElement calendarIcon = wait.until(ExpectedConditions.elementToBeClickable(locator));
	        
	        try {
	        calendarIcon.click();
	        } catch (Exception e) {
	        	bocwUtil.doClickByJs(calendarIcon);
			}

	        // Step 2: Click the Month-Year button to open the year range card
	        WebElement monthYearButton = driver.findElement(By.xpath("//button[@aria-label='Choose month and year']"));
	        monthYearButton.click();

	        // Step 3: Select the desired year
	        while (true) {
	            List<WebElement> yearRange = driver.findElements(By.xpath("//tbody[@class='mat-calendar-body']//tr//td"));
	            boolean yearFound = false;

	            for (WebElement yearOption : yearRange) {
	                if (yearOption.getText().equals(year)) {
	                    yearOption.click();
	                    yearFound = true;
	                    break;
	                }
	            }

	            if (yearFound) break; // Exit loop if the desired year is found

	            // Navigate to the previous or next year range if the year is not in the current range
	            WebElement prevOrNextButton = driver.findElement(By.xpath("//button[@class='mat-calendar-previous-button mdc-icon-button mat-mdc-icon-button mat-unthemed mat-mdc-button-base']"));
	            prevOrNextButton.click();
	        }

	        // Step 4: Select the desired month
	        WebElement monthToSelect = driver.findElement(By.xpath("//button[contains(@class, 'mat-calendar-body-cell')]//span[text()=' "+month+" ']"));
	        monthToSelect.click();

	        // Step 5: Select the desired day
	        WebElement dayToSelect = driver.findElement(By.xpath("//button[contains(@class, 'mat-calendar-body-cell')]//span[text()=' "+day+" ']"));
	        dayToSelect.click();
	    }
	
}
	
