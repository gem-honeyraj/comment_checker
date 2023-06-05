Feature: Admin User
  Scenario : User Management screen (Admin User)
    Given Enter Username and Password and validate <Validate_txt>,<username>,<password>
    Then Validate that Home page opens and logo is visible
    Then Click on <tab> and check that it got highlighted
    Then Check whether hamburgher button is present or not and clicking it minimizes the side bar