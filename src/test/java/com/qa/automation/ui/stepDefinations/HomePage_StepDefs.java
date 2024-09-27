package com.qa.automation.ui.stepDefinations;

import com.qa.automation.actions.HomePage_Action;
import com.qa.automation.actions.Locators;
import com.qa.automation.utils.BaseFunctions;
import com.qa.automation.utils.PropFileHandler;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;

public class HomePage_StepDefs extends BaseFunctions {

    private HomePage_Action homePageAction;

    // Constructor to initialize BaseFunctions and homePageAction
    public HomePage_StepDefs() {
        super(); // Initialize BaseFunctions
        this.homePageAction = new HomePage_Action(driver); // Initialize homePageAction with WebDriver
    }

    @Given("Open the WindowNation homepage")
    public void i_open_the_google_homepage() {
        // Open the windowNation home page
        String url = PropFileHandler.readProperty("appUrl");
        homePageAction.launchUrl(url);
        System.out.println("Opened app URL: " + url);
        
    }
    
    @Then("Verify the Home logo is present")
    public void i_verify_the_home_logo_is_present() {
        // Verify the element using the locator from the Locators class
        homePageAction.verifyElementIsPresent(Locators.HOME_LINK);
        System.out.println("Home logo is present and verified.");
    }

    @Then("Click on the product image icon")
    public void i_click_on_the_product_image_icon() {
        System.out.println("Attempting to click on the product image icon.");
        // Click the product image icon
        homePageAction.clickOnIconFrameSecond();
    }

    @Then("Click on the Next button")
    public void click_on_next() {
        System.out.println("Attempting to click on the second icon frame element.");
        // Call method to click the second icon frame
        homePageAction.clickOnNextToProceedform();
    }
    
    @Then("Enter first name {string} and last name {string}")
    public void iEnterFirstNameAndLastName(String firstName, String lastName) {
        homePageAction.enterFirstNameAndLastName(firstName, lastName);
//        homePageAction.clickOnElementWithText("Print");
//        homePageAction.clickOnElement("text", "Print");
        homePageAction.clickOnNextToProceedform();
    }
    
    @Then("Enter primary email {string} and primary contact {string}")
    public void i_enter_primary_email(String email, String contact) {
        // Use the email and contact parameters to perform actions
        System.out.println("Email: " + email);
        System.out.println("Contact: " + contact);
        // Assertion to verify that the contact contains only numerical digits
        Assert.assertTrue("Contact should contain only digits", contact.matches("\\d+"));
        homePageAction.enterPrimaryEmailAndPrimaryContact(email, contact);
        homePageAction.clickOnNextToProceedform();
   
    }
    
    @Then("Enter Address1 {string} address2 {string} city {string} state {string} and zipcode {string}")
    public void enter_quote_Home_address(String address1, String address2, String city, String state, String zipcode) throws InterruptedException {
        // Call the method from homePageAction to enter address details
        homePageAction.enterAddress(address1, address2, city, state, zipcode);
        
//        homePageAction.clickOnElement("anotherText", "Rental property");
        homePageAction.clickOnNextToProceedform();
    }
    
    @Then("select homeowner from two options I am the only home owner and There are multiple homeowners")
    public void select_homeowner_details() {
    	homePageAction.clickOnHomeOwnerButton();
    	homePageAction.clickOnNextToProceedform();
    }
    
    @Then("Enter windows count {string} and doors count {string}")
    public void enter_count_of_windows_and_doors(String windowCount, String doorCount) throws InterruptedException {
    	 homePageAction.enterNumberOfWindowsAndDoors(windowCount,doorCount);
    	 
    	 homePageAction.clickOnReasonToReplaceWindow();
    	 homePageAction.clickOnNextToProceedform();
    }
    
    @Then("click on how soon you start project list")
    public void click_on_start_project_span() throws InterruptedException {
    	homePageAction.clickOnStartTimeSpanProject();
    	homePageAction.clickOnNextToProceedform();
    }
    
    @Then("How did you hear about us")
    public void enter_how_do_you_here() {
    	homePageAction.clickOnElement("text", "Print");
    	homePageAction.clickOnNextToProceedform();
    }

    @Then("Force an intentional failure")
    public void i_force_a_failure() {
        System.out.println("Forcing a failure.");
        // Force the test to fail with a custom message
//        homePageAction.failTest();
       Assert.fail("Test case failed intentionally.");
    }
}
