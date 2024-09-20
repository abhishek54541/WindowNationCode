package com.qa.automation.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.automation.utils.BaseFunctions;

import java.util.NoSuchElementException;

import org.junit.Assert;

public class RequestACallPage_Action extends BaseFunctions {

    private WebDriver driver;

    // Constructor to initialize the WebDriver
    public RequestACallPage_Action(WebDriver driver) {
        super(); // Call the BaseFunctions constructor
        this.driver = driver; // Set the driver
    }
 
    public void clickOnRequestACallButton()  {
    	System.out.print("Click on Request a Call button");
    	WebElement element = driver.findElement(Locators.REQUEST_A_CALL);
    	waitForElementAndClick(element, 10);
    }
    
    public void enterFirstNameAndLastNameinRequestaCallPage(String firstName,String lastName)  {
    	System.out.print("Enter First and last name");
    	WebElement element = driver.findElement(Locators.FIRST_NAME);  // Replace Locators.INPUT_FIELD with your actual locator
        waitForElementAndSendKeys(element, firstName, 10);  //
        WebElement element_ = driver.findElement(Locators.LAST_NAME);  // Replace Locators.INPUT_FIELD with your actual locator
        waitForElementAndSendKeys(element_, lastName, 10);  //
    }
    
    public void enterEmailAndPhoneNumberAndZipCode(String primaryEmail,String phoneNumber, String zipCode) {
    	// Get element text
    	System.out.print("Enter Phone Number and Email");
    	WebElement element = driver.findElement(Locators.PHONE_NUMBER);  // Replace Locators.INPUT_FIELD with your actual locator
        waitForElementAndSendKeys(element, phoneNumber, 10);  //
        WebElement element_ = driver.findElement(Locators.EMAIL);  // Replace Locators.INPUT_FIELD with your actual locator
        waitForElementAndSendKeys(element_, primaryEmail, 10);  //
        WebElement element_1 = driver.findElement(Locators.ZIP_CODE);  // Replace Locators.INPUT_FIELD with your actual locator
        waitForElementAndSendKeys(element_1, zipCode, 10);
    }
    
    public void clickOnSubmitInfoBtn() {
    	System.out.print("Click on Submit Info button");
    	WebElement element = driver.findElement(Locators.SUBMIT);
    	waitForElementAndClick(element, 10);
    }
    
    public boolean verifyReuestACallPageTxt() {
        boolean flag = driver.findElement(Locators.REQUEST_A_CALL_TXT).isDisplayed();
        return flag;
 
    }
    
    public boolean verifySubmitTxt() {
        boolean flag = driver.findElement(Locators.SUBMIT_TXT).isDisplayed();
        return flag;
 
    }
    
}    
    
    
    