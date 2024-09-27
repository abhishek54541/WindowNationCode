package com.qa.automation.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"@target/failedrerun.txt"}, 
                 glue = {"com.qa.automation.ui.stepDefinations"},
                 plugin = {})
public class FailedRunnerTests extends AbstractTestNGCucumberTests {
    // This class serves as the TestNG runner for the failed tests
}
