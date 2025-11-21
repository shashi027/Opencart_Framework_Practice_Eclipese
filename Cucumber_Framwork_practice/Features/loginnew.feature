Feature: Login

  @Sanity
  Scenario: Successfull login with valid credentials
    Given user navigate to login page
    When user enters Email "17shashikumar@gmail.com" and password as "Yuiop17@"
    And Click on Login button
    Then user should redirect to MyAccount Page
