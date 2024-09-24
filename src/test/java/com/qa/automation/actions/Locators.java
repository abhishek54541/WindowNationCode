package com.qa.automation.actions;

import org.openqa.selenium.By;

public class Locators {

	 public static final By ICON_FRAME_SECOND = By.xpath("(//span[@class='icon-frame'])[2]");
	 public static final By NEXT_STEP = By.xpath("//button[text()=' Next ']");
	 public static final By FIRST_NAME = By.xpath("//input[@id='firstName']");
	 public static final By LAST_NAME = By.xpath("//input[@id='lastName']");
	 public static final By DYNAMIC_XPATH_HOW_YOU_KNOW_BTN = By.xpath("//div[text()='\" + elementText + \"']");
	 public static final By HOME_LINK = By.xpath("//a[@class='header-logo-link' and @aria-label='Home']");
	 public static final By PRIMARY_EMAIL = By.xpath("//input[@id='primaryEmail']");
	 public static final By PRIMARY_PHONE_NUMBER = By.xpath("//input[@id='primaryPhone']");
	 public static final By SECONDARY_PHONE_NUMBER_ = By.xpath("//input[@id='secondaryPhone']");
	 public static final By GET_CONTACT_INFO_TEXT = By.xpath("//h2[contains(text(),'What is your contact info')]");
	 public static final By STREET_ADDRESS_1 = By.xpath("//input[@id='streetAddress1']");
	 public static final By STREET_ADDRESS_2 = By.xpath("//input[@id='streetAddress2']");
	 public static final By CITY = By.xpath("//input[@id='city']");
	 public static final By STATE = By.xpath("//input[@id='state']");
	 public static final By ZIPCODE = By.xpath("//input[@id='zipcode']");
	 public static final By ENTER_NUMBER_OF_WINDOWS = By.xpath("//input[@id='count']");
	 public static final By ENTER_NUMBER_OF_DOORS = By.xpath("//input[@id='count']");
	 public static final By START_PROJECT_TIMELINE_IN_MONTHS = By.xpath("//p[contains(text(),' 0-3 months ')]");
	 public static final By  REASON_TO_REPLACE_WINDOW= By.xpath("//p[contains(text(),' My windows leak or get condensation  ')]");
	 public static final By RESIDENCE = By.xpath("//*[contains(text(),'My primary residence')]");
	 public static final By HOME_OWNER = By.xpath("//*[contains(text(),'I am the only homeowner')]");
	 
	 public static final By ICON_FRAME_THIRD = By.xpath("(//span[@class='icon-frame'])[9]");
	 
	 
	 public static By getDynamicXPathHowYouKnowBtn(String text) {
		 return By.xpath("//div[contains(text(),'" + text + "')]");
	    }
	 public static By getDynamicTypesOfProperty(String typesProperties) {
		    // Escape any special characters in the input if necessary
		    String escapedProperties = typesProperties.replace("'", "\\'");

		    // Build the dynamic XPath using the provided typesProperties
		    return By.xpath("//*[contains(text(),'" + escapedProperties + "')]");
		}
	 
}
