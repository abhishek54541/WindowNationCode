Feature: DCE Request a Call Page

  @requestcall
  Scenario: Navigate to WindowNation homepage
    Given Open the WindowNation homepage
    Then Verify the Home logo is present
    Then Click on the Request A call button
    Then Enter first name "Sharad" and last name "Kumar" in request call page
    Then Enter Email "sharadK@gmail.com" and Phone "3412344521" and zipCode "30301" and submit