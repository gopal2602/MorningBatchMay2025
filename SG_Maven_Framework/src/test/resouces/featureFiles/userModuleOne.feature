Feature: Automating User Module test cases

  Background:
    Given User Logins into the application
      | url                       | userName | password | pageTitle        |
      | http://localhost/login.do | admin    | manager  | actiTIME - Login |

@Regression @CreateDeleteUser @Cux @werew
Scenario: Verify create and delete user functionality
  Then Verify create user functionality in application
    | user_FirstName | user_LastName | user_Email      | user_UserName | user_Password | user_RetypePassword |
    | sg             | user1         | sg.user1@sg.com | sguser1       | sgAdmin@123   | sgAdmin@123         |
    And Verify delete user functionality
  Then Verify logout functionality in actiTime


  @Regression @CreateDeleteUser @Cux @werew
  Scenario: Verify create and delete user functionality
    Then Verify create user functionality in application
      | user_FirstName | user_LastName | user_Email      | user_UserName | user_Password | user_RetypePassword |
      | sg             | user1         | sg.user1@sg.com | sguser1       | sgAdmin@123   | sgAdmin@123         |
    And Verify delete user functionality
    Then Verify logout functionality in actiTime