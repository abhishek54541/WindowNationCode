package com.qa.automation.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.automation.utils.BaseFunctions;

import java.util.NoSuchElementException;

import org.junit.Assert;

public class HomePage_Action extends BaseFunctions {

    private WebDriver driver;

    // Constructor to initialize the WebDriver
    public HomePage_Action(WebDriver driver) {
        super(); // Call the BaseFunctions constructor
        this.driver = driver; // Set the driver
    }

    public void launchUrl(String url) {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized");
        }
        if (url != null && !url.isEmpty()) {
            driver.get(url);
        } else {
            throw new IllegalArgumentException("URL is null or empty.");
        }
    }
    
    // Verify element present using locators 
    public void verifyElementIsPresent(By locator) {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized");
        }
        WebElement element = driver.findElement(locator);
        Assert.assertNotNull("The element is not present on the page", element);
        Assert.assertTrue("Element is not displayed", element.isDisplayed());
        System.out.println("Element verified: " + locator.toString());
    }

    public void clickOnIconFrameSecond() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized");
        }
        scrollUntilElementFound(Locators.ICON_FRAME_SECOND);
        WebElement element = driver.findElement(Locators.ICON_FRAME_SECOND);  
        // No need for utils, call the method from BaseFunctions directly
        waitForElementAndClick(element, 10);  // Use method from BaseFunctions
    }
    
    public void clickOnNextToProceedform() {
    	System.out.print("Click on next button");
    	WebElement element = driver.findElement(Locators.NEXT_STEP);
    	waitForElementAndClick(element, 10);
    }
    
    public void enterFirstNameAndLastName(String firstName,String lastName) {
    	System.out.print("Enter First and last name");
    	WebElement element = driver.findElement(Locators.FIRST_NAME);  // Replace Locators.INPUT_FIELD with your actual locator
        waitForElementAndSendKeys(element, firstName, 10);  //
        WebElement element_ = driver.findElement(Locators.LAST_NAME);  // Replace Locators.INPUT_FIELD with your actual locator
        waitForElementAndSendKeys(element_, lastName, 10);  //
    }
    
    public void enterPrimaryEmailAndPrimaryContact(String primaryEmail,String primaryContact) {
    	// Get element text
//    	String actualText = getElementText(Locators.GET_CONTACT_INFO_TEXT);
//        System.out.println("Text of the element: " + actualText);
    	System.out.print("Enter Primary email and primary contact");
    	WebElement element = driver.findElement(Locators.PRIMARY_EMAIL);  // Replace Locators.INPUT_FIELD with your actual locator
        waitForElementAndSendKeys(element, primaryEmail, 10);  //
        WebElement element_ = driver.findElement(Locators.PRIMARY_PHONE_NUMBER);  // Replace Locators.INPUT_FIELD with your actual locator
        waitForElementAndSendKeys(element_, primaryContact, 10);  //
    }
    
    
    public void clickOnElement(String locatorType, String locatorValue) {
        try {
            By dynamicLocator = getDynamicLocator(locatorType, locatorValue);

            // Locate the element using the constructed locator
            WebElement element = driver.findElement(dynamicLocator);

            // Wait for the element to be visible before clicking
            waitForElementToBeVisible(element);

            // Click on the element
            element.click();

            System.out.println("Clicked on element with " + locatorType + ": " + locatorValue);
        } catch (Exception e) {
            System.out.println("Unable to click on element with " + locatorType + ": " + locatorValue);
            e.printStackTrace();
        }
    }
    
    
    /**
     * Returns a By object based on the locator type.
     * 
     * @param locatorType Type of locator (e.g., "text")
     * @param locatorValue The value of the locator
     * @return By object corresponding to the locator type and value
     */
    
    private By getDynamicLocator(String locatorType, String locatorValue) {
        switch (locatorType.toLowerCase()) {
            case "text":
                return Locators.getDynamicXPathHowYouKnowBtn(locatorValue);
            case "anotherText": // Add additional cases as needed
                return Locators.getDynamicTypesOfProperty(locatorValue);
            default:
                throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
        }
    }
    
    // Enter address for eg - "Address1,Address2,city,State,zipcode"
    public void enterAddress(String address1,String address2,String city,String state,String zipcode) throws InterruptedException {
    	System.out.print("Enter Address (AddressCity, State, Zipcode)");
    	WebElement element = driver.findElement(Locators.STREET_ADDRESS_1); 
        waitForElementAndSendKeys(element, address1, 20); 
        sendKeysAndEnter(element, state, 10);
        sendKeysAndTab(element, state, 10);
//        Thread.sleep(5000);
        WebElement element_1 = driver.findElement(Locators.STREET_ADDRESS_2);  
        waitForElementAndSendKeys(element_1, address2, 10); 
        sendKeysAndTab(element, state, 10);
//        Thread.sleep(5000);
        WebElement element_2 = driver.findElement(Locators.CITY); 
        waitForElementAndSendKeys(element_2, city, 10);
        sendKeysAndTab(element, state, 10);
//        Thread.sleep(5000);
        WebElement element_3 = driver.findElement(Locators.STATE); 
        sendKeysAndEnter(element_3, state, 10);
//        Thread.sleep(5000);
        WebElement element_4 = driver.findElement(Locators.ZIPCODE); 
        waitForElementAndSendKeys(element_4, zipcode, 10);
//        Thread.sleep(5000);
        WebElement element_5 = driver.findElement(Locators.RESIDENCE);
        element_5.click();
        
    }
    
    public void enterNumberOfWindowsAndDoors(String windowCount, String doorCount ) throws InterruptedException {
    	// Count of windows and doors
    	WebElement element = driver.findElement(Locators.ENTER_NUMBER_OF_WINDOWS); 
        waitForElementAndSendKeys(element, windowCount, 5);
        clickOnNextToProceedform();
        Thread.sleep(10000);
        WebElement element_1 = driver.findElement(Locators.ENTER_NUMBER_OF_DOORS);  
        waitForElementAndSendKeys(element_1, doorCount, 5); 
        Thread.sleep(10000);
        clickOnNextToProceedform();
    }
    
    public void clickOnHomeOwnerButton() {
    	WebElement element = driver.findElement(Locators.HOME_OWNER); 
    	element.click();
    }
    
    public void clickOnReasonToReplaceWindow() throws InterruptedException {
    	Thread.sleep(10000);
    	WebElement element = driver.findElement(Locators.REASON_TO_REPLACE_WINDOW); 
    	element.click();
    	
    }
    
    // click on time span for eg : 0-3 months, 3-6 months and 6-9 months
    public void clickOnStartTimeSpanProject() throws InterruptedException {
    	Thread.sleep(10000);
    	WebElement element = driver.findElement(Locators.START_PROJECT_TIMELINE_IN_MONTHS);
    	Thread.sleep(10000);
    	element.click();
    	
    }
    
    // Failed method
    public void failTest() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized");
        }

        try {
            // Attempt to find and click the element
            WebElement element = driver.findElement(Locators.NEXT_STEP);
            element.click(); // This will throw NoSuchElementException if the element is not found
            System.out.println("Element clicked successfully.");
        } catch (NoSuchElementException e) {
            // Assert failure if element is not found
            Assert.fail("Expected element not found: " + Locators.NEXT_STEP);
        }
    }
    
}
