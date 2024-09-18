package com.qa.automation.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)    
@CucumberOptions( features = {"@target/failedrerun.txt"}, glue = {"com.qa.automation.ui.stepDefinations"},
                 plugin = {})
    
public class FailedRunnerTests {
    
}