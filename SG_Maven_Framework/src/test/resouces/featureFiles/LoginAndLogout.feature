Feature: Automating User Module test cases

  @Login @xyz @ABC @WWWWWWWWWW @EEEEEEEE
  Scenario: Verify Login and Logout functionality in actiTime
    Given user navigates the URL
      | url                       | pageTitle        |
      | http://localhost/login.do | actiTIME - Login |
    Then user enter the userName "admin"
    And user enter the password "manager"
    Then user clicks on Login button
    And verify login was successful
    Then user click on logout link
    And verify logout was successful

