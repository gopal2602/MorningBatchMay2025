package com.sg.baseclass;

import com.sg.driver.CucumberTestRunner;
import com.sg.pages.UsersPage;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class UserModuleTwoBaseClass extends CucumberTestRunner {
    public boolean verifyLoginFunctionality(WebDriver oBrowser, String appUrl, String userName, String password, String pageTitle){
        try{
            Assert.assertTrue(appDep.navigateURL(oBrowser, appUrl, pageTitle));
            Assert.assertTrue(appDep.loginToActiTime(oBrowser, userName, password));
            report.writeReport(oBrowser, "Screenshot", "Login to application was successful");
            return true;
        }catch(Exception e){
            report.writeReport(oBrowser, "Exception", "Exception in 'verifyLoginFunctionality()' method. " + e);
            return false;
        }
    }


    /****************************************************
     * Method Name      : createUser()
     * purpose          : it will create the new user in ActiTime
     * Author           : tester1
     * Parameters       : WebDriver
     * Call Statement   : String userName = createUser(oBrowser);
     * Return Type      : boolean
     ******************************************************/
    public String createUser(WebDriver oBrowser, String firstName, String lastName, String email, String usersUserName, String password, String retypePassword){
        String userName = null;
        try{
            Assert.assertTrue(appInd.clickObject(oBrowser, UsersPage.obj_USERS_Tab));
            appInd.waitForElement(oBrowser, UsersPage.obj_AddUser_Button, "Clickable", "", 10);

            Assert.assertTrue(appInd.clickObject(oBrowser, UsersPage.obj_AddUser_Button));
            appInd.waitForElement(oBrowser, UsersPage.obj_CreateUser_Button, "Clickable", "", 10);

            //enter required details to create the new user
            Assert.assertTrue(appInd.setObject(oBrowser, UsersPage.obj_User_FirstName_Edit, firstName));
            Assert.assertTrue(appInd.setObject(oBrowser, UsersPage.obj_User_LastName_Edit, lastName));
            Assert.assertTrue(appInd.setObject(oBrowser, UsersPage.obj_User_Email_Edit, email));
            Assert.assertTrue(appInd.setObject(oBrowser, UsersPage.obj_User_UserName_Edit, usersUserName));
            Assert.assertTrue(appInd.setObject(oBrowser, UsersPage.obj_User_Password_Edit, password));
            Assert.assertTrue(appInd.setObject(oBrowser, UsersPage.obj_User_RetypePassword_Edit, retypePassword));
            Assert.assertTrue(appInd.clickObject(oBrowser, UsersPage.obj_CreateUser_Button));
            userName = lastName + ", " + firstName;
            appInd.waitForElement(oBrowser, By.xpath(String.format(UsersPage.obj_UserName_Link, userName)), "Text", userName, 10);
            appInd.waitForElement(oBrowser, By.xpath(String.format(UsersPage.obj_UserName_Link, userName)), "Clickable", "", 20);

            boolean blnRes = appInd.verifyElementPresent(oBrowser, By.xpath(String.format(UsersPage.obj_UserName_Link, userName)));
            if(blnRes == true){
                report.writeReport(oBrowser, "Pass", "The new user created successful");
                return userName;
            }else{
                System.out.println();
                report.writeReport(oBrowser, "Fail", "Failed to create the new user.");
                return null;
            }
        }catch(Exception e){
            report.writeReport(oBrowser, "Exception", "Exception in 'createUser()' method: " + e.getMessage());
            return null;
        }
    }


    /****************************************************
     * Method Name      : deleteUser()
     * purpose          : it will delete the user in ActiTime
     * Author           : tester1
     * Parameters       : WebDriver
     * Call Statement   : deleteUser(oBrowser, "User1, SG");
     * Return Type      : boolean
     ******************************************************/
    public boolean deleteUser(WebDriver oBrowser, String userName){
        try{
            appInd.waitForElement(oBrowser, By.xpath(String.format(UsersPage.obj_UserName_Link, userName)), "Clickable", "", 20);
            appInd.clickObject(oBrowser, By.xpath(String.format(UsersPage.obj_UserName_Link, userName)));
            appInd.waitForElement(oBrowser, UsersPage.obj_DeleteUser_Button, "Clickable", "", 10);
            appInd.clickObject(oBrowser, UsersPage.obj_DeleteUser_Button);
            appInd.threadSleep(2000);
            oBrowser.switchTo().alert().accept();
            appInd.threadSleep(1000);

            //verify user deleted successful
            appInd.waitForElement(oBrowser, By.xpath(String.format(UsersPage.obj_UserName_Link, userName)), "Invisible", "", 10);
            boolean blnRes = appInd.verifyElementNotPresent(oBrowser, By.xpath(String.format(UsersPage.obj_UserName_Link, userName)));

            if(blnRes == true) {
                report.writeReport(oBrowser, "Pass", "User deleted successful");
                return true;
            }else {
                report.writeReport(oBrowser, "Fail", "Failed to delete the user");
                return false;
            }
        }catch(Exception e){
            report.writeReport(oBrowser, "Exception", "Exception in 'deleteUser()' method: " + e.getMessage());
            return false;
        }
    }
}
