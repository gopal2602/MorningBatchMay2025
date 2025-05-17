Feature: Automating User Module test cases

@Regression123 @CreateDeleteUser @Cux @werew
Scenario Outline: Verify create and delete user functionality
  Given User Logins into the application with "<url>" "<userName>" "<password>" "<pageTitle>"
  Then Verify create user functionality with "<user_FirstName>" "<user_LastName>" "<user_Email>" "<user_UserName>" "<user_Password>" "<user_RetypePassword>"
  And Verify delete user functionality
  Then Verify logout functionality in actiTime

  Examples:
    | url                       | userName | password | pageTitle        | user_FirstName | user_LastName | user_Email      | user_UserName | user_Password | user_RetypePassword |
    | http://localhost/login.do | admin    | manager  | actiTIME - Login | sg             | user1         | sg.user1@sg.com | sguser1       | sgAdmin@123   | sgAdmin@123         |
    | http://localhost/login.do | admin    | manager  | actiTIME - Login | sg             | user1         | sg.user1@sg.com | sguser1       | sgAdmin@123   | sgAdmin@123         |


