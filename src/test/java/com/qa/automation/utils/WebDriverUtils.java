package com.qa.automation.utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WebDriverUtils {

    private WebDriver driver;

    public WebDriverUtils(WebDriver driver) {
        this.driver = driver;
    }

    // Method to wait for element to be visible and send keys
    public void waitForElementAndSendKeys(WebElement element, String text, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));  // Wait for element to be visible
        element.clear();  // Clear any existing text (optional)
        element.sendKeys(text);  // Send the provided text
    }
}
