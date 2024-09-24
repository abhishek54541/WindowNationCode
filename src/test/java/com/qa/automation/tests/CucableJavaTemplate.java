package com.qa.automation.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    // Use the placeholder for feature file paths
    features = {"src/test/resources/features/[CUCABLE:FEATURE].feature"},
    // Point to the package where step definitions are located
    glue = {"com.qa.automation.ui.stepDefinations"},
    // Ensure the Allure plugin is added
    plugin = {"pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
    // Set monochrome to true for better readability in the console output
    monochrome = true
)
public class CucableJavaTemplate {
}
