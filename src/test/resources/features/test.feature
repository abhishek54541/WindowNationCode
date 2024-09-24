Feature: DCE Test Cases

  @navigate_homepage
  Scenario: Navigate to WindowNation homepage
    Given Open the WindowNation homepage
    Then Verify the Home logo is present
    Then Click on the product image icon
    Then Click on the Next button
    Then Enter first name "Ani" and last name "Rudha"

  @project_info_form
  Scenario: Fill in ProjectInfo form
    Then Enter primary email "ani@gmail.com" and primary contact "9876543456"
    Then Enter Address1 "testing street1" address2 "Apt 4B" city "New York" state "NY" and zipcode "10001"
    Then select homeowner from two options I am the only home owner and There are multiple homeowners
    Then Enter windows count "2" and doors count "3"

  @intentional_failure
  Scenario: Trigger intentional failure for testing
    Given Open the WindowNation homepage
    Then Force an intentional failure
