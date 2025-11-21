Feature: Account registration

  @Regression
  Scenario: Successful account registration
    Given user navigates to registration account page
    When users enters the below credentials
      | firstName | John       |
      | lastName  | inglis     |
      | telephone | 9611300000 |
      | password  | Yuiop17@   |
    And user select Privacy Policy
    And user clicks on Continue button
    Then user account creation should be successful
