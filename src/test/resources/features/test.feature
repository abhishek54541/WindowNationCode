Feature: [CUCABLE:FEATURE]

  @navigate_homepage
  Scenario: Navigate to WindowNation homepage
    Given Open the WindowNation homepage
    Then Verify the Home logo is present
    Then Click on the product image icon
    Then Click on the Next button
    Then Enter first name "Anirudha" and last name "Singh"

  @project_info_form
  Scenario: Fill in ProjectInfo form
    Then Enter primary email "aniss@gmail.com" and primary contact "9342343244"
    Then Enter Address1 "testing street 1" address2 "testing street 2" city "New York" state "NY" and zipcode "10001"
    Then select homeowner from two options I am the only home owner and There are multiple homeowners
    Then Enter windows count "2" and doors count "3"
    Then click on how soon you start project list
    Then How did you hear about us 

  @intentional_failure
  Scenario: Trigger intentional failure for testing
    Given Open the WindowNation homepage
    Then Force an intentional failure
