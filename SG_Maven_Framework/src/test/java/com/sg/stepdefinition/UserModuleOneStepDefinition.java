package com.sg.stepdefinition;

import com.sg.driver.CucumberTestRunner;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class UserModuleOneStepDefinition extends CucumberTestRunner {
    @Given("User Logins into the application")
    public void userLoginsIntoTheApplication(DataTable dataTable) {
        Assert.assertTrue(userOneMethod.verifyLoginFunctionality(oBrowser, dataTable));
    }

    @Then("Verify create user functionality in application")
    public void verifyCreateUserFunctionalityInApplication(DataTable dataTable) {
        userName = userOneMethod.createUser(oBrowser, dataTable);
    }

    @And("Verify delete user functionality")
    public void verifyDeleteUserFunctionality() {
        Assert.assertTrue(userOneMethod.deleteUser(oBrowser, userName));
    }

    @Then("Verify logout functionality in actiTime")
    public void verifyLogoutFunctionalityInActitime() {
        Assert.assertTrue(appDep.logoutFromActiTime(oBrowser));
    }
}
