Feature: DCE Request a Call Page

  @requestcall
  Scenario: Request a call from WN Team
    Given Open the WindowNation homepage
    Then Verify the Home logo is present
    When Click on the Request A call button
    Then User is landed to Request a Call Page
    Then Enter first name "Sharad" and last name "Kumar" in request call page
    Then Enter Email "example@gmail.com" and Phone "3412344521" and zipCode "30316" and submit
    And User is landed to Submit form page