package com.sg.stepdefinition;

import com.sg.driver.CucumberTestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class UserModuleTwoStepDefinition extends CucumberTestRunner {
    @Given("User Logins into the application with {string} {string} {string} {string}")
    public void userLoginsIntoTheApplicationWith(String appUrl, String userName, String password, String pageTitle) {
        Assert.assertTrue(userTwoMethod.verifyLoginFunctionality(oBrowser, appUrl, userName, password, pageTitle));
    }

    @Then("Verify create user functionality with {string} {string} {string} {string} {string} {string}")
    public void verifyCreateUserFunctionalityWith(String firstName, String lastName, String email, String userUserName, String password, String retypePassword) {
        userName = userTwoMethod.createUser(oBrowser, firstName, lastName, email, userUserName, password, retypePassword);
    }
}
