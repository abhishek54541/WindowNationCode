package com.qa.automation.ui.stepDefinations;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.qa.automation.actions.LaunchUrlAndClick;
import com.qa.automation.utils.PropFileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {

    private WebDriver driver;
    private LaunchUrlAndClick launchUrlAndClick;

    // Before hook to initialize the browser before each scenario
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Setting up browser for scenario: " + scenario.getName());

        // Initialize WebDriver
        driver = new ChromeDriver();
        launchUrlAndClick = new LaunchUrlAndClick(driver); // Initialize LaunchUrlAndClick with WebDriver

        // Get the URL from the properties file
        String url = PropFileHandler.readProperty("appUrl");
        if (url != null && !url.isEmpty()) {
            try {
                // Open the URL
                launchUrlAndClick.openUrl(url);
                System.out.println("Opened URL: " + url);
            } catch (Exception e) {
                System.err.println("Error opening URL: " + e.getMessage());
            }
        } else {
            System.err.println("Error: URL is not specified in the properties file.");
        }
    }

    // After hook to close the browser after each scenario
    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Tearing down browser for scenario: " + scenario.getName());

        if (driver != null) {
            // Log the result of the scenario
            if (scenario.isFailed()) {
                System.err.println("Scenario failed: " + scenario.getName());
                try {
                    // Take a screenshot
                    takeScreenshot(scenario.getName());
                } catch (IOException e) {
                    System.err.println("Error taking screenshot: " + e.getMessage());
                }
            } else {
                System.out.println("Scenario passed: " + scenario.getName());
            }

            try {
                // Close the browser
                driver.quit();
                System.out.println("Browser closed for scenario: " + scenario.getName());
            } catch (Exception e) {
                System.err.println("Error while closing the browser: " + e.getMessage());
            }
        } else {
            System.err.println("WebDriver was not initialized; skipping browser closure.");
        }
    }

    // Method to take a screenshot if the scenario fails
    private void takeScreenshot(String scenarioName) throws IOException {
        // Define the target folder from properties file
        String screenshotFolder = PropFileHandler.readProperty("screenshotFolder");

        // Create the folder if it doesn't exist
        Path path = Paths.get(screenshotFolder);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // Take the screenshot and save it in the target folder
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = screenshotFolder + File.separator + scenarioName + ".png";
        Files.copy(screenshot.toPath(), Paths.get(screenshotPath));

        System.out.println("Screenshot saved at: " + screenshotPath);
    }
}
