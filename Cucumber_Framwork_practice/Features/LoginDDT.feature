Feature: Login DDT

  Scenario Outline: Login with Excel data
    Given User Navigates to Login Page.
    Then User should redirect to Myaccount page by entering email and password with excel row "<row_index>"

    Examples: 
      | row_index |
      |         1 |
      |         2 |
      |         3 |
      |         4 |
      |         5 |
