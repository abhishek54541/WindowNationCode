package com.qa.automation.ui.stepDefinations;

import static org.testng.Assert.assertEquals;

import org.junit.Assert;

import com.qa.automation.actions.HomePage_Action;
import com.qa.automation.actions.Locators;
import com.qa.automation.actions.RequestACallPage_Action;
import com.qa.automation.utils.BaseFunctions;
import com.qa.automation.utils.PropFileHandler;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class RequestACallPage_StepDefs extends BaseFunctions {

    private RequestACallPage_Action requestCallPage;

    // Constructor to initialize BaseFunctions and homePageAction
    public RequestACallPage_StepDefs() {
        super(); // Initialize BaseFunctions
        this.requestCallPage = new RequestACallPage_Action(driver); // Initialize homePageAction with WebDriver
    }



    @Then("Click on the Request A call button")
    public void click_on_next()  {
        System.out.println("Attempting to click on Request A Call Button.");
        requestCallPage.clickOnRequestACallButton();
        switchToNewTab();
    }
  
    
    @Then("Enter first name {string} and last name {string} in request call page")
    public void EnterFirstNameAndLastName(String firstName, String lastName)  {
    	requestCallPage.enterFirstNameAndLastNameinRequestaCallPage(firstName, lastName);

    }
    
    @Then("Enter Email {string} and Phone {string} and zipCode {string} and submit")
    public void enter_primary_email(String email, String phoneNumber, String zipCode) {
        // Use the email and contact parameters to perform actions
        System.out.println("Email: " + email);
        System.out.println("phoneNumber: " + phoneNumber);
        System.out.println("zipCode: " + zipCode);
        
        // Assertion to verify that the contact contains only numerical digits
        Assert.assertTrue("Contact should contain only digits", phoneNumber.matches("\\d+"));
        requestCallPage.enterEmailAndPhoneNumberAndZipCode(email, phoneNumber, zipCode);
        requestCallPage.clickOnSubmitInfoBtn();
   
    }
    
    @Then ("User is landed to Request a Call Page")
    public void verify_Request_A_Call_screen()
    {
        assertEquals(requestCallPage.verifyReuestACallPageTxt(), true);
    }
    
    @Then ("User is landed to Submit form page")
    public void verify_Dasboard_screen()
    {
        assertEquals(requestCallPage.verifySubmitTxt(), true);
    }
    

}
