package com.sg.stepdefinition;

import com.sg.driver.CucumberTestRunner;
import com.sg.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class LoginAndLogoutStepDefinition extends CucumberTestRunner {
    @Given("user navigates the URL")
    public void userNavigatesTheURL(DataTable dataTable) {
        List<Map<String, String>> inputData = dataTable.asMaps(String.class, String.class);
        boolean blnRes = appDep.navigateURL(oBrowser, inputData.get(0).get("url"), inputData.get(0).get("pageTitle"));
        report.writeReport(oBrowser, "screenshot", "URL Loaded successful");
        Assert.assertTrue(blnRes);
    }

    @Then("user enter the userName {string}")
    public void userEnterTheUserName(String username) {
        boolean blnRes = appInd.setObject(oBrowser, LoginPage.obj_UserName_Edit, username);
        report.writeReport(oBrowser, "screenshot", "Username entered successful");
        Assert.assertTrue(blnRes);
    }

    @And("user enter the password {string}")
    public void userEnterThePassword(String password) {
        boolean blnRes = appInd.setObject(oBrowser, LoginPage.obj_Password_Edit, password);
        report.writeReport(oBrowser, "screenshot", "Password entered successful");
        Assert.assertTrue(blnRes);
    }

    @Then("user clicks on Login button")
    public void userClicksOnLoginButton() {
        boolean blnRes = appInd.clickObject(oBrowser, LoginPage.obj_Login_Button);
        appInd.waitForElement(oBrowser, LoginPage.obj_HomePageTitle_Label, "Text", "Enter Time-Track", 10);
        report.writeReport(oBrowser, "screenshot", "login button clicked successful");
        Assert.assertTrue(blnRes);
    }

    @And("verify login was successful")
    public void verifyLoginWasSuccessful() {
        boolean blnRes = appInd.verifyText(oBrowser, LoginPage.obj_HomePageTitle_Label, "Text", "Enter Time-Track");
        report.writeReport(oBrowser, "screenshot", "login was successful");
        Assert.assertTrue(blnRes);
    }

    @Then("user click on logout link")
    public void userClickOnLogoutLink() {
        boolean blnRes = appInd.clickObject(oBrowser, LoginPage.obj_Logout_Link);
        appInd.waitForElement(oBrowser, LoginPage.obj_Login_Button, "Clickable", "", 10);
        report.writeReport(oBrowser, "screenshot", "Logout button clicked successful");
        Assert.assertTrue(blnRes);
    }

    @And("verify logout was successful")
    public void verifyLogoutWasSuccessful() {
        boolean blnRes = appInd.verifyText(oBrowser, LoginPage.obj_LoginPage_Lable, "Text", "Please identify yourself");
        report.writeReport(oBrowser, "screenshot", "login was successful");
        Assert.assertTrue(blnRes);
    }
}
