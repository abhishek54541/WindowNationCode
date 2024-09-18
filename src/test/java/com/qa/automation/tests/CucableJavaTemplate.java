package com.qa.automation.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    // Update the feature path correctly
    features = {"src/test/resources/features"},
    // Point to the package where step definitions are located
    glue = {"com.qa.automation.ui.stepDefinations"},
    // Ensure the Allure plugin is added
    plugin = {"pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
    // Set strict to true to fail if any step is undefined
    // Set this to true if you want to see detailed output
    monochrome = true
)
public class CucableJavaTemplate {
}
